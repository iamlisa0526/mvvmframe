package com.lisa.mvvmframe.baselib.customview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @Description: 自定义View基类
 * @Author: lisa
 * @CreateDate: 2020/5/21 13:17
 */
public abstract class BaseCustomView<V extends ViewDataBinding, VM extends BaseCustomViewModel> extends LinearLayout implements View.OnClickListener, ICustomView {
    private V dataBinding;
    private VM viewModel;
    private ICustomViewActionListener mListener;

    public BaseCustomView(Context context) {
        super(context);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public View getRootView() {
        return dataBinding.getRoot();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (setViewLayoutId() != 0) {
            dataBinding = DataBindingUtil.inflate(inflater, setViewLayoutId(), this, false);
            dataBinding.getRoot().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onAction(ICustomViewActionListener.ACTION_ROOT_VIEW_CLICKED, view, viewModel);
                    }
                    onRootClick(view);
                }
            });
        }

    }

    @Override
    public void setData(BaseCustomViewModel data) {
        viewModel = (VM) data;
        setDataToView(viewModel);
        if (dataBinding != null) {
            dataBinding.executePendingBindings();
        }
        onDataUpdated();
    }

    protected void onDataUpdated() {

    }

    @Override
    public void setStyle(int resId) {

    }

    @Override
    public void setActionListener(ICustomViewActionListener listener) {

    }

    @Override
    public void onClick(View v) {

    }

    public V getDataBinding() {
        return dataBinding;
    }

    public VM getViewModel() {
        return viewModel;
    }

    @IdRes
    protected abstract int setViewLayoutId();

    protected abstract void setDataToView(VM viewModel);

    protected abstract void onRootClick(View view);

}
