package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wj on 2016/1/5.
 * 追加评论实体类
 */
public class GoodsDetailForEvaluateAdd implements Serializable {
    private StoreInfo store_info;//店铺信息
    private String geval_id;
    private String geval_orderid; //订单id
    private String geval_orderno;//订单编号
    private String geval_ordergoodsid;// 订单商品表编号
    private String geval_goodsid; //商品id
    private String geval_goodsname;// 商品名称
    private String geval_goodsprice;// 商品价格
    private String geval_goodsimage;// 商品图片
    private String geval_scores;// 商品评分
    private String geval_content;// 评价内容
    private String geval_isanonymous ;//是否匿名，1是/0否
    private String geval_addtime ;//评价时间
    private String geval_storeid;// 店铺id
    private String geval_storename;// 店铺名称
    private String geval_frommemberid;//评价人编号
    private String geval_frommembername;// 评价人名称
    private String geval_explain;// 解释内容
    private String geval_image;// 晒单图片
    private String geval_content_again;// 追加评价内容
    private String geval_addtime_again;// 追加评价时间
    private String geval_image_again;// 追加评价图片
    private String geval_explain_again;// 追加评价解释
    private String comment;
    private String anony = "0";
    private HashMap<Integer, ImageFile> imageList = new HashMap<Integer, ImageFile>();

    public static class Attr{
        private final static String GEVAL_ID = "geval_id";
        private final static String GEVAL_ORDERID = "geval_orderid"; // 订单id
        private final static String GEVAL_ORDERNO = "geval_orderno";// 订单编号
        private final static String GEVAL_ORDERGOODSID = "geval_ordergoodsid";// 订单商品表编号
        private final static String GEVAL_GOODSID = "geval_goodsid";// 商品id
        private final static String GEVAL_GOODSNAME = "geval_goodsname";// 商品名称
        private final static String GEVAL_GOODSPRICE = "geval_goodsprice";// 商品价格
        private final static String GEVAL_GOODSIMAGE = "geval_goodsimage";// 商品图片
        private final static String GEVAL_SCORES = "geval_scores"; // 商品评分
        private final static String GEVAL_CONTENT = "geval_content"; // 评价内容
        private final static String GEVAL_ISANONYMOUS = "geval_isanonymous"; // 是否匿名，1是/0否
        private final static String GEVAL_ADDTIME = "geval_addtime"; // 评价时间
        private final static String GEVAL_STOREID = "geval_storeid"; // 店铺id
        private final static String GEVAL_STORENAME = "geval_storename"; // 店铺名称
        private final static String GEVAL_FROMMEMBERID = "geval_frommemberid"; // 评价人编号
        private final static String GEVAL_FROMMEMBERNAME = "geval_frommembername"; // 评价人名称
        private final static String GEVAL_EXPLAIN = "geval_explain"; // 解释内容
        private final static String GEVAL_IMAGE = "geval_image"; // 晒单图片
        private final static String GEVAL_CONTENT_AGAIN = "geval_content_again"; // 追加评价内容
        private final static String GEVAL_ADDTIME_AGAIN = "geval_addtime_again"; // 追加评价时间
        private final static String GEVAL_IMAGE_AGAIN = "geval_image_again"; // 追加评价图片
        private final static String GEVAL_EXPLAIN_AGAIN = "geval_explain_again"; // 追加评价解释
    }

