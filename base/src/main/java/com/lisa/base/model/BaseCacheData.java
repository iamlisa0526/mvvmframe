package com.lisa.base.model;

import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @Author: lisa
 * @CreateDate: 2020/5/6 10:03
 */
class BaseCacheData<T> implements Serializable {
    public long mUpdateTimeMills;
    public T mData;
}
