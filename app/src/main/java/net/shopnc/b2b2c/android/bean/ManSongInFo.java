/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:ManSongInFo.java
 */
package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 满就送Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class ManSongInFo {
		public static class Attr{
			public static final String MANSONG_NAME = "mansong_name";
			public static final String START_TIME = "start_time";
			public static final String END_TIME = "end_time";
			public static final String RULES="rules";
		}
		
		private String mansong_name;
		private String start_time;
		private String end_time;
		private String rules;
		
		public ManSongInFo() {
		}

		public ManSongInFo(String mansong_name, String start_time,
				String end_time, String rules) {
			super();
			this.mansong_name = mansong_name;
			this.start_time = start_time;
			this.end_time = end_time;
			this.rules = rules;
		}

		public static ManSongInFo newInstanceList(String json){
			ManSongInFo bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					 String mansong_name = obj.optString(Attr.MANSONG_NAME);
					 String start_time = obj.optString(Attr.START_TIME);
					 String end_time = obj.optString(Attr.END_TIME);
					 String rules = obj.optString(Attr.RULES);
					 bean = new ManSongInFo(mansong_name, start_time, end_time, rules);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}
		

		public String getRules() {
			return rules;
		}

		public void setRules(String rules) {
			this.rules = rules;
		}

		public String getMansong_name() {
			return mansong_name;
		}

		public void setMansong_name(String mansong_name) {
			this.mansong_name = mansong_name;
		}

		public String getStart_time() {
			return start_time;
		}

		public void setStart_time(String start_time) {
			this.start_time = start_time;
		}

		public String getEnd_time() {
			return end_time;
		}

		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}

		@Override
		public String toString() {
			return "ManSongInFo [mansong_name=" + mansong_name
					+ ", start_time=" + start_time + ", end_time=" + end_time
					+ ", rules=" + rules + "]";
		}


}
