package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by liu on 2018/2/8.
 */

public class DictArea {


    /**
     * id : 204e8d7ad02f44a8a83de3d93392a667
     * delFlag : 0
     * createTime : 1516609481000
     * updateTime : 1516609481000
     * createBy : u2897
     * updateBy : u2897
     * state : null
     * ids : null
     * pid : 2ec0da86c2324db98c49b1b623586edb
     * parentPath : -1,2ec0da86c2324db98c49b1b623586edb
     * code : huadong
     * label : 华东
     * value : huadong
     * remark : null
     * orders : 999
     * level : null
     * leaf : null
     * children : null
     */

    private String id;
    private String delFlag;
    private long createTime;
    private long updateTime;
    private String createBy;
    private String updateBy;
    private Object state;
    private Object ids;
    private String pid;
    private String parentPath;
    private String code;
    private String label;
    private String value;
    private Object remark;
    private int orders;
    private Object level;
    private Object leaf;
    private Object children;

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "";
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

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getIds() {
        return ids;
    }

    public void setIds(Object ids) {
        this.ids = ids;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public Object getLevel() {
        return level;
    }

    public void setLevel(Object level) {
        this.level = level;
    }

    public Object getLeaf() {
        return leaf;
    }

    public void setLeaf(Object leaf) {
        this.leaf = leaf;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }
}
