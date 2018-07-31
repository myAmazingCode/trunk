package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/1/17 10:15.
 */

public class BookOrderBean {
    private String bookAmount;
    private String bookCancelTime;
    private String bookOperate;
    private String bookPhone;
    private String bookState;
    private int bookStep;
    private String bookStepName;
    private BigDecimal depositAmount;
    private String endTime;
    private long ordersId;
    private String ordersSn;
    private String outOrdersSn;
    private long paySn;
    private String paymentClientType;
    private String paymentCode;
    private String paymentName;
    private String paymentTime;
    private BigDecimal predepositAmount;
    private BigDecimal realPayAmount;

    public String getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(String bookAmount) {
        this.bookAmount = bookAmount;
    }

    public String getBookCancelTime() {
        return bookCancelTime;
    }

    public void setBookCancelTime(String bookCancelTime) {
        this.bookCancelTime = bookCancelTime;
    }

    public String getBookOperate() {
        return bookOperate;
    }

    public void setBookOperate(String bookOperate) {
        this.bookOperate = bookOperate;
    }

    public String getBookPhone() {
        return bookPhone;
    }

    public void setBookPhone(String bookPhone) {
        this.bookPhone = bookPhone;
    }

    public String getBookState() {
        return bookState;
    }

    public void setBookState(String bookState) {
        this.bookState = bookState;
    }

    public int getBookStep() {
        return bookStep;
    }

    public void setBookStep(int bookStep) {
        this.bookStep = bookStep;
    }

    public String getBookStepName() {
        return bookStepName;
    }

    public void setBookStepName(String bookStepName) {
        this.bookStepName = bookStepName;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(long ordersId) {
        this.ordersId = ordersId;
    }

    public String getOrdersSn() {
        return ordersSn;
    }

    public void setOrdersSn(String ordersSn) {
        this.ordersSn = ordersSn;
    }

    public String getOutOrdersSn() {
        return outOrdersSn;
    }

    public void setOutOrdersSn(String outOrdersSn) {
        this.outOrdersSn = outOrdersSn;
    }

    public long getPaySn() {
        return paySn;
    }

    public void setPaySn(long paySn) {
        this.paySn = paySn;
    }

    public String getPaymentClientType() {
        return paymentClientType;
    }

    public void setPaymentClientType(String paymentClientType) {
        this.paymentClientType = paymentClientType;
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

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public BigDecimal getPredepositAmount() {
        return predepositAmount;
    }

    public void setPredepositAmount(BigDecimal predepositAmount) {
        this.predepositAmount = predepositAmount;
    }

    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }
}
