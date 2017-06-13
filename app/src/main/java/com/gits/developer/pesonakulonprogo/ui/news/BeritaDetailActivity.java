package com.gits.developer.pesonakulonprogo.ui.news;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.news.NewsDataModel;
import com.gits.developer.pesonakulonprogo.ui.CirclePagesIndicator;
import com.gits.developer.pesonakulonprogo.util.NameTag;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeritaDetailActivity extends AppCompatActivity {

    @BindView(R.id.appbar)AppBarLayout appBarLayout;
    @BindView(R.id.custom_toolbar)Toolbar toolbar;
    @BindView(R.id.collapsing)CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.viewpager)ViewPager viewPager;
    @BindView(R.id.circle_indicator)CirclePagesIndicator circlePagesIndicator;
    @BindView(R.id.tv_content_news)TextView tvContentNews;

    private NewsDataModel newsDataModel = new NewsDataModel();
    private ViewPagerSliderAdapter pagerSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail);
        ButterKnife.bind(this);

        //setSupportActionBar(toolbar);
        if (getIntent().getSerializableExtra(NameTag.detailNews) != null){
            newsDataModel = (NewsDataModel) getIntent().getSerializableExtra(NameTag.detailNews);
        }

        pagerSliderAdapter = new ViewPagerSliderAdapter(this,newsDataModel.getImage());
        viewPager.setAdapter(pagerSliderAdapter);
        circlePagesIndicator.setViewPager(viewPager);

        tvContentNews.setText(newsDataModel.getContent());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                System.out.println("xxxx scrollrange "+scrollRange);
                System.out.println("xxxx appbartotal : "+appBarLayout.getTotalScrollRange());
                System.out.println("xxxx ofset : "+verticalOffset);
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(newsDataModel.getTitle());
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
