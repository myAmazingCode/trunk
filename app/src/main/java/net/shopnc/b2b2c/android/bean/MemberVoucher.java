package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.bean.MemberVoucher.java
 * @author: Jie
 * @date: 2016-06-01 11:58
 */
public class MemberVoucher implements Serializable {

    private static final String TAG = "MemberVoucher";


    /**
     * voucherId : 20
     * voucherCode : 038730040063773373
     * templateId : 6
     * voucherTitle : 满送促销优惠券
     * voucherDescribe : 满送促销赠送的优惠券
     * startTime : 2016-06-15
     * endTime : 2016-09-13
     * price : 10
     * limitAmount : 1000
     * storeId : 6
     * storeName : 华为旗舰店
     * voucherState : 0
     * activeTime : 2016-06-15 16:47:09
     * type : 3
     * webUsable : 1
     * appUsable : 1
     * wechatUsable : 1
     * memberId : 4
     * memberName : shopnc_lhf
     * ordersId : 0
     * ordersSn : 0
     * voucherStateText : 未使用
     * startTimeText : 2016-06-15
     * endTimeText : 2016-09-13
     * voucherExpiredState : 0
     * voucherUsableClientType : all
     * voucherUsableClientTypeText : 全平台适用
     * store : {"storeId":6,"storeName":"华为旗舰店","gradeId":1,"gradeName":"默认等级","sellerId":6,"sellerName":"shopnc_hbj","classId":2,"className":"3C数码","state":1,"storeAddress":null,"storeCreateTime":"2016-06-07 15:28:54","storeEndTime":"2017-06-07 15:28:54","storeLogo":"image/63/b6/63b6dbf61f69b157269ee3a4dce12058.jpg","storeBanner":"image/ac/01/ac011d49117e69a525eede16e17dd711.jpg","storeAvatar":"","storeSeoKeywords":"","storeSeoDescription":"","storeQq":"","storeWw":"","storeWeixin":"","storePhone":"","storeZy":"电子产品","isRecommend":0,"storeTheme":"default","storeDesccredit":4.3,"storeServicecredit":4.3,"storeDeliverycredit":4.7,"storeCollect":2,"storeSales":0,"storePresales":"","storeAftersales":"","storeWorkingtime":"","isOwnShop":0,"storeLogoUrl":"http://java.shopnctest.com/upload/image/63/b6/63b6dbf61f69b157269ee3a4dce12058.jpg","storeBannerUrl":"http://java.shopnctest.com/upload/image/ac/01/ac011d49117e69a525eede16e17dd711.jpg","storeAvatarUrl":"http://java.shopnctest.com/public/img/default_store_avatar.png","shipCompany":null,"companyName":"华为科技公司","companyArea":"北京","billCycle":1,"billCycleType":1,"billCyleDescription":"月 / 1"}
     */

