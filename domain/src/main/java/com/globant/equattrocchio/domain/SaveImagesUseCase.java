package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class SaveImagesUseCase extends UseCase<Boolean,Void>{

    private ImagesServices imagesServices;

    public SaveImagesUseCase(ImagesServices imagesServices){
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Boolean> observer, Void aVoid) {
        imagesServices.saveImages(observer);
    }
}
