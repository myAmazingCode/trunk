package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 店铺优惠券<br>
 * Created by zxy on 2016-03-25
 */
public class Voucher implements Serializable {

    public Voucher(String voucherTitle,BigDecimal limitAmount) {
        this.voucherTitle = voucherTitle;
        this.limitAmount = limitAmount;
    }

    public Voucher() {
    }

    /**
     * 自增编码
     */
    private int voucherId;
    /**
     * 优惠券编码
     */
    private String voucherCode = "";
    /**
     * 优惠券模版编号
     */
    private int templateId;
    /**
     * 优惠券名称
     */
    private String voucherTitle = "";
    /**
     * 优惠券描述
     */
    private String voucherDescribe = "";
    /**
     * 优惠券有效期开始时间
     */
    private Timestamp startTime;
    /**
     * 优惠券有效期结束时间
     */
    private Timestamp endTime;
    /**
     * 面额
     */
    private BigDecimal price = new BigDecimal(0);
    /**
     * 优惠券使用时的订单限额
     */
    private BigDecimal limitAmount = new BigDecimal(0);
    /**
     * 店铺编号
     */
    private int storeId = 0;
    /**
     * 店铺名称
     */
    private String storeName = "";
    /**
     * 优惠券状态 0-未用,1-已用,2-作废
     */
    private int voucherState = 0;
    /**
     * 领取时间
     */
    private Timestamp activeTime;
    /**
     * 优惠券类型 1卡密兑换 2免费领取
     */
    private int type = 0;
    /**
     * PC端是否可用
     */
    private int webUsable;
    /**
     * APP端是否可用（包括wap、ios、android）
     */
    private int appUsable;
    /**
     * 微信端是否可用
     */
    private int wechatUsable;
    /**
     * 所有者会员ID
     */
    private int memberId = 0;
    /**
     * 所有者会员名称
     */
    private String memberName = "";
    /**
     * 使用订单编号
     */
    private int ordersId = 0;
    /**
     * 使用订单sn
     */
    private long ordersSn = 0;
    /**
     * 优惠券状态文本
     */
    private String voucherStateText = "";
    /**
     * 优惠券有效期开始时间
     */
    private String startTimeText = "";
    /**
     * 优惠券有效期结束时间
     */
    private String endTimeText = "";
    /**
     * 过期状态 0未过期，1已过期
     */
    private int voucherExpiredState = 0;
    /**
     * 客户端类型标识
     */
    private String voucherUsableClientType = "";
    /**
     * 客户端类型文本
     */
    private String voucherUsableClientTypeText = "";

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getVoucherTitle() {
        return voucherTitle;
    }

    public void setVoucherTitle(String voucherTitle) {
        this.voucherTitle = voucherTitle;
    }

    public String getVoucherDescribe() {
        return voucherDescribe;
    }

    public void setVoucherDescribe(String voucherDescribe) {
        this.voucherDescribe = voucherDescribe;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getVoucherState() {
        return voucherState;
    }

    public void setVoucherState(int voucherState) {
        this.voucherState = voucherState;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWebUsable() {
        return webUsable;
    }

    public void setWebUsable(int webUsable) {
        this.webUsable = webUsable;
    }

    public int getAppUsable() {
        return appUsable;
    }

    public void setAppUsable(int appUsable) {
        this.appUsable = appUsable;
    }

    public int getWechatUsable() {
        return wechatUsable;
    }

    public void setWechatUsable(int wechatUsable) {
        this.wechatUsable = wechatUsable;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public long getOrdersSn() {
        return ordersSn;
    }

    public void setOrdersSn(long ordersSn) {
        this.ordersSn = ordersSn;
    }

    public String getVoucherStateText() {
        return voucherStateText;
    }

    public void setVoucherStateText(String voucherStateText) {
        this.voucherStateText = voucherStateText;
    }

    public String getStartTimeText() {
        return startTimeText;
    }

    public void setStartTimeText(String startTimeText) {
        this.startTimeText = startTimeText;
    }

    public String getEndTimeText() {
        return endTimeText;
    }

    public void setEndTimeText(String endTimeText) {
        this.endTimeText = endTimeText;
    }

    public int getVoucherExpiredState() {
        return voucherExpiredState;
    }

    public void setVoucherExpiredState(int voucherExpiredState) {
        this.voucherExpiredState = voucherExpiredState;
    }

    public String getVoucherUsableClientType() {
        return voucherUsableClientType;
    }

    public void setVoucherUsableClientType(String voucherUsableClientType) {
        this.voucherUsableClientType = voucherUsableClientType;
    }

    public String getVoucherUsableClientTypeText() {
        return voucherUsableClientTypeText;
    }

    public void setVoucherUsableClientTypeText(String voucherUsableClientTypeText) {
        this.voucherUsableClientTypeText = voucherUsableClientTypeText;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "voucherId=" + voucherId +
                ", voucherCode='" + voucherCode + '\'' +
                ", templateId=" + templateId +
                ", voucherTitle='" + voucherTitle + '\'' +
                ", voucherDescribe='" + voucherDescribe + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", limitAmount=" + limitAmount +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", voucherState=" + voucherState +
                ", activeTime=" + activeTime +
                ", type=" + type +
                ", webUsable=" + webUsable +
                ", appUsable=" + appUsable +
                ", wechatUsable=" + wechatUsable +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", ordersId=" + ordersId +
                ", ordersSn=" + ordersSn +
                ", voucherStateText='" + voucherStateText + '\'' +
                ", startTimeText='" + startTimeText + '\'' +
                ", endTimeText='" + endTimeText + '\'' +
                ", voucherExpiredState=" + voucherExpiredState +
                ", voucherUsableClientType='" + voucherUsableClientType + '\'' +
                ", voucherUsableClientTypeText='" + voucherUsableClientTypeText + '\'' +
                '}';
    }


}
