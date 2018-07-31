package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by xws on 2017/2/17.
 */

public class CommissionWithdraw {


    /**
     * code : 200
     * datas : {"pageEntity":{"hasMore":false,"totalPage":1},"cashList":[{"cashId":5,"cashSn":"883810044102040467","distributorId":3,"memberId":4,"memberName":"shopnc_lhf","amount":92.73,"bindPhone":"13800138000","realName":"123","idCartNumber":"120222189812207584","accountType":"bank","bankAccountName":"wsdfsdf","payPerson":"adsfas","bankAccountNumber":"1313123123","addTime":"2017-01-22 09:31:36","payTime":"2017-01-22 00:00:00","state":1,"adminId":1,"adminName":"admin","refuseReason":"","stateText":"提现成功"},{"cashId":4,"cashSn":"090230044101500375","distributorId":3,"memberId":4,"memberName":"shopnc_lhf","amount":92.73,"bindPhone":"13800138000","realName":"123","idCartNumber":"120222189812207584","accountType":"bank","bankAccountName":"wsdfsdf","payPerson":"adsfas","bankAccountNumber":"1313123123","addTime":"2017-01-22 09:22:36","payTime":null,"state":2,"adminId":1,"adminName":"admin","refuseReason":"拒绝理由*:","stateText":"提现失败"}],"sum":159.9}
     */

    private String code;
    /**
     * pageEntity : {"hasMore":false,"totalPage":1}
     * cashList : [{"cashId":5,"cashSn":"883810044102040467","distributorId":3,"memberId":4,"memberName":"shopnc_lhf","amount":92.73,"bindPhone":"13800138000","realName":"123","idCartNumber":"120222189812207584","accountType":"bank","bankAccountName":"wsdfsdf","payPerson":"adsfas","bankAccountNumber":"1313123123","addTime":"2017-01-22 09:31:36","payTime":"2017-01-22 00:00:00","state":1,"adminId":1,"adminName":"admin","refuseReason":"","stateText":"提现成功"},{"cashId":4,"cashSn":"090230044101500375","distributorId":3,"memberId":4,"memberName":"shopnc_lhf","amount":92.73,"bindPhone":"13800138000","realName":"123","idCartNumber":"120222189812207584","accountType":"bank","bankAccountName":"wsdfsdf","payPerson":"adsfas","bankAccountNumber":"1313123123","addTime":"2017-01-22 09:22:36","payTime":null,"state":2,"adminId":1,"adminName":"admin","refuseReason":"拒绝理由*:","stateText":"提现失败"}]
     * sum : 159.9
     */

    private DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * hasMore : false
         * totalPage : 1
         */

        private PageEntityBean pageEntity;
        private double sum;
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

        private List<CashListBean> cashList;

        public PageEntityBean getPageEntity() {
            return pageEntity;
        }

        public void setPageEntity(PageEntityBean pageEntity) {
            this.pageEntity = pageEntity;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }

        public List<CashListBean> getCashList() {
            return cashList;
        }

        public void setCashList(List<CashListBean> cashList) {
            this.cashList = cashList;
        }

        public static class PageEntityBean {
            private boolean hasMore;
            private String totalPage;

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public String getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(String totalPage) {
                this.totalPage = totalPage;
            }
        }

        public static class CashListBean {
            private String cashId;
            private String cashSn;
            private String distributorId;
            private String memberId;
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
            private String state;
            private String adminId;
            private String adminName;
            private String refuseReason;
            private String stateText;

            public String getCashId() {
                return cashId;
            }

            public void setCashId(String cashId) {
                this.cashId = cashId;
            }

            public String getCashSn() {
                return cashSn;
            }

            public void setCashSn(String cashSn) {
                this.cashSn = cashSn;
            }

            public String getDistributorId() {
                return distributorId;
            }

            public void setDistributorId(String distributorId) {
                this.distributorId = distributorId;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
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

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getAdminId() {
                return adminId;
            }

            public void setAdminId(String adminId) {
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
