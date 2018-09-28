package com.miaopu.shop.ui.activity.story.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miaopu.shop.ui.activity.personal.MyWebViewActivity;
import com.miaopu.shop.ui.base.ListBaseAdapter;
import com.miaopu.shop.ui.base.SuperViewHolder;
import com.miaopu.shop.ui.model.BrandStory;
import com.miaopu.shop.utils.GlideUtils;
import com.miaopu.shop.utils.Utils;
import com.zzh.sexual.secret.R;

/**
 * Created by liuwenzheng on 2018/2/21.
 */

public class BrandStoryAdapter extends ListBaseAdapter<BrandStory> {

    public BrandStoryAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_brand_story;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        LinearLayout ll_item = holder.getView(R.id.ll_item);
        ImageView iv_head = holder.getView(R.id.iv_head);
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_comment = holder.getView(R.id.tv_comment);
        BrandStory data = mDataList.get(position);
        GlideUtils.loadImageView(mContext, data.getTitleImg(), iv_head, R.mipmap.ic_test_bg);
        tv_title.setText(data.getTitle());
        tv_time.setText(Utils.formatDateTime(data.getCreateTime(), "yyyy-MM-dd"));
        tv_comment.setText(data.getViewTimes() +"");
        ll_item.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MyWebViewActivity.class);
            intent.putExtra("data", data.getContent());
            intent.putExtra("id", data.getId());
            intent.putExtra("num", data.getViewTimes() +"");
            mContext.startActivity(intent);
        });
    }
}
