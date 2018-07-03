package com.globant.equattrocchio.domain.service;


import com.globant.equattrocchio.domain.model.Image;

import java.util.List;

import io.realm.RealmChangeListener;

public interface ImagesRepository {

     void addImages(List<Image> images);

     void setOnChangeListener(RealmChangeListener changeListener);

     public void delete(int id);
}
