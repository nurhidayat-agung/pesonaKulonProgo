package com.gits.developer.pesonakulonprogo.ui.plan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gits.developer.pesonakulonprogo.databinding.ItemPlanYourTripBinding;

/**
 * Created by kazt on 06/06/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ItemPlanYourTripBinding binding;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        binding = ItemPlanYourTripBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ItemPlanYourTripBinding binding;

        public MyViewHolder(ItemPlanYourTripBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(){
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {

        }
    }
}
