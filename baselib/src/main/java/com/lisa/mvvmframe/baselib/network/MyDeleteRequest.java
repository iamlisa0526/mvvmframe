/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lisa.mvvmframe.baselib.network;

import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.request.DeleteRequest;

import io.reactivex.disposables.Disposable;


/**
 * <p>描述：扩展GetResult请求，解决自定义ApiResult重复写代理的问题</p>
 * 1.假如你的自定义ApiResult是MyApiResult<br>
 * 作者： zhouyou<br>
 * 日期： 2017/7/7 10:23 <br>
 * 版本： v1.0<br>
 */
public class MyDeleteRequest extends DeleteRequest {
    public MyDeleteRequest(String url) {
        super(url);
    }

    /**
     * 覆写execute方法指定，代理用MyApiResult
     *
     * @param <T>
     * @return
     */

    @Override
    public <T> Disposable execute(CallBack<T> callBack) {
        return super.execute(new CallBackProxy<MyApiResult<T>, T>(callBack) {
        });
    }
}
