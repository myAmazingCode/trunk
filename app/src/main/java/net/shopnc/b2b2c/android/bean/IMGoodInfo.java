package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/30 17:59.
 */
public class IMGoodInfo {
    private int commonId;
    private String imageName;
    private String goodsName;
    private BigDecimal webPriceMin;
    private BigDecimal appPriceMin;
    private BigDecimal wechatPriceMin;

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getWebPriceMin() {
        return webPriceMin;
    }

    public void setWebPriceMin(BigDecimal webPriceMin) {
        this.webPriceMin = webPriceMin;
    }

    public BigDecimal getAppPriceMin() {
        return appPriceMin;
    }

    public void setAppPriceMin(BigDecimal appPriceMin) {
        this.appPriceMin = appPriceMin;
    }

    public BigDecimal getWechatPriceMin() {
        return wechatPriceMin;
    }

    public void setWechatPriceMin(BigDecimal wechatPriceMin) {
        this.wechatPriceMin = wechatPriceMin;
    }
}
