package com.miaopu.shop.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.miaopu.shop.ui.model.Diybg;
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
public class SingleImageAdapter extends RecyclerView.Adapter<SingleImageAdapter.HomeHorizontalItemHolder> {

    private List<Diybg> dataList;

    private Context mContext;

    private OnClickViewListener mClickViewListener;

    public void setClickViewListener(OnClickViewListener clickViewListener) {
        mClickViewListener = clickViewListener;
    }

    public SingleImageAdapter(Context context) {
        mContext = context;
        this.dataList = new ArrayList<>();
    }

    public void addAll(List<Diybg> data) {
        this.dataList.addAll(data);
    }

    @Override
    public HomeHorizontalItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHorizontalItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_recommend, parent, false));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(HomeHorizontalItemHolder holder, int position) {
//        l.d(dataList.get(position));
        GlideUtils.loadImageView(mContext, dataList.get(position).getValue(), holder.iv_cover, R.mipmap.ic_launcher);
        holder.itemView.setOnClickListener(view -> {
            if (mClickViewListener != null) {
                mClickViewListener.onClick(holder.itemView, position, dataList.get(position).getValue());
            }
        });
    }

    class HomeHorizontalItemHolder extends RecyclerView.ViewHolder {
        ImageView iv_cover;

        public HomeHorizontalItemHolder(View itemView) {
            super(itemView);
            iv_cover = itemView.findViewById(R.id.iv_cover);
        }
    }

    public interface OnClickViewListener {
        void onClick(View view, int position, String path);
    }
}
