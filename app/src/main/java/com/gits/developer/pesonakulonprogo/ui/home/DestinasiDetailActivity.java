package com.gits.developer.pesonakulonprogo.ui.home;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gits.developer.pesonakulonprogo.model.home.HomeDataModel;
import com.gits.developer.pesonakulonprogo.ui.CirclePagesIndicator;
import com.gits.developer.pesonakulonprogo.util.NameTag;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.MarkerData;
import com.gits.developer.pesonakulonprogo.ui.home.adapter.AdapterImageSlider;
import com.gits.developer.pesonakulonprogo.util.PDialog;
import com.gits.developer.pesonakulonprogo.util.RecyclerItemClickListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gits.developer.pesonakulonprogo.R.id.map;

public class DestinasiDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.collapsing)CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.viewpager)ViewPager viewPager;
    @BindView(R.id.circle_indicator)CirclePagesIndicator circlePagesIndicator;
    @BindView(R.id.appbar)AppBarLayout appBarLayout;
    @BindView(R.id.rec_view_nearby)RecyclerView recViewNearby;
    @BindView(R.id.tv_lokasi_dekat)TextView tvLokasiDekat;
    @BindView(R.id.tv_desc_place)TextView tvDescPlace;

    private HomeDataModel homeDataModel = new HomeDataModel();
    private List<HomeDataModel> dataModels = new ArrayList<>();
    private AdapterImageSlider pagerSliderAdapter;
    private DestinasiNearbyAdapter destinasiNearbyAdapter;
    private MarkerData markerData = new MarkerData();
    private boolean isAnyData = false;
    private int drawable = R.drawable.trisik1;
    private PDialog pDialog;
    private List<HomeDataModel> nearPlace = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinasi_detail);
        ButterKnife.bind(this);
        pDialog = new PDialog(this);

        //get data
        homeDataModel = (HomeDataModel) getIntent().getSerializableExtra(NameTag.detailPlace);
        dataModels = (List<HomeDataModel>) getIntent().getSerializableExtra(NameTag.listPlace);
        isAnyData = true;
        tvLokasiDekat.setText("Lokasi Dekat " + homeDataModel.getName());
        tvDescPlace.setText(homeDataModel.getDesc());
        for (HomeDataModel dataModel : dataModels){
            Location a = new Location("");
            a.setLatitude(homeDataModel.getLat());
            a.setLongitude(homeDataModel.getLongth());
            Location b = new Location("");
            b.setLatitude(dataModel.getLat());
            b.setLongitude(dataModel.getLongth());
            float disanceMeter = a.distanceTo(b);
            Log.d("cek","jarak : "+ disanceMeter);
            if (disanceMeter < 20000 && disanceMeter != 0){
                nearPlace.add(dataModel);
            }
        }

        destinasiNearbyAdapter = new DestinasiNearbyAdapter(DestinasiDetailActivity.this, nearPlace);


        setSupportActionBar(toolbar);

        pagerSliderAdapter = new AdapterImageSlider(this,homeDataModel.getImage());
        viewPager.setAdapter(pagerSliderAdapter);
        circlePagesIndicator.setViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (isAnyData){
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolbarLayout.setTitle(homeDataModel.getName());
                        isShow = true;
                    } else if(isShow) {
                        collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                        isShow = false;
                    }
                }else {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolbarLayout.setTitle(homeDataModel.getName());
                        isShow = true;
                    } else if(isShow) {
                        collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                        isShow = false;
                    }
                }

            }
        });

        recViewNearby.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recViewNearby.setAdapter(destinasiNearbyAdapter);
        recViewNearby.addOnItemTouchListener(new RecyclerItemClickListener(DestinasiDetailActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(DestinasiDetailActivity.this, DestinasiDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra(NameTag.detailPlace, nearPlace.get(position));
                intent.putExtra(NameTag.listPlace, (Serializable) dataModels);
                startActivity(intent);
            }
        }));

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (getIntent().getSerializableExtra(NameTag.detailPlace) != null){
            Double lat = homeDataModel.getLat();
            Double lng = homeDataModel.getLongth();
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(lat, lng))
                    .zoom(13f)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng)).title(homeDataModel.getName())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_18dp)));

        }else {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(-7.8615943, 110.1473849))
                    .zoom(13f)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(-7.8229681, 110.1473849)).title("Tempat Wisata")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_18dp)));
        }




    }
}
