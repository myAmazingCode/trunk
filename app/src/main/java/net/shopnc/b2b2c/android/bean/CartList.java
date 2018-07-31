package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 购物车列表Bean
 * @author KingKong·HE
 * @Time 2015年1月13日
 * @E-mail hjgang@bizpoer.com
 */
public class CartList {
		public static class Attr{
			public static final String BL_ID = "bl_id";
			public static final String BUYER_ID = "buyer_id";
			public static final String GOODS_PRICE = "goods_price";
			public static final String CART_ID = "cart_id";
			public static final String GOODS_NUM = "goods_num";
			public static final String GOODS_ID = "goods_id";
			public static final String GOODS_IMAGE_URL = "goods_image_url";
			public static final String GOODS_NAME = "goods_name";
			public static final String STORE_ID = "store_id";
			public static final String GOODS_IMAGE = "goods_image";
			public static final String STORE_NAME = "store_name";
			public static final String PREMIUMS = "premiums";
			public static final String TransportId = "transport_id";
		}
		private String bl_id;
		private String buyer_id;
		private String goods_price;
		private String cart_id;
		private String goods_num;
		private String goods_id;
		private String goods_image_url;
		private String goods_name;
		private String store_id;
		private String goods_image;
		private String store_name;
		private String premiums;
		private boolean Selected =false;
	    private String transport_id;
		
		public CartList() {
		}
		

		public CartList(String bl_id, String buyer_id, String goods_price,
				String cart_id, String goods_num, String goods_id,
				String goods_image_url, String goods_name, String store_id,
				String goods_image, String store_name,String premiums,String transport_id) {
			super();
			this.bl_id = bl_id;
			this.buyer_id = buyer_id;
			this.goods_price = goods_price;
			this.cart_id = cart_id;
			this.goods_num = goods_num;
			this.goods_id = goods_id;
			this.goods_image_url = goods_image_url;
			this.goods_name = goods_name;
			this.store_id = store_id;
			this.goods_image = goods_image;
			this.store_name = store_name;
			this.premiums = premiums;
			this.transport_id = transport_id;
		}


		public static ArrayList<CartList> newInstanceList(String jsonDatas){
			ArrayList<CartList> AdvertDatas = new ArrayList<CartList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String bl_id = obj.optString(Attr.BL_ID);
					String buyer_id = obj.optString(Attr.BUYER_ID);
					String goods_price = obj.optString(Attr.GOODS_PRICE);
					String cart_id = obj.optString(Attr.CART_ID);
					String goods_num = obj.optString(Attr.GOODS_NUM);
					String goods_id = obj.optString(Attr.GOODS_ID);
					String goods_image_url = obj.optString(Attr.GOODS_IMAGE_URL);
					String goods_name = obj.optString(Attr.GOODS_NAME);
					String store_id = obj.optString(Attr.STORE_ID);
					String goods_image = obj.optString(Attr.GOODS_IMAGE);
					String store_name = obj.optString(Attr.STORE_NAME);
					String premiums = obj.optString(Attr.PREMIUMS);
					String transport_id = obj.optString(Attr.TransportId);
					AdvertDatas.add(new CartList(bl_id, buyer_id, goods_price, cart_id,
							goods_num, goods_id, goods_image_url, goods_name, store_id, goods_image, store_name
							,premiums,transport_id));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}


		public boolean isSelected() {
			return Selected;
		}


		public void setSelected(boolean selected) {
			Selected = selected;
		}


		public String getBl_id() {
			return bl_id;
		}


		public void setBl_id(String bl_id) {
			this.bl_id = bl_id;
		}


		public String getBuyer_id() {
			return buyer_id;
		}


		public void setBuyer_id(String buyer_id) {
			this.buyer_id = buyer_id;
		}


		public String getGoods_price() {
			return goods_price;
		}


		public void setGoods_price(String goods_price) {
			this.goods_price = goods_price;
		}


		public String getCart_id() {
			return cart_id;
		}


		public void setCart_id(String cart_id) {
			this.cart_id = cart_id;
		}


		public String getGoods_num() {
			return goods_num;
		}


		public void setGoods_num(String goods_num) {
			this.goods_num = goods_num;
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


		public String getGoods_name() {
			return goods_name;
		}


		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}


		public String getStore_id() {
			return store_id;
		}


		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}


		public String getGoods_image() {
			return goods_image;
		}


		public void setGoods_image(String goods_image) {
			this.goods_image = goods_image;
		}


		public String getStore_name() {
			return store_name;
		}


		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}


		public String getPremiums() {
			return premiums;
		}


		public void setPremiums(String premiums) {
			this.premiums = premiums;
		}

	public String getTransport_id() {
		return transport_id;
	}

	public void setTransport_id(String transport_id) {
		this.transport_id = transport_id;
	}

	@Override
	public String toString() {
		return "CartList{" +
				"bl_id='" + bl_id + '\'' +
				", buyer_id='" + buyer_id + '\'' +
				", goods_price='" + goods_price + '\'' +
				", cart_id='" + cart_id + '\'' +
				", goods_num='" + goods_num + '\'' +
				", goods_id='" + goods_id + '\'' +
				", goods_image_url='" + goods_image_url + '\'' +
				", goods_name='" + goods_name + '\'' +
				", store_id='" + store_id + '\'' +
				", goods_image='" + goods_image + '\'' +
				", store_name='" + store_name + '\'' +
				", premiums='" + premiums + '\'' +
				", Selected=" + Selected +
				", transport_id='" + transport_id + '\'' +
				'}';
	}
}
