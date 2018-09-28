package com.miaopu.shop.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.zzh.sexual.secret.R;

/**
 * Created by user on 2017/11/21.
 *
 * @date: 2017/11/21
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 等待框
 */
public class LoadingDialog extends Dialog {
    TextView msg;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.progress_dialog);
        initDialog();
    }

    private void initDialog() {
        setContentView(R.layout.dialog_loading);
        msg = findViewById(R.id.id_tv_loadingmsg);
        msg.setText("加载中...");
    }

    public void setShowText(String text) {
        msg.setText(text);
    }
}
