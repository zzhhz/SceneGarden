package com.miaopu.shop.ui.activity.works.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzh.sexual.secret.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/1/16.
 *
 * @date: 2018/1/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class WorksStaggerHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    public TextView mTitle;
    @BindView(R.id.tv_info)
    public TextView mInfo;
    @BindView(R.id.iv_head)
    public ImageView mImageView;
    @BindView(R.id.tv_count)
    public TextView tv_count;

    public WorksStaggerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}