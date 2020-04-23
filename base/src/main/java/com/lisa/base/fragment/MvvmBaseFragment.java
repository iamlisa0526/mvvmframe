package com.lisa.base.fragment;

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

import com.lisa.base.viewmodle.BaseViewModle;

/**
 * @Description: MVVM fragment基类
 * @Author: lisa
 * @CreateDate: 2020/4/23 13:59
 */
public abstract class MvvmBaseFragment<V extends ViewDataBinding, VM extends BaseViewModle> extends Fragment {
    protected V viewDataBinding;
    protected VM viewModle;
    protected String fragmentTag = "";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(getFragmentTag(), this + ": onActivityCreated");
    }

    protected abstract String getFragmentTag();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        viewModle.attachUI(this);
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModle);
            viewDataBinding.executePendingBindings();
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract VM getViewModle();

    protected abstract int getBindingVariable();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(getFragmentTag(), this + ": onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (viewModle.isUIAttached())
            viewModle.detachUI();
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
