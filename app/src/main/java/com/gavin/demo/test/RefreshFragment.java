package com.gavin.demo.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.FragmentRecyclerBinding;
import com.gavin.demo.databinding.FragmentRefreshBinding;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/11/10
 */
public class RefreshFragment extends SupportFragment {

    private FragmentRefreshBinding binding;

    public static RefreshFragment newInstance() {
        return new RefreshFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_refresh, null, false);
        initView();
        return binding.getRoot();
    }

    public void initView() {
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringList.add("DEMO_" + i);
        }
        TestDataBindingAdapter adapter = new TestDataBindingAdapter(stringList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);

        TestListAdapter listAdapter = new TestListAdapter(_mActivity, stringList);
        binding.listView.setAdapter(listAdapter);
    }



}
