package com.globant.equattrocchio.data.mapper;

import com.globant.equattrocchio.data.response.ImageEntity;
import com.globant.equattrocchio.domain.model.Image;

public class ImageMapper {


    public Image map(ImageEntity imageEntity){
        Image image = new Image();
        image.setId(imageEntity.getId());
        //image.setSourceId(imageEntity.getSourceId());
        image.setLargeUrl(imageEntity.getLargeUrl());
        image.setUrl(imageEntity.getUrl());

        return image;
    }
}
