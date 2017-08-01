package com.dev.pyar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user2 on 8/1/2017.
 */

public class StandardResolution {
    @SerializedName("url")
    @Expose
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
