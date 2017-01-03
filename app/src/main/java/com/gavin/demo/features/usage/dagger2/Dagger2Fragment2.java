package com.gavin.demo.features.usage.dagger2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.LayoutNullBinding;
import com.gavin.demo.features.usage.dagger2.simple.CoffeeMaker;
import com.gavin.demo.features.usage.dagger2.simple.DripCoffeeModule;
import com.gavin.demo.features.usage.dagger2.simple.PumpModule;

import javax.inject.Singleton;

import dagger.Component;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Dagger2 使用
 *
 * @author gavin.xiong 2016/11/10
 */
public class Dagger2Fragment2 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    public static Dagger2Fragment2 newInstance() {
        return new Dagger2Fragment2();
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
    }

    @Override
    public void onClick(View view) {
        Coffee coffee = DaggerDagger2Fragment2_Coffee.builder().build();
        coffee.maker().brew();
    }

    @Singleton
    @Component(modules = { DripCoffeeModule.class })
    public interface Coffee {
        CoffeeMaker maker();
    }
}
