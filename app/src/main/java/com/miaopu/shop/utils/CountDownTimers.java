package com.miaopu.shop.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by user on 2017/10/24.
 *
 * @date: 2017/10/24
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 获取验证码计时器
 */
public class CountDownTimers extends CountDownTimer {
    private TextView mTextView;

    public CountDownTimers(TextView textView) {
        super(60000, 1000);
        mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (mTextView != null) {
            mTextView.setText((millisUntilFinished / 1000) + "秒重新获取");
            mTextView.setEnabled(false);
        }
    }

    @Override
    public void onFinish() {
        if (mTextView != null) {
            mTextView.setEnabled(true);
            mTextView.setText("重新发送");
        }
    }
}
