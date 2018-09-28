package com.miaopu.shop.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.common.util.FileUtils;
import com.google.gson.reflect.TypeToken;
import com.miaopu.shop.ui.activity.recommend.ProductActivity;
import com.miaopu.shop.ui.activity.story.BrandStoryActivity;
import com.miaopu.shop.ui.activity.works.WorksActivity;
import com.miaopu.shop.ui.adapter.HomeHorizontalAdsAdapter;
import com.miaopu.shop.ui.adapter.HomeHorizontalItemAdapter;
import com.miaopu.shop.ui.base.BaseShopActivity;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.model.HomeItemPoster;
import com.miaopu.shop.ui.model.Poster;
import com.miaopu.shop.ui.model.Works;
import com.zzh.horizontal.recyclerview.CardScaleHelper;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
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
 * @description: 首页
 */
public class HomeFragment extends BaseStoreFragment implements Toolbar.OnMenuItemClickListener {


    @BindView(R.id.viewPager)
    public ViewPager mViewPager;
    @BindView(R.id.rv_h_item)
    public RecyclerView rv_h_item;
    @BindView(R.id.rv_h_product)
    public RecyclerView rv_h_product;
    @BindView(R.id.rv_h_works)
    public RecyclerView rv_h_works;
    @BindView(R.id.toolbar_title)
    public TextView mTitle;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;


    private HomeHorizontalItemAdapter mHorizontalItemAdapter;
    private HomeHorizontalAdsAdapter mHorizontalAdsAdapter;
    private HomeHorizontalAdsAdapter mHorizontalProductAdapter;
    private HomeHorizontalAdsAdapter mHorizontalWorksAdapter;
    private ViewPagerAdapter mBannerAdapter;

    private CardScaleHelper mCardScaleHelper = null;
    private BaseShopActivity mShopActivity;

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);
        mShopActivity = (BaseShopActivity) getActivity();
        mTitle.setText("首页");
        mTitle.setTextColor(Color.parseColor("#333333"));
        setHasOptionsMenu(true);
        AppCompatActivity mAppCompatActivity = (AppCompatActivity) mContext;
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.setOnMenuItemClickListener(this);
    }

    @Override
    protected void initData() {
        mBannerAdapter = new ViewPagerAdapter(getChildFragmentManager(), null);
        mViewPager.setAdapter(mBannerAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mHorizontalItemAdapter = new HomeHorizontalItemAdapter(mContext);
        rv_h_item.setLayoutManager(manager);
        rv_h_item.setAdapter(mHorizontalItemAdapter);

        mHorizontalProductAdapter = new HomeHorizontalAdsAdapter(mContext, 2);
        rv_h_product.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rv_h_product.setAdapter(mHorizontalProductAdapter);

        mHorizontalWorksAdapter = new HomeHorizontalAdsAdapter(mContext, 3);
        rv_h_works.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rv_h_works.setAdapter(mHorizontalWorksAdapter);
        reloadData();
    }

    private void reloadData() {
        mShopActivity.showDialog();

        new Thread() {
            @Override
            public void run() {
                String homeBanner = FileUtils.getInst().readFromAsset("queryHomeBanner.json");
                String queryProductCategory = FileUtils.getInst().readFromAsset("queryProductCategory.json");
                String queryRecommendDesign = FileUtils.getInst().readFromAsset("queryRecommendDesign.json");
                Message message = mHandler.obtainMessage();

                message.what = 100;
                List<Poster> listPoster = JSON.parseObject(homeBanner, new TypeToken<List<Poster>>() {
                }.getType());
                List<HomeItemPoster> model = JSON.parseObject(queryProductCategory, new TypeToken<List<HomeItemPoster>>() {
                }.getType());

                List<Works> works = JSON.parseObject(queryRecommendDesign, new TypeToken<List<Works>>() {
                }.getType());
                Object[] objects = {listPoster, model, works};
                message.obj = objects;

                mHandler.sendMessage(message);
            }
        }.start();
    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

        switch (msg.what) {
            case 100:
                Object[] str = (Object[]) msg.obj;
                List<Poster> listPoster = (List<Poster>) str[0];
                mBannerAdapter.addAll(listPoster);
                mBannerAdapter.notifyDataSetChanged();
                List<HomeItemPoster> model = (List<HomeItemPoster>) str[1];
                rv_h_item.setVisibility(View.VISIBLE);
                mHorizontalItemAdapter.addAll(model);
                mHorizontalItemAdapter.notifyDataSetChanged();
                List<Works> works = (List<Works>) str[2];
                mHorizontalWorksAdapter.addAll(works);
                mHorizontalWorksAdapter.notifyDataSetChanged();
                mShopActivity.dismissDialog();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<BaseStoreFragment> dataList;

        public ViewPagerAdapter(FragmentManager fm, List<BaseStoreFragment> dataList) {
            super(fm);
            if (dataList == null) {
                this.dataList = new ArrayList<>();
            } else {
                this.dataList = dataList;
            }
        }

        public void addAll(List<Poster> list) {
            dataList.clear();
            if (list != null && !list.isEmpty()) {
                for (Poster poster : list) {
                    PosterFragment fragment = new PosterFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(PosterFragment.KEY_DATA_POSTER, poster);
                    fragment.setArguments(bundle);
                    dataList.add(fragment);
                }
            }
        }

        public void clear() {
            dataList.clear();
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

    @OnClick({R.id.tv_more_product, R.id.tv_more_works, R.id.rv_h_item_ads})
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.tv_more_works:
                Intent works = new Intent(mContext, WorksActivity.class);
                startActivity(works);
                break;
            case R.id.tv_more_product:
                Intent intent = new Intent(mContext, ProductActivity.class);
                startActivity(intent);
                break;
            case R.id.rv_h_item_ads:
                Intent intent2 = new Intent(mContext, BrandStoryActivity.class);
                mContext.startActivity(intent2);
                break;
        }

    }
}
