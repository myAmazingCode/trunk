package net.shopnc.b2b2c.android.bean;

/**
 * Created by xws on 2017/2/10.
 */

public class JoininMessage {


    /**
     * code : 200
     * datas : {"distributorJoin":{"memberId":50,"memberName":"testuser","bindPhone":"15028508536","realName":"szz","idCartNumber":"120104196009064321","idCartFrontImage":"image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png","idCartBackImage":"image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png","idCartHandImage":"image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png","payPerson":"asjsjs","bankAccountName":"bzbzhzhz","bankAccountNumber":"hxhxhzhz","accountType":"bank","state":30,"joininMessage":"12131213","joininTime":"2017-05-25 10:54:41","handleTime":"2017-05-25 10:57:28","idCartFrontImageUrl":"http://192.168.1.234/upload/image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png","idCartBackImageUrl":"http://192.168.1.234/upload/image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png","idCartHandImageUrl":"http://192.168.1.234/upload/image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png"},"distributorInfo":{"distributorId":7,"memberId":50,"memberName":"testuser","bindPhone":"15028508536","bindEmail":null,"realName":"szz","idCartNumber":"120104196009064321","idCartFrontImage":"image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png","idCartBackImage":"image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png","idCartHandImage":"image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png","bankAccountName":"bzbzhzhz","payPerson":"asjsjs","bankAccountNumber":"hxhxhzhz","accountType":"bank","state":1,"joininTime":"2017-05-25 10:54:41","lastLoginTime":null,"distributionOrdersCount":0,"payCommission":0,"unpayCommission":0,"distributionAmount":0,"commissionAvailable":0,"commissionFreeze":0,"defaultFavoritesId":29}}
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
         * distributorJoin : {"memberId":50,"memberName":"testuser","bindPhone":"15028508536","realName":"szz","idCartNumber":"120104196009064321","idCartFrontImage":"image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png","idCartBackImage":"image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png","idCartHandImage":"image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png","payPerson":"asjsjs","bankAccountName":"bzbzhzhz","bankAccountNumber":"hxhxhzhz","accountType":"bank","state":30,"joininMessage":"12131213","joininTime":"2017-05-25 10:54:41","handleTime":"2017-05-25 10:57:28","idCartFrontImageUrl":"http://192.168.1.234/upload/image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png","idCartBackImageUrl":"http://192.168.1.234/upload/image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png","idCartHandImageUrl":"http://192.168.1.234/upload/image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png"}
         * distributorInfo : {"distributorId":7,"memberId":50,"memberName":"testuser","bindPhone":"15028508536","bindEmail":null,"realName":"szz","idCartNumber":"120104196009064321","idCartFrontImage":"image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png","idCartBackImage":"image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png","idCartHandImage":"image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png","bankAccountName":"bzbzhzhz","payPerson":"asjsjs","bankAccountNumber":"hxhxhzhz","accountType":"bank","state":1,"joininTime":"2017-05-25 10:54:41","lastLoginTime":null,"distributionOrdersCount":0,"payCommission":0,"unpayCommission":0,"distributionAmount":0,"commissionAvailable":0,"commissionFreeze":0,"defaultFavoritesId":29}
         */

        private DistributorJoinBean distributorJoin;
        private DistributorInfoBean distributorInfo;

        public DistributorJoinBean getDistributorJoin() {
            return distributorJoin;
        }

        public void setDistributorJoin(DistributorJoinBean distributorJoin) {
            this.distributorJoin = distributorJoin;
        }

        public DistributorInfoBean getDistributorInfo() {
            return distributorInfo;
        }

        public void setDistributorInfo(DistributorInfoBean distributorInfo) {
            this.distributorInfo = distributorInfo;
        }

        public static class DistributorJoinBean {
            /**
             * memberId : 50
             * memberName : testuser
             * bindPhone : 15028508536
             * realName : szz
             * idCartNumber : 120104196009064321
             * idCartFrontImage : image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png
             * idCartBackImage : image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png
             * idCartHandImage : image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png
             * payPerson : asjsjs
             * bankAccountName : bzbzhzhz
             * bankAccountNumber : hxhxhzhz
             * accountType : bank
             * state : 30
             * joininMessage : 12131213
             * joininTime : 2017-05-25 10:54:41
             * handleTime : 2017-05-25 10:57:28
             * idCartFrontImageUrl : http://192.168.1.234/upload/image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png
             * idCartBackImageUrl : http://192.168.1.234/upload/image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png
             * idCartHandImageUrl : http://192.168.1.234/upload/image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png
             */

