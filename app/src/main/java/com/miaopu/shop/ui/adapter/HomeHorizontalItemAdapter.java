package com.miaopu.shop.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miaopu.shop.ui.activity.recommend.ProductActivity;
import com.miaopu.shop.ui.model.HomeItemPoster;
import com.miaopu.shop.utils.GlideUtils;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/1/10.
 *
 * @date: 2018/1/10
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 首页横向滑动的item适配器
 */
public class HomeHorizontalItemAdapter extends RecyclerView.Adapter<HomeHorizontalItemAdapter.HomeHorizontalItemHolder> {

    private List<HomeItemPoster> dataList;

    private Context mContext;

    public HomeHorizontalItemAdapter(Context context) {
        mContext = context;
        this.dataList = new ArrayList<>();
    }

    public void addAll(List<HomeItemPoster> data) {
        dataList.addAll(data);
    }

    @Override
    public HomeHorizontalItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHorizontalItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_horizontal_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(HomeHorizontalItemHolder holder, int position) {
        GlideUtils.loadCircleImageView(mContext, dataList.get(position).getMobileImg(), holder.header, R.mipmap.ic_launcher);
        holder.name.setText(dataList.get(position).getName());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ProductActivity.class);
            intent.putExtra("title", dataList.get(position).getName());
            mContext.startActivity(intent);
        });
    }

    class HomeHorizontalItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.iv_head)
        ImageView header;

        public HomeHorizontalItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
