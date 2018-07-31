package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2017/1/11 11:32.
 */

public class BookBean {
    /**
     * goodsId : 107869
     * commonId : 107868
     * goodsName : 完善预售
     * storeId : 1
     * downPercent : 10
     * downPayment : 1.1
     * finalPayment : 9.9
     * totalPayment : 11
     * downTime : 2017-01-13 16:54:10
     * createTime : 2017-01-06 16:52:36
     */

    private int goodsId;
    private int commonId;
    private String goodsName;
    private int storeId;
    private int downPercent;
    private BigDecimal downPayment;
    private BigDecimal finalPayment;
    private int totalPayment;
    private String downTime;
    private String createTime;

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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getDownPercent() {
        return downPercent;
    }

    public void setDownPercent(int downPercent) {
        this.downPercent = downPercent;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getFinalPayment() {
        return finalPayment;
    }

    public void setFinalPayment(BigDecimal finalPayment) {
        this.finalPayment = finalPayment;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
