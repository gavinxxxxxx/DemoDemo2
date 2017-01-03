package com.gavin.demo.features.usage.rxjava;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.LayoutNullBinding;
import com.gavin.demo.utils.L;

import java.util.concurrent.TimeUnit;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxJava - Hello World
 *
 * @author gavin.xiong 2016/11/10
 */
public class RxJavaFragment5 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    public static RxJavaFragment5 newInstance() {
        return new RxJavaFragment5();
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
//        base();
//        just();
//        gavin();
        map2();
    }

    /**
     * 基础用法
     */
    private void base() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World!");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                L.e("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("onError - " + e.toString());
            }

            @Override
            public void onNext(String s) {
                L.e("onNext - " + s);
            }
        });
    }

    /**
     * 简单用法
     */
    private void just() {
        Observable.just("Hello World").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                L.e("call - " + s);
            }
        });
    }

    /**
     * 简单转换 - map
     */
    private void gavin() {
//        Observable.just("Hello World").subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                L.e(s + "- gavin");
//            }
//        });

        Observable.just("Hello World")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " - gavin";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        L.e(s);
                    }
                });
    }

    /**
     * map 进阶
     */
    private void map2() {
//        Observable.just("gavin xiong")
//                .map(new Func1<String, Integer>() {
//                    @Override
//                    public Integer call(String s) {
//                        return s.hashCode();
//                    }
//                })
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        L.e(Integer.toString(integer));
//                    }
//                });

        /*
         * 前面说过，Subscriber做的事情越少越好，我们再增加一个map操作符
         */

        Observable.just("gavin xiong")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return Integer.toString(integer);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        L.e(s);
                    }
                });
    }

}
