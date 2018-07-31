package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class StoreGoodsClassList {

	public static final String LEVEL_ONE = "1";//级别1
	public static final String LEVEL_TWO = "2";//级别2


	public static class Attr{
			public static final String ID = "id";
			public static final String PID = "pid";
			public static final String LEVEL = "level";
			public static final String NAME = "name";
		}
		private String id;
		private int pid;
		private int level;
		private String name;
		
		public StoreGoodsClassList() {
		}
		
		public StoreGoodsClassList(String id, int pid, int level,
				String name) {
			super();
			this.id = id;
			this.pid = pid;
			this.level = level;
			this.name = name;
		}

		public static ArrayList<StoreGoodsClassList> newInstanceGList(String jsonDatas){
			ArrayList<StoreGoodsClassList> AdvertDatas = new ArrayList<StoreGoodsClassList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.ID);
					int pid = obj.optInt(Attr.PID);
					int level = obj.optInt(Attr.LEVEL);
					String name = obj.optString(Attr.NAME);
					if(pid == 0){
						AdvertDatas.add(new StoreGoodsClassList(id, pid, level, name));
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}
		
		public static ArrayList<StoreGoodsClassList> newInstanceCList(String jsonDatas,int fid){
			ArrayList<StoreGoodsClassList> AdvertDatas = new ArrayList<StoreGoodsClassList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.ID);
					int pid = obj.optInt(Attr.PID);
					int level = obj.optInt(Attr.LEVEL);
					String name = obj.optString(Attr.NAME);
					if(pid != 0 && fid == pid){
						AdvertDatas.add(new StoreGoodsClassList(id, pid, level, name));
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getPid() {
			return pid;
		}

		public void setPid(int pid) {
			this.pid = pid;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "StoreGoodsClassList [id=" + id + ", pid=" + pid
					+ ", level=" + level + ", name=" + name + "]";
		}
}
