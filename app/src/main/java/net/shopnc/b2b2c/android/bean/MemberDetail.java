package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * Created by xws on 2017/1/24.
 */

public class MemberDetail {


    /**
     * code : 200
     * datas : {"memberInfo":{"memberId":118530,"memberName":"lzz123","trueName":null,"memberSex":0,"birthday":null,"avatar":null,"email":"sfgd@qq.com","emailIsBind":0,"mobile":null,"mobileIsBind":0,"memberQQ":null,"memberWW":null,"registerTime":"2017-01-24 15:32:12","loginNum":4,"loginTime":"2017-01-24 16:02:14","lastLoginTime":"2017-01-24 15:43:14","loginIp":"192.168.1.251","lastLoginIp":"192.168.1.133","memberPoints":15,"predepositAvailable":0,"predepositFreeze":0,"addressProvinceId":0,"addressCityId":0,"addressAreaId":0,"addressAreaInfo":null,"experiencePoints":15,"allowBuy":1,"allowTalk":1,"state":1,"modifyNum":1,"weixinUserInfo":null,"qqUserInfo":null,"avatarUrl":"http://192.168.1.232/public/img/avatar.gif","emailEncrypt":"s****@qq.com","mobileEncrypt":"","securityLevel":0,"payPwdIsExist":0,"isDistributor":0}}
     */

    private int code;
    /**
     * memberInfo : {"memberId":118530,"memberName":"lzz123","trueName":null,"memberSex":0,"birthday":null,"avatar":null,"email":"sfgd@qq.com","emailIsBind":0,"mobile":null,"mobileIsBind":0,"memberQQ":null,"memberWW":null,"registerTime":"2017-01-24 15:32:12","loginNum":4,"loginTime":"2017-01-24 16:02:14","lastLoginTime":"2017-01-24 15:43:14","loginIp":"192.168.1.251","lastLoginIp":"192.168.1.133","memberPoints":15,"predepositAvailable":0,"predepositFreeze":0,"addressProvinceId":0,"addressCityId":0,"addressAreaId":0,"addressAreaInfo":null,"experiencePoints":15,"allowBuy":1,"allowTalk":1,"state":1,"modifyNum":1,"weixinUserInfo":null,"qqUserInfo":null,"avatarUrl":"http://192.168.1.232/public/img/avatar.gif","emailEncrypt":"s****@qq.com","mobileEncrypt":"","securityLevel":0,"payPwdIsExist":0,"isDistributor":0}
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
         * memberId : 118530
         * memberName : lzz123
         * trueName : null
         * memberSex : 0
         * birthday : null
         * avatar : null
         * email : sfgd@qq.com
         * emailIsBind : 0
         * mobile : null
         * mobileIsBind : 0
         * memberQQ : null
         * memberWW : null
         * registerTime : 2017-01-24 15:32:12
         * loginNum : 4
         * loginTime : 2017-01-24 16:02:14
         * lastLoginTime : 2017-01-24 15:43:14
         * loginIp : 192.168.1.251
         * lastLoginIp : 192.168.1.133
         * memberPoints : 15
         * predepositAvailable : 0
         * predepositFreeze : 0
         * addressProvinceId : 0
         * addressCityId : 0
         * addressAreaId : 0
         * addressAreaInfo : null
         * experiencePoints : 15
         * allowBuy : 1
         * allowTalk : 1
         * state : 1
         * modifyNum : 1
         * weixinUserInfo : null
         * qqUserInfo : null
         * avatarUrl : http://192.168.1.232/public/img/avatar.gif
         * emailEncrypt : s****@qq.com
         * mobileEncrypt :
         * securityLevel : 0
         * payPwdIsExist : 0
         * isDistributor : 0
         */

        private MemberInfoBean memberInfo;

        public MemberInfoBean getMemberInfo() {
            return memberInfo;
        }

        public void setMemberInfo(MemberInfoBean memberInfo) {
            this.memberInfo = memberInfo;
        }

        public static class MemberInfoBean {
            private int memberId;
            private String memberName;
            private String trueName;
            private int memberSex;
            private String birthday;
            private String avatar;
            private String email;
            private int emailIsBind;
            private String mobile;
            private int mobileIsBind;
            private String memberQQ;
            private String memberWW;
            private String registerTime;
            private int loginNum;
            private String loginTime;
            private String lastLoginTime;
            private String loginIp;
            private String lastLoginIp;
            private int memberPoints;
            private BigDecimal predepositAvailable;
            private BigDecimal predepositFreeze;
            private int addressProvinceId;
            private int addressCityId;
            private int addressAreaId;
            private String addressAreaInfo;
            private int experiencePoints;
            private int allowBuy;
            private int allowTalk;
            private int state;
            private int modifyNum;
            private String weixinUserInfo;
            private String qqUserInfo;
            private String avatarUrl;
            private String emailEncrypt;
            private String mobileEncrypt;
            private int securityLevel;
            private int payPwdIsExist;
            private int isDistributor;

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

