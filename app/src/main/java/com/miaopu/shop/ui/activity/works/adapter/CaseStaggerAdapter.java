package com.miaopu.shop.ui.activity.works.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.sexual.secret.R;
import com.miaopu.shop.ui.activity.example.CaseDetailActivity;
import com.miaopu.shop.ui.model.Works;
import com.miaopu.shop.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/16.
 *
 * @date: 2018/1/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 作品列表，
 */
public class CaseStaggerAdapter extends RecyclerView.Adapter<WorksStaggerHolder> {
    private final Context mContext;
    private List<Works> dataList;
    /**
     * 是否是我的案例， false 是案例， true是我的作品
     **/
    private boolean mIsWorks;

    public CaseStaggerAdapter(Context ctx) {
        mContext = ctx;
        dataList = new ArrayList<>();
    }

    public void clear() {
        dataList.clear();
    }

    public void add(Works item) {
        dataList.add(item);
    }

    public void addAll(List<Works> list) {
        dataList.addAll(list);
    }

    @Override
    public WorksStaggerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_case_stagger, parent, false);
        return new WorksStaggerHolder(v);
    }

    @Override
    public void onBindViewHolder(WorksStaggerHolder holder, int position) {
        Works item = dataList.get(position);
        GlideUtils.loadImageView(mContext, item.getDefaultImg(), holder.mImageView, R.mipmap.ic_launcher);
        if (mIsWorks) {
            holder.mTitle.setText(item.getTitle());
        } else {
            holder.mTitle.setText(item.getName());
        }
        holder.mInfo.setText(item.getSubTitle());
        holder.tv_count.setText(String.valueOf(item.getCommentCount()));

        holder.itemView.setOnClickListener(v -> {
            if (mIsWorks) {

            } else {
                Intent intent = new Intent(mContext, CaseDetailActivity.class);
                intent.putExtra(CaseDetailActivity.KEY_ID, item);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setIsWorks(boolean isWorks) {
        mIsWorks = isWorks;
    }
}