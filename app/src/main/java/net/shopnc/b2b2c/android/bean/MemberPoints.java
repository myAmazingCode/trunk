package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.bean.MemberPoints.java
 * @author: Jie
 * @date: 2016-05-31 14:48
 */
public class MemberPoints implements Serializable {

    private static final String TAG = "MemberPoints";

    boolean hasMore;
    int totalPage;
    int logId;
    int memberId;
    int points;
    String addTime;
    String description;
    String operationStage;
    String operationStageText;

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

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

    public String getOperationStage() {
        return operationStage;
    }

    public void setOperationStage(String operationStage) {
        this.operationStage = operationStage;
    }

    public String getOperationStageText() {
        return operationStageText;
    }

    public void setOperationStageText(String operationStageText) {
        this.operationStageText = operationStageText;
    }
}
