package com.gavin.demo.features.usage.vector;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.FragmentVectorBinding;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Vector使用示例
 *
 * @author gavin.xiong 2016/11/10
 */
public class VectorFragment extends SupportFragment {

    FragmentVectorBinding binding;

    public static VectorFragment newInstance() {
        return new VectorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vector, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {

    }
}
