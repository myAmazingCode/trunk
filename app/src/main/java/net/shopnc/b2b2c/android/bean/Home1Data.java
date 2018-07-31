package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Home1 Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class Home1Data {
		public static class Attr{
			public static final String IMAGE = "image";
			public static final String TITLE = "title";
			public static final String TYPE = "type";
			public static final String DATA = "data";
		}
		
		private String image;
		private String title;
		private String type;
		private String data;
		
		public Home1Data() {
		}

		public Home1Data(String image, String title, String type, String data) {
			super();
			this.image = image;
			this.title = title;
			this.type = type;
			this.data = data;
		}

		public static Home1Data newInstanceList(String json){
			Home1Data bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String image = obj.optString(Attr.IMAGE);
					String title = obj.optString(Attr.TITLE);
					String type = obj.optString(Attr.TYPE);
					String data = obj.optString(Attr.DATA);
					bean = new Home1Data(image,title,type,data);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
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
			return "Home1Data [image=" + image + ", title=" + title + ", type="
					+ type + ", data=" + data + "]";
		}
}
