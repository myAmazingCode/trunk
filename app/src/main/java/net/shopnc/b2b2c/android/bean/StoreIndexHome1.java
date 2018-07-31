package net.shopnc.b2b2c.android.bean;

import org.json.JSONObject;

/**
 * 店铺首页排行榜
 *
 * Created by huting on 2015/12/30.
 */
public class StoreIndexHome1 {

    public static class Attr{
        public static final String GOODS_ID = "goods_id";
        public static final String GOODS_NAME = "goods_name";
        public static final String GOODS_PRICE = "goods_price";
        public static final String GOODS_SALENUM = "goods_salenum";
        public static final String GOODS_IMAGE_URL = "goods_image_url";
    }
    private String goods_id;
    private String goods_name;
    private String goods_price;
    private String goods_salenum;
    private String goods_image_url;

    public StoreIndexHome1() {
    }

    public StoreIndexHome1(String goods_id, String goods_name, String goods_price,String goods_salenum,
                     String goods_image_url) {
        super();
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.goods_salenum = goods_salenum;
        this.goods_image_url = goods_image_url;
    }

    public static StoreIndexHome1 newInstanceList(JSONObject obj){
        StoreIndexHome1 bean = null;
        try {
            if(obj.length()> 0){
                String goods_id = obj.optString(Attr.GOODS_ID);
                String goods_name = obj.optString(Attr.GOODS_NAME);
                String goods_price = obj.optString(Attr.GOODS_PRICE);
                String goods_salenum = obj.optString(Attr.GOODS_SALENUM);
                String goods_image_url = obj.optString(Attr.GOODS_IMAGE_URL);

                bean = new StoreIndexHome1(goods_id,goods_name,goods_price,goods_salenum
                        ,goods_image_url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
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

    public String getGoods_salenum() {
        return goods_salenum;
    }

    public void setGoods_salenum(String goods_salenum) {
        this.goods_salenum = goods_salenum;
    }

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
    }

    @Override
    public String toString() {
        return "StoreIndexHome1{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", goods_salenum='" + goods_salenum + '\'' +
                ", goods_image_url='" + goods_image_url + '\'' +
                '}';
    }
}
