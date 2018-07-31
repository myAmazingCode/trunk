package net.shopnc.b2b2c.android.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GPS定位信息
 *
 * @author dqw
 * @Time 2015/9/14
 */
public class GpsInfo implements Parcelable {
    //经度
    private double lng;
    //纬度
    private double lat;
    //文字1

    public GpsInfo() {
    }

    public GpsInfo(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "GpsInfo{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }

    public static final Creator<GpsInfo> CREATOR = new Creator<GpsInfo>() {
        @Override
        public GpsInfo createFromParcel(Parcel parcel) {
            GpsInfo info = new GpsInfo(parcel.readDouble(),parcel.readDouble());
            return  info;
        }

        @Override
        public GpsInfo[] newArray(int i) {
            return new GpsInfo[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(lng);
        parcel.writeDouble(lat);
    }
}

