package net.shopnc.b2b2c.android.bean;

/**
 * 预存款日志Bean
 */
public class DepositLogInfo {

    /**
     * 日志序号
     */
    private String id;
    /**
     * 是否有更多
     */
    private boolean hasMore;
    /**
     * 信息总页数
     */
    private int totalPage;
    /**
     * 会员ID
     */
    private int memberId;
    /**
     * 处理操作
     */
    private String operationStage;
    /**
     * 可用余额
     */
    private double availableAmount;
    /**
     * 被冻结余额
     */
    private double freezeAmount;
    /**
     * 操作的时间
     */
    private String addTime;
    /**
     * 相关描述
     */
    private String description;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getOperationStage() {
        return operationStage;
    }

    public void setOperationStage(String operationStage) {
        this.operationStage = operationStage;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public double getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(double freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id){this.id = id;}
    public String getId(){
        return  id;
    }
}

