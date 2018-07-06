package com.globant.equattrocchio.data.mapper;

import com.globant.equattrocchio.data.response.ImageEntity;
import com.globant.equattrocchio.data.response.ImagesResponse;
import com.globant.equattrocchio.domain.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImagesResponseMapper {

    public ImagesResponseMapper(){}

    public List<Image> map(ImagesResponse result){
        List<Image> images = new ArrayList<Image>();
        if(result!=null) {
            for (ImageEntity i : result.getImages()) {
                ImageMapper imageMapper = new ImageMapper();
                Image image = imageMapper.map(i);
                images.add(image);
            }
        }
        return images;
    }

}
