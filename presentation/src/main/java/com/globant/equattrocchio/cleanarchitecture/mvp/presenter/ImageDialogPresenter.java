package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageDialogView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.DeleteClickedObserver;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.RefreshImagesUseCase;
import com.globant.equattrocchio.domain.model.CompleteImage;

import io.reactivex.observers.DisposableObserver;

public class ImageDialogPresenter {

    private ImageDialogView view;
    private GetImageByIdUseCase getImageByIdUseCase;
    private RefreshImagesUseCase refreshImagesUseCase;
    private int imageId;

    public ImageDialogPresenter(ImageDialogView view, GetImageByIdUseCase getImageByIdUseCase, RefreshImagesUseCase refreshImagesUseCase, int imageId){
        this.view = view;
        this.getImageByIdUseCase = getImageByIdUseCase;
        this.refreshImagesUseCase = refreshImagesUseCase;
        this.imageId = imageId;
        getImageById(imageId);
    }

    public void getImageById(int id){
        getImageByIdUseCase.execute(new DisposableObserver<CompleteImage>() {
            @Override
            public void onNext(CompleteImage image) {
                //Show image in fragment
                view.showImage(image);
            }

            @Override
            public void onError(Throwable e) {
                view.showMessage(R.string.error_image_id);
            }

            @Override
            public void onComplete() {

            }
        },id);
    }


    public void onDeleteClicked(){
        refreshImagesUseCase.delete(imageId);
    }


    public void register() {
        RxBus.subscribe(view.getActivity(), new DeleteClickedObserver() {
            @Override
            public void onEvent(DeleteClicked value) {
                onDeleteClicked();
            }
        });

    }

}
