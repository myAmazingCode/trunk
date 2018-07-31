package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 城市Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class CityList {
		public static class Attr{
			public static final String AREA_ID = "area_id";
			public static final String AREA_NAME = "area_name";
		}
		private String area_id;
		private String area_name;
		
		public CityList() {
		}

		public CityList(String area_id, String area_name) {
			super();
			this.area_id = area_id;
			this.area_name = area_name;
		}

		public static ArrayList<CityList> newInstanceList(String jsonDatas){
			ArrayList<CityList> AdvertDatas = new ArrayList<CityList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String area_id = obj.optString(Attr.AREA_ID);
					String area_name = obj.optString(Attr.AREA_NAME);
					AdvertDatas.add(new CityList(area_id, area_name));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}

		public String getArea_id() {
			return area_id;
		}

		public void setArea_id(String area_id) {
			this.area_id = area_id;
		}

		public String getArea_name() {
			return area_name;
		}

		public void setArea_name(String area_name) {
			this.area_name = area_name;
		}

		@Override
		public String toString() {
			return "CityList [area_id=" + area_id + ", area_name=" + area_name
					+ "]";
		}
}
