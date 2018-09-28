package com.miaopu.shop.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.miaopu.shop.ui.activity.recommend.ProductDetailActivity;
import com.miaopu.shop.ui.activity.story.BrandStoryActivity;
import com.miaopu.shop.ui.activity.works.WorksDetailActivity;
import com.miaopu.shop.ui.model.HomeItemPoster;
import com.miaopu.shop.utils.GlideUtils;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/10.
 *
 * @date: 2018/1/10
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 首页横向滑动的item广告
 */
public class HomeHorizontalAdsAdapter extends RecyclerView.Adapter<HomeHorizontalAdsAdapter.HomeHorizontalItemHolder> {

    private List<HomeItemPoster> dataList;
    private int type;

    private Context mContext;

    public HomeHorizontalAdsAdapter(Context context) {
        mContext = context;
        this.dataList = new ArrayList<>();
    }

    /**
     * @param context
     * @param type    类型 1. 首页广告 1； 2. 推荐商品 2；3. 推荐作品 3; 4. 只有图片
     */
    public HomeHorizontalAdsAdapter(Context context, int type) {
        mContext = context;
        this.dataList = new ArrayList<>();
        this.type = type;
    }

    public void addAll(List<? extends HomeItemPoster> data) {
        this.dataList.addAll(data);
    }

    @Override
    public HomeHorizontalItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHorizontalItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_recommend, parent, false));
    }

    @Override
    public int getItemCount() {
        if (type == 2 || type == 3) {
            return dataList.size();
        } else {
            return 3;
        }
    }

    @Override
    public void onBindViewHolder(HomeHorizontalItemHolder holder, int position) {

        if (type == 1) {
            if (position == 0) {
                //holder.iv_cover.setImageResource(R.mipmap.ic_ads_history);
                GlideUtils.loadHomeScaleImageView2(mContext, R.mipmap.ic_ads_history, holder.iv_cover);
            } else if (position == 1) {
                //holder.iv_cover.setImageResource(R.mipmap.ic_ads_teem);
                GlideUtils.loadHomeScaleImageView2(mContext, R.mipmap.ic_ads_teem, holder.iv_cover);
            } else {
                //holder.iv_cover.setImageResource(R.mipmap.ic_ads_design);
                //Glide.with(mContext).load(R.mipmap.ic_ads_history).into(holder.iv_cover);
                GlideUtils.loadHomeScaleImageView2(mContext, R.mipmap.ic_ads_design, holder.iv_cover);
            }
            holder.iv_cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0) {
                        Intent intent = new Intent(mContext, BrandStoryActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });
        } else if (type == 2) {
            HomeItemPoster poster = dataList.get(position);
            GlideUtils.loadHomeScaleImageView(mContext, poster.getImageUrl(), holder.iv_cover, R.mipmap.ic_launcher);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                intent.putExtra(ProductDetailActivity.DATA_PRODUCT, poster.getId());
                mContext.startActivity(intent);
            });
        } else if (type == 3) {
            HomeItemPoster poster = dataList.get(position);
            GlideUtils.loadHomeScaleImageView(mContext, poster.getImageUrl(), holder.iv_cover, R.mipmap.ic_launcher);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, WorksDetailActivity.class);
                intent.putExtra(WorksDetailActivity.KEY_ID, poster.getId());
                mContext.startActivity(intent);
            });
        } else if (type == 4) {
            GlideUtils.loadHomeScaleImageView(mContext, dataList.get(position).getImageUrl(), holder.iv_cover, R.mipmap.ic_launcher);
        }

    }

    class HomeHorizontalItemHolder extends RecyclerView.ViewHolder {
        ImageView iv_cover;

        public HomeHorizontalItemHolder(View itemView) {
            super(itemView);
            iv_cover = itemView.findViewById(R.id.iv_cover);
        }
    }
}
