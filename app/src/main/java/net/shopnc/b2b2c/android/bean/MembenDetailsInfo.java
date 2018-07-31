package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/6/29 16:13.
 */
public class MembenDetailsInfo {
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

    private CurrGrade currGrade;

    public MembenDetailsInfo(int memberId, String memberName, String trueName, int memberSex, String birthday, String avatar, String email, int emailIsBind, String mobile, int mobileIsBind, String memberQQ, String memberWW, String registerTime, int loginNum, String loginTime, String lastLoginTime, String loginIp, String lastLoginIp, int memberPoints, BigDecimal predepositAvailable, BigDecimal predepositFreeze, int addressProvinceId, int addressCityId, int addressAreaId, String addressAreaInfo, int experiencePoints, int allowBuy, int allowTalk, int state, int modifyNum, String weixinUserInfo, String qqUserInfo, String avatarUrl, String emailEncrypt, String mobileEncrypt, int securityLevel, int payPwdIsExist, CurrGrade currGrade) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.trueName = trueName;
        this.memberSex = memberSex;
        this.birthday = birthday;
        this.avatar = avatar;
        this.email = email;
        this.emailIsBind = emailIsBind;
        this.mobile = mobile;
        this.mobileIsBind = mobileIsBind;
        this.memberQQ = memberQQ;
        this.memberWW = memberWW;
        this.registerTime = registerTime;
        this.loginNum = loginNum;
        this.loginTime = loginTime;
        this.lastLoginTime = lastLoginTime;
        this.loginIp = loginIp;
        this.lastLoginIp = lastLoginIp;
        this.memberPoints = memberPoints;
        this.predepositAvailable = predepositAvailable;
        this.predepositFreeze = predepositFreeze;
        this.addressProvinceId = addressProvinceId;
        this.addressCityId = addressCityId;
        this.addressAreaId = addressAreaId;
        this.addressAreaInfo = addressAreaInfo;
        this.experiencePoints = experiencePoints;
        this.allowBuy = allowBuy;
        this.allowTalk = allowTalk;
        this.state = state;
        this.modifyNum = modifyNum;
        this.weixinUserInfo = weixinUserInfo;
        this.qqUserInfo = qqUserInfo;
        this.avatarUrl = avatarUrl;
        this.emailEncrypt = emailEncrypt;
        this.mobileEncrypt = mobileEncrypt;
        this.securityLevel = securityLevel;
        this.payPwdIsExist = payPwdIsExist;
        this.currGrade = currGrade;
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

    public CurrGrade getCurrGrade() {
        return currGrade;
    }

    public void setCurrGrade(CurrGrade currGrade) {
        this.currGrade = currGrade;
    }
}
