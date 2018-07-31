package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * 优惠券活动
 *
 * @author huting
 * @date 2016/4/19
 */
public class VoucherTemplateVo {
    /**
     * 自增编码
     */
    private int templateId;
    /**
     * 活动名称
     */
    private String templateTitle = "";
    /**
     * 活动描述
     */
    private String templateDescribe = "";
    /**
     * 优惠券有效期开始时间
     */
    private String useStartTime;
    /**
     * 优惠券有效期结束时间
     */
    private String useEndTime;
    /**
     * 面额
     */
    private BigDecimal templatePrice = new BigDecimal(0);
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
     * 最后更新的卖家编号
     */
    private int sellerId = 0;
    /**
     * 最后更新的卖家名称
     */
    private String sellerName = "";
    /**
     * 活动状态 1有效 2失效
     */
    private int templateState = 0;
    /**
     * 模版可发放的优惠券总数
     */
    private int totalNum = 0;
    /**
     * 模版已发放的优惠券数量
     */
    private int giveoutNum = 0;
    /**
     * 模版已经使用过的优惠券
     */
    private int usedNum = 0;
    /**
     * 活动创建时间
     */
    private String addTime;
    /**
     * 活动最后更新时间
     */
    private String updateTime;
    /**
     * 领取优惠券限制的会员等级
     */
    private int limitMemberGradeLevel = 0;
    /**
     * 领取优惠券限制的会员等级名称
     */
    private String limitMemberGradeName = "";
    /**
     * 类型 1卡密兑换 2免费领取
     */
    private int templateType = 0;
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
     * 类型为卡密兑换是否已经生成下属优惠券 0未生成 1已生成
     */
    private int haveCreated = 0;
    /**
     * 活动状态文本 有效、失效、过期
     */
    private String templateStateText = "";
    /**
     * 活动类型文本
     */
    private String templateTypeText = "";
    /**
     * 过期状态 0未过期，1已过期
     */
    private int expiredState = 0;
    /**
     * 客户端类型标识
     */
    private String usableClientType= "";
    /**
     * 客户端类型文本
     */
    private String usableClientTypeText= "";
    /**
     * 已领完状态 0未领完，1已领完
     */
    private int withoutState = 0;
    /**
     * 优惠券有效期开始时间
     */
    private String useStartTimeText = "";
    /**
     * 优惠券有效期结束时间
     */
    private String useEndTimeText = "";
    /**
     * 会员是否已领取 0未领取 1已领取
     */
    private int memberIsReceive = 0;

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public String getTemplateDescribe() {
        return templateDescribe;
    }

    public void setTemplateDescribe(String templateDescribe) {
        this.templateDescribe = templateDescribe;
    }

    public String getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(String useStartTime) {
        this.useStartTime = useStartTime;
    }

    public String getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(String useEndTime) {
        this.useEndTime = useEndTime;
    }

    public BigDecimal getTemplatePrice() {
        return templatePrice;
    }

    public void setTemplatePrice(BigDecimal templatePrice) {
        this.templatePrice = templatePrice;
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

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getTemplateState() {
        return templateState;
    }

    public void setTemplateState(int templateState) {
        this.templateState = templateState;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getGiveoutNum() {
        return giveoutNum;
    }

    public void setGiveoutNum(int giveoutNum) {
        this.giveoutNum = giveoutNum;
    }

    public int getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(int usedNum) {
        this.usedNum = usedNum;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getLimitMemberGradeLevel() {
        return limitMemberGradeLevel;
    }

    public void setLimitMemberGradeLevel(int limitMemberGradeLevel) {
        this.limitMemberGradeLevel = limitMemberGradeLevel;
    }

    public String getLimitMemberGradeName() {
        return limitMemberGradeName;
    }

    public void setLimitMemberGradeName(String limitMemberGradeName) {
        this.limitMemberGradeName = limitMemberGradeName;
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
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

    public int getHaveCreated() {
        return haveCreated;
    }

    public void setHaveCreated(int haveCreated) {
        this.haveCreated = haveCreated;
    }

    public String getTemplateStateText() {
        return templateStateText;
    }

    public void setTemplateStateText(String templateStateText) {
        this.templateStateText = templateStateText;
    }

    public String getTemplateTypeText() {
        return templateTypeText;
    }

    public void setTemplateTypeText(String templateTypeText) {
        this.templateTypeText = templateTypeText;
    }

    public int getExpiredState() {
        return expiredState;
    }

    public void setExpiredState(int expiredState) {
        this.expiredState = expiredState;
    }

    public String getUsableClientType() {
        return usableClientType;
    }

    public void setUsableClientType(String usableClientType) {
        this.usableClientType = usableClientType;
    }

    public String getUsableClientTypeText() {
        return usableClientTypeText;
    }

    public void setUsableClientTypeText(String usableClientTypeText) {
        this.usableClientTypeText = usableClientTypeText;
    }

    public int getWithoutState() {
        return withoutState;
    }

    public void setWithoutState(int withoutState) {
        this.withoutState = withoutState;
    }

    public String getUseStartTimeText() {
        return useStartTimeText;
    }

    public void setUseStartTimeText(String useStartTimeText) {
        this.useStartTimeText = useStartTimeText;
    }

    public String getUseEndTimeText() {
        return useEndTimeText;
    }

    public void setUseEndTimeText(String useEndTimeText) {
        this.useEndTimeText = useEndTimeText;
    }

    public int getMemberIsReceive() {
        return memberIsReceive;
    }

    public void setMemberIsReceive(int memberIsReceive) {
        this.memberIsReceive = memberIsReceive;
    }


    @Override
    public String toString() {
        return "VoucherTemplateVo{" +
                "templateId=" + templateId +
                ", templateTitle='" + templateTitle + '\'' +
                ", templateDescribe='" + templateDescribe + '\'' +
                ", useStartTime='" + useStartTime + '\'' +
                ", useEndTime='" + useEndTime + '\'' +
                ", templatePrice=" + templatePrice +
                ", limitAmount=" + limitAmount +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", templateState=" + templateState +
                ", totalNum=" + totalNum +
                ", giveoutNum=" + giveoutNum +
                ", usedNum=" + usedNum +
                ", addTime='" + addTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", limitMemberGradeLevel=" + limitMemberGradeLevel +
                ", limitMemberGradeName='" + limitMemberGradeName + '\'' +
                ", templateType=" + templateType +
                ", webUsable=" + webUsable +
                ", appUsable=" + appUsable +
                ", wechatUsable=" + wechatUsable +
                ", haveCreated=" + haveCreated +
                ", templateStateText='" + templateStateText + '\'' +
                ", templateTypeText='" + templateTypeText + '\'' +
                ", expiredState=" + expiredState +
                ", usableClientType='" + usableClientType + '\'' +
                ", usableClientTypeText='" + usableClientTypeText + '\'' +
                ", withoutState=" + withoutState +
                ", useStartTimeText='" + useStartTimeText + '\'' +
                ", useEndTimeText='" + useEndTimeText + '\'' +
                ", memberIsReceive=" + memberIsReceive +
                '}';
    }
}
