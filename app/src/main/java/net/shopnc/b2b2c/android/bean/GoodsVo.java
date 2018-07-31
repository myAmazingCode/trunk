package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品
 *
 * @author huting
 * @date 2016/4/14
 */
public class GoodsVo implements Serializable {

    /**
     * 商品SKU编号
     */
    private int goodsId;
    /**
     * 商品SPU
     */
    private int commonId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品卖点
     */
    private String jingle;
    /**
     * 起购量0
     */
    private int batchNum0;
    /**
     * 起购量0  终点
     */
    private int batchNum0End;
    /**
     * 起购量1
     */
    private int batchNum1;
    /**
     * 起购点1 终点
     */
    private int batchNum1End;
    /**
     * 商品价格
     */
    private double goodsPrice;
    /**
     * 起购量2
     */
    private int batchNum2End;
    /**
     * 起购价0
     */
    private double batchPrice0;
    /**
     * 起购价1
     */
    private double batchPrice1;
    /**
     * 起购价2
     */
    private double batchPrice2;
    /**
     * 销售模式
     */
    private int goodsModal;
    /**
     * 评价数量
     */
    private Integer evaluateNum = 0;
    /**
     * 好评率
     */
    private Integer goodsRate = 100;
    /**
     * 销售数量
     */
    private int goodsSaleNum;
    /**
     * 库存
     */
    private int goodsStorage;
    /**
     * 商品状态<br>
     * 可以购买1，不可购买0
     */
    private int goodsStatus;
    /**
     * 颜色规格值编号<br>
     * 编号为1的规格对应的规格值的编号
     */
    private int colorId;
    /**
     * 所在地
     */
    private String areaInfo;
    /**
     * 店铺编号
     */
    private int storeId;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 是否自营店铺</br>
     * 0-否 1-是
     */
    private int isOwnShop;
    /**
     * 商品图
     */
    private String imageSrc;
    /**
     * 商品图片列表
     */
    private List<GoodsImage> goodsImageList;
    /**
     * 计量单位
     */
    private String unitName;
    /**
     * 赠品
     */
    private int isGift;
    /**
     * 促销编号
     */
    private int promotionId;
    /**
     * 活动类型
     */
    private int promotionType;
    /**
     * 活动类型文字
     */
    private String promotionTypeText;
    /**
     * 价格区间
     */
    private String priceRange;
    /**
     * 规格JSON
     */
    private String specJson;
    /**
     * 规格JSON
     */
    private List<SpecJsonVo> specJsonVoList = new ArrayList<>();
    /**
     * 商品编号和规格值编号JSON
     */
    private String goodsSpecValueJson;
    /**
     * 商品分类编号
     */
    private int categoryId;
    /**
     * 商品分类名称
     */
    private String categoryName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 是否推荐
     */
    private int isCommend;
    /**
     * 商品状态<br>
     * 0下架，1正常，10违规禁售
     */
    private int goodsState;
    /**
     * 违规禁售原因
     */
    private String stateRemark;
    /**
     * 审核状态<br>
     * 0未通过，1已通过，10审核中
     */
    private int goodsVerify;
    /**
     * 审核失败原因
     */
    private String verifyRemark;
    /**
     * 商品一级分类
     */
    private int categoryId1;
    /**
     * 商品二级分类
     */
    private int categoryId2;
    /**
     * 商品三级分类
     */
    private int categoryId3;
    /**
     * sku列表
     */
    private List<Goods> goodsVoList;

    private double appPriceMin;

    private int appUsable;

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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getJingle() {
        return jingle;
    }

    public void setJingle(String jingle) {
        this.jingle = jingle;
    }

    public int getBatchNum0() {
        return batchNum0;
    }

    public void setBatchNum0(int batchNum0) {
        this.batchNum0 = batchNum0;
    }

    public int getBatchNum0End() {
        return batchNum0End;
    }

    public void setBatchNum0End(int batchNum0End) {
        this.batchNum0End = batchNum0End;
    }

    public int getBatchNum1() {
        return batchNum1;
    }

    public void setBatchNum1(int batchNum1) {
        this.batchNum1 = batchNum1;
    }

    public int getBatchNum1End() {
        return batchNum1End;
    }

    public void setBatchNum1End(int batchNum1End) {
        this.batchNum1End = batchNum1End;
    }

    public double getBatchNum2() {
        return batchPrice2;
    }

