package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 *
 * @author huting
 * @date 2016/4/19
 */
public class CartStoreVo {
    /**
     * sku
     */
    private List<CartItemVo> cartItemVoList;
    /**
     * spu
     */
    private List<CartSpuVo> cartSpuVoList;

    /**
     * 增加 优惠套装
     */
    private List<CartBundlingVo> cartBundlingVoList;

    public List<CartBundlingVo> getCartBundlingVoList() {
        return cartBundlingVoList;
    }

    public void setCartBundlingVoList(List<CartBundlingVo> cartBundlingVoList) {
        this.cartBundlingVoList = cartBundlingVoList;
    }

    /**
     * voucherTemplateVo
     */
    private List<VoucherTemplateVo> voucherTemplateVoList;

    private List<Conform> conformList;

    /**
     * 购物车总金额
     */
    private BigDecimal cartAmount;
    private String storeName;
    private int storeId;
    /**
     * 购物车商品种类(sku)数量
     */
    private int buyNum;

    public List<CartItemVo> getCartItemVoList() {
        return cartItemVoList;
    }

    public void setCartItemVoList(List<CartItemVo> cartItemVoList) {
        this.cartItemVoList = cartItemVoList;
    }

    public List<CartSpuVo> getCartSpuVoList() {
        return cartSpuVoList;
    }

    public void setCartSpuVoList(List<CartSpuVo> cartSpuVoList) {
        this.cartSpuVoList = cartSpuVoList;
    }

    public List<VoucherTemplateVo> getVoucherTemplateVoList() {
        return voucherTemplateVoList;
    }

    public void setVoucherTemplateVoList(List<VoucherTemplateVo> voucherTemplateVoList) {
        this.voucherTemplateVoList = voucherTemplateVoList;
    }

    public BigDecimal getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(BigDecimal cartAmount) {
        this.cartAmount = cartAmount;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public List<Conform> getConformList() {
        return conformList;
    }

    public void setConformList(List<Conform> conformList) {
        this.conformList = conformList;
    }

    @Override
    public String toString() {
        return "CartStoreVo{" +
                "cartItemVoList=" + cartItemVoList +
                ", cartSpuVoList=" + cartSpuVoList +
                ", voucherTemplateVoList=" + voucherTemplateVoList +
                ", cartAmount=" + cartAmount +
                ", storeName='" + storeName + '\'' +
                ", storeId=" + storeId +
                ", buyNum=" + buyNum +
                '}';
    }
}
