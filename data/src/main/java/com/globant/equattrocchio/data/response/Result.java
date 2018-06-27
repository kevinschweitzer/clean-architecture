package com.globant.equattrocchio.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("images")
    @Expose
    private List<Image> mImages = null;

    public List<Image> getImages() {
        return mImages;
    }

    public void setImages(List<Image> images) {
        this.mImages = images;
    }

}