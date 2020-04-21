package com.lisa.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lisa.base.viewmodle.BaseViewModle;

/**
 * @Description: MVVM Activity基类
 * @Author: lisa
 * @CreateDate: 2020/4/15 13:03
 */
public abstract class MvvmBaseActivity<V extends ViewDataBinding, VM extends BaseViewModle> extends AppCompatActivity {
    protected V viewDataBinding;
    protected VM viewModle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doDataBinding();
    }

    /**
     * 执行数据绑定
     */
    private void doDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        //todo 内存泄露
        viewModle = getViewModle();
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModle);
        }
        viewDataBinding.executePendingBindings();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract VM getViewModle();

    protected abstract int getBindingVariable();

}
