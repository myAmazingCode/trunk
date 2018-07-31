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
public class VirtualList {
		public static class Attr{
			public static final String VR_INDATE = "vr_indate";
			public static final String GC_ID = "gc_id";
			public static final String CODE_LIST = "code_list";
			public static final String COMMIS_RATE = "commis_rate";
			public static final String REFUND_STATE = "refund_state";
			public static final String ORDER_STATE = "order_state";
			public static final String GOODS_NAME = "goods_name";
			public static final String VR_SEND_TIMES = "vr_send_times";
			public static final String ORDER_MSG = "order_msg";
			public static final String ORDER_ID = "order_id";
			public static final String PAYMENT_TIME = "payment_time";
			public static final String CLOSE_REASON = "close_reason";
			public static final String PAYMENT_CODE = "payment_code";
			public static final String STATE_DESC = "state_desc";
			public static final String ORDER_AMOUNT = "order_amount";
			public static final String BUYER_PHONE = "buyer_phone";
			public static final String IF_CANCEL = "if_cancel";
			public static final String GOODS_PRICE = "goods_price";
			public static final String ORDER_FROM = "order_from";
			public static final String REFUND_AMOUNT = "refund_amount";
			public static final String VR_INVALID_REFUND = "vr_invalid_refund";
			public static final String STORE_NAME = "store_name";
			public static final String BUYER_ID = "buyer_id";
			public static final String GOODS_NUM = "goods_num";
			public static final String CLOSE_TIME = "close_time";
			public static final String VOUCHER_PRICE = "voucher_price";
			public static final String STORE_ID = "store_id";
			public static final String BUYER_NAME = "buyer_name";
			public static final String ORDER_PROMOTION_TYPE = "order_promotion_type";
			public static final String GOODS_IMAGE = "goods_image";
			public static final String VOUCHER_CODE = "voucher_code";
			public static final String PD_AMOUNT = "pd_amount";
			public static final String PAYMENT_NAME = "payment_name";
			public static final String GOODS_ID = "goods_id";
			public static final String ORDER_SN = "order_sn";
			public static final String IF_PAY = "if_pay";
			public static final String ADD_TIME = "add_time";
			public static final String FINNSHED_TIME = "finnshed_time";
			public static final String DELETE_STATE = "delete_state";
			public static final String BUYER_MSG = "buyer_msg";
			public static final String ORDER_STATE_TEXT = "order_state_text";
			public static final String GOODS_IMAGE_URL = "goods_image_url";
		}
		private String vr_indate;
		private String gc_id;
		private String code_list;
		private String commis_rate;
		private String refund_state;
		private String order_state;
		private String goods_name;
		private String vr_send_times;
		private String order_msg;
		private String order_id;
		private String payment_time;
		private String close_reason;
		private String payment_code;
		private String state_desc;
		private String order_amount;
		private String buyer_phone;
		private String if_cancel;
		private String goods_price;
		private String order_from;
		private String refund_amount;
		private String vr_invalid_refund;
		private String store_name;
		private String buyer_id;
		private String goods_num;
		private String close_time;
		private String voucher_price;
		private String store_id;
		private String buyer_name;
		private String order_promotion_type;
		private String goods_image;
		private String voucher_code;
		private String pd_amount;
		private String payment_name;
		private String goods_id;
		private String order_sn;
		private String if_pay;
		private String add_time;
		private String finnshed_time;
		private String delete_state;
		private String buyer_msg;
		private String order_state_text;
		private String goods_image_url;
		
		public VirtualList() {
		}

