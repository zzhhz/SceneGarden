package com.miaopu.shop.utils;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by user on 2018/2/2.
 *
 * @date: 2018/2/2
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 发送订阅事件
 */
public class EventUtils {
    public static void sendEventLogin() {
        EventBus.getDefault().post(Constants.EVENT_REFRESH_LOGIN);
    }
}
