package com.miaopu.shop.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zzh.sexual.secret.R;

/**
 * Created by user on 2017/11/22.
 *
 * @date: 2017/11/22
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 图片加载工具类
 * @since 3.8 glide
 */
public class GlideUtils {

    /**
     * 加载圆形图片
     *
     * @param ctx     上下文
     * @param url     图片地址
     * @param view    ImageVeiw
     * @param errorId 图片加载错误时显示的图片
     */
    public static void loadCircleImageView(Context ctx, String url, ImageView view, int errorId) {
        if (TextUtils.isEmpty(url)) {
            view.setImageResource(errorId);
            return;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(errorId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(ctx))
                .error(errorId);
        Glide.with(ctx).load(url).apply(options).into(view);
    }

    /**
     * 加载图片
     *
     * @param ctx     上下文
     * @param url     图片地址
     * @param view    ImageView
     * @param errorId 图片加载错误时显示的图片
     */
    public static void loadImageView(Context ctx, String url, ImageView view, int errorId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(errorId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorId);
        Glide.with(ctx).load(url).apply(options).into(view);

//        Glide.with(ctx).load(url).centerCrop().crossFade()
//                .error(errorId).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

    public static void loadBlurImageView(Context ctx, String url, ImageView view) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL).transform(new BlurTransformation(ctx))
                .error(R.mipmap.ic_launcher);
        Glide.with(ctx).load(url).apply(options).into(view);
    }

    /**
     * 加载图片 按比例缩放
     *
     * @param ctx     上下文
     * @param url     图片地址
     * @param view    ImageVeiw
     * @param errorId 图片加载错误时显示的图片
     */
    public static void loadScaleImageView(Context ctx, String url, final ImageView view, int errorId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(errorId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorId);
       /*  Glide.with(ctx).load(url).apply(options).into(view);*/

        Glide.with(ctx).asBitmap().load(url).apply(options).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int vWidth = view.getWidth();
                int bWidth = resource.getWidth();
                double scale = (double) vWidth / (double) bWidth;
                int vHeight = (int) (scale * resource.getHeight());
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = vHeight;
                view.setLayoutParams(layoutParams);
                view.setImageBitmap(resource);
            }

            /*@Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int vWidth = view.getWidth();
                int bWidth = resource.getWidth();
                double scale = (double) vWidth / (double) bWidth;
                int vHeight = (int) (scale * resource.getHeight());
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = vHeight;
                view.setLayoutParams(layoutParams);
                view.setImageBitmap(resource);
            }*/
        });
    }

    /**
     * 加载图片 按比例缩放
     *
     * @param ctx     上下文
     * @param url     图片地址
     * @param view    ImageVeiw
     * @param errorId 图片加载错误时显示的图片
     */
    public static void loadHomeScaleImageView(Context ctx, String url, final ImageView view, int errorId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(errorId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorId);
        Glide.with(ctx).asBitmap().load(url).apply(options).into(new SimpleTarget<Bitmap>() {
            /*@Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int vHeight = Utils.dp2px(ctx, 110);
                int bHeight = resource.getHeight();
                double scale = (double) vHeight / (double) bHeight;
                int vWidth = (int) (scale * resource.getWidth());
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = vHeight;
                layoutParams.width = vWidth;
                view.setLayoutParams(layoutParams);
                view.setImageBitmap(resource);
            }*/
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int vHeight = Utils.dp2px(ctx, 110);
                /*int bHeight = resource.getHeight();
                double scale = (double) vHeight / (double) bHeight;
                int vWidth = (int) (scale * resource.getWidth());*/
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = vHeight;
                layoutParams.width = vHeight;
                view.setLayoutParams(layoutParams);
                view.setImageBitmap(resource);
            }
        });
    }

    /**
     * 加载图片 按比例缩放
     *
     * @param ctx  上下文
     * @param url  图片地址
     * @param view ImageVeiw
     */
    public static void loadHomeScaleImageView2(Context ctx, int url, final ImageView view) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(ctx).asBitmap().load(url).apply(options).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int vHeight = Utils.dp2px(ctx, 110);
                int bHeight = resource.getHeight();
                double scale = (double) vHeight / (double) bHeight;
                int vWidth = (int) (scale * resource.getWidth());
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = vHeight;
                layoutParams.width = vWidth;
                view.setLayoutParams(layoutParams);
                view.setImageBitmap(resource);
            }
        });
    }

    /**
     * 加载图片 按比例缩放, 屏幕宽大小
     *
     * @param ctx  上下文
     * @param url  图片地址
     * @param view ImageView
     */
    public static void loadScaleImageView(Context ctx, String url, final ImageView view) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(ctx).asBitmap().load(url).apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        int vWidth = Utils.getDisplayWidth(ctx);
                        int bWidth = resource.getWidth();
                        double scale = (double) vWidth / (double) bWidth;
                        int vHeight = (int) (scale * resource.getHeight());
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        layoutParams.height = vHeight;
                        layoutParams.width = vWidth;
                        view.setLayoutParams(layoutParams);
                        view.setImageBitmap(resource);
                    }

                    /*@Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        int vWidth = Utils.getDisplayWidth(ctx);
                        int bWidth = resource.getWidth();
                        double scale = (double) vWidth / (double) bWidth;
                        int vHeight = (int) (scale * resource.getHeight());
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        layoutParams.height = vHeight;
                        layoutParams.width = vWidth;
                        view.setLayoutParams(layoutParams);
                        view.setImageBitmap(resource);
                    }*/
                });
    }

}
