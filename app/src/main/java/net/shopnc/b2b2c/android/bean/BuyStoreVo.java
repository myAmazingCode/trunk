package net.shopnc.b2b2c.android.bean;
import java.math.BigDecimal;
import java.util.List;

/**
 * 存放下单时单个店铺的购买信息<br/>
 * Created by hbj on 2015/11/27.
 */
public class BuyStoreVo {

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
     * 店铺下商品列表集合
     */
    private List<BuyGoodsItemVo> buyGoodsItemVoList;
    /**
     * spu 集合
     */
    private List<BuyGoodsSpuVo> buyGoodsSpuVoList;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺Id
     */
    private int storeId;
    /**
     * 店铺可用的满减送(购买第一步页面展示)
     */
    private List<Conform> conformList;
    /**
     * 店铺可用优惠券列表(购买第一步页面展示)
     */
    private List<VoucherVo> voucherVoList;
    /**
     * 店铺商品总价[不含运费、促销],在Vo内自动计算
     */
    private BigDecimal buyItemAmount = new BigDecimal(0);
    /**
     * 店铺运费
     */
    private BigDecimal freightAmount = new BigDecimal(0);
    /**
     * 线上支付/货到付款[offline/online]
     */
    private String paymentTypeCode ;
    /**
     * 是否自营
     */
    private int isOwnShop;

    public List<BuyGoodsItemVo> getBuyGoodsItemVoList() {
        return buyGoodsItemVoList;
    }

    public void setBuyGoodsItemVoList(List<BuyGoodsItemVo> buyGoodsItemVoList) {
        this.buyGoodsItemVoList = buyGoodsItemVoList;
    }

    public List<BuyGoodsSpuVo> getBuyGoodsSpuVoList() {
        return buyGoodsSpuVoList;
    }

    public void setBuyGoodsSpuVoList(List<BuyGoodsSpuVo> buyGoodsSpuVoList) {
        this.buyGoodsSpuVoList = buyGoodsSpuVoList;
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

    public List<Conform> getConformList() {
        return conformList;
    }

    public void setConformList(List<Conform> conformList) {
        this.conformList = conformList;
    }

    public List<VoucherVo> getVoucherVoList() {
        return voucherVoList;
    }

    public void setVoucherVoList(List<VoucherVo> voucherVoList) {
        this.voucherVoList = voucherVoList;
    }

    public BigDecimal getBuyItemAmount() {
        return buyItemAmount;
    }

    public void setBuyItemAmount(BigDecimal buyItemAmount) {
        this.buyItemAmount = buyItemAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public int getIsOwnShop() {
        return isOwnShop;
    }

    public void setIsOwnShop(int isOwnShop) {
        this.isOwnShop = isOwnShop;
    }
}
