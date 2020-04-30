package com.lisa.base.view;

/**
 * @Description: 提供给不需要databinding的地方用
 * @Author: lisa
 * @CreateDate: 2020/4/30 13:28
 */
public interface IBaseView {

    void showLoading();

    void showContent();

    void onRefreshEmpty();

    void onRefreshFail(String msg);

}
