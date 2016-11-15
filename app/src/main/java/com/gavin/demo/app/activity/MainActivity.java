package com.gavin.demo.app.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.gavin.demo.R;
import com.gavin.demo.app.fragment.PermissionFragment;
import com.gavin.demo.databinding.ActivityMainBinding;
import com.gavin.demo.utils.L;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/11/10
 */
public class MainActivity extends SupportActivity implements
        NavigationView.OnNavigationItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    ActivityMainBinding binding;

    TextView textView;
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.holder, PermissionFragment.newInstance());
        }

        binding.navigation.setNavigationItemSelectedListener(this);

        textView = (TextView) binding.navigation.getMenu().getItem(1).getActionView();
        textView.setText("99+");
        switchCompat = (SwitchCompat) binding.navigation.getMenu().getItem(4).getActionView();
        switchCompat.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_switch:
                switchCompat.toggle();
                break;
        }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        L.v("onCheckedChanged" + isChecked);
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
}
