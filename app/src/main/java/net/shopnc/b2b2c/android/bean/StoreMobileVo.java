package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 店铺背景图和轮播图
 *
 * @author huting
 * @date 2016/4/14
 */
public class StoreMobileVo implements Serializable{
    private String storeMobileId;
    private String picUrl;
    private String picType;
    private String smallPicType;
    private String fullPicUrl;
    private String url;

    private String bigPicUrl;
    private String smallPicType1;
    private String smallPicType2;
    private String smallPicType3;
    private String smallPicType4;
    private String smallPicType5;

    public String getSmallPicType1() {
        return smallPicType1;
    }
    public void setSmallPicType1(String smallPicType1) {
        this.smallPicType1 = smallPicType1;
    }
    public String getSmallPicType2() {
        return smallPicType2;
    }
    public void setSmallPicType2(String smallPicType2) {
        this.smallPicType2 = smallPicType2;
    }
    public String getSmallPicType3() {
        return smallPicType3;
    }
    public void setSmallPicType3(String smallPicType3) {
        this.smallPicType3 = smallPicType3;
    }
    public String getSmallPicType4() {
        return smallPicType4;
    }
    public void setSmallPicType4(String smallPicType4) {
        this.smallPicType4 = smallPicType4;
    }
    public String getSmallPicType5() {
        return smallPicType5;
    }
    public void setSmallPicType5(String smallPicType5) {
        this.smallPicType5 = smallPicType5;
    }
    public String getBigPicUrl() {
        return bigPicUrl;
    }
    public void setBigPicUrl(String bigPicUrl) {
        this.bigPicUrl = bigPicUrl;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getFullPicUrl() {
        return fullPicUrl;
    }
    public void setFullPicUrl(String fullPicUrl) {
        this.fullPicUrl = fullPicUrl;
    }
    public String getStoreMobileId() {
        return storeMobileId;
    }
    public void setStoreMobileId(String storeMobileId) {
        this.storeMobileId = storeMobileId;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getPicType() {
        return picType;
    }
    public void setPicType(String picType) {
        this.picType = picType;
    }
    public String getSmallPicType() {
        return smallPicType;
    }
    public void setSmallPicType(String smallPicType) {
        this.smallPicType = smallPicType;
    }

    @Override
    public String toString() {
        return "StoreMobileVo{" +
                "storeMobileId='" + storeMobileId + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", picType='" + picType + '\'' +
                ", smallPicType='" + smallPicType + '\'' +
                ", fullPicUrl='" + fullPicUrl + '\'' +
                ", url='" + url + '\'' +
                ", bigPicUrl='" + bigPicUrl + '\'' +
                ", smallPicType1='" + smallPicType1 + '\'' +
                ", smallPicType2='" + smallPicType2 + '\'' +
                ", smallPicType3='" + smallPicType3 + '\'' +
                ", smallPicType4='" + smallPicType4 + '\'' +
                ", smallPicType5='" + smallPicType5 + '\'' +
                '}';
    }
}
