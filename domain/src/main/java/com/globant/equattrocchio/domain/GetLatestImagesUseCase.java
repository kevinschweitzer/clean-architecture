package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.model.Images;
import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetLatestImagesUseCase extends UseCase<Images,Void> {

    private ImagesServices imagesServices;

    public GetLatestImagesUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Images> observer, Void aVoid) {
        imagesServices.getLatestImages(observer);
    }
}
