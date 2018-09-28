package com.miaopu.shop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.activity.personal.PersonalActivity;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.model.User;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.GlideUtils;
import com.zzh.sexual.secret.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 2018/1/2.
 *
 * @date: 2018/1/2
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 个人中心
 */
public class MineFragment extends BaseStoreFragment {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_account)
    TextView tvAccount;

    @BindView(R.id.layout_my_message)
    RelativeLayout layoutMyMessage;
    @BindView(R.id.toolbar_title)
    public TextView mTitle;


    private String checkStatus;
    private String type;

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);
        mTitle.setText("我的");
    }

    @Override
    protected void initData() {
        GlideUtils.loadCircleImageView(mContext, "", ivHead, R.mipmap.ic_test_bg);
        getCurrentInfo();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getCurrentInfo();
        }
    }

    private void getCurrentInfo() {
        if (ShopApplication.getCurrentUser() == null) {
            tvName.setText("登录/注册");
            tvName.setTextColor(getResources().getColor(R.color.homeBaseColor));
            tvAccount.setText("账号：");
            GlideUtils.loadCircleImageView(mContext, "", ivHead, R.mipmap.ic_test_bg);
            return;
        }
        User bean = ShopApplication.getCurrentUser();
        GlideUtils.loadCircleImageView(mContext, bean.getHeadImg(), ivHead, R.mipmap.ic_test_bg);
        tvName.setTextColor(getResources().getColor(R.color.black));
        tvName.setText((bean.getNickname() == null ? bean.getUsername() : bean.getNickname()) + " LV" + (bean.getLevel() == null ? "" : bean.getLevel()));
        tvAccount.setText("账号：" + (bean.getUsername() == null ? "" : bean.getUsername()));
        /*RetrofitUtils.Api().getCurrentInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<User>() {
            @Override
            public void onSuccess(User bean) {
                l.d(bean.toString());

                SpUtils.saveLogin(mContext, bean);
                GlideUtils.loadCircleImageView(mContext, bean.getHeadImg(), ivHead, R.mipmap.ic_test_bg);
                tvName.setTextColor(getResources().getColor(R.color.black));
                tvName.setText((bean.getNickname() == null ? bean.getUsername() : bean.getNickname()) + " LV" + (bean.getLevel() == null ? "" : bean.getLevel()));
                tvAccount.setText("账号：" + (bean.getUsername() == null ? "" : bean.getUsername()));
            }

            @Override
            public void onFailed(String code) {
                tvName.setText("登录/注册");
                tvName.setTextColor(getResources().getColor(R.color.homeBaseColor));
                tvAccount.setText("账号：");
                GlideUtils.loadCircleImageView(mContext, "", ivHead, R.mipmap.ic_test_bg);
            }
        });*/
    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == RESULT_OK) {
            GlideUtils.loadCircleImageView(mContext, ShopApplication.getCurrentUser().getHeadImg(), ivHead, R.mipmap.ic_test_bg);
        }
        if (requestCode == 110 && resultCode == RESULT_OK) {
            getCurrentInfo();
        }
    }

    @OnClick({R.id.layout_my_message})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.layout_my_message:
                intent = new Intent(mContext, PersonalActivity.class);
                startActivityForResult(intent, 102);
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventLogin(String action) {
        if (TextUtils.equals(Constants.EVENT_REFRESH_LOGIN, action)) {
            getCurrentInfo();
        }
    }
}
