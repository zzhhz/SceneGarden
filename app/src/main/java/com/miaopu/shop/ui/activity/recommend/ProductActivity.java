package com.miaopu.shop.ui.activity.recommend;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.fragment.ProductFragment;
import com.zzh.sexual.secret.R;

/**
 * Created by user on 2018/1/15.
 *
 * @date: 2018/1/15
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 推荐
 */
public class ProductActivity extends BaseSwipeActivity {
    @Override
    protected int setLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    protected void initView() {
        Fragment fragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(ProductFragment.JOIN_FROM, true);
        bundle.putString(ProductFragment.JOIN_FROM_TITLE, getIntent().getStringExtra("title"));
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.ll_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}
