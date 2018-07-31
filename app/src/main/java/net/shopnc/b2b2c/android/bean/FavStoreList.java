package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 收藏店铺Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class FavStoreList {
		public static class Attr{
			public static final String STORE_ID = "store_id";
			public static final String STORE_NAME = "store_name";
			public static final String FAV_TIME = "fav_time";
			public static final String FAV_TIME_TEXT = "fav_time_text";
			public static final String GOODS_COUNT = "goods_count";
			public static final String STORE_COLLECT = "store_collect";
			public static final String STORE_AVATAR = "store_avatar";
			public static final String STORE_AVATAR_URL = "store_avatar_url";
		}
		private String store_id;
		private String store_name;
		private String fav_time;
		private String fav_time_text;
		private String goods_count;
		private String store_collect;
		private String store_avatar;
		private String store_avatar_url;
		
		public FavStoreList() {
		}
		
		public FavStoreList(String store_id, String store_name,
				String fav_time, String fav_time_text, String goods_count,
				String store_collect, String store_avatar,
				String store_avatar_url) {
			super();
			this.store_id = store_id;
			this.store_name = store_name;
			this.fav_time = fav_time;
			this.fav_time_text = fav_time_text;
			this.goods_count = goods_count;
			this.store_collect = store_collect;
			this.store_avatar = store_avatar;
			this.store_avatar_url = store_avatar_url;
		}

		public static ArrayList<FavStoreList> newInstanceList(String jsonDatas){
			ArrayList<FavStoreList> AdvertDatas = new ArrayList<FavStoreList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String store_id = obj.optString(Attr.STORE_ID);
					String store_name = obj.optString(Attr.STORE_NAME);
					String fav_time = obj.optString(Attr.FAV_TIME);
					String fav_time_text = obj.optString(Attr.FAV_TIME_TEXT);
					String goods_count = obj.optString(Attr.GOODS_COUNT);
					String store_collect = obj.optString(Attr.STORE_COLLECT);
					String store_avatar = obj.optString(Attr.STORE_AVATAR);
					String store_avatar_url = obj.optString(Attr.STORE_AVATAR_URL);
					AdvertDatas.add(new FavStoreList(store_id, store_name, fav_time, fav_time_text, goods_count, store_collect, store_avatar, store_avatar_url));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
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

		public String getFav_time() {
			return fav_time;
		}

		public void setFav_time(String fav_time) {
			this.fav_time = fav_time;
		}

		public String getFav_time_text() {
			return fav_time_text;
		}

		public void setFav_time_text(String fav_time_text) {
			this.fav_time_text = fav_time_text;
		}

		public String getGoods_count() {
			return goods_count;
		}

		public void setGoods_count(String goods_count) {
			this.goods_count = goods_count;
		}

		public String getStore_collect() {
			return store_collect;
		}

		public void setStore_collect(String store_collect) {
			this.store_collect = store_collect;
		}

		public String getStore_avatar() {
			return store_avatar;
		}

		public void setStore_avatar(String store_avatar) {
			this.store_avatar = store_avatar;
		}

		public String getStore_avatar_url() {
			return store_avatar_url;
		}

		public void setStore_avatar_url(String store_avatar_url) {
			this.store_avatar_url = store_avatar_url;
		}

		@Override
		public String toString() {
			return "FavStoreList [store_id=" + store_id + ", store_name="
					+ store_name + ", fav_time=" + fav_time
					+ ", fav_time_text=" + fav_time_text + ", goods_count="
					+ goods_count + ", store_collect=" + store_collect
					+ ", store_avatar=" + store_avatar + ", store_avatar_url="
					+ store_avatar_url + "]";
		}

}
