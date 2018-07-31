package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 红包Bean
 * <p/>
 * dqw
 * 2015/8/27
 */
public class RedpacketInfo {

    private String id;
    private String code;
    private String tId;
    private String title;
    private String desc;
    private String startDate;
    private String endDate;
    private String price;
    private String limit;
    private String state;
    private String activieDate;
    private String ownerId;
    private String ownerName;
    private String orderId;
    private String customimg;
    private String customimgUrl;
    private String stateText;
    private String stateKey;
    private String endDateText;
    private String rpacket_state_text;
    public RedpacketInfo(String id, String code, String tId, String title, String desc, String startDate, String endDate, String price, String limit, String state, String activieDate, String ownerId, String ownerName, String orderId, String customimg, String customimgUrl, String stateText, String stateKey, String endDateText,String rpacket_state_text) {
        this.id = id;
        this.code = code;
        this.tId = tId;
        this.title = title;
        this.desc = desc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.limit = limit;
        this.state = state;
        this.activieDate = activieDate;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.orderId = orderId;
        this.customimg = customimg;
        this.customimgUrl = customimgUrl;
        this.stateText = stateText;
        this.stateKey = stateKey;
        this.endDateText = endDateText;
        this.rpacket_state_text = rpacket_state_text;
    }

    public static ArrayList<RedpacketInfo> newInstanceList(String json) {
        ArrayList<RedpacketInfo> list = new ArrayList<RedpacketInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.optString("rpacket_id");
                String code = obj.optString("rpacket_code");
                String tId = obj.optString("rpacket_t_id");
                String title = obj.optString("rpacket_title");
                String desc = obj.optString("rpacket_desc");
                String startDate = obj.optString("rpacket_start_date");
                String endDate = obj.optString("rpacket_end_date");
                String price = obj.optString("rpacket_price");
                String limit = obj.optString("rpacket_limit");
                String state = obj.optString("rpacket_state");
                String activeDate = obj.optString("rpacket_active_date");
                String ownerId = obj.optString("rpacket_owner_id");
                String ownerName = obj.optString("rpacket_owner_name");
                String orderId = obj.optString("rpacket_order_id");
                String customimg = obj.optString("rpacket_customimg");
                String customimgUrl = obj.optString("rpacket_customimg_url");
                String stateText = obj.optString("rpacket_state_text");
                String stateKey = obj.optString("rpacket_state_key");
                String endDateText = obj.optString("rpacket_end_date_text");
                String rpacket_state_text = obj.optString("rpacket_state_text");
                list.add(new RedpacketInfo(id, code, tId, title, desc, startDate, endDate, price, limit, state, activeDate, ownerId, ownerName, orderId, customimg, customimgUrl, stateText, stateKey, endDateText,rpacket_state_text));
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getActivieDate() {
        return activieDate;
    }

    public void setActivieDate(String activieDate) {
        this.activieDate = activieDate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomimg() {
        return customimg;
    }

    public void setCustomimg(String customimg) {
        this.customimg = customimg;
    }

    public String getCustomimgUrl() {
        return customimgUrl;
    }

    public void setCustomimgUrl(String customimgUrl) {
        this.customimgUrl = customimgUrl;
    }

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    public String getEndDateText() {
        return endDateText;
    }

    public void setEndDateText(String endDateText) {
        this.endDateText = endDateText;
    }

    @Override
    public String toString() {
        return "RedpacketInfo{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", tId='" + tId + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", price='" + price + '\'' +
                ", limit='" + limit + '\'' +
                ", state='" + state + '\'' +
                ", activieDate='" + activieDate + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customimg='" + customimg + '\'' +
                ", customimgUrl='" + customimgUrl + '\'' +
                ", stateText='" + stateText + '\'' +
                ", stateKey='" + stateKey + '\'' +
                ", endDateText='" + endDateText + '\'' +
                '}';
    }

    public String getRpacket_state_text() {
        return rpacket_state_text;
    }

    public void setRpacket_state_text(String rpacket_state_text) {
        this.rpacket_state_text = rpacket_state_text;
    }
}
