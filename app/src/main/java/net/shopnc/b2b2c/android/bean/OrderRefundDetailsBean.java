package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/19.
 */
public class OrderRefundDetailsBean {
    private String refund_id;

    private String admin_state_v;

    private String refund_sn;

    private String admin_state;

    private String goods_list ;

    private String seller_state;

    private String seller_state_v;

    private String store_id;

    private String order_sn;

    private String add_time;

    private String refund_amount;

    private String order_id;

    private String store_name;

    public OrderRefundDetailsBean() {
    }


    public OrderRefundDetailsBean(String refund_id, String admin_state_v, String refund_sn, String admin_state, String goods_list, String seller_state, String seller_state_v, String store_id, String order_sn, String add_time, String refund_amount, String order_id, String store_name) {
        this.refund_id = refund_id;
        this.admin_state_v = admin_state_v;
        this.refund_sn = refund_sn;
        this.admin_state = admin_state;
        this.goods_list = goods_list;
        this.seller_state = seller_state;
        this.seller_state_v = seller_state_v;
        this.store_id = store_id;
        this.order_sn = order_sn;
        this.add_time = add_time;
        this.refund_amount = refund_amount;
        this.order_id = order_id;
        this.store_name = store_name;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getAdmin_state_v() {
        return admin_state_v;
    }

    public void setAdmin_state_v(String admin_state_v) {
        this.admin_state_v = admin_state_v;
    }

    public String getRefund_sn() {
        return refund_sn;
    }

    public void setRefund_sn(String refund_sn) {
        this.refund_sn = refund_sn;
    }

    public String getAdmin_state() {
        return admin_state;
    }

    public void setAdmin_state(String admin_state) {
        this.admin_state = admin_state;
    }

    public String getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(String goods_list) {
        this.goods_list = goods_list;
    }

    public String getSeller_state() {
        return seller_state;
    }

    public void setSeller_state(String seller_state) {
        this.seller_state = seller_state;
    }

    public String getSeller_state_v() {
        return seller_state_v;
    }

    public void setSeller_state_v(String seller_state_v) {
        this.seller_state_v = seller_state_v;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
}
