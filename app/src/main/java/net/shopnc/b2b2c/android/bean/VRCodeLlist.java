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
public class VRCodeLlist {
		public static class Attr{
			public static final String VR_CODE = "vr_code";
			public static final String VR_INDATE = "vr_indate";
		}
		private String vr_code;
		private String vr_indate;
		
		public VRCodeLlist() {
		}

		public VRCodeLlist(String vr_code, String vr_indate) {
			super();
			this.vr_code = vr_code;
			this.vr_indate = vr_indate;
		}


		public static ArrayList<VRCodeLlist> newInstanceList(String jsonDatas){
			ArrayList<VRCodeLlist> Datas = new ArrayList<VRCodeLlist>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String vr_code = obj.optString(Attr.VR_CODE);
					String vr_indate = obj.optString(Attr.VR_INDATE);
					Datas.add(new VRCodeLlist(vr_code, vr_indate));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return Datas;
		}

		public String getVr_code() {
			return vr_code;
		}

		public void setVr_code(String vr_code) {
			this.vr_code = vr_code;
		}

		public String getVr_indate() {
			return vr_indate;
		}

		public void setVr_indate(String vr_indate) {
			this.vr_indate = vr_indate;
		}

		@Override
		public String toString() {
			return "VRCodeLlist [vr_code=" + vr_code + ", vr_indate="
					+ vr_indate + "]";
		}
		
}
