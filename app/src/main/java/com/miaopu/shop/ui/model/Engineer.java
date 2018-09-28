package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwenzheng on 2018/2/25.
 */

public class Engineer implements Parcelable {

    /**
     * ownerAddress : 安徽省/合肥市/瑶海区
     * designerId : ebde742e12f44266ad268313fdf59763
     * contractorId : 0e9de708ae0846a58054446914a0ed7f
     * ownerMobile : 123
     * contractor : {"nickname":"小豆子","remark":"123","defaultName":"小豆子","enabled":false,"province":"安徽省/芜湖市/弋江区","updateBy":"u2897","accountNonLocked":false,"id":"0e9de708ae0846a58054446914a0ed7f","type":"0103","realname":"小豆子","isFollow":false,"address":"123123","credentialsNonExpired":false,"checkStatus":"0103_success","mobile":"15555555551","email":"173813143@qq.com","accountNonExpired":false,"delFlag":"0","headImg":"/resource/img/Avatar%20sample%20324.png","username":"15555555551","updateTime":1517726041000,"createTime":1517725287000,"sex":"1"}
     * remark : 123
     * designs : [{"delFlag":"0","updateBy":"ebde742e12f44266ad268313fdf59763","id":"468f3ae8170843b29f55ded249276b7b","engineeringId":"41e35bfdda8b450f996cfc7e443c9278","updateTime":1519481385000,"createTime":1519481385000,"designId":"50ea28c2877a4303b508396d04754334","createBy":"ebde742e12f44266ad268313fdf59763"},{"delFlag":"0","updateBy":"ebde742e12f44266ad268313fdf59763","id":"76d1f125f66f45aaafd77774db7ccb0b","engineeringId":"41e35bfdda8b450f996cfc7e443c9278","updateTime":1519481385000,"createTime":1519481385000,"designId":"71f237dea66d4512b0e3951ef8eacfba","createBy":"ebde742e12f44266ad268313fdf59763"}]
     * state : 2
     * delFlag : 0
     * updateBy : 0e9de708ae0846a58054446914a0ed7f
     * ownerName : 123123
     * title : 123
     * id : 41e35bfdda8b450f996cfc7e443c9278
     * updateTime : 1519481665000
     * createTime : 1519481385000
     * createBy : ebde742e12f44266ad268313fdf59763
     */

    private String ownerAddress;
    private String designerId;
    private String contractorId;
    private String ownerMobile;
    private ContractorBean contractor;
    private String remark;
    private String state;
    private String delFlag;
    private String updateBy;
    private String ownerName;
    private String title;
    private String id;
    private long updateTime;
    private long createTime;
    private String createBy;
    private List<DesignsBean> designs;
    private String designerText;
    private String contractorText;



    public String getContractorText() {
        return contractorText;
    }

    public void setContractorText(String contractorText) {
        this.contractorText = contractorText;
    }

    public String getDesignerText() {
        return designerText;
    }

