package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesLocal;

import io.realm.RealmChangeListener;

public class RefreshImagesUseCase {

    private ImagesLocal imagesLocal;

    public RefreshImagesUseCase(ImagesLocal imagesLocal){
        this.imagesLocal = imagesLocal;
    }

    public void addChangeListener(RealmChangeListener changeListener){
        imagesLocal.setOnChangeListener(changeListener);
    }

    public void delete(int id){
        imagesLocal.delete(id);
    }
}
