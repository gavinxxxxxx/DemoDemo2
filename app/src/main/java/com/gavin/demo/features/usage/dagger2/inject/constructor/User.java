package com.gavin.demo.features.usage.dagger2.inject.constructor;

import javax.inject.Inject;

public class User {

    public String name;

    // 用Inject标记构造函数,表示用它来注入到目标对象中去
    @Inject
    public User() {
        this.name = "生活就像海洋";
    }
}
