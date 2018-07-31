package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 充值卡充值日志Bean
 * <p/>
 * dqw
 * 2015/8/26
 */
public class RechargeCardLogInfo {
    private String id;
    private String memberId;
    private String memberName;
    private String type;
    private String addTime;
    private String addTimeText;
    private String availableAmount;
    private String freezeAmount;
    private String description;

    public RechargeCardLogInfo(String id, String memberId, String memberName, String type, String addTime, String addTimeText, String availableAmount, String freezeAmount, String description) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.type = type;
        this.addTime = addTime;
        this.addTimeText = addTimeText;
        this.availableAmount = availableAmount;
        this.freezeAmount = freezeAmount;
        this.description = description;
    }

    public static ArrayList<RechargeCardLogInfo> newInstanceList(String json) {
        ArrayList<RechargeCardLogInfo> list = new ArrayList<RechargeCardLogInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.optString("id");
                String memberId = obj.optString("member_id");
                String memberName = obj.optString("member_name");
                String type = obj.optString("type");
                String addTime = obj.optString("add_time");
                String addTimeText = obj.optString("add_time_text");
                String availableAmount = obj.optString("available_amount");
                String freezeAmount = obj.optString("freeze_amount");
                String description = obj.optString("description");
                list.add(new RechargeCardLogInfo(id, memberId, memberName, type, addTime, addTimeText, availableAmount, freezeAmount, description));
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(String freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RechargeCardLogInfo{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", type='" + type + '\'' +
                ", addTime='" + addTime + '\'' +
                ", addTimeText='" + addTimeText + '\'' +
                ", availableAmount='" + availableAmount + '\'' +
                ", freezeAmount='" + freezeAmount + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

