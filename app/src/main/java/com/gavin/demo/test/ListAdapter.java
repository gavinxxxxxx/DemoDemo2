package com.gavin.demo.test;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * ListView基类适配器
 *
 * @author gavin.xiong 2016/9/10
 */
public abstract class ListAdapter<T> extends BaseAdapter {

	protected List<T> mList;
	private LayoutInflater inflater;
	private int layoutId;
	public Context mContext;

	public ListAdapter(Context context, List<T> mList, @LayoutRes int layoutId) {
		inflater = LayoutInflater.from(context);
		this.mList = mList;
		this.layoutId = layoutId;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mList != null ? mList.size() : 0;
	}

	@Override
	public T getItem(int arg0) {
		return mList != null ? mList.get(arg0) : null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder holder;
		if (view == null) {
			view = inflater.inflate(layoutId, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		convert(holder, getItem(position), position);
		return view;
	}

	public abstract void convert(ViewHolder holder, T item, int position);
}
