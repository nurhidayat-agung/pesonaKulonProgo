package com.gits.developer.pesonakulonprogo.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gits.developer.pesonakulonprogo.R;

/**
 * Created by annasblackhat on 11/29/16.
 */

public class DestinasiNearbyAdapter extends RecyclerView.Adapter<DestinasiNearbyAdapter.ViewHolderNearby>{
    @Override
    public ViewHolderNearby onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderNearby(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_popup_map, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderNearby holder, int position) {
        switch (position){
            case 0 :
                holder.ivPopUpMarker.setImageResource(R.drawable.mangrove);
                holder.tvPlaceTitle.setText("Mangrove Congot");
                break;
            case 1 :
                holder.ivPopUpMarker.setImageResource(R.drawable.congot1);
                holder.tvPlaceTitle.setText("Pantai Congot");
                break;
            case 2 :
                holder.ivPopUpMarker.setImageResource(R.drawable.glagah1);
                holder.tvPlaceTitle.setText("Pantai Glagah");
                break;
            case 3 :
                holder.ivPopUpMarker.setImageResource(R.drawable.trisik1);
                holder.tvPlaceTitle.setText("Pantai Trisik");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
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
