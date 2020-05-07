package com.lisa.base.model;

import java.io.Serializable;

/**
 * @Description: 缓存数据基类
 * @Author: lisa
 * @CreateDate: 2020/5/6 10:03
 */
class BaseCacheData<T> implements Serializable {
    public long mUpdateTimeMills;
    public T mData;
}
