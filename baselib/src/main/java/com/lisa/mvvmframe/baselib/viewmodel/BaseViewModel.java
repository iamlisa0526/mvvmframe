package com.lisa.mvvmframe.baselib.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.lisa.mvvmframe.baselib.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Description: ViewModel基类
 * @Author: lisa
 * @CreateDate: 2020/4/15 13:07
 */
public class BaseViewModel<V, M extends SuperBaseModel> extends ViewModel implements IBaseViewModel<V> {
    private Reference<V> uiRef;
    protected M modle;

    @Override
    public void attachUI(V view) {
        uiRef = new WeakReference<>(view);
    }

    @Override
    public V getPageView() {
        if (uiRef == null) {
            return null;
        }
        return uiRef.get();
    }

    @Override
    public boolean isUIAttached() {
        return uiRef != null && uiRef.get() != null;
    }

    @Override
    public void detachUI() {
        if (uiRef != null) {
            uiRef.clear();
            uiRef = null;
        }
        if (modle != null) {
            modle.cancell();
        }
    }
}
