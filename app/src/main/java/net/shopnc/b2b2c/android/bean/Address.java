package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 地址实体类
 *
 * @author huting
 * @date 2016/4/11
 */
public class Address implements Serializable {
    /**
     * 收货地址编号</br>
     * 主键、自增
     */
    private Integer addressId;
    /**
     * 会员ID
     */
    private int memberId;
    /**
     * 联系人
     */
    private String realName;
    /**
     * 一级地区ID
     */
    private int areaId1;
    /**
     * 二级地区ID
     */
    private int areaId2;
    /**
     * 三级地区Id
     */
    private int areaId3;
    /**
     * 四级地区
     */
    private int areaId4;
    /**
     * 最末级地区
     */
    private int areaId;
    /**
     * 省市县(区)内容
     */
    private String areaInfo;
    /**
     * 街道地址
     */

    private String address;
    /**
     * 手机
     */

    private String mobPhone;
    /**
     * 固话
     */

    private String telPhone;
    /**
     * 是否作为默认收货地址</br>
     * 0-否 1-是
     */
    private int isDefault;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAreaId1() {
        return areaId1;
    }

    public void setAreaId1(int areaId1) {
        this.areaId1 = areaId1;
    }

    public int getAreaId2() {
        return areaId2;
    }

    public void setAreaId2(int areaId2) {
        this.areaId2 = areaId2;
    }

    public int getAreaId3() {
        return areaId3;
    }

    public void setAreaId3(int areaId3) {
        this.areaId3 = areaId3;
    }

    public int getAreaId4() {
        return areaId4;
    }

    public void setAreaId4(int areaId4) {
        this.areaId4 = areaId4;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", memberId=" + memberId +
                ", realName='" + realName + '\'' +
                ", areaId1=" + areaId1 +
                ", areaId2=" + areaId2 +
                ", areaId3=" + areaId3 +
                ", areaId4=" + areaId4 +
                ", areaId=" + areaId +
                ", areaInfo='" + areaInfo + '\'' +
                ", address='" + address + '\'' +
                ", mobPhone='" + mobPhone + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