		public VirtualList(String vr_indate, String gc_id, String code_list,
				String commis_rate, String refund_state, String order_state,
				String goods_name, String vr_send_times, String order_msg,
				String order_id, String payment_time, String close_reason,
				String payment_code, String state_desc, String order_amount,
				String buyer_phone, String if_cancel, String goods_price,
				String order_from, String refund_amount,
				String vr_invalid_refund, String store_name, String buyer_id,
				String goods_num, String close_time, String voucher_price,
				String store_id, String buyer_name,
				String order_promotion_type, String goods_image,
				String voucher_code, String pd_amount, String payment_name,
				String goods_id, String order_sn, String if_pay,
				String add_time, String finnshed_time, String delete_state,
				String buyer_msg, String order_state_text,String goods_image_url) {
			super();
			this.vr_indate = vr_indate;
			this.gc_id = gc_id;
			this.code_list = code_list;
			this.commis_rate = commis_rate;
			this.refund_state = refund_state;
			this.order_state = order_state;
			this.goods_name = goods_name;
			this.vr_send_times = vr_send_times;
			this.order_msg = order_msg;
			this.order_id = order_id;
			this.payment_time = payment_time;
			this.close_reason = close_reason;
			this.payment_code = payment_code;
			this.state_desc = state_desc;
			this.order_amount = order_amount;
			this.buyer_phone = buyer_phone;
			this.if_cancel = if_cancel;
			this.goods_price = goods_price;
			this.order_from = order_from;
			this.refund_amount = refund_amount;
			this.vr_invalid_refund = vr_invalid_refund;
			this.store_name = store_name;
			this.buyer_id = buyer_id;
			this.goods_num = goods_num;
			this.close_time = close_time;
			this.voucher_price = voucher_price;
			this.store_id = store_id;
			this.buyer_name = buyer_name;
			this.order_promotion_type = order_promotion_type;
			this.goods_image = goods_image;
			this.voucher_code = voucher_code;
			this.pd_amount = pd_amount;
			this.payment_name = payment_name;
			this.goods_id = goods_id;
			this.order_sn = order_sn;
			this.if_pay = if_pay;
			this.add_time = add_time;
			this.finnshed_time = finnshed_time;
			this.delete_state = delete_state;
			this.buyer_msg = buyer_msg;
			this.order_state_text = order_state_text;
			this.goods_image_url = goods_image_url;
		}

		public static ArrayList<VirtualList> newInstanceList(String jsonDatas){
			ArrayList<VirtualList> AdvertDatas = new ArrayList<VirtualList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String vr_indate = obj.optString(Attr.VR_INDATE);
					String gc_id = obj.optString(Attr.GC_ID);
					String code_list = obj.optString(Attr.CODE_LIST);
					String commis_rate = obj.optString(Attr.COMMIS_RATE);
					String refund_state = obj.optString(Attr.REFUND_STATE);
					String order_state = obj.optString(Attr.ORDER_STATE);
					String goods_name = obj.optString(Attr.GOODS_NAME);
					String vr_send_times = obj.optString(Attr.VR_SEND_TIMES);
					String order_msg = obj.optString(Attr.ORDER_MSG);
					String order_id = obj.optString(Attr.ORDER_ID);
					String payment_time = obj.optString(Attr.PAYMENT_TIME);
					String close_reason = obj.optString(Attr.CLOSE_REASON);
					String payment_code = obj.optString(Attr.PAYMENT_CODE);
					String state_desc = obj.optString(Attr.STATE_DESC);
					String order_amount = obj.optString(Attr.ORDER_AMOUNT);
					String buyer_phone = obj.optString(Attr.BUYER_PHONE);
					String if_cancel = obj.optString(Attr.IF_CANCEL);
					String goods_price = obj.optString(Attr.GOODS_PRICE);
					String order_from = obj.optString(Attr.ORDER_FROM);
					String refund_amount = obj.optString(Attr.REFUND_AMOUNT);
					String vr_invalid_refund = obj.optString(Attr.VR_INVALID_REFUND);
					String store_name = obj.optString(Attr.STORE_NAME);
					String buyer_id = obj.optString(Attr.BUYER_ID);
					String goods_num = obj.optString(Attr.GOODS_NUM);
					String close_time = obj.optString(Attr.CLOSE_TIME);
					String voucher_price = obj.optString(Attr.VOUCHER_PRICE);
					String store_id = obj.optString(Attr.STORE_ID);
					String buyer_name = obj.optString(Attr.BUYER_NAME);
					String order_promotion_type = obj.optString(Attr.ORDER_PROMOTION_TYPE);
					String goods_image = obj.optString(Attr.GOODS_IMAGE);
					String voucher_code = obj.optString(Attr.VOUCHER_CODE);
					String pd_amount = obj.optString(Attr.PD_AMOUNT);
					String payment_name = obj.optString(Attr.PAYMENT_NAME);
					String goods_id = obj.optString(Attr.GOODS_ID);
					String order_sn = obj.optString(Attr.ORDER_SN);
					String if_pay = obj.optString(Attr.IF_PAY);
					String add_time = obj.optString(Attr.ADD_TIME);
					String finnshed_time = obj.optString(Attr.FINNSHED_TIME);
					String delete_state = obj.optString(Attr.DELETE_STATE);
					String buyer_msg = obj.optString(Attr.BUYER_MSG);
					String order_state_text = obj.optString(Attr.ORDER_STATE_TEXT);
					String goods_image_url = obj.optString(Attr.GOODS_IMAGE_URL);
					
					AdvertDatas.add(new VirtualList(vr_indate, gc_id, code_list, commis_rate, refund_state, order_state,
							goods_name, vr_send_times, order_msg, order_id, payment_time, close_reason, payment_code,
							state_desc, order_amount, buyer_phone, if_cancel, goods_price, order_from, refund_amount, 
							vr_invalid_refund, store_name, buyer_id, goods_num, close_time, voucher_price, store_id,
							buyer_name, order_promotion_type, goods_image, voucher_code, pd_amount, payment_name,
							goods_id, order_sn, if_pay, add_time, finnshed_time, delete_state, buyer_msg, order_state_text,goods_image_url));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}
		
