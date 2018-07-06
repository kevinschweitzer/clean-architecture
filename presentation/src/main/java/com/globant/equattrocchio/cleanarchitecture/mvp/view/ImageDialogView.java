package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.DeleteClickedObserver;
import com.globant.equattrocchio.domain.model.CompleteImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageDialogView extends DialogFragmentView {

    @BindView(R.id.text_id) TextView idText;
    @BindView(R.id.text_url) TextView urlText;
    @BindView(R.id.text_site) TextView siteText;
    @BindView(R.id.text_copyright) TextView copyrightText;
    @BindView(R.id.image_view) ImageView imageView;

    public ImageDialogView(DialogFragment fragment){
        super(fragment);
    }

    public void showImage(CompleteImage image){
       // Activity activity = fragment.getActivity();
        if(image!=null && getFragment()!=null) {
            ButterKnife.bind(this,getFragment().getDialog());
            Glide.with(getFragment()).load(image.getUrl()).into(imageView);
            idText.setText(image.getId()+"");
            urlText.setText(image.getUrl());
            siteText.setText(image.getSite());
            copyrightText.setText(image.getCopyright());
        }

    }

    @OnClick(R.id.btn_delete)
    public void onDeletedClicked(){
        RxBus.post(new DeleteClickedObserver.DeleteClicked());
    }

    public void showMessage(int resourceId) {
        Activity activity = getFragment().getActivity();
        if(activity!=null)  Toast.makeText(activity,resourceId,Toast.LENGTH_SHORT).show();
    }

    public void hide() {
        getFragment().dismiss();
    }
}
