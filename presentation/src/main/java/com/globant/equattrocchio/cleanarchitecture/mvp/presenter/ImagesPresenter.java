package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;
import android.util.Log;
import android.view.Display;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.DeleteClickedObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageClickedObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.RefreshClickedObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.SaveInstanceObserver;
import com.globant.equattrocchio.data.mapper.ImageMapper;
import com.globant.equattrocchio.data.response.ImageEntity;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.RefreshImagesUseCase;
import com.globant.equattrocchio.domain.SaveImagesUseCase;
import com.globant.equattrocchio.domain.model.CompleteImage;
import com.globant.equattrocchio.domain.model.Image;
import com.google.gson.Gson;

import java.util.List;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ImagesPresenter {

    private ImagesView view;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageByIdUseCase getImageByIdUseCase;
    private SaveImagesUseCase saveImagesUseCase;
    private RefreshImagesUseCase refreshImagesUseCase;

    public ImagesPresenter(final ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase, GetImageByIdUseCase getImageByIdUseCase, SaveImagesUseCase saveImagesUseCase, RefreshImagesUseCase refreshImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
        this.getImageByIdUseCase = getImageByIdUseCase;
        this.saveImagesUseCase = saveImagesUseCase;
        this.refreshImagesUseCase = refreshImagesUseCase;

        RealmChangeListener changeListener = new RealmChangeListener<RealmResults<ImageEntity>>() {
            @Override
            public void onChange(RealmResults<ImageEntity> images) {
                //Refresh View
                List<Image> imageList = (new ImageMapper()).map(images);
                Log.i("Images changeListener",(new Gson()).toJson(imageList));
                view.setImages(imageList);
            }
        };
        refreshImagesUseCase.addChangeListener(changeListener);
    }

    public void onCallServiceButtonPressed() {

        getLatestImagesUseCase.execute(new DisposableObserver<List<Image>>() {
            @Override
            public void onNext(@NonNull List<Image> images) {
                setImagesInView(images);
            }

            @Override
            public void onError(@NonNull Throwable e) {
               showError(R.string.connection_error);
            }

            @Override
            public void onComplete() {

            }
        },null);

        //todo acá tengo que llamar a la domain layer para que llame a la data layer y haga el llamdo al servicio
    }

    public void setImagesInView(List<Image> images){
        view.setImages(images);
    }

    public void onImagePressed(long id){
        view.showImageDialog(id);
    }


    public void onRefreshClicked(){
        saveImagesUseCase.execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                onNextRefreshClicked(aBoolean);
            }

            @Override
            public void onError(Throwable e) {
                showError(R.string.refresh_error);
            }

            @Override
            public void onComplete() {

            }
        },null);
    }


    public void onNextRefreshClicked(boolean aBoolean){
        if(aBoolean){
            view.showToast(R.string.refresh_success);
        }
        else{
            view.showToast(R.string.refresh_error);
        }
    }

    public void showError(int stringId){
        view.showToast(stringId);

    }

    public void conserveInstance(){
        List<Image> images = refreshImagesUseCase.getImagesFromRepository();
        view.setImages(images);
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

        RxBus.subscribe(activity, new RefreshClickedObserver() {
            @Override
            public void onEvent(RefreshClicked value) {
                onRefreshClicked();
            }
        });

        RxBus.subscribe(activity, new SaveInstanceObserver() {
            @Override
            public void onEvent(SaveInstance value) {
                conserveInstance();
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
