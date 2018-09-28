package com.miaopu.shop.ui.activity.works.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.miaopu.shop.ui.model.HomeItemPoster;
import com.miaopu.shop.utils.GlideUtils;
import com.miaopu.shop.utils.Utils;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/30.
 *
 * @date: 2018/1/30
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 单个图片
 */
public class SingleImage2Adapter extends RecyclerView.Adapter<SingleImage2Adapter.ViewHolder> {

    private List<HomeItemPoster> dataList;
    private Context mContext;
    private int mWidth;

    public SingleImage2Adapter(Context context, List<HomeItemPoster> list) {
        mContext = context;
        if (list != null) {
            this.dataList = list;
        } else {
            this.dataList = new ArrayList<>();
        }
        mWidth = Utils.getDisplayWidth(mContext);
    }

    public void addAll(List<HomeItemPoster> data) {
        dataList.addAll(data);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_single_image2, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.mImageView.getLayoutParams();
        layoutParams.width = mWidth;
        layoutParams.height = mWidth;
        holder.mImageView.setLayoutParams(layoutParams);
        GlideUtils.loadImageView(mContext, dataList.get(position).getImgUrl(), holder.mImageView, R.mipmap.ic_launcher);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
