package com.lisa.mvvmframe.baselib.customview;

import android.view.View;

/**
 * @Description: 自定义View点击事件回调接口
 * @Author: lisa
 * @CreateDate: 2020/5/21 13:24
 */
public interface ICustomViewActionListener {
    String ACTION_ROOT_VIEW_CLICKED = "action_root_view_clicked";

    void onAction(String action, View view, BaseCustomViewModel viewModel);
}
