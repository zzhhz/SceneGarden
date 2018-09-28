package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by user on 2018/1/11.
 *
 * @date: 2018/1/11
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 商品
 */
public class Product extends HomeItemPoster implements Parcelable {


    /**
     * id : 0c3713e206494959804a511aad00b32d
     * delFlag : 0
     * createTime : 1517507759000
     * updateTime : 1517584775000
     * createBy : u2897
     * updateBy : u2897
     * state : null
     * ids : null
     * title : 爱优尚带果金桔树盆栽
     * price : 118.34
     * store : 0
     * categoryId : 003
     * remark : null
     * marketable : 1
     * defaultImage : http://p28t2n2ld.bkt.clouddn.com/TB1ugNmX4OMSKJjSZFlXXXqQFXa_!!0-item_picfff3cd5313cf403ebb5e88aed9d52d75.jpg
     * mobileDefaultImage : http://p28t2n2ld.bkt.clouddn.com/TB1q2mrfBfM8KJjSZFOXXXr5XXa_!!0-item_pic.jpg_430x430q90afeb2ab05cf747f9b2834e3c00459abf.jpg
     * supplierId : null
     * origin : 山东济南
     * version : 36
     * proSupplierId : null
     * content :
     * color : 黄色
     * shelvesTime : 2018-02-06 至 2018-03-06
     * flowerTime : 2018-02-16 至 2018-03-12
     * norms : {"株高":"11","冠高":"2","冠径":"3","根茎":"2","盆径":"1"}
     * botanyName : 黄色的盆景
     * botanyVariety : 品种1
     * botanyCategory : 植物类别1
     * botanyFunction : 植物功能1
     * fitPosition : 植物位置1
     * fitRegion : 植物区域1
     * fitSeason : 植物季节2
     * productCategory : 这是产品类别1
     * productStyle : 产品风格2
     * moveDifficulty : 难度2
     * isRecommend : 1
     * platformPrice : null
     * pageInfo : null
     * num : 0
     * attachList : null
     * supplierName : null
     */

    private String title;
    private double price;
    private int store;
    private String categoryId;
    private String remark;
    private String marketable;
    private String defaultImage;
    private String mobileDefaultImage;
    private String supplierId;
    private String origin;
    private int version;
    private String proSupplierId;
    private String content;
    private String color;
    private String shelvesTime;
    private String flowerTime;
    private String norms;
    private String botanyName;
    private String botanyVariety;
    private String botanyCategory;
    private String botanyFunction;
    private String fitPosition;
    private String fitRegion;
    private String fitSeason;
    private String productCategory;
    private String productStyle;
    private String moveDifficulty;
    private String isRecommend;
    private String platformPrice;
    private String pageInfo;
    private int num;
    private String attachList;
    private String supplierName;
    private List<String> images;
    private String categoryNamePtah;
    private int isSelected;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public Product() {
    }

    public String getCategoryNamePtah() {
        return categoryNamePtah;
    }

