package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.model.CompleteImage;
import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetImageByIdUseCase extends UseCase<CompleteImage,Integer> {

    private ImagesServices imagesServices;

    public GetImageByIdUseCase(ImagesServices imagesServices){
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<CompleteImage> observer, Integer i) {
        imagesServices.getImageById(observer,i);
    }
}
