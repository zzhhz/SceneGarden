package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/17.
 *
 * @date: 2018/1/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 评论
 */
public class Comment {


    /**
     * id : 12b3094ef9564b07862735c2694c013b
     * delFlag : 0
     * createTime : 1516669968000
     * updateTime : 1516669968000
     * createBy : u2897
     * updateBy : u2897
     * state : null
     * ids : null
     * targetId : 29681550c5354e0cac6362ba749cdb68
     * content : 商品不错，挺好
     * memberId : u2897
     * nickname : 我是甜甜圈呀
     * author : null
     * title : null
     * replyComment : null
     * replyId : null
     * type : null
     * headImg : http://p28t2n2ld.bkt.clouddn.com/timg100da09526004a90a72ce931c41505ab.jpg
     * images : [{"id":"797c754469e64c379648e5f2a0d51f0b","delFlag":"0","createTime":1516886169000,"updateTime":1516886169000,"createBy":"u2897","updateBy":"u2897","state":null,"ids":null,"targetId":"f79feae46d1a4be3a4f24f602246ec82","imgUrl":"http://p28t2n2ld.bkt.clouddn.com/t009e1fcf745049fbb0a1509120ca94d0.png","imgTitle":null,"imgBrief":null,"sort":null}]
     */

    private String id;
    private String delFlag;
    private long createTime;
    private long updateTime;
    private String createBy;
    private String updateBy;
    private String state;
    private String ids;
    private String targetId;
    private String content;
    private String memberId;
    private String nickname;
    private String author;
    private String title;
    private String replyComment;
    private String replyId;
    private String type;
    private String headImg;
    private List<Poster> images;

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "Comment{" +
                "id='" + id + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", state='" + state + '\'' +
                ", ids='" + ids + '\'' +
                ", targetId='" + targetId + '\'' +
                ", content='" + content + '\'' +
                ", memberId='" + memberId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", replyComment='" + replyComment + '\'' +
                ", replyId='" + replyId + '\'' +
                ", type='" + type + '\'' +
                ", headImg='" + headImg + '\'' +
                ", images=" + images +
                '}';
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(String replyComment) {
        this.replyComment = replyComment;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public List<Poster> getImages() {
        return images;
    }

    public void setImages(List<Poster> images) {
        this.images = images;
    }

    public List<String> getListImages() {
        if (images != null && !images.isEmpty()){
            List<String> list = new ArrayList<>();
            for (Poster poster : images){
                list.add(poster.getImgUrl());
            }
            return list;
        }
        return new ArrayList<String>();
    }
}
