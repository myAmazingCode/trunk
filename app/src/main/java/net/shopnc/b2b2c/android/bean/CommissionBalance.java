package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by xws on 2017/2/17.
 */

public class CommissionBalance {


    /**
     * code : 200
     * datas : {"pageEntity":{"hasMore":true,"totalPage":2},"logList":[{"logId":23,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":79.95,"freezeAmount":0,"addTime":"2017-02-08 08:43:26","description":"增加推广佣金，推广订单编号：35","operationStageText":""},{"logId":22,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":79.95,"freezeAmount":0,"addTime":"2017-02-08 08:43:25","description":"增加推广佣金，推广订单编号：34","operationStageText":""},{"logId":21,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"cashPay","availableAmount":0,"freezeAmount":-92.73,"addTime":"2017-01-22 09:32:31","description":"提现成功，提现单号: 883810044102040467","operationStageText":""},{"logId":20,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"cashApply","availableAmount":-92.73,"freezeAmount":92.73,"addTime":"2017-01-22 09:31:36","description":"申请提现，冻结推广佣金，提现单号: 883810044102040467","operationStageText":""},{"logId":19,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"cashRefuse","availableAmount":92.73,"freezeAmount":-92.73,"addTime":"2017-01-22 09:25:42","description":"管理员拒绝提现，提现单号: 090230044101500375,拒绝理由：拒绝理由*:","operationStageText":""},{"logId":18,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"cashApply","availableAmount":-92.73,"freezeAmount":92.73,"addTime":"2017-01-22 09:22:36","description":"申请提现，冻结推广佣金，提现单号: 090230044101500375","operationStageText":""},{"logId":10,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":29.95,"freezeAmount":0,"addTime":"2017-01-18 08:51:42","description":"增加推广佣金，推广订单编号：21","operationStageText":""},{"logId":9,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":3.46,"freezeAmount":0,"addTime":"2017-01-17 10:50:47","description":"增加推广佣金，推广订单编号：17","operationStageText":""},{"logId":7,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":31.46,"freezeAmount":0,"addTime":"2017-01-13 08:42:51","description":"增加推广佣金，推广订单编号：14","operationStageText":""},{"logId":6,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":0.99,"freezeAmount":0,"addTime":"2017-01-13 08:42:51","description":"增加推广佣金，推广订单编号：12","operationStageText":""}],"sum":159.9}
     */

    private String code;
    /**
     * pageEntity : {"hasMore":true,"totalPage":2}
     * logList : [{"logId":23,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":79.95,"freezeAmount":0,"addTime":"2017-02-08 08:43:26","description":"增加推广佣金，推广订单编号：35","operationStageText":""},{"logId":22,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":79.95,"freezeAmount":0,"addTime":"2017-02-08 08:43:25","description":"增加推广佣金，推广订单编号：34","operationStageText":""},{"logId":21,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"cashPay","availableAmount":0,"freezeAmount":-92.73,"addTime":"2017-01-22 09:32:31","description":"提现成功，提现单号: 883810044102040467","operationStageText":""},{"logId":20,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"cashApply","availableAmount":-92.73,"freezeAmount":92.73,"addTime":"2017-01-22 09:31:36","description":"申请提现，冻结推广佣金，提现单号: 883810044102040467","operationStageText":""},{"logId":19,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"cashRefuse","availableAmount":92.73,"freezeAmount":-92.73,"addTime":"2017-01-22 09:25:42","description":"管理员拒绝提现，提现单号: 090230044101500375,拒绝理由：拒绝理由*:","operationStageText":""},{"logId":18,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"cashApply","availableAmount":-92.73,"freezeAmount":92.73,"addTime":"2017-01-22 09:22:36","description":"申请提现，冻结推广佣金，提现单号: 090230044101500375","operationStageText":""},{"logId":10,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":29.95,"freezeAmount":0,"addTime":"2017-01-18 08:51:42","description":"增加推广佣金，推广订单编号：21","operationStageText":""},{"logId":9,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":3.46,"freezeAmount":0,"addTime":"2017-01-17 10:50:47","description":"增加推广佣金，推广订单编号：17","operationStageText":""},{"logId":7,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":31.46,"freezeAmount":0,"addTime":"2017-01-13 08:42:51","description":"增加推广佣金，推广订单编号：14","operationStageText":""},{"logId":6,"distributorId":3,"memberId":4,"memberName":"shopnc_lhf","adminId":0,"adminName":"","operationStage":"increase","availableAmount":0.99,"freezeAmount":0,"addTime":"2017-01-13 08:42:51","description":"增加推广佣金，推广订单编号：12","operationStageText":""}]
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
         * hasMore : true
         * totalPage : 2
         */

        private PageEntityBean pageEntity;
        private double sum;
        /**
         * logId : 23
         * distributorId : 3
         * memberId : 4
         * memberName : shopnc_lhf
         * adminId : 0
         * adminName :
         * operationStage : increase
         * availableAmount : 79.95
         * freezeAmount : 0
         * addTime : 2017-02-08 08:43:26
         * description : 增加推广佣金，推广订单编号：35
         * operationStageText :
         */

        private List<LogListBean> logList;

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

        public List<LogListBean> getLogList() {
            return logList;
        }

        public void setLogList(List<LogListBean> logList) {
            this.logList = logList;
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

        public static class LogListBean {
            private String logId;
            private String distributorId;
            private String memberId;
            private String memberName;
            private String adminId;
            private String adminName;
            private String operationStage;
            private double availableAmount;
            private String freezeAmount;
            private String addTime;
            private String description;
            private String operationStageText;

            public String getLogId() {
                return logId;
            }

            public void setLogId(String logId) {
                this.logId = logId;
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

            public String getOperationStage() {
                return operationStage;
            }

            public void setOperationStage(String operationStage) {
                this.operationStage = operationStage;
            }

            public double getAvailableAmount() {
                return availableAmount;
            }

            public void setAvailableAmount(double availableAmount) {
                this.availableAmount = availableAmount;
            }

            public String getFreezeAmount() {
                return freezeAmount;
            }

            public void setFreezeAmount(String freezeAmount) {
                this.freezeAmount = freezeAmount;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getOperationStageText() {
                return operationStageText;
            }

            public void setOperationStageText(String operationStageText) {
                this.operationStageText = operationStageText;
            }
        }
    }
}
