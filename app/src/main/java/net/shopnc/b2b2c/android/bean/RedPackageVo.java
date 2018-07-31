package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description 平台红包
 * @Author qyf
 *
 * Created 2016/5/6 18:43.
 */
public class RedPackageVo {
    /**
     * 自增编码
     */
    private int redPackageId;
    /**
     * 红包编码
     */
    private String redPackageCode = "";
    /**
     * 红包名称
     */
    private String redPackageTitle = "";
    /**
     * 面额
     */
    private BigDecimal redPackagePrice = new BigDecimal(0);
    /**
     * 红包使用时的订单限额
     */
    private BigDecimal limitAmount = new BigDecimal(0);
    /**
     * 红包有效期开始时间
     */
    private String startTimeText = "";
    /**
     * 红包有效期结束时间
     */
    private String endTimeText = "";

    public RedPackageVo(int redPackageId, String redPackageCode, String redPackageTitle, BigDecimal redPackagePrice, BigDecimal limitAmount, String startTimeText, String endTimeText) {
        this.redPackageId = redPackageId;
        this.redPackageCode = redPackageCode;
        this.redPackageTitle = redPackageTitle;
        this.redPackagePrice = redPackagePrice;
        this.limitAmount = limitAmount;
        this.startTimeText = startTimeText;
        this.endTimeText = endTimeText;
    }

    public int getRedPackageId() {
        return redPackageId;
    }

    public void setRedPackageId(int redPackageId) {
        this.redPackageId = redPackageId;
    }

    public String getRedPackageCode() {
        return redPackageCode;
    }

    public void setRedPackageCode(String redPackageCode) {
        this.redPackageCode = redPackageCode;
    }

    public String getRedPackageTitle() {
        return redPackageTitle;
    }

    public void setRedPackageTitle(String redPackageTitle) {
        this.redPackageTitle = redPackageTitle;
    }

    public BigDecimal getRedPackagePrice() {
        return redPackagePrice;
    }

    public void setRedPackagePrice(BigDecimal redPackagePrice) {
        this.redPackagePrice = redPackagePrice;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String getStartTimeText() {
        return startTimeText;
    }

    public void setStartTimeText(String startTimeText) {
        this.startTimeText = startTimeText;
    }

    public String getEndTimeText() {
        return endTimeText;
    }

    public void setEndTimeText(String endTimeText) {
        this.endTimeText = endTimeText;
    }

    @Override
    public String toString() {
        return "RedPackageVo{" +
                "redPackageId=" + redPackageId +
                ", redPackageCode='" + redPackageCode + '\'' +
                ", redPackageTitle='" + redPackageTitle + '\'' +
                ", redPackagePrice=" + redPackagePrice +
                ", limitAmount=" + limitAmount +
                ", startTimeText='" + startTimeText + '\'' +
                ", endTimeText='" + endTimeText + '\'' +
                '}';
    }
}
