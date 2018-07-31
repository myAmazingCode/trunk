package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品详细页店铺代金券BEAN
 *
 * @author dqw
 * @Time 2015/9/7
 */
public class GoodsDetailStoreVoucherInfo {

    private String id;
    private String price;
    private String limit;
    private String endDate;

    public GoodsDetailStoreVoucherInfo(String id, String price, String limit, String endDate) {
        this.id = id;
        this.price = price;
        this.limit = limit;
        this.endDate = endDate;
    }

    public static ArrayList<GoodsDetailStoreVoucherInfo> newInstanceList(String json) {
        ArrayList<GoodsDetailStoreVoucherInfo> arrayList = new ArrayList<GoodsDetailStoreVoucherInfo>();
        try {
            if (json != null && !json.equals("[]")) {
                JSONArray arr = new JSONArray(json);
                int size = null == arr ? 0 : arr.length();
                for (int i = 0; i < size; i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    String id = obj.optString("voucher_t_id");
                    String price = obj.optString("voucher_t_price");
                    String limit = obj.optString("voucher_t_limit");
                    String endDate = obj.optString("voucher_t_end_date");
                    arrayList.add(new GoodsDetailStoreVoucherInfo(id, price, limit, endDate));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "GoodsDetailStoreVoucherInfo{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", limit='" + limit + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
