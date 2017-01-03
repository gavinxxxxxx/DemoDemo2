package com.gavin.demo.features.usage.dagger2.inject.constructor;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.LayoutNullBinding;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Dagger2 Inject 方式1
 *      （http://www.jianshu.com/p/cd2c1c9f68d4）
 *
 *      1. 注解(Annotation)来标注目标类中所依赖的其他类
 *      2. 同样用注解来标注所依赖的其他类的构造函数
 *      3. 编写XXXComponent类，并用Component注解来标注该类，并且该类是接口或抽象类
 *      4. make project
 *      5. DaggerXXXComponent.builder().build().inject(this);
 *
 * @author gavin.xiong 2016/11/10
 */
public class Dagger2Fragment4 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    //添加@Inject注解，表示这个mPoetry是需要注入的
    @Inject
    User user;

    public static Dagger2Fragment4 newInstance() {
        return new Dagger2Fragment4();
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

        DaggerUserComponent.builder().build().inject(this);
    }

    @Override
    public void onClick(View view) {
        Snackbar.make(binding.textView, user.name, Snackbar.LENGTH_LONG).show();
    }

}
