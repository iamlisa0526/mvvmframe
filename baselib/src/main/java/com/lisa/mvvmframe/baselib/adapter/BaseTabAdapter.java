package com.lisa.mvvmframe.baselib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @Description: BaseTabAdapter
 * @Author: lisa
 * @CreateDate: 2020/6/8 09:10
 */
public class BaseTabAdapter extends FragmentPagerAdapter {
    private String[] tabTitles;
    private List<Fragment> fragments;

    public BaseTabAdapter(FragmentManager fm, String[] tabTitles, List<Fragment> fragments) {
        super(fm);
        this.tabTitles = tabTitles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
