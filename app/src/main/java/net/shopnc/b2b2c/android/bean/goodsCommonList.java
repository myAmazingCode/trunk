package net.shopnc.b2b2c.android.bean;

/**
 * Created by snm on 2016/8/31.
 */
public class goodsCommonList {


    /**
     * goods_image_url : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/store/goods/1/1_05248571535322701_240.png
     * goods_name : 充电器/数据线
     * goods_price : 300.00
     * goods_commonid : 100013
     */

    private String goods_image_url;
    private String goods_name;
    private String goods_price;
    private String goods_id;
    private String goods_commonid;

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
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

    public String getGoods_commonid() {
        return goods_commonid;
    }

    public void setGoods_commonid(String goods_commonid) {
        this.goods_commonid = goods_commonid;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}
