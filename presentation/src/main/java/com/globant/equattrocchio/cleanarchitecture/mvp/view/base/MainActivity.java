package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.service.ImagesServices;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private ImagesPresenter presenter;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageByIdUseCase getImageByIdUseCase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImagesServices imagesServices = new ImagesServicesImpl();
        getLatestImagesUseCase = new GetLatestImagesUseCase(imagesServices);
        getImageByIdUseCase = new GetImageByIdUseCase(imagesServices);
        presenter = new ImagesPresenter(new ImagesView(this),getLatestImagesUseCase,getImageByIdUseCase);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }
}