package com.lisa.base.utils;

import com.lisa.base.preferences.BasePreferences;

/**
 * @Description: java类作用描述
 * @Author: lisa
 * @CreateDate: 2020/5/6 13:48
 */
public class BasicDataPreferenceUtil extends BasePreferences {
    private static final String BASIC_DATA_PREFERENCE_FILE_NAME = "network_api_module_basic_data_preference";
    private static BasicDataPreferenceUtil instance;

    public static BasicDataPreferenceUtil getInstance() {
        if (instance == null) {
            synchronized (BasicDataPreferenceUtil.class) {
                if (instance == null) {
                    instance = new BasicDataPreferenceUtil();
                }
            }
        }
        return instance;
    }

    @Override
    protected String getSPFileName() {
        return BASIC_DATA_PREFERENCE_FILE_NAME;
    }
}
