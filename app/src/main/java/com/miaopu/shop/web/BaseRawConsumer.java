package com.miaopu.shop.web;


import com.miaopu.shop.ui.model.RespListModel;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by user on 2017/10/25.
 *
 * @date: 2017/10/25
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 对成功的方法进行一次封装。
 */
public abstract class BaseRawConsumer<T> implements Consumer<RespListModel<T>> {

    /**
     * 数据返回成功状态值 000 成功
     */
    public static final String CODE_SUCCESS = "000";
    public static final String CODE_SUCCESS_2 = "200";

    @Override
    public void accept(RespListModel<T> model) throws Exception {
        if (CODE_SUCCESS.equals(model.getCode()) || CODE_SUCCESS_2.equals(model.getCode())) {
            onSuccess(model);
        } else {
            onFailed(model.getCode(), model.getMessage());
        }
    }

    /**
     * 成功回调
     *
     * @param model
     */
    public abstract void onSuccess(RespListModel<T> model);

    /**
     * 失败回调
     *
     * @param code    状态值
     * @param message 错误信息
     */
    public void onFailed(String code, String message) {

    }

}
