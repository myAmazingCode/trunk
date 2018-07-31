package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/8.
 */
public class OrderDetailsBean {
    private String order_id;
    private String order_sn;
    private String store_id;
    private String store_name;
    private String add_time;
    private String payment_time;
    private String shipping_time;
    private String finnshed_time;
    private String order_amount;
    private String shipping_fee;
    private String real_pay_amount;
    private String state_desc;
    private String payment_name;
    private String order_message;
    private String reciver_phone;
    private String reciver_name;
    private String reciver_addr;
    private String store_member_id;
    private String store_phone;
    private String order_tips;
    private String promotion;
    private String invoice;
    private String if_deliver;
    private String if_buyer_cancel;
    private String if_refund_cancel;
    private String if_receive;
    private String if_evaluation;
    private String if_lock;
    private String goods_list;
    private String zengpin_list;
    private String ownshop;

    public OrderDetailsBean() {
    }

    public OrderDetailsBean(String order_id, String order_sn, String store_id, String store_name, String add_time, String payment_time, String shipping_time, String finnshed_time, String order_amount, String shipping_fee, String real_pay_amount, String state_desc, String payment_name, String order_message, String reciver_phone, String reciver_name, String reciver_addr, String store_member_id, String store_phone, String order_tips, String promotion, String invoice, String if_deliver, String if_buyer_cancel, String if_refund_cancel, String if_receive, String if_evaluation, String if_lock, String goods_list, String zengpin_list, String ownshop) {
        this.order_id = order_id;
        this.order_sn = order_sn;
        this.store_id = store_id;
        this.store_name = store_name;
        this.add_time = add_time;
        this.payment_time = payment_time;
        this.shipping_time = shipping_time;
        this.finnshed_time = finnshed_time;
        this.order_amount = order_amount;
        this.shipping_fee = shipping_fee;
        this.real_pay_amount = real_pay_amount;
        this.state_desc = state_desc;
        this.payment_name = payment_name;
        this.order_message = order_message;
        this.reciver_phone = reciver_phone;
        this.reciver_name = reciver_name;
        this.reciver_addr = reciver_addr;
        this.store_member_id = store_member_id;
        this.store_phone = store_phone;
        this.order_tips = order_tips;
        this.promotion = promotion;
        this.invoice = invoice;
        this.if_deliver = if_deliver;
        this.if_buyer_cancel = if_buyer_cancel;
        this.if_refund_cancel = if_refund_cancel;
        this.if_receive = if_receive;
        this.if_evaluation = if_evaluation;
        this.if_lock = if_lock;
        this.goods_list = goods_list;
        this.zengpin_list = zengpin_list;
        this.ownshop = ownshop;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    public String getShipping_time() {
        return shipping_time;
    }

    public void setShipping_time(String shipping_time) {
        this.shipping_time = shipping_time;
    }

    public String getFinnshed_time() {
        return finnshed_time;
    }

    public void setFinnshed_time(String finnshed_time) {
        this.finnshed_time = finnshed_time;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getReal_pay_amount() {
        return real_pay_amount;
    }

    public void setReal_pay_amount(String real_pay_amount) {
        this.real_pay_amount = real_pay_amount;
    }

    public String getState_desc() {
        return state_desc;
    }

    public void setState_desc(String state_desc) {
        this.state_desc = state_desc;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public String getOrder_message() {
        return order_message;
    }

    public void setOrder_message(String order_message) {
        this.order_message = order_message;
    }

    public String getReciver_phone() {
        return reciver_phone;
    }

    public void setReciver_phone(String reciver_phone) {
        this.reciver_phone = reciver_phone;
    }

    public String getReciver_addr() {
        return reciver_addr;
    }

    public void setReciver_addr(String reciver_addr) {
        this.reciver_addr = reciver_addr;
    }

    public String getReciver_name() {
        return reciver_name;
    }

    public void setReciver_name(String reciver_name) {
        this.reciver_name = reciver_name;
    }

    public String getStore_member_id() {
        return store_member_id;
    }

    public void setStore_member_id(String store_member_id) {
        this.store_member_id = store_member_id;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public String getOrder_tips() {
        return order_tips;
    }

    public void setOrder_tips(String order_tips) {
        this.order_tips = order_tips;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getIf_deliver() {
        return if_deliver;
    }

    public void setIf_deliver(String if_deliver) {
        this.if_deliver = if_deliver;
    }

    public String getIf_buyer_cancel() {
        return if_buyer_cancel;
    }

    public void setIf_buyer_cancel(String if_buyer_cancel) {
        this.if_buyer_cancel = if_buyer_cancel;
    }

    public String getIf_refund_cancel() {
        return if_refund_cancel;
    }

    public void setIf_refund_cancel(String if_refund_cancel) {
        this.if_refund_cancel = if_refund_cancel;
    }

    public String getIf_receive() {
        return if_receive;
    }

    public void setIf_receive(String if_receive) {
        this.if_receive = if_receive;
    }

    public String getIf_evaluation() {
        return if_evaluation;
    }

    public void setIf_evaluation(String if_evaluation) {
        this.if_evaluation = if_evaluation;
    }

    public String getIf_lock() {
        return if_lock;
    }

    public void setIf_lock(String if_lock) {
        this.if_lock = if_lock;
    }

    public String getOwnshop() {
        return ownshop;
    }

    public void setOwnshop(String ownshop) {
        this.ownshop = ownshop;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(String goods_list) {
        this.goods_list = goods_list;
    }

    public String getZengpin_list() {
        return zengpin_list;
    }

    public void setZengpin_list(String zenpin_list) {
        this.zengpin_list = zenpin_list;
    }

    @Override
    public String toString() {
        return "OrderDetailsBean{" +
                "order_id='" + order_id + '\'' +
                ", order_sn='" + order_sn + '\'' +
                ", store_id='" + store_id + '\'' +
                ", store_name='" + store_name + '\'' +
                ", add_time='" + add_time + '\'' +
                ", payment_time='" + payment_time + '\'' +
                ", shipping_time='" + shipping_time + '\'' +
                ", finnshed_time='" + finnshed_time + '\'' +
                ", order_amount='" + order_amount + '\'' +
                ", shipping_fee='" + shipping_fee + '\'' +
                ", real_pay_amount='" + real_pay_amount + '\'' +
                ", state_desc='" + state_desc + '\'' +
                ", payment_name='" + payment_name + '\'' +
                ", order_message='" + order_message + '\'' +
                ", reciver_phone='" + reciver_phone + '\'' +
                ", reciver_name='" + reciver_name + '\'' +
                ", reciver_addr='" + reciver_addr + '\'' +
                ", store_member_id='" + store_member_id + '\'' +
                ", store_phone='" + store_phone + '\'' +
                ", order_tips='" + order_tips + '\'' +
                ", promotion='" + promotion + '\'' +
                ", invoice='" + invoice + '\'' +
                ", if_deliver='" + if_deliver + '\'' +
                ", if_buyer_cancel='" + if_buyer_cancel + '\'' +
                ", if_refund_cancel='" + if_refund_cancel + '\'' +
                ", if_receive='" + if_receive + '\'' +
                ", if_evaluation='" + if_evaluation + '\'' +
                ", if_lock='" + if_lock + '\'' +
                ", goods_list='" + goods_list + '\'' +
                ", zenpin_list='" + zengpin_list + '\'' +
                ", ownshop='" + ownshop + '\'' +
                '}';
    }
}
