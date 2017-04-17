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
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RxJava 用处示例
 *
 * @author gavin.xiong 2016/11/10
 */
public class ReRxJavaFragment extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    public static ReRxJavaFragment newInstance() {
        return new ReRxJavaFragment();
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
        aaa();
    }

    private void aaa() {
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello, world!");
                        subscriber.onCompleted();
                    }
                });

        Subscriber subscriber = new Subscriber<String>() {

            @Override
            public void onNext(String s) {
                L.e(s);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        };

        observable.subscribe(subscriber);

        Observable.just("Hello, world!")
                .map(s -> hashCode())
                .map(s -> s + " - gavin")
//                .subscribe(s -> Snackbar.make(binding.textView, s, Snackbar.LENGTH_LONG).show());
                .subscribe(L::e);

        query("gavin")
                .flatMap(Observable::from)
                .filter(s -> s.startsWith("http"))
                .flatMap(this::getTitle)
                .filter(s -> s != null)
                .take(2)
                .doOnNext(this::saveTitle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
//                .subscribe(L::e);
    }

    Observable<List<String>> query(String text) {
        List<String> urlList = new ArrayList<>();
        urlList.add("http://www.jianshu.com/p/7796d7807503");
        urlList.add("http://www.jianshu.com/p/517545fcbacf");
        urlList.add("http://www.jianshu.com/p/c4ff68505235");
        urlList.add("http://www.jianshu.com/p/b9212b5a28f6");
        urlList.add("http://www.jianshu.com/p/b4c4647aedc2");
        return Observable.just(urlList);
    }

    // 返回网站的标题，如果404了就返回null
    Observable<String> getTitle(String url) {
        return Observable.just(url + " - gavin");
    }

    void saveTitle(String s) {
        L.e("saveTitle - " + s);
    }
}
