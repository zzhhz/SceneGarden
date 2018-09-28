package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by liuwenzheng on 2018/2/25.
 */

public class Contractor {
    /**
     * nickname : 小豆子
     * remark : 123
     * defaultName : 小豆子
     * enabled : false
     * province : 安徽省/芜湖市/弋江区
     * updateBy : u2897
     * accountNonLocked : false
     * id : 0e9de708ae0846a58054446914a0ed7f
     * type : 0103
     * realname : 小豆子
     * address : 123123
     * credentialsNonExpired : false
     * checkStatus : 0103_success
     * mobile : 15555555551
     * email : 173813143@qq.com
     * accountNonExpired : false
     * delFlag : 0
     * headImg : /resource/img/Avatar%20sample%20324.png
     * username : 15555555551
     * updateTime : 1517726041000
     * createTime : 1517725287000
     * sex : 1
     */

    private String nickname;
    private String remark;
    private String defaultName;
    private boolean enabled;
    private String province;
    private String updateBy;
    private boolean accountNonLocked;
    private String id;
    private String type;
    private String realname;
    private String address;
    private boolean credentialsNonExpired;
    private String checkStatus;
    private String mobile;
    private String email;
    private boolean accountNonExpired;
    private String delFlag;
    private String headImg;
    private String username;
    private long updateTime;
    private long createTime;
    private String sex;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
