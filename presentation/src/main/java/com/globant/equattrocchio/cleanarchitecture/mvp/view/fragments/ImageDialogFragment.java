package com.globant.equattrocchio.cleanarchitecture.mvp.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.DeleteClickedObserver;
import com.globant.equattrocchio.domain.model.CompleteImage;
import com.globant.equattrocchio.domain.model.Image;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageDialogFragment extends DialogFragment {

    private static final String IMAGE_KEY = "SOLO_IMAGE";
    @BindView(R.id.text_id) TextView idText;
    @BindView(R.id.text_url) TextView urlText;
    @BindView(R.id.text_site) TextView siteText;
    @BindView(R.id.text_copyright) TextView copyrightText;
    @BindView(R.id.image_view) ImageView imageView;

    public static ImageDialogFragment newInstance(CompleteImage image){
        Bundle args = new Bundle();
        args.putSerializable(IMAGE_KEY,image);
        ImageDialogFragment fragment = new ImageDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);
        CompleteImage image = (CompleteImage)getArguments().getSerializable(IMAGE_KEY);
        if(image!=null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.partial_image_dialog, null);
            ButterKnife.bind(this,view);
            Glide.with(getActivity()).load(image.getUrl()).into(imageView);
            idText.setText(image.getId()+"");
            urlText.setText(image.getUrl());
            siteText.setText(image.getSite());
            copyrightText.setText(image.getCopyright());
            return new AlertDialog.Builder(getActivity()).setView(view).create();
        }

        return null;

    }

    @OnClick(R.id.btn_delete)
    public void onDeletedClicked(){
        CompleteImage image = (CompleteImage)getArguments().getSerializable(IMAGE_KEY);
        RxBus.post(new DeleteClickedObserver.DeleteClicked(image.getId()));
    }
}
