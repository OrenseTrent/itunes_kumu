package com.kumu.trent.vendor.server.transformer;

import com.google.gson.annotations.SerializedName;



public class BaseTransformer {

    @SerializedName("msg")
    public String msg = "Internal Server Error";

    @SerializedName("status")
    public Boolean status = false;

    @SerializedName("status_code")
    public String status_code = "RETROFIT_FAILED";

    @SerializedName("token")
    public String token = "";

    @SerializedName("new_token")
    public String new_token = "";

    @SerializedName("has_morepages")
    public Boolean has_morepages = false;

    @SerializedName("hasRequirements")
    public boolean hasRequirements(){
        return false;
    }

    public boolean checkEmpty(Object obj){
        return obj !=  null;
    }
}
