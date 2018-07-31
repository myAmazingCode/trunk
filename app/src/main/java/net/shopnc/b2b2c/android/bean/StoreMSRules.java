package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 店铺活动--满即送的规则
 *
 * Created by huting on 2016/1/4.
 */
public class StoreMSRules implements Serializable{

    private String goods_url;
    private String rule_id;
    private String price;
    private String mansong_goods_name;
    private String goods_id;
    private String mansong_id;
    private String goods_image_url;
    private String goods_storage;
    private String goods_image;
    private String discount;

    public StoreMSRules(){}
    public StoreMSRules(String goods_url, String rule_id, String price, String mansong_goods_name, String goods_id, String mansong_id,
                        String goods_image_url, String goods_storage, String goods_image,
                        String discount) {
        this.goods_url = goods_url;
        this.rule_id = rule_id;
        this.price = price;
        this.mansong_goods_name = mansong_goods_name;
        this.goods_id = goods_id;
        this.mansong_id = mansong_id;
        this.goods_image_url = goods_image_url;
        this.goods_storage = goods_storage;
        this.goods_image = goods_image;
        this.discount = discount;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public String getRule_id() {
        return rule_id;
    }

    public void setRule_id(String rule_id) {
        this.rule_id = rule_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMansong_goods_name() {
        return mansong_goods_name;
    }

    public void setMansong_goods_name(String mansong_goods_name) {
        this.mansong_goods_name = mansong_goods_name;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getMansong_id() {
        return mansong_id;
    }

    public void setMansong_id(String mansong_id) {
        this.mansong_id = mansong_id;
    }

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
    }

    public String getGoods_storage() {
        return goods_storage;
    }

    public void setGoods_storage(String goods_storage) {
        this.goods_storage = goods_storage;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "StoreMSRules{" +
                "goods_url='" + goods_url + '\'' +
                ", rule_id='" + rule_id + '\'' +
                ", price='" + price + '\'' +
                ", mansong_goods_name='" + mansong_goods_name + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", mansong_id='" + mansong_id + '\'' +
                ", goods_image_url='" + goods_image_url + '\'' +
                ", goods_storage='" + goods_storage + '\'' +
                ", goods_image='" + goods_image + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }
}
