package com.miaopu.shop.ui.activity.works;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miaopu.shop.ui.adapter.ViewPagerAdapter;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Tags;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 2018/1/16.
 *
 * @date: 2018/1/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 作品列表
 */
public class WorksActivity extends BaseSwipeActivity implements Toolbar.OnMenuItemClickListener, TabLayout.OnTabSelectedListener {
    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    public ViewPager mViewPager;

    private ViewPagerAdapter mPagerAdapter;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_works;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar(R.string.works, Color.parseColor("#020202"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        menu.getItem(1).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initData() {
        mTabLayout.addOnTabSelectedListener(this);
        RetrofitUtils.Api().getDesignTag().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseHandleConsumer<List<Tags>>() {
                    @Override
                    public void onSuccess(List<Tags> model) {
                        if (model != null && !model.isEmpty()) {
                            List<BaseStoreFragment> list = new ArrayList<>();
                            for (int i = 0; i < model.size(); i++) {
                                WorksStaggerFragment fragment = new WorksStaggerFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString(WorksStaggerFragment.KEY_DATA, model.get(i).getId());
                                fragment.setArguments(bundle);
                                list.add(fragment);
                            }
                            mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
                            mViewPager.setAdapter(mPagerAdapter);
                            mTabLayout.setupWithViewPager(mViewPager);
                            int tabCount = mTabLayout.getTabCount();
                            for (int i = 0; i < tabCount; i++) {
                                TabLayout.Tab tab = mTabLayout.getTabAt(i);
                                tab.setText(model.get(i).getLabel());
                            }
                        }
                    }

                    @Override
                    public void onFailed(String code) {

                    }
                });


    }

    @Override
    protected void initSetListener() {
        mToolbar.setOnMenuItemClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_path:
                //showMessage("点击了菜单");
                break;
        }
        return false;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
