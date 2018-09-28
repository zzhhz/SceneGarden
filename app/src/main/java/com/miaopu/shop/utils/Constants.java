package com.miaopu.shop.utils;

import com.miaopu.shop.ui.model.DIYProduct;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2017/12/7.
 *
 * @date: 2017/12/7
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 常量类
 */
public class Constants {

    /**
     * 环信客服
     * appkey "1124180304253829#hortzz"
     * tenant 75119
     */
    public static final String KF_APP_KEY = "1117180318253952#hortzz";
    public static final String KF_TENANT = "75768";
    public static final String KF_ACCOUNT = "hortzz";

    public static final String TENCENT_BUGLY_ID = "7a18196f56";
    public static final int PAGE_SIZE = 15;


    public static final String EVENT_REFRESH_HOME_TOTAL = "com.zzh.refresh.home.total";
    public static final String EVENT_REFRESH_SHOP_CAR = "com.zzh.refresh.home.shop.car";
    public static final String EVENT_REFRESH_HOME_WASH = "com.zzh.refresh.home.wash";
    public static final String EVENT_REFRESH_HOME_REPAIR = "com.zzh.refresh.home.repair";
    public static final String EVENT_REFRESH_HOME_MAINtENANCE = "com.zzh.refresh.home.maintenance";
    public static final String EVENT_REFRESH_LOGIN = "com.miaopu.login";
    public static final String EVENT_REFRESH_FOLLOW = "com.miaopu.refresh.follow";
    public static final String EVENT_REFRESH_PAY = "com.miaopu.refresh.pay";
    /**
     * url http://ngrok1.gemcap.me/
     * http://120.79.166.150:8011
     * http://www.hortzz.com
     */
    public static final String BASE_URL = "http://www.hortzz.com";
    /**
     * 一夜默认显示的行数
     */
    public static final String PAGE_DEFAULT_SIZE = "10";


    public static final String METHOD = "yfzc/api?method=";
    public static final String LOGIN = "aop.company.login";
    public static final String METHOD_HOME = "aop.staff.workOrder.list";

    /**
     * 首页数据接口
     */
    public static final String API_HOME = METHOD + METHOD_HOME;
    public static final String METHOD_CODE = "aop.company.code";

    public static final String METHOD_EDIT_PASS = "aop.company.psw";
    public static final String METHOD_LOGIN = "aop.company.login";
    public static final String METHOD_FAULT = "aop.user.carStop.add";
    public static final String METHOD_PROBLEM_TYPE = "aop.dictionary.list";
    public static final String METHOD_WORK_DETAIL = "aop.service.order.detail";
    public static final String METHOD_WORK_NOW = "aop.service.dowork";
    /**
     * 故障上报
     */
    public static final String API_FAULT = METHOD + METHOD_FAULT;

    /**
     * 获取验证码
     */
    public static final String API_GET_CODE = METHOD + METHOD_CODE;
    /**
     * 修改密码接口
     */
    public static final String API_EDIT_PASS = METHOD + METHOD_EDIT_PASS;
    /**
     * 登录接口
     */
    public static final String API_LOGIN = METHOD + METHOD_LOGIN;
    /**
     * 问题类型
     */
    public static final String API_PROBLEM_TYPE = METHOD + METHOD_PROBLEM_TYPE;

    /**
     * 工单详情
     */
    public static final String API_WORK_DETAIL = METHOD + METHOD_WORK_DETAIL;
    /**
     * 通用立即工作
     */
    public static final String API_WORK_NOW = METHOD + METHOD_WORK_NOW;


    /**
     * 七牛
     */
    public static final String QINIU_AK = "c10sTG1vRW-TUqWcNYNqyXzp3mT1Vdi0Z9iLNIeI";
    public static final String QINIU_SK = "1CPR0zna7WEHduvP-fymmbKx158lA70cMOWmE9tB";
    public static final String QINIU_BUCKNAME = "wanghuifeng";
    public static final String QINIUBASE_URL = "http://7xsf4g.com1.z0.glb.clouddn.com";
    /**
     * 支付相关
     ***/
    public static final String ALIPAY_NOTIFY = BASE_URL + "/notify/getPayNotify";

