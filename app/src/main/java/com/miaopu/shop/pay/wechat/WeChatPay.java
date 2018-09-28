package com.miaopu.shop.pay.wechat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.miaopu.shop.pay.MD5Util;
import com.miaopu.shop.utils.Constants;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by user on 2017/9/9.
 *
 * @Date: 2017/9/9
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 微信支付封装。
 */
public class WeChatPay {

    public static final String TAG = "---WeChat---";

    private Context mContext;
    private String body;
    /**
     * //订单号
     */
    private String orderNo;
    /**
     * //支付金额
     */
    private double price;
    private IWXAPI msgApi;
    private PayReq mPayReq;
    private Map<String, String> resultUnifiedOrder;
    public static Handler sHandler;
    public static final int WeChatPayMessageFlag = 1103;

    public WeChatPay() {
    }

    /**
     * @param context
     * @param body     订单描述
     * @param orderNo  订单编号
     * @param price    支付金额
     * @param sHandler 结果处理，主要发送状态值
     */
    public WeChatPay(Context context, String body, String orderNo, double price, Handler sHandler) {
        mContext = context;
        this.body = body;
        this.orderNo = orderNo;
        this.price = price;
        WeChatPay.sHandler = sHandler;
        msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp(Constants.WX_APP_ID);
        mPayReq = new PayReq();
    }

    public void GetAccessToken() {
        boolean isPaySupported = msgApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (isPaySupported) {
            new GetPrepayIdGenSendTask().execute();
        } else {
            Toast.makeText(mContext, "没有安装微信，或微信版本过低！", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class GetPrepayIdGenSendTask extends
            AsyncTask<Void, Void, Map<String, String>> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(mContext, "提示", "初始化微信支付中...");
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            resultUnifiedOrder = result;
            if (result.get("return_code").equals("FAIL")) {
                Toast.makeText(mContext, result.get("return_msg"), Toast.LENGTH_SHORT).show();
            } else {
                genPayReq();
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String, String> doInBackground(Void... params) {

            String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = genProductArgs();
            String isoEntity = "";
            try {
                isoEntity = new String(entity.toString().getBytes(),
                        "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buf = WeChatUtil.httpPost(url, isoEntity);
            String content = "";
            if (buf != null) {
                content = new String(buf);
            }
            Map<String, String> xml = decodeXml(content);
            if (content == null || content.equals("")) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    Toast.makeText(mContext, "微信获取prepaid信息失败，请联系客服", Toast.LENGTH_SHORT).show();
                }
            }
            return xml;
        }
    }

    /**
     * 生成APP微信支付参数 生成签名参数
     */
    protected void genPayReq() {

        mPayReq.appId = Constants.WX_APP_ID;
        mPayReq.partnerId = Constants.WX_APP_PARTERNER_ID;
        mPayReq.prepayId = resultUnifiedOrder.get("prepay_id");
        mPayReq.packageValue = "prepay_id=" + resultUnifiedOrder.get("prepay_id");
        mPayReq.nonceStr = genNonceStr();
        mPayReq.timeStamp = String.valueOf(genTimeStamp());

        Map<String, String> params = new HashMap<>();
        params.put("appid", mPayReq.appId);
        params.put("noncestr", mPayReq.nonceStr);
        params.put("package", mPayReq.packageValue);
        params.put("partnerid", mPayReq.partnerId);
        params.put("prepayid", mPayReq.prepayId);
        params.put("timestamp", mPayReq.timeStamp);

        mPayReq.sign = genAppSign(params);
        sendPayReq();
    }

    private String genAppSign(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        Set<String> strings = params.keySet();
        for (String key : strings) {
            sb.append(key);
            sb.append("=").append(params.get(key)).append("&");
        }
        sb.append("key=");
        sb.append(Constants.WX_API_KEY);

        String appSign = MD5Util.getMessageDigest(sb.toString().getBytes());
        Log.e(TAG, "genAppSign" + appSign);
        return appSign;
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private void sendPayReq() {
        msgApi.registerApp(Constants.WX_APP_ID);
        msgApi.sendReq(mPayReq);
    }


    public Map<String, String> decodeXml(String content) {
        try {
            Map<String, String> xml = new HashMap<>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("xml".equals(nodeName) == false) {
                            xml.put(nodeName, parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }
            return xml;
        } catch (Exception e) {
            Log.e(TAG, "decodeXml Exception" + e.getMessage());
        }
        return null;

    }

    private String genNonceStr() {
        Random random = new Random();
        return MD5Util.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    private String genProductArgs() {

        try {
            String nonceStr = genNonceStr();
            Map<String, String> params = new HashMap<>();
            params.put("appid", Constants.WX_APP_ID);
            params.put("body", body);
            params.put("mch_id", Constants.WX_APP_PARTERNER_ID);
            params.put("nonce_str", nonceStr);
            params.put("notify_url", Constants.WX_NOTIFY_URL);
            params.put("out_trade_no", orderNo);
            params.put("spbill_create_ip", "127.0.0.1");
            BigDecimal bg = new BigDecimal(price * 100);
            double doubleValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            params.put("total_fee", (int) doubleValue + "");
            params.put("trade_type", "APP");
            String sign = genPackageSign(params);
            params.put("sign", sign);
            String xmlstring = toXml(params);
            return xmlstring;

        } catch (Exception e) {
            Log.e("WeChat", "genProductArgs" + "genProductArgs fail, ex = " + e.getMessage());
            return null;
        }
    }

    /**
     * 生成签名
     */
    private String genPackageSign(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        Set<String> set = params.keySet();
        for (String key :
                set) {
            sb.append(key);
            sb.append('=');
            sb.append(params.get(key));
            sb.append('&');
        }

        sb.append("key=");
        sb.append(Constants.WX_API_KEY);
        String packageSign = MD5Util.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("", "genPackageSign" + packageSign);
        return packageSign;
    }

    private String toXml(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        Set<String> set = params.keySet();
        for (String key : set) {
            sb.append("<").append(key).append(">");
            sb.append(params.get(key));
            sb.append("</").append(key).append(">");
        }
        Log.e(TAG, "toXml" + sb.toString());
        return sb.toString();
    }


}
