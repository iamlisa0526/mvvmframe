package com.lisa.mvvmframe.baselib.view.mvc

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.Callback.OnReloadListener
import com.kingja.loadsir.core.Convertor
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lisa.mvvmframe.baselib.R
import com.lisa.mvvmframe.baselib.dto.BasePageDto
import com.lisa.mvvmframe.baselib.loadsir.EmptyCallback
import com.lisa.mvvmframe.baselib.loadsir.ErrorCallback
import com.lisa.mvvmframe.baselib.network.MyApiResult
import kotlinx.android.synthetic.main.activity_base_list.*

/**
 * @Description: 列表基类
 * @Author: lisa
 * @CreateDate: 2020/5/26 10:20
 */
abstract class BaseListActivity<T> : BaseActivity() {
    protected val mList = arrayListOf<T>()
    private lateinit var mAdapter: RecyclerView.Adapter<*>
    private lateinit var mLoadService: LoadService<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base_list)

        header.visibility = View.VISIBLE

        mAdapter = getAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = mAdapter

        //注册loadsir，显示空布局,错误数据等
        mLoadService = LoadSir.getDefault().register(refresh_layout)

        if (!isRefresh())
            refresh_layout.setEnableRefresh(false)

        request()
    }

    /**
     * 是否刷新，默认不刷新
     */
    private fun isRefresh(): Boolean {
        return false
    }

    protected abstract fun request()

    /**
     * 获取适配器
     *
     * @return
     */
    protected abstract fun getAdapter(): RecyclerView.Adapter<*>

    /**
     * 更新数据
     */
    protected fun updateData(list: ArrayList<T>) {
        mList.clear()
        mList.addAll(list)
        mAdapter.notifyDataSetChanged()

        if (mList.isEmpty())
            mLoadService.showCallback(EmptyCallback::class.java)
    }


}
