package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品浏览
 */
public class GoodsBrowseInfo {
    private String goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsImageUrl;

    public GoodsBrowseInfo(String goodsId, String goodsName, String goodsPrice, String goodsImageUrl) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsImageUrl = goodsImageUrl;
    }

    public static ArrayList<GoodsBrowseInfo> newInstanceList(String json) {
        ArrayList<GoodsBrowseInfo> brandArray = new ArrayList<GoodsBrowseInfo>();

        try {
            JSONArray array = new JSONArray(json);
            int size = null == array ? 0 : array.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = array.getJSONObject(i);
                String goodsId = obj.optString("goods_id");
                String goodsName = obj.optString("goods_name");
                String goodsPrice = obj.optString("goods_promotion_price");
                String goodsImageUrl = obj.optString("goods_image_url");
                brandArray.add(new GoodsBrowseInfo(goodsId, goodsName, goodsPrice, goodsImageUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return brandArray;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    @Override
    public String toString() {
        return "GoodsBrowseInfo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsImageUrl='" + goodsImageUrl + '\'' +
                '}';
    }
}

