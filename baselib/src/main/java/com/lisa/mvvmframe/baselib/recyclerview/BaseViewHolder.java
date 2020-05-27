package com.lisa.mvvmframe.baselib.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lisa.mvvmframe.baselib.customview.BaseCustomViewModel;
import com.lisa.mvvmframe.baselib.customview.ICustomView;

import io.reactivex.annotations.NonNull;

/**
 * @Description: ViewHolder基类
 * @Author: lisa
 * @CreateDate: 2020/5/26 13:03
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private ICustomView view;

    public BaseViewHolder(ICustomView view) {
        super((View) view);
        this.view = view;
    }

    public void bind(@NonNull BaseCustomViewModel item) {
        view.setData(item);
    }
}
