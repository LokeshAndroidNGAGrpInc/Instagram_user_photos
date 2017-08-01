package com.dev.pyar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user2 on 8/1/2017.
 */

public class Images {
    @SerializedName("standard_resolution")
    @Expose
    StandardResolution mStandardResolution;

    public StandardResolution getStandardResolution() {
        return mStandardResolution;
    }

    public void setStandardResolution(StandardResolution standardResolution) {
        mStandardResolution = standardResolution;
    }
}
