package com.gits.developer.pesonakulonprogo.ui.plan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gaurav.cdsrecyclerview.CdsRecyclerViewAdapter;
import com.gits.developer.pesonakulonprogo.databinding.ItemPlanYourTripBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 06/06/17.
 */

public class RVDragAdapter extends CdsRecyclerViewAdapter<String, RVDragAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ItemPlanYourTripBinding binding;
    List<String> list = new ArrayList<>();

    public RVDragAdapter(Context context, List<String> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        binding = ItemPlanYourTripBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final ItemPlanYourTripBinding binding;

        public MyViewHolder(ItemPlanYourTripBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(){
            binding.executePendingBindings();
        }
    }
}
