package com.globant.equattrocchio.data.mapper;

import com.globant.equattrocchio.data.response.ImageEntity;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.model.Images;

public class ResultMapper {

    public ResultMapper(){}

    public Images map(Result result){
        Images images = new Images();
        for (ImageEntity i: result.getImages()) {
            Image image = new Image();
            image.setId(i.getId());
            image.setUrl(i.getUrl());
            image.setLargeUrl(i.getLargeUrl());
            image.setSourceId(i.getSourceId());

            images.add(image);
        }
        return images;
    }

}
