package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 预存款日志Bean
 * <p/>
 * dqw
 * 2015/8/25
 */
public class PredepositLogInfo {
    private String id;
    private String memberId;
    private String memberName;
    private String adminName;
    private String type;
    private String avAmount;
    private String freezeAmount;
    private String addTime;
    private String addTimeText;
    private String desc;

    public PredepositLogInfo(String id, String memberId, String memberName, String adminName, String type, String avAmount, String freezeAmount, String addTime, String addTimeText, String desc) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.adminName = adminName;
        this.type = type;
        this.avAmount = avAmount;
        this.freezeAmount = freezeAmount;
        this.addTime = addTime;
        this.addTimeText = addTimeText;
        this.desc = desc;
    }

    public static ArrayList<PredepositLogInfo> newInstanceList(String json) {
        ArrayList<PredepositLogInfo> list = new ArrayList<PredepositLogInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.optString("lg_id");
                String memberId = obj.optString("lg_member_id");
                String memberName = obj.optString("lg_member_name");
                String adminName = obj.optString("lg_admin_name");
                String type = obj.optString("lg_type");
                String avAmount = obj.optString("lg_av_amount");
                String freezeAmount = obj.optString("lg_freeze_amount");
                String addTime = obj.optString("lg_add_time");
                String addTimeText = obj.optString("lg_add_time_text");
                String desc = obj.optString("lg_desc");
                list.add(new PredepositLogInfo(id, memberId, memberName, adminName, type, avAmount, freezeAmount, addTime, addTimeText, desc));
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvAmount() {
        return avAmount;
    }

    public void setAvAmount(String avAmount) {
        this.avAmount = avAmount;
    }

    public String getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(String freezeAmount) {
        this.freezeAmount = freezeAmount;
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

    @Override
    public String toString() {
        return "PredepositLogInfo{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", adminName='" + adminName + '\'' +
                ", type='" + type + '\'' +
                ", avAmount='" + avAmount + '\'' +
                ", freezeAmount='" + freezeAmount + '\'' +
                ", addTime='" + addTime + '\'' +
                ", addTimeText='" + addTimeText + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

