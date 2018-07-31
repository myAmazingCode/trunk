package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 代金券BEAN
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class StoreVoucherList {
		public static class Attr{
			public static final String VOUCHER_T_ID = "voucher_t_id";
			public static final String STORE_ID = "voucher_store_id";
			public static final String VOUCHER_PRICE = "voucher_price";
			public static final String DESC = "desc";
		}
		private String voucher_t_id;
		private String store_id;
		private String voucher_price;
		private String desc;
		
		public StoreVoucherList() {
		}

		public StoreVoucherList(String voucher_t_id, String store_id,
				String voucher_price, String desc) {
			super();
			this.voucher_t_id = voucher_t_id;
			this.store_id = store_id;
			this.voucher_price = voucher_price;
			this.desc = desc;
		}

			public static StoreVoucherList newInstanceList(String json){
				StoreVoucherList bean = null;
				try {
					JSONObject obj = new JSONObject(json);
					if(obj.length()> 0){
					String voucher_t_id = obj.optString(Attr.VOUCHER_T_ID);
					String store_id = obj.optString(Attr.STORE_ID);
					String voucher_price = obj.optString(Attr.VOUCHER_PRICE);
					String desc = obj.optString(Attr.DESC);
					bean=new StoreVoucherList(voucher_t_id, store_id, voucher_price, desc) ;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}

		public String getVoucher_t_id() {
			return voucher_t_id;
		}

		public void setVoucher_t_id(String voucher_t_id) {
			this.voucher_t_id = voucher_t_id;
		}

		public String getStore_id() {
			return store_id;
		}

		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}

		public String getVoucher_price() {
			return voucher_price;
		}

		public void setVoucher_price(String voucher_price) {
			this.voucher_price = voucher_price;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return "StoreVoucherList [voucher_t_id=" + voucher_t_id
					+ ", store_id=" + store_id + ", voucher_price="
					+ voucher_price + ", desc=" + desc + "]";
		}

}
