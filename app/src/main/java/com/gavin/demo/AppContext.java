package com.gavin.demo;

import android.app.Application;

/**
 * Application
 *
 * @author gavin.xiong 2016/5/18
 */
public class AppContext extends Application {

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getApplication() {
        return application;
    }

}
