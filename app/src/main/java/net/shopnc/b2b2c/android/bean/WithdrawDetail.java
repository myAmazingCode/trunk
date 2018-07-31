package net.shopnc.b2b2c.android.bean;

/**
 * Created by xws on 2017/2/17.
 */

public class WithdrawDetail {


    /**
     * code : 200
     * datas : {"cashInfo":{"cashId":5,"cashSn":"883810044102040467","distributorId":3,"memberId":4,"memberName":"shopnc_lhf","amount":92.73,"bindPhone":"13800138000","realName":"123","idCartNumber":"120222189812207584","accountType":"bank","bankAccountName":"wsdfsdf","payPerson":"adsfas","bankAccountNumber":"1313123123","addTime":"2017-01-22 09:31:36","payTime":"2017-01-22 00:00:00","state":1,"adminId":1,"adminName":"admin","refuseReason":"","stateText":"提现成功"}}
     */

    private int code;
    /**
     * cashInfo : {"cashId":5,"cashSn":"883810044102040467","distributorId":3,"memberId":4,"memberName":"shopnc_lhf","amount":92.73,"bindPhone":"13800138000","realName":"123","idCartNumber":"120222189812207584","accountType":"bank","bankAccountName":"wsdfsdf","payPerson":"adsfas","bankAccountNumber":"1313123123","addTime":"2017-01-22 09:31:36","payTime":"2017-01-22 00:00:00","state":1,"adminId":1,"adminName":"admin","refuseReason":"","stateText":"提现成功"}
     */

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
         * cashId : 5
         * cashSn : 883810044102040467
         * distributorId : 3
         * memberId : 4
         * memberName : shopnc_lhf
         * amount : 92.73
         * bindPhone : 13800138000
         * realName : 123
         * idCartNumber : 120222189812207584
         * accountType : bank
         * bankAccountName : wsdfsdf
         * payPerson : adsfas
         * bankAccountNumber : 1313123123
         * addTime : 2017-01-22 09:31:36
         * payTime : 2017-01-22 00:00:00
         * state : 1
         * adminId : 1
         * adminName : admin
         * refuseReason :
         * stateText : 提现成功
         */

        private CashInfoBean cashInfo;

        public CashInfoBean getCashInfo() {
            return cashInfo;
        }

        public void setCashInfo(CashInfoBean cashInfo) {
            this.cashInfo = cashInfo;
        }

        public static class CashInfoBean {
            private int cashId;
            private String cashSn;
            private int distributorId;
            private int memberId;
            private String memberName;
            private double amount;
            private String bindPhone;
            private String realName;
            private String idCartNumber;
            private String accountType;
            private String bankAccountName;
            private String payPerson;
            private String bankAccountNumber;
            private String addTime;
            private String payTime;
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

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getBindPhone() {
                return bindPhone;
            }

            public void setBindPhone(String bindPhone) {
                this.bindPhone = bindPhone;
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

            public String getAccountType() {
                return accountType;
            }

            public void setAccountType(String accountType) {
                this.accountType = accountType;
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
