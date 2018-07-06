package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.service.ImagesRepository;

import java.util.List;

import io.realm.RealmChangeListener;

public class RefreshImagesUseCase {

    private ImagesRepository imagesRepository;

    public RefreshImagesUseCase(ImagesRepository imagesLocal){
        this.imagesRepository = imagesLocal;
    }

    public void addChangeListener(RealmChangeListener changeListener){
        imagesRepository.setOnChangeListener(changeListener);
    }

    public void delete(long id){
        imagesRepository.delete(id);
    }

    public List<Image> getImagesFromRepository(){
        return imagesRepository.getImagesFromRepository();
    }

    public void deleteAll(){
        imagesRepository.deleteAll();
    }

}
