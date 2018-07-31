package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * IM用户资料
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 */
public class IMMemberInFo {
		public static class Attr{
			public static final String MEMBER_ID = "member_id";
			public static final String MEMBER_NAME = "member_name";
			public static final String MEMBER_AVATAR = "member_avatar";
			public static final String STORE_NAME = "store_name";
			public static final String GRADE_ID = "grade_id";
			public static final String STORE_ID = "store_id";
			public static final String SELLER_NAME = "seller_name";
		}
		
		private String member_id;
		private String member_name;
		private String member_avatar;
		private String store_name;
		private String grade_id;
		private String store_id;
		private String seller_name;
		
		public IMMemberInFo() {
		}


		public IMMemberInFo(String member_id, String member_name,
				String member_avatar, String store_name, String grade_id,
				String store_id, String seller_name) {
			super();
			this.member_id = member_id;
			this.member_name = member_name;
			this.member_avatar = member_avatar;
			this.store_name = store_name;
			this.grade_id = grade_id;
			this.store_id = store_id;
			this.seller_name = seller_name;
		}


		public static IMMemberInFo newInstanceList(String json){
			IMMemberInFo bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String member_id = obj.optString(Attr.MEMBER_ID);
					String member_name = obj.optString(Attr.MEMBER_NAME);
					String member_avatar = obj.optString(Attr.MEMBER_AVATAR);
					String store_name = obj.optString(Attr.STORE_NAME);
					String grade_id = obj.optString(Attr.GRADE_ID);
					String store_id = obj.optString(Attr.STORE_ID);
					String seller_name = obj.optString(Attr.SELLER_NAME);
					bean = new IMMemberInFo(member_id, member_name, member_avatar, store_name, grade_id, store_id, seller_name);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}


		public String getMember_id() {
			return member_id;
		}


		public void setMember_id(String member_id) {
			this.member_id = member_id;
		}


		public String getMember_name() {
			return member_name;
		}


		public void setMember_name(String member_name) {
			this.member_name = member_name;
		}


		public String getMember_avatar() {
			return member_avatar;
		}


		public void setMember_avatar(String member_avatar) {
			this.member_avatar = member_avatar;
		}


		public String getStore_name() {
			return store_name;
		}


		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}


		public String getGrade_id() {
			return grade_id;
		}


		public void setGrade_id(String grade_id) {
			this.grade_id = grade_id;
		}


		public String getStore_id() {
			return store_id;
		}


		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}


		public String getSeller_name() {
			return seller_name;
		}


		public void setSeller_name(String seller_name) {
			this.seller_name = seller_name;
		}


		@Override
		public String toString() {
			return "IMMemberInFo [member_id=" + member_id + ", member_name="
					+ member_name + ", member_avatar=" + member_avatar
					+ ", store_name=" + store_name + ", grade_id=" + grade_id
					+ ", store_id=" + store_id + ", seller_name=" + seller_name
					+ "]";
		}

}
