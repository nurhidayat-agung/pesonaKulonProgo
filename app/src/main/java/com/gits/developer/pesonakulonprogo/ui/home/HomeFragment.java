package com.gits.developer.pesonakulonprogo.ui.home;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gits.developer.pesonakulonprogo.databinding.LayoutPopupMapBinding;
import com.gits.developer.pesonakulonprogo.model.home.HomeDataModel;
import com.gits.developer.pesonakulonprogo.model.home.HomeResponData;
import com.gits.developer.pesonakulonprogo.network.config.MyConn;
import com.gits.developer.pesonakulonprogo.network.service.HomeService;
import com.gits.developer.pesonakulonprogo.ui.news.BeritaActivity;
import com.gits.developer.pesonakulonprogo.util.NameTag;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.MarkerData;
import com.gits.developer.pesonakulonprogo.util.PDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gits.developer.pesonakulonprogo.R.id.map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private MarkerData markerData = new MarkerData();
    private SupportMapFragment mapFragment;
    private MyConn myConn = new MyConn();
    public List<HomeDataModel> homeDataModels = new ArrayList<>();
    public ImageView ivPopUpMarker;
    private LruCache<String, Bitmap> mMemoryCache;
    private PDialog pDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        //pdialog
        pDialog = new PDialog(getActivity());

        //cache image setup
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;
         mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };

        pDialog.showPDialog();
        HomeService homeService = myConn.createService(HomeService.class);
        Call<HomeResponData> homeResponDataCall = homeService.getLocation();
        homeResponDataCall.enqueue(new Callback<HomeResponData>() {
            @Override
            public void onResponse(Call<HomeResponData> call, Response<HomeResponData> response) {
                homeDataModels = response.body().getDate();
                loadMap();
                loadSpiritImage();
            }

            @Override
            public void onFailure(Call<HomeResponData> call, Throwable t) {
                pDialog.hidePDialog();
                Toast.makeText(getActivity(), "eror koneksi", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void loadSpiritImage() {
        for (HomeDataModel homeDataModel : homeDataModels){
            Glide.with(getActivity())
                    .load(homeDataModel.getImage().get(0).getUrl())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                           addBitmapToMemoryCache(homeDataModel.getId_location(),resource);
                        }
                    });
        }
        pDialog.hidePDialog();
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    private void loadMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.layout_kategori)
    void showCategory(){
        View dialoglayout = getActivity().getLayoutInflater().inflate(R.layout.layout_popup_kategori, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialoglayout);
        builder.setTitle("Filter Pencarian");

        RecyclerView recyclerView = (RecyclerView) dialoglayout.findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.setAdapter(new FilterAdapter());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-7.8615943, 110.1473849))
                .zoom(11f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        for (HomeDataModel dataModel : homeDataModels){
                    map.addMarker(new MarkerOptions()
                            .snippet(dataModel.getId_location())
                            .position(new LatLng(dataModel.getLat(), dataModel.getLongth()))
                            .title(dataModel.getName())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        }

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater(getArguments()).inflate(R.layout.layout_popup_map, null);
                ivPopUpMarker = (ImageView) v.findViewById(R.id.iv_pop_up_marker);
                TextView tvTitleMarker = (TextView) v.findViewById(R.id.place_title);
                tvTitleMarker.setText(marker.getTitle());
                Bitmap bitmap = getBitmapFromMemCache(marker.getSnippet());
                ivPopUpMarker.setImageBitmap(bitmap);
                return v;
            }
        });

        map.setOnInfoWindowClickListener(marker -> {
            Intent goDet = new Intent(getContext(), DestinasiDetailActivity.class);
            HomeDataModel dataModel = new HomeDataModel();
            for (HomeDataModel homeDataModel : homeDataModels){
                if (marker.getSnippet().equalsIgnoreCase(homeDataModel.getId_location())){
                    dataModel = homeDataModel;
                }
            }
            goDet.putExtra(NameTag.detailPlace, dataModel);
            goDet.putExtra(NameTag.listPlace, (Serializable) homeDataModels);
            Log.d("cek", dataModel.getName());
            Log.d("cek","size : "+homeDataModels.size());
            startActivity(goDet);
        });
    }

    @OnClick(R.id.layout_berita)
    void showNews(){
        startActivity(new Intent(getContext(), BeritaActivity.class));
    }

}



