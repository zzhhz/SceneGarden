package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by liuwenzheng on 2018/2/13.
 */

public class DeliveryList implements Serializable{

    /**
     * id : fffbedd729aa4e19b812fe0d4ab7c17d
     * delFlag : 0
     * createTime : 1517988044000
     * updateTime : 1517988044000
     * createBy : 5eb980158ec142d7a07a464bd351d354
     * updateBy : 5eb980158ec142d7a07a464bd351d354
     * state : null
     * ids : null
     * memberId : 5eb980158ec142d7a07a464bd351d354
     * name : 123
     * phone : 15253148180
     * province : 安徽省/合肥市/瑶海区
     * city : null
     * area : null
     * sort : null
     * address : 123123
     * defaultFlag : 1
     * code : 250023
     */

    private String id;
    private String delFlag;
    private long createTime;
    private long updateTime;
    private String createBy;
    private String updateBy;
    private Object state;
    private Object ids;
    private String memberId;
    private String name;
    private String phone;
    private String province;
    private Object city;
    private Object area;
    private Object sort;
    private String address;
    private String defaultFlag;
    private String code;

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getIds() {
        return ids;
    }

    public void setIds(Object ids) {
        this.ids = ids;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
