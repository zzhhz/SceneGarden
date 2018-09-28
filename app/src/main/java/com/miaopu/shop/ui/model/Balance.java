package com.miaopu.shop.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by user on 2018/2/24.
 *
 * @date: 2018/2/24
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class Balance implements Parcelable {
    private List<ShopCar> items;
    private double totalAmount;
    private boolean isChange;

    public List<ShopCar> getItems() {
        return items;
    }

    public void setItems(List<ShopCar> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Balance() {
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.items);
        dest.writeDouble(this.totalAmount);
        dest.writeByte(this.isChange ? (byte) 1 : (byte) 0);
    }

    protected Balance(Parcel in) {
        this.items = in.createTypedArrayList(ShopCar.CREATOR);
        this.totalAmount = in.readDouble();
        this.isChange = in.readByte() != 0;
    }

    public static final Creator<Balance> CREATOR = new Creator<Balance>() {
        @Override
        public Balance createFromParcel(Parcel source) {
            return new Balance(source);
        }

        @Override
        public Balance[] newArray(int size) {
            return new Balance[size];
        }
    };
}
