package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * IM消息列表
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 */
public class IMMsgList {
		public static class Attr{
			public static final String M_ID = "m_id";
			public static final String F_ID = "f_id";
			public static final String F_NAME = "f_name";
			public static final String T_ID = "t_id";
			public static final String T_NAME = "t_name";
			public static final String t_MSG = "t_msg";
			public static final String ADD_TIME = "add_time";
			public static final String USER = "user";
		}
		
		public static class Attr2{
			public static final String U_ID = "u_id";
			public static final String U_NAME = "u_name";
			public static final String S_ID = "s_id";
			public static final String UPDAE_TIME = "update_time";
			public static final String CONNECTED = "connected";
			public static final String S_NAME = "s_name";
			public static final String AVATAR = "avatar";
			public static final String ONLINE = "online";
		}
		
		private String m_id;
		private String f_id;
		private String f_name;
		private String t_id;
		private String t_name;
		private String t_msg;
		private String add_time;
		private String user;
		
		private boolean viewFlag = true;//
		
		private String u_id;
		private String u_name;
		private String s_id;
		private String update_time;
		private String connected;
		private String s_name;
		private String avatar;
		private String online;
		
		public IMMsgList() {
		}

		public IMMsgList(String m_id, String f_id, String f_name, String t_id,
				String t_name, String t_msg, String add_time, String user) {
			super();
			this.m_id = m_id;
			this.f_id = f_id;
			this.f_name = f_name;
			this.t_id = t_id;
			this.t_name = t_name;
			this.t_msg = t_msg;
			this.add_time = add_time;
			this.user = user;
		}
		

		public IMMsgList(String u_name, String s_id,
				String update_time, String connected, String s_name,
				String avatar, String online) {
			super();
			this.u_name = u_name;
			this.s_id = s_id;
			this.update_time = update_time;
			this.connected = connected;
			this.s_name = s_name;
			this.avatar = avatar;
			this.online = online;
		}

		public static IMMsgList newInstanceList(String json){
			IMMsgList bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String m_id = obj.optString(Attr.M_ID);
					String f_id = obj.optString(Attr.F_ID);
					String f_name = obj.optString(Attr.F_NAME);
					String t_id = obj.optString(Attr.T_ID);
					String t_name = obj.optString(Attr.T_NAME);
					String t_msg = obj.optString(Attr.t_MSG);
					String add_time = obj.optString(Attr.ADD_TIME);
					String user = obj.optString(Attr.USER);
					 bean = new IMMsgList(m_id, f_id, f_name, t_id, t_name, t_msg, add_time, user);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}
		
		public static IMMsgList newInstanceUserBean(String json){
			IMMsgList bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String u_name = obj.optString(Attr2.U_NAME);
					String s_id = obj.optString(Attr2.S_ID);
					String update_time = obj.optString(Attr2.UPDAE_TIME);
					String connected = obj.optString(Attr2.CONNECTED);
					String s_name = obj.optString(Attr2.S_NAME);
					String avatar = obj.optString(Attr2.AVATAR);
					String online = obj.optString(Attr2.ONLINE);
					 bean = new IMMsgList(u_name, s_id, update_time, connected, s_name, avatar, online);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}
		
		

		public String getU_id() {
			return u_id;
		}

		public void setU_id(String u_id) {
			this.u_id = u_id;
		}

		public String getU_name() {
			return u_name;
		}

		public void setU_name(String u_name) {
			this.u_name = u_name;
		}

		public String getS_id() {
			return s_id;
		}

		public void setS_id(String s_id) {
			this.s_id = s_id;
		}

		public String getUpdate_time() {
			return update_time;
		}

		public void setUpdate_time(String update_time) {
			this.update_time = update_time;
		}

		public String getConnected() {
			return connected;
		}

		public void setConnected(String connected) {
			this.connected = connected;
		}

		public String getS_name() {
			return s_name;
		}

		public void setS_name(String s_name) {
			this.s_name = s_name;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getOnline() {
			return online;
		}

		public void setOnline(String online) {
			this.online = online;
		}

		public String getM_id() {
			return m_id;
		}

		public void setM_id(String m_id) {
			this.m_id = m_id;
		}

		public String getF_id() {
			return f_id;
		}

		public void setF_id(String f_id) {
			this.f_id = f_id;
		}

		public String getF_name() {
			return f_name;
		}

		public void setF_name(String f_name) {
			this.f_name = f_name;
		}

		public String getT_id() {
			return t_id;
		}

		public void setT_id(String t_id) {
			this.t_id = t_id;
		}

		public String getT_name() {
			return t_name;
		}

		public void setT_name(String t_name) {
			this.t_name = t_name;
		}

		public String getT_msg() {
			return t_msg;
		}

		public void setT_msg(String t_msg) {
			this.t_msg = t_msg;
		}

		public String getAdd_time() {
			return add_time;
		}

		public void setAdd_time(String add_time) {
			this.add_time = add_time;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}
		


		public boolean isViewFlag() {
			return viewFlag;
		}

		public void setViewFlag(boolean viewFlag) {
			this.viewFlag = viewFlag;
		}

		@Override
		public String toString() {
			return "IMMsgList [m_id=" + m_id + ", f_id=" + f_id + ", f_name="
					+ f_name + ", t_id=" + t_id + ", t_name=" + t_name
					+ ", t_msg=" + t_msg + ", add_time=" + add_time + ", user="
					+ user + "]";
		}

}
