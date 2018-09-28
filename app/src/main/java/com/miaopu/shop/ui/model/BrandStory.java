package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by liuwenzheng on 2018/2/23.
 */

public class BrandStory {
    /**
     * nickname : 施工队伍
     * viewTimes : 9
     * categoryId : 1
     * subTitle : 1
     * content : 1
     * indexFlag : 0
     * delFlag : 0
     * titleImg : 123
     * updateBy : u2897
     * category : 1
     * title : 1
     * id : 1
     * updateTime : 1515834700000
     * createTime : 1514613566000
     * sort : 1
     * tagList : [{"dicId":"8dbda7a73cdd440a8b5cf370cafb29b6","delFlag":"0","updateBy":"u2897","id":"121030025c3345fc8981462a1233d75c","updateTime":1516439257000,"createTime":1516439257000,"targetId":"1","dicValue":"案例一","createBy":"u2897"}]
     * type : 1
     * createBy : u2897
     * author : 1
     */

    private String nickname;
    private int viewTimes;
    private String categoryId;
    private String subTitle;
    private String content;
    private String indexFlag;
    private String delFlag;
    private String titleImg;
    private String updateBy;
    private String category;
    private String title;
    private String id;
    private long updateTime;
    private long createTime;
    private int sort;
    private String type;
    private String createBy;
    private String author;
    private List<Tags> tagList;

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "";
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIndexFlag() {
        return indexFlag;
    }

    public void setIndexFlag(String indexFlag) {
        this.indexFlag = indexFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Tags> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tags> tagList) {
        this.tagList = tagList;
    }

}
