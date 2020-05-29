package com.lisa.mvvmframe.baselib.recyclerview.mvc

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @Description:    RecyclerView.Adapter基类
 * @Author:         lisa
 * @CreateDate:     2020/5/28 13:48
 */

abstract class BaseAdapter<T>(private val list: List<T>, @LayoutRes private val layoutId: Int) : RecyclerView.Adapter<BaseViewHolder>() {
    private var mItemListener: OnItemClickListener<T>? = null

    fun setOnItemClickListener(mListener: OnItemClickListener<T>): BaseAdapter<T> {
        this.mItemListener = mListener
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindViewHolder(holder.itemView, list[position], position)
        holder.itemView.setOnClickListener { mItemListener?.onItemClick(it, position, list[position]) }
    }

    protected abstract fun onBindViewHolder(itemView: View, model: T, position: Int)

    override fun getItemCount(): Int {
        return list.size
    }


}
