package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesRepository;

import io.realm.RealmChangeListener;

public class RefreshImagesUseCase {

    private ImagesRepository imagesLocal;

    public RefreshImagesUseCase(ImagesRepository imagesLocal){
        this.imagesLocal = imagesLocal;
    }

    public void addChangeListener(RealmChangeListener changeListener){
        imagesLocal.setOnChangeListener(changeListener);
    }

    public void delete(int id){
        imagesLocal.delete(id);
    }
}
