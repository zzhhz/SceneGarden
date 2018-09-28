package com.miaopu.shop.ui.activity.recommend.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.miaopu.shop.ui.model.Comment;
import com.miaopu.shop.utils.GlideUtils;
import com.miaopu.shop.utils.Utils;
import com.miaopu.shop.utils.l;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/17.
 *
 * @date: 2018/1/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 用户评论
 */
public class UserCommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private final Context mContext;
    private List<Comment> dataList;

    public UserCommentAdapter(Context ctx) {
        mContext = ctx;
        dataList = new ArrayList<>();
    }

    public void clear() {
        dataList.clear();
    }

    public void add(Comment item) {
        dataList.add(item);
    }

    public void addAll(List<Comment> list) {
        if (list != null) {
            dataList.addAll(list);
        }
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_comment, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment item = dataList.get(position);
        GlideUtils.loadCircleImageView(mContext, item.getHeadImg(), holder.iv_head, R.mipmap.ic_launcher);
        holder.tv_comment.setText(item.getContent());
        holder.tv_name.setText(item.getNickname());
        holder.tv_titme.setText(Utils.formatDateTime(item.getCreateTime()));
        holder.mAutoGridView.setAdapter(new SingleImageAdapter(mContext, -1, item.getListImages()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class SingleImageAdapter extends ArrayAdapter<String> {

        public SingleImageAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_single_image, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String item = getItem(position);
            l.d(item);
            GlideUtils.loadImageView(mContext, item, holder.image, R.mipmap.ic_launcher);
            return convertView;
        }

        class ViewHolder {
            ImageView image;

            public ViewHolder(View item) {
                this.image = item.findViewById(R.id.image);
                this.image.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }

    }

}