package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description SKU
 * @Author qyf
 *
 * Created 2016/4/20 18:51.
 */
public class Goods implements Serializable{
    /**
     * 商品SKU编号
     */
    private int goodsId;
    /**
     * 商品SPU
     */
    private int commonId;
    /**
     * 规格<br>
     * 例“红色 L”
     */
    private String goodsSpecs;
    /**
     * 规格<br>
     * 例黄色；160ml
     */
    private String goodsSpecString;
    /**
     * 完整规格<br>
     * 例“颜色：红色；尺码：L”
     */
    private String goodsFullSpecs;
    /**
     * 所选规格值编号<br>
     * 列 “1,2,3,4”
     */
    private String specValueIds;
    /**
     * 商品价格0
     */
    private BigDecimal goodsPrice0;
    /**
     * 商品价格1
     */
    private BigDecimal goodsPrice1;
    /**
     * 商品价格2
     */
    private BigDecimal goodsPrice2;
    /**
     * PC端起购价0
     */
    private BigDecimal webPrice0 = new BigDecimal(0);
    /**
     * PC端起购价1
     */
    private BigDecimal webPrice1 = new BigDecimal(0);
    /**
     * PC端起购价2
     */
    private BigDecimal webPrice2 = new BigDecimal(0);
    /**
     * PC端促销进行状态
     */
    private int webUsable = 0;
    /**
     * APP端起购价0
     */
    private BigDecimal appPrice0 = new BigDecimal(0);
    /**
     * APP端起购价1
     */
    private BigDecimal appPrice1 = new BigDecimal(0);
    /**
     * APP端起购价2
     */
    private BigDecimal appPrice2 = new BigDecimal(0);
    /**
     * APP端促销进行状态
     */
    private int appUsable = 0;
    /**
     * 微信端起购价0
     */
    private BigDecimal wechatPrice0 = new BigDecimal(0);
    /**
     * 微信端起购价1
     */
    private BigDecimal wechatPrice1 = new BigDecimal(0);
    /**
     * 微信端起购价2
     */
    private BigDecimal wechatPrice2 = new BigDecimal(0);
    /**
     * 微信端促销进行状态
     */
    private int wechatUsable = 0;
    /**
     * 限时折扣促销编号
     */
    private int promotionId = 0;
    /**
     * 促销开始时间
     */
    private String promotionStartTime;
    /**
     * 促销结束时间
     */
    private String promotionEndTime;
    /**
     * 活动状态
     */
    private int promotionState = 0;
    /**
     * 活动类型
     */
    private int promotionType = GoodsPromotionType.GENERAL;
    /**
     * 活动类型文字
     */
    private String promotionTypeText;
    /**
     * 折扣标题
     */
    private String promotionTitle;
    /**
     * 商品货号
     */
    private String goodsSerial;
    /**
     * 颜色规格值编号<br>
     * 编号为1的规格对应的规格值的编号
     */
    private int colorId;
    /**
     * 库存
     */
    private int goodsStorage;
    /**
     * 图片名称
     */
    private String imageName;
    /**
     * 图片路径
     */
    private String imageSrc;

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

    public String getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(String goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public String getGoodsFullSpecs() {
        return goodsFullSpecs;
    }

    public void setGoodsFullSpecs(String goodsFullSpecs) {
        this.goodsFullSpecs = goodsFullSpecs;
    }

    public String getSpecValueIds() {
        return specValueIds;
    }

    public void setSpecValueIds(String specValueIds) {
        this.specValueIds = specValueIds;
    }

    public BigDecimal getGoodsPrice0() {
        return goodsPrice0;
    }

    public void setGoodsPrice0(BigDecimal goodsPrice0) {
        this.goodsPrice0 = goodsPrice0;
    }

    public BigDecimal getGoodsPrice1() {
        return goodsPrice1;
    }

    public void setGoodsPrice1(BigDecimal goodsPrice1) {
        this.goodsPrice1 = goodsPrice1;
    }

    public BigDecimal getGoodsPrice2() {
        return goodsPrice2;
    }

    public void setGoodsPrice2(BigDecimal goodsPrice2) {
        this.goodsPrice2 = goodsPrice2;
    }

    public BigDecimal getWebPrice0() {
        return webPrice0;
    }

    public void setWebPrice0(BigDecimal webPrice0) {
        this.webPrice0 = webPrice0;
    }

    public BigDecimal getWebPrice1() {
        return webPrice1;
    }

    public void setWebPrice1(BigDecimal webPrice1) {
        this.webPrice1 = webPrice1;
    }

    public BigDecimal getWebPrice2() {
        return webPrice2;
    }

    public void setWebPrice2(BigDecimal webPrice2) {
        this.webPrice2 = webPrice2;
    }

    public int getWebUsable() {
        return webUsable;
    }

    public void setWebUsable(int webUsable) {
        this.webUsable = webUsable;
    }

    public BigDecimal getAppPrice0() {
        return appPrice0;
    }

    public void setAppPrice0(BigDecimal appPrice0) {
        this.appPrice0 = appPrice0;
    }

    public BigDecimal getAppPrice1() {
        return appPrice1;
    }

    public void setAppPrice1(BigDecimal appPrice1) {
        this.appPrice1 = appPrice1;
    }

    public BigDecimal getAppPrice2() {
        return appPrice2;
    }

    public void setAppPrice2(BigDecimal appPrice2) {
        this.appPrice2 = appPrice2;
    }

    public int getAppUsable() {
        return appUsable;
    }

    public void setAppUsable(int appUsable) {
        this.appUsable = appUsable;
    }

    public BigDecimal getWechatPrice0() {
        return wechatPrice0;
    }

    public void setWechatPrice0(BigDecimal wechatPrice0) {
        this.wechatPrice0 = wechatPrice0;
    }

    public BigDecimal getWechatPrice1() {
        return wechatPrice1;
    }

    public void setWechatPrice1(BigDecimal wechatPrice1) {
        this.wechatPrice1 = wechatPrice1;
    }

    public BigDecimal getWechatPrice2() {
        return wechatPrice2;
    }

    public void setWechatPrice2(BigDecimal wechatPrice2) {
        this.wechatPrice2 = wechatPrice2;
    }

    public int getWechatUsable() {
        return wechatUsable;
    }

    public void setWechatUsable(int wechatUsable) {
        this.wechatUsable = wechatUsable;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(String promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public String getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(String promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public int getPromotionState() {
        return promotionState;
    }

    public void setPromotionState(int promotionState) {
        this.promotionState = promotionState;
    }

    public int getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(int promotionType) {
        this.promotionType = promotionType;
    }

    public String getPromotionTypeText() {
        return promotionTypeText;
    }

    public void setPromotionTypeText(String promotionTypeText) {
        this.promotionTypeText = promotionTypeText;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }

    public String getGoodsSerial() {
        return goodsSerial;
    }

    public void setGoodsSerial(String goodsSerial) {
        this.goodsSerial = goodsSerial;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getGoodsStorage() {
        return goodsStorage;
    }

    public void setGoodsStorage(int goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getGoodsSpecString() {
        return goodsSpecString;
    }

    public void setGoodsSpecString(String goodsSpecString) {
        this.goodsSpecString = goodsSpecString;
    }
}
