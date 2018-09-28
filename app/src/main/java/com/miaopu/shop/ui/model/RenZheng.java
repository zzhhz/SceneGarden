package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by liuwenzheng on 2018/3/11.
 */

public class RenZheng {
    private List<Images> images;
    private List<Images> materialFile;
    private String       brief;
    private String       material;
    private String       id;
    private String       area;
    private String       realName;
    private String       phone;
    private String       qq;
    private String       onlineStuff;
    private String       reason;
    private String workscategory;
    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "RenZheng{}";
    }

    public String getWorkscategory() {
        return workscategory;
    }

    public void setWorkscategory(String workscategory) {
        this.workscategory = workscategory;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Images> getMaterialFile() {
        return materialFile;
    }

    public void setMaterialFile(List<Images> materialFile) {
        this.materialFile = materialFile;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getOnlineStuff() {
        return onlineStuff;
    }

    public void setOnlineStuff(String onlineStuff) {
        this.onlineStuff = onlineStuff;
    }
}
