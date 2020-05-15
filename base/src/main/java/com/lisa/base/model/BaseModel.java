package com.lisa.base.model;

import java.lang.ref.WeakReference;

/**
 * @Description: 不分页model基类
 * @Author: lisa
 * @CreateDate: 2020/5/6 11:01
 */
public abstract class BaseModel<T> extends SuperBaseModel<T> {
    /**
     * 加载网络数据成功，通知所有的注册者加载结果
     *
     * @param data
     */
    protected void loadSuccess(T data) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerList) {
                    if (weakListener.get() instanceof BaseModel.IModelListener) {
                        IModelListener listenerItem = (IModelListener) weakListener.get();
                        if (listenerItem != null) {
                            listenerItem.onLoadFinish(BaseModel.this, data);
                        }
                    }
                }
                /** 如果我们需要缓存数据，加载成功，保存到preferences*/
                if (getCachedPreferenceKey() != null) {
                    saveData2Preference(data);
                }
            }, 0);
        }
    }

    /**
     * 加载网络数据失败，通知所有的注册者加载结果
     *
     * @param prompt 错误提示
     */
    protected void loadFail(String prompt) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerList) {
                    if (weakListener.get() instanceof BaseModel.IModelListener) {
                        IModelListener listenerItem = (IModelListener) weakListener.get();
                        if (listenerItem != null) {
                            listenerItem.onLoadFinish(BaseModel.this, prompt);
                        }
                    }
                }
            }, 0);
        }
    }

    @Override
    protected void notifyCachedData(T data) {
        loadSuccess(data);
    }

    public interface IModelListener<T> extends IBaseModelListener {
        void onLoadFinish(BaseModel model, T data);

        void onLoadFail(BaseModel model, String prompt);
    }

}
