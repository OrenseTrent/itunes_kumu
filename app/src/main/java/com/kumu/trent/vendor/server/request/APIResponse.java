package com.kumu.trent.vendor.server.request;

import com.kumu.trent.vendor.android.java.Log;
import com.kumu.trent.vendor.server.transformer.BaseTransformer;
import com.kumu.trent.vendor.server.util.InvalidToken;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;



public class APIResponse<T extends BaseTransformer> {

    public int id;

    private boolean isNext = false;
    private boolean isPrev = false;
    private boolean isFirst = false;
    private boolean success;

    public boolean isNext() {
        return isNext;
    }

    public boolean isPrev() {
        return isPrev;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isSuccess() {
        return apiRequest.getResponse() != null && getCode() == 200;
    }

    private APIRequest apiRequest;

    public APIResponse(APIRequest apiRequest){
        this.apiRequest = apiRequest;
        switch (apiRequest.getDataResponseType()) {
            case DEFAULT:
                break;
            case NEXT:
                isNext = true;
                break;
            case PREV:
                isPrev = true;
                break;
            case FIRST:
                isFirst = true;
                break;
        }

        this.success = true;
    }

    public int getCode() {
        return apiRequest.getResponse().code();
    }

    public String getMessage() {
        return apiRequest.getResponse().message();
    }

    public T getData(Class K) {
        return processResponse(K);
    }

    public T getData() {
        return processResponse(BaseTransformer.class);
    }

    public T processResponse(Class K){
        if ( apiRequest.getResponse().code() >= 400 && apiRequest.getResponse().code() < 599 ) {
            Converter<ResponseBody, T> converter = apiRequest.getRetrofit().responseBodyConverter(K, new Annotation[0]);
            try {
                T temp = converter.convert(apiRequest.getResponse().errorBody());
                if (apiRequest.checkToken && temp.status_code.equals("TOKEN_EXPIRED")) {
                    EventBus.getDefault().post(InvalidToken.newInstance());
                }
                return temp;
            } catch (IOException e) {
                Log.dd("ResponseData Error", e.getMessage());
                return (T) new BaseTransformer();
            }
        } else {
            return (T) apiRequest.getResponse().body();
        }
    }
}
