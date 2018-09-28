package com.miaopu.shop.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.model.Poster;
import com.miaopu.shop.utils.GlideUtils;
import com.miaopu.shop.utils.l;
import com.zzh.sexual.secret.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/1/3.
 *
 * @date: 2018/1/3
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 首页轮播图：推荐设计师
 */
public class PosterFragment extends BaseStoreFragment {
    public static final String KEY_DATA_POSTER = "KEY_DATA_POSTER";
    private Poster mPoster;
    @BindView(R.id.iv)
    public ImageView iv;

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_poster;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);

        Bundle bundle = getArguments();
        if (bundle != null && !bundle.isEmpty()) {
            mPoster = bundle.getParcelable(KEY_DATA_POSTER);
        }
        fragment.setOnClickListener(v -> {
            /*if (mPoster != null && !TextUtils.isEmpty(mPoster.getId())) {
                Utils.toastTips(mContext, "" + mPoster.getId());
                Intent intent = new Intent(mContext, DesignerActivity.class);
                intent.putExtra(KEY_DATA_POSTER, mPoster);
                startActivity(intent);
            }*/

        });

        l.d(mPoster.getImgUrl());
        GlideUtils.loadScaleImageView(mContext, mPoster.getBannerPath(), iv, R.mipmap.ic_launcher);

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
