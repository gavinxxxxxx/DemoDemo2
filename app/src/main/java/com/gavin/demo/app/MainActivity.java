package com.gavin.demo.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.widget.TextView;

import com.gavin.demo.R;
import com.gavin.demo.databinding.ActivityMainBinding;
import com.gavin.demo.features.style.bottombar.TabFragment;
import com.gavin.demo.features.style.tag.TagFragment;
import com.gavin.demo.features.usage.dagger2.Dagger2Fragment;
import com.gavin.demo.features.usage.permission.PermissionFragment;
import com.gavin.demo.features.usage.retrofit.RetrofitFragment;
import com.gavin.demo.features.usage.rxjava.ReRxJavaFragment;
import com.gavin.demo.features.usage.rxjava.RxJavaFragment;
import com.gavin.demo.features.usage.vector.VectorFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/11/10
 */
public class MainActivity extends SupportActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        EventBus.getDefault().register(this);

        if (savedInstanceState == null) {
//            loadRootFragment(R.id.holder, PermissionFragment.newInstance());
            loadRootFragment(R.id.holder, ReRxJavaFragment.newInstance());
        }

        initNavigationView();
    }

    /**
     * 初始化左侧菜单
     */
    private void initNavigationView() {
        binding.navigation.setNavigationItemSelectedListener(this);

        TextView textView = (TextView) binding.navigation.getMenu().findItem(R.id.nav_tip).getActionView();
        textView.setText("9+");
        TextView textView2 = (TextView) binding.navigation.getMenu().findItem(R.id.nav_msg).getActionView().findViewById(R.id.textView);
        textView2.setText("提示信息");
        SwitchCompat switchCompat = (SwitchCompat) binding.navigation.getMenu().findItem(R.id.nav_switch).getActionView();
        switchCompat.setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        binding.drawer.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_bottom_navigation:
                startWithPopDelayed(TabFragment.newInstance());
                break;
            case R.id.nav_tag_group:
                startWithPopDelayed(TagFragment.newInstance());
                break;
            case R.id.nav_vector:
                startWithPopDelayed(VectorFragment.newInstance());
                break;
            case R.id.nav_rxJava:
                startWithPopDelayed(RxJavaFragment.newInstance());
                break;
            case R.id.nav_retrofit:
                startWithPopDelayed(RetrofitFragment.newInstance());
                break;
            case R.id.nav_dagger2:
                startWithPopDelayed(Dagger2Fragment.newInstance());
                break;
        }
        return false;
    }

    private void startWithPopDelayed(final SupportFragment fragment) {
        binding.navigation.postDelayed(new Runnable() {
            @Override
            public void run() {
                startWithPop(fragment);
            }
        }, 300);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @Override
    public void onBackPressedSupport() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressedSupport();
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * 左侧菜单开关
     */
    @Subscribe
    public void OnDrawerToggle(DrawerToggleEvent event) {
        if (event.open && !binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.openDrawer(GravityCompat.START);
        } else if (!event.open && binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START);
        }
    }
}
