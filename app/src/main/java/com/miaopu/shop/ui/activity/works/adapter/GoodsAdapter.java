package com.miaopu.shop.ui.activity.works.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.miaopu.shop.ui.base.ListBaseAdapter;
import com.miaopu.shop.ui.base.SuperViewHolder;
import com.miaopu.shop.ui.model.ProductCheck;
import com.zzh.sexual.secret.R;

/**
 * Created by liuwenzheng on 2018/2/27.
 */

public class GoodsAdapter extends ListBaseAdapter<ProductCheck> {
    public GoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_product_check;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ProductCheck data = mDataList.get(position);
        TextView title = holder.getView(R.id.tv_title);
        TextView num = holder.getView(R.id.tv_num);
        Button btnDelete = holder.getView(R.id.btnDelete);

        title.setText(data.getTitle());
        num.setText("数量：" + data.getNum());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(position);
                }
            }
        });
    }
    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

}
