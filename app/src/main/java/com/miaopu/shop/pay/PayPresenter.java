package com.miaopu.shop.pay;


import com.zzh.sexual.secret.R;
import com.miaopu.shop.pay.alipay.AliPay;
import com.miaopu.shop.pay.interfaces.IPay;
import com.miaopu.shop.pay.wechat.WeChatPay;
import com.miaopu.shop.ui.base.BasePayActivity;

import java.text.DecimalFormat;

/**
 * Created by user on 2017/9/9.
 *
 * @Date: 2017/9/9
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class PayPresenter implements IPay {

    public void pay(PayEnum payment, BasePayActivity activity, String orderId, double price) {
        if (payment == PayEnum.AliPay) {
            AliPay aliPay = new AliPay(activity.mPayHandler, activity);
            aliPay.pay(orderId, activity.getResources().getString(R.string.app_name), "null", new DecimalFormat("0.00").format(price));
        } else if (payment == PayEnum.WxPay) {
            WeChatPay pay = new WeChatPay(activity, activity.getResources().getString(R.string.app_name), orderId, price, activity.mPayHandler);
            pay.GetAccessToken();
        }
    }
}
