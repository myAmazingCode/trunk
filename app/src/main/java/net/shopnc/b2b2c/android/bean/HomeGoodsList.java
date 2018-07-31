package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 首页 HomeGoods Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class HomeGoodsList {
		public static class Attr{
			public static final String GOODS_ID = "goods_id";
			public static final String GOODS_NAME = "goods_name";
			public static final String GOODS_PROMOTION_PRICE = "goods_promotion_price";
			public static final String GOODS_IMAGE = "goods_image";
		}
		private String goods_id;
		private String goods_name;
		private String goods_promotion_price;
		private String goods_image;
		
		public HomeGoodsList() {
		}

		public HomeGoodsList(String goods_id, String goods_name,
				String goods_promotion_price, String goods_image) {
			super();
			this.goods_id = goods_id;
			this.goods_name = goods_name;
			this.goods_promotion_price = goods_promotion_price;
			this.goods_image = goods_image;
		}

		public static ArrayList<HomeGoodsList> newInstanceList(String jsonDatas){
			ArrayList<HomeGoodsList> AdvertDatas = new ArrayList<HomeGoodsList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String goods_id = obj.optString(Attr.GOODS_ID);
					String goods_name = obj.optString(Attr.GOODS_NAME);
					String goods_promotion_price = obj.optString(Attr.GOODS_PROMOTION_PRICE);
					String goods_image = obj.optString(Attr.GOODS_IMAGE);
					AdvertDatas.add(new HomeGoodsList(goods_id, goods_name, goods_promotion_price, goods_image));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
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

		public String getGoods_promotion_price() {
			return goods_promotion_price;
		}

		public void setGoods_promotion_price(String goods_promotion_price) {
			this.goods_promotion_price = goods_promotion_price;
		}

		public String getGoods_image() {
			return goods_image;
		}

		public void setGoods_image(String goods_image) {
			this.goods_image = goods_image;
		}

		@Override
		public String toString() {
			return "HomeGoodsList [goods_id=" + goods_id + ", goods_name="
					+ goods_name + ", goods_promotion_price="
					+ goods_promotion_price + ", goods_image=" + goods_image
					+ "]";
		}

}
