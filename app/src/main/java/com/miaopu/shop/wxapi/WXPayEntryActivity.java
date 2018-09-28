package com.miaopu.shop.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.miaopu.shop.pay.wechat.WeChatPay;
import com.miaopu.shop.ui.base.BasePayActivity;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.l;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by user on 2017/9/9.
 *
 * @Date: 2017/9/9
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 支付
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TextUtils.isEmpty(Constants.WX_APP_ID)) {
            api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
        } else {
            api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
        }
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        //LLog.d( "onPayFinish, errCode = " + resp.errCode);
        if (BasePayActivity.WeChatHandler == null) {
            Toast.makeText(this, "支付完成，结果编码为" + resp.errCode, Toast.LENGTH_SHORT).show();
            WXPayEntryActivity.this.finish();
            return;
        }
        l.d(resp.errCode + ", " + resp.errStr);
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:// 正确返回
                BasePayActivity.WeChatHandler.sendMessageDelayed(BasePayActivity.WeChatHandler
                        .obtainMessage(WeChatPay.WeChatPayMessageFlag, "ERR_OK"), 300);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:// 认证被否决
                BasePayActivity.WeChatHandler.sendMessageDelayed(BasePayActivity.WeChatHandler
                        .obtainMessage(WeChatPay.WeChatPayMessageFlag, "ERR_AUTH_DENIED"), 300);
                break;
            case BaseResp.ErrCode.ERR_COMM:// 一般错误
                BasePayActivity.WeChatHandler.sendMessageDelayed(BasePayActivity.WeChatHandler
                        .obtainMessage(WeChatPay.WeChatPayMessageFlag, "ERR_COMM"), 300);
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:// 发送失败
                BasePayActivity.WeChatHandler.sendMessageDelayed(BasePayActivity.WeChatHandler
                        .obtainMessage(WeChatPay.WeChatPayMessageFlag, "ERR_SENT_FAILED"), 300);
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:// 不支持错误
                BasePayActivity.WeChatHandler.sendMessageDelayed(BasePayActivity.WeChatHandler
                        .obtainMessage(WeChatPay.WeChatPayMessageFlag, "ERR_UNSUPPORT"), 300);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:// 用户取消
                BasePayActivity.WeChatHandler.sendMessageDelayed(BasePayActivity.WeChatHandler
                        .obtainMessage(WeChatPay.WeChatPayMessageFlag, "ERR_USER_CANCEL"), 300);
                break;
            default:
                break;
        }
        WXPayEntryActivity.this.finish();
    }
}