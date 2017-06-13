package com.gits.developer.pesonakulonprogo.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.home.HomeDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annasblackhat on 11/29/16.
 */

public class DestinasiNearbyAdapter extends RecyclerView.Adapter<DestinasiNearbyAdapter.ViewHolderNearby>{
    private Context context;
    private List<HomeDataModel> nearPlaces = new ArrayList<>();

    public DestinasiNearbyAdapter(Context context, List<HomeDataModel> nearPlace) {
        this.context = context;
        this.nearPlaces = nearPlace;
    }

    @Override
    public ViewHolderNearby onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderNearby(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_popup_map, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderNearby holder, int position) {
        Glide.with(context)
                .load(nearPlaces.get(position).getImage().get(0).getUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.ivPopUpMarker.setImageBitmap(resource);
                    }
                });
        holder.tvPlaceTitle.setText(nearPlaces.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.nearPlaces.size();
    }


    class ViewHolderNearby extends RecyclerView.ViewHolder{
        public ImageView ivPopUpMarker;
        public TextView tvPlaceTitle;

        public ViewHolderNearby(View itemView) {
            super(itemView);
            ivPopUpMarker = (ImageView) itemView.findViewById(R.id.iv_pop_up_marker);
            tvPlaceTitle = (TextView) itemView.findViewById(R.id.place_title);
        }
    }
}
