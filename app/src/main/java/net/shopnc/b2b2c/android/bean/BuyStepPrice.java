package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description 确认订单页相关金额数据
 * @Author qyf
 *
 * Created 2016/5/10 17:05.
 */
public class BuyStepPrice {
    private int storeId;
    private BigDecimal priceGood;
    private BigDecimal freight;
    private BigDecimal freightOrigin;
    private BigDecimal priceVoucher=new BigDecimal(0);   //只记录当前优惠券金额
    private BigDecimal priceConform=new BigDecimal(0);   //只记录当前优惠金额
    private int voucherId;   //优惠券id
    private int conformId;   //优惠id
    private String receiverMessage;   //留言

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getPriceGood() {
        return priceGood;
    }

    public void setPriceGood(BigDecimal priceGood) {
        this.priceGood = priceGood;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getFreightOrigin() {
        return freightOrigin;
    }

    public void setFreightOrigin(BigDecimal freightOrigin) {
        this.freightOrigin = freightOrigin;
    }

    public BigDecimal getPriceVoucher() {
        return priceVoucher;
    }

    public void setPriceVoucher(BigDecimal priceVoucher) {
        this.priceVoucher = priceVoucher;
    }

    public BigDecimal getPriceConform() {
        return priceConform;
    }

    public void setPriceConform(BigDecimal priceConform) {
        this.priceConform = priceConform;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getConformId() {
        return conformId;
    }

    public void setConformId(int conformId) {
        this.conformId = conformId;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void setReceiverMessage(String receiverMessage) {
        this.receiverMessage = receiverMessage;
    }
}
