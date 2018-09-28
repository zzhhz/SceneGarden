package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2018/1/10.
 *
 * @date: 2018/1/10
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class HomeItemPoster implements Parcelable {

    private String id;
    private String delFlag;
    private long createTime;
    private long updateTime;
    private String createBy;
    private String updateBy;
    private String state;
    private String ids;
    private String targetId;
    private String imgUrl;
    private String imgTitle;
    private String imgBrief;
    private String sort;
    private String mobileImg;
    private String name;

    public String getMobileImg() {
        return mobileImg;
    }

    public void setMobileImg(String mobileImg) {
        this.mobileImg = mobileImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgBrief() {
        return imgBrief;
    }

    public void setImgBrief(String imgBrief) {
        this.imgBrief = imgBrief;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getImageUrl() {
        return this.imgUrl;
    }

    public HomeItemPoster() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.delFlag);
        dest.writeLong(this.createTime);
        dest.writeLong(this.updateTime);
        dest.writeString(this.createBy);
        dest.writeString(this.updateBy);
        dest.writeString(this.state);
        dest.writeString(this.ids);
        dest.writeString(this.targetId);
        dest.writeString(this.imgUrl);
        dest.writeString(this.imgTitle);
        dest.writeString(this.imgBrief);
        dest.writeString(this.sort);
        dest.writeString(this.mobileImg);
        dest.writeString(this.name);
    }

    protected HomeItemPoster(Parcel in) {
        this.id = in.readString();
        this.delFlag = in.readString();
        this.createTime = in.readLong();
        this.updateTime = in.readLong();
        this.createBy = in.readString();
        this.updateBy = in.readString();
        this.state = in.readString();
        this.ids = in.readString();
        this.targetId = in.readString();
        this.imgUrl = in.readString();
        this.imgTitle = in.readString();
        this.imgBrief = in.readString();
        this.sort = in.readString();
        this.mobileImg = in.readString();
        this.name = in.readString();
    }

    public static final Creator<HomeItemPoster> CREATOR = new Creator<HomeItemPoster>() {
        @Override
        public HomeItemPoster createFromParcel(Parcel source) {
            return new HomeItemPoster(source);
        }

        @Override
        public HomeItemPoster[] newArray(int size) {
            return new HomeItemPoster[size];
        }
    };
}
