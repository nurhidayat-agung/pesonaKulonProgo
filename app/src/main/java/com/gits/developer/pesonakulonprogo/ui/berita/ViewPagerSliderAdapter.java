package com.gits.developer.pesonakulonprogo.ui.berita;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gits.developer.pesonakulonprogo.R;

/**
 * Created by annasblackhat on 11/16/16.
 */

public class ViewPagerSliderAdapter extends PagerAdapter {

    private Context context;

    public ViewPagerSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewpager_berita_detail, container, false);
        ((ImageView)v.findViewById(R.id.img)).setImageResource(R.drawable.mangrove);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
