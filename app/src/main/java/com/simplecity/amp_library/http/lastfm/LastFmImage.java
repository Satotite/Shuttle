package com.simplecity.amp_library.http.lastfm;

import com.google.gson.annotations.SerializedName;

public class LastFmImage {

    @SerializedName("#text")
    private String url;

    @SerializedName("size")
    private String size;

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }
}