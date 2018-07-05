package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageClickedObserver;
import com.globant.equattrocchio.domain.model.Image;

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
        return new ViewHolder(parent,v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Image image = images.get(position);
        holder.setId(image.getId());
        holder.idTextView.setText(image.getId()+"");
        Glide.with(holder.imageView.getContext()).load(image.getUrl()).into(holder.imageView);
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
        private long imageId;
        @BindView (R.id.text_view) TextView idTextView;
        @BindView(R.id.image_view) ImageView imageView;

        public ViewHolder(ViewGroup parent,View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        public void setId(long imageId){
            this.imageId = imageId;
        }


        @OnClick(R.id.card_view)
        public void onHolderClicked(View view){
            RxBus.post(new ImageClickedObserver.ImageClicked(imageId));
        }

    }
}
