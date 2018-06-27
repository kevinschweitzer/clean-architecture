package com.globant.equattrocchio.domain.model;

import java.util.ArrayList;

public class Images {

    private ArrayList<Image> images;

    public Images(){
        images = new ArrayList<Image>();
    }

    public void add(Image image){
        images.add(image);
    }

    public ArrayList<Image> getList(){
        return images;
    }

}
