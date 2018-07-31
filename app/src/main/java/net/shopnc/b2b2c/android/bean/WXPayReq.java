package net.shopnc.b2b2c.android.bean;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/7/6 13:22.
 */
public class WXPayReq {
    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;
    private String sign;

    public WXPayReq(String appid, String partnerid, String prepayid, String noncestr, String timestamp, String sign) {
        this.appid = appid;
        this.partnerid = partnerid;
        this.prepayid = prepayid;
        this.noncestr = noncestr;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
