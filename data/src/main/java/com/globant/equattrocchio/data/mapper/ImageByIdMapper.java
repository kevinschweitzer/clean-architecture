package com.globant.equattrocchio.data.mapper;

import com.globant.equattrocchio.data.response.ImageByIdResponse;
import com.globant.equattrocchio.domain.model.CompleteImage;

public class ImageByIdMapper {


    public CompleteImage map(ImageByIdResponse imageById){
        CompleteImage image = new CompleteImage();
        image.setId(imageById.getId());
        image.setUrl(imageById.getUrl());
        image.setLargeUrl(imageById.getLargeUrl());
        image.setSourceId(imageById.getSourceId());
        image.setCopyright(imageById.getCopyright());
        image.setSite(imageById.getSite());

        return image;
    }
}
