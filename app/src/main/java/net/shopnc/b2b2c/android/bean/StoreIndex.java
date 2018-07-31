package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 店铺详情界面
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 * @E-mail hjgang@bizpoer.com
 */
public class StoreIndex {
		public static class Attr{
			public static final String STORE_ID = "store_id";
			public static final String STORE_NAME = "store_name";
			public static final String MEMBER_ID = "member_id";
			public static final String MEMBER_NAME = "member_name";
			public static final String STORE_AVATAR = "store_avatar";
			public static final String GOODS_COUNT = "goods_count";
			public static final String STORE_COLLECT = "store_collect";
			public static final String IS_FAVORATE = "is_favorate";
			public static final String IS_OWN_SHOP = "is_own_shop";
			public static final String STORE_CREDIT_TEXT = "store_credit_text";
			public static final String MB_TITLE_IMG = "mb_title_img";
			public static final String MB_SLIDERS = "mb_sliders";
		}
		
		public static class SAttr{
			public static final String IMG = "img";
			public static final String IMGURL = "imgUrl";
			public static final String TYPE = "type";
			public static final String LINK = "link";
		}
		
		private String store_id;
		private String store_name;
		private String store_avatar;
		private String goods_count;
		private String store_collect;
		private String is_favorate;
		private String is_own_shop;
		private String store_credit_text;
		private String mb_title_img;
		private ArrayList<StoreIndex> mb_sliders;
		private String member_name;
		private String member_id;
		private String img;
		private String imgUrl;
		private String type;
		private String link;
		
		public StoreIndex() {
		}
		
		public StoreIndex(String store_id, String store_name,
						  String store_avatar, String goods_count, String store_collect,
						  String is_favorate, String is_own_shop,
						  String store_credit_text, String mb_title_img,
						  ArrayList<StoreIndex> mb_sliders, String member_name,
						  String member_id) {
			super();
			this.store_id = store_id;
			this.store_name = store_name;
			this.store_avatar = store_avatar;
			this.goods_count = goods_count;
			this.store_collect = store_collect;
			this.is_favorate = is_favorate;
			this.is_own_shop = is_own_shop;
			this.store_credit_text = store_credit_text;
			this.mb_title_img = mb_title_img;
			this.mb_sliders = mb_sliders;
			this.member_name = member_name;
			this.member_id = member_id;
		}

		public StoreIndex(String img, String imgUrl, String type, String link) {
			super();
			this.img = img;
			this.imgUrl = imgUrl;
			this.type = type;
			this.link = link;
		}
		
		public static StoreIndex newInstanceList(String json){
			StoreIndex bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					String store_id = obj.optString(Attr.STORE_ID);
					String store_name = obj.optString(Attr.STORE_NAME);
					String store_avatar = obj.optString(Attr.STORE_AVATAR);
					String goods_count = obj.optString(Attr.GOODS_COUNT);
					String store_collect = obj.optString(Attr.STORE_COLLECT);
					String is_favorate = obj.optString(Attr.IS_FAVORATE);
					String is_own_shop = obj.optString(Attr.IS_OWN_SHOP);
					String store_credit_text = obj.optString(Attr.STORE_CREDIT_TEXT);
					String mb_title_img = obj.optString(Attr.MB_TITLE_IMG);
					String sliders = obj.optString(Attr.MB_SLIDERS);
					String member_name = obj.optString(Attr.MEMBER_NAME);
					String member_id = obj.optString(Attr.MEMBER_ID);
					ArrayList<StoreIndex> mb_sliders = newInstancePicList(sliders);
					bean = new StoreIndex(store_id, store_name, store_avatar, goods_count, store_collect, is_favorate, is_own_shop, store_credit_text, mb_title_img, mb_sliders, member_name, member_id);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}
		public static ArrayList<StoreIndex> newInstancePicList(String jsonDatas){
			ArrayList<StoreIndex> stringDatas = new ArrayList<StoreIndex>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject piclist= arr.getJSONObject(i);
					String img = piclist.getString(SAttr.IMG);
					String imgUrl = piclist.getString(SAttr.IMGURL);
					String link = piclist.getString(SAttr.LINK);
					String type = piclist.getString(SAttr.TYPE);
					stringDatas.add(new StoreIndex(img, imgUrl, type, link));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return stringDatas;
		}
		
		
		public String getMember_name() {
			return member_name;
		}

		public void setMember_name(String member_name) {
			this.member_name = member_name;
		}

		public String getMember_id() {
			return member_id;
		}

		public void setMember_id(String member_id) {
			this.member_id = member_id;
		}

		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getStore_id() {
			return store_id;
		}
		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}
		public String getStore_name() {
			return store_name;
		}
		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}
		public String getStore_avatar() {
			return store_avatar;
		}
		public void setStore_avatar(String store_avatar) {
			this.store_avatar = store_avatar;
		}
		public String getGoods_count() {
			return goods_count;
		}
		public void setGoods_count(String goods_count) {
			this.goods_count = goods_count;
		}
		public String getStore_collect() {
			return store_collect;
		}
		public void setStore_collect(String store_collect) {
			this.store_collect = store_collect;
		}
		public String getIs_favorate() {
			return is_favorate;
		}
		public void setIs_favorate(String is_favorate) {
			this.is_favorate = is_favorate;
		}
		public String getIs_own_shop() {
			return is_own_shop;
		}
		public void setIs_own_shop(String is_own_shop) {
			this.is_own_shop = is_own_shop;
		}
		public String getStore_credit_text() {
			return store_credit_text;
		}
		public void setStore_credit_text(String store_credit_text) {
			this.store_credit_text = store_credit_text;
		}
		public String getMb_title_img() {
			return mb_title_img;
		}
		public void setMb_title_img(String mb_title_img) {
			this.mb_title_img = mb_title_img;
		}
		public ArrayList<StoreIndex> getMb_sliders() {
			return mb_sliders;
		}
		public void setMb_sliders(ArrayList<StoreIndex> mb_sliders) {
			this.mb_sliders = mb_sliders;
		}
		@Override
		public String toString() {
			return "StoreInFo [store_id=" + store_id + ", store_name="
					+ store_name + ", store_avatar=" + store_avatar
					+ ", goods_count=" + goods_count + ", store_collect="
					+ store_collect + ", is_favorate=" + is_favorate
					+ ", is_own_shop=" + is_own_shop + ", store_credit_text="
					+ store_credit_text + ", mb_title_img=" + mb_title_img
					+ ", mb_sliders=" + mb_sliders + "]";
		}
}
