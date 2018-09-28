package com.miaopu.shop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.model.User;

/**
 * Created by user on 2017/11/28.
 *
 * @date: 2017/11/28
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 数据持久化
 */
public class SpUtils {

    public static final String XML_LOG_MSG = "miao_pu_login";
    public static final String XML_LOGIN_USER = "user_info";
    public static final String XML_LOGIN_TOKEN = "user_token";


    /**
     * 保存登录信息
     *
     * @param ctx  上下文
     * @param user 用户信息
     */
    public static void saveLogin(Context ctx, User user) {
        ShopApplication.setCurrentUser(user);
        SharedPreferences sp = ctx.getSharedPreferences(XML_LOG_MSG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //
        String json = JSON.toJSONString(user);
        editor.putString(XML_LOGIN_USER, json);
        editor.commit();
    }

    /**
     * 退出登录
     *
     * @param ctx 上下文
     */
    public static void logout(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(XML_LOG_MSG, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        ShopApplication.setCurrentUser(null);
    }

    /**
     * 获取用户登录信息
     *
     * @param ctx
     */
    public static User getUserLogin(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(XML_LOG_MSG, Context.MODE_PRIVATE);
        String json = sp.getString(XML_LOGIN_USER, "");
        return JSON.parseObject(json, User.class);
    }

    public static void saveToken(Context ctx, String token) {
        ShopApplication.saveToken(token);
        SharedPreferences sp = ctx.getSharedPreferences(XML_LOG_MSG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(XML_LOGIN_TOKEN, token);
        editor.commit();
    }

    public static String getToken(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(XML_LOG_MSG, Context.MODE_PRIVATE);
        String json = sp.getString(XML_LOGIN_TOKEN, "");
        return json;
    }

}
