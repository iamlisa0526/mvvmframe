package com.lisa.mvvmframe.baselib.utils;


import com.lisa.mvvmframe.baselib.preferences.BasePreferences;

/**
 * @Description: Preferences工具类
 * @Author: lisa
 * @CreateDate: 2020/5/6 16:48
 */
public class PreferencesUtil extends BasePreferences {
    private static PreferencesUtil mInstance;

    public static PreferencesUtil getInstance() {
        if (mInstance == null) {
            synchronized (PreferencesUtil.class) {
                if (mInstance == null) {
                    mInstance = new PreferencesUtil();
                }
            }
        }
        return mInstance;
    }

    @Override
    protected String getSPFileName() {
        return "common_data";
    }
}
