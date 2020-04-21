package com.lisa.base;

import android.app.Application;

/**
 * @Description: Application基类
 * @Author: lisa
 * @CreateDate: 2020/4/12 09:37
 */
public class BaseApplication extends Application {
    //OOM won't happen
    public static Application application;

    public static boolean debug;

    /**
     * 继承该类的子类在onCreate调用该方法即可，传参应该是Application的debug开关
     *
     * @param isDebug
     */
    public void setDebug(boolean isDebug) {
        debug = isDebug;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
