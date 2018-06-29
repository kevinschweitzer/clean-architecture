package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageClickedObserver;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.model.CompleteImage;
import com.globant.equattrocchio.domain.model.Image;
import java.util.List;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ImagesPresenter {

    private ImagesView view;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageByIdUseCase getImageByIdUseCase;

    public ImagesPresenter(ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase, GetImageByIdUseCase getImageByIdUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
        this.getImageByIdUseCase = getImageByIdUseCase;
    }

    private void onCallServiceButtonPressed() {

        getLatestImagesUseCase.execute(new DisposableObserver<List<Image>>() {
            @Override
            public void onNext(@NonNull List<Image> images) {
                view.setImages(images);
            }

            @Override
            public void onError(@NonNull Throwable e) {
               view.showError();
            }

            @Override
            public void onComplete() {

            }
        },null);

        //todo acá tengo que llamar a la domain layer para que llame a la data layer y haga el llamdo al servicio
    }

    private void onImagePressed(int id){
        getImageByIdUseCase.execute(new DisposableObserver<CompleteImage>() {
            @Override
            public void onNext(CompleteImage image) {
                //Show image in fragment
                view.showImageDialog(image);
            }

            @Override
            public void onError(Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {

            }
        },id);
    }



    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new CallServiceButtonObserver() {
            @Override
            public void onEvent(CallServiceButtonPressed event) {
                onCallServiceButtonPressed();
            }
        });

        RxBus.subscribe(activity, new ImageClickedObserver() {
            @Override
            public void onEvent(ImageClicked value) {
                onImagePressed(value.getId());
            }
        });

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }
}
