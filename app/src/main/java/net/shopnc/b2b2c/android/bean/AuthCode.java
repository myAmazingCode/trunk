package net.shopnc.b2b2c.android.bean;


public class AuthCode {

    /**
     * authCodeValidTime : 10
     * authCodeResendTime : 60
     */

    private int authCodeValidTime;//动态码的有效时间（分钟）
    private int authCodeResendTime;//动态码的重发间隔秒数

    public int getAuthCodeValidTime() {
        return authCodeValidTime;
    }

    public void setAuthCodeValidTime(int authCodeValidTime) {
        this.authCodeValidTime = authCodeValidTime;
    }

    public int getAuthCodeResendTime() {
        return authCodeResendTime;
    }

    public void setAuthCodeResendTime(int authCodeResendTime) {
        this.authCodeResendTime = authCodeResendTime;
    }
}
