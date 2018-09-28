package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by liuwenzheng on 2018/2/25.
 */

public class Design {
    /**
     * memberId : ebde742e12f44266ad268313fdf59763
     * style : oushi
     * updateBy : u2897
     * category : river
     * id : 2ffe7157963443e69cea82443117255b
     * scale : 1000yx
     * commentCount : 0
     * createBy : ebde742e12f44266ad268313fdf59763
     * defaultImg : http://p28t2n2ld.bkt.clouddn.com/安全软件公司网站模板是一款基于HTML5_CSS3制作的软件公司官网模板。c9ed8837f7d543bc981b668e18d4ef4b.jpg
     * subTitle : 123
     * state : success
     * delFlag : 0
     * brief : 123
     * title : 123
     * area : huabei
     * updateTime : 1519527202000
     * createTime : 1519485196000
     * dicIds : d1d4f956346749da90c52383079afbe2,
     */

    private String memberId;
    private String style;
    private String updateBy;
    private String category;
    private String id;
    private String scale;
    private int commentCount;
    private String createBy;
    private String defaultImg;
    private String subTitle;
    private String state;
    private String delFlag;
    private String brief;
    private String title;
    private String area;
    private long updateTime;
    private long createTime;
    private String dicIds;

    private int isSelected;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "";
    }
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getDicIds() {
        return dicIds;
    }

    public void setDicIds(String dicIds) {
        this.dicIds = dicIds;
    }
}
