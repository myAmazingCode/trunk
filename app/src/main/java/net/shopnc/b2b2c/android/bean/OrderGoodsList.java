package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 订单商品列表Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com 
 */
public class OrderGoodsList {
		public static class Attr{
			public static final String BUYER_ID = "buyer_id";
			public static final String GOODS_PAY_PRICE = "goods_pay_price";
			public static final String PROMOTIONS_ID = "promotions_id";
			public static final String COMMIS_RATE = "commis_rate";
			public static final String GOODS_NUM = "goods_num";
			public static final String GOODS_IMAGE_URL = "goods_image_url";
			public static final String STORE_ID = "store_id";
			public static final String GOODS_NAME = "goods_name";
			public static final String GOODS_IMAGE = "goods_image";
			public static final String ORDER_ID = "order_id";
			public static final String GOODS_PRICE = "goods_price";
			public static final String GOODS_ID = "goods_id";
			public static final String GOODS_TYPE = "goods_type";
			public static final String REC_ID = "rec_id";
            public static final String GOODS_SPEC="goods_spec";
		}
		private String buyer_id;
		private String goods_pay_price;
		private String promotions_id;
		private String commis_rate;
		private String goods_num;
		private String goods_image_url;
		private String store_id;
		private String goods_name;
		private String goods_image;
		private String order_id;
		private String goods_price;
		private String goods_id;
		private String goods_type;
		private String rec_id;
    private String goods_spec;
		
		public OrderGoodsList() {
		}

		
		
		public OrderGoodsList(String buyer_id, String goods_pay_price,
				String promotions_id, String commis_rate, String goods_num,
				String goods_image_url, String store_id, String goods_name,
				String goods_image, String order_id, String goods_price,
				String goods_id, String goods_type, String rec_id,String goods_spec) {
			super();
			this.buyer_id = buyer_id;
			this.goods_pay_price = goods_pay_price;
			this.promotions_id = promotions_id;
			this.commis_rate = commis_rate;
			this.goods_num = goods_num;
			this.goods_image_url = goods_image_url;
			this.store_id = store_id;
			this.goods_name = goods_name;
			this.goods_image = goods_image;
			this.order_id = order_id;
			this.goods_price = goods_price;
			this.goods_id = goods_id;
			this.goods_type = goods_type;
			this.rec_id = rec_id;
            this.goods_spec=goods_spec;
		}



		public static ArrayList<OrderGoodsList> newInstanceList(String jsonDatas){
			ArrayList<OrderGoodsList> AdvertDatas = new ArrayList<OrderGoodsList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String buyer_id = obj.optString(Attr.BUYER_ID);
					String goods_pay_price = obj.optString(Attr.GOODS_PAY_PRICE);
					String promotions_id = obj.optString(Attr.PROMOTIONS_ID);
					String commis_rate = obj.optString(Attr.COMMIS_RATE);
					String goods_num = obj.optString(Attr.GOODS_NUM);
					String goods_image_url = obj.optString(Attr.GOODS_IMAGE_URL);
					String store_id = obj.optString(Attr.STORE_ID);
					String goods_name = obj.optString(Attr.GOODS_NAME);
					String goods_image = obj.optString(Attr.GOODS_IMAGE);
					String order_id = obj.optString(Attr.ORDER_ID);
                    String goods_price = obj.optString(Attr.GOODS_PRICE);
					String goods_id = obj.optString(Attr.GOODS_ID);
					String goods_type = obj.optString(Attr.GOODS_TYPE);
					String rec_id = obj.optString(Attr.REC_ID);
                    String goods_spec=obj.optString(Attr.GOODS_SPEC);
					AdvertDatas.add(new OrderGoodsList(buyer_id, goods_pay_price, promotions_id,
							commis_rate, goods_num, goods_image_url, store_id, goods_name, goods_image, order_id, goods_price, goods_id, goods_type, rec_id,goods_spec));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}



		public String getBuyer_id() {
			return buyer_id;
		}



		public void setBuyer_id(String buyer_id) {
			this.buyer_id = buyer_id;
		}



		public String getGoods_pay_price() {
			return goods_pay_price;
		}



		public void setGoods_pay_price(String goods_pay_price) {
			this.goods_pay_price = goods_pay_price;
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



		public String getGoods_num() {
			return goods_num;
		}



		public void setGoods_num(String goods_num) {
			this.goods_num = goods_num;
		}



		public String getGoods_image_url() {
			return goods_image_url;
		}



		public void setGoods_image_url(String goods_image_url) {
			this.goods_image_url = goods_image_url;
		}



		public String getStore_id() {
			return store_id;
		}



		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}



		public String getGoods_name() {
			return goods_name;
		}



		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}



		public String getGoods_image() {
			return goods_image;
		}



		public void setGoods_image(String goods_image) {
			this.goods_image = goods_image;
		}



		public String getOrder_id() {
			return order_id;
		}



		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}



		public String getGoods_price() {
			return goods_price;
		}



		public void setGoods_price(String goods_price) {
			this.goods_price = goods_price;
		}



		public String getGoods_id() {
			return goods_id;
		}



		public void setGoods_id(String goods_id) {
			this.goods_id = goods_id;
		}



		public String getGoods_type() {
			return goods_type;
		}



		public void setGoods_type(String goods_type) {
			this.goods_type = goods_type;
		}



		public String getRec_id() {
			return rec_id;
		}



		public void setRec_id(String rec_id) {
			this.rec_id = rec_id;
		}

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    @Override
		public String toString() {
			return "OrderGoodsList [buyer_id=" + buyer_id
					+ ", goods_pay_price=" + goods_pay_price
					+ ", promotions_id=" + promotions_id + ", commis_rate="
					+ commis_rate + ", goods_num=" + goods_num
					+ ", goods_image_url=" + goods_image_url + ", store_id="
					+ store_id + ", goods_name=" + goods_name
					+ ", goods_image=" + goods_image + ", order_id=" + order_id
					+ ", goods_price=" + goods_price + ", goods_id=" + goods_id
					+ ", goods_type=" + goods_type + ", rec_id=" + rec_id + "]";
		}

}