    public void setCategoryNamePtah(String categoryNamePtah) {
        this.categoryNamePtah = categoryNamePtah;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMarketable() {
        return marketable;
    }

    public void setMarketable(String marketable) {
        this.marketable = marketable;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getMobileDefaultImage() {
        return mobileDefaultImage;
    }

    public void setMobileDefaultImage(String mobileDefaultImage) {
        this.mobileDefaultImage = mobileDefaultImage;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShelvesTime() {
        return shelvesTime;
    }

    public void setShelvesTime(String shelvesTime) {
        this.shelvesTime = shelvesTime;
    }

    public String getFlowerTime() {
        return flowerTime;
    }

    public void setFlowerTime(String flowerTime) {
        this.flowerTime = flowerTime;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getBotanyName() {
        return botanyName;
    }

    public void setBotanyName(String botanyName) {
        this.botanyName = botanyName;
    }

    public String getBotanyVariety() {
        return botanyVariety;
    }

    public void setBotanyVariety(String botanyVariety) {
        this.botanyVariety = botanyVariety;
    }

    public String getBotanyCategory() {
        return botanyCategory;
    }

    public void setBotanyCategory(String botanyCategory) {
        this.botanyCategory = botanyCategory;
    }

    public String getBotanyFunction() {
        return botanyFunction;
    }

    public void setBotanyFunction(String botanyFunction) {
        this.botanyFunction = botanyFunction;
    }

    public String getFitPosition() {
        return fitPosition;
    }

    public void setFitPosition(String fitPosition) {
        this.fitPosition = fitPosition;
    }

    public String getFitRegion() {
        return fitRegion;
    }

    public void setFitRegion(String fitRegion) {
        this.fitRegion = fitRegion;
    }

    public String getFitSeason() {
        return fitSeason;
    }

    public void setFitSeason(String fitSeason) {
        this.fitSeason = fitSeason;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductStyle() {
        return productStyle;
    }

    public void setProductStyle(String productStyle) {
        this.productStyle = productStyle;
    }

    public String getMoveDifficulty() {
        return moveDifficulty;
    }

    public void setMoveDifficulty(String moveDifficulty) {
        this.moveDifficulty = moveDifficulty;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProSupplierId() {
        return proSupplierId;
    }

    public void setProSupplierId(String proSupplierId) {
        this.proSupplierId = proSupplierId;
    }

    public String getPlatformPrice() {
        return platformPrice;
    }

    public void setPlatformPrice(String platformPrice) {
        this.platformPrice = platformPrice;
    }

    public String getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getAttachList() {
        return attachList;
    }

    public void setAttachList(String attachList) {
        this.attachList = attachList;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String getImageUrl() {
        return this.mobileDefaultImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.title);
        dest.writeDouble(this.price);
        dest.writeInt(this.store);
        dest.writeString(this.categoryId);
        dest.writeString(this.remark);
        dest.writeString(this.marketable);
        dest.writeString(this.defaultImage);
        dest.writeString(this.mobileDefaultImage);
        dest.writeString(this.supplierId);
        dest.writeString(this.origin);
        dest.writeInt(this.version);
        dest.writeString(this.proSupplierId);
        dest.writeString(this.content);
        dest.writeString(this.color);
        dest.writeString(this.shelvesTime);
        dest.writeString(this.flowerTime);
        dest.writeString(this.norms);
        dest.writeString(this.botanyName);
        dest.writeString(this.botanyVariety);
        dest.writeString(this.botanyCategory);
        dest.writeString(this.botanyFunction);
        dest.writeString(this.fitPosition);
        dest.writeString(this.fitRegion);
        dest.writeString(this.fitSeason);
        dest.writeString(this.productCategory);
        dest.writeString(this.productStyle);
        dest.writeString(this.moveDifficulty);
        dest.writeString(this.isRecommend);
        dest.writeString(this.platformPrice);
        dest.writeString(this.pageInfo);
        dest.writeInt(this.num);
        dest.writeString(this.attachList);
        dest.writeString(this.supplierName);
        dest.writeStringList(this.images);
    }

    protected Product(Parcel in) {
        super(in);
        this.title = in.readString();
        this.price = in.readDouble();
        this.store = in.readInt();
        this.categoryId = in.readString();
        this.remark = in.readString();
        this.marketable = in.readString();
        this.defaultImage = in.readString();
        this.mobileDefaultImage = in.readString();
        this.supplierId = in.readString();
        this.origin = in.readString();
        this.version = in.readInt();
        this.proSupplierId = in.readString();
        this.content = in.readString();
        this.color = in.readString();
        this.shelvesTime = in.readString();
        this.flowerTime = in.readString();
        this.norms = in.readString();
        this.botanyName = in.readString();
        this.botanyVariety = in.readString();
        this.botanyCategory = in.readString();
        this.botanyFunction = in.readString();
        this.fitPosition = in.readString();
        this.fitRegion = in.readString();
        this.fitSeason = in.readString();
        this.productCategory = in.readString();
        this.productStyle = in.readString();
        this.moveDifficulty = in.readString();
        this.isRecommend = in.readString();
        this.platformPrice = in.readString();
        this.pageInfo = in.readString();
        this.num = in.readInt();
        this.attachList = in.readString();
        this.supplierName = in.readString();
        this.images = in.createStringArrayList();
    }

    @Override
    public String toString() {
        if (this != null) {
            return JSON.toJSONString(this);
        }
        return "Product{}";
    }
}
