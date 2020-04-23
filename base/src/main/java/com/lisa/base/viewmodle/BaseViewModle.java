package com.lisa.base.viewmodle;

import android.arch.lifecycle.ViewModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Description: ViewModle基类
 * @Author: lisa
 * @CreateDate: 2020/4/15 13:07
 */
public class BaseViewModle<V> extends ViewModel implements IBaseViewModle<V> {
    private Reference<V> uiRef;

    @Override
    public void attachUI(V view) {
        uiRef = new WeakReference<>(view);
    }

    @Override
    public V getPageView() {
        return null;
    }

    @Override
    public boolean isUIAttached() {
        return false;
    }

    @Override
    public void detachUI() {

    }
}
