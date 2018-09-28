package com.miaopu.shop.ui.activity.recommend.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miaopu.shop.ui.view.AutoGridView;
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
 * @description: 评价
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.iv_user_head)
    public ImageView iv_head;
    @BindView(R.id.tv_user_name)
    public TextView tv_name;
    @BindView(R.id.tv_comment)
    public TextView tv_comment;
    @BindView(R.id.tv_time)
    public TextView tv_titme;
    @BindView(R.id.ll_image_content)
    public LinearLayout ll_image_content;
    @BindView(R.id.gv_image)
    public AutoGridView mAutoGridView;


    public CommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
