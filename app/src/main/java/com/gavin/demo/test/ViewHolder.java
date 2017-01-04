package com.gavin.demo.test;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ListAdapter ViewHolder
 *
 * @author gavin.xiong 2016/9/10
 */
public class ViewHolder {

	public View rootView;
	private SparseArray<View> views;

	public ViewHolder(View view) {
		if (null == views) {
			views = new SparseArray<>();
		}
		this.rootView = view;
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T $(@IdRes int viewId) {
		View view = views.get(viewId);
		if (view == null) {
			view = rootView.findViewById(viewId);
			views.put(viewId, view);
		}
		return (T) view;
	}

	public void setText(@IdRes int viewId, CharSequence text) {
		TextView tv = $(viewId);
		tv.setText(text);
	}

	public void setText(@IdRes int viewId, @StringRes int textId) {
		TextView tv = $(viewId);
		tv.setText(textId);
	}

	public void setBackground(@IdRes int viewId, int resId) {
		$(viewId).setBackgroundResource(resId);
	}

	public void setBackgroundColor(@IdRes int viewId, int color) {
		$(viewId).setBackgroundColor(color);
	}

	public void setImage(@IdRes int viewId, int id) {
		ImageView iv = $(viewId);
		iv.setImageResource(id);
	}

	public void setVisibility(@IdRes int viewId, int visible) {
		$(viewId).setVisibility(visible);
	}

	public void setOnClickListener(@IdRes int viewId, OnClickListener onClickListener) {
		$(viewId).setOnClickListener(onClickListener);
	}
}
