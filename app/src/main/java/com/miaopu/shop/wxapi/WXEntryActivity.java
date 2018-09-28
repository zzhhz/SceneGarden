package com.miaopu.shop.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.miaopu.shop.pay.wechat.WeChatUtil;
import com.miaopu.shop.ui.base.BasePayActivity;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.Utils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    public static String codes = null;

    /**
     * 处理微信发出的向第三方应用请求app message
     * <p>
     * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
     * 做点其他的事情，包括根本不打开任何页面
     */
    public void onGetMessageFromWXReq(WXMediaMessage msg) {
        Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(
                getPackageName());
        startActivity(iLaunchMyself);
    }

    /**
     * 处理微信向第三方应用发起的消息
     * <p>
     * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
     * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作 回调。
     * <p>
     * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
     */
    public void onShowMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null && msg.mediaObject != null
                && (msg.mediaObject instanceof WXAppExtendObject)) {
            WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
            Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID, false);
        api.registerApp(Constants.WX_APP_ID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            // Toast.makeText(this, "code = " + ((SendAuth.Resp)
            // resp).code,Toast.LENGTH_SHORT).show();
        }
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if (BasePayActivity.WechatOperationType == 1) {
                    SendAuth.Resp resp2 = (SendAuth.Resp) resp;
                   /* codes = resp2.code;
                    Message message = Message.obtain();
                    message.what = 200;
                    message.obj = codes;
                    BasePayActivity.WeChatHandler.sendMessage(message);
                    finish();*/
                    new GetOpenIdTask().execute(resp2.code);
                } else {
                    Utils.toastTips(WXEntryActivity.this, "分享成功");
                    WXEntryActivity.this.finish();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (BasePayActivity.WechatOperationType == 1) {
                    BasePayActivity.WeChatHandler.sendEmptyMessageDelayed(BasePayActivity.WechatLoginCancelMessageWhat, 300);
                } else {
                    Utils.toastTips(WXEntryActivity.this, "取消分享");
                }
                WXEntryActivity.this.finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                WXEntryActivity.this.finish();
                BasePayActivity.WeChatHandler.sendEmptyMessage(BasePayActivity.WechatLoginNoWechatClient);
                break;
            default:
                WXEntryActivity.this.finish();
                break;
        }
    }

    private class GetOpenIdTask extends
            AsyncTask<String, Void, Map<String, String>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (result == null) {
                Utils.toastTips(WXEntryActivity.this, "获取微信信息失败，请重试");
                WXEntryActivity.this.finish();
            } else {
                if (result.containsKey("errcode")) {
                    Utils.toastTips(WXEntryActivity.this,
                            (result.get("errmsg") == null) ? "获取微信信息失败，请重试"
                                    : result.get("errmsg"));
                    WXEntryActivity.this.finish();
                } else {
                    new GetUserInfoTask().execute("" + result.get("access_token"), "" + result.get("openid"));
                }
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String, String> doInBackground(String... params) {

            String genUrlString = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                    + Constants.WX_APP_ID
                    + "&secret="
                    + /*KeyStore.getWechatAppSecret()*/ Constants.WX_API_SECRET
                    + "&code="
                    + params[0]
                    + "&grant_type=authorization_code";
            String url = String.format(genUrlString);
            byte[] buf = WeChatUtil.httpGet(url);
            String content = "";
            if (buf != null) {
                content = new String(buf);
            }
            if (content == null || content.equals("")) {
                return null;
            }
            Map<String, String> rMap = new HashMap<String, String>();
            try {
                JSONObject jsonObj = new JSONObject(content);
                if (jsonObj.has("errcode")) {
                    rMap.put("errcode", jsonObj.optString("errcode"));
                    rMap.put("errmsg", jsonObj.optString("errmsg"));
                } else {
                    rMap.put("access_token", jsonObj.optString("access_token"));
                    rMap.put("openid", jsonObj.optString("openid"));
                    rMap.put("scope", jsonObj.optString("scope"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return rMap;
        }
    }

    private class GetUserInfoTask extends
            AsyncTask<String, Void, Map<String, String>> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (result == null) {
                Utils.toastTips(WXEntryActivity.this, "获取微信用户信息失败，请重试");
                WXEntryActivity.this.finish();
            } else {
                if (result.containsKey("errcode")) {
                    Utils.toastTips(WXEntryActivity.this,
                            (result.get("errmsg") == null) ? "获取微信信息失败，请重试"
                                    : result.get("errmsg"));
                    WXEntryActivity.this.finish();
                } else {
                    Message msgMessage = BasePayActivity.WeChatHandler
                            .obtainMessage();
                    msgMessage.what = BasePayActivity.WechatLoginMessageWhat;
                    msgMessage.obj = result;
                    BasePayActivity.WeChatHandler.sendMessageDelayed(msgMessage,
                            400);
                    WXEntryActivity.this.finish();
                }
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String, String> doInBackground(String... params) {
            String genUrlString = "https://api.weixin.qq.com/sns/userinfo?access_token="
                    + params[0]
                    + "&openid="
                    + params[1];
            String url = String.format(genUrlString);
            byte[] buf = WeChatUtil.httpGet(url);
            String content = "";
            if (buf != null) {
                content = new String(buf);
            }
            if (content == null || content.equals("")) {
                return null;
            }
            Map<String, String> rMap = new HashMap<String, String>();
            try {
                JSONObject jsonObj = new JSONObject(content);
                if (jsonObj.has("errcode")) {
                    rMap.put("errcode", jsonObj.optString("errcode"));
                    rMap.put("errmsg", jsonObj.optString("errmsg"));
                } else {
                    rMap.put("nickname", jsonObj.optString("nickname"));
                    rMap.put("openid", jsonObj.optString("openid"));
                    rMap.put("headimgurl", jsonObj.optString("headimgurl"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return rMap;
        }
    }

}