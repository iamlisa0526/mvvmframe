package com.lisa.base.viewmodel;

/**
 * @Description: java类作用描述
 * @Author: lisa
 * @CreateDate: 2020/4/23 14:59
 */
interface IBaseViewModel<V> {
    void attachUI(V view);

    V getPageView();

    boolean isUIAttached();

    void detachUI();
}
