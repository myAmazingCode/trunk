package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 虚拟订单详情
 *
 * @author dqw
 * @Time 2015/8/14
 */
public class VirtualInfo {
    private String orderId;
    private String orderSn;
    private String storeId;
    private String storeName;
    private String addTime;
    private String buyerPhone;
    private String paymentTime;
    private String stateDesc;
    private String goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsNum;
    private String goodsImageUrl;
    private String vrIndate;
    private String ifCancel;
    private String ifRefund;
    private String ifEvaluation;
    private String ifResend;
    private String codeList;
    private String buyerMsg;

    public VirtualInfo(String orderId, String orderSn, String storeId, String storeName, String addTime, String buyerPhone, String paymentTime, String stateDesc, String goodsId, String goodsName, String goodsPrice, String goodsNum, String goodsImageUrl, String vrIndate, String ifCancel, String ifRefund, String ifEvaluation, String ifResend, String codeList, String buyerMsg) {
        this.orderId = orderId;
        this.orderSn = orderSn;
        this.storeId = storeId;
        this.storeName = storeName;
        this.addTime = addTime;
        this.buyerPhone = buyerPhone;
        this.paymentTime = paymentTime;
        this.stateDesc = stateDesc;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsNum = goodsNum;
        this.goodsImageUrl = goodsImageUrl;
        this.vrIndate = vrIndate;
        this.ifCancel = ifCancel;
        this.ifRefund = ifRefund;
        this.ifEvaluation = ifEvaluation;
        this.ifResend = ifResend;
        this.codeList = codeList;
        this.buyerMsg = buyerMsg;
    }

    public static VirtualInfo newInstanceInfo(String json) {
        VirtualInfo bean = null;
        try {
            JSONObject obj = new JSONObject(json);
            if (obj.length() > 0) {
                String orderId = obj.optString("order_id");
                String orderSn = obj.optString("order_sn");
                String storeId = obj.optString("store_id");
                String storeName = obj.optString("store_name");
                String addTime = obj.optString("add_time");
                String buyerPhone = obj.optString("buyer_phone");
                String paymentTime = obj.optString("payment_time");
                String stateDesc = obj.optString("state_desc");
                String goodsId = obj.optString("goods_id");
                String goodsName = obj.optString("goods_name");
                String goodsPrice = obj.optString("goods_price");
                String goodsNum = obj.optString("goods_num");
                String goodsImageUrl = obj.optString("goods_image_url");
                String vrIndate = obj.optString("vr_indate");
                String ifCancel = obj.optString("if_cancel");
                String ifRefund = obj.optString("if_refund");
                String ifEvaluation = obj.optString("if_evaluation");
                String ifResend = obj.optString("if_resend");
                String codeList = obj.optString("code_list");
                String buyerMsg = obj.optString("buyer_msg");
                bean = new VirtualInfo(orderId, orderSn, storeId, storeName, addTime, buyerPhone, paymentTime, stateDesc, goodsId, goodsName, goodsPrice, goodsNum, goodsImageUrl, vrIndate, ifCancel, ifRefund, ifEvaluation, ifResend, codeList, buyerMsg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public String getVrIndate() {
        return vrIndate;
    }

    public void setVrIndate(String vrIndate) {
        this.vrIndate = vrIndate;
    }

    public String getIfCancel() {
        return ifCancel;
    }

    public void setIfCancel(String ifCancel) {
        this.ifCancel = ifCancel;
    }

    public String getIfRefund() {
        return ifRefund;
    }

    public void setIfRefund(String ifRefund) {
        this.ifRefund = ifRefund;
    }

    public String getIfEvaluation() {
        return ifEvaluation;
    }

    public void setIfEvaluation(String ifEvaluation) {
        this.ifEvaluation = ifEvaluation;
    }

    public String getIfResend() {
        return ifResend;
    }

    public void setIfResend(String ifResend) {
        this.ifResend = ifResend;
    }

    public String getCodeList() {
        return codeList;
    }

    public void setCodeList(String codeList) {
        this.codeList = codeList;
    }

    public String getBuyerMsg() {
        return buyerMsg;
    }

    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg;
    }

    @Override
    public String toString() {
        return "VirtualInfo{" +
                "orderId='" + orderId + '\'' +
                ", orderSn='" + orderSn + '\'' +
                ", storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", addTime='" + addTime + '\'' +
                ", buyerPhone='" + buyerPhone + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", stateDesc='" + stateDesc + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsNum='" + goodsNum + '\'' +
                ", goodsImageUrl='" + goodsImageUrl + '\'' +
                ", vrIndate='" + vrIndate + '\'' +
                ", ifCancel='" + ifCancel + '\'' +
                ", ifRefund='" + ifRefund + '\'' +
                ", ifEvaluation='" + ifEvaluation + '\'' +
                ", ifResend='" + ifResend + '\'' +
                ", codeList='" + codeList + '\'' +
                ", buyerMsg='" + buyerMsg + '\'' +
                '}';
    }
}


