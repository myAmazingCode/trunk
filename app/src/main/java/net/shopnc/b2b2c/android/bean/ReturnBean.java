package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/20.
 */
public class ReturnBean {
    private String refund_id;

    private String refund_sn;

    private String goods_img_360;

    private String buyer_message;

    private String reason_info;

    private String store_id;

    private String goods_name;

    private String admin_message;

    private String order_id;

    private String admin_state;

    private String seller_state;

    private String goods_id;

    private String order_sn;

    private String add_time;

    private String refund_amount;

    private String store_name;

    private String seller_message;


    public ReturnBean() {
    }

    public ReturnBean(String refund_id, String refund_sn, String goods_img_360, String buyer_message, String reason_info, String store_id, String goods_name, String order_id, String admin_state, String seller_state, String goods_id, String order_sn, String add_time, String refund_amount, String store_name,String admin_message,String seller_message) {
        this.refund_id = refund_id;
        this.refund_sn = refund_sn;
        this.goods_img_360 = goods_img_360;
        this.buyer_message = buyer_message;
        this.reason_info = reason_info;
        this.store_id = store_id;
        this.goods_name = goods_name;
        this.order_id = order_id;
        this.admin_state = admin_state;
        this.seller_state = seller_state;
        this.goods_id = goods_id;
        this.order_sn = order_sn;
        this.add_time = add_time;
        this.refund_amount = refund_amount;
        this.store_name = store_name;
        this.admin_message=admin_message;
        this.seller_message=seller_message;
    }


    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_sn() {
        return refund_sn;
    }

    public void setRefund_sn(String refund_sn) {
        this.refund_sn = refund_sn;
    }

    public String getGoods_img_360() {
        return goods_img_360;
    }

    public void setGoods_img_360(String goods_img_360) {
        this.goods_img_360 = goods_img_360;
    }

    public String getReason_info() {
        return reason_info;
    }

    public void setReason_info(String reason_info) {
        this.reason_info = reason_info;
    }

    public String getBuyer_message() {
        return buyer_message;
    }

    public void setBuyer_message(String buyer_message) {
        this.buyer_message = buyer_message;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getAdmin_state() {
        return admin_state;
    }

    public void setAdmin_state(String admin_state) {
        this.admin_state = admin_state;
    }

    public String getSeller_state() {
        return seller_state;
    }

    public void setSeller_state(String seller_state) {
        this.seller_state = seller_state;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(String refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }


    public String getAdmin_message() {
        return admin_message;
    }

    public void setAdmin_message(String admin_message) {
        this.admin_message = admin_message;
    }

    public String getSeller_message() {
        return seller_message;
    }

    public void setSeller_message(String seller_message) {
        this.seller_message = seller_message;
    }
}
