package com.lisa.mvvmframe.baselib.view.mvc

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.Callback.OnReloadListener
import com.kingja.loadsir.core.Convertor
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
    private lateinit var myAdapter: RecyclerView.Adapter<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base_list)

        header.visibility = View.VISIBLE

        myAdapter = getAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = myAdapter

        if (!isRefresh())
            refresh_layout.setEnableRefresh(false)

        registerLoadSir()

        request()
    }

    /**
     * 注册loadsir，显示空数据,请求错误等布局
     */
    private fun registerLoadSir() {
        //刷新说明是分页数据
        if (isRefresh()) {

            LoadSir.getDefault().register<MyApiResult<BasePageDto<T>>>(context, object : OnReloadListener {
                override fun onReload(v: View?) {

                }
            }, object : Convertor<MyApiResult<BasePageDto<T>>> {
                override fun map(apiResult: MyApiResult<BasePageDto<T>>): Class<out Callback>? {
                    val data = apiResult.data as BasePageDto<T>

                    var resultCode: Class<out Callback?>? = null

                    when (apiResult.code) {

                        MyApiResult.SUCCESS_CODE -> if (data.content.isEmpty()) {
                            resultCode = EmptyCallback::class.java
                        }

                        MyApiResult.ERROR_CODE -> resultCode = ErrorCallback::class.java

                        else -> resultCode = null
                    }

                    return resultCode
                }
            })

        } else {//不刷新说明不分页
            LoadSir.getDefault().register<MyApiResult<List<T>>>(context, object : OnReloadListener {
                override fun onReload(v: View?) {

                }
            }, object : Convertor<MyApiResult<List<T>>> {
                override fun map(apiResult: MyApiResult<List<T>>): Class<out Callback>? {
                    val data = apiResult.data as List<T>

                    var resultCode: Class<out Callback?>? = null

                    when (apiResult.code) {

                        MyApiResult.SUCCESS_CODE -> if (data.isEmpty()) {
                            resultCode = EmptyCallback::class.java
                        }

                        MyApiResult.ERROR_CODE -> resultCode = ErrorCallback::class.java

                        else -> resultCode = null
                    }

                    return resultCode
                }
            })
        }


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
        myAdapter.notifyDataSetChanged()
    }


}
