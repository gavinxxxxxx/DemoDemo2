package com.gavin.demo.test;

import android.content.Context;

import com.gavin.demo.R;

import java.util.List;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2017/1/4  2017/1/4
 */
public class TestListAdapter extends ListAdapter<String> {

    public TestListAdapter(Context context, List<String> mList) {
        super(context, mList, R.layout.item);
    }

    @Override
    public void convert(ViewHolder holder, String item, int position) {
        holder.setText(R.id.textView1, mList.get(position));
    }
}
