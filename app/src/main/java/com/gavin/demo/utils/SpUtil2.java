package com.gavin.demo.utils;

import android.preference.PreferenceManager;

import com.gavin.demo.app.AppContext;

/**
 * @author lsxiao
 * @date 2015-11-08 23:21
 */
public class SpUtil2 {

    public static void saveOrUpdate(String key, String json) {
        PreferenceManager.getDefaultSharedPreferences(AppContext
                .getApplication()).edit().putString(key, json).apply();
    }

    public static String find(String key) {
        return PreferenceManager.getDefaultSharedPreferences(AppContext
                .getApplication()).getString(key, null);
    }

    public static void delete(String key) {
        PreferenceManager.getDefaultSharedPreferences(AppContext
                .getApplication()).edit().remove(key).apply();
    }

    public static void clearAll() {
        PreferenceManager.getDefaultSharedPreferences(AppContext
                .getApplication()).edit().clear().apply();
    }

}
