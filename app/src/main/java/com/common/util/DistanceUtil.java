package com.common.util;


import com.miaopu.shop.ShopApplication;

public class DistanceUtil {

    public static int getCameraAlbumWidth() {
        return (ShopApplication.getInstance().getScreenWidth() - ShopApplication.getInstance().dp2px(10)) / 4 -ShopApplication.getInstance().dp2px(4);
    }
    
    // 相机照片列表高度计算 
    public static int getCameraPhotoAreaHeight() {
        return getCameraPhotoWidth() + ShopApplication.getInstance().dp2px(4);
    }
    
    public static int getCameraPhotoWidth() {
        return ShopApplication.getInstance().getScreenWidth() / 4 - ShopApplication.getInstance().dp2px(2);
    }

    //活动标签页grid图片高度
    public static int getActivityHeight() {
        return (ShopApplication.getInstance().getScreenWidth() - ShopApplication.getInstance().dp2px(24)) / 3;
    }
}
