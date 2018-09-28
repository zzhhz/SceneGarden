package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/16.
 *
 * @date: 2018/1/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 作品
 */
public class Works extends Product {

    /**
     * id : 1
     * delFlag : 0
     * createTime : null
     * updateTime : null
     * createBy : null
     * updateBy : null
     * state : null
     * memberId : 2
     * nickname : 12
     * title : 2
     * subTitle : 2
     * defaultImg : 2
     * brief : null
     * products : null
     * tags : [{"id":"1","delFlag":"0","createTime":null,"updateTime":null,"createBy":null,"updateBy":null,"state":null,"targetId":"1","dicId":"1","dicValue":"1"}]
     * commentCount : 0
     */
    private String memberId;
    private String nickname;
    private String subTitle;
    private String defaultImg;
    private String brief;
    private String products;
    private int commentCount;
    private String headImg;

    private List<Tags> tags;
    private List<HomeItemPoster> attaches;

    public List<HomeItemPoster> getAttaches() {
        return attaches;
    }

    public void setAttaches(List<HomeItemPoster> attaches) {
        this.attaches = attaches;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    @Override
    public String getImageUrl() {
        return defaultImg;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String getTitle() {
        if (TextUtils.isEmpty(super.getTitle())) {
            return super.getName();
        } else {
            return super.getTitle();
        }
    }

    public Works() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.memberId);
        dest.writeString(this.nickname);
        dest.writeString(this.subTitle);
        dest.writeString(this.defaultImg);
        dest.writeString(this.brief);
        dest.writeString(this.products);
        dest.writeInt(this.commentCount);
        dest.writeString(this.headImg);
        dest.writeList(this.tags);
        dest.writeTypedList(this.attaches);
    }

    protected Works(Parcel in) {
        super(in);
        this.memberId = in.readString();
        this.nickname = in.readString();
        this.subTitle = in.readString();
        this.defaultImg = in.readString();
        this.brief = in.readString();
        this.products = in.readString();
        this.commentCount = in.readInt();
        this.headImg = in.readString();
        this.tags = new ArrayList<Tags>();
        in.readList(this.tags, Tags.class.getClassLoader());
        this.attaches = in.createTypedArrayList(HomeItemPoster.CREATOR);
    }

    public static final Creator<Works> CREATOR = new Creator<Works>() {
        @Override
        public Works createFromParcel(Parcel source) {
            return new Works(source);
        }

        @Override
        public Works[] newArray(int size) {
            return new Works[size];
        }
    };
}
