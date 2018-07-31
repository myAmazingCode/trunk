package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 积分日志Bean
 *
 * @author dqw
 * @date 2015/8/25
 */
public class PointLogInfo {
    private String id;
    private String memberId;
    private String memberName;
    private String adminId;
    private String adminName;
    private String points;
    private String addTime;
    private String addTimeText;
    private String desc;
    private String stage;
    private String stageText;

    public PointLogInfo(String id, String memberId, String memberName, String adminId, String adminName, String points, String addTime, String addTimeText, String desc, String stage, String stageText) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.adminId = adminId;
        this.adminName = adminName;
        this.points = points;
        this.addTime = addTime;
        this.addTimeText = addTimeText;
        this.desc = desc;
        this.stage = stage;
        this.stageText = stageText;
    }

    public static ArrayList<PointLogInfo> newInstanceList(String json) {
        ArrayList<PointLogInfo> list = new ArrayList<PointLogInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.optString("pl_id");
                String memberId = obj.optString("pl_memberid");
                String memberName = obj.optString("pl_membername");
                String adminId = obj.optString("pl_adminid");
                String adminName = obj.optString("pl_adminname");
                String points = obj.optString("pl_points");
                String addTime = obj.optString("pl_addtime");
                String addTimeText = obj.optString("addtimetext");
                String desc = obj.optString("pl_desc");
                String stage = obj.optString("pl_stage");
                String stageText = obj.optString("stagetext");
                list.add(new PointLogInfo(id, memberId, memberName, adminId, adminName, points, addTime, addTimeText, desc, stage, stageText));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddTimeText() {
        return addTimeText;
    }

    public void setAddTimeText(String addTimeText) {
        this.addTimeText = addTimeText;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStageText() {
        return stageText;
    }

    public void setStageText(String stageText) {
        this.stageText = stageText;
    }

    @Override
    public String toString() {
        return "PointLogInfo{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", points='" + points + '\'' +
                ", addTime='" + addTime + '\'' +
                ", addTimeText='" + addTimeText + '\'' +
                ", desc='" + desc + '\'' +
                ", stage='" + stage + '\'' +
                ", stageText='" + stageText + '\'' +
                '}';
    }
}

