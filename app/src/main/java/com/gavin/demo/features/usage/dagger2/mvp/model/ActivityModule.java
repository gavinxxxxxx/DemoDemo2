package com.gavin.demo.features.usage.dagger2.mvp.model;

import com.gavin.demo.features.usage.dagger2.mvp.presenter.DaggerPresenter;
import com.gavin.demo.features.usage.dagger2.mvp.view.Dagger2Fragment3;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Dagger2Fragment3 fragment;

    public ActivityModule(Dagger2Fragment3 fragment) {
        this.fragment = fragment;
    }

    @Provides
    public Dagger2Fragment3 provideActivity() {
        return fragment;
    }

    @Provides
    public User provideUser() {
        return new User("user form ActivityModule");
    }

    @Provides
    public DaggerPresenter provideDaggerPresenter(Dagger2Fragment3 fragment, User user) {
        return new DaggerPresenter(fragment, user);
    }
}