package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 发票详情Bean
 * @author KingKong·HE
 * @Time 2014年2月14日 下午5:09:16
 * @E-mail hjgang@bizpoer.com
 */
public class InvoiceInFO {
	public static class Attr{
		public static final String INV_ID = "inv_id";
		public static final String MEMBER_ID = "member_id";
		public static final String INV_STATE = "inv_state";
		public static final String INV_TITLE = "inv_title";
		public static final String INV_CONTENT = "inv_content";
		public static final String INV_COMPANY = "inv_company";
		public static final String INV_CODE = "inv_code";
		public static final String INV_REG_ADDR = "inv_reg_addr";
		public static final String INV_REG_PHONE = "inv_reg_phone";
		public static final String INV_REG_BNAME = "inv_reg_bname";
		public static final String INV_REG_BACCOUNT = "inv_reg_baccount";
		public static final String INV_REC_NAME = "inv_rec_name";
		public static final String INV_REC_MOBPHONE = "inv_rec_mobphone";
		public static final String INV_REC_PROVINCE = "inv_rec_province";
		public static final String INV_GOTO_ADDR = "inv_goto_addr";
		public static final String CONTENT = "content";
	}
	
	private String inv_id;
	private String member_id;
	private String inv_state;
	private String inv_title;
	private String inv_content;
	private String inv_company;
	private String inv_code;
	private String inv_reg_addr;
	private String inv_reg_phone;
	private String inv_reg_bname;
	private String inv_reg_baccount;
	private String inv_rec_name;
	private String inv_rec_mobphone;
	private String inv_rec_province;
	private String inv_goto_addr;
	private String content;
	
	public InvoiceInFO() {
	}

	public InvoiceInFO(String inv_id, String member_id, String inv_state,
			String inv_title, String inv_content, String inv_company,
			String inv_code, String inv_reg_addr, String inv_reg_phone,
			String inv_reg_bname, String inv_reg_baccount, String inv_rec_name,
			String inv_rec_mobphone, String inv_rec_province,
			String inv_goto_addr, String content) {
		super();
		this.inv_id = inv_id;
		this.member_id = member_id;
		this.inv_state = inv_state;
		this.inv_title = inv_title;
		this.inv_content = inv_content;
		this.inv_company = inv_company;
		this.inv_code = inv_code;
		this.inv_reg_addr = inv_reg_addr;
		this.inv_reg_phone = inv_reg_phone;
		this.inv_reg_bname = inv_reg_bname;
		this.inv_reg_baccount = inv_reg_baccount;
		this.inv_rec_name = inv_rec_name;
		this.inv_rec_mobphone = inv_rec_mobphone;
		this.inv_rec_province = inv_rec_province;
		this.inv_goto_addr = inv_goto_addr;
		this.content = content;
	}

