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
public class PdrechargeInfo {
    private String id;
    private String sn;
    private String memberId;
    private String memberName;
    private String amount;
    private String paymentCode;
    private String paymentName;
    private String tradeSn;
    private String addTime;
    private String addTimeText;
    private String paymentState;
    private String paymentStateText;
    private String paymentTime;
    private String admin;

    public PdrechargeInfo(String id, String sn, String memberId, String memberName, String amount, String paymentCode, String paymentName, String tradeSn, String addTime, String addTimeText, String paymentState, String paymentStateText, String paymentTime, String admin) {
        this.id = id;
        this.sn = sn;
        this.memberId = memberId;
        this.memberName = memberName;
        this.amount = amount;
        this.paymentCode = paymentCode;
        this.paymentName = paymentName;
        this.tradeSn = tradeSn;
        this.addTime = addTime;
        this.addTimeText = addTimeText;
        this.paymentState = paymentState;
        this.paymentStateText = paymentStateText;
        this.paymentTime = paymentTime;
        this.admin = admin;
    }

    public static ArrayList<PdrechargeInfo> newInstanceList(String json) {
        ArrayList<PdrechargeInfo> list = new ArrayList<PdrechargeInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.optString("pdr_id");
                String sn = obj.optString("pdr_sn");
                String memberId = obj.optString("pdr_member_id");
                String memberName = obj.optString("pdr_member_name");
                String amount = obj.optString("pdr_amount");
                String paymentCode = obj.optString("pdr_payment_code");
                String paymentName = obj.optString("pdr_payment_name");
                String tradeSn = obj.optString("pdr_trade_sn");
                String addTime = obj.optString("pdr_add_time");
                String addTimeText = obj.optString("pdr_add_time_text");
                String paymentState = obj.optString("pdr_payment_state");
                String paymentStateText = obj.optString("pdr_payment_state_text");
                String paymentTime = obj.optString("pdr_payment_time");
                String admin = obj.optString("pdr_admin");
                list.add(new PdrechargeInfo(id, sn, memberId, memberName, amount, paymentCode, paymentName, tradeSn, addTime, addTimeText, paymentState, paymentStateText, paymentTime, admin));
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getTradeSn() {
        return tradeSn;
    }

    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn;
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

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getPaymentStateText() {
        return paymentStateText;
    }

    public void setPaymentStateText(String paymentStateText) {
        this.paymentStateText = paymentStateText;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "PdrechargeInfo{" +
                "id='" + id + '\'' +
                ", sn='" + sn + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", amount='" + amount + '\'' +
                ", paymentCode='" + paymentCode + '\'' +
                ", paymentName='" + paymentName + '\'' +
                ", tradeSn='" + tradeSn + '\'' +
                ", addTime='" + addTime + '\'' +
                ", addTimeText='" + addTimeText + '\'' +
                ", paymentState='" + paymentState + '\'' +
                ", paymentStateText='" + paymentStateText + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }
}
