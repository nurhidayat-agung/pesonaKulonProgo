package com.gits.developer.pesonakulonprogo.ui.news;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.news.NewsImageList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annasblackhat on 11/16/16.
 */

public class ViewPagerSliderAdapter extends PagerAdapter {

    private Context context;
    private List<NewsImageList> imageLists = new ArrayList<>();

    public ViewPagerSliderAdapter(Context context, List<NewsImageList> imageLists) {
        this.context = context;
        this.imageLists = imageLists;
    }

    @Override
    public int getCount() {
        return this.imageLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewpager_berita_detail, container, false);
        Glide.with(container.getContext())
                .load(imageLists.get(position).getUrl())
                .fitCenter()
                .placeholder(R.drawable.logo_pemerintah)
                .error(R.drawable.logo_pemerintah)
                .into((ImageView)v.findViewById(R.id.img));
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
