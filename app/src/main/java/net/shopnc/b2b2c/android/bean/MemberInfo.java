package net.shopnc.b2b2c.android.bean;

/**
 * @Description 登录成功的返回信息
 * @Author qyf
 * <p>
 * Created 2016/4/6 18:59.
 */
public class MemberInfo {
    private int memberId;
    private String memberName;
    private String token;
    private String avatar;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", token='" + token + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