    private int voucherId;
    private String voucherCode;
    private int templateId;
    private String voucherTitle;
    private String voucherDescribe;
    private String startTime;
    private String endTime;
    private int price;
    private float limitAmount;
    private int storeId;
    private String storeName;
    private int voucherState;
    private String activeTime;
    private int type;
    private int webUsable;
    private int appUsable;
    private int wechatUsable;
    private int memberId;
    private String memberName;
    private int ordersId;
    private long ordersSn;
    private String voucherStateText;
    private String startTimeText;
    private String endTimeText;
    private int voucherExpiredState;
    private String voucherUsableClientType;
    private String voucherUsableClientTypeText;
    /**
     * storeId : 6
     * storeName : 华为旗舰店
     * gradeId : 1
     * gradeName : 默认等级
     * sellerId : 6
     * sellerName : shopnc_hbj
     * classId : 2
     * className : 3C数码
     * state : 1
     * storeAddress : null
     * storeCreateTime : 2016-06-07 15:28:54
     * storeEndTime : 2017-06-07 15:28:54
     * storeLogo : image/63/b6/63b6dbf61f69b157269ee3a4dce12058.jpg
     * storeBanner : image/ac/01/ac011d49117e69a525eede16e17dd711.jpg
     * storeAvatar :
     * storeSeoKeywords :
     * storeSeoDescription :
     * storeQq :
     * storeWw :
     * storeWeixin :
     * storePhone :
     * storeZy : 电子产品
     * isRecommend : 0
     * storeTheme : default
     * storeDesccredit : 4.3
     * storeServicecredit : 4.3
     * storeDeliverycredit : 4.7
     * storeCollect : 2
     * storeSales : 0
     * storePresales :
     * storeAftersales :
     * storeWorkingtime :
     * isOwnShop : 0
     * storeLogoUrl : http://java.shopnctest.com/upload/image/63/b6/63b6dbf61f69b157269ee3a4dce12058.jpg
     * storeBannerUrl : http://java.shopnctest.com/upload/image/ac/01/ac011d49117e69a525eede16e17dd711.jpg
     * storeAvatarUrl : http://java.shopnctest.com/public/img/default_store_avatar.png
     * shipCompany : null
     * companyName : 华为科技公司
     * companyArea : 北京
     * billCycle : 1
     * billCycleType : 1
     * billCyleDescription : 月 / 1
     */

