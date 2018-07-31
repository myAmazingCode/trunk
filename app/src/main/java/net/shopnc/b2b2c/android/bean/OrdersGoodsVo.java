package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description 订单商品
 * @Author qyf
 * <p>
 * Created 2016/5/11 18:54.
 */
public class OrdersGoodsVo {
    /**
     * 订单商品编号
     */
    private int ordersGoodsId;
    /**
     * 订单ID
     */
    private int ordersId;
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
     * 商品(购买时的)单价
     */
    private BigDecimal goodsPrice;
    /**
     * (该商品)实付支付总金额
     */
    private BigDecimal goodsPayAmount;
    /**
     * 购买数量
     */
    private int buyNum;
    /**
     * 商品图片
     */
    private String goodsImage;
    /**
     * 商品图片带URL
     */
    private String imageSrc;
    /**
     * 商品类型</br>
     */
    private int goodsType = 0;
    /**
     * 店铺ID
     */
    private int storeId;
    /**
     * 会员ID
     */
    private int memberId;
    /**
     * 佣金比例
     */
    private int commissionRate;
    /**
     * 商品分类ID
     */
    private int categoryId;
    /**
     * 完整规格
     */
    private String goodsFullSpecs;
    /**
     * 佣金金额
     */
    private BigDecimal commissionAmount;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 下单时间
     */
    private String createTime;
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
     * cj[ 商品是否显示退款]
     * 1.显示 ， 2 不显示
     */
    private int showRefund = 0;

    /**
     * 已有退款记录时显示退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 已有退款记录时显示是否显示"查看退款详情"按钮
     */
    private int showRefundInfo = 0;
    /**
     * 已有退款记录时显示显示"查看退款还是退货详情"按钮
     */
    private int refundType;

    /**
     * 退款id
     */
    private int refundId;

    /**
     * 退款单号
     */
    private long refundSn;
    /**
     * 商品原价
     */
    private BigDecimal basePrice;
    /**
     * 促销价比原价节省金额(商品级)
     */
    private BigDecimal savePrice;
    /**
     * 计量单位
     */
    private String unitName;
    /**
     * 折扣标题
     */
    private String promotionTitle = "";

    /**
     * 商品投诉id
     */
    private int complainId;

    public int getOrdersGoodsId() {
        return ordersGoodsId;
    }

    public void setOrdersGoodsId(int ordersGoodsId) {
        this.ordersGoodsId = ordersGoodsId;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
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

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsPayAmount() {
        return goodsPayAmount;
    }

    public void setGoodsPayAmount(BigDecimal goodsPayAmount) {
        this.goodsPayAmount = goodsPayAmount;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(int commissionRate) {
        this.commissionRate = commissionRate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsFullSpecs() {
        return goodsFullSpecs;
    }

    public void setGoodsFullSpecs(String goodsFullSpecs) {
        this.goodsFullSpecs = goodsFullSpecs;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public int getShowRefund() {
        return showRefund;
    }

    public void setShowRefund(int showRefund) {
        this.showRefund = showRefund;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public int getShowRefundInfo() {
        return showRefundInfo;
    }

    public void setShowRefundInfo(int showRefundInfo) {
        this.showRefundInfo = showRefundInfo;
    }

    public int getRefundType() {
        return refundType;
    }

    public void setRefundType(int refundType) {
        this.refundType = refundType;
    }

    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }

    public long getRefundSn() {
        return refundSn;
    }

    public void setRefundSn(long refundSn) {
        this.refundSn = refundSn;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }

    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }
}
