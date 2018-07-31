package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * 存放下单时单个商品信息<br/>
 * Created by hbj on 2015/11/27.
 */
public class BuyGoodsItemVo {
    /**
     * 购物车ID[如果直接购买此项不赋值]
     */
    private int cartId;
    /**
     * 商品Id
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
     * 商品规格
     */
    private String goodsFullSpecs;
    /**
     * 商品单价[不同端的最后单价]
     */
    private BigDecimal goodsPrice;
    /**
     * 商品图
     */
    private String imageName;
    /**
     * 购买数量
     */
    private int buyNum;
    /**
     * 商品小计[单价 * 数量]
     */
    private BigDecimal itemAmount;
    /**
     * 商品运费
     */
    private BigDecimal goodsFreight;
    /**
     * 商品库存
     */
    private int goodsStorage;
    /**
     * 商品分类
     */
    private int categoryId;
    /**
     * 商品状态
     */
    private int goodsStatus;
    /**
     * 店铺ID
     */
    private int storeId;
    /**
     * 店铺名
     */
    private String storeName;
    /**
     * 商品库存是否足够
     */
    private int storageStatus;
    /**
     * 运费模板ID
     */
    private int freightTemplateId;
    /**
     * 图片路径
     */
    private String imageSrc;
    /**
     * 是否支持配送0否1是
     */
    private int allowSend ;
    /**
     * 商品重量
     */
    private BigDecimal freightWeight;
    /**
     * 商品体积
     */
    private BigDecimal freightVolume;

    /**
     * 一级分类
     */
    private int categoryId1;
    /**
     * 二级分类
     */
    private int categoryId2;
    /**
     * 三级分类
     */
    private int categoryId3;
    /**
     * 是否使用增值税发票
     */
    private int allowVat;
    /**
     * 是否自营
     */
    private int isOwnShop;
    /**
     * 计量单位
     */
    private String unitName;
    /**
     * 是否达到起购量
     */
    private int batchNumState ;
    /**
     * 起购量0
     */
    private int batchNum0;
    /**
     * 起购量0  终点
     */
    private int batchNum0End = 0;
    /**
     * 起购量1
     */
    private int batchNum1;
    /**
     * 起购量1  终点
     */
    private int batchNum1End = 0;
    /**
     * 起购量2
     */
    private int batchNum2;
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
    private int appUsable;
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
    private int wechatUsable;
    /**
     * 促销开始时间
     */
    private String promotionBeginTime;
    /**
     * 促销结束时间
     */
    private String promotionEndTime;
    /**
     * 销售模式
     */
    private int goodsModal;
    /**
     * SPU 主图
     */
    private String spuImageSrc;
    /**
     * 所在spu下的sku购买总量
     */
    private int spuBuyNum;
    /**
     * 参加促销
     */
    private int joinBigSale = 0;
    /**
     * 活动类型文字
     */
    private String promotionTypeText;
    /**
     * 折扣标题
     */
    private String promotionTitle;
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
     * 商品原价
     */
    private BigDecimal basePrice;
    /**
     * 促销价比原价节省金额(商品级)
     */
    private BigDecimal savePrice;
    /**
     * 商品实际支付金额
     */
    private BigDecimal payAmount = new BigDecimal(0);

    private int isGift;

    private List<GoodGift> giftVoList;

    public BookBean book;

    private int trysPostUseState;

    private int trysSendUseState;

    public BuyGoodsItemVo(){}

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsFullSpecs() {
        return goodsFullSpecs;
    }

    public void setGoodsFullSpecs(String goodsFullSpecs) {
        this.goodsFullSpecs = goodsFullSpecs;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    public BigDecimal getGoodsFreight() {
        return goodsFreight;
    }

    public void setGoodsFreight(BigDecimal goodsFreight) {
        this.goodsFreight = goodsFreight;
    }

    public int getGoodsStorage() {
        return goodsStorage;
    }

    public void setGoodsStorage(int goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
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

    public int getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(int storageStatus) {
        this.storageStatus = storageStatus;
    }

    public int getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(int freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getAllowSend() {
        return allowSend;
    }

    public void setAllowSend(int allowSend) {
        this.allowSend = allowSend;
    }

    public BigDecimal getFreightWeight() {
        return freightWeight;
    }

    public void setFreightWeight(BigDecimal freightWeight) {
        this.freightWeight = freightWeight;
    }

    public BigDecimal getFreightVolume() {
        return freightVolume;
    }

    public void setFreightVolume(BigDecimal freightVolume) {
        this.freightVolume = freightVolume;
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

    public int getAllowVat() {
        return allowVat;
    }

    public void setAllowVat(int allowVat) {
        this.allowVat = allowVat;
    }

    public int getIsOwnShop() {
        return isOwnShop;
    }

    public void setIsOwnShop(int isOwnShop) {
        this.isOwnShop = isOwnShop;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getBatchNumState() {
        return batchNumState;
    }

    public void setBatchNumState(int batchNumState) {
        this.batchNumState = batchNumState;
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

    public int getBatchNum2() {
        return batchNum2;
    }

    public void setBatchNum2(int batchNum2) {
        this.batchNum2 = batchNum2;
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

    public String getPromotionBeginTime() {
        return promotionBeginTime;
    }

    public void setPromotionBeginTime(String promotionBeginTime) {
        this.promotionBeginTime = promotionBeginTime;
    }

    public String getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(String promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public int getGoodsModal() {
        return goodsModal;
    }

    public void setGoodsModal(int goodsModal) {
        this.goodsModal = goodsModal;
    }

    public String getSpuImageSrc() {
        return spuImageSrc;
    }

    public void setSpuImageSrc(String spuImageSrc) {
        this.spuImageSrc = spuImageSrc;
    }

    public int getSpuBuyNum() {
        return spuBuyNum;
    }

    public void setSpuBuyNum(int spuBuyNum) {
        this.spuBuyNum = spuBuyNum;
    }

    public int getJoinBigSale() {
        return joinBigSale;
    }

    public void setJoinBigSale(int joinBigSale) {
        this.joinBigSale = joinBigSale;
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

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getSavePrice() {
        return savePrice;
    }

    public void setSavePrice(BigDecimal savePrice) {
        this.savePrice = savePrice;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public int getIsGift() {
        return isGift;
    }

    public void setIsGift(int isGift) {
        this.isGift = isGift;
    }

    public List<GoodGift> getGiftVoList() {
        return giftVoList;
    }

    public void setGiftVoList(List<GoodGift> giftVoList) {
        this.giftVoList = giftVoList;
    }

    public int getTrysPostUseState() {
        return trysPostUseState;
    }

    public void setTrysPostUseState(int trysPostUseState) {
        this.trysPostUseState = trysPostUseState;
    }

    public int getTrysSendUseState() {
        return trysSendUseState;
    }

    public void setTrysSendUseState(int trysSendUseState) {
        this.trysSendUseState = trysSendUseState;
    }
}
