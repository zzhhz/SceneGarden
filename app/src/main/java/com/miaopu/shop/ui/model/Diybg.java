package com.miaopu.shop.ui.model;

/**
 * Created by liu on 2018/3/9.
 */

public class Diybg {
    /**
     * parentPath : -1,1c39a4e7d70e427e81a5362868580de8
     * pid : 1c39a4e7d70e427e81a5362868580de8
     * orders : 999
     * code : bg001
     * delFlag : 0
     * updateBy : u2897
     * id : 4824a2d61a1f4346a6c8ec788147d5e9
     * updateTime : 1520559273000
     * label : bg001
     * createTime : 1520559273000
     * value : http://hortzz.com/resource/img/demo-05.png
     * createBy : u2897
     */

    private String parentPath;
    private String pid;
    private int    orders;
    private String code;
    private String delFlag;
    private String updateBy;
    private String id;
    private long   updateTime;
    private String label;
    private long   createTime;
    private String value;
    private String createBy;

    public Diybg(String value) {
        this.value = value;
    }

    public Diybg() {
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
