package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/13.
 */
public class GoodsListBean {

    private String goods_type;

    private String goods_name;

    private String goods_spec;

    private String goods_price;

    private String goods_num;

    private String goods_img_360;

    private String goods_id;

    private String goods_pay_price;
    private String order_goods_id;
    private String store_id;

    public String getGoods_pay_price() {
        return goods_pay_price;
    }

    public void setGoods_pay_price(String goods_pay_price) {
        this.goods_pay_price = goods_pay_price;
    }

    public String getOrder_goods_id() {
        return order_goods_id;
    }

    public void setOrder_goods_id(String order_goods_id) {
        this.order_goods_id = order_goods_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public void setGoods_type(String goods_type){
        this.goods_type = goods_type;
    }
    public String getGoods_type(){
        return this.goods_type;
    }
    public void setGoods_name(String goods_name){
        this.goods_name = goods_name;
    }
    public String getGoods_name(){
        return this.goods_name;
    }
    public void setGoods_spec(String goods_spec){
        this.goods_spec = goods_spec;
    }
    public String getGoods_spec(){
        return this.goods_spec;
    }
    public void setGoods_price(String goods_price){
        this.goods_price = goods_price;
    }
    public String getGoods_price(){
        return this.goods_price;
    }
    public void setGoods_num(String goods_num){
        this.goods_num = goods_num;
    }
    public String getGoods_num(){
        return this.goods_num;
    }
    public void setGoods_img_360(String goods_img_360){
        this.goods_img_360 = goods_img_360;
    }
    public String getGoods_img_360(){
        return this.goods_img_360;
    }
    public void setGoods_id(String goods_id){
        this.goods_id = goods_id;
    }
    public String getGoods_id(){
        return this.goods_id;
    }
}
