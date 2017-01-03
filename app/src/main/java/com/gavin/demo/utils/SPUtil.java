package com.gavin.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.gavin.demo.app.AppContext;

/**
 * SharedPreferences 数据存储工具类
 *
 * @author gavin.xiong
 */
public class SPUtil {

    private static String PREFERENCE = "PREFERENCE";

    /**
     * 存储字符串数据类型
     */
    public static void saveString(String key, String value) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 返回String类型数据，默认是“”；
     */
    public static String getString(String key) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 存储boolean数据类型
     */
    public static void saveBoolean(String key, boolean value) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 返回boolean类型数据，默认是true；
     */
    public static boolean getBoolean(String key) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

}
