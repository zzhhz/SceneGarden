package com.miaopu.shop.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.common.util.FileUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.reflect.TypeToken;
import com.miaopu.shop.ui.adapter.ProductListAdapter;
import com.miaopu.shop.ui.base.BaseShopActivity;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Condition2;
import com.miaopu.shop.ui.model.Product;
import com.miaopu.shop.ui.view.ScreenSheetDialog;
import com.miaopu.shop.utils.Constants;
import com.zzh.sexual.secret.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2018/1/2.
 *
 * @date: 2018/1/2
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 商品
 */
public class ProductFragment extends BaseStoreFragment implements OnLoadMoreListener, OnRefreshListener, ScreenSheetDialog.ScreenListener {
    public static final String JOIN_FROM = "join_from";
    public static final String JOIN_FROM_TITLE = "title";
    @BindView(R.id.recyclerView)
    public LRecyclerView mRecyclerView;
    @BindView(R.id.iv_back)
    public ImageView iv_back;

    private LRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ProductListAdapter mListAdapter;
    private int page = 1;
    private ScreenSheetDialog mSheetDialog;
    private boolean hasNextPage = false;
    private String title;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_product;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);
        mListAdapter = new ProductListAdapter(mContext);
        mLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new LRecyclerViewAdapter(mListAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnLoadMoreListener(this);
        mRecyclerView.setOnRefreshListener(this);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(JOIN_FROM)) {
            boolean b = bundle.getBoolean(JOIN_FROM, false);
            title = bundle.getString(JOIN_FROM_TITLE);
            if (b) {
                iv_back.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void initData() {
        mRecyclerView.forceToRefresh();
    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

        switch (msg.what)
        {
            case 200:
                List<Product> model = (List<Product>) msg.obj;
                mListAdapter.addAll(model);
                mRecyclerView.refreshComplete(Constants.PAGE_SIZE);
                if (mContext instanceof BaseSwipeActivity) {
                    ((BaseSwipeActivity) mContext).dismissDialog();
                } else if (mContext instanceof BaseShopActivity) {
                    ((BaseShopActivity) mContext).dismissDialog();
                }
                break;
        }
    }

    @Override
    public void onLoadMore() {
        if (hasNextPage) {
            page++;
            reloadData(null);
        } else {
            mRecyclerView.refreshComplete(Constants.PAGE_SIZE);
        }

    }

    @Override
    public void onRefresh() {
        page = 1;
        if (page == 1) {
            if (mContext instanceof BaseSwipeActivity) {
                ((BaseSwipeActivity) mContext).showDialog();
            } else if (mContext instanceof BaseShopActivity) {
                if (mContext != null) {
                    ((BaseShopActivity) mContext).showDialog();
                }
            }
        }
        mListAdapter.clear();
        mAdapter.notifyDataSetChanged();
        Condition2 condition2 = new Condition2();
        condition2.setTitle(title);
        reloadData(condition2);

    }

    private void reloadData(Condition2 condition2) {
        new Thread() {
            @Override
            public void run() {
                String list = FileUtils.getInst().readFromAsset("product_list.json");
                List<Product> model = JSON.parseObject(list, new TypeToken<List<Product>>() {
                }.getType());
                Message msg = mHandler.obtainMessage();
                msg.obj = model;
                msg.what = 200;
                mHandler.sendMessage(msg);

            }
        }.start();
    }

    @OnClick({ R.id.iv_back})
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
        }

    }

    private void showScreen(View v) {
        if (mSheetDialog == null) {
            mSheetDialog = new ScreenSheetDialog(mContext);
            mSheetDialog.setScreenListener(this);
        }
        mSheetDialog.show(mContainer);
    }

    @Override
    public void onClickConfirm(String min, String max, String min2, String max2, String min3, String max3, String min4, String max4, String min5, String max5, String min6, String max6, String style, String property) {

    }
}
