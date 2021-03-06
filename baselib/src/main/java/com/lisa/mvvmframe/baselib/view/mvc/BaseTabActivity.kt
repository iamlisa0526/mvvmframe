package com.lisa.mvvmframe.baselib.view.mvc

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.lisa.mvvmframe.baselib.R
import com.lisa.mvvmframe.baselib.adapter.BaseTabAdapter
import com.lisa.mvvmframe.baselib.utils.TabLayoutUtils
import kotlinx.android.synthetic.main.activity_base_tab.*
import java.util.*

/**
 * @Description: BaseTabAdapter
 * @Author: lisa
 * @CreateDate: 2020/6/8 09:10
 */
abstract class BaseTabActivity : BaseActivity() {
    private var fragments: List<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_tab)
        initTab()
    }

    private fun initTab() {
        vp_fragment.adapter = BaseTabAdapter(supportFragmentManager, titles, getFragments(fragments))
        tab_layout.tabMode = TabLayout.MODE_FIXED
        tab_layout.setupWithViewPager(vp_fragment)
        TabLayoutUtils.setIndicator(tab_layout, 25, 25) //设置tab中item的margin
    }

    abstract val titles: Array<String>

    abstract fun getFragments(fragments: List<Fragment>?): List<Fragment?>?
}