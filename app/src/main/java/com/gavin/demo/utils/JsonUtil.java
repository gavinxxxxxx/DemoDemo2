package com.gavin.demo.utils;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * FastJson 工具类
 * <p/>
 * 支持反序列号（在不需要序列化的字段上加注解
 * < @JSONField(serialize = false) > ）
 * (只在把对象转成字符串有效)
 */
public class JsonUtil {

    private static String TAG = "FastJson";

    static String toJson(Object o) {
        try {
            return JSON.toJSONString(o);
        } catch (Exception e) {
            return "";
        }
    }

    static <T> T toObject(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            L.e(TAG, e.toString());
            return null;
        }
    }

    static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            return JSON.parseArray(json, clazz);
        } catch (Exception e) {
            L.e(TAG, e.toString());
            return null;
        }
    }

    static String getString(String json, String key) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString(key);
        } catch (JSONException e) {
            L.e(TAG, e.toString());
            return null;
        }
    }
}