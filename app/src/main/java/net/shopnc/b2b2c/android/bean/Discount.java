package net.shopnc.b2b2c.android.bean;


/**
 * @Description 商品详情页折扣信息实体
 * @Author qyf
 *
 * Created 2016/4/18 16:38.
 */
public class Discount {
    /**
     * 折扣促销编号
     */
    private int discountId;

    /**
     * 店铺编号
     */
    private int storeId;

    /**
     * 折扣名称
     */
    private String discountName;

    /**
     * 折扣
     */
    private double discountRate;

    /**
     * 折扣标题
     */
    private String discountTitle;
    /**
     * 折扣标题最终
     */
    private String discountTitleFinal;

    /**
     * 折扣说明
     */
    private String discountExplain;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 活动状态
     */
    private int discountState;

    /**
     * 活动状态文字
     */
    private String discountStatus;
    /**
     * 促销倒计时(秒)
     * 未满足条件为0
     */
    private long promotionCountDownTime;
    /**
     * 促销倒计时文字描述
     */
    private String promotionCountDownTimeText;

    public long getPromotionCountDownTime() {
        return promotionCountDownTime;
    }

    public void setPromotionCountDownTime(long promotionCountDownTime) {
        this.promotionCountDownTime = promotionCountDownTime;
    }

    /**
     * 参数商品数量
     */


    private long goodsCount;

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }

    public String getDiscountTitleFinal() {
        return discountTitleFinal;
    }

    public void setDiscountTitleFinal(String discountTitleFinal) {
        this.discountTitleFinal = discountTitleFinal;
    }

    public String getDiscountExplain() {
        return discountExplain;
    }

    public void setDiscountExplain(String discountExplain) {
        this.discountExplain = discountExplain;
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

    public int getDiscountState() {
        return discountState;
    }

    public void setDiscountState(int discountState) {
        this.discountState = discountState;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId=" + discountId +
                ", storeId=" + storeId +
                ", discountName='" + discountName + '\'' +
                ", discountRate=" + discountRate +
                ", discountTitle='" + discountTitle + '\'' +
                ", discountTitleFinal='" + discountTitleFinal + '\'' +
                ", discountExplain='" + discountExplain + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", discountState=" + discountState +
                ", discountStatus='" + discountStatus + '\'' +
                ", promotionCountDownTime=" + promotionCountDownTime +
                ", promotionCountDownTimeText='" + promotionCountDownTimeText + '\'' +
                ", goodsCount=" + goodsCount +
                '}';
    }
}
