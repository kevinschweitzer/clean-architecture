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
    private long imageId;

    public ImageDialogPresenter(ImageDialogView view, GetImageByIdUseCase getImageByIdUseCase, RefreshImagesUseCase refreshImagesUseCase, long imageId){
        this.view = view;
        this.getImageByIdUseCase = getImageByIdUseCase;
        this.refreshImagesUseCase = refreshImagesUseCase;
        this.imageId = imageId;
        getImageById(imageId);
    }

    public void getImageById(long id){
        getImageByIdUseCase.execute(new DisposableObserver<CompleteImage>() {
            @Override
            public void onNext(CompleteImage image) {
                //Show image in fragment
                showImageInFragment(image);
            }

            @Override
            public void onError(Throwable e) {
                showError(R.string.error_image_id);
            }

            @Override
            public void onComplete() {

            }
        },id);
    }

    public void showImageInFragment(CompleteImage image){
        view.showImage(image);
    }

    public void showError(int stringId){
        view.showMessage(stringId);
    }

    public void onDeleteClicked(){
        refreshImagesUseCase.delete(imageId);
        view.hide();
    }


    public void register() {
        Activity activity = view.getFragment().getActivity();
        if(activity!=null){
            RxBus.subscribe(activity, new DeleteClickedObserver() {
                @Override
                public void onEvent(DeleteClicked value) {
                    onDeleteClicked();
                }
            });
        }


    }

}