    private StoreBean store;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(float limitAmount) {
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

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
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

    public void setOrdersSn(int ordersSn) {
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

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public static class StoreBean {
        private int storeId;
        private String storeName;
        private int gradeId;
        private String gradeName;
        private int sellerId;
        private String sellerName;
        private int classId;
        private String className;
        private int state;
        private Object storeAddress;
        private String storeCreateTime;
        private String storeEndTime;
        private String storeLogo;
        private String storeBanner;
        private String storeAvatar;
        private String storeSeoKeywords;
        private String storeSeoDescription;
        private String storeQq;
        private String storeWw;
        private String storeWeixin;
        private String storePhone;
        private String storeZy;
        private int isRecommend;
        private String storeTheme;
        private double storeDesccredit;
        private double storeServicecredit;
        private double storeDeliverycredit;
        private int storeCollect;
        private int storeSales;
        private String storePresales;
        private String storeAftersales;
        private String storeWorkingtime;
        private int isOwnShop;
        private String storeLogoUrl;
        private String storeBannerUrl;
        private String storeAvatarUrl;
        private Object shipCompany;
        private String companyName;
        private String companyArea;
        private int billCycle;
        private int billCycleType;
        private String billCyleDescription;

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

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
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

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(Object storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getStoreCreateTime() {
            return storeCreateTime;
        }

        public void setStoreCreateTime(String storeCreateTime) {
            this.storeCreateTime = storeCreateTime;
        }

        public String getStoreEndTime() {
            return storeEndTime;
        }

        public void setStoreEndTime(String storeEndTime) {
            this.storeEndTime = storeEndTime;
        }

        public String getStoreLogo() {
            return storeLogo;
        }

        public void setStoreLogo(String storeLogo) {
            this.storeLogo = storeLogo;
        }

        public String getStoreBanner() {
            return storeBanner;
        }

        public void setStoreBanner(String storeBanner) {
            this.storeBanner = storeBanner;
        }

        public String getStoreAvatar() {
            return storeAvatar;
        }

        public void setStoreAvatar(String storeAvatar) {
            this.storeAvatar = storeAvatar;
        }

        public String getStoreSeoKeywords() {
            return storeSeoKeywords;
        }

        public void setStoreSeoKeywords(String storeSeoKeywords) {
            this.storeSeoKeywords = storeSeoKeywords;
        }

        public String getStoreSeoDescription() {
            return storeSeoDescription;
        }

        public void setStoreSeoDescription(String storeSeoDescription) {
            this.storeSeoDescription = storeSeoDescription;
        }

        public String getStoreQq() {
            return storeQq;
        }

        public void setStoreQq(String storeQq) {
            this.storeQq = storeQq;
        }

        public String getStoreWw() {
            return storeWw;
        }

        public void setStoreWw(String storeWw) {
            this.storeWw = storeWw;
        }

        public String getStoreWeixin() {
            return storeWeixin;
        }

        public void setStoreWeixin(String storeWeixin) {
            this.storeWeixin = storeWeixin;
        }

        public String getStorePhone() {
            return storePhone;
        }

        public void setStorePhone(String storePhone) {
            this.storePhone = storePhone;
        }

        public String getStoreZy() {
            return storeZy;
        }

        public void setStoreZy(String storeZy) {
            this.storeZy = storeZy;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getStoreTheme() {
            return storeTheme;
        }

        public void setStoreTheme(String storeTheme) {
            this.storeTheme = storeTheme;
        }

        public double getStoreDesccredit() {
            return storeDesccredit;
        }

        public void setStoreDesccredit(double storeDesccredit) {
            this.storeDesccredit = storeDesccredit;
        }

        public double getStoreServicecredit() {
            return storeServicecredit;
        }

        public void setStoreServicecredit(double storeServicecredit) {
            this.storeServicecredit = storeServicecredit;
        }

        public double getStoreDeliverycredit() {
            return storeDeliverycredit;
        }

        public void setStoreDeliverycredit(double storeDeliverycredit) {
            this.storeDeliverycredit = storeDeliverycredit;
        }

        public int getStoreCollect() {
            return storeCollect;
        }

        public void setStoreCollect(int storeCollect) {
            this.storeCollect = storeCollect;
        }

        public int getStoreSales() {
            return storeSales;
        }

        public void setStoreSales(int storeSales) {
            this.storeSales = storeSales;
        }

        public String getStorePresales() {
            return storePresales;
        }

        public void setStorePresales(String storePresales) {
            this.storePresales = storePresales;
        }

        public String getStoreAftersales() {
            return storeAftersales;
        }

        public void setStoreAftersales(String storeAftersales) {
            this.storeAftersales = storeAftersales;
        }

        public String getStoreWorkingtime() {
            return storeWorkingtime;
        }

        public void setStoreWorkingtime(String storeWorkingtime) {
            this.storeWorkingtime = storeWorkingtime;
        }

        public int getIsOwnShop() {
            return isOwnShop;
        }

        public void setIsOwnShop(int isOwnShop) {
            this.isOwnShop = isOwnShop;
        }

        public String getStoreLogoUrl() {
            return storeLogoUrl;
        }

        public void setStoreLogoUrl(String storeLogoUrl) {
            this.storeLogoUrl = storeLogoUrl;
        }

        public String getStoreBannerUrl() {
            return storeBannerUrl;
        }

        public void setStoreBannerUrl(String storeBannerUrl) {
            this.storeBannerUrl = storeBannerUrl;
        }

        public String getStoreAvatarUrl() {
            return storeAvatarUrl;
        }

        public void setStoreAvatarUrl(String storeAvatarUrl) {
            this.storeAvatarUrl = storeAvatarUrl;
        }

        public Object getShipCompany() {
            return shipCompany;
        }

        public void setShipCompany(Object shipCompany) {
            this.shipCompany = shipCompany;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyArea() {
            return companyArea;
        }

        public void setCompanyArea(String companyArea) {
            this.companyArea = companyArea;
        }

        public int getBillCycle() {
            return billCycle;
        }

        public void setBillCycle(int billCycle) {
            this.billCycle = billCycle;
        }

        public int getBillCycleType() {
            return billCycleType;
        }

        public void setBillCycleType(int billCycleType) {
            this.billCycleType = billCycleType;
        }

        public String getBillCyleDescription() {
            return billCyleDescription;
        }

        public void setBillCyleDescription(String billCyleDescription) {
            this.billCyleDescription = billCyleDescription;
        }
    }
}
