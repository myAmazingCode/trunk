package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 满就送Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class ManSongRules {
		public static class Attr{
			public static final String PRICE = "price";
			public static final String DISCOUNT = "discount";
			public static final String MANSONG_GOODS_NAME = "mansong_goods_name";
			public static final String GOODS_ID = "goods_id";
			public static final String GOODS_IMAGE_URL = "goods_image_url";
		}
		private String price;
		private String discount;
		private String mansong_goods_name;
		private String goods_id;
		private String goods_image_url;
		
		public ManSongRules() {
		}
		
		public ManSongRules(String price, String discount,
				String mansong_goods_name, String goods_id,
				String goods_image_url) {
			super();
			this.price = price;
			this.discount = discount;
			this.mansong_goods_name = mansong_goods_name;
			this.goods_id = goods_id;
			this.goods_image_url = goods_image_url;
		}

		public static ArrayList<ManSongRules> newInstanceList(String jsonDatas){
			ArrayList<ManSongRules> rules_list = new ArrayList<ManSongRules>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String price = obj.optString(Attr.PRICE);
					String discount = obj.optString(Attr.DISCOUNT);
					String mansong_goods_name = obj.optString(Attr.MANSONG_GOODS_NAME);
					String goods_id = obj.optString(Attr.GOODS_ID);
					String goods_image_url = obj.optString(Attr.GOODS_IMAGE_URL);
					ManSongRules rules = new ManSongRules(price, discount, mansong_goods_name, goods_id, goods_image_url);
					rules_list.add(rules);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return rules_list;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getDiscount() {
			return discount;
		}

		public void setDiscount(String discount) {
			this.discount = discount;
		}

		public String getMansong_goods_name() {
			return mansong_goods_name;
		}

		public void setMansong_goods_name(String mansong_goods_name) {
			this.mansong_goods_name = mansong_goods_name;
		}

		public String getGoods_id() {
			return goods_id;
		}

		public void setGoods_id(String goods_id) {
			this.goods_id = goods_id;
		}

		public String getGoods_image_url() {
			return goods_image_url;
		}

		public void setGoods_image_url(String goods_image_url) {
			this.goods_image_url = goods_image_url;
		}

		@Override
		public String toString() {
			return "ManSongRules [price=" + price + ", discount=" + discount
					+ ", mansong_goods_name=" + mansong_goods_name
					+ ", goods_id=" + goods_id + ", goods_image_url="
					+ goods_image_url + "]";
		}

}
