package com.miaopu.shop.ui.model;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by ZZH on 2018/2/25.
 *
 * @Date: 2018/2/25
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 商品页，筛选条件
 */
public class Condition2 {

    /**
     * categoryName :
     * title : 其他分类
     * echo_categoryId : 39a6d89db983451688dafe050676c6da
     * fitSeason :
     * fitRegion :
     * plantHeights : ["",""]
     * crownHeights : ["",""]
     * crownDiameters : ["",""]
     * rootDiameters : ["",""]
     * potDiameters : ["",""]
     */

    private String categoryName;
    private String title;
    private String echo_categoryId;
    private String fitSeason;
    private String fitRegion;
    private List<String> plantHeights;
    private List<String> crownHeights;
    private List<String> crownDiameters;
    private List<String> rootDiameters;
    private List<String> potDiameters;
    private List<String> multPrice;

    public List<String> getMultPrice() {
        return multPrice;
    }

    public void setMultPrice(List<String> multPrice) {
        this.multPrice = multPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEcho_categoryId() {
        return echo_categoryId;
    }

    public void setEcho_categoryId(String echo_categoryId) {
        this.echo_categoryId = echo_categoryId;
    }

    public String getFitSeason() {
        return fitSeason;
    }

    public void setFitSeason(String fitSeason) {
        this.fitSeason = fitSeason;
    }

    public String getFitRegion() {
        return fitRegion;
    }

    public void setFitRegion(String fitRegion) {
        this.fitRegion = fitRegion;
    }

    public List<String> getPlantHeights() {
        return plantHeights;
    }

    public void setPlantHeights(List<String> plantHeights) {
        this.plantHeights = plantHeights;
    }

    public List<String> getCrownHeights() {
        return crownHeights;
    }

    public void setCrownHeights(List<String> crownHeights) {
        this.crownHeights = crownHeights;
    }

    public List<String> getCrownDiameters() {
        return crownDiameters;
    }

    public void setCrownDiameters(List<String> crownDiameters) {
        this.crownDiameters = crownDiameters;
    }

    public List<String> getRootDiameters() {
        return rootDiameters;
    }

    public void setRootDiameters(List<String> rootDiameters) {
        this.rootDiameters = rootDiameters;
    }

    public List<String> getPotDiameters() {
        return potDiameters;
    }

    public void setPotDiameters(List<String> potDiameters) {
        this.potDiameters = potDiameters;
    }

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        } else {
            return "Condition2{}";
        }
    }
}
