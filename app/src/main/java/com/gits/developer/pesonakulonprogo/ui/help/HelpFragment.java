package com.gits.developer.pesonakulonprogo.ui.help;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.databinding.ActivityHelpBinding;

/**
 * Created by kazt on 15/06/17.
 */

public class HelpFragment extends Fragment {
    private ActivityHelpBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_help, container, false);

        return binding.getRoot();
    }
}
