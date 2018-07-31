package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * @Description 提交订单页实体
 * @Author qyf
 *
 * Created 2016/4/29 18:08.
 */
public class BuyStep {
    private int allowVat;       //发票 :只有所有商品都支持增值税发票才提供增值税发票 0否/1是
    private Address address;
    private int allowOffline;   //是否出现货到付款选项 1–是 0–否
    private List<BuyStoreVo> buyStoreVoList;
    private int isCart;
    private int isExistTrys;

    public BuyStep(int allowVat, Address address, int allowOffline, List<BuyStoreVo> buyStoreVoList, int isCart, int isExistTrys) {
        this.allowVat = allowVat;
        this.address = address;
        this.allowOffline = allowOffline;
        this.buyStoreVoList = buyStoreVoList;
        this.isCart = isCart;
        this.isExistTrys = isExistTrys;
    }

    public int getAllowVat() {
        return allowVat;
    }

    public void setAllowVat(int allowVat) {
        this.allowVat = allowVat;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAllowOffline() {
        return allowOffline;
    }

    public void setAllowOffline(int allowOffline) {
        this.allowOffline = allowOffline;
    }

    public List<BuyStoreVo> getBuyStoreVoList() {
        return buyStoreVoList;
    }

    public void setBuyStoreVoList(List<BuyStoreVo> buyStoreVoList) {
        this.buyStoreVoList = buyStoreVoList;
    }

    public int getIsCart() {
        return isCart;
    }

    public void setIsCart(int isCart) {
        this.isCart = isCart;
    }

    public int getIsExistTrys() {
        return isExistTrys;
    }

    public void setIsExistTrys(int isExistTrys) {
        this.isExistTrys = isExistTrys;
    }

}
