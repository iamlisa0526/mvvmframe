package com.lisa.mvvmframe.baselib.view.mvc;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.liveeventbus.LiveEventBus;

/**
 * @Description: MVC-Activity基类
 * @Author: lisa
 * @CreateDate: 2020/5/27 09:26
 */
public class BaseActivity extends AppCompatActivity {
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        //关闭activity
        LiveEventBus.get("close_activity", Boolean.class)
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean b) {
                        if (b) finish();
                    }
                });


    }
}
