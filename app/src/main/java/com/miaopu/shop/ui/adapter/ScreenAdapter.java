package com.miaopu.shop.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/16.
 *
 * @date: 2018/1/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 商品页面的筛选
 */
public class ScreenAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> dataList;
    /**
     * 不再使用
     */
    private List<String> mSelectedList;
    private int mSelectLabel = -1;

    private boolean isShow = false;

    public ScreenAdapter(Context context) {
        mContext = context;
        dataList = new ArrayList<>();
        mSelectedList = new ArrayList<>();
    }

    public void addAll(List<String> list) {
        if (list != null && !list.isEmpty()) {
            dataList.addAll(list);
        }
    }

    public void setShowAll(boolean show) {
        isShow = show;
    }

    public boolean isShow() {
        return isShow;
    }

    @Override
    public int getCount() {
        if (isShow) {
            return dataList.size();
        } else {
            return (dataList.size() > 8) ? 8 : dataList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_screen, null);
            holder = new ViewHolder();
            holder.mButton = convertView.findViewById(R.id.rb_screen);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String text = dataList.get(position);
        /*if (mSelectedList.contains(text)) {
            holder.mButton.setChecked(true);
        } else {
            holder.mButton.setChecked(false);
        }*/
        if (position == mSelectLabel) {
            holder.mButton.setBackgroundResource(R.drawable.shape_screen_selected);
        } else {
            holder.mButton.setBackgroundResource(R.drawable.shape_screen_un_select);
        }
        holder.mButton.setText(text);
        return convertView;
    }

    public void resetSelect() {
        mSelectLabel = -1;
    }

    class ViewHolder {
        TextView mButton;
    }

    /**
     * 不在使用
     *
     * @return
     */
    public List<String> getSelectedList() {
        return mSelectedList;
    }

    public void setSelectLabel(int selectLabel) {
        mSelectLabel = selectLabel;
    }

    public String getSelectLabel() {
        if (mSelectLabel > -1 && mSelectLabel < dataList.size()) {
            return dataList.get(mSelectLabel);
        } else {
            return "";
        }
    }
}
