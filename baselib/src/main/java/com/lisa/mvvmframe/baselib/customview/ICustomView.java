package com.lisa.mvvmframe.baselib.customview;

import android.support.annotation.IdRes;

/**
 * @Description: 自定义View基类的回调接口
 * @Author: lisa
 * @CreateDate: 2020/5/21 13:21
 */
interface ICustomView<M extends BaseCustomViewModel> {
    void setData(M data);

    void setStyle(@IdRes int resId);

    void setActionListener(ICustomViewActionListener listener);
}
