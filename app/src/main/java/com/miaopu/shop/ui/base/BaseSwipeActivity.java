package com.miaopu.shop.ui.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.view.LoadingDialog;
import com.zzh.sexual.secret.R;
import com.zzh.zlibs.base.BaseActivity;

/**
 * Created by user on 2017/12/22.
 *
 * @date: 2017/12/22
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 可以往右滑动的退出当前页面的基类。
 */
public abstract class BaseSwipeActivity extends BaseActivity {


    protected LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShopApplication.addActivity(this);
    }

    public void showDialog(String msg) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(mContext);
        }
        if (!TextUtils.isEmpty(msg)) {
            mLoadingDialog.setShowText(msg);
        }
        mLoadingDialog.show();
    }

    public void showDialog() {
        showDialog(null);
    }

    public void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * @param ic_back  返回按钮
     * @param title    标题
     * @param listener 返回按钮的监听事件
     */
    public void initToolbar(int ic_back, String title, View.OnClickListener listener) {
        setToolbarAndTitle(R.id.toolbar, R.id.toolbar_title);
        toolbars(R.layout.include_toolbar, ic_back, title, listener);
    }

    /**
     * @param title    标题
     * @param listener 返回按钮的监听事件
     */
    public void initToolbar(String title, View.OnClickListener listener) {
        setToolbarAndTitle(R.id.toolbar, R.id.toolbar_title);
        toolbars(R.layout.include_toolbar, R.mipmap.ic_back, title, listener);
    }

    /**
     * @param title    标题 资源id
     * @param listener 返回按钮的监听事件
     */
    public void initToolbar(int title, View.OnClickListener listener) {
        setToolbarAndTitle(R.id.toolbar, R.id.toolbar_title);
        toolbars(R.id.toolbar, R.mipmap.ic_back, getResources().getString(title), listener);
    }

    /**
     * @param title 标题
     */
    public void initToolbar(String title) {
        setToolbarAndTitle(R.id.toolbar, R.id.toolbar_title);
        toolbars(R.id.toolbar, R.mipmap.ic_back, title, null);
    }

    /**
     * @param title 标题
     */
    public void initToolbar(int title, int color) {
        setToolbarAndTitle(R.id.toolbar, R.id.toolbar_title);
        toolbars(R.id.toolbar, R.mipmap.ic_back, getResources().getString(title), null);
        mTitle.setTextColor(color);
    }

    /**
     * @param title 标题资源id R.string.title
     */
    public void initToolbar(int title) {
        setToolbarAndTitle(R.id.toolbar, R.id.toolbar_title);
        toolbars(R.id.toolbar, R.mipmap.ic_back, getResources().getString(title), null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShopApplication.removeActivity(this);
    }
}
