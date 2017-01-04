package com.gavin.demo.features.usage.retrofit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * RetrofitPagerAdapter
 *
 * @author gavin.xiong 2016/12/5
 */
public class RetrofitPagerAdapter extends FragmentPagerAdapter {

    private String[] tabs = new String[]{
            "示例",
            "GET & POST",
    };

    public RetrofitPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RetrofitFragment1.newInstance();
            case 1:
                return RetrofitFragment2.newInstance();
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
