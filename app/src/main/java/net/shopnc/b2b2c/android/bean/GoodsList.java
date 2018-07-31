package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品列表Bean(insert goods_addtime)
 *
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class GoodsList {
    public static class Attr {
        public static final String GOODS_ID = "goods_id";
        public static final String GOODS_PRICE = "goods_price";
        public static final String GOODS_MARKETPRICE = "goods_marketprice";
        public static final String GOODS_NAME = "goods_name";
        public static final String GOODS_IMAGE = "goods_image";
        public static final String GOODS_SALENUM = "goods_salenum";
        public static final String EVAUATION_GOOD_STAR = "evaluation_good_star";
        public static final String EVALUATION_COUNT = "evaluation_count";
        public static final String GROUP_FLAG = "group_flag";
        public static final String XIANSHI_FLAG = "xianshi_flag";
        public static final String GOODS_IMAGES_URL = "goods_image_url";
        public static final String IS_VIRTUAL = "is_virtual";
        public static final String IS_FCODE = "is_fcode";
        public static final String IS_APPOINT = "is_appoint";
        public static final String IS_PRESELL = "is_presell";
        public static final String HAVE_GIFT = "have_gift";
        public static final String SOLE_FLAG = "sole_flag";
        public static final String GOODS_JINGLE = "goods_jingle";
        public static final String STORE_ID = "store_id";
        public static final String STORE_NAME = "store_name";
        public static final String IS_OWN_SHOP = "is_own_shop";
        public static final String GOODS_ADDTIME = "goods_addtime";
    }

    private String goods_id;
    private String goods_price;
    private String goods_marketprice;
    private String goods_image;
    private String goods_salenum;
    private String evaluation_good_star;
    private String evaluation_count;
    private String group_flag;
    private String xianshi_flag;
    private String goods_image_url;
    private String goods_name;
    private String is_virtual;
    private String is_fcode;
    private String is_appoint;
    private String is_presell;
    private String have_gift;
    private String sole_flag;
    private String goods_jingle;
    private String store_id;
    private String store_name;
    private String is_own_shop;
    private String goods_addtime;//INSERT

    public GoodsList() {
    }


    public GoodsList(String goods_id, String goods_price,
                     String goods_marketprice, String goods_image,
                     String goods_salenum, String evaluation_good_star,
                     String evaluation_count, String group_flag,
                     String xianshi_flag, String goods_image_url, String goods_name,
                     String is_virtual, String is_fcode, String is_appoint,
                     String is_presell, String have_gift, String sole_flag, String goods_jingle,
                     String store_id, String store_name, String is_own_shop,String goods_addtime) {
        super();
        this.goods_id = goods_id;
        this.goods_price = goods_price;
        this.goods_marketprice = goods_marketprice;
        this.goods_image = goods_image;
        this.goods_salenum = goods_salenum;
        this.evaluation_good_star = evaluation_good_star;
        this.evaluation_count = evaluation_count;
        this.group_flag = group_flag;
        this.xianshi_flag = xianshi_flag;
        this.goods_image_url = goods_image_url;
        this.goods_name = goods_name;
        this.is_virtual = is_virtual;
        this.is_fcode = is_fcode;
        this.is_appoint = is_appoint;
        this.is_presell = is_presell;
        this.have_gift = have_gift;
        this.sole_flag = sole_flag;
        this.goods_jingle = goods_jingle;
        this.store_id = store_id;
        this.store_name = store_name;
        this.is_own_shop = is_own_shop;
        this.goods_addtime = goods_addtime;
    }


    public static ArrayList<GoodsList> newInstanceList(String jsonDatas) {
        ArrayList<GoodsList> AdvertDatas = new ArrayList<GoodsList>();
        try {
            JSONArray arr = new JSONArray(jsonDatas);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String goods_id = obj.optString(Attr.GOODS_ID);
                String goods_price = obj.optString(Attr.GOODS_PRICE);
                String goods_marketprice = obj.optString(Attr.GOODS_MARKETPRICE);
                String goods_image = obj.optString(Attr.GOODS_IMAGE);
                String goods_salenum = obj.optString(Attr.GOODS_SALENUM);
                String evaluation_good_star = obj.optString(Attr.EVAUATION_GOOD_STAR);
                String evaluation_count = obj.optString(Attr.EVALUATION_COUNT);
                String group_flag = obj.optString(Attr.GROUP_FLAG);
                String xianshi_flag = obj.optString(Attr.XIANSHI_FLAG);
                String goods_image_url = obj.optString(Attr.GOODS_IMAGES_URL);
                String goods_name = obj.optString(Attr.GOODS_NAME);
                String is_virtual = obj.optString(Attr.IS_VIRTUAL);
                String is_fcode = obj.optString(Attr.IS_FCODE);
                String is_appoint = obj.optString(Attr.IS_APPOINT);
                String is_presell = obj.optString(Attr.IS_PRESELL);
                String have_gift = obj.optString(Attr.HAVE_GIFT);
                String sole_flag = obj.optString(Attr.SOLE_FLAG);
                String goods_jingle = obj.optString(Attr.GOODS_JINGLE);
                String store_id = obj.optString(Attr.STORE_ID);
                String store_name = obj.optString(Attr.STORE_NAME);
                String is_own_shop = obj.optString(Attr.IS_OWN_SHOP);
                String goods_addtime = obj.optString(Attr.GOODS_ADDTIME);
                GoodsList bean = new GoodsList(goods_id, goods_price,
                        goods_marketprice, goods_image, goods_salenum,
                        evaluation_good_star, evaluation_count, group_flag,
                        xianshi_flag, goods_image_url, goods_name, is_virtual,
                        is_fcode, is_appoint, is_presell, have_gift, sole_flag,
                        goods_jingle, store_id, store_name, is_own_shop,goods_addtime);
                AdvertDatas.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return AdvertDatas;
    }


    public String getIs_virtual() {
        return is_virtual;
    }


    public void setIs_virtual(String is_virtual) {
        this.is_virtual = is_virtual;
    }


    public String getIs_fcode() {
        return is_fcode;
    }


    public void setIs_fcode(String is_fcode) {
        this.is_fcode = is_fcode;
    }


    public String getIs_appoint() {
        return is_appoint;
    }


    public void setIs_appoint(String is_appoint) {
        this.is_appoint = is_appoint;
    }


    public String getIs_presell() {
        return is_presell;
    }


    public void setIs_presell(String is_presell) {
        this.is_presell = is_presell;
    }


    public String getHave_gift() {
        return have_gift;
    }


    public void setHave_gift(String have_gift) {
        this.have_gift = have_gift;
    }


    public String getGoods_id() {
        return goods_id;
    }


    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }


    public String getGoods_price() {
        return goods_price;
    }


    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }


    public String getGoods_marketprice() {
        return goods_marketprice;
    }


    public void setGoods_marketprice(String goods_marketprice) {
        this.goods_marketprice = goods_marketprice;
    }


    public String getGoods_image() {
        return goods_image;
    }


    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }


    public String getGoods_salenum() {
        return goods_salenum;
    }


    public void setGoods_salenum(String goods_salenum) {
        this.goods_salenum = goods_salenum;
    }


    public String getEvaluation_good_star() {
        return evaluation_good_star;
    }


    public void setEvaluation_good_star(String evaluation_good_star) {
        this.evaluation_good_star = evaluation_good_star;
    }


    public String getEvaluation_count() {
        return evaluation_count;
    }


    public void setEvaluation_count(String evaluation_count) {
        this.evaluation_count = evaluation_count;
    }


    public String getGroup_flag() {
        return group_flag;
    }


    public void setGroup_flag(String group_flag) {
        this.group_flag = group_flag;
    }


    public String getXianshi_flag() {
        return xianshi_flag;
    }


    public void setXianshi_flag(String xianshi_flag) {
        this.xianshi_flag = xianshi_flag;
    }


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

    public String getSole_flag() {
        return sole_flag;
    }

    public void setSole_flag(String sole_flag) {
        this.sole_flag = sole_flag;
    }

    public String getGoods_jingle() {
        return goods_jingle;
    }

    public void setGoods_jingle(String goods_jingle) {
        this.goods_jingle = goods_jingle;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getIs_own_shop() {
        return is_own_shop;
    }

    public void setIs_own_shop(String is_own_shop) {
        this.is_own_shop = is_own_shop;
    }

    public String getGoods_addtime() {
        return goods_addtime;
    }

    public void setGoods_addtime(String goods_addtime) {
        this.goods_addtime = goods_addtime;
    }

    @Override
    public String toString() {
        return "GoodsList{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", goods_marketprice='" + goods_marketprice + '\'' +
                ", goods_image='" + goods_image + '\'' +
                ", goods_salenum='" + goods_salenum + '\'' +
                ", evaluation_good_star='" + evaluation_good_star + '\'' +
                ", evaluation_count='" + evaluation_count + '\'' +
                ", group_flag='" + group_flag + '\'' +
                ", xianshi_flag='" + xianshi_flag + '\'' +
                ", goods_image_url='" + goods_image_url + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", is_virtual='" + is_virtual + '\'' +
                ", is_fcode='" + is_fcode + '\'' +
                ", is_appoint='" + is_appoint + '\'' +
                ", is_presell='" + is_presell + '\'' +
                ", have_gift='" + have_gift + '\'' +
                ", sole_flag='" + sole_flag + '\'' +
                ", goods_jingle='" + goods_jingle + '\'' +
                ", store_id='" + store_id + '\'' +
                ", store_name='" + store_name + '\'' +
                ", is_own_shop='" + is_own_shop + '\'' +
                ", goods_addtime='" + goods_addtime + '\'' +
                '}';
    }
}
