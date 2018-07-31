package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 店铺活动--满即送
 *
 * Created by huting on 2016/1/4.
 */
public class StoreMSActivities{
    public static class Attr{
        public static final String REMARK = "remark";
        public static final String QUOTA_ID = "quota_id";
        public static final String MANSONG_STATE_TEXT = "mansong_state_text";
        public static final String STATE = "state";
        public static final String STORE_ID = "store_id";
        public static final String EDITABLE = "editable";
        public static final String MEMBER_ID = "member_id";
        public static final String END_TIME = "end_time";
        public static final String MANSONG_ID = "mansong_id";
        public static final String START_TIME = "start_time";
        public static final String MANSONG_NAME = "mansong_name";
        public static final String END_TIME_TEXT = "end_time_text";
        public static final String MEMBER_NAME = "member_name";
        public static final String STORE_NAME = "store_name";
        public static final String START_TIME_TEXT = "start_time_text";
        public static final String RULES = "rules";
    }

    private String remark;
    private String quota_id;
    private String mansong_state_text;
    private String state;
    private String store_id;
    private String editable;
    private String member_id;
    private String end_time;
    private String mansong_id;
    private String start_time;
    private String mansong_name;
    private String end_time_text;
    private String member_name;
    private String store_name;
    private String start_time_text;
    private String rules;


    public StoreMSActivities(){}
    public StoreMSActivities(String rules, String remark, String quota_id, String mansong_state_text,
                             String state, String store_id, String editable, String member_id, String end_time,
                             String mansong_id, String start_time, String mansong_name, String end_time_text,
                             String member_name, String store_name, String start_time_text) {
        this.rules = rules;
        this.remark = remark;
        this.quota_id = quota_id;
        this.mansong_state_text = mansong_state_text;
        this.state = state;
        this.store_id = store_id;
        this.editable = editable;
        this.member_id = member_id;
        this.end_time = end_time;
        this.mansong_id = mansong_id;
        this.start_time = start_time;
        this.mansong_name = mansong_name;
        this.end_time_text = end_time_text;
        this.member_name = member_name;
        this.store_name = store_name;
        this.start_time_text = start_time_text;
    }


    public static StoreMSActivities newInstanceList(String jsonDatas){
        StoreMSActivities AdvertDatas = new StoreMSActivities();
        try {
            JSONObject obj = new JSONObject(jsonDatas);

            String rules = obj.optString(Attr.RULES);
            String remark = obj.optString(Attr.REMARK);
            String quota_id = obj.optString(Attr.QUOTA_ID);
            String mansong_state_text = obj.optString(Attr.MANSONG_STATE_TEXT);
            String state = obj.optString(Attr.STATE);
            String store_id = obj.optString(Attr.STORE_ID);
            String editable = obj.optString(Attr.EDITABLE);
            String member_id = obj.optString(Attr.MEMBER_ID);
            String end_time = obj.optString(Attr.END_TIME);
            String mansong_id = obj.optString(Attr.MANSONG_ID);
            String start_time = obj.optString(Attr.START_TIME);
            String mansong_name = obj.optString(Attr.MANSONG_NAME);
            String end_time_text = obj.optString(Attr.END_TIME_TEXT);
            String member_name = obj.optString(Attr.MEMBER_NAME);
            String store_name = obj.optString(Attr.STORE_NAME);
            String start_time_text = obj.optString(Attr.START_TIME_TEXT);

            AdvertDatas.setRules(rules);
            AdvertDatas.setRemark(remark);
            AdvertDatas.setQuota_id(quota_id);
            AdvertDatas.setMansong_state_text(mansong_state_text);
            AdvertDatas.setState(state);
            AdvertDatas.setStore_id(store_id);
            AdvertDatas.setEditable(editable);
            AdvertDatas.setMember_id(member_id);
            AdvertDatas.setEnd_time(end_time);
            AdvertDatas.setMansong_id(mansong_id);
            AdvertDatas.setStart_time(start_time);
            AdvertDatas.setMansong_name(mansong_name);
            AdvertDatas.setEnd_time_text(end_time_text);
            AdvertDatas.setMember_name(member_name);
            AdvertDatas.setStore_name(store_name);
            AdvertDatas.setStart_time_text(start_time_text);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return AdvertDatas;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQuota_id() {
        return quota_id;
    }

    public void setQuota_id(String quota_id) {
        this.quota_id = quota_id;
    }

    public String getMansong_state_text() {
        return mansong_state_text;
    }

    public void setMansong_state_text(String mansong_state_text) {
        this.mansong_state_text = mansong_state_text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getMansong_id() {
        return mansong_id;
    }

    public void setMansong_id(String mansong_id) {
        this.mansong_id = mansong_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getMansong_name() {
        return mansong_name;
    }

    public void setMansong_name(String mansong_name) {
        this.mansong_name = mansong_name;
    }

    public String getEnd_time_text() {
        return end_time_text;
    }

    public void setEnd_time_text(String end_time_text) {
        this.end_time_text = end_time_text;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStart_time_text() {
        return start_time_text;
    }

    public void setStart_time_text(String start_time_text) {
        this.start_time_text = start_time_text;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "StoreMSActivities{" +
                "remark='" + remark + '\'' +
                ", quota_id='" + quota_id + '\'' +
                ", mansong_state_text='" + mansong_state_text + '\'' +
                ", state='" + state + '\'' +
                ", store_id='" + store_id + '\'' +
                ", editable='" + editable + '\'' +
                ", member_id='" + member_id + '\'' +
                ", end_time='" + end_time + '\'' +
                ", mansong_id='" + mansong_id + '\'' +
                ", start_time='" + start_time + '\'' +
                ", mansong_name='" + mansong_name + '\'' +
                ", end_time_text='" + end_time_text + '\'' +
                ", member_name='" + member_name + '\'' +
                ", store_name='" + store_name + '\'' +
                ", start_time_text='" + start_time_text + '\'' +
                ", rules='" + rules + '\'' +
                '}';
    }
}