    /**
     * public static final String ALIPAY_APP_PARTERNER_ID = "2088911064033977";
     * public static final String ALIPAY_SELLER = "zhidonglife@163.com";
     * public static final String ALIPAY_RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL8yPnyoR278zkk5aJTaj2OpLj10RlESO4XQagk7blGYaFFpiPw2QFHsWMzun7Om3gBMgSXHRowX3w/RnszDlm6hZTb73neP2esCv1rN9Et4v3OMoKghr9ol1T1A3hdyNwS0gjGXUT42LD4LOJ7gF8gwLLCLvKiJnw9B90Z+zHv9AgMBAAECgYAbc9lpCLiJBR+Rf/62MNOZ2bbb+BAIn/eP9SbkgHYMnTeNSPv0Ss3ddXQiqLihkPobTODWTdChWOQcX1rPx92XcVvLCW1dlhDCRtlWJ3K1WuZhSYWcE7ZT3bXBf2zrvcg5d92tlTRYuMsUrNdHoyngOcpf80zw3qpf8sRwRmqeQQJBAOY2EUpKOTs0tPyW2xmVCF7GRMbzLDNRBIaJOBBvuryhR6VmDmvOlPkZekbD6OcpXwS80LF25UxzgbGAqjhreXUCQQDUnVEwShv1ePdaqvJ9H07rS+9+64XMPoa5996GmwBNOiHTAoZxz2+WT7f2Z9tlANFtvyg6HnlOYmG7p40gKJ9pAkBVN8aGvZsZ85Tbu+w3OBf1HWfwTawbAu6t7rW2P+XcVcdzzqef+MTkwS2mbHcWuxXVZx/J0b1n44oq7voUEJTRAkEAsoV1Skqvtyle6C5pdU34gfE8oyE3Mwu9LCFII1W6px2MkRSptp4qX9eH68nAmcpaDwBW0pYL3RkWXu6MvNffIQJAZfABNUYdIK00fIpEXdqEebyi8UYcZTpVj1KW4Z411Vl5h+2Mc1T4ulEq/E6dK8IGECXpIeP6o0ydncRkHx2h2g==";
     */
    public static final String ALIPAY_APP_PARTERNER_ID = "2088921879090940";
    public static final String ALIPAY_SELLER = "hortzz@163.com";
    public static final String ALIPAY_RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALtzVETVhGFtWJOYOwuzUQnoBHAjEcRjdmGMb6KeJQEmPIOSsVivSqSPr8Fb4VAkYsM7+4W9aPRFL2hGwTRL6sS+82jQAhyhfuql5PBAx4cFaz9yvLI9n+q3q089tbEyoucRoD8IF8ktrWl44a4BgpmrWQWen/34jVHDJVppz/5vAgMBAAECgYBpgJHPZDVr+HVALRAel8BmyPWmf7aCSFDauoYiisvdB75Ld4blmVNzp4OOWr5HsxL7O4lnTqIByebS0+/uFUR+nHy9utCVz7C8+fIMfDHpg16A4r4thFzb/UTk4m+vHEG6ixYbJWW21Mmmc5EJO42vChstedno/ZKCKj/MClAG8QJBAOzG9Vp2M6wJnGCBDKPyBm9Zx/NxNP9bHW4JNMd3QVG8ZEaxFUOGQIzVDKHYfeTd6sWvhvYTt97ETD/waDjr+jMCQQDKqzF83AK4JM63qX9S1W9WbPAuMm7U18uJrBUbzsxoM+dTA+KiGNONZSUg8JYBXI4yJIkMPiJR8xv+bNrplebVAkEAy8qmrHimGsGe5XTNQ2JxZW+Z6I8cdVif6PBli/S5tLb4e9u4mIi5kD1jBP4HxW+3vPRJrgzkqgi8NCAgJDX+/wJBALvaVnKPU43vwBQI7dWPtDV9oFqRsJ1ksGqZsK8nzdvAUGhoL4dkT6K9395aYhA9BCiCKO3KIfGnz4jSyU69/UkCQQCjseMiP0JxzHIiSAlsdS0ZPez3Uwnwo+eofwpPgpyh4ZQN2IJSHEUNBOasKR/tiRgTQuL6l+wt+AEmS7wCMgIO";

    public static final String WX_APP_ID = "";
    public static final String WX_API_SECRET = "";
    public static final String WX_API_KEY = "";
    public static final String WX_APP_PARTERNER_ID = "";
    public static final String WX_NOTIFY_URL = "";


    /**
     * 获取七牛token
     */
    public static String getToken() {

        Mac mac = new Mac(QINIU_AK, QINIU_SK);
        PutPolicy putPolicy = new PutPolicy(QINIU_BUCKNAME);
        putPolicy.returnBody = "{\"name\": $(fname),\"size\": \"$(fsize)\",\"w\": \"$(imageInfo.width)\",\"h\": \"$(imageInfo.height)\",\"key\":$(etag)}";
        try {
            String uptoken = putPolicy.token(mac);
            l.e("debug:uptoken = " + uptoken);
            return uptoken;
        } catch (AuthException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final List<DIYProduct> DIY_ORDER = new LinkedList<>();

    public static void addDIY(DIYProduct product) {
        if (DIY_ORDER.contains(product)) {
            DIYProduct remove = DIY_ORDER.remove(DIY_ORDER.indexOf(product));
            int flagNum = remove.getFlagNum() + 1;
            remove.setFlagNum(flagNum);
            DIY_ORDER.add(remove);
        } else {
            DIY_ORDER.add(product);
        }
    }

    public static void removeDIY(DIYProduct product) {
        if (DIY_ORDER.contains(product)) {
            DIYProduct remove = DIY_ORDER.remove(DIY_ORDER.indexOf(product));
            int flagNum = remove.getFlagNum();
            if (flagNum > 1) {
                flagNum = flagNum - 1;
                remove.setFlagNum(flagNum);
                DIY_ORDER.add(remove);
            }
        }
    }
}
