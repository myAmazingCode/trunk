package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 最近联系人列表
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 */
public class IMFriendsList {
		public static class Attr{
			public static final String U_ID = "u_id";
			public static final String u_NAME = "u_name";
			public static final String AVATAR = "avatar";
			public static final String FRIEND = "friend";
			public static final String RECENT = "recent";
			public static final String TIME = "time";
		}
		private String u_id;
		private String u_name;
		private String avatar;
		private String friend;
		private String recent;
		private String time;
		private int new_message_num;
		
		public IMFriendsList() {
		}

		public IMFriendsList(String u_id, String u_name, String avatar,
				String friend, String recent, String time,int new_message_num) {
			super();
			this.u_id = u_id;
			this.u_name = u_name;
			this.avatar = avatar;
			this.friend = friend;
			this.recent = recent;
			this.time = time;
			this.new_message_num = new_message_num;
		}

		public static IMFriendsList newInstanceList(String json){
			IMFriendsList bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String u_id = obj.optString(Attr.U_ID);
					String u_name = obj.optString(Attr.u_NAME);
					String avatar = obj.optString(Attr.AVATAR);
					String friend = obj.optString(Attr.FRIEND);
					String recent = obj.optString(Attr.RECENT);
					String time = obj.optString(Attr.TIME);
					 bean = new IMFriendsList(u_id, u_name, avatar, friend, recent, time,0);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}
		
		
		
		public int getNew_message_num() {
			return new_message_num;
		}

		public void setNew_message_num(int new_message_num) {
			this.new_message_num = new_message_num;
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

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getFriend() {
			return friend;
		}

		public void setFriend(String friend) {
			this.friend = friend;
		}

		public String getRecent() {
			return recent;
		}

		public void setRecent(String recent) {
			this.recent = recent;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		@Override
		public String toString() {
			return "IMFriendsList [u_id=" + u_id + ", u_name=" + u_name
					+ ", avatar=" + avatar + ", friend=" + friend + ", recent="
					+ recent + ", time=" + time + "]";
		}
}
