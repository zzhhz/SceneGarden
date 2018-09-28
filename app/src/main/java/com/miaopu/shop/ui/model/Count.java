package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by liuwenzheng on 2018/2/22.
 */

public class Count {
    /**
     * design : 2
     * cart : 1
     * order : 7
     */

    private int design;
    private int cart;
    private int order;
    private int caseCount;

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "";
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public int getDesign() {
        return design;
    }

    public void setDesign(int design) {
        this.design = design;
    }

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
