package com.lisa.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lisa.base.loadsir.EmptyCallback;
import com.lisa.base.loadsir.ErrorCallback;
import com.lisa.base.loadsir.LoadingCallback;
import com.lisa.base.viewmodel.BaseViewModel;

/**
 * @Description: MVVM Activity基类
 * @Author: lisa
 * @CreateDate: 2020/4/15 13:03
 */
public abstract class MvvmBaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements IBaseView {
    protected V viewDataBinding;
    protected VM viewModle;
    private LoadService loadService;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract VM getViewModle();

    protected abstract int getBindingVariable();

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
            viewDataBinding.executePendingBindings();
        }
    }

    protected void setLoadSir(View view) {
        loadService = LoadSir.getDefault().register(view, (Callback.OnReloadListener) v -> onRetryBtnClick());
    }

    /**
     * 重试按钮点击事件
     */
    protected abstract void onRetryBtnClick();

    @Override
    public void showLoading() {
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showContent() {
        if (loadService != null) {
            loadService.showSuccess();
        }
    }

    @Override
    public void onRefreshEmpty() {
        if (loadService != null) {
            loadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onRefreshFail(String msg) {
        if (loadService != null) {
            loadService.showCallback(ErrorCallback.class);
        }
    }

}
