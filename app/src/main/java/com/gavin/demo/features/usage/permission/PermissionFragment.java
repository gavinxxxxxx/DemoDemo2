package com.gavin.demo.features.usage.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.app.Constants;
import com.gavin.demo.R;
import com.gavin.demo.databinding.FragmentPermissionBinding;
import com.gavin.demo.test.TestFragment;
import com.gavin.demo.utils.SPUtil;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 权限申请页
 *
 * @author gavin.xiong 2016/11/10
 */
public class PermissionFragment extends SupportFragment {

    FragmentPermissionBinding binding;

    public static PermissionFragment newInstance() {
        return new PermissionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_permission, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        if (SPUtil.getBoolean(Constants.IntentExtra.IS_OPEN_AGAIN)) {
            checkPermission();
        } else {
            SPUtil.saveBoolean(Constants.IntentExtra.IS_OPEN_AGAIN, true);
            binding.root.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enterStatusEnable();
                    checkPermission();
                }
            }, 1000);
        }
    }

    /**
     * 检查当前手机布局能否进入状态栏
     */
    private void enterStatusEnable() {
        int[] location = new int[2];
        binding.root.getLocationInWindow(location);
        SPUtil.saveBoolean(Constants.IntentExtra.ENTER_STATUS_ENABLE, location[1] < 2);
    }

    private void checkPermission() {
        if (!(ContextCompat.checkSelfPermission(_mActivity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                || !(ContextCompat.checkSelfPermission(_mActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                || !(ContextCompat.checkSelfPermission(_mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            requestPermission();
        } else {
            startWithPop(TestFragment.newInstance());
        }
    }

    private void requestPermission() {
        requestPermissions(new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, Constants.RequestCode.PERMISSION_NEEDFUL);
    }

    private void showDialog() {
        new AlertDialog.Builder(_mActivity)
                .setTitle("应用缺少必要权限")
                .setMessage("是否前往设置页打开权限？")
                .setCancelable(false)
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        _mActivity.finish();
                        System.exit(0);
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openAppSetting();
                    }
                })
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                            dialogInterface.cancel();
                            _mActivity.finish();
                            System.exit(0);
                        }
                        return true;
                    }
                })
                .show();
    }

    /**
     * 启动应用的设置
     */
    private void openAppSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + _mActivity.getPackageName()));
        startActivityForResult(intent, Constants.RequestCode.PERMISSION_SETTING);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.RequestCode.PERMISSION_NEEDFUL) {
            for (int grantResult : grantResults) {
                if (grantResult == -1) {
                    showDialog();
                    return;
                }
            }
            checkPermission();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkPermission();
    }
}
