package com.miaopu.shop.ui.camera;

import android.graphics.Color;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.miaopu.shop.ui.base.BaseShopActivity;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.GlideUtils;
import com.zzh.sexual.secret.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZH on 2018/3/9.
 *
 * @Date: 2018/3/9
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class PreviewActivity extends BaseShopActivity {

    @BindView(R.id.iv_diy)
    ImageView iv_diy;

    @BindView(R.id.tv_one_key_order)
    TextView tv_one_key_order;
    @BindView(R.id.tv_save)
    TextView tv_save;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_preview;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar("DIY预览");
        mTitle.setTextColor(Color.parseColor("#333333"));
        String path = getIntent().getStringExtra("path");
        GlideUtils.loadScaleImageView(mContext, path, iv_diy);

        tv_save.setOnClickListener(this);
        tv_one_key_order.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.tv_save:
                showMessage("保存本地成功");
                break;
            case R.id.tv_one_key_order:
                if (!Constants.DIY_ORDER.isEmpty()) {
                    finish();
                }
                break;

        }
    }
}
