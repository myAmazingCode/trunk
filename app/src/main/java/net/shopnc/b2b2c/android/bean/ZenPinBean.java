package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/8.
 */
public class ZenPinBean {
    private String goods_name;
    private String goods_num;


    public ZenPinBean() {
    }

    public ZenPinBean(String goods_num, String goods_name) {
        this.goods_num = goods_num;
        this.goods_name = goods_name;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }
}
