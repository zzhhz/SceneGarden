package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2018/1/3.
 *
 * @date: 2018/1/3
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 首页轮播图
 */
public class Poster implements Parcelable {

    private String id;
    private String imgUrl;
    /**
     * delFlag : 0
     * createTime : 1517110826000
     * updateTime : 1517233275000
     * createBy : u2897
     * updateBy : u2897
     * state : publish
     * ids : null
     * name : banner1
     * jumpUrl : https://www.baidu.com
     * bannerPath : http://p28t2n2ld.bkt.clouddn.com/02b92c3870ad5c49e787957a273f93c003.jpg
     * sort : 1
     * remark : banner1
     */

    private String delFlag;
    private long createTime;
    private long updateTime;
    private String createBy;
    private String updateBy;
    private String state;
    private String ids;
    private String name;
    private String jumpUrl;
    private String bannerPath;
    private int sort;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public Poster() {
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.imgUrl);
        dest.writeString(this.delFlag);
        dest.writeLong(this.createTime);
        dest.writeLong(this.updateTime);
        dest.writeString(this.createBy);
        dest.writeString(this.updateBy);
        dest.writeString(this.state);
        dest.writeString(this.ids);
        dest.writeString(this.name);
        dest.writeString(this.jumpUrl);
        dest.writeString(this.bannerPath);
        dest.writeInt(this.sort);
        dest.writeString(this.remark);
    }

    protected Poster(Parcel in) {
        this.id = in.readString();
        this.imgUrl = in.readString();
        this.delFlag = in.readString();
        this.createTime = in.readLong();
        this.updateTime = in.readLong();
        this.createBy = in.readString();
        this.updateBy = in.readString();
        this.state = in.readString();
        this.ids = in.readString();
        this.name = in.readString();
        this.jumpUrl = in.readString();
        this.bannerPath = in.readString();
        this.sort = in.readInt();
        this.remark = in.readString();
    }

    public static final Creator<Poster> CREATOR = new Creator<Poster>() {
        @Override
        public Poster createFromParcel(Parcel source) {
            return new Poster(source);
        }

        @Override
        public Poster[] newArray(int size) {
            return new Poster[size];
        }
    };
}
