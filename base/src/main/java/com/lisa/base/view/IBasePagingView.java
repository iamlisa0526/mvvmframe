package com.lisa.base.view;

/**
 * @Description: 分页刷新回调
 * @Author: lisa
 * @CreateDate: 2020/4/30 15:01
 */
public interface IBasePagingView extends IBaseView {

    void onLoadMoreFail(String msg);

    void onLoadMoreEmpty();

}
