package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 预存款提现Bean
 * <p/>
 * dqw
 * 2015/8/25
 */
public class PdcashInfo {
    private String id;
    private String sn;
    private String memberId;
    private String memberName;
    private String amount;
    private String bankName;
    private String bankNo;
    private String bankUser;
    private String addTime;
    private String addTimeText;
    private String paymentTime;
    private String paymentTimeText;
    private String paymentState;
    private String paymentStateText;
    private String paymentAdmin;

    public PdcashInfo(String id, String sn, String memberId, String memberName, String amount, String bankName, String bankNo, String bankUser, String addTime, String addTimeText, String paymentTime, String paymentTimeText, String paymentState, String paymentStateText, String paymentAdmin) {
        this.id = id;
        this.sn = sn;
        this.memberId = memberId;
        this.memberName = memberName;
        this.amount = amount;
        this.bankName = bankName;
        this.bankNo = bankNo;
        this.bankUser = bankUser;
        this.addTime = addTime;
        this.addTimeText = addTimeText;
        this.paymentTime = paymentTime;
        this.paymentTimeText = paymentTimeText;
        this.paymentState = paymentState;
        this.paymentStateText = paymentStateText;
        this.paymentAdmin = paymentAdmin;
    }

    public static ArrayList<PdcashInfo> newInstanceList(String json) {
        ArrayList<PdcashInfo> list = new ArrayList<PdcashInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.optString("pdc_id");
                String sn = obj.optString("pdc_sn");
                String memberId = obj.optString("pdc_member_id");
                String memberName = obj.optString("pdc_member_name");
                String amount = obj.optString("pdc_amount");
                String bankName = obj.optString("pdc_bank_name");
                String bankNo = obj.optString("pdc_bank_no");
                String bankUser = obj.optString("pdc_bank_user");
                String addTime = obj.optString("pdc_add_time");
                String addTimeText = obj.optString("pdc_add_time_text");
                String paymentTime = obj.optString("pdc_payment_time");
                String paymentTimeText = obj.optString("pdc_payment_time_text");
                String paymentState = obj.optString("pdc_payment_state");
                String paymentStateText = obj.optString("pdc_payment_state_text");
                String paymentAdmin = obj.optString("pdc_payment_admin");
                list.add(new PdcashInfo(id, sn, memberId, memberName, amount, bankName, bankNo, bankUser, addTime, addTimeText, paymentTime, paymentTimeText, paymentState, paymentStateText, paymentAdmin));
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankUser() {
        return bankUser;
    }

    public void setBankUser(String bankUser) {
        this.bankUser = bankUser;
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

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentTimeText() {
        return paymentTimeText;
    }

    public void setPaymentTimeText(String paymentTimeText) {
        this.paymentTimeText = paymentTimeText;
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

    public String getPaymentAdmin() {
        return paymentAdmin;
    }

    public void setPaymentAdmin(String paymentAdmin) {
        this.paymentAdmin = paymentAdmin;
    }

    @Override
    public String toString() {
        return "PdcashInfo{" +
                "id='" + id + '\'' +
                ", sn='" + sn + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", amount='" + amount + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", bankUser='" + bankUser + '\'' +
                ", addTime='" + addTime + '\'' +
                ", addTimeText='" + addTimeText + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", paymentTimeText='" + paymentTimeText + '\'' +
                ", paymentState='" + paymentState + '\'' +
                ", paymentStateText='" + paymentStateText + '\'' +
                ", paymentAdmin='" + paymentAdmin + '\'' +
                '}';
    }
}

