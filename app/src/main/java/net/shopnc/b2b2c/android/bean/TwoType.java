/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:AdvertList.java
 */
package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 子分类列表Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class TwoType {
		public static class Attr{
			public static final String GC_ID = "gc_id";
			public static final String GC_NAME = "gc_name";
		}
		private String gc_id;
		private String gc_name;
		
		public TwoType() {
		}
		
		public TwoType(String gc_id, String gc_name) {
			super();
			this.gc_id = gc_id;
			this.gc_name = gc_name;
		}


		public static ArrayList<TwoType> newInstanceList(String jsonDatas){
			ArrayList<TwoType> typeList = new ArrayList<TwoType>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String gc_id = obj.optString(Attr.GC_ID);
					String gc_name = obj.optString(Attr.GC_NAME);
					TwoType t=new TwoType(gc_id, gc_name);
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

		@Override
		public String toString() {
			return "Type [gc_id=" + gc_id + ", gc_name=" + gc_name + "]";
		}

}