    public void setDesignerText(String designerText) {
        this.designerText = designerText;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getDesignerId() {
        return designerId;
    }

    public void setDesignerId(String designerId) {
        this.designerId = designerId;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public ContractorBean getContractor() {
        return contractor;
    }

    public void setContractor(ContractorBean contractor) {
        this.contractor = contractor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public List<DesignsBean> getDesigns() {
        return designs;
    }

    public void setDesigns(List<DesignsBean> designs) {
        this.designs = designs;
    }

    public static class ContractorBean implements Parcelable {
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
         * isFollow : false
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
        private boolean isFollow;
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

        public boolean isIsFollow() {
            return isFollow;
        }

        public void setIsFollow(boolean isFollow) {
            this.isFollow = isFollow;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.nickname);
            dest.writeString(this.remark);
            dest.writeString(this.defaultName);
            dest.writeByte(this.enabled ? (byte) 1 : (byte) 0);
            dest.writeString(this.province);
            dest.writeString(this.updateBy);
            dest.writeByte(this.accountNonLocked ? (byte) 1 : (byte) 0);
            dest.writeString(this.id);
            dest.writeString(this.type);
            dest.writeString(this.realname);
            dest.writeByte(this.isFollow ? (byte) 1 : (byte) 0);
            dest.writeString(this.address);
            dest.writeByte(this.credentialsNonExpired ? (byte) 1 : (byte) 0);
            dest.writeString(this.checkStatus);
            dest.writeString(this.mobile);
            dest.writeString(this.email);
            dest.writeByte(this.accountNonExpired ? (byte) 1 : (byte) 0);
            dest.writeString(this.delFlag);
            dest.writeString(this.headImg);
            dest.writeString(this.username);
            dest.writeLong(this.updateTime);
            dest.writeLong(this.createTime);
            dest.writeString(this.sex);
        }

        public ContractorBean() {
        }

        protected ContractorBean(Parcel in) {
            this.nickname = in.readString();
            this.remark = in.readString();
            this.defaultName = in.readString();
            this.enabled = in.readByte() != 0;
            this.province = in.readString();
            this.updateBy = in.readString();
            this.accountNonLocked = in.readByte() != 0;
            this.id = in.readString();
            this.type = in.readString();
            this.realname = in.readString();
            this.isFollow = in.readByte() != 0;
            this.address = in.readString();
            this.credentialsNonExpired = in.readByte() != 0;
            this.checkStatus = in.readString();
            this.mobile = in.readString();
            this.email = in.readString();
            this.accountNonExpired = in.readByte() != 0;
            this.delFlag = in.readString();
            this.headImg = in.readString();
            this.username = in.readString();
            this.updateTime = in.readLong();
            this.createTime = in.readLong();
            this.sex = in.readString();
        }

        public static final Parcelable.Creator<ContractorBean> CREATOR = new Parcelable.Creator<ContractorBean>() {
            @Override
            public ContractorBean createFromParcel(Parcel source) {
                return new ContractorBean(source);
            }

            @Override
            public ContractorBean[] newArray(int size) {
                return new ContractorBean[size];
            }
        };
    }

    public static class DesignsBean implements Parcelable {
        /**
         * delFlag : 0
         * updateBy : ebde742e12f44266ad268313fdf59763
         * id : 468f3ae8170843b29f55ded249276b7b
         * engineeringId : 41e35bfdda8b450f996cfc7e443c9278
         * updateTime : 1519481385000
         * createTime : 1519481385000
         * designId : 50ea28c2877a4303b508396d04754334
         * createBy : ebde742e12f44266ad268313fdf59763
         */

        private String delFlag;
        private String updateBy;
        private String id;
        private String engineeringId;
        private long updateTime;
        private long createTime;
        private String designId;
        private String createBy;

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEngineeringId() {
            return engineeringId;
        }

        public void setEngineeringId(String engineeringId) {
            this.engineeringId = engineeringId;
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

        public String getDesignId() {
            return designId;
        }

        public void setDesignId(String designId) {
            this.designId = designId;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.delFlag);
            dest.writeString(this.updateBy);
            dest.writeString(this.id);
            dest.writeString(this.engineeringId);
            dest.writeLong(this.updateTime);
            dest.writeLong(this.createTime);
            dest.writeString(this.designId);
            dest.writeString(this.createBy);
        }

        public DesignsBean() {
        }

        protected DesignsBean(Parcel in) {
            this.delFlag = in.readString();
            this.updateBy = in.readString();
            this.id = in.readString();
            this.engineeringId = in.readString();
            this.updateTime = in.readLong();
            this.createTime = in.readLong();
            this.designId = in.readString();
            this.createBy = in.readString();
        }

        public static final Creator<DesignsBean> CREATOR = new Creator<DesignsBean>() {
            @Override
            public DesignsBean createFromParcel(Parcel source) {
                return new DesignsBean(source);
            }

            @Override
            public DesignsBean[] newArray(int size) {
                return new DesignsBean[size];
            }
        };
    }

    public Engineer() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ownerAddress);
        dest.writeString(this.designerId);
        dest.writeString(this.contractorId);
        dest.writeString(this.ownerMobile);
        dest.writeParcelable(this.contractor, flags);
        dest.writeString(this.remark);
        dest.writeString(this.state);
        dest.writeString(this.delFlag);
        dest.writeString(this.updateBy);
        dest.writeString(this.ownerName);
        dest.writeString(this.title);
        dest.writeString(this.id);
        dest.writeLong(this.updateTime);
        dest.writeLong(this.createTime);
        dest.writeString(this.createBy);
        dest.writeTypedList(this.designs);
        dest.writeString(this.designerText);
        dest.writeString(this.contractorText);
    }

    protected Engineer(Parcel in) {
        this.ownerAddress = in.readString();
        this.designerId = in.readString();
        this.contractorId = in.readString();
        this.ownerMobile = in.readString();
        this.contractor = in.readParcelable(ContractorBean.class.getClassLoader());
        this.remark = in.readString();
        this.state = in.readString();
        this.delFlag = in.readString();
        this.updateBy = in.readString();
        this.ownerName = in.readString();
        this.title = in.readString();
        this.id = in.readString();
        this.updateTime = in.readLong();
        this.createTime = in.readLong();
        this.createBy = in.readString();
        this.designs = in.createTypedArrayList(DesignsBean.CREATOR);
        this.designerText = in.readString();
        this.contractorText = in.readString();
    }

    public static final Creator<Engineer> CREATOR = new Creator<Engineer>() {
        @Override
        public Engineer createFromParcel(Parcel source) {
            return new Engineer(source);
        }

        @Override
        public Engineer[] newArray(int size) {
            return new Engineer[size];
        }
    };
}
