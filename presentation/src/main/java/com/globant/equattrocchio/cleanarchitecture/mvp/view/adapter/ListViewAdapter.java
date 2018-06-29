package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageClickedObserver;
import com.globant.equattrocchio.domain.model.Image;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewAdapter extends ArrayAdapter<Image> {

    @BindView(R.id.text_view) TextView textView;
    @BindView(R.id.image_view) ImageView imageView;

    public ListViewAdapter(@NonNull Context context, ArrayList<Image> images) {
        super(context,0,images);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Image image = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.partial_card_view, parent, false);
        }
        ButterKnife.bind(this, convertView);
       // ImageView imageView = convertView.findViewById(R.id.image_view);
        //TextView textView = convertView.findViewById(R.id.text_view);
        Glide.with(this.getContext()).load(image.getUrl()).into(imageView);
        textView.setText(image.getId()+"");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.post(new ImageClickedObserver.ImageClicked(image.getId()));
            }
        });

        return convertView;

    }

}
