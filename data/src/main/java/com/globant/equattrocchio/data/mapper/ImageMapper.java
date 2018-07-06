package com.globant.equattrocchio.data.mapper;

import com.globant.equattrocchio.data.response.ImageEntity;
import com.globant.equattrocchio.domain.model.Image;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class ImageMapper {


    public Image map(ImageEntity imageEntity){
        Image image = new Image();
        image.setId(imageEntity.getId());
        image.setSourceId(imageEntity.getSourceId());
        image.setLargeUrl(imageEntity.getLargeUrl());
        image.setUrl(imageEntity.getUrl());

        return image;
    }

    public ImageEntity map(Image image){
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(image.getId());
        imageEntity.setLargeUrl(image.getLargeUrl());
        imageEntity.setUrl(image.getUrl());
        imageEntity.setSourceId(image.getSourceId());

        return imageEntity;
    }

    public List<Image> map(RealmResults<ImageEntity> images){
        List<Image> imageList = new ArrayList<>();
        for (ImageEntity image:images) {
            imageList.add(map(image));
        }

        return imageList;
    }
}
