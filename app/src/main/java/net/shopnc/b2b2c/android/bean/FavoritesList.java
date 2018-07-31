package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 收藏商品列表Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class FavoritesList {
		public static class Attr{
			public static final String GOODS_NAME = "goods_name";
			public static final String GOODS_IMAGE_URL = "goods_image_url";
			public static final String GOODS_PRICE = "goods_price";
			public static final String FAV_ID = "fav_id";
		}
		private String goods_name;
		private String goods_image_url;
		private String goods_price;
		private String fav_id;
		
		public FavoritesList() {
		}
		
		public FavoritesList(String goods_name, String goods_image_url,
				String goods_price, String fav_id) {
			super();
			this.goods_name = goods_name;
			this.goods_image_url = goods_image_url;
			this.goods_price = goods_price;
			this.fav_id = fav_id;
		}

		public static ArrayList<FavoritesList> newInstanceList(String jsonDatas){
			ArrayList<FavoritesList> AdvertDatas = new ArrayList<FavoritesList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String goods_name = obj.optString(Attr.GOODS_NAME);
					String goods_image_url = obj.optString(Attr.GOODS_IMAGE_URL);
					String goods_price = obj.optString(Attr.GOODS_PRICE);
					String fav_id = obj.optString(Attr.FAV_ID);
					AdvertDatas.add(new FavoritesList(goods_name, goods_image_url, goods_price, fav_id));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}


		public String getGoods_name() {
			return goods_name;
		}


		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}


		public String getGoods_image_url() {
			return goods_image_url;
		}


		public void setGoods_image_url(String goods_image_url) {
			this.goods_image_url = goods_image_url;
		}


		public String getGoods_price() {
			return goods_price;
		}


		public void setGoods_price(String goods_price) {
			this.goods_price = goods_price;
		}


		public String getFav_id() {
			return fav_id;
		}


		public void setFav_id(String fav_id) {
			this.fav_id = fav_id;
		}


		@Override
		public String toString() {
			return "FavoritesList [goods_name=" + goods_name
					+ ", goods_image_url=" + goods_image_url + ", goods_price="
					+ goods_price + ", fav_id=" + fav_id + "]";
		}
}
