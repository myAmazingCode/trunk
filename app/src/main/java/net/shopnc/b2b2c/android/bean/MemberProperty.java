package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.bean.MemberProperty.java
 * @author: Jie
 * @date: 2016-05-30 15:51
 */
public class MemberProperty {

    private int points;
    BigDecimal predeposit = new BigDecimal(0);
    long redpacket;
    long voucher;
    int expPoints;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public BigDecimal getPredeposit() {
        return predeposit;
    }

    public void setPredeposit(BigDecimal predeposit) {
        this.predeposit = predeposit;
    }

    public long getRedpacket() {
        return redpacket;
    }

    public void setRedpacket(long redpacket) {
        this.redpacket = redpacket;
    }

    public long getVoucher() {
        return voucher;
    }

    public void setVoucher(long voucher) {
        this.voucher = voucher;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public void setExpPoints(int expPoints) {
        this.expPoints = expPoints;
    }
}
