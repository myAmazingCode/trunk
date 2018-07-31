package net.shopnc.b2b2c.android.bean;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/23 9:21
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description
 */
public class PredepositWithdrawDetail {


    /**
     * code : 200
     * datas : {"cashInfo":{"cashId":3,"cashSn":"435950013969286897","memberId":1,"amount":1,"receiveCompany":"中国农业银行","receiveAccount":"111111111111111","receiveUser":"侯哥","addTime":"2017-05-16 14:25:42","payTime":null,"state":0,"adminId":0,"adminName":"","refuseReason":"","stateText":"未处理"}}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * cashInfo : {"cashId":3,"cashSn":"435950013969286897","memberId":1,"amount":1,"receiveCompany":"中国农业银行","receiveAccount":"111111111111111","receiveUser":"侯哥","addTime":"2017-05-16 14:25:42","payTime":null,"state":0,"adminId":0,"adminName":"","refuseReason":"","stateText":"未处理"}
         */

        private CashInfoBean cashInfo;

        public CashInfoBean getCashInfo() {
            return cashInfo;
        }

        public void setCashInfo(CashInfoBean cashInfo) {
            this.cashInfo = cashInfo;
        }

        public static class CashInfoBean {
            /**
             * cashId : 3
             * cashSn : 435950013969286897
             * memberId : 1
             * amount : 1
             * receiveCompany : 中国农业银行
             * receiveAccount : 111111111111111
             * receiveUser : 侯哥
             * addTime : 2017-05-16 14:25:42
             * payTime : null
             * state : 0
             * adminId : 0
             * adminName :
             * refuseReason :
             * stateText : 未处理
             */

            private int cashId;
            private String cashSn;
            private int memberId;
            private double amount;
            private String receiveCompany;
            private String receiveAccount;
            private String receiveUser;
            private String addTime;
            private Object payTime;
            private int state;
            private int adminId;
            private String adminName;
            private String refuseReason;
            private String stateText;

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

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getAdminId() {
                return adminId;
            }

            public void setAdminId(int adminId) {
                this.adminId = adminId;
            }

            public String getAdminName() {
                return adminName;
            }

            public void setAdminName(String adminName) {
                this.adminName = adminName;
            }

            public String getRefuseReason() {
                return refuseReason;
            }

            public void setRefuseReason(String refuseReason) {
                this.refuseReason = refuseReason;
            }

            public String getStateText() {
                return stateText;
            }

            public void setStateText(String stateText) {
                this.stateText = stateText;
            }
        }
    }
}
