package com.gavin.demo.features.usage.dagger2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gavin.demo.features.usage.dagger2.inject.constructor.Dagger2Fragment4;
import com.gavin.demo.features.usage.dagger2.inject.module.Dagger2Fragment5;
import com.gavin.demo.features.usage.dagger2.mvp.view.Dagger2Fragment3;


/**
 * Dagger2PagerAdapter
 *
 * @author gavin.xiong 2016/12/5
 */
public class Dagger2PagerAdapter extends FragmentPagerAdapter {

    private String[] tabs = new String[]{
            "依赖注入",
            "simple",
            "mvp simple",
            "inject 1",
            "inject 2",
    };

    public Dagger2PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Dagger2Fragment1.newInstance();
            case 1:
                return Dagger2Fragment2.newInstance();
            case 2:
                return Dagger2Fragment3.newInstance();
            case 3:
                return Dagger2Fragment4.newInstance();
            case 4:
                return Dagger2Fragment5.newInstance();
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
