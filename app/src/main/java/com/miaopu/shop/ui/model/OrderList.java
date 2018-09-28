package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by liu on 2018/2/5.
 */

public class OrderList {

    /**
     * navigatePages : 8
     * nextPage : 0
     * navigateLastPage : 1
     * hasNextPage : false
     * startRow : 1
     * size : 4
     * pageSize : 15
     * lastPage : 1
     * hasPreviousPage : false
     * pages : 1
     * navigatepageNums : [1]
     * list : [{"receiveCode":"265552","memberId":"2798a5702b4141a1bceb6cd3f8d009af","receiveAddress":"安徽省/芜湖市/鸠江区河南路","receiveMan":"王汇丰","totalAmount":369,"code":"DD20180204000443","updateBy":"2798a5702b4141a1bceb6cd3f8d009af","payTime":1517749855000,"id":"d4c3223ba67143078feccd4c31d730ff","orderItemList":[{"totalPrice":369,"evaluate":true,"price":123,"defaultImage":"http://p28t2n2ld.bkt.clouddn.com/TB2CEPHX3L8F1JjSszgXXarfpXa_!!2529851865b63e80355c7c4a29aece3b98667fa993.jpg","productNum":3,"id":"c8fea021d7ec4f15a5ecdee2f16626f3","color":"青/蓝/紫","productId":"d48e509acb9c460288bacda71ca83967","productTitle":"虎皮兰金边虎皮兰盆景植物 室内盆景盆栽 吸甲醛装修房包邮","orderCode":"DD20180204000443","productStyle":"产品风格1"}],"createBy":"2798a5702b4141a1bceb6cd3f8d009af","state":"FINISH","payStatus":"1","points":36.9,"delFlag":"0","dealAmount":369,"updateTime":1517750134000,"createTime":1517749578000,"receiveTel":"15305419871"}]
     */

    private int navigatePages;
    private int            nextPage;
    private int            navigateLastPage;
    private boolean        hasNextPage;
    private int            startRow;
    private int            size;
    private int            pageSize;
    private int            lastPage;
    private boolean        hasPreviousPage;
    private int            pages;
    private List<Integer>  navigatepageNums;
    private List<Order> list;

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        if (this != null)
        {
            return JSON.toJSONString(this);
        }
        return "OrderList{" + "navigatePages=" + navigatePages + ", nextPage=" + nextPage + ", navigateLastPage=" + navigateLastPage + ", hasNextPage=" + hasNextPage + ", startRow=" + startRow + ", size=" + size + ", pageSize=" + pageSize + ", lastPage=" + lastPage + ", hasPreviousPage=" + hasPreviousPage + ", pages=" + pages + ", navigatepageNums=" + navigatepageNums + ", list=" + list + '}';
    }
}
