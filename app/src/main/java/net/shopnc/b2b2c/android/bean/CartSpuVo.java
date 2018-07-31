package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * sku  vo
 *
 * @author huting
 * @date 2016/4/19
 */
public class CartSpuVo {
    /**
     * sku list
     */
    private List<CartItemVo> cartItemVoList;
    /**
     * spu ID
     */
    private int commonId;
    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 图片路径
     */
    private String imageSrc;
    /**
     * 销售模式
     */
    private int goodsModal;
    /**
     * 起购量0
     */
    private int batchNum0;
    /**
     * 起购量1
     */
    private int batchNum1;
    /**
     * 起购量2
     */
    private int batchNum2;
    /**
     * 起购量0  终点
     */
    private int batchNum0End = 0;
    /**
     * 起购量1  终点
     */
    private int batchNum1End = 0;
    /**
     * 商品状态
     */
    private int goodsStatus;

    public List<CartItemVo> getCartItemVoList() {
        return cartItemVoList;
    }

    public void setCartItemVoList(List<CartItemVo> cartItemVoList) {
        this.cartItemVoList = cartItemVoList;
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

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getGoodsModal() {
        return goodsModal;
    }

    public void setGoodsModal(int goodsModal) {
        this.goodsModal = goodsModal;
    }

    public int getBatchNum0() {
        return batchNum0;
    }

    public void setBatchNum0(int batchNum0) {
        this.batchNum0 = batchNum0;
    }

    public int getBatchNum1() {
        return batchNum1;
    }

    public void setBatchNum1(int batchNum1) {
        this.batchNum1 = batchNum1;
    }

    public int getBatchNum2() {
        return batchNum2;
    }

    public void setBatchNum2(int batchNum2) {
        this.batchNum2 = batchNum2;
    }

    public int getBatchNum0End() {
        return batchNum0End;
    }

    public void setBatchNum0End(int batchNum0End) {
        this.batchNum0End = batchNum0End;
    }

    public int getBatchNum1End() {
        return batchNum1End;
    }

    public void setBatchNum1End(int batchNum1End) {
        this.batchNum1End = batchNum1End;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    @Override
    public String toString() {
        return "CartSpuVo{" +
                "cartItemVoList=" + cartItemVoList +
                ", commonId=" + commonId +
                ", goodsName='" + goodsName + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                ", goodsModal=" + goodsModal +
                ", batchNum0=" + batchNum0 +
                ", batchNum1=" + batchNum1 +
                ", batchNum2=" + batchNum2 +
                ", batchNum0End=" + batchNum0End +
                ", batchNum1End=" + batchNum1End +
                ", goodsStatus=" + goodsStatus +
                '}';
    }
}
