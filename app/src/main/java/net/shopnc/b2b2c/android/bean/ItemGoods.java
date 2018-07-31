package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 商品项目实体
 */
public class ItemGoods implements Serializable{

    private int commonId;
    private String goodsName;
    private double appPriceMin;
    private double wechatPriceMin;
    private String imageUrl;

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getAppPriceMin() {
        return appPriceMin;
    }

    public void setAppPriceMin(double appPriceMin) {
        this.appPriceMin = appPriceMin;
    }

    public double getWechatPriceMin() {
        return wechatPriceMin;
    }

    public void setWechatPriceMin(double wechatPriceMin) {
        this.wechatPriceMin = wechatPriceMin;
    }
}
