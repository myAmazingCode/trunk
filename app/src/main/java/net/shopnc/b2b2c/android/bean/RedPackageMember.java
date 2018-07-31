package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.bean.RedPackageMember.java
 * @author: Jie
 * @date: 2016-05-31 17:32
 */
public class RedPackageMember implements Serializable {

    private static final String TAG = "RedPackageMember";

    int redPackageId; //红包ID
    String redPackageCode; //红包编码
    int templateId;//红包活动编号
    String redPackageTitle; //红包名称
    String redPackageDescribe;  //红包描述
    String startTime;//红包有效期开始时间，精确到时分秒
    String endTime; //红包有效期结束时间，精确到时分秒
    int redPackagePrice; //红包面额
    int limitAmount;  //红包使用时的订单限额
    int redPackageState; //红包状态 0-未用,1-已用,2-作废
    String activeTime; //领取时间
    int redPackageType; //红包类型 1卡密红包 2积分红包
    int webUsable;//PC端是否可用 0不可用 1可用
    int appUsable; //APP端是否可用（包括wap、ios、android） 0不可用 1可用
    int wechatUsable;//微信端是否可用 0不可用 1可用
    int memberId;  //所属会员ID
    String memberName; //所有者会员名称
    int ordersPayId;//使用该红包的订单支付单号
    int ordersPaySn; //使用该红包的订单支付单编号
    String redPackageStateText; //红包状态文本 已使用、已作废、已过期、未使用
    int redPackageExpiredState; //过期状态 0未过期，1已过期
    String redPackageUsableClientType; //可用客户端类型标识，all表示全平台、web表示PC专享、app表示手机专享、wechat表示微信专享
    String redPackageUsableClientTypeText; //可用客户端类型文本
    String startTimeText; //红包有效期开始时间文本，精确到天
    String endTimeText; //红包有效期开始时间文本，精确到天

    public int getRedPackageId() {
        return redPackageId;
    }

    public void setRedPackageId(int redPackageId) {
        this.redPackageId = redPackageId;
    }

    public String getRedPackageCode() {
        return redPackageCode;
    }

    public void setRedPackageCode(String redPackageCode) {
        this.redPackageCode = redPackageCode;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getRedPackageTitle() {
        return redPackageTitle;
    }

    public void setRedPackageTitle(String redPackageTitle) {
        this.redPackageTitle = redPackageTitle;
    }

    public String getRedPackageDescribe() {
        return redPackageDescribe;
    }

    public void setRedPackageDescribe(String redPackageDescribe) {
        this.redPackageDescribe = redPackageDescribe;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getRedPackagePrice() {
        return redPackagePrice;
    }

    public void setRedPackagePrice(int redPackagePrice) {
        this.redPackagePrice = redPackagePrice;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(int limitAmount) {
        this.limitAmount = limitAmount;
    }

    public int getRedPackageState() {
        return redPackageState;
    }

    public void setRedPackageState(int redPackageState) {
        this.redPackageState = redPackageState;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public int getRedPackageType() {
        return redPackageType;
    }

    public void setRedPackageType(int redPackageType) {
        this.redPackageType = redPackageType;
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

    public int getOrdersPayId() {
        return ordersPayId;
    }

    public void setOrdersPayId(int ordersPayId) {
        this.ordersPayId = ordersPayId;
    }

    public int getOrdersPaySn() {
        return ordersPaySn;
    }

    public void setOrdersPaySn(int ordersPaySn) {
        this.ordersPaySn = ordersPaySn;
    }

    public String getRedPackageStateText() {
        return redPackageStateText;
    }

    public void setRedPackageStateText(String redPackageStateText) {
        this.redPackageStateText = redPackageStateText;
    }

    public int getRedPackageExpiredState() {
        return redPackageExpiredState;
    }

    public void setRedPackageExpiredState(int redPackageExpiredState) {
        this.redPackageExpiredState = redPackageExpiredState;
    }

    public String getRedPackageUsableClientType() {
        return redPackageUsableClientType;
    }

    public void setRedPackageUsableClientType(String redPackageUsableClientType) {
        this.redPackageUsableClientType = redPackageUsableClientType;
    }

    public String getRedPackageUsableClientTypeText() {
        return redPackageUsableClientTypeText;
    }

    public void setRedPackageUsableClientTypeText(String redPackageUsableClientTypeText) {
        this.redPackageUsableClientTypeText = redPackageUsableClientTypeText;
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
}