    public void setBatchNum2(int batchPrice2) {
        this.batchPrice2 = batchPrice2;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public double getBatchPrice0() {
        return batchPrice0;
    }

    public void setBatchPrice0(double batchPrice0) {
        this.batchPrice0 = batchPrice0;
    }

    public double getBatchPrice1() {
        return batchPrice1;
    }

    public void setBatchPrice1(double batchPrice1) {
        this.batchPrice1 = batchPrice1;
    }

    public double getBatchPrice2() {
        return batchPrice2;
    }

    public void setBatchPrice2(double batchPrice2) {
        this.batchPrice2 = batchPrice2;
    }

    public int getGoodsModal() {
        return goodsModal;
    }

    public void setGoodsModal(int goodsModal) {
        this.goodsModal = goodsModal;
    }

    public Integer getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    public Integer getGoodsRate() {
        return goodsRate;
    }

    public void setGoodsRate(Integer goodsRate) {
        this.goodsRate = goodsRate;
    }

    public int getGoodsSaleNum() {
        return goodsSaleNum;
    }

    public void setGoodsSaleNum(int goodsSaleNum) {
        this.goodsSaleNum = goodsSaleNum;
    }

    public int getGoodsStorage() {
        return goodsStorage;
    }

    public void setGoodsStorage(int goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getIsOwnShop() {
        return isOwnShop;
    }

    public void setIsOwnShop(int isOwnShop) {
        this.isOwnShop = isOwnShop;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public List<GoodsImage> getGoodsImageList() {
        return goodsImageList;
    }

    public void setGoodsImageList(List<GoodsImage> goodsImageList) {
        this.goodsImageList = goodsImageList;
    }

    public String getSpecJson() {
        return specJson;
    }

    public void setSpecJson(String specJson) {
        this.specJson = specJson;
    }

    public String getGoodsSpecValueJson() {
        return goodsSpecValueJson;
    }

    public void setGoodsSpecValueJson(String goodsSpecValueJson) {
        this.goodsSpecValueJson = goodsSpecValueJson;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(int categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public int getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(int categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public int getCategoryId3() {
        return categoryId3;
    }

    public void setCategoryId3(int categoryId3) {
        this.categoryId3 = categoryId3;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getIsGift() {
        return isGift;
    }

    public void setIsGift(int isGift) {
        this.isGift = isGift;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
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

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public List<SpecJsonVo> getSpecJsonVoList() {
        return specJsonVoList;
    }

    public void setSpecJsonVoList(List<SpecJsonVo> specJsonVoList) {
        this.specJsonVoList = specJsonVoList;
    }

    public int getIsCommend() {
        return isCommend;
    }

    public void setIsCommend(int isCommend) {
        this.isCommend = isCommend;
    }

    public int getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(int goodsState) {
        this.goodsState = goodsState;
    }

    public String getStateRemark() {
        return stateRemark;
    }

    public void setStateRemark(String stateRemark) {
        this.stateRemark = stateRemark;
    }

    public int getGoodsVerify() {
        return goodsVerify;
    }

    public void setGoodsVerify(int goodsVerify) {
        this.goodsVerify = goodsVerify;
    }

    public String getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
    }

    public List<Goods> getGoodsVoList() {
        return goodsVoList;
    }

    public void setGoodsVoList(List<Goods> goodsVoList) {
        this.goodsVoList = goodsVoList;
    }

    public double getAppPriceMin() {
        return appPriceMin;
    }

    public void setAppPriceMin(double appPriceMin) {
        this.appPriceMin = appPriceMin;
    }

    public int getAppUsable() {
        return appUsable;
    }

    public void setAppUsable(int appUsable) {
        this.appUsable = appUsable;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "goodsId=" + goodsId +
                ", commonId=" + commonId +
                ", goodsName='" + goodsName + '\'' +
                ", jingle='" + jingle + '\'' +
                ", batchNum0=" + batchNum0 +
                ", batchNum0End=" + batchNum0End +
                ", batchNum1=" + batchNum1 +
                ", batchNum1End=" + batchNum1End +
                ", goodsPrice=" + goodsPrice +
                ", batchNum2End=" + batchNum2End +
                ", batchPrice0=" + batchPrice0 +
                ", batchPrice1=" + batchPrice1 +
                ", batchPrice2=" + batchPrice2 +
                ", goodsModal=" + goodsModal +
                ", evaluateNum=" + evaluateNum +
                ", goodsRate=" + goodsRate +
                ", goodsSaleNum=" + goodsSaleNum +
                ", goodsStorage=" + goodsStorage +
                ", goodsStatus=" + goodsStatus +
                ", colorId=" + colorId +
                ", areaInfo='" + areaInfo + '\'' +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", isOwnShop=" + isOwnShop +
                ", imageSrc='" + imageSrc + '\'' +
                ", goodsImageList=" + goodsImageList +
                ", unitName='" + unitName + '\'' +
                ", promotionId=" + promotionId +
                ", promotionType=" + promotionType +
                ", promotionTypeText='" + promotionTypeText + '\'' +
                ", priceRange='" + priceRange + '\'' +
                ", specJson='" + specJson + '\'' +
                ", specJsonVoList=" + specJsonVoList +
                ", goodsSpecValueJson='" + goodsSpecValueJson + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isCommend=" + isCommend +
                ", goodsState=" + goodsState +
                ", stateRemark='" + stateRemark + '\'' +
                ", goodsVerify=" + goodsVerify +
                ", verifyRemark='" + verifyRemark + '\'' +
                ", categoryId1=" + categoryId1 +
                ", categoryId2=" + categoryId2 +
                ", categoryId3=" + categoryId3 +
                ", goodsVoList=" + goodsVoList +
                ", appPriceMin=" + appPriceMin +
                ", appUsable=" + appUsable +
                '}';
    }
}
