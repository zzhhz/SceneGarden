package com.miaopu.shop.ui.model;

import java.util.List;

/**
 * Created by liu on 2018/2/9.
 */

public class MyCollection {
    /**
     * pageNum : 1
     * pageSize : 1
     * size : 1
     * startRow : 0
     * endRow : 0
     * total : 1
     * pages : 1
     * list : [{"id":"12","delFlag":"0","createTime":null,"updateTime":1516538351000,"createBy":null,"updateBy":"u2897","state":null,"ids":null,"nickname":"设计师2","mobile":"12","level":"5","realname":"12","roleId":null,"type":"0102","province":null,"city":null,"area":null,"address":null,"email":null,"headImg":null,"tags":[],"sex":null,"remark":"可牛逼了偷偷的告诉你...  我的签名可牛逼了偷偷的告诉你...  我的签名可牛逼了偷偷的告诉你...  我的签名可牛逼了偷偷的告诉你...  我的签名可牛逼了偷偷的告诉你...  ","password":"123qwe","signature":"可牛逼了偷偷的告诉你...  我的签名可牛逼了偷偷的告诉你...  我的签名可牛逼了偷偷的告诉你...  我的签名可牛逼了偷偷的告诉你...  我的签名可牛逼了偷偷的告诉你...  ","birthday":null,"lastLoginTime":null,"lastLoginIp":null,"perfectFlag":null,"designerFlag":null,"wxOpenid":null,"checkStatus":"pass","checkOpinion":"测试12用户审核通过了","authorities":null,"searchRegisterTime":null,"validCode":null,"isFollow":null,"username":"12","enabled":false,"accountNonExpired":false,"accountNonLocked":false,"credentialsNonExpired":false}]
     * prePage : 0
     * nextPage : 0
     * isFirstPage : true
     * isLastPage : true
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * firstPage : 1
     * lastPage : 1
     */

    private int pageNum;
    private int            pageSize;
    private int            size;
    private int            startRow;
    private int            endRow;
    private int            total;
    private int            pages;
    private int            prePage;
    private int            nextPage;
    private boolean        isFirstPage;
    private boolean        isLastPage;
    private boolean        hasPreviousPage;
    private boolean        hasNextPage;
    private int            navigatePages;
    private int            navigateFirstPage;
    private int            navigateLastPage;
    private int            firstPage;
    private int            lastPage;
    private List<DesWithConsList> list;
    private List<Integer>  navigatepageNums;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public List<DesWithConsList> getList() {
        return list;
    }

    public void setList(List<DesWithConsList> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
