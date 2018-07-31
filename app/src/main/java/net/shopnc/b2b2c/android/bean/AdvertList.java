package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 首页Top广告图Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class AdvertList {
		public static class Attr{
			public static final String IMAGE = "image";
			public static final String TYPE = "type";
			public static final String DATA = "data";
		}
		private String image;
		private String type;
		private String data;
		
		public AdvertList() {
		}

		public AdvertList(String image, String type, String data) {
			super();
			this.image = image;
			this.type = type;
			this.data = data;
		}


		public static ArrayList<AdvertList> newInstanceList(String jsonDatas){
			ArrayList<AdvertList> AdvertDatas = new ArrayList<AdvertList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String image = obj.optString(Attr.IMAGE);
					String data = obj.optString(Attr.DATA);
					String type = obj.optString(Attr.TYPE);
					AdvertDatas.add(new AdvertList(image, type, data));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
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

		@Override
		public String toString() {
			return "AdvertList [image=" + image + ", type=" + type + ", data="
					+ data + "]";
		}
}
