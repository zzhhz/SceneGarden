package com.miaopu.shop.ui.model;

/**
 * Created by user on 2018/1/3.
 *
 * @date: 2018/1/3
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class Resp {


    /**
     * msg : 商品康乃馨花卉盆栽庭院阳台室内观花植物带花苞四季开花绿植康乃馨数量不足
     * code : 200
     * success : true
     * isChange : true
     */

    private String msg;
    private String code;
    private boolean success;
    private boolean isChange;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIsChange() {
        return isChange;
    }

    public void setIsChange(boolean isChange) {
        this.isChange = isChange;
    }
}
