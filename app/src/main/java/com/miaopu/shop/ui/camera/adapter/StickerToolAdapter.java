package com.miaopu.shop.ui.camera.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.miaopu.shop.ui.model.DIYProduct;
import com.miaopu.shop.utils.GlideUtils;
import com.zzh.sexual.secret.R;

import java.util.List;

/**
 * 
 * 贴纸适配器
 * @author tongqian.ni
 */
public class StickerToolAdapter extends BaseAdapter {

    List<DIYProduct> filterUris;
    Context     mContext;

    public StickerToolAdapter(Context context, List<DIYProduct> effects) {
        filterUris = effects;
        mContext = context;
    }

    @Override
    public int getCount() {
        return filterUris.size();
    }

    @Override
    public DIYProduct getItem(int position) {
        return filterUris.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EffectHolder holder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_bottom_tool, null);
            holder = new EffectHolder();
            holder.logo = (ImageView) convertView.findViewById(R.id.effect_image);
            holder.container = (ImageView) convertView.findViewById(R.id.effect_background);
            //holder.navImage.setOnClickListener(holder.clickListener);
            convertView.setTag(holder);
        } else {
            holder = (EffectHolder) convertView.getTag();
        }

        final DIYProduct effect = (DIYProduct) getItem(position);

        return showItem(convertView, holder, effect);
    }

    private View showItem(View convertView, EffectHolder holder, final DIYProduct sticker) {
        holder.container.setVisibility(View.GONE);
//        ImageLoaderUtils.displayDrawableImage(Uri.parse(sticker.getId()), holder.logo, null);
        GlideUtils.loadImageView(mContext, sticker.getDefaultImage(), holder.logo, R.mipmap.ic_launcher);
        return convertView;
    }

    class EffectHolder {
        ImageView logo;
        ImageView container;
    }

}
