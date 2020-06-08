package com.lisa.mvvmframe.baselib.view.mvc;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @Description: Fragment基类
 * @Author: lisa
 * @CreateDate: 2020/6/8 09:10
 */
public class BaseFragment extends Fragment {
    protected Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }
}
