package com.globant.equattrocchio.domain.service;

import com.globant.equattrocchio.domain.model.Images;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(Observer<Images> observer);
}
