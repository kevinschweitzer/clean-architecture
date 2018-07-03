package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImageDialogPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageDialogView;
import com.globant.equattrocchio.data.ImagesRepositoryImpl;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.RefreshImagesUseCase;
import com.globant.equattrocchio.domain.service.ImagesRepository;
import com.globant.equattrocchio.domain.service.ImagesServices;

public class ImageDialogFragment extends DialogFragment {

    private ImageDialogPresenter presenter;

    private static final String ID_KEY = "ID_IMAGE";


    public static ImageDialogFragment newInstance(int id){
        Bundle args = new Bundle();
        args.putSerializable(ID_KEY,id);
        ImageDialogFragment fragment = new ImageDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.partial_image_dialog, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImagesServices imagesServices = new ImagesServicesImpl();
        ImagesRepository imagesRepository = new ImagesRepositoryImpl();
        GetImageByIdUseCase getImageByIdUseCase = new GetImageByIdUseCase(imagesServices);
        RefreshImagesUseCase refreshImagesUseCase = new RefreshImagesUseCase(imagesRepository);
        int imageId = (int)getArguments().getSerializable(ID_KEY);
        presenter = new ImageDialogPresenter(new ImageDialogView(this),getImageByIdUseCase,refreshImagesUseCase,imageId);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
    }

}