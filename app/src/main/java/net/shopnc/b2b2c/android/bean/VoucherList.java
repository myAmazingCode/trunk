package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 收货地址列表Bean
 *
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class VoucherList {
    public static class Attr {
        public static final String VOUCHER_ID = "voucher_id";
        public static final String VOUCHER_CODE = "voucher_code";
        public static final String VOUCHER_TITLE = "voucher_title";
        public static final String VOUCHER_DESC = "voucher_desc";
        public static final String VOUCHER_START_DATE = "voucher_start_date";
        public static final String VOUCHER_END_DATE = "voucher_end_date";
        public static final String VOUCHER_PRICE = "voucher_price";
        public static final String VOUCHER_LIMIT = "voucher_limit";
        public static final String VOUCHER_STATE = "voucher_state";
        public static final String VOUCHER_ORDER_ID = "voucher_order_id";
        public static final String VOUCHER_STORE_ID = "voucher_store_id";
        public static final String STORE_NAME = "store_name";
        public static final String STORE_ID = "store_id";
        public static final String STORE_DOMAIN = "store_domain";
        public static final String VOUCHER_T_CUSTOMIMG = "voucher_t_customimg";
        public static final String VOUCHER_STATE_TEXT = "voucher_state_text";
        public static final String STORE_AVATAR_URL = "store_avatar_url";
    }

    private String voucher_id;
    private String voucher_code;
    private String voucher_title;
    private String voucher_desc;
    private String voucher_start_date;
    private String voucher_end_date;
    private String voucher_price;
    private String voucher_limit;
    private String voucher_order_id;
    private String voucher_store_id;
    private String store_name;
    private String store_id;
    private String store_domain;
    private String voucher_t_customimg;
    private String voucher_state_text;
    private String store_avatar_url;

    public VoucherList() {
    }

    public VoucherList(String voucher_id, String voucher_code,
                       String voucher_title, String voucher_desc,
                       String voucher_start_date, String voucher_end_date,
                       String voucher_price, String voucher_limit,
                       String voucher_order_id, String voucher_store_id,
                       String store_name, String store_id, String store_domain,
                       String voucher_t_customimg, String voucher_state_text, String store_avatar_url) {
        super();
        this.voucher_id = voucher_id;
        this.voucher_code = voucher_code;
        this.voucher_title = voucher_title;
        this.voucher_desc = voucher_desc;
        this.voucher_start_date = voucher_start_date;
        this.voucher_end_date = voucher_end_date;
        this.voucher_price = voucher_price;
        this.voucher_limit = voucher_limit;
        this.voucher_order_id = voucher_order_id;
        this.voucher_store_id = voucher_store_id;
        this.store_name = store_name;
        this.store_id = store_id;
        this.store_domain = store_domain;
        this.voucher_t_customimg = voucher_t_customimg;
        this.voucher_state_text = voucher_state_text;
        this.store_avatar_url = store_avatar_url;
    }


    public static ArrayList<VoucherList> newInstanceList(String jsonDatas) {
        ArrayList<VoucherList> bean = new ArrayList<VoucherList>();

        try {
            JSONArray arr = new JSONArray(jsonDatas);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String voucher_id = obj.optString(Attr.VOUCHER_ID);
                String voucher_code = obj.optString(Attr.VOUCHER_CODE);
                String voucher_title = obj.optString(Attr.VOUCHER_TITLE);
                String voucher_desc = obj.optString(Attr.VOUCHER_DESC);
                String voucher_start_date = obj.optString(Attr.VOUCHER_START_DATE);
                String voucher_end_date = obj.optString(Attr.VOUCHER_END_DATE);
                String voucher_price = obj.optString(Attr.VOUCHER_PRICE);
                String voucher_limit = obj.optString(Attr.VOUCHER_LIMIT);
                String voucher_order_id = obj.optString(Attr.VOUCHER_ORDER_ID);
                String voucher_store_id = obj.optString(Attr.VOUCHER_STORE_ID);
                String store_name = obj.optString(Attr.STORE_NAME);
                String store_id = obj.optString(Attr.STORE_ID);
                String store_domain = obj.optString(Attr.STORE_DOMAIN);
                String voucher_t_customimg = obj.optString(Attr.VOUCHER_T_CUSTOMIMG);
                String voucher_state_text = obj.optString(Attr.VOUCHER_STATE_TEXT);
                String store_avatar_url = obj.optString(Attr.STORE_AVATAR_URL);
                bean.add(new VoucherList(voucher_id, voucher_code, voucher_title, voucher_desc, voucher_start_date, voucher_end_date, voucher_price, voucher_limit, voucher_order_id, voucher_store_id, store_name, store_id, store_domain, voucher_t_customimg, voucher_state_text, store_avatar_url));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getVoucher_code() {
        return voucher_code;
    }

    public void setVoucher_code(String voucher_code) {
        this.voucher_code = voucher_code;
    }

    public String getVoucher_title() {
        return voucher_title;
    }

    public void setVoucher_title(String voucher_title) {
        this.voucher_title = voucher_title;
    }

    public String getVoucher_desc() {
        return voucher_desc;
    }

    public void setVoucher_desc(String voucher_desc) {
        this.voucher_desc = voucher_desc;
    }

    public String getVoucher_start_date() {
        return voucher_start_date;
    }

    public void setVoucher_start_date(String voucher_start_date) {
        this.voucher_start_date = voucher_start_date;
    }

    public String getVoucher_end_date() {
        return voucher_end_date;
    }

    public void setVoucher_end_date(String voucher_end_date) {
        this.voucher_end_date = voucher_end_date;
    }

    public String getVoucher_price() {
        return voucher_price;
    }

    public void setVoucher_price(String voucher_price) {
        this.voucher_price = voucher_price;
    }

    public String getVoucher_limit() {
        return voucher_limit;
    }

    public void setVoucher_limit(String voucher_limit) {
        this.voucher_limit = voucher_limit;
    }

    public String getVoucher_order_id() {
        return voucher_order_id;
    }

    public void setVoucher_order_id(String voucher_order_id) {
        this.voucher_order_id = voucher_order_id;
    }

    public String getVoucher_store_id() {
        return voucher_store_id;
    }

    public void setVoucher_store_id(String voucher_store_id) {
        this.voucher_store_id = voucher_store_id;
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

    public String getStore_domain() {
        return store_domain;
    }

    public void setStore_domain(String store_domain) {
        this.store_domain = store_domain;
    }

    public String getVoucher_t_customimg() {
        return voucher_t_customimg;
    }

    public void setVoucher_t_customimg(String voucher_t_customimg) {
        this.voucher_t_customimg = voucher_t_customimg;
    }

    public String getVoucher_state_text() {
        return voucher_state_text;
    }

    public void setVoucher_state_text(String voucher_state_text) {
        this.voucher_state_text = voucher_state_text;
    }

    public String getStore_avatar_url() {
        return store_avatar_url;
    }

    public void setStore_avatar_url(String store_avatar_url) {
        this.store_avatar_url = store_avatar_url;
    }

    @Override
    public String toString() {
        return "VoucherList{" +
                "voucher_id='" + voucher_id + '\'' +
                ", voucher_code='" + voucher_code + '\'' +
                ", voucher_title='" + voucher_title + '\'' +
                ", voucher_desc='" + voucher_desc + '\'' +
                ", voucher_start_date='" + voucher_start_date + '\'' +
                ", voucher_end_date='" + voucher_end_date + '\'' +
                ", voucher_price='" + voucher_price + '\'' +
                ", voucher_limit='" + voucher_limit + '\'' +
                ", voucher_order_id='" + voucher_order_id + '\'' +
                ", voucher_store_id='" + voucher_store_id + '\'' +
                ", store_name='" + store_name + '\'' +
                ", store_id='" + store_id + '\'' +
                ", store_domain='" + store_domain + '\'' +
                ", voucher_t_customimg='" + voucher_t_customimg + '\'' +
                ", voucher_state_text='" + voucher_state_text + '\'' +
                ", store_avatar_url='" + store_avatar_url + '\'' +
                '}';
    }
}
