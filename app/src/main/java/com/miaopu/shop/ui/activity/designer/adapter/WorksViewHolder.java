package com.miaopu.shop.ui.activity.designer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
 * @description:
 */
public class WorksViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image)
    public ImageView mImageView;
    @BindView(R.id.tv_title)
    public TextView tv_title;
    @BindView(R.id.tv_info)
    public TextView tv_info;

    public WorksViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