            public String getTrueName() {
                return trueName;
            }

            public void setTrueName(String trueName) {
                this.trueName = trueName;
            }

            public int getMemberSex() {
                return memberSex;
            }

            public void setMemberSex(int memberSex) {
                this.memberSex = memberSex;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getEmailIsBind() {
                return emailIsBind;
            }

            public void setEmailIsBind(int emailIsBind) {
                this.emailIsBind = emailIsBind;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getMobileIsBind() {
                return mobileIsBind;
            }

            public void setMobileIsBind(int mobileIsBind) {
                this.mobileIsBind = mobileIsBind;
            }

            public String getMemberQQ() {
                return memberQQ;
            }

            public void setMemberQQ(String memberQQ) {
                this.memberQQ = memberQQ;
            }

            public String getMemberWW() {
                return memberWW;
            }

            public void setMemberWW(String memberWW) {
                this.memberWW = memberWW;
            }

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public int getLoginNum() {
                return loginNum;
            }

            public void setLoginNum(int loginNum) {
                this.loginNum = loginNum;
            }

            public String getLoginTime() {
                return loginTime;
            }

            public void setLoginTime(String loginTime) {
                this.loginTime = loginTime;
            }

            public String getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(String lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getLoginIp() {
                return loginIp;
            }

            public void setLoginIp(String loginIp) {
                this.loginIp = loginIp;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }

            public int getMemberPoints() {
                return memberPoints;
            }

            public void setMemberPoints(int memberPoints) {
                this.memberPoints = memberPoints;
            }

            public BigDecimal getPredepositAvailable() {
                return predepositAvailable;
            }

            public void setPredepositAvailable(BigDecimal predepositAvailable) {
                this.predepositAvailable = predepositAvailable;
            }

            public BigDecimal getPredepositFreeze() {
                return predepositFreeze;
            }

            public void setPredepositFreeze(BigDecimal predepositFreeze) {
                this.predepositFreeze = predepositFreeze;
            }

            public int getAddressProvinceId() {
                return addressProvinceId;
            }

            public void setAddressProvinceId(int addressProvinceId) {
                this.addressProvinceId = addressProvinceId;
            }

            public int getAddressCityId() {
                return addressCityId;
            }

            public void setAddressCityId(int addressCityId) {
                this.addressCityId = addressCityId;
            }

            public int getAddressAreaId() {
                return addressAreaId;
            }

            public void setAddressAreaId(int addressAreaId) {
                this.addressAreaId = addressAreaId;
            }

            public String getAddressAreaInfo() {
                return addressAreaInfo;
            }

            public void setAddressAreaInfo(String addressAreaInfo) {
                this.addressAreaInfo = addressAreaInfo;
            }

            public int getExperiencePoints() {
                return experiencePoints;
            }

            public void setExperiencePoints(int experiencePoints) {
                this.experiencePoints = experiencePoints;
            }

            public int getAllowBuy() {
                return allowBuy;
            }

            public void setAllowBuy(int allowBuy) {
                this.allowBuy = allowBuy;
            }

            public int getAllowTalk() {
                return allowTalk;
            }

            public void setAllowTalk(int allowTalk) {
                this.allowTalk = allowTalk;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getModifyNum() {
                return modifyNum;
            }

            public void setModifyNum(int modifyNum) {
                this.modifyNum = modifyNum;
            }

            public String getWeixinUserInfo() {
                return weixinUserInfo;
            }

            public void setWeixinUserInfo(String weixinUserInfo) {
                this.weixinUserInfo = weixinUserInfo;
            }

            public String getQqUserInfo() {
                return qqUserInfo;
            }

            public void setQqUserInfo(String qqUserInfo) {
                this.qqUserInfo = qqUserInfo;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getEmailEncrypt() {
                return emailEncrypt;
            }

            public void setEmailEncrypt(String emailEncrypt) {
                this.emailEncrypt = emailEncrypt;
            }

            public String getMobileEncrypt() {
                return mobileEncrypt;
            }

            public void setMobileEncrypt(String mobileEncrypt) {
                this.mobileEncrypt = mobileEncrypt;
            }

            public int getSecurityLevel() {
                return securityLevel;
            }

            public void setSecurityLevel(int securityLevel) {
                this.securityLevel = securityLevel;
            }

            public int getPayPwdIsExist() {
                return payPwdIsExist;
            }

            public void setPayPwdIsExist(int payPwdIsExist) {
                this.payPwdIsExist = payPwdIsExist;
            }

            public int getIsDistributor() {
                return isDistributor;
            }

            public void setIsDistributor(int isDistributor) {
                this.isDistributor = isDistributor;
            }
        }
    }
}
