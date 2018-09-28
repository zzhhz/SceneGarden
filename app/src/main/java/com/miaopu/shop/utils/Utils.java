package com.miaopu.shop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zzh.zlibs.utils.ZUtils;

import java.lang.reflect.Field;

/**
 * Created by user on 2017/11/16.
 *
 * @date: 2017/11/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 工具类
 */
public class Utils extends ZUtils {

    public static String getImageNameTime() {
        return formatDateTime(System.currentTimeMillis(), "yyyy_MM_dd_HH_mm_ss_SSS");
    }

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 设置tab 下划线的左右边距
     *
     * @param tab
     * @param left  右边距
     * @param right 左边距
     */
    public static void setMargin(TabLayout tab, int left, int right) {
        Class<?> clz = tab.getClass();
        Field field = null;
        try {
            field = clz.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        try {
            LinearLayout llTab = (LinearLayout) field.get(tab);
            int marginLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, left, Resources.getSystem().getDisplayMetrics());
            int marginRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, right, Resources.getSystem().getDisplayMetrics());
            for (int i = 0; i < llTab.getChildCount(); i++) {
                View child = llTab.getChildAt(i);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = marginLeft;
                params.rightMargin = marginRight;
                child.setLayoutParams(params);
                child.invalidate();
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param ctx
     * @param msg
     */
    public static void toastTips(Context ctx, String msg) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 金额保留两位小数
     *
     * @param d
     * @return
     */
    public static String formatMoney(double d) {
        //DecimalFormat df = new DecimalFormat("########.00");
        return String.format("%.2f", d);
    }

    /**
     * 着色
     *
     * @param drawable
     * @param colors
     * @return
     */
    public static Drawable tintDrawable(Drawable drawable, int colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    /**
     * 富文本展示
     *
     * @param web     初始化的web view
     * @param urlDate 展示的数据
     */
    public static void openWebView(WebView web, String urlDate) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        web.loadDataWithBaseURL(null, getHtmlData(urlDate), "text/html", "utf-8", null);
        web.getSettings().setJavaScriptEnabled(false);
    }

    private static String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    /**
     * 打开软键盘
     *
     * @param mEditText
     * @param mContext
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * 判断当前软键盘是否打开
     *
     * @param activity
     * @return
     */
    public static boolean isSoftInputShow(Activity activity) {

        // 虚拟键盘隐藏 判断view是否为空
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            // 隐藏虚拟键盘
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
//       inputmanger.hideSoftInputFromWindow(view.getWindowToken(),0);

            return inputmanger.isActive() && activity.getWindow().getCurrentFocus() != null;
        }
        return false;
    }
}
