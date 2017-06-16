package com.gits.developer.pesonakulonprogo.ui.help;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.databinding.ActivityHelpBinding;
import com.gits.developer.pesonakulonprogo.databinding.ActivityHelpDetailBinding;
import com.gits.developer.pesonakulonprogo.model.help.HelpModel;
import com.gits.developer.pesonakulonprogo.model.help.HelpResponServer;
import com.gits.developer.pesonakulonprogo.network.config.MyConn;
import com.gits.developer.pesonakulonprogo.network.service.HelpService;
import com.gits.developer.pesonakulonprogo.ui.help.adapter.HelpRVAdapter;
import com.gits.developer.pesonakulonprogo.util.PDialog;
import com.gits.developer.pesonakulonprogo.util.RecyclerItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kazt on 15/06/17.
 */

public class HelpFragment extends Fragment {
    private ActivityHelpBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private HelpRVAdapter adapter;
    private MyConn myConn = new MyConn();
    private HelpService service;
    private PDialog pDialog;
    private Call<HelpResponServer> getHelp;
    private List<HelpModel> datas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_help, container, false);
        layoutManager = new LinearLayoutManager(getActivity());
        pDialog = new PDialog(getActivity());
        service = myConn.createService(HelpService.class);
        pDialog.showPDialog();
        getHelp = service.getHelp();
        getHelp.enqueue(new Callback<HelpResponServer>() {
            @Override
            public void onResponse(Call<HelpResponServer> call, Response<HelpResponServer> response) {
                pDialog.hidePDialog();
                datas = response.body().getData();
                adapter = new HelpRVAdapter(getActivity(),datas);
                binding.rvHelp.setLayoutManager(layoutManager);
                binding.rvHelp.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<HelpResponServer> call, Throwable t) {
                pDialog.hidePDialog();
                pDialog.fail();
            }
        });

        binding.rvHelp.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showDetail(datas.get(position));
            }
        }));

        return binding.getRoot();
    }

    private void showDetail(HelpModel helpModel) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityHelpDetailBinding binding = ActivityHelpDetailBinding.inflate(getActivity().getLayoutInflater(),null,false);
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        dialog.setCanceledOnTouchOutside(true);
        binding.tvHelpdetJudul.setText(helpModel.getName());
        binding.tvHelpdetContent.setText(helpModel.getDesc());
        dialog.show();
    }
}
