package com.gavin.demo.features.usage.rxjava;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.LayoutNullBinding;

import java.io.File;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJava 用处示例
 *
 * @author gavin.xiong 2016/11/10
 */
public class RxJavaFragment1 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    public static RxJavaFragment1 newInstance() {
        return new RxJavaFragment1();
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

    }

    File[] folders = new File[10]; // 文件夹

    private Bitmap getBitmapFromFile(File file) {
        return BitmapFactory.decodeFile(file.getName());
    }

    private void addImage(Bitmap bitmap) {

    }

    private void code1() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (File folder : folders) {
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        if (file.getName().endsWith(".png")) {
                            final Bitmap bitmap = getBitmapFromFile(file);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addImage(bitmap);
                                }
                            });
                        }
                    }
                }
            }
        }.start();
    }

    private void code2() {
        Observable.from(folders)
                .flatMap(new Func1<File, Observable<File>>() {
                    @Override
                    public Observable<File> call(File file) {
                        return Observable.from(file.listFiles());
                    }
                })
                .filter(new Func1<File, Boolean>() {
                    @Override
                    public Boolean call(File file) {
                        return file.getName().endsWith(".png");
                    }
                })
                .map(new Func1<File, Bitmap>() {
                    @Override
                    public Bitmap call(File file) {
                        return getBitmapFromFile(file);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        addImage(bitmap);
                    }
                });
    }
}
