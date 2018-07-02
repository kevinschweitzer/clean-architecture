package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.DeleteClickedObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageClickedObserver;
import com.globant.equattrocchio.domain.model.Image;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Image> images;

    public RecyclerViewAdapter(List<Image> images){
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_card_view,parent,false);
        return new ViewHolder(parent,v,images);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Image image = images.get(position);
        holder.getIdTextView().setText(image.getId()+"");
        Glide.with(holder.getImageView().getContext()).load(image.getUrl()).into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final List<Image> images;
        @BindView (R.id.text_view) TextView idTextView;
        @BindView(R.id.image_view) ImageView imageView;

        public ViewHolder(ViewGroup parent,View view, List<Image> images) {
            super(view);
            this.images = images;
            ButterKnife.bind(this,view);
        }

        public TextView getIdTextView(){
            return idTextView;
        }


        public ImageView getImageView(){
            return imageView;
        }


        @OnClick(R.id.card_view)
        public void onHolderClicked(View view){
            Image image = images.get(getAdapterPosition());
            RxBus.post(new ImageClickedObserver.ImageClicked(image.getId()));
        }

    }
}
