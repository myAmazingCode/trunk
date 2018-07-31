package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 聊天记录
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 */
public class IMHistoryList {
		public static class Attr{
			public static final String F_IP = "f_ip";
			public static final String TIME = "time";
			public static final String T_ID = "t_id";
			public static final String F_NAME = "f_name";
			public static final String F_ID = "f_id";
			public static final String t_MSG = "t_msg";
			public static final String T_NAME = "t_name";
			public static final String ADD_TIME = "add_time";
			public static final String M_ID = "m_id";
		}
		private String f_ip;
		private String time;
		private String t_id;
		private String f_name;
		private String f_id;
		private String t_msg;
		private String t_name;
		private String add_time;
		private String m_id;
		
		public IMHistoryList() {
		}
		public IMHistoryList(String f_ip, String time, String t_id,
				String f_name, String f_id, String t_msg, String t_name,
				String add_time, String m_id) {
			super();
			this.f_ip = f_ip;
			this.time = time;
			this.t_id = t_id;
			this.f_name = f_name;
			this.f_id = f_id;
			this.t_msg = t_msg;
			this.t_name = t_name;
			this.add_time = add_time;
			this.m_id = m_id;
		}

		public static ArrayList<IMHistoryList> newInstanceList(String jsonDatas){
			ArrayList<IMHistoryList> AdvertDatas = new ArrayList<IMHistoryList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String f_ip = obj.optString(Attr.F_IP);
					String time = obj.optString(Attr.TIME);
					String t_id = obj.optString(Attr.T_ID);
					String f_name = obj.optString(Attr.F_NAME);
					String f_id = obj.optString(Attr.F_ID);
					String t_msg = obj.optString(Attr.t_MSG);
					String t_name = obj.optString(Attr.T_NAME);
					String add_time = obj.optString(Attr.ADD_TIME);
					String m_id = obj.optString(Attr.M_ID);
					AdvertDatas.add(new IMHistoryList(f_ip, time, t_id, f_name, f_id, t_msg,
							t_name, add_time, m_id));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}
		public String getF_ip() {
			return f_ip;
		}
		public void setF_ip(String f_ip) {
			this.f_ip = f_ip;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getT_id() {
			return t_id;
		}
		public void setT_id(String t_id) {
			this.t_id = t_id;
		}
		public String getF_name() {
			return f_name;
		}
		public void setF_name(String f_name) {
			this.f_name = f_name;
		}
		public String getF_id() {
			return f_id;
		}
		public void setF_id(String f_id) {
			this.f_id = f_id;
		}
		public String getT_msg() {
			return t_msg;
		}
		public void setT_msg(String t_msg) {
			this.t_msg = t_msg;
		}
		public String getT_name() {
			return t_name;
		}
		public void setT_name(String t_name) {
			this.t_name = t_name;
		}
		public String getAdd_time() {
			return add_time;
		}
		public void setAdd_time(String add_time) {
			this.add_time = add_time;
		}
		public String getM_id() {
			return m_id;
		}
		public void setM_id(String m_id) {
			this.m_id = m_id;
		}
		@Override
		public String toString() {
			return "IMHistoryList [f_ip=" + f_ip + ", time=" + time + ", t_id="
					+ t_id + ", f_name=" + f_name + ", f_id=" + f_id
					+ ", t_msg=" + t_msg + ", t_name=" + t_name + ", add_time="
					+ add_time + ", m_id=" + m_id + "]";
		}
}
