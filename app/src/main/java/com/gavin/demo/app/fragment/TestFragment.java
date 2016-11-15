package com.gavin.demo.app.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.app.event.StartBrotherEvent;
import com.gavin.demo.databinding.FragmentTestBinding;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 测试页
 *
 * @author gavin.xiong 2016/11/10
 */
public class TestFragment extends SupportFragment implements View.OnClickListener{

    FragmentTestBinding binding;

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.root.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view, "TestFragment.onClick()", Snackbar.LENGTH_LONG).show();
        EventBus.getDefault().post(new StartBrotherEvent(RecyclerFragment.newInstance()));
    }
}
