package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter.RecyclerViewAdapter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImageDialogFragment;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.RefreshClickedObserver;
import com.globant.equattrocchio.domain.model.Image;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @Nullable @BindView(R.id.toolbar)Toolbar toolbar;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        if(toolbar!=null && activity!=null){
            activity.setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.app_name);
        }
    }

    public void showText(String text) {

    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }


    public void setImages(List<Image> images){
        Activity activity = getActivity();
        // use a linear layout manager
        if(activity!=null){
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(images);
            recyclerView.setAdapter(adapter);
        }

    }


    public void showImageDialog(long imageId){
        FragmentManager fragmentManager = getFragmentManager();
        ImageDialogFragment.newInstance(imageId).show(fragmentManager,"fragment");
    }

    @OnClick(R.id.btn_refresh)
    public void saveImages(){
        RxBus.post(new RefreshClickedObserver.RefreshClicked());
    }

    public void showToast(int stringId){
        Context context = getContext();
        if(context!=null) Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show();
    }



}
