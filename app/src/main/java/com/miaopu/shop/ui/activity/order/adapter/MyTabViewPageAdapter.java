package com.miaopu.shop.ui.activity.order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.miaopu.shop.ui.base.BaseStoreFragment;

import java.util.List;

/**
 * TabLayout切换fragment适配器
 * Created by chenM on 2016/1/10.
 *
 * 参数：FragmentManager
 *      Context
 *      List<Fragment>
 *      String[] tab的title
 *      count  tab数量
 *
 */
public class MyTabViewPageAdapter extends FragmentPagerAdapter {
    private int PAGE_COUNT;
    private String[] tabTitles;
    List<BaseStoreFragment> list;

    public MyTabViewPageAdapter(FragmentManager fm, List<BaseStoreFragment> list, String[] title, int count) {
        super(fm);
        this.list = list;
        this.tabTitles = title;
        this.PAGE_COUNT = count;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}