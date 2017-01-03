package com.gavin.demo.features.usage.dagger2.mvp.presenter;

import com.gavin.demo.features.usage.dagger2.mvp.view.Dagger2Fragment3;
import com.gavin.demo.features.usage.dagger2.mvp.model.User;

public class DaggerPresenter {

    Dagger2Fragment3 fragment;
    User user;

    public DaggerPresenter(Dagger2Fragment3 fragment, User user) {
        this.user = user;
        this.fragment = fragment;
    }

    public void showUserName() {
        fragment.showUserName(user.name);
    }
}
