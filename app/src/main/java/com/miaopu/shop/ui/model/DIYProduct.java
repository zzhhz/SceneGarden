package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liu on 2018/3/9.
 */

public class DIYProduct extends Product implements Parcelable {
    /**
     * potDiameter : 11
     * moveDifficulty : 困难
     * fitSeason : 夏
     * botanyCategory : 植物类别2
     * flowerTime : 3-6月
     * origin : 山东青州
     * productStyle : 产品风格1
     * plantHeight : 11
     * id : 8bc013953a994545ada01fd671e8f237
     * color : 铁海棠
     * version : 10.0
     * crownHeight : 12
     * productCategory : 这是产品类别2
     * botanyVariety : 品种1
     * categoryNamePtah : 其他分类/其他
     * botanyFunction : 植物功能2
     * categoryId : 003
     * crownDiameter : 11
     * store : 877
     * rootDiameter : 12
     * price : 11.23
     * fitRegion : 植物区域1
     * fitPosition : 植物位置1
     * defaultImage : http://p28t2n2ld.bkt.clouddn.com/TB2tR61xr4npuFjSZFmXXXl4FXa_!!142493863d8aaa4fc91684715bf44a0f7c0e71a1f.jpg
     * title : 海棠花 盆栽四季开花植物室内净化空气绿植虎刺梅花卉盆栽海棠花
     * updateTime : 1520229562787
     * createTime : 1519787824000
     * shelvesTime : 3-5月
     * marketable : 1
     * notAnalyzedCategoryName : 其他
     * categoryName : 其他
     */

    private String potDiameter;
    private String moveDifficulty;
    private String fitSeason;
    private String botanyCategory;
    private String flowerTime;
    private String origin;
    private String productStyle;
    private String plantHeight;
    private String id;
    private String color;
    private String crownHeight;
    private String productCategory;
    private String botanyVariety;
    private String categoryNamePtah;
    private String botanyFunction;
    private String categoryId;
    private String crownDiameter;
    private int    store;
    private String rootDiameter;
    private double price;
    private String fitRegion;
    private String fitPosition;
    private String defaultImage;
    private String title;
    private long   updateTime;
    private long   createTime;
    private String shelvesTime;
    private String marketable;
    private String notAnalyzedCategoryName;
    private String categoryName;
    private int flagNum = 0;
    private int maxNumber;

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getFlagNum() {
        if (flagNum < 1){
            flagNum = 1;
        }
        return flagNum;
    }

