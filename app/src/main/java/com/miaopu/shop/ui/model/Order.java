package com.miaopu.shop.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2018/2/7.
 *
 * @date: 2018/2/7
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class Order implements Serializable{

    /**
     * receiveCode : 265552
     * memberId : 2798a5702b4141a1bceb6cd3f8d009af
     * receiveAddress : 安徽省/芜湖市/鸠江区河南路
     * receiveMan : 王汇丰
     * totalAmount : 369
     * code : DD20180204000443
     * updateBy : 2798a5702b4141a1bceb6cd3f8d009af
     * payTime : 1517749855000
     * id : d4c3223ba67143078feccd4c31d730ff
     * orderItemList : [{"totalPrice":369,"evaluate":true,"price":123,"defaultImage":"http://p28t2n2ld.bkt.clouddn.com/TB2CEPHX3L8F1JjSszgXXarfpXa_!!2529851865b63e80355c7c4a29aece3b98667fa993.jpg","productNum":3,"id":"c8fea021d7ec4f15a5ecdee2f16626f3","color":"青/蓝/紫","productId":"d48e509acb9c460288bacda71ca83967","productTitle":"虎皮兰金边虎皮兰盆景植物 室内盆景盆栽 吸甲醛装修房包邮","orderCode":"DD20180204000443","productStyle":"产品风格1"}]
     * createBy : 2798a5702b4141a1bceb6cd3f8d009af
     * state : FINISH
     * payStatus : 1
     * points : 36.9
     * delFlag : 0
     * dealAmount : 369
     * updateTime : 1517750134000
     * createTime : 1517749578000
     * receiveTel : 15305419871
     */

    private String receiveCode;
    private String memberId;
    private String receiveAddress;
    private String receiveMan;
    private double totalAmount;
    private String code;
    private String updateBy;
    private long payTime;
    private String id;
    private String createBy;
    private String state;
    private String payStatus;
    private double points;
    private String delFlag;
    private String payment;
    private double dealAmount;
    private long updateTime;
    private long createTime;
    private String receiveTel;
    private double deliveryFee;
    private List<OrderItem> orderItemList;

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(double dealAmount) {
        this.dealAmount = dealAmount;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getReceiveTel() {
        return receiveTel;
    }

    public void setReceiveTel(String receiveTel) {
        this.receiveTel = receiveTel;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
