package com.kumu.trent.server.request;

import android.content.Context;

import com.kumu.trent.config.Keys;
import com.kumu.trent.config.Url;
import com.kumu.trent.data.model.api.SampleModel;
import com.kumu.trent.vendor.server.request.APIRequest;
import com.kumu.trent.vendor.server.request.APIResponse;
import com.kumu.trent.vendor.server.transformer.BaseTransformer;
import com.kumu.trent.vendor.server.transformer.CollectionTransformer;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public class Auth {

    public static Auth getDefault(){
        return new Auth();
    }



    public void search(Context context, String term, String country, String media,String all) {
        APIRequest apiRequest = new APIRequest<CollectionTransformer<SampleModel>>(context) {
            @Override
            public Call<CollectionTransformer<SampleModel>> onCreateCall() {
                return getRetrofit().create(RequestService.class).requestSearch(Url.getSearch(), getMultipartBody());
            }

            @Override
            public void onResponse() {
                EventBus.getDefault().post(new SearchResponse(this));
            }
        };

        apiRequest
                .addParameter(Keys.TERM, term)
                .addParameter(Keys.COUNTRY, country)
                .addParameter(Keys.MEDIA, media)
                .addParameter("all",all)
                .addParameter("limit", 50)
                .execute();

    }



    public interface RequestService {
        @Multipart
        @POST("{p}")
        Call<CollectionTransformer<SampleModel>> requestSearch(@Path(value = "p", encoded = true) String p, @Part List<MultipartBody.Part> part);
    }


    public class LoginResponse extends APIResponse<BaseTransformer> {
        public LoginResponse(APIRequest apiRequest) {
            super(apiRequest);
        }
    }

    public class SearchResponse extends APIResponse<CollectionTransformer<SampleModel>> {
        public SearchResponse(APIRequest apiRequest) {
            super(apiRequest);
        }
    }
}
