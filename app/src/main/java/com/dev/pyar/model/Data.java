package com.dev.pyar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user2 on 8/1/2017.
 */

public class Data {
    @SerializedName("images")
    @Expose
    Images mImages;

    public Images getImages() {
        return mImages;
    }

    public void setImages(Images images) {
        mImages = images;
    }
}
