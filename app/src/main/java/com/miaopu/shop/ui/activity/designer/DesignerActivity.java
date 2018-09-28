package com.miaopu.shop.ui.activity.designer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.permissions.RxPermissions;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.adapter.ViewPagerAdapter;
import com.miaopu.shop.ui.base.BaseShopActivity;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.DesWithConsList;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.GlideUtils;
import com.miaopu.shop.utils.Utils;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.zzh.sexual.secret.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 2018/1/10.
 *
 * @date: 2018/1/10
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 设计师详情页面
 */
public class DesignerActivity extends BaseSwipeActivity implements TabLayout.OnTabSelectedListener, ViewTreeObserver.OnGlobalLayoutListener {

    public static final String KEY_DESIGNER = "KEY_DESIGNER";

    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    public ViewPager mViewPager;

    private ViewPagerAdapter mViewPagerAdapter;

    private WorksFragment mWorksFragment;
    private ProfileFragment mProfileFragment;

    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.iv_user_head)
    ImageView iv_user_head;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_good)
    TextView tv_good;
    @BindView(R.id.tv_info)
    TextView tv_info;

    @BindView(R.id.iv_bg)
    ImageView iv_bg;
    @BindView(R.id.tv_collect)
    TextView tv_collect;

    RxPermissions mRxPermissions;

    private DesWithConsList mDesWithConsList;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_designer;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mRxPermissions = new RxPermissions(this);
        mDesWithConsList = getIntent().getParcelableExtra(KEY_DESIGNER);
        mWorksFragment = new WorksFragment();
        mProfileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DESIGNER, mDesWithConsList);
        mProfileFragment.setArguments(bundle);
        List<BaseStoreFragment> list = new ArrayList<>();
        list.add(mWorksFragment);
        list.add(mProfileFragment);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        TabLayout.Tab tabWorks = mTabLayout.getTabAt(0);
        tabWorks.setText("他的作品");
        TabLayout.Tab tabProfile = mTabLayout.getTabAt(1);
        tabProfile.setText("他的资料");
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);

        if (mDesWithConsList != null) {
            if ("0102".equals(mDesWithConsList.getType())) {
                tabWorks.setText("他的作品");
                mWorksFragment.queryMyDesignByPage(mDesWithConsList.getId());
            } else {
                tabWorks.setText("他的案例");
                mWorksFragment.queryByPageWithMap(mDesWithConsList.getId());
            }
            bindDesignerInfo();

            if (mDesWithConsList.isFollow()) {
                tv_collect.setText("已关注");
            } else {
                tv_collect.setText("关注");
            }
        }
    }

    private void bindDesignerInfo() {
        tv_user_name.setText(mDesWithConsList.getNickname());
        tv_city.setText(mDesWithConsList.getCity());
        tv_info.setText(mDesWithConsList.getRemark());
        tv_good.setText(mDesWithConsList.getMobile());
        GlideUtils.loadCircleImageView(this, mDesWithConsList.getHeadImg(), iv_user_head, R.mipmap.ic_launcher);
        GlideUtils.loadImageView(this, mDesWithConsList.getHeadImg(), iv_bg, R.mipmap.ic_launcher);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initSetListener() {
        final TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mViewPager.addOnPageChangeListener(listener);
        mViewPager.setCurrentItem(0);
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @OnClick({R.id.ic_back, R.id.tv_collect, R.id.tv_contact})
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_collect:
                if (TextUtils.isEmpty(ShopApplication.getToken())) {
                    showMessage("请先登录应用程序");
                } else {
                    if (mDesWithConsList.isFollow()) {
                        cancelcollection();
                    } else {
                        collection();
                    }
                }
                break;
            case R.id.tv_contact:
                if (mRxPermissions.isGranted(Manifest.permission.CALL_PHONE)) {
                    callPhone();
                } else {
                    mRxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean flag) throws Exception {
                            if (flag) {
                                callPhone();
                            } else {
                                showMessage("请授予程序拨打电话权限");
                            }

                        }
                    });
                }
                break;
        }
    }

    @SuppressLint("MissingPermission")
    private void callPhone() {
        if (!TextUtils.isEmpty(mDesWithConsList.getMobile())) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mDesWithConsList.getMobile()));
            startActivity(intent);
        }
    }

    private boolean flag = true;

    @Override
    public void onGlobalLayout() {
        if (flag) {
            Utils.setMargin(mTabLayout, 20, 20);
        }
        flag = false;
    }

    private void collection() {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", mDesWithConsList.getId());
        params.put("type", mDesWithConsList.getType());
        RetrofitUtils.Api().addCollectionDes(RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<String>() {
            @Override
            public void onSuccess(String model) {
                if (mContext instanceof BaseSwipeActivity) {
                    ((BaseSwipeActivity) mContext).dismissDialog();
                } else if (mContext instanceof BaseShopActivity) {
                    ((BaseShopActivity) mContext).dismissDialog();
                }
                tv_collect.setText("已关注");
                EventBus.getDefault().post(Constants.EVENT_REFRESH_FOLLOW);
                Toast mToast = Toast.makeText(mContext, "关注成功", Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
                mDesWithConsList.setFollow(true);
            }

            @Override
            public void onFailed(String code, String message) {
                if (mContext instanceof BaseSwipeActivity) {
                    ((BaseSwipeActivity) mContext).dismissDialog();
                } else if (mContext instanceof BaseShopActivity) {
                    ((BaseShopActivity) mContext).dismissDialog();
                }
            }
        });
    }

    private void cancelcollection() {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", mDesWithConsList.getId());
        params.put("type", mDesWithConsList.getType());
        RetrofitUtils.Api().cancelCollectionDes(RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<String>() {
            @Override
            public void onSuccess(String model) {
                if (mContext instanceof BaseSwipeActivity) {
                    ((BaseSwipeActivity) mContext).dismissDialog();
                } else if (mContext instanceof BaseShopActivity) {
                    ((BaseShopActivity) mContext).dismissDialog();
                }
                tv_collect.setText("关注");
                EventBus.getDefault().post(Constants.EVENT_REFRESH_FOLLOW);
                Toast mToast = Toast.makeText(mContext, "取消关注成功", Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
                mDesWithConsList.setFollow(false);
            }

            @Override
            public void onFailed(String code, String message) {
                if (mContext instanceof BaseSwipeActivity) {
                    ((BaseSwipeActivity) mContext).dismissDialog();
                } else if (mContext instanceof BaseShopActivity) {
                    ((BaseShopActivity) mContext).dismissDialog();
                }
            }
        });
    }
}
