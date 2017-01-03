package com.gavin.demo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gavin.demo.app.Constants;
import com.gavin.demo.utils.DisplayUtil;
import com.gavin.demo.utils.SPUtil;

/**
 * 这是一个拒绝状态栏的toolbar
 *
 * @author gavin.xiong
 * @date 2016/7/27
 */
public class VToolbar extends Toolbar {

    public VToolbar(Context context) {
        super(context);
    }

    public VToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
//        if (SPUtil.getBoolean(Constants.IntentExtra.ENTER_STATUS_ENABLE)) {
            setMarginTop();
//        }
        super.onAttachedToWindow();
    }

    private void setMarginTop() {
        ViewGroup.LayoutParams params = getLayoutParams();
        if (params == null) {
            return;
        }
        if (params instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) params).setMargins(0, DisplayUtil.getStatusHeight(), 0, 0);
        } else if (params instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) params).setMargins(0, DisplayUtil.getStatusHeight(), 0, 0);
        } else if (params instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) params).setMargins(0, DisplayUtil.getStatusHeight(), 0, 0);
        }
        setLayoutParams(params);
    }

}
