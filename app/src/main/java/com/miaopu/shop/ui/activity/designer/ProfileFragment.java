package com.miaopu.shop.ui.activity.designer;

import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.model.DesWithConsList;
import com.zzh.sexual.secret.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/1/11.
 *
 * @date: 2018/1/11
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 设计师简介详情
 */
public class ProfileFragment extends BaseStoreFragment {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_good)
    TextView tv_good;
    @BindView(R.id.tv_info)
    TextView tv_info;

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);
    }

    @Override
    protected void initData() {
        bindData(getArguments().getParcelable(DesignerActivity.KEY_DESIGNER));

    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    public void bindData(DesWithConsList desWithConsList) {

        tv_name.setText(desWithConsList.getNickname());
        tv_address.setText(desWithConsList.getProvince() + " " + desWithConsList.getCity() + " " + desWithConsList.getArea() + " " + desWithConsList.getAddress());
        tv_good.setText(desWithConsList.getMobile());
        tv_info.setText(desWithConsList.getRemark());


    }
}
