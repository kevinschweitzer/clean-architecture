package com.globant.equattrocchio.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("images")
    @Expose
    private List<ImageEntity> mImages = null;

    public List<ImageEntity> getImages() {
        return mImages;
    }

    public void setImages(List<ImageEntity> images) {
        this.mImages = images;
    }

}