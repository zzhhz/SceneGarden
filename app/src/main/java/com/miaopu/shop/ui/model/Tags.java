package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2018/1/16.
 *
 * @date: 2018/1/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 标签
 */
public class Tags implements Parcelable {
    private String id;
    private String delFlag;
    private long createTime;
    private long updateTime;
    private String createBy;
    private String updateBy;
    private String state;
    private String targetId;
    private String dicId;
    private String dicValue;
    private String label;
    private String value;
    private String code;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public Tags() {
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
        dest.writeString(this.targetId);
        dest.writeString(this.dicId);
        dest.writeString(this.dicValue);
        dest.writeString(this.label);
        dest.writeString(this.value);
        dest.writeString(this.code);
    }

    protected Tags(Parcel in) {
        this.id = in.readString();
        this.delFlag = in.readString();
        this.createTime = in.readLong();
        this.updateTime = in.readLong();
        this.createBy = in.readString();
        this.updateBy = in.readString();
        this.state = in.readString();
        this.targetId = in.readString();
        this.dicId = in.readString();
        this.dicValue = in.readString();
        this.label = in.readString();
        this.value = in.readString();
        this.code = in.readString();
    }

    public static final Creator<Tags> CREATOR = new Creator<Tags>() {
        @Override
        public Tags createFromParcel(Parcel source) {
            return new Tags(source);
        }

        @Override
        public Tags[] newArray(int size) {
            return new Tags[size];
        }
    };
}