    @Override
    public String toString() {
        return "GoodsDetailForEvaluateAdd{" +
                "store_info=" + store_info +
                ", geval_orderid='" + geval_orderid + '\'' +
                ", geval_orderno='" + geval_orderno + '\'' +
                ", geval_ordergoodsid='" + geval_ordergoodsid + '\'' +
                ", geval_goodsid='" + geval_goodsid + '\'' +
                ", geval_goodsname='" + geval_goodsname + '\'' +
                ", geval_goodsprice='" + geval_goodsprice + '\'' +
                ", geval_goodsimage='" + geval_goodsimage + '\'' +
                ", geval_scores='" + geval_scores + '\'' +
                ", geval_content='" + geval_content + '\'' +
                ", geval_isanonymous='" + geval_isanonymous + '\'' +
                ", geval_addtime='" + geval_addtime + '\'' +
                ", geval_storeid='" + geval_storeid + '\'' +
                ", geval_storename='" + geval_storename + '\'' +
                ", geval_frommemberid='" + geval_frommemberid + '\'' +
                ", geval_frommembername='" + geval_frommembername + '\'' +
                ", geval_explain='" + geval_explain + '\'' +
                ", geval_image='" + geval_image + '\'' +
                ", geval_content_again='" + geval_content_again + '\'' +
                ", geval_addtime_again='" + geval_addtime_again + '\'' +
                ", geval_image_again='" + geval_image_again + '\'' +
                ", geval_explain_again='" + geval_explain_again + '\'' +
                ", comment='" + comment + '\'' +
                ", anony='" + anony + '\'' +
                ", imageList=" + imageList +
                '}';
    }


    public static List<GoodsDetailForEvaluateAdd> newInstanceList(String json){
        List<GoodsDetailForEvaluateAdd> list = new ArrayList<GoodsDetailForEvaluateAdd>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                GoodsDetailForEvaluateAdd goodsDetailsForEvaluateAdd = new GoodsDetailForEvaluateAdd();

                goodsDetailsForEvaluateAdd.setGeval_id(obj.optString(Attr.GEVAL_ID));
                goodsDetailsForEvaluateAdd.setGeval_orderid(obj.optString(Attr.GEVAL_ORDERID));
                goodsDetailsForEvaluateAdd.setGeval_orderno(obj.optString(Attr.GEVAL_ORDERNO));
                goodsDetailsForEvaluateAdd.setGeval_ordergoodsid(obj.optString(Attr.GEVAL_ORDERGOODSID));
                goodsDetailsForEvaluateAdd.setGeval_goodsid(obj.optString(Attr.GEVAL_GOODSID));
                goodsDetailsForEvaluateAdd.setGeval_goodsname(obj.optString(Attr.GEVAL_GOODSNAME));
                goodsDetailsForEvaluateAdd.setGeval_goodsprice(obj.optString(Attr.GEVAL_GOODSPRICE));
                goodsDetailsForEvaluateAdd.setGeval_goodsimage(obj.optString(Attr.GEVAL_GOODSIMAGE));
                goodsDetailsForEvaluateAdd.setGeval_scores(obj.optString(Attr.GEVAL_SCORES));
                goodsDetailsForEvaluateAdd.setGeval_content(obj.optString(Attr.GEVAL_CONTENT));
                goodsDetailsForEvaluateAdd.setGeval_isanonymous(obj.optString(Attr.GEVAL_ISANONYMOUS));
                goodsDetailsForEvaluateAdd.setGeval_addtime(obj.optString(Attr.GEVAL_ADDTIME));
                goodsDetailsForEvaluateAdd.setGeval_storeid(obj.optString(Attr.GEVAL_STOREID));
                goodsDetailsForEvaluateAdd.setGeval_storename(obj.optString(Attr.GEVAL_STORENAME));
                goodsDetailsForEvaluateAdd.setGeval_frommemberid(obj.optString(Attr.GEVAL_FROMMEMBERID));
                goodsDetailsForEvaluateAdd.setGeval_frommembername(obj.optString(Attr.GEVAL_FROMMEMBERNAME));
                goodsDetailsForEvaluateAdd.setGeval_explain(obj.optString(Attr.GEVAL_EXPLAIN));
                goodsDetailsForEvaluateAdd.setGeval_image(obj.optString(Attr.GEVAL_IMAGE));
                goodsDetailsForEvaluateAdd.setGeval_addtime_again(obj.optString(Attr.GEVAL_ADDTIME_AGAIN));
                goodsDetailsForEvaluateAdd.setGeval_image_again(obj.optString(Attr.GEVAL_IMAGE_AGAIN));
                goodsDetailsForEvaluateAdd.setGeval_explain_again(obj.optString(Attr.GEVAL_EXPLAIN_AGAIN));

                list.add(goodsDetailsForEvaluateAdd);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public String getGeval_id() {
        return geval_id;
    }

    public void setGeval_id(String geval_id) {
        this.geval_id = geval_id;
    }

    public StoreInfo getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfo store_info) {
        this.store_info = store_info;
    }

