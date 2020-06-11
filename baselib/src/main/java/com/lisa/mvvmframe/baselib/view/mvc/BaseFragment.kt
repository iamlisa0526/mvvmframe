package com.lisa.mvvmframe.baselib.view.mvc

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @Description: Fragment基类
 * @Author: lisa
 * @CreateDate: 2020/6/8 09:10
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var mContext: FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this.activity!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    /**
     * 初始化
     */
    abstract fun init()

    /**
     * xml布局
     */
    @LayoutRes
    abstract fun getLayout(): Int

}