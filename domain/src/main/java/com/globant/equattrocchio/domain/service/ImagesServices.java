package com.globant.equattrocchio.domain.service;

import com.globant.equattrocchio.domain.model.CompleteImage;
import com.globant.equattrocchio.domain.model.Image;

import java.util.List;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(Observer<List<Image>> observer);

    void getImageById(Observer<CompleteImage> observer, long id);

    void saveImages(Observer<Boolean> observer);
}
