package com.lisa.base.model;

/**
 * @Description: 无需分页的model基类
 * @Author: lisa
 * @CreateDate: 2020/5/6 11:01
 */
public class BaseModel extends SuperBaseModel {
    @Override
    public void refresh() {

    }

    @Override
    protected void load() {

    }

    @Override
    protected void notifyCachedData(Object data) {

    }
}
