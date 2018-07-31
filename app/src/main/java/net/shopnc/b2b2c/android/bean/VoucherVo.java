package net.shopnc.b2b2c.android.bean;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 优惠券Vo
 * Created by hbj on 2016/4/11.
 */
public class VoucherVo {
    /**
     * 自增编码
     */
    private int voucherId;
    /**
     * 优惠券名称
     */
    private String voucherTitle = "";
    /**
     * 优惠券有效期开始时间
     */
    private String startTime;
    /**
     * 优惠券有效期结束时间
     */
    private String endTime;
    /**
     * 面额
     */
    private BigDecimal price = new BigDecimal(0);
    /**
     * 优惠券使用时的订单限额
     */
    private BigDecimal limitAmount = new BigDecimal(0);
    /**
     * 店铺Id
     */
    private int storeId = 0;

    public VoucherVo(Voucher voucher) {
        this.voucherId = voucher.getVoucherId();
        this.voucherTitle = voucher.getVoucherTitle();
        this.startTime = new SimpleDateFormat("yyyy-MM-dd").format(voucher.getStartTime()).toString();
        this.endTime = new SimpleDateFormat("yyyy-MM-dd").format(voucher.getEndTime()).toString();
        this.price = voucher.getPrice();
        this.limitAmount = voucher.getLimitAmount();
        this.storeId = voucher.getStoreId();
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherTitle() {
        return voucherTitle;
    }

    public void setVoucherTitle(String voucherTitle) {
        this.voucherTitle = voucherTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "VoucherVo{" +
                "voucherId=" + voucherId +
                ", voucherTitle='" + voucherTitle + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", price=" + price +
                ", limitAmount=" + limitAmount +
                ", storeId=" + storeId +
                '}';
    }
}
