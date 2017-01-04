package com.gavin.demo.features.usage.retrofit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.app.DrawerToggleEvent;
import com.gavin.demo.databinding.LayoutTabViewPagerBinding;
import com.gavin.demo.utils.L;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Retrofit 主页面
 *
 * @author gavin.xiong 2016/11/10
 */
public class RetrofitFragment extends SupportFragment implements TabLayout.OnTabSelectedListener {

    LayoutTabViewPagerBinding binding;

    public static RetrofitFragment newInstance() {
        return new RetrofitFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_tab_view_pager, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.toolbar.setTitle("Retrofit");
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DrawerToggleEvent(true));
            }
        });

        initViewPager();
    }

    private void initViewPager() {
        RetrofitPagerAdapter pagerAdapter = new RetrofitPagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setOffscreenPageLimit(5);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        binding.tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        L.v("onTabSelected");
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        L.v("onTabUnselected");
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        L.v("onTabReselected");
    }
}
