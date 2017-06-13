package com.gits.developer.pesonakulonprogo.ui.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.home.HomeImgData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 23/05/17.
 */

public class AdapterImageSlider extends PagerAdapter {
    private Context context;
    private List<HomeImgData> imgUrl = new ArrayList<>();

    public AdapterImageSlider(Context context, List<HomeImgData> imgUrl) {
        this.context = context;
        this.imgUrl = imgUrl;
    }

    @Override
    public int getCount() {
        return this.imgUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewpager_berita_detail, container, false);
        Glide.with(container.getContext())
                .load(imgUrl.get(position).getUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        ((ImageView)v.findViewById(R.id.img)).setImageBitmap(resource);
                    }
                });
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
