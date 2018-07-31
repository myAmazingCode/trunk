package net.shopnc.b2b2c.android.bean;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/4 15:49
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 提现申请初始化类
 */
public class WithdrawApply {


    /**
     * distributor : {"distributorId":45417,"memberId":17820,"memberName":"zxybuyer","bindPhone":"18711111111","bindEmail":null,"realName":"zxybuyer","idCartNumber":"131182198602245029","idCartFrontImage":"image/82/5b/825b28c606396593ac58ffe65a5d9519.jpg","idCartBackImage":"image/d7/78/d7787117d896f0faffce4702d1f38348.jpg","idCartHandImage":"image/aa/07/aa0717e55e5a5423e0659e69954b40e1.jpg","bankAccountName":"建行","payPerson":"张三","bankAccountNumber":"111111111111","accountType":"alipay","state":1,"joininTime":"2016-12-14 14:13:03","lastLoginTime":null,"distributionOrdersCount":0,"payCommission":0,"unpayCommission":0,"distributionAmount":0,"commissionAvailable":121.5,"commissionFreeze":10,"defaultFavoritesId":45418}
     */

    private DistributorBean distributor;

    public DistributorBean getDistributor() {
        return distributor;
    }

    public void setDistributor(DistributorBean distributor) {
        this.distributor = distributor;
    }

    public static class DistributorBean {
        /**
         * distributorId : 45417
         * memberId : 17820
         * memberName : zxybuyer
         * bindPhone : 18711111111
         * bindEmail : null
         * realName : zxybuyer
         * idCartNumber : 131182198602245029
         * idCartFrontImage : image/82/5b/825b28c606396593ac58ffe65a5d9519.jpg
         * idCartBackImage : image/d7/78/d7787117d896f0faffce4702d1f38348.jpg
         * idCartHandImage : image/aa/07/aa0717e55e5a5423e0659e69954b40e1.jpg
         * bankAccountName : 建行
         * payPerson : 张三
         * bankAccountNumber : 111111111111
         * accountType : alipay
         * state : 1
         * joininTime : 2016-12-14 14:13:03
         * lastLoginTime : null
         * distributionOrdersCount : 0
         * payCommission : 0
         * unpayCommission : 0
         * distributionAmount : 0
         * commissionAvailable : 121.5
         * commissionFreeze : 10
         * defaultFavoritesId : 45418
         */

        private int distributorId;
        private int memberId;
        private String memberName;
        private String bindPhone;
        private Object bindEmail;
        private String realName;
        private String idCartNumber;
        private String idCartFrontImage;
        private String idCartBackImage;
        private String idCartHandImage;
        private String bankAccountName;
        private String payPerson;
        private String bankAccountNumber;
        private String accountType;
        private int state;
        private String joininTime;
        private Object lastLoginTime;
        private int distributionOrdersCount;
        private int payCommission;
        private int unpayCommission;
        private int distributionAmount;
        private double commissionAvailable;
        private float commissionFreeze;
        private int defaultFavoritesId;

        public int getDistributorId() {
            return distributorId;
        }

        public void setDistributorId(int distributorId) {
            this.distributorId = distributorId;
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

        public String getBindPhone() {
            return bindPhone;
        }

        public void setBindPhone(String bindPhone) {
            this.bindPhone = bindPhone;
        }

        public Object getBindEmail() {
            return bindEmail;
        }

        public void setBindEmail(Object bindEmail) {
            this.bindEmail = bindEmail;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdCartNumber() {
            return idCartNumber;
        }

        public void setIdCartNumber(String idCartNumber) {
            this.idCartNumber = idCartNumber;
        }

        public String getIdCartFrontImage() {
            return idCartFrontImage;
        }

        public void setIdCartFrontImage(String idCartFrontImage) {
            this.idCartFrontImage = idCartFrontImage;
        }

        public String getIdCartBackImage() {
            return idCartBackImage;
        }

        public void setIdCartBackImage(String idCartBackImage) {
            this.idCartBackImage = idCartBackImage;
        }

        public String getIdCartHandImage() {
            return idCartHandImage;
        }

        public void setIdCartHandImage(String idCartHandImage) {
            this.idCartHandImage = idCartHandImage;
        }

        public String getBankAccountName() {
            return bankAccountName;
        }

        public void setBankAccountName(String bankAccountName) {
            this.bankAccountName = bankAccountName;
        }

        public String getPayPerson() {
            return payPerson;
        }

        public void setPayPerson(String payPerson) {
            this.payPerson = payPerson;
        }

        public String getBankAccountNumber() {
            return bankAccountNumber;
        }

        public void setBankAccountNumber(String bankAccountNumber) {
            this.bankAccountNumber = bankAccountNumber;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getJoininTime() {
            return joininTime;
        }

        public void setJoininTime(String joininTime) {
            this.joininTime = joininTime;
        }

        public Object getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Object lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getDistributionOrdersCount() {
            return distributionOrdersCount;
        }

        public void setDistributionOrdersCount(int distributionOrdersCount) {
            this.distributionOrdersCount = distributionOrdersCount;
        }

        public int getPayCommission() {
            return payCommission;
        }

        public void setPayCommission(int payCommission) {
            this.payCommission = payCommission;
        }

        public int getUnpayCommission() {
            return unpayCommission;
        }

        public void setUnpayCommission(int unpayCommission) {
            this.unpayCommission = unpayCommission;
        }

        public int getDistributionAmount() {
            return distributionAmount;
        }

        public void setDistributionAmount(int distributionAmount) {
            this.distributionAmount = distributionAmount;
        }

        public double getCommissionAvailable() {
            return commissionAvailable;
        }

        public void setCommissionAvailable(double commissionAvailable) {
            this.commissionAvailable = commissionAvailable;
        }

        public float getCommissionFreeze() {
            return commissionFreeze;
        }

        public void setCommissionFreeze(float commissionFreeze) {
            this.commissionFreeze = commissionFreeze;
        }

        public int getDefaultFavoritesId() {
            return defaultFavoritesId;
        }

        public void setDefaultFavoritesId(int defaultFavoritesId) {
            this.defaultFavoritesId = defaultFavoritesId;
        }
    }
}
