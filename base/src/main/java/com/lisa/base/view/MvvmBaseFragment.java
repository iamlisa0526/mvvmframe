package com.lisa.base.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lisa.base.loadsir.EmptyCallback;
import com.lisa.base.loadsir.ErrorCallback;
import com.lisa.base.loadsir.LoadingCallback;
import com.lisa.base.utils.ToastUtil;
import com.lisa.base.viewmodel.BaseViewModel;

/**
 * @Description: MVVM fragment基类
 * @Author: lisa
 * @CreateDate: 2020/4/23 13:59
 */
public abstract class MvvmBaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements IBasePagingView {
    protected V viewDataBinding;
    protected VM viewModle;
    protected String fragmentTag = "";
    private LoadService loadService;
    private boolean isShowedContent = false;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract VM getViewModle();

    protected abstract int getBindingVariable();

    protected abstract String getFragmentTag();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(getFragmentTag(), this + ": onActivityCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParams();
    }

    /**
     * 初始化参数
     */
    protected void initParams() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModle = getViewModle();
        if (viewModle != null) {
            viewModle.attachUI(this);
        }
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModle);
            viewDataBinding.executePendingBindings();
        }
    }

    /**
     * 设置loadsir
     *
     * @param view
     */
    protected void setLoadSir(View view) {
        loadService = LoadSir.getDefault().register(view, (Callback.OnReloadListener) v -> onRetryBtnClick());
    }

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
            isShowedContent = true;
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
            if (!isShowedContent) {
                loadService.showCallback(ErrorCallback.class);
            } else {
                ToastUtil.showToast(getContext(), msg);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(getFragmentTag(), this + ": onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (viewModle != null && viewModle.isUIAttached()) {
            viewModle.detachUI();
        }
        Log.d(getFragmentTag(), this + ": onDetach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getFragmentTag(), this + ": onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(getFragmentTag(), this + ": onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getFragmentTag(), this + ": onResume");
    }

    @Override
    public void onDestroy() {
        Log.d(getFragmentTag(), this + ": onDestroy");
        super.onDestroy();
    }
}
