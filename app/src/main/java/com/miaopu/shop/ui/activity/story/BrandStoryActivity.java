package com.miaopu.shop.ui.activity.story;

import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.common.util.FileUtils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.reflect.TypeToken;
import com.miaopu.shop.ui.activity.story.adapter.BrandStoryAdapter;
import com.miaopu.shop.ui.base.BaseShopActivity;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.BrandStory;
import com.miaopu.shop.utils.Constants;
import com.zzh.sexual.secret.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandStoryActivity extends BaseSwipeActivity {

    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView;

    private BrandStoryAdapter adapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private int currentPage = 1;
    private boolean hasNextPage = false;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_brand_story;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar(R.string.brand_story, Color.parseColor("#020202"));
    }

    @Override
    protected void initData() {

        adapter = new BrandStoryAdapter(mContext);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setOnRefreshListener(() -> {
            currentPage = 1;
            if (currentPage == 1) {
                if (mContext instanceof BaseSwipeActivity) {
                    ((BaseSwipeActivity) mContext).showDialog();
                } else if (mContext instanceof BaseShopActivity) {
                    ((BaseShopActivity) mContext).showDialog();
                }
            }
            adapter.clear();
            mLRecyclerViewAdapter.notifyDataSetChanged();
            requestData();
        });
        recyclerView.setOnLoadMoreListener(() -> {
            currentPage++;
            if (hasNextPage) {
                currentPage++;
                requestData();
            } else {
                recyclerView.refreshComplete(Constants.PAGE_SIZE);
            }
        });
        recyclerView.setAdapter(mLRecyclerViewAdapter);
        recyclerView.forceToRefresh();
    }

    private void requestData() {
        new Thread() {
            @Override
            public void run() {
                String s = FileUtils.getInst().readFromAsset("BrandStoryList.json");
                List<BrandStory> model = JSON.parseObject(s, new TypeToken<List<BrandStory>>() {
                }.getType());
                Message message = mHandler.obtainMessage();
                message.what = 100;
                message.obj = model;
                mHandler.sendMessage(message);
            }
        }.start();
        /*Map<String, String> params = new HashMap<>();
        params.put("pageNo", currentPage +"");
        params.put("pageSize", Constants.PAGE_SIZE +"");*/
        /*RetrofitUtils.Api().getBrandStoryList(currentPage, Constants.PAGE_SIZE, RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseHandleConsumer<WrapperModel<BrandStory>>() {
                    @Override
                    public void onSuccess(WrapperModel<BrandStory> model) {
                        l.d(model.toString());
                        if (model != null && model.getList() != null && !model.getList().isEmpty()) {
                            adapter.addAll(model.getList());
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(View.GONE);
                        }
                        hasNextPage = model.isHasNextPage();
                        recyclerView.refreshComplete(Constants.PAGE_SIZE);
                        if (mContext instanceof BaseSwipeActivity) {
                            ((BaseSwipeActivity) mContext).dismissDialog();
                        } else if (mContext instanceof BaseShopActivity) {
                            ((BaseShopActivity) mContext).dismissDialog();
                        }
                    }

                    @Override
                    public void onFailed(String code, String message) {
                        mLRecyclerViewAdapter.notifyDataSetChanged();
                        Utils.toastTips(mContext, message);
                        recyclerView.refreshComplete(Constants.PAGE_SIZE);
                        if (mContext instanceof BaseSwipeActivity) {
                            ((BaseSwipeActivity) mContext).dismissDialog();
                        } else if (mContext instanceof BaseShopActivity) {
                            ((BaseShopActivity) mContext).dismissDialog();
                        }
                    }
                });*/
    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {
        List<BrandStory> model = (List<BrandStory>) msg.obj;
        adapter.addAll(model);
        mLRecyclerViewAdapter.notifyDataSetChanged();
        recyclerView.refreshComplete(Constants.PAGE_SIZE);
        if (mContext instanceof BaseSwipeActivity) {
            ((BaseSwipeActivity) mContext).dismissDialog();
        } else if (mContext instanceof BaseShopActivity) {
            ((BaseShopActivity) mContext).dismissDialog();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
