package com.lisa.base.model;

/**
 * @Description: 需要分页的model基类
 * @Author: lisa
 * @CreateDate: 2020/5/6 10:54
 */
public class BasePagingModel<T> extends SuperBaseModel<T> {
    @Override
    public void refresh() {

    }

    @Override
    protected void load() {

    }

    @Override
    protected void notifyCachedData(T data) {

    }
}
