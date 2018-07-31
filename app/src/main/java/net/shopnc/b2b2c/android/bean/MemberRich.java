package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 作者：Jie on 2016/6/22 17:47
 */
public class MemberRich implements Serializable {


    int points;//  会员积分
    int predeposit;// 会员预存款
    long redpacket; //会员可用红包数量
    long voucher; //会员可用优惠券数量
    int expPoints; //会员经验值

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPredeposit() {
        return predeposit;
    }

    public void setPredeposit(int predeposit) {
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
