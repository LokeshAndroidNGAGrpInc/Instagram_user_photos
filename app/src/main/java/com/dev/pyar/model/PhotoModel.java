package com.dev.pyar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user2 on 8/1/2017.
 */

public class PhotoModel {
    @SerializedName("data")
    @Expose
    List<Data>   mDataList= null;

    public List<Data> getDataList() {
        return mDataList;
    }

    public void setDataList(List<Data> dataList) {
        mDataList = dataList;
    }
}
