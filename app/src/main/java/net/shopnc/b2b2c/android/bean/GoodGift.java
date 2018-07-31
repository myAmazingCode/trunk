package net.shopnc.b2b2c.android.bean;

import net.shopnc.b2b2c.android.common.Common;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/9/5 10:46.
 */
public class GoodGift {
    private int giftId;
    private int goodsId;
    private int commonId;
    private int giftNum;
    private int giftType;
    private int itemId;
    private int itemCommonId;
    private String goodsName;
    private String unitName;
    private String goodsFullSpecs;
    private String imageSrc;

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
    }

    public int getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(int giftNum) {
        this.giftNum = giftNum;
    }

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemCommonId() {
        return itemCommonId;
    }

    public void setItemCommonId(int itemCommonId) {
        this.itemCommonId = itemCommonId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsFullSpecs() {
        return goodsFullSpecs;
    }

    public void setGoodsFullSpecs(String goodsFullSpecs) {
        this.goodsFullSpecs = goodsFullSpecs;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getShowText() {
        return goodsName + " " + (Common.isEmpty(goodsFullSpecs) ? "" : goodsFullSpecs) + " x " + giftNum + unitName + " (赠完为止)";
    }

    public String getCartShowText() {
        return /*"[赠品]" + */goodsName + " " + (Common.isEmpty(goodsFullSpecs) ? "" : goodsFullSpecs);
    }

    @Override
    public String toString() {
        return "GoodGift{" +
                "giftId=" + giftId +
                ", goodsId=" + goodsId +
                ", commonId=" + commonId +
                ", giftNum=" + giftNum +
                ", giftType=" + giftType +
                ", itemId=" + itemId +
                ", itemCommonId=" + itemCommonId +
                ", goodsName='" + goodsName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", goodsFullSpecs='" + goodsFullSpecs + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                '}';
    }
}
