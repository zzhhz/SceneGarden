package com.miaopu.shop.ui.model;

import java.io.Serializable;

/**
 * Created by user on 2018/2/7.
 *
 * @date: 2018/2/7
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class OrderItem implements Serializable{


    /**
     * totalPrice : 369
     * evaluate : true
     * price : 123
     * defaultImage : http://p28t2n2ld.bkt.clouddn.com/TB2CEPHX3L8F1JjSszgXXarfpXa_!!2529851865b63e80355c7c4a29aece3b98667fa993.jpg
     * productNum : 3
     * id : c8fea021d7ec4f15a5ecdee2f16626f3
     * color : 青/蓝/紫
     * productId : d48e509acb9c460288bacda71ca83967
     * productTitle : 虎皮兰金边虎皮兰盆景植物 室内盆景盆栽 吸甲醛装修房包邮
     * orderCode : DD20180204000443
     * productStyle : 产品风格1
     */

    private double totalPrice;
    private boolean evaluate;
    private double price;
    private String defaultImage;
    private int productNum;
    private String id;
    private String color;
    private String productId;
    private String productTitle;
    private String orderCode;
    private String productStyle;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isEvaluate() {
        return evaluate;
    }

    public void setEvaluate(boolean evaluate) {
        this.evaluate = evaluate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getProductStyle() {
        return productStyle;
    }

    public void setProductStyle(String productStyle) {
        this.productStyle = productStyle;
    }
}
