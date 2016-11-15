package com.gavin.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

import com.gavin.demo.AppContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

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

    /**
     * 存储int数据类型
     */
    public static void saveInt(String key, int value) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 返回int类型数据，默认是true；
     */
    public static int getInt(String key) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    /**
     * 存储float数据类型
     */
    public static void saveFloat(String key, float value) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * 返回float类型数据，默认是true；
     */
    public static float getFloat(String key) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return sp.getFloat(key, 0);
    }

    /**
     * 存储long数据类型
     */
    public static void saveLong(String key, long value) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 返回long类型数据，默认是true；
     */
    public static long getLong(String key) {
        SharedPreferences sp = AppContext.getApplication().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }

    /**
     * 储存复杂的数据对象字段
     */
    public static <T> void saveObjToShare(String key, T t) {
        if (t == null) {
            saveString(key, "");
        } else {
            String json = JsonUtil.toJson(t);
            saveString(key, json);
        }
    }

    /**
     * 得到复杂的数据对象
     */
    public static <T> T getObjFromShare(String key, Class<T> clazz) {
        String json = getString(key);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return JsonUtil.toObject(json, clazz);
        }
    }

    /**
     * 得到复杂的数据对象
     */
    public static <T> List<T> getListFromShare(String key, Class<T> clazz) {
        String json = getString(key);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return JsonUtil.toList(json, clazz);
        }
    }

    /**
     * 储存复杂的数据对象字段
     */
    public static <T> boolean saveObjToShareBase64(String key, T t) {
        return saveObjToShareBase64(PREFERENCE, key, t);
    }

    /**
     * 储存复杂的数据对象字段
     */
    public static <T> boolean saveObjToShareBase64(String name, String key, T t) {
        try {
            SharedPreferences sp = AppContext.getApplication().getSharedPreferences(name, Context.MODE_PRIVATE);
            // 存储
            Editor editor = sp.edit();
            if (t == null) {
                editor.putString(key, "");
                editor.commit();
                return true;
            }
            ByteArrayOutputStream toByte = new ByteArrayOutputStream();
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(toByte);
            oos.writeObject(t);
            // 对byte[]进行Base64编码
            String payCityMapBase64 = new String(Base64.encode(toByte.toByteArray(), Base64.DEFAULT));
            editor.putString(key, payCityMapBase64);
            editor.commit();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 得到复杂的数据对象
     */
    public static <T> T getObjFromShareBase64(String key) {
        return getObjFromShareBase64(PREFERENCE, key);
    }

    /**
     * 得到复杂的数据对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjFromShareBase64(String name, String key) {
        try {
            SharedPreferences sp = AppContext.getApplication().getSharedPreferences(name, Context.MODE_PRIVATE);
            String payCityMapBase64 = sp.getString(key, "");
            if (payCityMapBase64.length() == 0) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(payCityMapBase64, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
