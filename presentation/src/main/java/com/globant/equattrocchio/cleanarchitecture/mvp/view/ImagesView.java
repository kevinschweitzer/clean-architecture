package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter.RecyclerViewAdapter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.fragments.ImageDialogFragment;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.domain.model.CompleteImage;
import com.globant.equattrocchio.domain.model.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void showText(String text) {

    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void showError() {
        Toast.makeText(getContext(),"Ha ocurrido un error", Toast.LENGTH_SHORT);
    }

    public void setImages(List<Image> images){
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(images);
        recyclerView.setAdapter(adapter);
    }

    public void showImageDialog(CompleteImage image){
        FragmentManager fragmentManager = getFragmentManager();
        ImageDialogFragment.newInstance(image).show(fragmentManager,"fragment");
    }
}
