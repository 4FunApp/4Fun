package com.joker.fourfun.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.joker.fourfun.FourFun;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by joker on 2017/1/4.
 */

public class PreferenceUtil {
    public static final String DEFAULT_PREFERENCES_FILE = "fourfun";

    public static SharedPreferences getSp() {
        return FourFun.getInstance().getContext().getSharedPreferences
                (DEFAULT_PREFERENCES_FILE,
                        MODE_PRIVATE);
    }

    public static SharedPreferences getSp(Activity activity, String name) {
        return activity.getSharedPreferences(name, MODE_PRIVATE);
    }

    /**
     * 默认的存值
     *
     * @param key
     * @param value
     */
    public static void putBoolean(String key, boolean value) {
        getSp().edit().putBoolean(key, value).apply();
    }

    /**
     * 携有 sharedPreference 文件名的存值方法
     *
     * @param activity
     * @param name
     * @param key
     * @param value
     */
    public static void putBoolean(AppCompatActivity activity, String name, String key, boolean value) {
        getSp(activity, name).edit().putBoolean(key, value).apply();
    }

    /**
     * 默认取值
     *
     * @param key
     */
    public static boolean getDefaultFalse(String key) {
        return getSp().getBoolean(key, false);
    }

    public static boolean getDefaultFalse(AppCompatActivity activity, String name, String key) {
        return getSp(activity, name).getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getSp().getBoolean(key, defValue);
    }

    public static boolean getBoolean(AppCompatActivity activity, String name, String key, boolean
            defValue) {
        return getSp(activity, name).getBoolean(key, defValue);
    }
}
