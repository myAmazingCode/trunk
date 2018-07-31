package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 购买商品列表Bean
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com 
 */
public class PlayGoodsList {
		public static class Attr{
			public static final String GOODS_LIST = "goods_list";
			public static final String STORE_GOODS_TOTAL = "store_goods_total";
			public static final String STORE_PREMIUMS_LIST = "store_premiums_list";
			public static final String STORE_MANSONG_RULE_LIST = "store_mansong_rule_list";
			public static final String STORE_VOUCHER_LIST = "store_voucher_list";
			public static final String FREIGHT = "freight";
			public static final String STORE_NAME = "store_name";
		}
		private String store_id;
		private String goods_list;
		private String store_goods_total;
		private String store_premiums_list;
		private String store_mansong_rule_list;
		private String store_voucher_list;
		private String freight;
		private String store_name;
		private String freight_val;
		
		public PlayGoodsList() {
		}
		

		public PlayGoodsList(String goods_list, String store_goods_total,
				String store_premiums_list, String store_mansong_rule_list,
				String store_voucher_list, String freight,String store_name) {
			super();
			this.goods_list = goods_list;
			this.store_goods_total = store_goods_total;
			this.store_premiums_list = store_premiums_list;
			this.store_mansong_rule_list = store_mansong_rule_list;
			this.store_voucher_list = store_voucher_list;
			this.freight = freight;
			this.store_name = store_name;
		}

		public static PlayGoodsList newInstanceList(String json){
			PlayGoodsList bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String goods_list = obj.optString(Attr.GOODS_LIST);
					String store_goods_total = obj.optString(Attr.STORE_GOODS_TOTAL);
					String store_premiums_list = obj.optString(Attr.STORE_PREMIUMS_LIST);
					String store_mansong_rule_list = obj.optString(Attr.STORE_MANSONG_RULE_LIST);
					String store_voucher_list = obj.optString(Attr.STORE_VOUCHER_LIST);
					String freight = obj.optString(Attr.FREIGHT);
					String store_name = obj.optString(Attr.STORE_NAME);
					 bean = new PlayGoodsList(goods_list, store_goods_total, store_premiums_list, store_mansong_rule_list, store_voucher_list, freight,store_name);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}

		
		
		public String getStore_id() {
			return store_id;
		}


		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}


		public String getFreight_val() {
			return freight_val;
		}


		public void setFreight_val(String freight_val) {
			this.freight_val = freight_val;
		}


		public String getGoods_list() {
			return goods_list;
		}


		public void setGoods_list(String goods_list) {
			this.goods_list = goods_list;
		}


		public String getStore_name() {
			return store_name;
		}


		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}


		public String getStore_goods_total() {
			return store_goods_total;
		}


		public void setStore_goods_total(String store_goods_total) {
			this.store_goods_total = store_goods_total;
		}


		public String getStore_premiums_list() {
			return store_premiums_list;
		}


		public void setStore_premiums_list(String store_premiums_list) {
			this.store_premiums_list = store_premiums_list;
		}


		public String getStore_mansong_rule_list() {
			return store_mansong_rule_list;
		}


		public void setStore_mansong_rule_list(String store_mansong_rule_list) {
			this.store_mansong_rule_list = store_mansong_rule_list;
		}


		public String getStore_voucher_list() {
			return store_voucher_list;
		}


		public void setStore_voucher_list(String store_voucher_list) {
			this.store_voucher_list = store_voucher_list;
		}


		public String getFreight() {
			return freight;
		}


		public void setFreight(String freight) {
			this.freight = freight;
		}


		@Override
		public String toString() {
			return "StoreCartList [store_id=" + store_id + ", goods_list="
					+ goods_list + ", store_goods_total=" + store_goods_total
					+ ", store_premiums_list=" + store_premiums_list
					+ ", store_mansong_rule_list=" + store_mansong_rule_list
					+ ", store_voucher_list=" + store_voucher_list
					+ ", freight=" + freight + ", store_name=" + store_name
					+ ", freight_val=" + freight_val + "]";
		}




}
