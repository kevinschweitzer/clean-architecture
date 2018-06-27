package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.tv_incoming_json) TextView tvlabel;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void showText(String text) {
        tvlabel.setText(text);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void showError() {
        tvlabel.setText(R.string.connection_error);
    }
}
