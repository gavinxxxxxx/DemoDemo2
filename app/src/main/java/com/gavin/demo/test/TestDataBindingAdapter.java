package com.gavin.demo.test;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.ItemBinding;

import java.util.ArrayList;

/**
 * DataBinding Adapter 示例
 *
 * @author gavin.xiong 2016/11/10
 */
public class TestDataBindingAdapter extends RecyclerView.Adapter<TestDataBindingAdapter.ViewHolder> {

    private ArrayList<String> mData = new ArrayList<>();

    public TestDataBindingAdapter(ArrayList<String> data) {
        mData.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.item, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ((ItemBinding) viewHolder.binding).textView1.setText(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}