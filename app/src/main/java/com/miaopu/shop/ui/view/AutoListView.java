package com.miaopu.shop.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by user on 2018/1/15.
 *
 * @date: 2018/1/15
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 可以在scroll view 使用的grid view
 */
public class AutoListView extends ListView {
    public AutoListView(Context context) {
        super(context);
    }

    public AutoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
