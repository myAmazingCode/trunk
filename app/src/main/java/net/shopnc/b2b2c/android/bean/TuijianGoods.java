package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by snm on 2016/9/2.
 */
public class TuijianGoods {

    private GoodsDetails goods_info;
    private String spec_image;
    private String image_list;
    private String video_path;
    private String mansong_info;
    private String gift_array;
    private String spec_list;
    private String goods_image;
    private String store_info;
    private String voucher;

    public TuijianGoods(GoodsDetails goods_info, String spec_image, String image_list, String video_path, String mansong_info, String gift_array, String spec_list, String goods_image, String store_info, String voucher) {
        this.goods_info = goods_info;
        this.spec_image = spec_image;
        this.image_list = image_list;
        this.video_path = video_path;
        this.mansong_info = mansong_info;
        this.gift_array = gift_array;
        this.spec_list = spec_list;
        this.goods_image = goods_image;
        this.store_info = store_info;
        this.voucher = voucher;
    }

    public GoodsDetails getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(GoodsDetails goods_info) {
        this.goods_info = goods_info;
    }

    public String getSpec_image() {
        return spec_image;
    }

    public void setSpec_image(String spec_image) {
        this.spec_image = spec_image;
    }

    public String getImage_list() {
        return image_list;
    }

    public void setImage_list(String image_list) {
        this.image_list = image_list;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getMansong_info() {
        return mansong_info;
    }

    public void setMansong_info(String mansong_info) {
        this.mansong_info = mansong_info;
    }

    public String getGift_array() {
        return gift_array;
    }

    public void setGift_array(String gift_array) {
        this.gift_array = gift_array;
    }

    public String getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(String spec_list) {
        this.spec_list = spec_list;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getStore_info() {
        return store_info;
    }

    public void setStore_info(String store_info) {
        this.store_info = store_info;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public static ArrayList<TuijianGoods> getInstanceList(String jsonDatas){
        ArrayList<TuijianGoods> AdvertDatas = new ArrayList<TuijianGoods>();

        try {
            JSONArray arr = new JSONArray(jsonDatas);
            int size = null == arr ? 0 : arr.length();
            for(int i = 0; i < size; i++){
                JSONObject obj = arr.getJSONObject(i);
                TuijianGoods goods = getInstance(obj);
                AdvertDatas.add(goods);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return AdvertDatas;
    }

    public static TuijianGoods getInstance(JSONObject obj){
        String goods_info = obj.optString("goods_info");
        GoodsDetails goodsDetails = GoodsDetails.newInstanceList(goods_info);
        String spec_image = obj.optString("spec_image");
        String image_list = obj.optString("image_list");
        String video_path = obj.optString("video_path");
        String mansong_info = obj.optString("mansong_info");
        String gift_array = obj.optString("gift_array");
        String spec_list = obj.optString("spec_list");
        String goods_image = obj.optString("goods_image");
        String store_info = obj.optString("store_info");
        String voucher = obj.optString("voucher");
        TuijianGoods goods = new TuijianGoods(goodsDetails, spec_image, image_list,video_path,mansong_info,gift_array,spec_list,goods_image,store_info,voucher);
        return goods;
    }


}
