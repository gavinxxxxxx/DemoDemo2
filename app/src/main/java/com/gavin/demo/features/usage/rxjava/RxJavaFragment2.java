package com.gavin.demo.features.usage.rxjava;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.FragmentVectorBinding;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RxJava 用法示例 - 基本实现 & 场景示例
 *
 * @author gavin.xiong 2016/11/10
 */
public class RxJavaFragment2 extends SupportFragment implements View.OnClickListener {

    FragmentVectorBinding binding;

    public static RxJavaFragment2 newInstance() {
        return new RxJavaFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vector, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.imageView.setOnClickListener(this);
    }

    int[] resList = new int[]{
            R.drawable.ic_arrow_back_black_24dp,
            R.drawable.ic_check_box_black_24dp,
            R.drawable.ic_mail_outline_black_24dp,
            R.drawable.ic_short_text_black_24dp
    };
    int position = -1;

    @Override
    public void onClick(View view) {

        /**
         * 加载图片将会发生在 IO 线程，而设置图片则被设定在了主线程。这就意味着，即使加载图片耗费了几十甚至几百毫秒的时间，也不会造成丝毫界面的卡顿。
         */
        Observable.create(
                new Observable.OnSubscribe<Drawable>() {
                    @Override
                    public void call(Subscriber<? super Drawable> subscriber) {
                        position++;
                        int drawableRes = resList[position % (resList.length + 1)];
                        Drawable drawable = ContextCompat.getDrawable(_mActivity, drawableRes);
                        subscriber.onNext(drawable);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onNext(Drawable drawable) {
                        binding.imageView.setImageDrawable(drawable);
                    }

                    @Override
                    public void onCompleted() {
                        Snackbar.make(binding.imageView, "onCompleted", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.imageView.setImageDrawable(null);
                        Snackbar.make(binding.imageView, "onError - " + e.toString(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }

}