            private int memberId;
            private String memberName;
            private String bindPhone;
            private String realName;
            private String idCartNumber;
            private String idCartFrontImage;
            private String idCartBackImage;
            private String idCartHandImage;
            private String payPerson;
            private String bankAccountName;
            private String bankAccountNumber;
            private String accountType;
            private int state;
            private String joininMessage;
            private String joininTime;
            private String handleTime;
            private String idCartFrontImageUrl;
            private String idCartBackImageUrl;
            private String idCartHandImageUrl;

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

            public String getPayPerson() {
                return payPerson;
            }

            public void setPayPerson(String payPerson) {
                this.payPerson = payPerson;
            }

            public String getBankAccountName() {
                return bankAccountName;
            }

            public void setBankAccountName(String bankAccountName) {
                this.bankAccountName = bankAccountName;
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

            public String getJoininMessage() {
                return joininMessage;
            }

            public void setJoininMessage(String joininMessage) {
                this.joininMessage = joininMessage;
            }

            public String getJoininTime() {
                return joininTime;
            }

            public void setJoininTime(String joininTime) {
                this.joininTime = joininTime;
            }

            public String getHandleTime() {
                return handleTime;
            }

            public void setHandleTime(String handleTime) {
                this.handleTime = handleTime;
            }

            public String getIdCartFrontImageUrl() {
                return idCartFrontImageUrl;
            }

            public void setIdCartFrontImageUrl(String idCartFrontImageUrl) {
                this.idCartFrontImageUrl = idCartFrontImageUrl;
            }

            public String getIdCartBackImageUrl() {
                return idCartBackImageUrl;
            }

            public void setIdCartBackImageUrl(String idCartBackImageUrl) {
                this.idCartBackImageUrl = idCartBackImageUrl;
            }

            public String getIdCartHandImageUrl() {
                return idCartHandImageUrl;
            }

            public void setIdCartHandImageUrl(String idCartHandImageUrl) {
                this.idCartHandImageUrl = idCartHandImageUrl;
            }
        }

        public static class DistributorInfoBean {
            /**
             * distributorId : 7
             * memberId : 50
             * memberName : testuser
             * bindPhone : 15028508536
             * bindEmail : null
             * realName : szz
             * idCartNumber : 120104196009064321
             * idCartFrontImage : image/67/66/67663d5d7fe971fbe073dce0eeabb74c.png
             * idCartBackImage : image/45/1f/451f3cd0a4c6d294c818c53d003afd1b.png
             * idCartHandImage : image/68/ba/68ba74c72d5e326fa0c1c35c2d34fc12.png
             * bankAccountName : bzbzhzhz
             * payPerson : asjsjs
             * bankAccountNumber : hxhxhzhz
             * accountType : bank
             * state : 1
             * joininTime : 2017-05-25 10:54:41
             * lastLoginTime : null
             * distributionOrdersCount : 0
             * payCommission : 0
             * unpayCommission : 0
             * distributionAmount : 0
             * commissionAvailable : 0
             * commissionFreeze : 0
             * defaultFavoritesId : 29
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
            private double payCommission;
            private double unpayCommission;
            private double distributionAmount;
            private double commissionAvailable;
            private double commissionFreeze;
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

            public double getPayCommission() {
                return payCommission;
            }

            public void setPayCommission(double payCommission) {
                this.payCommission = payCommission;
            }

            public double getUnpayCommission() {
                return unpayCommission;
            }

            public void setUnpayCommission(double unpayCommission) {
                this.unpayCommission = unpayCommission;
            }

            public double getDistributionAmount() {
                return distributionAmount;
            }

            public void setDistributionAmount(double distributionAmount) {
                this.distributionAmount = distributionAmount;
            }

            public double getCommissionAvailable() {
                return commissionAvailable;
            }

            public void setCommissionAvailable(double commissionAvailable) {
                this.commissionAvailable = commissionAvailable;
            }

            public double getCommissionFreeze() {
                return commissionFreeze;
            }

            public void setCommissionFreeze(double commissionFreeze) {
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
}
