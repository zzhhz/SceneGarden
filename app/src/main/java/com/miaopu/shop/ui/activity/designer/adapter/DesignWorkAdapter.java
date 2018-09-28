package com.miaopu.shop.ui.activity.designer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miaopu.shop.ui.activity.example.CaseDetailActivity;
import com.miaopu.shop.ui.activity.works.WorksDetailActivity;
import com.miaopu.shop.ui.model.Works;
import com.miaopu.shop.utils.GlideUtils;
import com.zzh.sexual.secret.R;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by user on 2018/1/11.
 *
 * @date: 2018/1/11
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class DesignWorkAdapter extends RecyclerView.Adapter<WorksViewHolder> {
    private final Context mContext;
    private List<Works> dataList;
    private String[] imgRes = {"http://f.hiphotos.baidu.com/image/h%3D300/sign=87d62ddde0c4b7452b94b116fffd1e78/58ee3d6d55fbb2fbce95f3e6444a20a44723dc8c.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D300/sign=55e035e1da1373f0ea3f699f940e4b8b/0bd162d9f2d3572c957c31468113632763d0c38a.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/a2cc7cd98d1001e9d00da6e8b30e7bec54e79720.jpg",
            "http://d.hiphotos.baidu.com/image/pic/item/8644ebf81a4c510f62b950fd6b59252dd52aa5dc.jpg", "http://d.hiphotos.baidu.com/image/pic/item/eaf81a4c510fd9f97c9cf8f32e2dd42a2934a4a5.jpg"};
    private boolean mOnClickItemEnable;

    public DesignWorkAdapter(Context ctx) {
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
    public WorksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_designer_works, parent, false);
        return new WorksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WorksViewHolder holder, int position) {
        Works works = dataList.get(position);
        GlideUtils.loadScaleImageView(mContext, works.getDefaultImg(), holder.mImageView, R.mipmap.ic_launcher);
        holder.tv_title.setText(works.getTitle());
        holder.tv_info.setText(works.getSubTitle());

        holder.itemView.setOnClickListener(v -> {
            if (mOnClickItemEnable) {
                Intent intent = new Intent(mContext, WorksDetailActivity.class);
                intent.putExtra(WorksDetailActivity.KEY_ID, works.getId());
                mContext.startActivity(intent);
            } else {
                Intent intent = new Intent(mContext, CaseDetailActivity.class);
                intent.putExtra(CaseDetailActivity.KEY_ID, works);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnClickItemEnable(boolean onClickItemEnable) {
        mOnClickItemEnable = onClickItemEnable;
    }

    public boolean isOnClickItemEnable() {
        return mOnClickItemEnable;
    }
}