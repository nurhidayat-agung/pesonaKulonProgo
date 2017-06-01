package com.gits.developer.pesonakulonprogo.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gits.developer.pesonakulonprogo.ui.berita.BeritaActivity;
import com.gits.developer.pesonakulonprogo.util.NameTag;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.MarkerData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gits.developer.pesonakulonprogo.R.id.map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private MarkerData markerData = new MarkerData();

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        return v;
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

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater(getArguments()).inflate(R.layout.layout_popup_map, null);
                ImageView ivPopUpMarker = (ImageView) v.findViewById(R.id.iv_pop_up_marker);
                TextView tvTitleMarker = (TextView) v.findViewById(R.id.place_title);
                tvTitleMarker.setText(marker.getTitle());
                if (marker.getTitle().equalsIgnoreCase("glagah")){
                    ivPopUpMarker.setImageResource(R.drawable.glagah1);
                    markerData.setTitle("Glagah Beach");
                    markerData.setDesc(getString(R.string.glagahdesc));
                    markerData.setImage(R.drawable.glagah1);
                    markerData.setLat(-7.915054);
                    markerData.setLongt(110.0707685);
                }else if (marker.getTitle().equalsIgnoreCase("Mangrove Congot")){
                    ivPopUpMarker.setImageResource(R.drawable.mangrove);
                    markerData.setTitle("Mangrove Congot");
                    markerData.setDesc(getString(R.string.mangrovedesc));
                    markerData.setImage(R.drawable.mangrove);
                    markerData.setLat(-7.8933459);
                    markerData.setLongt(110.017637);
                }else if (marker.getTitle().equalsIgnoreCase("Pantai Congot")){
                    ivPopUpMarker.setImageResource(R.drawable.congot1);
                    markerData.setTitle("Pantai Congot");
                    markerData.setDesc(getString(R.string.congotdesc));
                    markerData.setImage(R.drawable.congot1);
                    markerData.setLat(-7.8994109);
                    markerData.setLongt(110.0335927);
                }else if (marker.getTitle().equalsIgnoreCase("Pantai Trisik")){
                    ivPopUpMarker.setImageResource(R.drawable.trisik1);
                    markerData.setTitle("Pantai Trisik");
                    markerData.setDesc(getString(R.string.trisikdesc));
                    markerData.setImage(R.drawable.trisik1);
                    markerData.setLat(-7.9748254);
                    markerData.setLongt(110.1918931);
                }else if (marker.getTitle().equalsIgnoreCase("Towil Field")){
                    ivPopUpMarker.setImageResource(R.drawable.towil1);
                    markerData.setTitle("Towil Field");
                    markerData.setDesc(getString(R.string.towildesc));
                    markerData.setImage(R.drawable.towil1);
                    markerData.setLat(-7.8577565);
                    markerData.setLongt(110.2028644);
                }
                return v;
            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent goDet = new Intent(getContext(), DestinasiDetailActivity.class);
                goDet.putExtra(NameTag.MARKERDATA, markerData);
                startActivity(goDet);
            }
        });

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.8229681, 110.1473849)).title("Alun-Alun Wates")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_home_grey_700_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.760240, 110.1226504)).title("Waduk Sermo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7628814, 110.1161091)).title("Tuk Mudal")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.9748254, 110.1918931)).title("Pantai Trisik")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.8577565, 110.2028644)).title("Towil Field")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.6464957, 110.1788275)).title("Suroloyo Peak")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.6678482, 110.2235331)).title("Sendangsono")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.6597404, 110.2185337)).title("Pegagan Banjarharjo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.6597404, 110.2185337)).title("Pegagan Banjarharjo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.9026812, 110.2947036)).title("Nyi Ageng Serang")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.647054, 110.1392812)).title("Ngglinggo - Tritis")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.8933459, 110.017637)).title("Mangrove Congot")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.6610549, 110.2053944)).title("Kopi Pak Rohmat")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7669565, 110.1156369)).title("Kembang Soka")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.766688, 110.1180826)).title("Kedhung Pedut")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.8058835, 110.1258548)).title("KaliBiru")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.8058835, 110.2651067)).title("Jembatan Duet")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7468187, 110.1383046)).title("Gunung Lanang")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7519624, 110.1258548)).title("Grojogan Sewu")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7035962, 110.1258548)).title("Goa Sriti")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7364144, 110.1304737)).title("Goa Sikidang")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7471047, 110.128826)).title("Goa Kiskendo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.915054, 110.0707685)).title("Glagah")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.658715, 110.2341547)).title("Ebung Tonegoro")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.6984409, 110.2198779)).title("Dolan Deso Boro")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.6698582, 110.2002333)).title("Curug Sidoharjo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.8994109, 110.0335927)).title("Pantai Congot")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7202135, 110.2284112)).title("Arus Progo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_linked_camera_grey_900_18dp)));

    }

    @OnClick(R.id.layout_berita)
    void showNews(){
        startActivity(new Intent(getContext(), BeritaActivity.class));
    }

}
