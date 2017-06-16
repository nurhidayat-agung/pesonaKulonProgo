package com.gits.developer.pesonakulonprogo.util;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kazt on 16/06/17.
 */

public class GeneralViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;
    private Context context;
    private View itemView;

    public GeneralViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        context = itemView.getContext();
        this.itemView = itemView;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public Context getContext() {
        return context;
    }

    public View getItemView() {
        return itemView;
    }

}
