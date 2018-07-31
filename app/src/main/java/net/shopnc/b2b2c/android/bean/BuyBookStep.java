package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/1/12 10:47.
 */

public class BuyBookStep {
    private BigDecimal fullPaymentTotal;
    private int allowVat;
    private BigDecimal downPaymentTotal;
    private Address address;
    private int sellerId;
    private BuyGoodsItemVo buyGoodsItemVo;
    private int imIsOnline;
    private String buyStep;
    private String memberMobile;
    private List<Address> addressList;

    public BigDecimal getFullPaymentTotal() {
        return fullPaymentTotal;
    }

    public void setFullPaymentTotal(BigDecimal fullPaymentTotal) {
        this.fullPaymentTotal = fullPaymentTotal;
    }

    public int getAllowVat() {
        return allowVat;
    }

    public void setAllowVat(int allowVat) {
        this.allowVat = allowVat;
    }

    public BigDecimal getDownPaymentTotal() {
        return downPaymentTotal;
    }

    public void setDownPaymentTotal(BigDecimal downPaymentTotal) {
        this.downPaymentTotal = downPaymentTotal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public BuyGoodsItemVo getBuyGoodsItemVo() {
        return buyGoodsItemVo;
    }

    public void setBuyGoodsItemVo(BuyGoodsItemVo buyGoodsItemVo) {
        this.buyGoodsItemVo = buyGoodsItemVo;
    }

    public int getImIsOnline() {
        return imIsOnline;
    }

    public void setImIsOnline(int imIsOnline) {
        this.imIsOnline = imIsOnline;
    }

    public String getBuyStep() {
        return buyStep;
    }

    public void setBuyStep(String buyStep) {
        this.buyStep = buyStep;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
