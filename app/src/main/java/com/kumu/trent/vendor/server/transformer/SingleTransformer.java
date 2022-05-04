package com.kumu.trent.vendor.server.transformer;

import com.google.gson.annotations.SerializedName;



public class SingleTransformer<T> extends BaseTransformer{

    @SerializedName("results")
    public T results;
}
