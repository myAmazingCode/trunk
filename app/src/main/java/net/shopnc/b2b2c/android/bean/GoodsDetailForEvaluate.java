package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wj on 2015/12/24.
 * 评论实体类
 */
public class GoodsDetailForEvaluate  implements Serializable {

    private StoreInfo store_info;//店铺信息
    private String rec_id; //索引id
    private String order_id;// 订单id
    private String goods_id;//商品id
    private String goods_name;
    private String goods_price;
    private String goods_num;
    private String goods_image;
    private String goods_pay_price;// 实际支付价格
    private String store_id;//店铺id
    private String buyer_id;//买家id
    private String goods_type;//商品类型 1默认2团购商品3限时折扣商品4组合套装5赠品8加价购活动商品9加价购换购商品
    private String promotions_id;//促销活动ID
    private String commis_rate;// 佣金比例
    private String gc_id;//商品分类（最后一级）
    private String goods_spec;//商品规格
    private String goods_contractid;//商品开启的消费者保障服务id
    private String goods_image_url;//商品图片路径
    private float  score = 0;//商品评分1~5
    private String comment;//评论内容，不超过150个字符
    private String anony = "0";//是否匿名评价，1为匿名/0为不匿名
    private HashMap<Integer, ImageFile> imageList = new HashMap<Integer, ImageFile>();

    public static class Attr{
        public static final String STORE_INFO = "store_info";
        public static final String REC_ID = "rec_id";
        public static final String ORDER_ID = "order_id";
        public static final String GOODS_ID = "goods_id";
        public static final String GOODS_NUM = "goods_num";
        public static final String GOODS_IMAGE = "goods_image";
        public static final String GOODS_PAY_PRICE = "goods_pay_price";
        public static final String STORE_ID = "store_id";
        public static final String BUYER_ID = "buyer_id";
        public static final String GOODS_TYPE = "goods_type";
        public static final String PROMOTIONS_ID = "promotions_id";
        public static final String COMMIS_RATE = "commis_rate";
        public static final String GC_ID = "gc_id";
        public static final String GOODS_SPEC = "goods_spec";
        public static final String GOODS_NAME = "goods_name";// 商品名称
        public static final String GOODS_PRICE = "goods_price";
        public static final String GOODS_CONTRACTID = "goods_contractid";
        public static final String GOODS_IMAGE_URL = "goods_image_url";
        public static final String SCORE = "score";
        public static final String COMMENT = "comment";
        public static final String ANONY = "anony";

    }

    //是否匿名
    private boolean isSelected;




    public StoreInfo getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfo store_info) {
        this.store_info = store_info;
    }
    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_pay_price() {
        return goods_pay_price;
    }

    public void setGoods_pay_price(String goods_pay_price) {
        this.goods_pay_price = goods_pay_price;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getPromotions_id() {
        return promotions_id;
    }

    public void setPromotions_id(String promotions_id) {
        this.promotions_id = promotions_id;
    }

    public String getCommis_rate() {
        return commis_rate;
    }

    public void setCommis_rate(String commis_rate) {
        this.commis_rate = commis_rate;
    }

    public String getGc_id() {
        return gc_id;
    }

    public void setGc_id(String gc_id) {
        this.gc_id = gc_id;
    }

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    public String getGoods_contractid() {
        return goods_contractid;
    }

    public void setGoods_contractid(String goods_contractid) {
        this.goods_contractid = goods_contractid;
    }

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAnony() {
        return anony;
    }

    public void setAnony(String anony) {
        this.anony = anony;
    }

    public HashMap<Integer, ImageFile> getImageList() {
        return imageList;
    }

    public void setImageList(HashMap<Integer, ImageFile> imageList) {
        this.imageList = imageList;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "GoodsDetailsForComment{" +
                "rec_id='" + rec_id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", goods_num='" + goods_num + '\'' +
                ", goods_image='" + goods_image + '\'' +
                ", goods_pay_price='" + goods_pay_price + '\'' +
                ", store_id='" + store_id + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", goods_type='" + goods_type + '\'' +
                ", promotions_id='" + promotions_id + '\'' +
                ", commis_rate='" + commis_rate + '\'' +
                ", gc_id='" + gc_id + '\'' +
                ", goods_spec='" + goods_spec + '\'' +
                ", goods_contractid='" + goods_contractid + '\'' +
                ", goods_image_url='" + goods_image_url + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", anony='" + anony + '\'' +
                ", imageList=" + imageList +
                ", isSelected=" + isSelected +
                '}';
    }


    public static List<GoodsDetailForEvaluate> newInstanceList(String json){
        List<GoodsDetailForEvaluate> list = new ArrayList<GoodsDetailForEvaluate>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);

                GoodsDetailForEvaluate goodsDetailsByComment = new GoodsDetailForEvaluate();

                goodsDetailsByComment.setBuyer_id(obj.optString(Attr.BUYER_ID));
                goodsDetailsByComment.setCommis_rate(obj.optString(Attr.COMMIS_RATE));
                goodsDetailsByComment.setGc_id(obj.optString(Attr.GC_ID));
                goodsDetailsByComment.setGoods_contractid(obj.optString(Attr.GOODS_CONTRACTID));
                goodsDetailsByComment.setGoods_id(obj.optString(Attr.GOODS_ID));
                goodsDetailsByComment.setGoods_image(obj.optString(Attr.GOODS_IMAGE));
                goodsDetailsByComment.setGoods_image_url(obj.optString(Attr.GOODS_IMAGE_URL));
                goodsDetailsByComment.setGoods_name(obj.optString(Attr.GOODS_NAME));
                goodsDetailsByComment.setGoods_pay_price(obj.optString(Attr.GOODS_PAY_PRICE));
                goodsDetailsByComment.setGoods_price(obj.optString(Attr.GOODS_PRICE));
                goodsDetailsByComment.setGoods_type(obj.optString(Attr.GOODS_TYPE));
                goodsDetailsByComment.setGoods_spec(obj.optString(Attr.GOODS_SPEC));
                goodsDetailsByComment.setPromotions_id(obj.optString(Attr.PROMOTIONS_ID));
                goodsDetailsByComment.setOrder_id(obj.optString(Attr.ORDER_ID));
                /*
                if(obj.optString(Attr.SCORE) != null && !obj.optString(Attr.SCORE).equals("")){
                    goodsDetailsByComment.setScore(Float.parseFloat(obj.optString(Attr.SCORE)));
                }else{
                    goodsDetailsByComment.setScore(1);
                }
                */
                //goodsDetailsByComment.setScore(Float.parseFloat(obj.optString(Attr.SCORE)));
                goodsDetailsByComment.setComment(obj.optString(Attr.COMMENT));
                goodsDetailsByComment.setStore_id(obj.optString(Attr.STORE_ID));
                goodsDetailsByComment.setGoods_num(obj.optString(Attr.GOODS_NUM));
                goodsDetailsByComment.setAnony(obj.optString(Attr.ANONY));
                goodsDetailsByComment.setRec_id(obj.optString(Attr.REC_ID));
                list.add(goodsDetailsByComment);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
