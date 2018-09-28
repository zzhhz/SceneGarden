package com.miaopu.shop.ui.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
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
 * @description: 商品holder
 */
public class ProductViewHolder extends LRecyclerViewAdapter.ViewHolder {

    @BindView(R.id.iv_head)
    public ImageView iv_head;
    @BindView(R.id.tv_title)
    public TextView tv_title;
    @BindView(R.id.tv_info)
    public TextView tv_info;
    @BindView(R.id.tv_price)
    public TextView tv_price;


    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
