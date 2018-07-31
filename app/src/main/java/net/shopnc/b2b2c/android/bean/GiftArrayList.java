package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 赠品BEAN
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class GiftArrayList {
		public static class Attr{
			public static final String GIFT_ID = "gift_id";
			public static final String GOODS_ID = "goods_id";
			public static final String GOODS_COMMONID = "goods_commonid";
			public static final String GIFT_GOODSID = "gift_goodsid";
			public static final String GIFT_GOODSNAME = "gift_goodsname";
			public static final String GIFT_GOODSIMAGE = "gift_goodsimage";
			public static final String GIFT_AMOUNT = "gift_amount";
		}
		private String gift_id;
		private String goods_id;
		private String goods_commonid;
		private String gift_goodsid;
		private String gift_goodsname;
		private String gift_goodsimage;
		private String gift_amount;
		
		public GiftArrayList() {
		}
		

		public GiftArrayList(String gift_id, String goods_id,
				String goods_commonid, String gift_goodsid,
				String gift_goodsname, String gift_goodsimage,
				String gift_amount) {
			super();
			this.gift_id = gift_id;
			this.goods_id = goods_id;
			this.goods_commonid = goods_commonid;
			this.gift_goodsid = gift_goodsid;
			this.gift_goodsname = gift_goodsname;
			this.gift_goodsimage = gift_goodsimage;
			this.gift_amount = gift_amount;
		}


		public static ArrayList<GiftArrayList> newInstanceList(String jsonDatas){
			ArrayList<GiftArrayList> AdvertDatas = new ArrayList<GiftArrayList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String gift_id = obj.optString(Attr.GIFT_ID);
					String goods_id = obj.optString(Attr.GOODS_ID);
					String goods_commonid = obj.optString(Attr.GOODS_COMMONID);
					String gift_goodsid = obj.optString(Attr.GIFT_GOODSID);
					String gift_goodsname = obj.optString(Attr.GIFT_GOODSNAME);
					String gift_goodsimage = obj.optString(Attr.GIFT_GOODSIMAGE);
					String gift_amount = obj.optString(Attr.GIFT_AMOUNT);
					AdvertDatas.add(new GiftArrayList(gift_id, goods_id, goods_commonid, gift_goodsid, gift_goodsname, gift_goodsimage, gift_amount));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}


		public String getGift_id() {
			return gift_id;
		}


		public void setGift_id(String gift_id) {
			this.gift_id = gift_id;
		}


		public String getGoods_id() {
			return goods_id;
		}


		public void setGoods_id(String goods_id) {
			this.goods_id = goods_id;
		}


		public String getGoods_commonid() {
			return goods_commonid;
		}


		public void setGoods_commonid(String goods_commonid) {
			this.goods_commonid = goods_commonid;
		}


		public String getGift_goodsid() {
			return gift_goodsid;
		}


		public void setGift_goodsid(String gift_goodsid) {
			this.gift_goodsid = gift_goodsid;
		}


		public String getGift_goodsname() {
			return gift_goodsname;
		}


		public void setGift_goodsname(String gift_goodsname) {
			this.gift_goodsname = gift_goodsname;
		}


		public String getGift_goodsimage() {
			return gift_goodsimage;
		}


		public void setGift_goodsimage(String gift_goodsimage) {
			this.gift_goodsimage = gift_goodsimage;
		}


		public String getGift_amount() {
			return gift_amount;
		}


		public void setGift_amount(String gift_amount) {
			this.gift_amount = gift_amount;
		}


		@Override
		public String toString() {
			return "GiftArrayList [gift_id=" + gift_id + ", goods_id="
					+ goods_id + ", goods_commonid=" + goods_commonid
					+ ", gift_goodsid=" + gift_goodsid + ", gift_goodsname="
					+ gift_goodsname + ", gift_goodsimage=" + gift_goodsimage
					+ ", gift_amount=" + gift_amount + "]";
		}

}
