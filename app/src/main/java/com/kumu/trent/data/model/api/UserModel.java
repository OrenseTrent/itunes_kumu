package com.kumu.trent.data.model.api;

import com.kumu.trent.vendor.android.base.AndroidModel;
import com.google.gson.annotations.SerializedName;



public class UserModel extends AndroidModel {
    @SerializedName("name")
    public String name;

    @Override
    public String toString() {
        return convertToString(this);
    }

    @Override
    public UserModel convertFromJson(String json) {
        return convertFromJson(json, UserModel.class);
    }
}
