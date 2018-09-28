package com.miaopu.shop.ui.activity.works.adapter;

import android.content.Context;
import android.widget.TextView;

import com.miaopu.shop.ui.base.ListBaseAdapter;
import com.miaopu.shop.ui.base.SuperViewHolder;
import com.zzh.sexual.secret.R;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by liuwenzheng on 2018/2/25.
 */

public class GuigeAdapter extends ListBaseAdapter<Map<String, String>> {
    public GuigeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_guige;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TextView tv_left = holder.getView(R.id.tv_left);
        TextView tv_right = holder.getView(R.id.tv_right);

        Map<String, String> data = mDataList.get(position);
        Iterator<String> iter = data.keySet().iterator();
        while(iter.hasNext()){
            String key=iter.next();
            String value = data.get(key);
            tv_left.setText(key);
            tv_right.setText(value);
        }
    }
}
