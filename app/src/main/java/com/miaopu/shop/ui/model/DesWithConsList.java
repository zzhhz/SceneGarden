package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2018/2/9.
 */

public class DesWithConsList implements Parcelable{

    /**
     * id : 12
     * delFlag : 0
     * createTime : null
     * updateTime : 1516538351000
     * createBy : null
     * updateBy : u2897
     * state : null
     * ids : null
     * nickname : 设计师2
     * mobile : 12
     * level : 5
     * realname : 12
     * roleId : null
     * type : 0102
     * province : null
     * city : null
     * area : null
     * address : null
     * email : null
     * headImg : null
     * tags : []
     * sex : null
     * remark : 可牛逼了偷偷的告诉你... 我的签名可牛逼了偷偷的告诉你... 我的签名可牛逼了偷偷的告诉你... 我的签名可牛逼了偷偷的告诉你... 我的签名可牛逼了偷偷的告诉你...
     * password : 123qwe
     * signature : 可牛逼了偷偷的告诉你... 我的签名可牛逼了偷偷的告诉你... 我的签名可牛逼了偷偷的告诉你... 我的签名可牛逼了偷偷的告诉你... 我的签名可牛逼了偷偷的告诉你...
     * birthday : null
     * lastLoginTime : null
     * lastLoginIp : null
     * perfectFlag : null
     * designerFlag : null
     * wxOpenid : null
     * checkStatus : pass
     * checkOpinion : 测试12用户审核通过了
     * authorities : null
     * searchRegisterTime : null
     * validCode : null
     * isFollow : null
     * username : 12
     * enabled : false
     * accountNonExpired : false
     * accountNonLocked : false
     * credentialsNonExpired : false
     */

    private String id;
    private String  delFlag;
    private long  createTime;
    private long    updateTime;
    private String  createBy;
    private String  updateBy;
    private String  state;
    private String  ids;
    private String  nickname;
    private String  mobile;
    private String  level;
    private String  realname;
    private String  roleId;
    private String  type;
    private String  province;
    private String  city;
    private String  area;
    private String  address;
    private String  email;
    private String  headImg;
    private String  sex;
    private String  remark;
    private String  password;
    private String  signature;
    private String  birthday;
    private String  lastLoginTime;
    private String  lastLoginIp;
    private String  perfectFlag;
    private String  designerFlag;
    private String  wxOpenid;
    private String  checkStatus;
    private String  checkOpinion;
    private String  authorities;
    private String  searchRegisterTime;
    private String  validCode;
    private boolean  isFollow;
    private String  username;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;


    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "DesWithConsList{}";
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getPerfectFlag() {
        return perfectFlag;
    }

    public void setPerfectFlag(String perfectFlag) {
        this.perfectFlag = perfectFlag;
    }

    public String getDesignerFlag() {
        return designerFlag;
    }

    public void setDesignerFlag(String designerFlag) {
        this.designerFlag = designerFlag;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getSearchRegisterTime() {
        return searchRegisterTime;
    }

    public void setSearchRegisterTime(String searchRegisterTime) {
        this.searchRegisterTime = searchRegisterTime;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
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
        dest.writeString(this.nickname);
        dest.writeString(this.mobile);
        dest.writeString(this.level);
        dest.writeString(this.realname);
        dest.writeString(this.roleId);
        dest.writeString(this.type);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.area);
        dest.writeString(this.address);
        dest.writeString(this.email);
        dest.writeString(this.headImg);
        dest.writeString(this.sex);
        dest.writeString(this.remark);
        dest.writeString(this.password);
        dest.writeString(this.signature);
        dest.writeString(this.birthday);
        dest.writeString(this.lastLoginTime);
        dest.writeString(this.lastLoginIp);
        dest.writeString(this.perfectFlag);
        dest.writeString(this.designerFlag);
        dest.writeString(this.wxOpenid);
        dest.writeString(this.checkStatus);
        dest.writeString(this.checkOpinion);
        dest.writeString(this.authorities);
        dest.writeString(this.searchRegisterTime);
        dest.writeString(this.validCode);
        dest.writeByte(this.isFollow ? (byte) 1 : (byte) 0);
        dest.writeString(this.username);
        dest.writeByte(this.enabled ? (byte) 1 : (byte) 0);
        dest.writeByte(this.accountNonExpired ? (byte) 1 : (byte) 0);
        dest.writeByte(this.accountNonLocked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.credentialsNonExpired ? (byte) 1 : (byte) 0);
    }

    public DesWithConsList() {
    }

    protected DesWithConsList(Parcel in) {
        this.id = in.readString();
        this.delFlag = in.readString();
        this.createTime = in.readLong();
        this.updateTime = in.readLong();
        this.createBy = in.readString();
        this.updateBy = in.readString();
        this.state = in.readString();
        this.ids = in.readString();
        this.nickname = in.readString();
        this.mobile = in.readString();
        this.level = in.readString();
        this.realname = in.readString();
        this.roleId = in.readString();
        this.type = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.area = in.readString();
        this.address = in.readString();
        this.email = in.readString();
        this.headImg = in.readString();
        this.sex = in.readString();
        this.remark = in.readString();
        this.password = in.readString();
        this.signature = in.readString();
        this.birthday = in.readString();
        this.lastLoginTime = in.readString();
        this.lastLoginIp = in.readString();
        this.perfectFlag = in.readString();
        this.designerFlag = in.readString();
        this.wxOpenid = in.readString();
        this.checkStatus = in.readString();
        this.checkOpinion = in.readString();
        this.authorities = in.readString();
        this.searchRegisterTime = in.readString();
        this.validCode = in.readString();
        this.isFollow = in.readByte() != 0;
        this.username = in.readString();
        this.enabled = in.readByte() != 0;
        this.accountNonExpired = in.readByte() != 0;
        this.accountNonLocked = in.readByte() != 0;
        this.credentialsNonExpired = in.readByte() != 0;
    }

    public static final Creator<DesWithConsList> CREATOR = new Creator<DesWithConsList>() {
        @Override
        public DesWithConsList createFromParcel(Parcel source) {
            return new DesWithConsList(source);
        }

        @Override
        public DesWithConsList[] newArray(int size) {
            return new DesWithConsList[size];
        }
    };
}