	public static InvoiceInFO newInstanceList(String json){
		InvoiceInFO bean = null;
		try {
			JSONObject obj = new JSONObject(json);
			if(obj.length()> 0){
				String inv_id = obj.optString(Attr.INV_ID);
                String member_id = obj.optString(Attr.MEMBER_ID);
                String inv_state = obj.optString(Attr.INV_STATE);
				String inv_title = obj.optString(Attr.INV_TITLE);
				String inv_content = obj.optString(Attr.INV_CONTENT);
				String inv_company = obj.optString(Attr.INV_COMPANY);
				String inv_code = obj.optString(Attr.INV_CODE);
				String inv_reg_addr = obj.optString(Attr.INV_REG_ADDR);
				String inv_reg_phone = obj.optString(Attr.INV_REG_PHONE);
				String inv_reg_bname = obj.optString(Attr.INV_REG_BNAME);
				String inv_reg_baccount = obj.optString(Attr.INV_REG_BACCOUNT);
				String inv_rec_name = obj.optString(Attr.INV_REC_NAME);
				String inv_rec_mobphone = obj.optString(Attr.INV_REC_MOBPHONE);
				String inv_rec_province = obj.optString(Attr.INV_REC_PROVINCE);
				String inv_goto_addr = obj.optString(Attr.INV_GOTO_ADDR);
				String content = obj.optString(Attr.CONTENT);
				 bean = new InvoiceInFO(inv_id, member_id, inv_state, inv_title, inv_content,
						 inv_company, inv_code, inv_reg_addr, inv_reg_phone, inv_reg_bname,
						 inv_reg_baccount, inv_rec_name, inv_rec_mobphone, inv_rec_province,
						 inv_goto_addr, content);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public String getInv_id() {
		return inv_id;
	}

	public void setInv_id(String inv_id) {
		this.inv_id = inv_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getInv_state() {
		return inv_state;
	}

	public void setInv_state(String inv_state) {
		this.inv_state = inv_state;
	}

	public String getInv_title() {
		return inv_title;
	}

	public void setInv_title(String inv_title) {
		this.inv_title = inv_title;
	}

	public String getInv_content() {
		return inv_content;
	}

	public void setInv_content(String inv_content) {
		this.inv_content = inv_content;
	}

	public String getInv_company() {
		return inv_company;
	}

	public void setInv_company(String inv_company) {
		this.inv_company = inv_company;
	}

	public String getInv_code() {
		return inv_code;
	}

	public void setInv_code(String inv_code) {
		this.inv_code = inv_code;
	}

	public String getInv_reg_addr() {
		return inv_reg_addr;
	}

	public void setInv_reg_addr(String inv_reg_addr) {
		this.inv_reg_addr = inv_reg_addr;
	}

	public String getInv_reg_phone() {
		return inv_reg_phone;
	}

	public void setInv_reg_phone(String inv_reg_phone) {
		this.inv_reg_phone = inv_reg_phone;
	}

	public String getInv_reg_bname() {
		return inv_reg_bname;
	}

	public void setInv_reg_bname(String inv_reg_bname) {
		this.inv_reg_bname = inv_reg_bname;
	}

	public String getInv_reg_baccount() {
		return inv_reg_baccount;
	}

	public void setInv_reg_baccount(String inv_reg_baccount) {
		this.inv_reg_baccount = inv_reg_baccount;
	}

	public String getInv_rec_name() {
		return inv_rec_name;
	}

	public void setInv_rec_name(String inv_rec_name) {
		this.inv_rec_name = inv_rec_name;
	}

	public String getInv_rec_mobphone() {
		return inv_rec_mobphone;
	}

	public void setInv_rec_mobphone(String inv_rec_mobphone) {
		this.inv_rec_mobphone = inv_rec_mobphone;
	}

	public String getInv_rec_province() {
		return inv_rec_province;
	}

	public void setInv_rec_province(String inv_rec_province) {
		this.inv_rec_province = inv_rec_province;
	}

	public String getInv_goto_addr() {
		return inv_goto_addr;
	}

	public void setInv_goto_addr(String inv_goto_addr) {
		this.inv_goto_addr = inv_goto_addr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "InvoiceInFO [inv_id=" + inv_id + ", member_id=" + member_id
				+ ", inv_state=" + inv_state + ", inv_title=" + inv_title
				+ ", inv_content=" + inv_content + ", inv_company="
				+ inv_company + ", inv_code=" + inv_code + ", inv_reg_addr="
				+ inv_reg_addr + ", inv_reg_phone=" + inv_reg_phone
				+ ", inv_reg_bname=" + inv_reg_bname + ", inv_reg_baccount="
				+ inv_reg_baccount + ", inv_rec_name=" + inv_rec_name
				+ ", inv_rec_mobphone=" + inv_rec_mobphone
				+ ", inv_rec_province=" + inv_rec_province + ", inv_goto_addr="
				+ inv_goto_addr + ", content=" + content + "]";
	}
}
