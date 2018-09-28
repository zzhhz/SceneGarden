package com.miaopu.shop.ui.model;

import java.io.Serializable;

/**
 * Created by liuwenzheng on 2018/2/27.
 */

public class ProductCheck implements Serializable{
    private String productId;
    private String num;
    private String title;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
