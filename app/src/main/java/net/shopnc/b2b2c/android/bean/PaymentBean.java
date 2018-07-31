package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.List;

/**
 * @Description
 * @Author qyf
 *
 * Created 2016/5/17 14:57.
 */
public class PaymentBean {
    private int payId;
    private BigDecimal payAmount;
    private List<PaymentListItem> paymentList;
    private int allowPredeposit;
    private BigDecimal predepositAmount;

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public List<PaymentListItem> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<PaymentListItem> paymentList) {
        this.paymentList = paymentList;
    }

    public int getAllowPredeposit() {
        return allowPredeposit;
    }

    public void setAllowPredeposit(int allowPredeposit) {
        this.allowPredeposit = allowPredeposit;
    }

    public BigDecimal getPredepositAmount() {
        return predepositAmount;
    }

    public void setPredepositAmount(BigDecimal predepositAmount) {
        this.predepositAmount = predepositAmount;
    }

    @Override
    public String toString() {
        return "PaymentBean{" +
                "payId=" + payId +
                ", payAmount=" + payAmount +
                ", paymentList=" + paymentList +
                ", allowPredeposit=" + allowPredeposit +
                ", predepositAmount=" + predepositAmount +
                '}';
    }
}
