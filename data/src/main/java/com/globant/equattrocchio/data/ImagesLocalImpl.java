package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.mapper.ImageMapper;
import com.globant.equattrocchio.data.response.ImageEntity;
import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.service.ImagesLocal;

import java.util.List;
import java.util.Observer;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ImagesLocalImpl implements ImagesLocal {

    private static final String ID_FIELD = "id";
    private RealmResults<ImageEntity> images;


    @Override
    public void addImages(List<Image> images) {
        ImageMapper mapper = new ImageMapper();
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            for (Image image : images) {
                ImageEntity imageEntity = mapper.map(image);
                realm.copyToRealm(imageEntity);
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
    public void delete(int id){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        ImageEntity imageEntity = realm.where(ImageEntity.class).equalTo(ID_FIELD, id).findFirst();
        imageEntity.deleteFromRealm();

        realm.commitTransaction();
    }


}
