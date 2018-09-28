package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by user on 2017/12/25.
 *
 * @date: 2017/12/25
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class User {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String id;
    private String delFlag;
    private long createTime;
    private long updateTime;
    private String createBy;
    private String updateBy;
    private String state;
    private Object ids;
    private String nickname;
    private String mobile;
    private String level;
    private String realname;
    private String roleId;
    private String type;
    private String province;
    private String city;
    private String area;
    private String address;
    private String email;
    private String headImg;
    private Object tagsStr;
    private String sex;
    private String remark;
    private String password;
    private String signature;
    private Object birthday;
    private Object lastLoginTime;
    private Object lastLoginIp;
    private Object perfectFlag;
    private Object designerFlag;
    private Object wxOpenid;
    private String checkStatus;
    private Object checkOpinion;
    private Object authorities;
    private Object searchRegisterTime;
    private Object validCode;
    private boolean isFollow;
    private boolean enabled;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private List<Tags> tags;

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

    public Object getIds() {
        return ids;
    }

    public void setIds(Object ids) {
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

    public Object getTagsStr() {
        return tagsStr;
    }

    public void setTagsStr(Object tagsStr) {
        this.tagsStr = tagsStr;
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

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Object getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Object lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Object getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(Object lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Object getPerfectFlag() {
        return perfectFlag;
    }

    public void setPerfectFlag(Object perfectFlag) {
        this.perfectFlag = perfectFlag;
    }

    public Object getDesignerFlag() {
        return designerFlag;
    }

    public void setDesignerFlag(Object designerFlag) {
        this.designerFlag = designerFlag;
    }

    public Object getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(Object wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Object getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(Object checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public Object getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Object authorities) {
        this.authorities = authorities;
    }

    public Object getSearchRegisterTime() {
        return searchRegisterTime;
    }

    public void setSearchRegisterTime(Object searchRegisterTime) {
        this.searchRegisterTime = searchRegisterTime;
    }

    public Object getValidCode() {
        return validCode;
    }

    public void setValidCode(Object validCode) {
        this.validCode = validCode;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "";
    }
}
