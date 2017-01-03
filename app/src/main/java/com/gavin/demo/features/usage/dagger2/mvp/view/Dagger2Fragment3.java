package com.gavin.demo.features.usage.dagger2.mvp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.LayoutNullBinding;
import com.gavin.demo.features.usage.dagger2.mvp.model.ActivityModule;
import com.gavin.demo.features.usage.dagger2.mvp.presenter.DaggerPresenter;

import javax.inject.Inject;

import dagger.Component;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Dagger2 MVP 示例
 *
 * @author gavin.xiong 2016/11/10
 */
public class Dagger2Fragment3 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    @Inject
    DaggerPresenter presenter;

    public static Dagger2Fragment3 newInstance() {
        return new Dagger2Fragment3();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_null, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.textView.setOnClickListener(this);

        inject();
    }

    @Override
    public void onClick(View view) {
        presenter.showUserName();
    }

    private void inject() {
        DaggerDagger2Fragment3_ActivityComponent
                .builder()
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    public void showUserName(String name) {
        Snackbar.make(binding.textView, name, Snackbar.LENGTH_LONG).show();
    }

    @Component(modules = ActivityModule.class)
    public interface ActivityComponent {
        void inject(Dagger2Fragment3 fragment);
    }
}
