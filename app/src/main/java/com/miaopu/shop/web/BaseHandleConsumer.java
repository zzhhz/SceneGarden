package com.miaopu.shop.web;


import com.miaopu.shop.ui.model.RespModel;
import com.miaopu.shop.utils.l;

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
public abstract class BaseHandleConsumer<T> implements Consumer<RespModel<T>> {

    /**
     * 数据返回成功状态值 000 成功
     */
    public static final String CODE_SUCCESS = "000";
    public static final String CODE_SUCCESS_2 = "200";


    @Override
    public void accept(RespModel<T> model) throws Exception {
        if (CODE_SUCCESS.equals(model.getCode()) || CODE_SUCCESS_2.equals(model.getCode())) {
            onSuccess(model.getData());
        } else {
            l.d("错误信息： " + model.getCode() + ", " + model.getMessage());
            onFailed(model.getCode());
            onFailed(model.getCode(), model.getMessage());
        }

    }

    /**
     * 成功回调
     *
     * @param model
     */
    public abstract void onSuccess(T model);

    /**
     * 失败回调
     *
     * @param code 状态值
     */
    public void onFailed(String code) {

    }

    /**
     * 失败回调
     *
     * @param code    状态值
     * @param message 错误信息
     */
    public void onFailed(String code, String message) {

    }

}
