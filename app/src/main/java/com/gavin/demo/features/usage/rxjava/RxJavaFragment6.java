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

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 测试页
 *
 * @author gavin.xiong 2016/11/10
 */
public class RxJavaFragment6 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    public static RxJavaFragment6 newInstance() {
        return new RxJavaFragment6();
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
        query("Hello, world!")
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        for (String s : strings) {
                            L.e(s);
                        }
                    }
                });

        query("gavin.xiong")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " ** ";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        L.e(s);
                    }
                });

        query("flatMap2")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return getTitle(s);
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String title) {
                        return title != null; // 过滤掉title为空的结果
                    }
                })
                .take(5) // 如果我们只想要最多5个结果：
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        // saveTitle(); // 如果我们想在打印之前，把每个标题保存到磁盘：
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
     * 关键字搜索 返回URL列表
     *
     * @param text
     * @return URL列表
     */
    private Observable<List<String>> query(String text) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add(text + " - " + i);
        }
        return Observable.just(stringList);
    }

    /**
     * 根据URL返回网页Title
     *
     * @param text
     * @return
     */
    private Observable<String> getTitle(String text) {
        return Observable.just("标题 - " + text);
    }
}
