package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 红包Bean
 */
public class RpacketInfo {

	private Double rpacketPrice;
	private Double rpacketLimit;
	private String rpacketId;
	private String rpacketDesc;

	public RpacketInfo(Double rpacketPrice, Double rpacketLimit, String rpacketId, String rpacketDesc) {
		this.rpacketPrice = rpacketPrice;
		this.rpacketLimit = rpacketLimit;
		this.rpacketId = rpacketId;
		this.rpacketDesc = rpacketDesc;
	}

	public static ArrayList<RpacketInfo> newInstanceList(String jsonDatas) {
		ArrayList<RpacketInfo> rpacketList = new ArrayList<RpacketInfo>();
		try {
			JSONArray arr = new JSONArray(jsonDatas);
			int size = null == arr ? 0 : arr.length();
			for (int i = 0; i < size; i++) {
				JSONObject obj = arr.getJSONObject(i);
				Double rpacketPrice = obj.optDouble("rpacket_price");
				Double rpacketLimit = obj.optDouble("rpacket_limit");
				String rpacketId = obj.optString("rpacket_t_id");
				String rpacketDesc = obj.optString("desc");
				RpacketInfo bean = new RpacketInfo(rpacketPrice,rpacketLimit,rpacketId,rpacketDesc);
				rpacketList.add(bean);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rpacketList;
	}

	public Double getRpacketPrice() {
		return rpacketPrice;
	}

	public void setRpacketPrice(Double rpacketPrice) {
		this.rpacketPrice = rpacketPrice;
	}

	public Double getRpacketLimit() {
		return rpacketLimit;
	}

	public void setRpacketLimit(Double rpacketLimit) {
		this.rpacketLimit = rpacketLimit;
	}

	public String getRpacketId() {
		return rpacketId;
	}

	public void setRpacketId(String rpacketId) {
		this.rpacketId = rpacketId;
	}

	public String getRpacketDesc() {
		return rpacketDesc;
	}

	public void setRpacketDesc(String rpacketDesc) {
		this.rpacketDesc = rpacketDesc;
	}

	@Override
	public String toString() {
		return "RpacketInfo{" +
				"rpacketPrice='" + rpacketPrice + '\'' +
				", rpacketLimit='" + rpacketLimit + '\'' +
				", rpacketId='" + rpacketId + '\'' +
				", rpacketDesc='" + rpacketDesc + '\'' +
				'}';
	}
}
		