    public String getGeval_orderid() {
        return geval_orderid;
    }

    public void setGeval_orderid(String geval_orderid) {
        this.geval_orderid = geval_orderid;
    }

    public String getGeval_orderno() {
        return geval_orderno;
    }

    public void setGeval_orderno(String geval_orderno) {
        this.geval_orderno = geval_orderno;
    }

    public String getGeval_ordergoodsid() {
        return geval_ordergoodsid;
    }

    public void setGeval_ordergoodsid(String geval_ordergoodsid) {
        this.geval_ordergoodsid = geval_ordergoodsid;
    }

    public String getGeval_goodsid() {
        return geval_goodsid;
    }

    public void setGeval_goodsid(String geval_goodsid) {
        this.geval_goodsid = geval_goodsid;
    }

    public String getGeval_goodsname() {
        return geval_goodsname;
    }

    public void setGeval_goodsname(String geval_goodsname) {
        this.geval_goodsname = geval_goodsname;
    }

    public String getGeval_goodsprice() {
        return geval_goodsprice;
    }

    public void setGeval_goodsprice(String geval_goodsprice) {
        this.geval_goodsprice = geval_goodsprice;
    }

    public String getGeval_goodsimage() {
        return geval_goodsimage;
    }

    public void setGeval_goodsimage(String geval_goodsimage) {
        this.geval_goodsimage = geval_goodsimage;
    }

    public String getGeval_scores() {
        return geval_scores;
    }

    public void setGeval_scores(String geval_scores) {
        this.geval_scores = geval_scores;
    }

    public String getGeval_content() {
        return geval_content;
    }

    public void setGeval_content(String geval_content) {
        this.geval_content = geval_content;
    }

    public String getGeval_isanonymous() {
        return geval_isanonymous;
    }

    public void setGeval_isanonymous(String geval_isanonymous) {
        this.geval_isanonymous = geval_isanonymous;
    }

    public String getGeval_addtime() {
        return geval_addtime;
    }

    public void setGeval_addtime(String geval_addtime) {
        this.geval_addtime = geval_addtime;
    }

    public String getGeval_storeid() {
        return geval_storeid;
    }

    public void setGeval_storeid(String geval_storeid) {
        this.geval_storeid = geval_storeid;
    }

    public String getGeval_storename() {
        return geval_storename;
    }

    public void setGeval_storename(String geval_storename) {
        this.geval_storename = geval_storename;
    }

    public String getGeval_frommemberid() {
        return geval_frommemberid;
    }

    public void setGeval_frommemberid(String geval_frommemberid) {
        this.geval_frommemberid = geval_frommemberid;
    }

    public String getGeval_frommembername() {
        return geval_frommembername;
    }

    public void setGeval_frommembername(String geval_frommembername) {
        this.geval_frommembername = geval_frommembername;
    }

    public String getGeval_explain() {
        return geval_explain;
    }

    public void setGeval_explain(String geval_explain) {
        this.geval_explain = geval_explain;
    }

    public String getGeval_image() {
        return geval_image;
    }

    public void setGeval_image(String geval_image) {
        this.geval_image = geval_image;
    }

    public String getGeval_content_again() {
        return geval_content_again;
    }

    public void setGeval_content_again(String geval_content_again) {
        this.geval_content_again = geval_content_again;
    }

    public String getGeval_addtime_again() {
        return geval_addtime_again;
    }

    public void setGeval_addtime_again(String geval_addtime_again) {
        this.geval_addtime_again = geval_addtime_again;
    }

    public String getGeval_image_again() {
        return geval_image_again;
    }

    public void setGeval_image_again(String geval_image_again) {
        this.geval_image_again = geval_image_again;
    }

    public String getGeval_explain_again() {
        return geval_explain_again;
    }

    public void setGeval_explain_again(String geval_explain_again) {
        this.geval_explain_again = geval_explain_again;
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
}
