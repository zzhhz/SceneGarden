package com.miaopu.shop.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zzh.sexual.secret.R;

/**
 * Created by user on 2018/1/3.
 *
 * @date: 2018/1/3
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class MultipleViewPager extends ViewPager {
    private boolean isParentScrollView = false;

    public MultipleViewPager(Context context) {
        super(context);
    }

    public MultipleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MultipleViewPager);

        isParentScrollView = array.getBoolean(R.styleable.MultipleViewPager_isParentScrollView, false);
        array.recycle();
    }

    PointF downPoint = new PointF();
    OnSingleTouchListener onSingleTouchListener;

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        switch (evt.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录按下时候的坐标
                downPoint.x = evt.getX();
                downPoint.y = evt.getY();
                if (this.getChildCount() > 1) { //有内容，多于1个时
                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (this.getChildCount() > 1) { //有内容，多于1个时
                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                // 在up时判断是否按下和松手的坐标为一个点
                if (PointF.length(evt.getX() - downPoint.x, evt.getY()
                        - downPoint.y) < (float) 5.0) {
                    onSingleTouch(this);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(evt);
    }

    public void onSingleTouch(View v) {
        if (onSingleTouchListener != null) {
            onSingleTouchListener.onSingleTouch(v);
        }
    }

    public interface OnSingleTouchListener {
        public void onSingleTouch(View v);
    }

    public void setOnSingleTouchListener(
            OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isParentScrollView) {
            int height = 0;
            View childView = getChildAt(0);
            if (childView != null) {
                childView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = childView.getHeight();
                if (h > height) {
                    height = h;
                }
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
