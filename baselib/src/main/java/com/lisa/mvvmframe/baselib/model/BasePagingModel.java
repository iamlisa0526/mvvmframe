package com.lisa.mvvmframe.baselib.model;

import java.lang.ref.WeakReference;

/**
 * @Description: 分页model基类
 * @Author: lisa
 * @CreateDate: 2020/5/6 10:54
 */
public abstract class BasePagingModel<T> extends SuperBaseModel<T> {
    protected boolean isRefresh = true;
    protected int pageNo = 1;
    protected int pageSize = 15;

    /**
     * 发消息给UI线程
     *
     * @param data
     * @param isEmpty     是否为空
     * @param isFirstPage 是否第一页
     * @param hasNextPage 是否还有下一页
     */
    protected void loadSuccess(T data, final boolean isEmpty, final boolean isFirstPage, final boolean hasNextPage) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerList) {
                    if (weakListener.get() instanceof BasePagingModel.IPagingModelListener) {
                        IPagingModelListener listenerItem = (IPagingModelListener) weakListener.get();
                        if (listenerItem != null) {
                            listenerItem.onLoadFinish(BasePagingModel.this, data, isEmpty, isFirstPage, hasNextPage);
                        }
                    }
                }
                /** 如果我们需要缓存数据，加载成功，保存到preferences*/
                if (getCachedPreferenceKey() != null && isFirstPage) {
                    saveData2Preference(data);
                }
            }, 0);
        }
    }

    protected void loadFail(final String prompt, final boolean isFirstPage) {
        synchronized (this) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (WeakReference<IBaseModelListener> weakListener : mWeakListenerList) {
                        if (weakListener instanceof BasePagingModel.IPagingModelListener) {
                            IPagingModelListener listenerItem = (IPagingModelListener) weakListener.get();
                            if (listenerItem != null) {
                                listenerItem.onLoadFail(BasePagingModel.this, prompt, isFirstPage);
                            }
                        }
                    }
                }
            }, 0);
        }
    }

    @Override
    protected void notifyCachedData(T data) {
        loadSuccess(data, false, true, true);
    }

    public interface IPagingModelListener<T> extends IBaseModelListener {
        void onLoadFinish(BasePagingModel model, T data, boolean isEmpty, boolean isFirstPage, boolean hasNextPage);

        void onLoadFail(BasePagingModel model, String prompt, boolean isFirstPage);
    }

}
