package com.miaopu.shop.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.miaopu.shop.ui.base.BaseStoreFragment;

import java.util.List;

/**
 * Created by user on 2018/1/11.
 *
 * @date: 2018/1/11
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseStoreFragment> dataList;

    public ViewPagerAdapter(FragmentManager fm, List<BaseStoreFragment> dataList) {
        super(fm);
        this.dataList = dataList;
    }

    @Override
    public Fragment getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }
}