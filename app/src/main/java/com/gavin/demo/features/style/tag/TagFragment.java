package com.gavin.demo.features.style.tag;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.FragmentTagGroupBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * TagGroup示例
 *
 * @author gavin.xiong 2016/11/10
 */
public class TagFragment extends SupportFragment {

    FragmentTagGroupBinding binding;

    public static TagFragment newInstance() {
        return new TagFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tag_group, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        List<String> batch = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            batch.add(randomString(new Random().nextInt(20) + 1));
        }
        List<TagColor> colors = TagColor.getRandomColors(batch.size());
        binding.tagGroup.setTags(colors, (String[]) batch.toArray(new String[batch.size()]));

        binding.tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                Snackbar.make(binding.tagGroup, tag, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 方法体很简便，优点是生成的随机字符串中字符种类很多，缺点是只适用于JDK 1.7的版本。
     */
    public static String random(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));
        }
        return builder.toString();
    }

    /**
     * 方法一可以产生给定字符串中可以出现的字符，但也只能出现这些字符。
     */
    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            sb.append(str.charAt(num));
        }
        return sb.toString();
    }

}
