package com.globant.equattrocchio.data;

import android.util.Log;

import com.globant.equattrocchio.data.mapper.ImageMapper;
import com.globant.equattrocchio.data.response.ImageEntity;
import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.service.ImagesRepository;
import com.google.gson.Gson;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ImagesRepositoryImpl implements ImagesRepository {

    private static final String ID_FIELD = "id";
    private RealmResults<ImageEntity> images;


    @Override
    public void addImages(List<Image> images) {
        ImageMapper mapper = new ImageMapper();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        try {
            for (Image image : images) {
                ImageEntity imageEntity = mapper.map(image);
                realm.insertOrUpdate(imageEntity);
            }
            realm.commitTransaction();
        } finally {
            realm.close();
        }
    }

    @Override
    public void setOnChangeListener(RealmChangeListener changeListener) {
        Realm realm = Realm.getDefaultInstance();
        images = realm.where(ImageEntity.class).findAll();
        images.addChangeListener(changeListener);
    }

    @Override
    public void delete(long id){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        ImageEntity imageEntity = realm.where(ImageEntity.class).equalTo(ID_FIELD, id).findFirst();

        if(imageEntity!=null) imageEntity.deleteFromRealm();

        realm.commitTransaction();

    }

    @Override
    public void deleteAll() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageEntity> results = realm.where(ImageEntity.class).findAll();

        realm.beginTransaction();

        results.deleteAllFromRealm();

        realm.commitTransaction();
    }

    @Override
    public List<Image> getImagesFromRepository() {
        Realm realm = Realm.getDefaultInstance();

        return (new ImageMapper()).map(realm.where(ImageEntity.class).findAll());
    }


}
