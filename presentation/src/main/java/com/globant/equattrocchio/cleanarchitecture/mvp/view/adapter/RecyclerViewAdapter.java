package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter;

import android.support.annotation.NonNull;
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
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageClickedObserver;
import com.globant.equattrocchio.domain.model.Image;

import java.util.List;

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
        holder.getIdTextView().setText(image.getId()+"");
        Glide.with(holder.getParent().getContext()).load(image.getUrl()).into(holder.getImageView());
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.post(new ImageClickedObserver.ImageClicked(image.getId()));
            }
        });
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
        private final TextView idTextView;
        private final ImageView imageView;
        private final ViewGroup parent;
        private final View view;

        public ViewHolder(ViewGroup parent,View view) {
            super(view);
            this.view = view;
            this.parent = parent;
            idTextView = (TextView)itemView.findViewById(R.id.text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }

        public TextView getIdTextView(){
            return idTextView;
        }


        public ImageView getImageView(){
            return imageView;
        }

        public ViewGroup getParent(){
            return parent;
        }

        public View getView(){ return view; }
    }
}
