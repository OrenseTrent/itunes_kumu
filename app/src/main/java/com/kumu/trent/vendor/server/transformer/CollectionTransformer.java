package com.kumu.trent.vendor.server.transformer;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class CollectionTransformer<T> extends BaseTransformer{

    @SerializedName("results")
    public List<T> results;
}
