package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/4 8:53.
 */
public class RefundDetailVo {
    private int refundId;

    private int ordersId;

    private String batchNo;

    private BigDecimal refundAmount;

    private BigDecimal payAmount;

    private BigDecimal pdAmount;

    private BigDecimal rcbAmount;

    private String refundCode;

    private int refundState;

    private String addTime;

    private String payTime;

    private String paymentName;

    private String refundStateText;

    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPdAmount() {
        return pdAmount;
    }

    public void setPdAmount(BigDecimal pdAmount) {
        this.pdAmount = pdAmount;
    }

    public BigDecimal getRcbAmount() {
        return rcbAmount;
    }

    public void setRcbAmount(BigDecimal rcbAmount) {
        this.rcbAmount = rcbAmount;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public int getRefundState() {
        return refundState;
    }

    public void setRefundState(int refundState) {
        this.refundState = refundState;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getRefundStateText() {
        return refundStateText;
    }

    public void setRefundStateText(String refundStateText) {
        this.refundStateText = refundStateText;
    }
}