		public String getGoods_image_url() {
			return goods_image_url;
		}

		public void setGoods_image_url(String goods_image_url) {
			this.goods_image_url = goods_image_url;
		}

		public String getVr_indate() {
			return vr_indate;
		}

		public void setVr_indate(String vr_indate) {
			this.vr_indate = vr_indate;
		}

		public String getGc_id() {
			return gc_id;
		}

		public void setGc_id(String gc_id) {
			this.gc_id = gc_id;
		}

		public String getCode_list() {
			return code_list;
		}

		public void setCode_list(String code_list) {
			this.code_list = code_list;
		}

		public String getCommis_rate() {
			return commis_rate;
		}

		public void setCommis_rate(String commis_rate) {
			this.commis_rate = commis_rate;
		}

		public String getRefund_state() {
			return refund_state;
		}

		public void setRefund_state(String refund_state) {
			this.refund_state = refund_state;
		}

		public String getOrder_state() {
			return order_state;
		}

		public void setOrder_state(String order_state) {
			this.order_state = order_state;
		}

		public String getGoods_name() {
			return goods_name;
		}

		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}

		public String getVr_send_times() {
			return vr_send_times;
		}

		public void setVr_send_times(String vr_send_times) {
			this.vr_send_times = vr_send_times;
		}

		public String getOrder_msg() {
			return order_msg;
		}

		public void setOrder_msg(String order_msg) {
			this.order_msg = order_msg;
		}

		public String getOrder_id() {
			return order_id;
		}

		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}

		public String getPayment_time() {
			return payment_time;
		}

		public void setPayment_time(String payment_time) {
			this.payment_time = payment_time;
		}

		public String getClose_reason() {
			return close_reason;
		}

		public void setClose_reason(String close_reason) {
			this.close_reason = close_reason;
		}

		public String getPayment_code() {
			return payment_code;
		}

		public void setPayment_code(String payment_code) {
			this.payment_code = payment_code;
		}

		public String getState_desc() {
			return state_desc;
		}

		public void setState_desc(String state_desc) {
			this.state_desc = state_desc;
		}

		public String getOrder_amount() {
			return order_amount;
		}

		public void setOrder_amount(String order_amount) {
			this.order_amount = order_amount;
		}

		public String getBuyer_phone() {
			return buyer_phone;
		}

		public void setBuyer_phone(String buyer_phone) {
			this.buyer_phone = buyer_phone;
		}

		public String getIf_cancel() {
			return if_cancel;
		}

		public void setIf_cancel(String if_cancel) {
			this.if_cancel = if_cancel;
		}

		public String getGoods_price() {
			return goods_price;
		}

		public void setGoods_price(String goods_price) {
			this.goods_price = goods_price;
		}

		public String getOrder_from() {
			return order_from;
		}

		public void setOrder_from(String order_from) {
			this.order_from = order_from;
		}

		public String getRefund_amount() {
			return refund_amount;
		}

		public void setRefund_amount(String refund_amount) {
			this.refund_amount = refund_amount;
		}

		public String getVr_invalid_refund() {
			return vr_invalid_refund;
		}

		public void setVr_invalid_refund(String vr_invalid_refund) {
			this.vr_invalid_refund = vr_invalid_refund;
		}

		public String getStore_name() {
			return store_name;
		}

		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}

		public String getBuyer_id() {
			return buyer_id;
		}

		public void setBuyer_id(String buyer_id) {
			this.buyer_id = buyer_id;
		}

		public String getGoods_num() {
			return goods_num;
		}

		public void setGoods_num(String goods_num) {
			this.goods_num = goods_num;
		}

		public String getClose_time() {
			return close_time;
		}

		public void setClose_time(String close_time) {
			this.close_time = close_time;
		}

		public String getVoucher_price() {
			return voucher_price;
		}

		public void setVoucher_price(String voucher_price) {
			this.voucher_price = voucher_price;
		}

		public String getStore_id() {
			return store_id;
		}

		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}

		public String getBuyer_name() {
			return buyer_name;
		}

		public void setBuyer_name(String buyer_name) {
			this.buyer_name = buyer_name;
		}

		public String getOrder_promotion_type() {
			return order_promotion_type;
		}

		public void setOrder_promotion_type(String order_promotion_type) {
			this.order_promotion_type = order_promotion_type;
		}

		public String getGoods_image() {
			return goods_image;
		}

		public void setGoods_image(String goods_image) {
			this.goods_image = goods_image;
		}

		public String getVoucher_code() {
			return voucher_code;
		}

		public void setVoucher_code(String voucher_code) {
			this.voucher_code = voucher_code;
		}

		public String getPd_amount() {
			return pd_amount;
		}

		public void setPd_amount(String pd_amount) {
			this.pd_amount = pd_amount;
		}

		public String getPayment_name() {
			return payment_name;
		}

		public void setPayment_name(String payment_name) {
			this.payment_name = payment_name;
		}

		public String getGoods_id() {
			return goods_id;
		}

		public void setGoods_id(String goods_id) {
			this.goods_id = goods_id;
		}

		public String getOrder_sn() {
			return order_sn;
		}

		public void setOrder_sn(String order_sn) {
			this.order_sn = order_sn;
		}

		public String getIf_pay() {
			return if_pay;
		}

		public void setIf_pay(String if_pay) {
			this.if_pay = if_pay;
		}

		public String getAdd_time() {
			return add_time;
		}

		public void setAdd_time(String add_time) {
			this.add_time = add_time;
		}

		public String getFinnshed_time() {
			return finnshed_time;
		}

		public void setFinnshed_time(String finnshed_time) {
			this.finnshed_time = finnshed_time;
		}

		public String getDelete_state() {
			return delete_state;
		}

		public void setDelete_state(String delete_state) {
			this.delete_state = delete_state;
		}

		public String getBuyer_msg() {
			return buyer_msg;
		}

		public void setBuyer_msg(String buyer_msg) {
			this.buyer_msg = buyer_msg;
		}

		public String getOrder_state_text() {
			return order_state_text;
		}

		public void setOrder_state_text(String order_state_text) {
			this.order_state_text = order_state_text;
		}

		@Override
		public String toString() {
			return "VirtualList [vr_indate=" + vr_indate + ", gc_id=" + gc_id
					+ ", code_list=" + code_list + ", commis_rate="
					+ commis_rate + ", refund_state=" + refund_state
					+ ", order_state=" + order_state + ", goods_name="
					+ goods_name + ", vr_send_times=" + vr_send_times
					+ ", order_msg=" + order_msg + ", order_id=" + order_id
					+ ", payment_time=" + payment_time + ", close_reason="
					+ close_reason + ", payment_code=" + payment_code
					+ ", state_desc=" + state_desc + ", order_amount="
					+ order_amount + ", buyer_phone=" + buyer_phone
					+ ", if_cancel=" + if_cancel + ", goods_price="
					+ goods_price + ", order_from=" + order_from
					+ ", refund_amount=" + refund_amount
					+ ", vr_invalid_refund=" + vr_invalid_refund
					+ ", store_name=" + store_name + ", buyer_id=" + buyer_id
					+ ", goods_num=" + goods_num + ", close_time=" + close_time
					+ ", voucher_price=" + voucher_price + ", store_id="
					+ store_id + ", buyer_name=" + buyer_name
					+ ", order_promotion_type=" + order_promotion_type
					+ ", goods_image=" + goods_image + ", voucher_code="
					+ voucher_code + ", pd_amount=" + pd_amount
					+ ", payment_name=" + payment_name + ", goods_id="
					+ goods_id + ", order_sn=" + order_sn + ", if_pay="
					+ if_pay + ", add_time=" + add_time + ", finnshed_time="
					+ finnshed_time + ", delete_state=" + delete_state
					+ ", buyer_msg=" + buyer_msg + ", order_state_text="
					+ order_state_text + "]";
		}

}
