package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.SaveInstanceObserver;
import com.globant.equattrocchio.data.ImagesRepositoryImpl;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.RefreshImagesUseCase;
import com.globant.equattrocchio.domain.SaveImagesUseCase;
import com.globant.equattrocchio.domain.service.ImagesRepository;
import com.globant.equattrocchio.domain.service.ImagesServices;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private ImagesPresenter presenter;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        ImagesServices imagesServices = new ImagesServicesImpl();
        ImagesRepository imagesLocal = new ImagesRepositoryImpl();
        GetLatestImagesUseCase getLatestImagesUseCase = new GetLatestImagesUseCase(imagesServices);
        GetImageByIdUseCase getImageByIdUseCase = new GetImageByIdUseCase(imagesServices);
        SaveImagesUseCase saveImagesUseCase = new SaveImagesUseCase(imagesServices);
        RefreshImagesUseCase refreshImagesUseCase = new RefreshImagesUseCase(imagesLocal);
        presenter = new ImagesPresenter(new ImagesView(this),getLatestImagesUseCase,getImageByIdUseCase,saveImagesUseCase,refreshImagesUseCase);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();

        if(savedInstanceState!=null){
            RxBus.post(new SaveInstanceObserver.SaveInstance());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }
}