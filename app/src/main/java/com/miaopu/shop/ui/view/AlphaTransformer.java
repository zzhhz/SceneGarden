package com.miaopu.shop.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.miaopu.shop.utils.l;

/**
 * Created by user on 2018/1/3.
 *
 * @date: 2018/1/3
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class AlphaTransformer implements ViewPager.PageTransformer {


    private Context context;
    private float elevation;
    private static final float MIN_SCALE = 0.70f;
    private static final float MIN_ALPHA = 0.5f;
    final float SCALE_MAX = 0.8f;
    final float ALPHA_MAX = 0.5f;
    public AlphaTransformer(Context context) {
        this.context = context;
        elevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10, context.getResources().getDisplayMetrics());
    }

    @Override
    public void transformPage(View page, float position) {
        float scale = (position < 0)
                ? ((1 - SCALE_MAX) * position + 1)
                : ((SCALE_MAX - 1) * position + 1);
        float alpha = (position < 0)
                ? ((1 - ALPHA_MAX) * position + 1)
                : ((ALPHA_MAX - 1) * position + 1);
        //为了滑动过程中，page间距不变，这里做了处理
        if(position < 0) {
            ViewCompat.setPivotX(page, page.getWidth());
            ViewCompat.setPivotY(page, page.getHeight() / 2);
        } else {
            ViewCompat.setPivotX(page, 0);
            ViewCompat.setPivotY(page, page.getHeight() / 2);
        }
        ViewCompat.setScaleX(page, scale);
        ViewCompat.setScaleY(page, scale);
        ViewCompat.setAlpha(page, Math.abs(alpha));
        /*if (position < -1 || position > 1) {
        } else {
            if (position < 0) {
                float scaleX = 1 + 0.1f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
                ((CardView) page).setCardElevation((1 + position) * elevation);
            } else {
                float scaleX = 1 - 0.1f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
                ((CardView) page).setCardElevation((1 - position) * elevation);
            }
        }*/
    }


    /*private static final float MIN_SCALE = 0.70f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1 || position > 1) {
            page.setAlpha(MIN_ALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) {
            // [-1,1]
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            if (position < 0) {
                float scaleX = 1 + 0.3f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - 0.3f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            }
            l.d(page.toString());
            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }*/
}
