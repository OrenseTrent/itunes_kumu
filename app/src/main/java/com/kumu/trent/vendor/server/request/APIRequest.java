package com.kumu.trent.vendor.server.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.provider.Settings;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kumu.trent.config.Keys;
import com.kumu.trent.config.Url;
import com.kumu.trent.vendor.android.java.Log;
import com.kumu.trent.vendor.server.transformer.BaseTransformer;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIRequest<T extends BaseTransformer> implements Callback<T> {

    private final String DEVICE_NAME = "android";

    private Context context;
    private List<MultipartBody.Part> multipartBody;
    private HashMap<String, String> params;
    private Retrofit retrofit;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    private int page = 1;
    private int perPage = 5;
    private boolean hasMorePage = false;
    private boolean showNoInternetConnection = true;
    private String authorization = "xxxxxx";
    private DataResponseType dataResponseType = DataResponseType.DEFAULT;
    public boolean checkToken = true;
    public boolean isCanceled = false;

    public enum DataResponseType {
        DEFAULT,
        NEXT,
        PREV,
        FIRST
    }

    public APIRequest(Context context){
        this.context = context;

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json").build();
                        return chain.proceed(request);
                    }
                })
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Url.APP)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public APIRequest addParameter(String key, Object object) {

        if (multipartBody == null) {
            multipartBody = new ArrayList<>();
        }

        if(object instanceof File){
            File file = (File) object;
            multipartBody.add(MultipartBody.Part.createFormData(key, file.getName(), RequestBody.create(MediaType.parse("image/*"), file)));
        }else{
            multipartBody.add(MultipartBody.Part.createFormData(key, String.valueOf(object)));
        }

        if (params == null) {
            params = new HashMap<>();
        }

        params.put(key, String.valueOf(object));
        return this;
    }

    public Call<T> onCreateCall() {

        return null;
    }

    public interface Service{

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public String getAuthorization() {
        return authorization;
    }

    public String getDeviceName() {
        return DEVICE_NAME;
    }

    public String getDeviceID() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public DataResponseType getDataResponseType() {
        return dataResponseType;
    }

    public List<MultipartBody.Part> getMultipartBody(){
        if (multipartBody == null) {
            multipartBody = new ArrayList<>();
        }

        List<MultipartBody.Part> parts = multipartBody;
        parts.add(MultipartBody.Part.createFormData(Keys.PAGE, String.valueOf(getPage())));
        parts.add(MultipartBody.Part.createFormData(Keys.PER_PAGE, String.valueOf(getPerPage())));
        parts.add(MultipartBody.Part.createFormData(Keys.DEVICE_ID, getDeviceID()));
        parts.add(MultipartBody.Part.createFormData(Keys.DEVICE_NAME, getDeviceName()));

        return parts;
    }

    public HashMap<String, String> getParameter(){
        if (params == null) {
            params = new HashMap<>();
        }

        HashMap<String, String> parts = params;
        parts.put(Keys.PAGE, String.valueOf(getPage()));
        parts.put(Keys.PER_PAGE, String.valueOf(getPerPage()));
        parts.put(Keys.DEVICE_ID, getDeviceID());
        parts.put(Keys.DEVICE_NAME, getDeviceName());

        return parts;
    }

    public APIRequest addAuthorization(String authorization) {
        this.authorization = "Bearer " + authorization;
        return this;
    }

    public void onResponse(){

    }

    public void broadcast(Object event){
        EventBus.getDefault().post(event);
    }

    /** for pagination */
    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public APIRequest setPage(int page) {
        this.page = page;
        return this;
    }

    public APIRequest setPerPage(int perPage) {
        this.perPage = perPage;
        return this;
    }

    private void setDataResponseType(DataResponseType dataResponseType) {
        this.dataResponseType = dataResponseType;
    }


    public APIRequest setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
        return this;
    }

    public APIRequest showDefaultProgressDialog(String message) {
        this.progressDialog = new ProgressDialog(context).show(context, "", message, false, false);
        return this;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public APIRequest setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        return this;
    }

    public APIRequest showSwipeRefreshLayout(boolean b) {
        if (getSwipeRefreshLayout() != null) {
            getSwipeRefreshLayout().setRefreshing(b);
        }
        return this;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }


    /** Commands */
    public void execute() {
        setDataResponseType(DataResponseType.DEFAULT);
        run();
    }

    public void nextPage() {
        setDataResponseType(DataResponseType.NEXT);
        if (hasMorePage) {
            setPage(getPage() + 1);
        }
        run();
    }

    public void previousPage() {
        setDataResponseType(DataResponseType.PREV);
        if (getPage() > 1) {
            setPage(getPage() - 1);
        }
        run();
    }

    public void first() {
        setDataResponseType(DataResponseType.FIRST);
        setPage(1);
        run();
    }

    public void run() {
        isCanceled = false;
        onCreateCall().enqueue(this);
    }

    public void cancelRequest() {
        isCanceled = true;
        onCreateCall().cancel();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.e("APIRequest", response.code());
        Log.e("APIRequest","URL: "+response.raw().request().url());
        Log.e("APIRequest","BODY: " + response.raw().toString());

        dismissLoading();
        if(!isCanceled){
            this.response = response;
            onResponse();
        }
    }

    private Response<T> response;
    public Response<T> getResponse(){
        return response;
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        dismissLoading();
        if(!isCanceled){
            onResponse();
        }
    }

    private void dismissLoading(){
        if (getProgressDialog() != null) {
            getProgressDialog().dismiss();
        }
        if (getSwipeRefreshLayout() != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public class OnFailedResponse{

    }
}

