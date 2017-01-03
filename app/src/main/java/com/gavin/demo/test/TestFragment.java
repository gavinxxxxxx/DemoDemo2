package com.gavin.demo.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.FragmentTestBinding;
import com.gavin.demo.features.usage.retrofit.User;
import com.gavin.demo.utils.JsonUtil;
import com.gavin.demo.utils.L;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 测试页
 *
 * @author gavin.xiong 2016/11/10
 */
public class TestFragment extends SupportFragment implements View.OnClickListener {

    FragmentTestBinding binding;

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.root.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        User user = new User();
        user.setId("1");
        user.setHeadPic("headPic");
        user.setNickName("nickName");
        user.setPhone("phone");
        user.setSex(1);

//        parse(user);
//        parse2();
        parse3();
    }

    private void parse(User user) {
        String json = JsonUtil.toJson(user);
        L.e(json);

        User user1 = JsonUtil.toObj(json, User.class);
        L.e(user1);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        String json1 = JsonUtil.toJson(userList);
        L.e(json1);

        L.e(JsonUtil.toList(json1, new TypeToken<ArrayList<User>>() {}));
        L.e(JsonUtil.toList2(json1, User[].class));
    }

    private void parse2() {
        String json = "{'headPic':'headPic',id:1001,'nickName':'nickName','phone':'phone','sex':'1'}";

        User user = JsonUtil.toObj(json, User.class);

        L.e(user);
        L.e(user.getId());
        L.e(user.getSex());
    }

    private void parse3() {
        User us = new User();
//        us.setName("NAME");
        us.setNickName("EMAIL");
        us.setSex(12);
        us.setHeadPic("");

        L.e(JsonUtil.toJson(us));

        L.e(JsonUtil.toObj(JsonUtil.toJson(us), User.class));
    }

}
