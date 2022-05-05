package com.kumu.trent.server.request;

import android.content.Context;

import com.kumu.trent.config.Keys;
import com.kumu.trent.config.Url;
import com.kumu.trent.data.model.api.TrackModel;
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

/**
 * Handling of api calls using retrofit library
 */

public class Auth {

    public static Auth getDefault(){
        return new Auth();
    }

    public void search(Context context, String term, String country, String media,String all) {
        APIRequest apiRequest = new APIRequest<CollectionTransformer<TrackModel>>(context) {
            @Override
            public Call<CollectionTransformer<TrackModel>> onCreateCall() {
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

    public void detail(Context context, int id) {
        APIRequest apiRequest = new APIRequest<CollectionTransformer<TrackModel>>(context) {
            @Override
            public Call<CollectionTransformer<TrackModel>> onCreateCall() {
                return getRetrofit().create(RequestService.class).requestDetail(Url.getLookup(), getMultipartBody());
            }

            @Override
            public void onResponse() {
                EventBus.getDefault().post(new SearchResponse(this));
            }
        };

        apiRequest
                .addParameter("id", id)
                .execute();

    }



    public interface RequestService {
        @Multipart
        @POST("{p}")
        Call<CollectionTransformer<TrackModel>> requestSearch(@Path(value = "p", encoded = true) String p, @Part List<MultipartBody.Part> part);

        @Multipart
        @POST("{p}")
        Call<CollectionTransformer<TrackModel>> requestDetail(@Path(value = "p", encoded = true) String p, @Part List<MultipartBody.Part> part);
    }


    public class LoginResponse extends APIResponse<BaseTransformer> {
        public LoginResponse(APIRequest apiRequest) {
            super(apiRequest);
        }
    }

    public class SearchResponse extends APIResponse<CollectionTransformer<TrackModel>> {
        public SearchResponse(APIRequest apiRequest) {
            super(apiRequest);
        }
    }

    public class DetailResponse extends APIResponse<CollectionTransformer<TrackModel>> {
        public DetailResponse(APIRequest apiRequest) {
            super(apiRequest);
        }
    }
}
