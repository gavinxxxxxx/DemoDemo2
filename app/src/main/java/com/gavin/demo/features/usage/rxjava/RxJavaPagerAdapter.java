package com.gavin.demo.features.usage.rxjava;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * RxJavaPagerAdapter
 *
 * @author gavin.xiong 2016/12/5
 */
public class RxJavaPagerAdapter extends FragmentPagerAdapter {

    private String[] tabs = new String[]{
            "示例",
            "基本实现 & 场景示例",
            "变换(map & flatMap)",
            "线程控制：Scheduler",
            "Hello World",
            "操作符",
    };

    public RxJavaPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RxJavaFragment1.newInstance();
            case 1:
                return RxJavaFragment2.newInstance();
            case 2:
                return RxJavaFragment3.newInstance();
            case 3:
                return RxJavaFragment4.newInstance();
            case 4:
                return RxJavaFragment5.newInstance();
            case 5:
                return RxJavaFragment6.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
