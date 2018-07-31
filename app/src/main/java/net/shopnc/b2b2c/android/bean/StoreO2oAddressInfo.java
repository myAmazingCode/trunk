package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商家分店地址信息Bean
 *
 * @author dqw
 * @date 2015/9/11
 */
public class StoreO2oAddressInfo {
    private String name;
    private String address;
    private String phone;
    private String busInfo;
    private String city;
    private String district;
    private String lng;
    private String lat;
    private String distance;

    public StoreO2oAddressInfo(String name, String address, String phone, String busInfo, String city, String district, String lng, String lat, String distance) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.busInfo = busInfo;
        this.city = city;
        this.district = district;
        this.lng = lng;
        this.lat = lat;
        this.distance = distance;
    }

    public static ArrayList<StoreO2oAddressInfo> newInstanceList(String json) {
        ArrayList<StoreO2oAddressInfo> list = new ArrayList<StoreO2oAddressInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String name = obj.optString("name_info");
                String address = obj.optString("address_info");
                String phone = obj.optString("phone_info");
                String busInfo = obj.optString("bus_info");
                String city = obj.optString("city");
                String district = obj.optString("district");
                String lng = obj.optString("lng");
                String lat = obj.optString("lat");
                String distance = obj.optString("distance");
                list.add(new StoreO2oAddressInfo(name,address,phone,busInfo,city,district,lng,lat,distance));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusInfo() {
        return busInfo;
    }

    public void setBusInfo(String busInfo) {
        this.busInfo = busInfo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "StoreO2oAddressInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", busInfo='" + busInfo + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}


