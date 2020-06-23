package com.lisa.mvvmframe.baselib.view.mvc;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.liveeventbus.LiveEventBus;

/**
 * @Description: Activity基类
 * @Author: lisa
 * @CreateDate: 2020/5/27 09:26
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        setContentView(getLayout());

        //关闭activity
        LiveEventBus.get("KEY_CLOSE_ACTIVITY", Boolean.class)
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean b) {
                        if (b) finish();
                    }
                });

        init();
    }

    protected abstract void init();

    protected abstract @LayoutRes
    int getLayout();
}
