package com.gavin.demo.features.usage.dagger2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.LayoutNullBinding;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Dagger2 依赖注入
 *
 * @author gavin.xiong 2016/11/10
 */
public class Dagger2Fragment1 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    public static Dagger2Fragment1 newInstance() {
        return new Dagger2Fragment1();
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
//        CoffeeMachine coffeeMachine = new CoffeeMachine(new Cooker("gavin", "雅哈咖啡"));
//        L.e(coffeeMachine.makeCoffee());
//
//        CoffeeMachineWithInjection coffeeMachineWithInjection = new CoffeeMachineWithInjection(
//                new com.gavin.demo.features.usage.dagger2.coffee2.CoffeeMaker() {
//            @Override
//            public String makeCoffee() {
//                return "Coffee is made by SimperMarker";
//            }
//        });
//
//        L.e(coffeeMachineWithInjection.makeCoffee());

    }

}
