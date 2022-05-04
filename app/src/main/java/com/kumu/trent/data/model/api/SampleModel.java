package com.kumu.trent.data.model.api;

import com.kumu.trent.vendor.android.base.AndroidModel;
import com.google.gson.annotations.SerializedName;


public class SampleModel extends AndroidModel {


    @SerializedName("wrapperType")
    public String wrapperType;
    @SerializedName("kind")
    public String kind;
    @SerializedName("collectionId")
    public Integer collectionId;
    @SerializedName("trackId")
    public Integer trackId;
    @SerializedName("artistName")
    public String artistName;
    @SerializedName("collectionName")
    public String collectionName;
    @SerializedName("trackName")
    public String trackName;
    @SerializedName("collectionCensoredName")
    public String collectionCensoredName;
    @SerializedName("trackCensoredName")
    public String trackCensoredName;
    @SerializedName("collectionArtistId")
    public Integer collectionArtistId;
    @SerializedName("collectionArtistViewUrl")
    public String collectionArtistViewUrl;
    @SerializedName("collectionViewUrl")
    public String collectionViewUrl;
    @SerializedName("trackViewUrl")
    public String trackViewUrl;
    @SerializedName("previewUrl")
    public String previewUrl;
    @SerializedName("artworkUrl30")
    public String artworkUrl30;
    @SerializedName("artworkUrl60")
    public String artworkUrl60;
    @SerializedName("artworkUrl100")
    public String artworkUrl100;
    @SerializedName("collectionPrice")
    public Double collectionPrice;
    @SerializedName("trackPrice")
    public Double trackPrice;
    @SerializedName("trackRentalPrice")
    public Double trackRentalPrice;
    @SerializedName("collectionHdPrice")
    public Double collectionHdPrice;
    @SerializedName("trackHdPrice")
    public Double trackHdPrice;
    @SerializedName("trackHdRentalPrice")
    public Double trackHdRentalPrice;
    @SerializedName("releaseDate")
    public String releaseDate;
    @SerializedName("collectionExplicitness")
    public String collectionExplicitness;
    @SerializedName("trackExplicitness")
    public String trackExplicitness;
    @SerializedName("discCount")
    public Integer discCount;
    @SerializedName("discNumber")
    public Integer discNumber;
    @SerializedName("trackCount")
    public Integer trackCount;
    @SerializedName("trackNumber")
    public Integer trackNumber;
    @SerializedName("trackTimeMillis")
    public Integer trackTimeMillis;
    @SerializedName("country")
    public String country;
    @SerializedName("currency")
    public String currency;
    @SerializedName("primaryGenreName")
    public String primaryGenreName;
    @SerializedName("contentAdvisoryRating")
    public String contentAdvisoryRating;
    @SerializedName("shortDescription")
    public String shortDescription;
    @SerializedName("longDescription")
    public String longDescription;
    @SerializedName("hasITunesExtras")
    public Boolean hasITunesExtras;

    @Override
    public String toString() {
        return convertToString(this);
    }

    @Override
    public SampleModel convertFromJson(String json) {
        return convertFromJson(json, SampleModel.class);
    }
}
