package com.lisa.mvvmframe.baselib.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.lisa.mvvmframe.baselib.R;

/**
 * @Description: loadsir空数据回调显示的view
 * @Author: lisa
 * @CreateDate: 2020/4/30 13:38
 */
public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }
}
