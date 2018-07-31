package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 分类列表Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class OneType {
		public static class Attr{
			public static final String GC_ID = "gc_id";
			public static final String GC_NAME = "gc_name";
			public static final String IMAGE = "image";
			public static final String TEXT = "text";
		}
		private String gc_id;
		private String gc_name;
		private String image;
		private String text;
		
		public OneType() {
		}
		
		public OneType(String gc_id, String gc_name, String image, String text) {
			super();
			this.gc_id = gc_id;
			this.gc_name = gc_name;
			this.image = image;
			this.text = text;
		}


		public static ArrayList<OneType> newInstanceList(String jsonDatas){
			ArrayList<OneType> typeList = new ArrayList<OneType>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String gc_id = obj.optString(Attr.GC_ID);
					String gc_name = obj.optString(Attr.GC_NAME);
					String image = obj.optString(Attr.IMAGE);
					String text = obj.optString(Attr.TEXT);
					OneType t=new OneType(gc_id, gc_name, image, text);
					typeList.add(t);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return typeList;
		}


		public String getGc_id() {
			return gc_id;
		}


		public void setGc_id(String gc_id) {
			this.gc_id = gc_id;
		}


		public String getGc_name() {
			return gc_name;
		}


		public void setGc_name(String gc_name) {
			this.gc_name = gc_name;
		}


		public String getImage() {
			return image;
		}


		public void setImage(String image) {
			this.image = image;
		}


		public String getText() {
			return text;
		}


		public void setText(String text) {
			this.text = text;
		}


		@Override
		public String toString() {
			return "Type [gc_id=" + gc_id + ", gc_name=" + gc_name + ", image="
					+ image + ", text=" + text + "]";
		}

}
