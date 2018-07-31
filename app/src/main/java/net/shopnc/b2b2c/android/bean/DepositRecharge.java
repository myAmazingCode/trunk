package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Timestamp;

/**
 * 充值明细实体类
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.bean.DepositRecharge.java
 * @author: Jie
 * @date: 2016-05-27 17:37
 */
public class DepositRecharge implements Serializable{


    boolean hasMore;
    int totalPage;
    int rechargeId;
    String rechargeSn;
    double amount;
    String paymentCode;
    String paymentName;
    String addTime;
    int payState;
    String payTime;
    String payStateText;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(int rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getRechargeSn() {
        return rechargeSn;
    }

    public void setRechargeSn(String rechargeSn) {
        this.rechargeSn = rechargeSn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayStateText() {
        return payStateText;
    }

    public void setPayStateText(String payStateText) {
        this.payStateText = payStateText;
    }
}
