package com.miaopu.shop.ui.activity.works.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miaopu.shop.ui.model.Product;
import com.miaopu.shop.utils.GlideUtils;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/12.
 *
 * @date: 2018/1/12
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class WorksAdapter extends RecyclerView.Adapter<CheckProductViewHolder> {
    private final Context mContext;
    private List<Product> dataList;

    public WorksAdapter(Context ctx) {
        mContext = ctx;
        dataList = new ArrayList<>();
    }

    public void clear() {
        dataList.clear();
    }

    public void add(Product item) {
        dataList.add(item);
    }

    public void addAll(List<Product> list) {
        dataList.addAll(list);
    }

    @Override
    public CheckProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_check_product, parent, false);
        return new CheckProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CheckProductViewHolder holder, int position) {
        Product item = dataList.get(position);
        holder.tv_price.setText("￥ "+item.getPrice());
        holder.tv_title.setText(item.getTitle());
        holder.tv_info.setText(item.getRemark());
        GlideUtils.loadImageView(mContext, item.getDefaultImage(), holder.iv_head, R.mipmap.ic_launcher);
        if(item.getIsSelected() == 0){
            holder.iv_right.setBackgroundResource(R.drawable.not_pitch);
        }else if(item.getIsSelected() == 1){
            holder.iv_right.setBackgroundResource(R.drawable.pitch_on);
        }
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(mContext, ProductDetailActivity.class);
//            intent.putExtra(ProductDetailActivity.DATA_PRODUCT, item.getId());
//            mContext.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}