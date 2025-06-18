package com.simplecity.amp_library.http.lastfm;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class LastFmAlbum implements LastFmResult {

    @SerializedName("album")
    private Album album;

    @Override
    public String getImageUrl() {
        return album != null ? LastFmUtils.getBestImageUrl(album.getImages()) : null;
    }

    public Album getAlbum() {
        return album;
    }

    public static class Album {
        @SerializedName("name")
        private String name;

        @SerializedName("image")
        private List<LastFmImage> images = new ArrayList<>();

        @SerializedName("wiki")
        private Wiki wiki;

        public String getName() {
            return name;
        }

        public List<LastFmImage> getImages() {
            return images;
        }

        public Wiki getWiki() {
            return wiki;
        }
    }

    public static class Wiki {
        @SerializedName("summary")
        private String summary;

        public String getSummary() {
            return summary;
        }
    }
}