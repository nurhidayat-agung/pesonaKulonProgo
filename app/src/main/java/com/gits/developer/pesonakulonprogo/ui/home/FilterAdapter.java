package com.gits.developer.pesonakulonprogo.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gits.developer.pesonakulonprogo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sasha Grey on 9/6/2016.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolderFilter> {

    private String[] kategori = {"Wisata","Rumah Makan","Hotel","SPBU","Toko","Acara"};
    private int[] icons = {R.drawable.wisata, R.drawable.food, R.drawable.hotel, R.drawable.spbu, R.drawable.toko, R.drawable.agenda};

    @Override
    public ViewHolderFilter onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderFilter(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_popup_kategori, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderFilter holder, int position) {
        holder.cat.setText(kategori[position]);
        Glide.with(holder.itemView.getContext())
                .load(icons[position])
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return kategori.length;
    }

    class ViewHolderFilter extends RecyclerView.ViewHolder{

        @BindView(R.id.category_icon)ImageView img;
        @BindView(R.id.category_name)TextView cat;

        public ViewHolderFilter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
