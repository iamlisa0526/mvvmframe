package com.lisa.mvvmframe.baselib.model;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.text.TextUtils;

import com.lisa.mvvmframe.baselib.utils.BasicDataPreferenceUtil;
import com.lisa.mvvmframe.baselib.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * @Description: Model基类
 * @Author: lisa
 * @CreateDate: 2020/5/6 09:29
 */
public abstract class SuperBaseModel<T> {
    protected Handler mUiHandler = new Handler(Looper.getMainLooper());
    private CompositeDisposable mCompositeDisposable;
    protected ReferenceQueue<IBaseModelListener> mReferenceQueue;
    protected ConcurrentLinkedQueue<WeakReference<IBaseModelListener>> mWeakListenerList;
    private BaseCacheData<T> mData;

    public SuperBaseModel() {
        mReferenceQueue = new ReferenceQueue<>();
        mWeakListenerList = new ConcurrentLinkedQueue<>();
        if (getCachedPreferenceKey() != null) {
            mData = new BaseCacheData<>();
        }
    }

    /**
     * 注册监听
     *
     * @param listener
     */
    public void register(IBaseModelListener listener) {
        if (listener == null)
            return;

        synchronized (this) {
            //每次注册的时候清理已经被系统回收的对象
            Reference<? extends IBaseModelListener> releaseListener = null;
            while ((releaseListener = mReferenceQueue.poll()) != null) {
                mWeakListenerList.remove(releaseListener);
            }

            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerList) {
                IBaseModelListener listenerItem = weakListener.get();
                if (listenerItem == listener)
                    return;
            }

            WeakReference<IBaseModelListener> weakListener = new WeakReference<>(listener, mReferenceQueue);
            mWeakListenerList.add(weakListener);
        }

    }

    /**
     * 注销监听
     *
     * @param listener
     */
    public void unRegister(IBaseModelListener listener) {

        if (listener == null) return;
        synchronized (this) {
            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerList) {
                IBaseModelListener listenerItem = weakListener.get();
                if (listener == listenerItem) {
                    mWeakListenerList.remove(weakListener);
                    break;
                }
            }
        }
    }

    /**
     * 缓存数据
     *
     * @param data
     */
    protected void saveData2Preference(T data) {
        this.mData.mData = data;
        this.mData.mUpdateTimeMills = System.currentTimeMillis();
        BasicDataPreferenceUtil.getInstance().setString(getCachedPreferenceKey(), GsonUtil.toJson(data));
    }

    /**
     * 刷新
     */
    public abstract void refresh();

    /**
     * 加载
     */
    protected abstract void load();

    /**
     * 缓存数据
     *
     * @param data
     */
    protected abstract void notifyCachedData(T data);

    /**
     * 该model的数据是否需要缓存，如果需要请返回缓存的key
     *
     * @return
     */
    protected String getCachedPreferenceKey() {
        return null;
    }

    /**
     * 缓存的数据的类型
     *
     * @return
     */
    protected Type getTClass() {
        return null;
    }

    /**
     * 该model的数据是否有apk级别的缓存，如果有请返回，默认没有
     *
     * @return
     */
    protected String getApkString() {
        return null;
    }

    /**
     * 是否更新数据，可以在这里设计策略，可以是一天一次，一个月一次等；
     * 默认是每次请求都更新
     *
     * @return
     */
    protected boolean isNeedUpdate() {
        return true;
    }

    /**
     * 防止内存泄露
     */
    @CallSuper
    public void cancell() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public void addDisposable(Disposable d) {
        if (d == null) return;
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

    /**
     * 获取换粗数据并加载
     */
    public void getCachedDataAndLoad() {
        if (getCachedPreferenceKey() != null) {
            String saveDataString = BasicDataPreferenceUtil.getInstance().getString(getCachedPreferenceKey());
            if (!TextUtils.isEmpty(saveDataString)) {
                try {
                    T saveData = GsonUtil.fromJson(new JSONObject(saveDataString).getString("mData"), getTClass());
                    if (saveData != null) {
                        notifyCachedData(saveData);
                        if (isNeedUpdate()) {
                            load();
                        }
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (getApkString() != null) {
                notifyCachedData(GsonUtil.fromJson(getApkString(), getTClass()));
            }

            load();
        }
    }

    protected interface IBaseModelListener {
    }
}
