package com.kumu.trent.vendor.android.base;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;



public class AndroidModel {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    public int id;

    public String convertToString(Object src){
        new Gson().fromJson("", AndroidModel.class);
        return new Gson().toJson(src);
    }

    public <T> T convertFromJson(String json){
        return convertFromJson(json, null);
    }

    public <T> T convertFromJson(String json, Class<T> classOfT){
        return new Gson().fromJson(json, classOfT);
    }

    @Override
    public boolean equals(Object obj) {
        return (this.id == ((AndroidModel) obj).id);
    }
}