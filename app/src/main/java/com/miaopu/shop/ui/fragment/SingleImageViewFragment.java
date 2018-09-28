package com.miaopu.shop.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.utils.GlideUtils;
import com.zzh.sexual.secret.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/1/17.
 *
 * @date: 2018/1/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class SingleImageViewFragment extends BaseStoreFragment {
    public static final String KEY_DATA_POSTER = "KEY_DATA_POSTER";
    @BindView(R.id.iv)
    public ImageView iv;

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_single_imageview;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);

        Bundle bundle = getArguments();
        String url = bundle.getString(KEY_DATA_POSTER);
        GlideUtils.loadScaleImageView(mContext, url, iv, R.mipmap.ic_launcher);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }
}
