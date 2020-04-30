package com.lisa.base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @Description: toast工具类
 * @Author: lisa
 * @CreateDate: 2020/4/30 15:01
 */
public class ToastUtil {
    private static Toast toast;

    public static void showToast(Context context, String msg) {
        try {
            if (context != null && !TextUtils.isEmpty(msg)) {
                if (toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
                toast.setText(msg);
                toast.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
