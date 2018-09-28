package com.miaopu.shop.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zzh.sexual.secret.R;
import com.miaopu.shop.ui.adapter.ScreenAdapter;
import com.miaopu.shop.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/1/15.
 *
 * @date: 2018/1/15
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class ScreenSheetDialog {

    private PopupWindow mPopupWindow;
    private Context mContext;

    @BindView(R.id.gv_style)
    public GridView mStyleView;
    @BindView(R.id.gv_property)
    public GridView mPropertyView;
    @BindView(R.id.iv_arrow)
    public ImageView iv_style;
    @BindView(R.id.iv_arrow2)
    public ImageView iv_property;
    @BindView(R.id.et_max_value)
    public EditText maxValue;
    @BindView(R.id.et_min_value)
    public EditText minValue;
    @BindView(R.id.ll_show)
    public LinearLayout ll_show_style;
    @BindView(R.id.ll_property)
    public LinearLayout ll_show_property;
    @BindView(R.id.tv_reset)
    public View mReset;
    @BindView(R.id.tv_confirm)
    public View mConfirm;

    @BindView(R.id.et_max_value_guan)
    public EditText gMaxValue;
    @BindView(R.id.et_min_value_guan)
    public EditText gMinValue;
    @BindView(R.id.et_min_value_jing)
    public EditText jMinValue;
    @BindView(R.id.et_max_value_jing)
    public EditText jMaxValue;
    @BindView(R.id.et_max_value_gen)
    public EditText genMaxValue;
    @BindView(R.id.et_min_value_gen)
    public EditText genMinValue;
    @BindView(R.id.et_max_value_pen)
    public EditText pMaxValue;
    @BindView(R.id.et_min_value_pen)
    public EditText pMinValue;
    @BindView(R.id.et_min_value_price)
    public EditText priceMinValue;
    @BindView(R.id.et_max_value_price)
    public EditText priceMaxValue;


    public ScreenAdapter mStyleAdapter;
    public ScreenAdapter mPropertyAdapter;

    public ScreenListener mScreenListener;

    public ScreenSheetDialog(Context context) {
        mContext = context;
        initPopWindow();
        initListener();
    }


    private void initListener() {
        ll_show_style.setOnClickListener(v -> {
            mStyleAdapter.setShowAll(!mStyleAdapter.isShow());
            mStyleAdapter.notifyDataSetChanged();
        });
        ll_show_property.setOnClickListener(v -> {
            mPropertyAdapter.setShowAll(!mPropertyAdapter.isShow());
            mPropertyAdapter.notifyDataSetChanged();
        });

        mReset.setOnClickListener(v -> {
            maxValue.setText("");
            minValue.setText("");
            gMinValue.setText("");
            gMaxValue.setText("");
            jMinValue.setText("");
            jMaxValue.setText("");
            genMinValue.setText("");
            genMaxValue.setText("");
            pMinValue.setText("");
            pMaxValue.setText("");
            priceMinValue.setText("");
            priceMaxValue.setText("");
            mStyleAdapter.resetSelect();
            mStyleAdapter.notifyDataSetChanged();
            mPropertyAdapter.resetSelect();
            mPropertyAdapter.notifyDataSetChanged();
        });
        mConfirm.setOnClickListener(v -> {
            if (mScreenListener != null) {
                String s = minValue.getText().toString();
                String s1 = maxValue.getText().toString();

                mScreenListener.onClickConfirm(s, s1,
                        gMinValue.getText().toString(), gMaxValue.getText().toString(),
                        jMinValue.getText().toString(), jMaxValue.getText().toString(),
                        genMinValue.getText().toString(), genMaxValue.getText().toString(),
                        pMinValue.getText().toString(), pMaxValue.getText().toString(),
                        priceMinValue.getText().toString(), priceMaxValue.getText().toString(),
                        mStyleAdapter.getSelectLabel(), mPropertyAdapter.getSelectLabel());
                maxValue.setText("");
                minValue.setText("");
                gMinValue.setText("");
                gMaxValue.setText("");
                jMinValue.setText("");
                jMaxValue.setText("");
                genMinValue.setText("");
                genMaxValue.setText("");
                pMinValue.setText("");
                pMaxValue.setText("");
                priceMinValue.setText("");
                priceMaxValue.setText("");
                mStyleAdapter.resetSelect();
                mStyleAdapter.notifyDataSetChanged();
                mPropertyAdapter.resetSelect();
                mPropertyAdapter.notifyDataSetChanged();
            }
            mPopupWindow.dismiss();
        });

        mStyleView.setOnItemClickListener((parent, view, position, id) -> {
            mStyleAdapter.setSelectLabel(position);
            mStyleAdapter.notifyDataSetChanged();

        });
        mPropertyView.setOnItemClickListener((parent, view, position, id) -> {
            mPropertyAdapter.setSelectLabel(position);
            mPropertyAdapter.notifyDataSetChanged();
        });

    }

    private void initPopWindow() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_screen, null);
        ButterKnife.bind(this, contentView);
        mPopupWindow = new PopupWindow(contentView);
        mPopupWindow.setHeight((int) (Utils.getDisplayHeight(mContext) * 0.75));
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        //点击空白处时，隐藏掉pop窗口
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //添加弹出、弹入的动画
        mPopupWindow.setAnimationStyle(R.style.popwindow);
    }

    public void setScreenListener(ScreenListener screenListener) {
        mScreenListener = screenListener;
    }

    public void setData(List<String> style, List<String> property) {
        mStyleAdapter = new ScreenAdapter(mContext);
        mPropertyAdapter = new ScreenAdapter(mContext);
        mStyleAdapter.addAll(style);
        mPropertyAdapter.addAll(property);
        mStyleView.setAdapter(mStyleAdapter);
        mPropertyView.setAdapter(mPropertyAdapter);
    }


    public void show(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        Drawable property = iv_property.getDrawable();
        if (property != null) {
            Utils.tintDrawable(property, Color.parseColor("#818ffa"));
        }

        Drawable drawable = iv_style.getDrawable();
        if (drawable != null) {
            Utils.tintDrawable(drawable, Color.parseColor("#818ffa"));
        }
        backgroundAlpha(0.75f);
        mPopupWindow.setOnDismissListener(() -> {
            backgroundAlpha(1);
        });
    }

    /**
     * //0.0-1.0
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        if (mContext instanceof Activity) {
            WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
            lp.alpha = bgAlpha;
            ((Activity) mContext).getWindow().setAttributes(lp);
            ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }

    public interface ScreenListener {
        //void onClickConfirm(double min, double max, List<String> style, List<String> property);
        //void onClickConfirm(double min, double max, String style, String property);
        void onClickConfirm(String min, String max,
                            String min2, String max2,
                            String min3, String max3,
                            String min4, String max4,
                            String min5, String max5,
                            String min6, String max6,
                            String style, String property);
    }

}
