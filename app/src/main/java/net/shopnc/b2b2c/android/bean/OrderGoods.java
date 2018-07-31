package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/11.
 */
public class OrderGoods {
    private String rec_id;
    private String goods_id;
    private String goods_name;
    private String goods_price;
    private String goods_num;
    private String goods_spec;
    private String image_url;
    private String refund;

    public OrderGoods() {
    }

    public OrderGoods(String rec_id, String goods_id, String goods_name, String goods_price, String goods_num, String goods_spec, String image_url, String refund) {
        this.rec_id = rec_id;
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.goods_num = goods_num;
        this.goods_spec = goods_spec;
        this.image_url = image_url;
        this.refund = refund;
    }

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "rec_id='" + rec_id + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", goods_num='" + goods_num + '\'' +
                ", goods_spec='" + goods_spec + '\'' +
                ", image_url='" + image_url + '\'' +
                ", refund='" + refund + '\'' +
                '}';
    }
}