    public DIYProduct(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public void setFlagNum(int flagNum) {
        this.flagNum = flagNum;
    }

    public String getPotDiameter() {
        return potDiameter;
    }

    public void setPotDiameter(String potDiameter) {
        this.potDiameter = potDiameter;
    }

    public String getMoveDifficulty() {
        return moveDifficulty;
    }

    public void setMoveDifficulty(String moveDifficulty) {
        this.moveDifficulty = moveDifficulty;
    }

    public String getFitSeason() {
        return fitSeason;
    }

    public void setFitSeason(String fitSeason) {
        this.fitSeason = fitSeason;
    }

    public String getBotanyCategory() {
        return botanyCategory;
    }

    public void setBotanyCategory(String botanyCategory) {
        this.botanyCategory = botanyCategory;
    }

    public String getFlowerTime() {
        return flowerTime;
    }

    public void setFlowerTime(String flowerTime) {
        this.flowerTime = flowerTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProductStyle() {
        return productStyle;
    }

    public void setProductStyle(String productStyle) {
        this.productStyle = productStyle;
    }

    public String getPlantHeight() {
        return plantHeight;
    }

    public void setPlantHeight(String plantHeight) {
        this.plantHeight = plantHeight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCrownHeight() {
        return crownHeight;
    }

    public void setCrownHeight(String crownHeight) {
        this.crownHeight = crownHeight;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getBotanyVariety() {
        return botanyVariety;
    }

    public void setBotanyVariety(String botanyVariety) {
        this.botanyVariety = botanyVariety;
    }

    public String getCategoryNamePtah() {
        return categoryNamePtah;
    }

    public void setCategoryNamePtah(String categoryNamePtah) {
        this.categoryNamePtah = categoryNamePtah;
    }

    public String getBotanyFunction() {
        return botanyFunction;
    }

    public void setBotanyFunction(String botanyFunction) {
        this.botanyFunction = botanyFunction;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCrownDiameter() {
        return crownDiameter;
    }

    public void setCrownDiameter(String crownDiameter) {
        this.crownDiameter = crownDiameter;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public String getRootDiameter() {
        return rootDiameter;
    }

    public void setRootDiameter(String rootDiameter) {
        this.rootDiameter = rootDiameter;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFitRegion() {
        return fitRegion;
    }

    public void setFitRegion(String fitRegion) {
        this.fitRegion = fitRegion;
    }

    public String getFitPosition() {
        return fitPosition;
    }

    public void setFitPosition(String fitPosition) {
        this.fitPosition = fitPosition;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getShelvesTime() {
        return shelvesTime;
    }

    public void setShelvesTime(String shelvesTime) {
        this.shelvesTime = shelvesTime;
    }

    public String getMarketable() {
        return marketable;
    }

    public void setMarketable(String marketable) {
        this.marketable = marketable;
    }

    public String getNotAnalyzedCategoryName() {
        return notAnalyzedCategoryName;
    }

    public void setNotAnalyzedCategoryName(String notAnalyzedCategoryName) {
        this.notAnalyzedCategoryName = notAnalyzedCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public DIYProduct() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DIYProduct)) return false;

        DIYProduct that = (DIYProduct) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.potDiameter);
        dest.writeString(this.moveDifficulty);
        dest.writeString(this.fitSeason);
        dest.writeString(this.botanyCategory);
        dest.writeString(this.flowerTime);
        dest.writeString(this.origin);
        dest.writeString(this.productStyle);
        dest.writeString(this.plantHeight);
        dest.writeString(this.id);
        dest.writeString(this.color);
        dest.writeString(this.crownHeight);
        dest.writeString(this.productCategory);
        dest.writeString(this.botanyVariety);
        dest.writeString(this.categoryNamePtah);
        dest.writeString(this.botanyFunction);
        dest.writeString(this.categoryId);
        dest.writeString(this.crownDiameter);
        dest.writeInt(this.store);
        dest.writeString(this.rootDiameter);
        dest.writeDouble(this.price);
        dest.writeString(this.fitRegion);
        dest.writeString(this.fitPosition);
        dest.writeString(this.defaultImage);
        dest.writeString(this.title);
        dest.writeLong(this.updateTime);
        dest.writeLong(this.createTime);
        dest.writeString(this.shelvesTime);
        dest.writeString(this.marketable);
        dest.writeString(this.notAnalyzedCategoryName);
        dest.writeString(this.categoryName);
    }

    protected DIYProduct(Parcel in) {
        super(in);
        this.potDiameter = in.readString();
        this.moveDifficulty = in.readString();
        this.fitSeason = in.readString();
        this.botanyCategory = in.readString();
        this.flowerTime = in.readString();
        this.origin = in.readString();
        this.productStyle = in.readString();
        this.plantHeight = in.readString();
        this.id = in.readString();
        this.color = in.readString();
        this.crownHeight = in.readString();
        this.productCategory = in.readString();
        this.botanyVariety = in.readString();
        this.categoryNamePtah = in.readString();
        this.botanyFunction = in.readString();
        this.categoryId = in.readString();
        this.crownDiameter = in.readString();
        this.store = in.readInt();
        this.rootDiameter = in.readString();
        this.price = in.readDouble();
        this.fitRegion = in.readString();
        this.fitPosition = in.readString();
        this.defaultImage = in.readString();
        this.title = in.readString();
        this.updateTime = in.readLong();
        this.createTime = in.readLong();
        this.shelvesTime = in.readString();
        this.marketable = in.readString();
        this.notAnalyzedCategoryName = in.readString();
        this.categoryName = in.readString();
    }

    public static final Creator<DIYProduct> CREATOR = new Creator<DIYProduct>() {
        @Override
        public DIYProduct createFromParcel(Parcel source) {
            return new DIYProduct(source);
        }

        @Override
        public DIYProduct[] newArray(int size) {
            return new DIYProduct[size];
        }
    };
}
