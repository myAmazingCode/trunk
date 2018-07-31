package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 首页 Home3 Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com 
 */
public class Home3Data {
		public static class Attr{
			public static final String ITEM = "item";
			public static final String TITLE = "title";
		}
		
		public static class ItemData{
			public static final String IMAGE = "image";
			public static final String TYPE = "type";
			public static final String DATA = "data";
		}
		
		private String title;
		private String image;
		private String type;
		private String data;
		private String item;
		
		public Home3Data() {
		}

		public Home3Data(String image, String type, String data) {
			super();
			this.image = image;
			this.type = type;
			this.data = data;
		}

		public Home3Data(String title, String item) {
			super();
			this.title = title;
			this.item = item;
		}

		public static Home3Data newInstanceDetelis(String json){
			Home3Data bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String item = obj.optString(Attr.ITEM);
					String title = obj.optString(Attr.TITLE);
					bean = new Home3Data(title, item);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}
		
		public static ArrayList<Home3Data> newInstanceList(String jsonDatas){
			ArrayList<Home3Data> list = new ArrayList<Home3Data>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String image = obj.optString(ItemData.IMAGE);
					String type = obj.optString(ItemData.TYPE);
					String data = obj.optString(ItemData.DATA);
					list.add(new Home3Data(image, type, data));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return list;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getItem() {
			return item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		@Override
		public String toString() {
			return "Home3Data [title=" + title + ", image=" + image
					+ ", type=" + type + ", data=" + data + ", item="
					+ item + "]";
		}

}
