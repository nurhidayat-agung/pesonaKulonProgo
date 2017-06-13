package com.gits.developer.pesonakulonprogo.ui.plan;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.gaurav.cdsrecyclerview.CdsRecyclerView;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.databinding.ActivityPlanBinding;
import com.gits.developer.pesonakulonprogo.ui.plan.adapter.RVAdapter;
import com.gits.developer.pesonakulonprogo.ui.plan.adapter.RVDragAdapter;
import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener;
import com.michaelflisar.dragselectrecyclerview.DragSelectionProcessor;
import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener;
import com.michaelflisar.dragselectrecyclerview.DragSelectionProcessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kazt on 06/06/17.
 */

public class PlanYourTrip extends Fragment{
//    private ActivityPlanBinding binding;
    @BindView(R.id.recyclerView)CdsRecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RVAdapter rvAdapter;
    private List<String> listdata = new ArrayList<>();
    private RVDragAdapter mRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_plan, container, false);
        mRecyclerView = new CdsRecyclerView(getActivity());
        ButterKnife.bind(this, v);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewAdapter = new RVDragAdapter(getActivity(), getData());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setItemClickListener(new CdsRecyclerView.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        mRecyclerView.setItemLongPressListener(new CdsRecyclerView.ItemLongPressListener() {
            @Override
            public void onItemLongClick(int position) {

            }
        });

        mRecyclerView.enableItemSwipe();
        mRecyclerView.enableItemDrag();
        mRecyclerView.setItemDragCompleteListener((i, i1) -> {

        });
        mRecyclerView.removeItemDragCompleteListener();
        mRecyclerView.setItemSwipeCompleteListener(i -> {

        });
        mRecyclerView.removeItemSwipeCompleteListener();
        //setupRV();
        return v;
    }

    private List<String> getData() {
        for (int a = 0; a < 7; a++){
            listdata.add("cek");
        }
        return listdata;
    }

//    private void setupRV() {
//        layoutManager = new LinearLayoutManager(getActivity());
//        rvAdapter = new RVAdapter();
//        binding.rvPlan.setLayoutManager(layoutManager);
//        binding.rvPlan.setAdapter(rvAdapter);
//        RecyclerView.OnItemTouchListener mDragSelectTouchListener = new DragSelectTouchListener()
//                .withSelectListener((i, i1, b) -> {
//
//                });
//        binding.rvPlan.addOnItemTouchListener(mDragSelectTouchListener);
//    }
}
