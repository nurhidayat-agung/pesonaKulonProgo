package com.gits.developer.pesonakulonprogo.ui.help.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gits.developer.pesonakulonprogo.databinding.ItemRvHelpBinding;
import com.gits.developer.pesonakulonprogo.model.help.HelpModel;
import com.gits.developer.pesonakulonprogo.util.GeneralViewHolder;

import java.util.List;

/**
 * Created by kazt on 16/06/17.
 */

public class HelpRVAdapter extends RecyclerView.Adapter<GeneralViewHolder>{
    private Context c;
    private List<HelpModel> datas;
    private ItemRvHelpBinding binding;

    public HelpRVAdapter(Context c, List<HelpModel> datas) {
        this.c = c;
        this.datas = datas;
    }

    @Override
    public GeneralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemRvHelpBinding.inflate(LayoutInflater.from(c),parent,false);
        return new GeneralViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(GeneralViewHolder holder, int position) {
        HelpModel data = datas.get(position);
        binding.tvInflateHelpMainmenu.setText(data.getName());
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }
}
