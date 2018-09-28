package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by user on 2018/2/11.
 *
 * @date: 2018/2/11
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class ShopCar implements Parcelable {

    /**
     * number : 2
     * maxNumber : 450
     * memberId : ebde742e12f44266ad268313fdf59763
     * delFlag : 0
     * updateBy : ebde742e12f44266ad268313fdf59763
     * price : 89
     * title : 花冠商品
     * defaultImage : http://p28t2n2ld.bkt.clouddn.com/timg (1)8dda8c75cde4454db25b91bc1a52d74e.jpeg
     * total : 178
     * id : 3998bd9f79724fc8ba6b78587864a88c
     * color : 红色
     * productId : 609cb5c46dac4f8e87cd024fcb4aa319
     * updateTime : 1518328607000
     * createTime : 1518328605000
     * productVersion : 3
     * createBy : ebde742e12f44266ad268313fdf59763
     * marketable : 1
     */

    private int number;
    private int maxNumber;
    private String memberId;
    private String delFlag;
    private String updateBy;
    private double price;
    private String title;
    private String defaultImage;
    private int total;
    private String id;
    private String color;
    private String productId;
    private long updateTime;
    private long createTime;
    private int productVersion;
    private String createBy;
    private String marketable;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public int getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(int productVersion) {
        this.productVersion = productVersion;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getMarketable() {
        return marketable;
    }

    public void setMarketable(String marketable) {
        this.marketable = marketable;
    }

    public ShopCar() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopCar shopCar = (ShopCar) o;
        if (TextUtils.isEmpty(id)) {
            return productId.equals(shopCar.productId);
        } else {
            return id.equals(shopCar.id);
        }
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
        dest.writeInt(this.number);
        dest.writeInt(this.maxNumber);
        dest.writeString(this.memberId);
        dest.writeString(this.delFlag);
        dest.writeString(this.updateBy);
        dest.writeDouble(this.price);
        dest.writeString(this.title);
        dest.writeString(this.defaultImage);
        dest.writeInt(this.total);
        dest.writeString(this.id);
        dest.writeString(this.color);
        dest.writeString(this.productId);
        dest.writeLong(this.updateTime);
        dest.writeLong(this.createTime);
        dest.writeInt(this.productVersion);
        dest.writeString(this.createBy);
        dest.writeString(this.marketable);
    }

    protected ShopCar(Parcel in) {
        this.number = in.readInt();
        this.maxNumber = in.readInt();
        this.memberId = in.readString();
        this.delFlag = in.readString();
        this.updateBy = in.readString();
        this.price = in.readDouble();
        this.title = in.readString();
        this.defaultImage = in.readString();
        this.total = in.readInt();
        this.id = in.readString();
        this.color = in.readString();
        this.productId = in.readString();
        this.updateTime = in.readLong();
        this.createTime = in.readLong();
        this.productVersion = in.readInt();
        this.createBy = in.readString();
        this.marketable = in.readString();
    }

    public static final Creator<ShopCar> CREATOR = new Creator<ShopCar>() {
        @Override
        public ShopCar createFromParcel(Parcel source) {
            return new ShopCar(source);
        }

        @Override
        public ShopCar[] newArray(int size) {
            return new ShopCar[size];
        }
    };
}
