package com.miaopu.shop;

import android.app.Activity;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.miaopu.shop.ui.model.User;
import com.miaopu.shop.utils.AppConstants;
import com.miaopu.shop.utils.SpUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2017/12/25.
 *
 * @date: 2017/12/25
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class ShopApplication extends MultiDexApplication {

    private static ShopApplication mInstance;
    private DisplayMetrics displayMetrics = null;
    private static User mCurrentUser;
    private static String token;

    public static String getToken() {
        if (TextUtils.isEmpty(token)) {
            token = SpUtils.getToken(mInstance);
        }
        return token;
    }

    public static void saveToken(String token) {
        ShopApplication.token = token;
    }

    public static User getCurrentUser() {
        if (mCurrentUser == null) {
            mCurrentUser = SpUtils.getUserLogin(mInstance);
        }
        return mCurrentUser;
    }

    public static void setCurrentUser(User mCurrentUser) {
        ShopApplication.mCurrentUser = mCurrentUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initImageLoader();
    }

    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .defaultDisplayImageOptions(defaultOptions)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new UnlimitedDiskCache(StorageUtils.getOwnCacheDirectory(this, AppConstants.APP_IMAGE)))
                .diskCacheSize(100 * 1024 * 1024).tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024)
                .threadPoolSize(3)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static ShopApplication getInstance() {
        return mInstance;
    }

    private final static List<Activity> list = new LinkedList<>();

    public static void addActivity(Activity act) {
        list.add(act);
    }

    public static void removeActivity(Activity act) {
        if (list.contains(act)) {
            list.remove(act);
        }
    }

    public static void exitActivity() {
        for (Activity act : list) {
            act.finish();
        }
    }

    /**
     * 本程序使用到的目录
     *
     * @return
     */
    public static String getAppFileDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MiaoPu";
    }

    public int dp2px(float f) {
        return (int) (0.5F + f * getScreenDensity());
    }

    public float getScreenDensity() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.density;
    }

    public int getScreenWidth() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.widthPixels;
    }

    public void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
        this.displayMetrics = DisplayMetrics;
    }

    public int getScreenHeight() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.heightPixels;
    }

    //获取应用的data/data/....Cache目录
    public String getCacheDirPath() {
        return getCacheDir().getAbsolutePath();
    }
}
