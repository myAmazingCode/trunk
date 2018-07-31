package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 首页 Home2 Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com 
 */
public class Home2Data {
		public static class Attr{
			public static final String TITLE = "title";
			public static final String SQUARE_IMAGE = "square_image";
			public static final String SQUARE_TYPE = "square_type";
			public static final String SQUARE_DATA = "square_data";
			public static final String RECTANGLE1_IMAGE = "rectangle1_image";
			public static final String RECTANGLE1_TYPE = "rectangle1_type";
			public static final String RECTANGLE1_DATA = "rectangle1_data";
			public static final String RECTANGLE2_IMAGE = "rectangle2_image";
			public static final String RECTANGLE2_TYPE = "rectangle2_type";
			public static final String RECTANGLE2_DATA = "rectangle2_data";
		}
		private String title;
		private String square_image;
		private String square_type;
		private String square_data;
		private String rectangle1_image;
		private String rectangle1_type;
		private String rectangle1_data;
		private String rectangle2_image;
		private String rectangle2_type;
		private String rectangle2_data;
		
		public Home2Data() {
		}
		
		public Home2Data(String title, String square_image, String square_type,String square_data,
				String rectangle1_image, String rectangle1_type,
				String rectangle1_data, String rectangle2_image,
				String rectangle2_type, String rectangle2_data) {
			super();
			this.title = title;
			this.square_image = square_image;
			this.square_type = square_type;
			this.rectangle1_image = rectangle1_image;
			this.rectangle1_type = rectangle1_type;
			this.rectangle1_data = rectangle1_data;
			this.rectangle2_image = rectangle2_image;
			this.rectangle2_type = rectangle2_type;
			this.rectangle2_data = rectangle2_data;
			this.square_data = square_data;
		}
		
		public static Home2Data newInstanceDetelis(String json){
			Home2Data bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String title = obj.optString(Attr.TITLE);
					String square_image = obj.optString(Attr.SQUARE_IMAGE);
					String square_type = obj.optString(Attr.SQUARE_TYPE);
					String square_data = obj.optString(Attr.SQUARE_DATA);
					String rectangle1_image = obj.optString(Attr.RECTANGLE1_IMAGE);
					String rectangle1_type = obj.optString(Attr.RECTANGLE1_TYPE);
					String rectangle1_data = obj.optString(Attr.RECTANGLE1_DATA);
					String rectangle2_image = obj.optString(Attr.RECTANGLE2_IMAGE);
					String rectangle2_type = obj.optString(Attr.RECTANGLE2_TYPE);
					String rectangle2_data = obj.optString(Attr.RECTANGLE2_DATA);
					bean = new Home2Data(title,square_image,square_type,square_data
							,rectangle1_image,rectangle1_type,rectangle1_data,rectangle2_image,rectangle2_type,rectangle2_data);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getSquare_image() {
			return square_image;
		}

		public void setSquare_image(String square_image) {
			this.square_image = square_image;
		}

		public String getSquare_type() {
			return square_type;
		}

		public String getSquare_data() {
			return square_data;
		}

		public void setSquare_data(String square_data) {
			this.square_data = square_data;
		}

		public void setSquare_type(String square_type) {
			this.square_type = square_type;
		}

		public String getRectangle1_image() {
			return rectangle1_image;
		}

		public void setRectangle1_image(String rectangle1_image) {
			this.rectangle1_image = rectangle1_image;
		}

		public String getRectangle1_type() {
			return rectangle1_type;
		}

		public void setRectangle1_type(String rectangle1_type) {
			this.rectangle1_type = rectangle1_type;
		}

		public String getRectangle1_data() {
			return rectangle1_data;
		}

		public void setRectangle1_data(String rectangle1_data) {
			this.rectangle1_data = rectangle1_data;
		}

		public String getRectangle2_image() {
			return rectangle2_image;
		}

		public void setRectangle2_image(String rectangle2_image) {
			this.rectangle2_image = rectangle2_image;
		}

		public String getRectangle2_type() {
			return rectangle2_type;
		}

		public void setRectangle2_type(String rectangle2_type) {
			this.rectangle2_type = rectangle2_type;
		}

		public String getRectangle2_data() {
			return rectangle2_data;
		}

		public void setRectangle2_data(String rectangle2_data) {
			this.rectangle2_data = rectangle2_data;
		}

		@Override
		public String toString() {
			return "Home2Data [title=" + title + ", square_image="
					+ square_image + ", square_type=" + square_type
					+ ", rectangle1_image=" + rectangle1_image
					+ ", rectangle1_type=" + rectangle1_type
					+ ", rectangle1_data=" + rectangle1_data
					+ ", rectangle2_image=" + rectangle2_image
					+ ", rectangle2_type=" + rectangle2_type
					+ ", rectangle2_data=" + rectangle2_data + "]";
		}
}
