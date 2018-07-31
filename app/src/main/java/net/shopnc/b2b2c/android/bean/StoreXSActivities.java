package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 店铺 “限时活动”
 *
 * Created by huting on 2016/1/4.
 */
public class StoreXSActivities {

    public static class Attr{
        public static final String XIANSHI_ID = "xianshi_id";
        public static final String LOWER_LIMIT = "lower_limit";
        public static final String QUOTA_ID = "quota_id";
        public static final String STATE = "state";
        public static final String STORE_ID = "store_id";
        public static final String XIANSHI_NAME = "xianshi_name";
        public static final String EDITABLE = "editable";
        public static final String MEMBER_ID = "member_id";
        public static final String XIANSHI_TITLE = "xianshi_title";
        public static final String END_TIME = "end_time";
        public static final String START_TIME = "start_time";
        public static final String END_TIME_TEXT = "end_time_text";
        public static final String XIANSHI_STATE_TEXT = "xianshi_state_text";
        public static final String MEMBER_NAME = "member_name";
        public static final String STORE_NAME = "store_name";
        public static final String START_TIME_TEXT = "start_time_text";
        public static final String XIANSHI_EXPLAIN = "xianshi_explain";

    }

    private String xianshi_id;
    private String lower_limit;
    private String quota_id;
    private String state;
    private String store_id;
    private String xianshi_name;
    private String editable;
    private String member_id;
    private String xianshi_title;
    private String end_time;
    private String start_time;
    private String end_time_text;
    private String xianshi_state_text;
    private String member_name;
    private String store_name;
    private String start_time_text;
    private String xianshi_explain;

    public StoreXSActivities(){}

    public StoreXSActivities(String xianshi_id, String lower_limit, String quota_id,
                             String state, String store_id, String xianshi_name, String editable,
                             String member_id, String xianshi_title, String end_time, String start_time,
                             String end_time_text, String xianshi_state_text, String member_name,
                             String store_name, String start_time_text, String xianshi_explain) {
        this.xianshi_id = xianshi_id;
        this.lower_limit = lower_limit;
        this.quota_id = quota_id;
        this.state = state;
        this.store_id = store_id;
        this.xianshi_name = xianshi_name;
        this.editable = editable;
        this.member_id = member_id;
        this.xianshi_title = xianshi_title;
        this.end_time = end_time;
        this.start_time = start_time;
        this.end_time_text = end_time_text;
        this.xianshi_state_text = xianshi_state_text;
        this.member_name = member_name;
        this.store_name = store_name;
        this.start_time_text = start_time_text;
        this.xianshi_explain = xianshi_explain;
    }


    public static StoreXSActivities newInstanceList(String jsonDatas){
        StoreXSActivities AdvertDatas = new StoreXSActivities();
        try {
             JSONObject obj = new JSONObject(jsonDatas);
             String xianshi_id = obj.optString(Attr.XIANSHI_ID);
             String lower_limit = obj.optString(Attr.LOWER_LIMIT);
             String quota_id = obj.optString(Attr.QUOTA_ID);
             String state = obj.optString(Attr.STATE);
             String store_id = obj.optString(Attr.STORE_ID);
             String xianshi_name = obj.optString(Attr.XIANSHI_NAME);
             String editable = obj.optString(Attr.EDITABLE);
             String member_id = obj.optString(Attr.MEMBER_ID);
             String xianshi_title = obj.optString(Attr.XIANSHI_TITLE);
             String end_time = obj.optString(Attr.END_TIME);
             String start_time = obj.optString(Attr.START_TIME);
             String end_time_text = obj.optString(Attr.END_TIME_TEXT);
             String xianshi_state_text = obj.optString(Attr.XIANSHI_STATE_TEXT);
             String member_name = obj.optString(Attr.MEMBER_NAME);
             String store_name = obj.optString(Attr.STORE_NAME);
             String start_time_text = obj.optString(Attr.START_TIME_TEXT);
             String xianshi_explain = obj.optString(Attr.XIANSHI_EXPLAIN);

             AdvertDatas = new StoreXSActivities(xianshi_id, lower_limit, quota_id, state,
                     store_id,xianshi_name,editable,member_id,xianshi_title,end_time,start_time,
                     end_time_text,xianshi_state_text,member_name,store_name,start_time_text,xianshi_explain);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return AdvertDatas;
    }

    public String getXianshi_id() {
        return xianshi_id;
    }

    public void setXianshi_id(String xianshi_id) {
        this.xianshi_id = xianshi_id;
    }

    public String getLower_limit() {
        return lower_limit;
    }

    public void setLower_limit(String lower_limit) {
        this.lower_limit = lower_limit;
    }

    public String getQuota_id() {
        return quota_id;
    }

    public void setQuota_id(String quota_id) {
        this.quota_id = quota_id;
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

    public String getXianshi_name() {
        return xianshi_name;
    }

    public void setXianshi_name(String xianshi_name) {
        this.xianshi_name = xianshi_name;
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

    public String getXianshi_title() {
        return xianshi_title;
    }

    public void setXianshi_title(String xianshi_title) {
        this.xianshi_title = xianshi_title;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time_text() {
        return end_time_text;
    }

    public void setEnd_time_text(String end_time_text) {
        this.end_time_text = end_time_text;
    }

    public String getXianshi_state_text() {
        return xianshi_state_text;
    }

    public void setXianshi_state_text(String xianshi_state_text) {
        this.xianshi_state_text = xianshi_state_text;
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

    public String getXianshi_explain() {
        return xianshi_explain;
    }

    public void setXianshi_explain(String xianshi_explain) {
        this.xianshi_explain = xianshi_explain;
    }

    @Override
    public String toString() {
        return "StoreXSActivities{" +
                "xianshi_id='" + xianshi_id + '\'' +
                ", lower_limit='" + lower_limit + '\'' +
                ", quota_id='" + quota_id + '\'' +
                ", state='" + state + '\'' +
                ", store_id='" + store_id + '\'' +
                ", xianshi_name='" + xianshi_name + '\'' +
                ", editable='" + editable + '\'' +
                ", member_id='" + member_id + '\'' +
                ", xianshi_title='" + xianshi_title + '\'' +
                ", end_time='" + end_time + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time_text='" + end_time_text + '\'' +
                ", xianshi_state_text='" + xianshi_state_text + '\'' +
                ", member_name='" + member_name + '\'' +
                ", store_name='" + store_name + '\'' +
                ", start_time_text='" + start_time_text + '\'' +
                ", xianshi_explain='" + xianshi_explain + '\'' +
                '}';
    }
}
