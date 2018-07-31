package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/13.
 */
public class OrderExchangeBean {

    private String book_amount;

    private String allow_refund_amount;

    private String order_amount;

    private String store_id;

    private String order_sn;

    private String order_id;

    private String store_name;

    private String order_type;

    public void setBook_amount(String book_amount){
        this.book_amount = book_amount;
    }
    public String getBook_amount(){
        return this.book_amount;
    }
    public void setAllow_refund_amount(String allow_refund_amount){
        this.allow_refund_amount = allow_refund_amount;
    }
    public String getAllow_refund_amount(){
        return this.allow_refund_amount;
    }
    public void setOrder_amount(String order_amount){
        this.order_amount = order_amount;
    }
    public String getOrder_amount(){
        return this.order_amount;
    }
    public void setStore_id(String store_id){
        this.store_id = store_id;
    }
    public String getStore_id(){
        return this.store_id;
    }
    public void setOrder_sn(String order_sn){
        this.order_sn = order_sn;
    }
    public String getOrder_sn(){
        return this.order_sn;
    }
    public void setOrder_id(String order_id){
        this.order_id = order_id;
    }
    public String getOrder_id(){
        return this.order_id;
    }
    public void setStore_name(String store_name){
        this.store_name = store_name;
    }
    public String getStore_name(){
        return this.store_name;
    }
    public void setOrder_type(String order_type){
        this.order_type = order_type;
    }
    public String getOrder_type(){
        return this.order_type;
    }

}
