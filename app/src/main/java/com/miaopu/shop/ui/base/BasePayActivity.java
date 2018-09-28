package com.miaopu.shop.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.miaopu.shop.pay.PayEnum;
import com.miaopu.shop.pay.PayPresenter;
import com.miaopu.shop.pay.PayResult;
import com.miaopu.shop.pay.alipay.AliPay;
import com.miaopu.shop.pay.interfaces.IPay;
import com.miaopu.shop.pay.wechat.WeChatPay;
import com.miaopu.shop.utils.Utils;

import java.util.Map;

/**
 * Created by user on 2018/2/23.
 *
 * @date: 2018/2/23
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 支付使用到的
 */
public abstract class BasePayActivity<P extends IPay> extends BaseSwipeActivity {
    public static int WechatOperationType;
    public static int WechatLoginCancelMessageWhat;
    public static int WechatLoginNoWechatClient;
    public static int WechatLoginMessageWhat;
    public static Handler WeChatHandler = null;
    public Handler mPayHandler;
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (getPresenterClass() != null) {
                presenter = (P) getPresenterClass().newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mPayHandler = sHandler;
    }

    protected Class<? extends IPay> getPresenterClass() {
        return PayPresenter.class;
    }

    protected abstract void payResultCallback(PayEnum plat, boolean paySuccess, String resultCode);

    public Handler sHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AliPay.SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        payResultCallback(PayEnum.AliPay, true, "9000");
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”
                        // 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        payResultCallback(PayEnum.AliPay, false, resultStatus);
                    }
                }
                break;

                case AliPay.SDK_CHECK_FLAG: {
                    Utils.toastTips(BasePayActivity.this, "检查结果为：" + msg.obj);
                }
                break;

                case WeChatPay.WeChatPayMessageFlag: {
                    String errorCode = msg.obj.toString();
                    if (errorCode.equals("ERR_OK")) {
                        payResultCallback(PayEnum.WxPay, true, "ERR_OK");

                    } else if (errorCode.equals("ERR_USER_CANCEL")) {
                        payResultCallback(PayEnum.WxPay, true, "ERR_USER_CANCEL");
                    } else {
                        payResultCallback(PayEnum.WxPay, true, errorCode);
                    }
                }
                break;
                default:
                    break;
            }
        }
    };

}