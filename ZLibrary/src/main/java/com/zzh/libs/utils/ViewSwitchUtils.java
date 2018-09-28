package com.zzh.libs.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

/**
 * Created by user on 2018/1/11.
 *
 * @date: 2018/1/11
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class ViewSwitchUtils {
    public static void startSwitchBackgroundAnim(ImageView view, Bitmap bitmap) {
        Drawable oldDrawable = view.getDrawable();
        Drawable oldBitmapDrawable ;
        TransitionDrawable oldTransitionDrawable = null;
        if (oldDrawable instanceof TransitionDrawable) {
            oldTransitionDrawable = (TransitionDrawable) oldDrawable;
            oldBitmapDrawable = oldTransitionDrawable.findDrawableByLayerId(oldTransitionDrawable.getId(1));
        } else if (oldDrawable instanceof BitmapDrawable) {
            oldBitmapDrawable = oldDrawable;
        } else {
            oldBitmapDrawable = new ColorDrawable(0xffc2c2c2);
        }

        if (oldTransitionDrawable == null) {
            oldTransitionDrawable = new TransitionDrawable(new Drawable[]{oldBitmapDrawable, new BitmapDrawable(bitmap)});
            oldTransitionDrawable.setId(0, 0);
            oldTransitionDrawable.setId(1, 1);
            oldTransitionDrawable.setCrossFadeEnabled(true);
            view.setImageDrawable(oldTransitionDrawable);
        } else {
            oldTransitionDrawable.setDrawableByLayerId(oldTransitionDrawable.getId(0), oldBitmapDrawable);
            oldTransitionDrawable.setDrawableByLayerId(oldTransitionDrawable.getId(1), new BitmapDrawable(bitmap));
        }
        oldTransitionDrawable.startTransition(1000);
    }
}
