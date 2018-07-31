package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class VirtualGoodsInFo {
		public static class Attr{
			public static final String GOODS_NAME = "goods_name";
			public static final String GOODS_TOTAL = "goods_total";
			public static final String GOODS_PRICE = "goods_price";
			public static final String QUNANTITY = "quantity";
			public static final String STORE_NAME = "store_name";
			public static final String GOODS_IMAGE = "goods_image";
			public static final String GOODS_ID = "goods_id";
			public static final String GOODS_IMAGE_URL = "goods_image_url";
		}
		
		private String goods_name;
		private String goods_total;
		private String goods_price;
		private String quantity;
		private String store_name;
		private String goods_image;
		private String goods_id;
		private String goods_image_url;
		
		public VirtualGoodsInFo() {
		}

		public VirtualGoodsInFo(String goods_name, String goods_total,
				String goods_price, String quantity, String store_name,
				String goods_image, String goods_id, String goods_image_url) {
			super();
			this.goods_name = goods_name;
			this.goods_total = goods_total;
			this.goods_price = goods_price;
			this.quantity = quantity;
			this.store_name = store_name;
			this.goods_image = goods_image;
			this.goods_id = goods_id;
			this.goods_image_url = goods_image_url;
		}

		public static VirtualGoodsInFo newInstanceList(String json){
			VirtualGoodsInFo bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String goods_name = obj.optString(Attr.GOODS_NAME);
					String goods_total = obj.optString(Attr.GOODS_TOTAL);
					String goods_price = obj.optString(Attr.GOODS_PRICE);
					String quantity = obj.optString(Attr.QUNANTITY);
					String store_name = obj.optString(Attr.STORE_NAME);
					String goods_image = obj.optString(Attr.GOODS_IMAGE);
					String goods_id = obj.optString(Attr.GOODS_ID);
					String goods_image_url = obj.optString(Attr.GOODS_IMAGE_URL);
					bean = new VirtualGoodsInFo(goods_name, goods_total, goods_price, quantity, store_name, goods_image, goods_id,goods_image_url);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}

		public String getGoods_name() {
			return goods_name;
		}

		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}

		public String getGoods_total() {
			return goods_total;
		}

		public void setGoods_total(String goods_total) {
			this.goods_total = goods_total;
		}

		public String getGoods_price() {
			return goods_price;
		}

		public void setGoods_price(String goods_price) {
			this.goods_price = goods_price;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		public String getStore_name() {
			return store_name;
		}

		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}

		public String getGoods_image() {
			return goods_image;
		}

		public void setGoods_image(String goods_image) {
			this.goods_image = goods_image;
		}

		public String getGoods_id() {
			return goods_id;
		}

		public void setGoods_id(String goods_id) {
			this.goods_id = goods_id;
		}

		public String getGoods_image_url() {
			return goods_image_url;
		}

		public void setGoods_image_url(String goods_image_url) {
			this.goods_image_url = goods_image_url;
		}

		@Override
		public String toString() {
			return "VirtualGoodsInFo [goods_name=" + goods_name
					+ ", goods_total=" + goods_total + ", goods_price="
					+ goods_price + ", quantity=" + quantity + ", store_name="
					+ store_name + ", goods_image=" + goods_image
					+ ", goods_id=" + goods_id + ", goods_image_url="
					+ goods_image_url + "]";
		}


}
