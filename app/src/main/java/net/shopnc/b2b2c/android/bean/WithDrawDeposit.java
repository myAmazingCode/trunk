package net.shopnc.b2b2c.android.bean;

import android.support.annotation.Nullable;

/**
 * 提现 实体类
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.bean.WithDrawDeposit.java
 * @author: Jie
 * @date: 2016-05-29 09:14
 */
public class WithDrawDeposit {
    private static final String TAG = "WithDrawDeposit";

    /**
     * 是否有下一页
     */
    private boolean hasMore;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 提现ID
     */
    private int cashId;
    /**
     * 提现编号
     */
    private String cashSn;
    /**
     * 会员ID
     */
    private int memberId;
    /**
     * 提现金额
     */
    private double amount;
    /**
     * 收款公司,比如支付宝、建行等
     */
    private String receiveCompany;
    /**
     * 收款账号
     */
    private String receiveAccount;
    /**
     * 开户人姓名
     */
    private String receiveUser;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 付款时间
     */
    @Nullable
    private String payTime;
    /**
     * 提现状态 0未处理 1提现成功 2提现失败
     */
    private int state;
    /**
     * 拒绝提现理由
     */
    private String refuseReason;
    /**
     * 提现状态文本
     */
    private String stateText;

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

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

    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
    }

    public String getCashSn() {
        return cashSn;
    }

    public void setCashSn(String cashSn) {
        this.cashSn = cashSn;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
}
