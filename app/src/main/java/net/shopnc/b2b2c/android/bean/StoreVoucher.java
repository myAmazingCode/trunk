package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 店铺代金券
 *
 * @author  huting
 * @date 2016-01-07
 */
public class StoreVoucher implements Serializable{
    private String voucher_t_id; //代金券模板ID
    private String voucher_t_title; //代金券模板标题
    private String voucher_t_desc; //代金券模板描述
    private String voucher_t_start_date; //代金券模板开始时间
    private String voucher_t_end_date; //代金券模板结束时间
    private String voucher_t_end_date_text; //代金券模板结束时间文字
    private String voucher_t_price; //代金券模板金额
    private String voucher_t_limit; //代金券使用时的订单限额
    private String voucher_t_store_id; //店铺ID
    private String voucher_t_storename; //店铺名称
    private String voucher_t_sc_id; //所属店铺分类ID
    private String voucher_t_state; //代金券模版状态(1-有效,2-失效)
    private String voucher_t_state_text; //代金券模版状态文字
    private String voucher_t_total; //模版可发放的代金券总数
    private String voucher_t_giveout; //模版已发放的代金券数量
    private String voucher_t_used; //模版已经使用过的代金券
    private String voucher_t_add_date; //模版的创建时间
    private String voucher_t_quotaid; //套餐ID
    private String voucher_t_points; //兑换所需积分
    private String voucher_t_eachlimit; //每人限领张数
    private String voucher_t_customimg; //自定义代金券模板图片
    private String voucher_t_recommend; //是否推荐 0不推荐 1推荐
    private String voucher_t_gettype; //领取方式 1积分兑换 2卡密兑换 3免费领取
    private String voucher_t_gettype_key; //领取方式标识
    private String voucher_t_gettype_text; //领取方式文字
    private String voucher_t_mgradelimit; //领取代金券限制的会员等级
    private String voucher_t_mgradelimittext; //限制的会员等级名称
    private String voucher_t_sc_name; //店铺分类


    public StoreVoucher(){}
    public StoreVoucher(String voucher_t_id, String voucher_t_title, String voucher_t_desc,
                        String voucher_t_start_date, String voucher_t_end_date,
                        String voucher_t_end_date_text, String voucher_t_price,
                        String voucher_t_limit, String voucher_t_store_id, String voucher_t_storename,
                        String voucher_t_sc_id, String voucher_t_state, String voucher_t_state_text,
                        String voucher_t_total, String voucher_t_giveout, String voucher_t_used,
                        String voucher_t_add_date, String voucher_t_quotaid, String voucher_t_points,
                        String voucher_t_eachlimit, String voucher_t_customimg, String voucher_t_recommend,
                        String voucher_t_gettype, String voucher_t_gettype_key, String voucher_t_gettype_text,
                        String voucher_t_mgradelimit, String voucher_t_mgradelimittext, String voucher_t_sc_name) {
        this.voucher_t_id = voucher_t_id;
        this.voucher_t_title = voucher_t_title;
        this.voucher_t_desc = voucher_t_desc;
        this.voucher_t_start_date = voucher_t_start_date;
        this.voucher_t_end_date = voucher_t_end_date;
        this.voucher_t_end_date_text = voucher_t_end_date_text;
        this.voucher_t_price = voucher_t_price;
        this.voucher_t_limit = voucher_t_limit;
        this.voucher_t_store_id = voucher_t_store_id;
        this.voucher_t_storename = voucher_t_storename;
        this.voucher_t_sc_id = voucher_t_sc_id;
        this.voucher_t_state = voucher_t_state;
        this.voucher_t_state_text = voucher_t_state_text;
        this.voucher_t_total = voucher_t_total;
        this.voucher_t_giveout = voucher_t_giveout;
        this.voucher_t_used = voucher_t_used;
        this.voucher_t_add_date = voucher_t_add_date;
        this.voucher_t_quotaid = voucher_t_quotaid;
        this.voucher_t_points = voucher_t_points;
        this.voucher_t_eachlimit = voucher_t_eachlimit;
        this.voucher_t_customimg = voucher_t_customimg;
        this.voucher_t_recommend = voucher_t_recommend;
        this.voucher_t_gettype = voucher_t_gettype;
        this.voucher_t_gettype_key = voucher_t_gettype_key;
        this.voucher_t_gettype_text = voucher_t_gettype_text;
        this.voucher_t_mgradelimit = voucher_t_mgradelimit;
        this.voucher_t_mgradelimittext = voucher_t_mgradelimittext;
        this.voucher_t_sc_name = voucher_t_sc_name;
    }

    public String getVoucher_t_id() {
        return voucher_t_id;
    }

    public void setVoucher_t_id(String voucher_t_id) {
        this.voucher_t_id = voucher_t_id;
    }

    public String getVoucher_t_title() {
        return voucher_t_title;
    }

    public void setVoucher_t_title(String voucher_t_title) {
        this.voucher_t_title = voucher_t_title;
    }

    public String getVoucher_t_desc() {
        return voucher_t_desc;
    }

    public void setVoucher_t_desc(String voucher_t_desc) {
        this.voucher_t_desc = voucher_t_desc;
    }

    public String getVoucher_t_start_date() {
        return voucher_t_start_date;
    }

    public void setVoucher_t_start_date(String voucher_t_start_date) {
        this.voucher_t_start_date = voucher_t_start_date;
    }

    public String getVoucher_t_end_date() {
        return voucher_t_end_date;
    }

    public void setVoucher_t_end_date(String voucher_t_end_date) {
        this.voucher_t_end_date = voucher_t_end_date;
    }

    public String getVoucher_t_end_date_text() {
        return voucher_t_end_date_text;
    }

    public void setVoucher_t_end_date_text(String voucher_t_end_date_text) {
        this.voucher_t_end_date_text = voucher_t_end_date_text;
    }

    public String getVoucher_t_price() {
        return voucher_t_price;
    }

    public void setVoucher_t_price(String voucher_t_price) {
        this.voucher_t_price = voucher_t_price;
    }

    public String getVoucher_t_limit() {
        return voucher_t_limit;
    }

    public void setVoucher_t_limit(String voucher_t_limit) {
        this.voucher_t_limit = voucher_t_limit;
    }

    public String getVoucher_t_store_id() {
        return voucher_t_store_id;
    }

    public void setVoucher_t_store_id(String voucher_t_store_id) {
        this.voucher_t_store_id = voucher_t_store_id;
    }

    public String getVoucher_t_storename() {
        return voucher_t_storename;
    }

    public void setVoucher_t_storename(String voucher_t_storename) {
        this.voucher_t_storename = voucher_t_storename;
    }

    public String getVoucher_t_sc_id() {
        return voucher_t_sc_id;
    }

    public void setVoucher_t_sc_id(String voucher_t_sc_id) {
        this.voucher_t_sc_id = voucher_t_sc_id;
    }

    public String getVoucher_t_state() {
        return voucher_t_state;
    }

    public void setVoucher_t_state(String voucher_t_state) {
        this.voucher_t_state = voucher_t_state;
    }

    public String getVoucher_t_state_text() {
        return voucher_t_state_text;
    }

    public void setVoucher_t_state_text(String voucher_t_state_text) {
        this.voucher_t_state_text = voucher_t_state_text;
    }

    public String getVoucher_t_total() {
        return voucher_t_total;
    }

    public void setVoucher_t_total(String voucher_t_total) {
        this.voucher_t_total = voucher_t_total;
    }

    public String getVoucher_t_giveout() {
        return voucher_t_giveout;
    }

    public void setVoucher_t_giveout(String voucher_t_giveout) {
        this.voucher_t_giveout = voucher_t_giveout;
    }

    public String getVoucher_t_used() {
        return voucher_t_used;
    }

    public void setVoucher_t_used(String voucher_t_used) {
        this.voucher_t_used = voucher_t_used;
    }

    public String getVoucher_t_add_date() {
        return voucher_t_add_date;
    }

    public void setVoucher_t_add_date(String voucher_t_add_date) {
        this.voucher_t_add_date = voucher_t_add_date;
    }

    public String getVoucher_t_quotaid() {
        return voucher_t_quotaid;
    }

    public void setVoucher_t_quotaid(String voucher_t_quotaid) {
        this.voucher_t_quotaid = voucher_t_quotaid;
    }

    public String getVoucher_t_points() {
        return voucher_t_points;
    }

    public void setVoucher_t_points(String voucher_t_points) {
        this.voucher_t_points = voucher_t_points;
    }

    public String getVoucher_t_eachlimit() {
        return voucher_t_eachlimit;
    }

    public void setVoucher_t_eachlimit(String voucher_t_eachlimit) {
        this.voucher_t_eachlimit = voucher_t_eachlimit;
    }

    public String getVoucher_t_customimg() {
        return voucher_t_customimg;
    }

    public void setVoucher_t_customimg(String voucher_t_customimg) {
        this.voucher_t_customimg = voucher_t_customimg;
    }

    public String getVoucher_t_recommend() {
        return voucher_t_recommend;
    }

    public void setVoucher_t_recommend(String voucher_t_recommend) {
        this.voucher_t_recommend = voucher_t_recommend;
    }

    public String getVoucher_t_gettype() {
        return voucher_t_gettype;
    }

    public void setVoucher_t_gettype(String voucher_t_gettype) {
        this.voucher_t_gettype = voucher_t_gettype;
    }

    public String getVoucher_t_gettype_key() {
        return voucher_t_gettype_key;
    }

    public void setVoucher_t_gettype_key(String voucher_t_gettype_key) {
        this.voucher_t_gettype_key = voucher_t_gettype_key;
    }

    public String getVoucher_t_gettype_text() {
        return voucher_t_gettype_text;
    }

    public void setVoucher_t_gettype_text(String voucher_t_gettype_text) {
        this.voucher_t_gettype_text = voucher_t_gettype_text;
    }

    public String getVoucher_t_mgradelimit() {
        return voucher_t_mgradelimit;
    }

    public void setVoucher_t_mgradelimit(String voucher_t_mgradelimit) {
        this.voucher_t_mgradelimit = voucher_t_mgradelimit;
    }

    public String getVoucher_t_mgradelimittext() {
        return voucher_t_mgradelimittext;
    }

    public void setVoucher_t_mgradelimittext(String voucher_t_mgradelimittext) {
        this.voucher_t_mgradelimittext = voucher_t_mgradelimittext;
    }

    public String getVoucher_t_sc_name() {
        return voucher_t_sc_name;
    }

    public void setVoucher_t_sc_name(String voucher_t_sc_name) {
        this.voucher_t_sc_name = voucher_t_sc_name;
    }

    @Override
    public String toString() {
        return "StoreVoucher{" +
                "voucher_t_id='" + voucher_t_id + '\'' +
                ", voucher_t_title='" + voucher_t_title + '\'' +
                ", voucher_t_desc='" + voucher_t_desc + '\'' +
                ", voucher_t_start_date='" + voucher_t_start_date + '\'' +
                ", voucher_t_end_date='" + voucher_t_end_date + '\'' +
                ", voucher_t_end_date_text='" + voucher_t_end_date_text + '\'' +
                ", voucher_t_price='" + voucher_t_price + '\'' +
                ", voucher_t_limit='" + voucher_t_limit + '\'' +
                ", voucher_t_store_id='" + voucher_t_store_id + '\'' +
                ", voucher_t_storename='" + voucher_t_storename + '\'' +
                ", voucher_t_sc_id='" + voucher_t_sc_id + '\'' +
                ", voucher_t_state='" + voucher_t_state + '\'' +
                ", voucher_t_state_text='" + voucher_t_state_text + '\'' +
                ", voucher_t_total='" + voucher_t_total + '\'' +
                ", voucher_t_giveout='" + voucher_t_giveout + '\'' +
                ", voucher_t_used='" + voucher_t_used + '\'' +
                ", voucher_t_add_date='" + voucher_t_add_date + '\'' +
                ", voucher_t_quotaid='" + voucher_t_quotaid + '\'' +
                ", voucher_t_points='" + voucher_t_points + '\'' +
                ", voucher_t_eachlimit='" + voucher_t_eachlimit + '\'' +
                ", voucher_t_customimg='" + voucher_t_customimg + '\'' +
                ", voucher_t_recommend='" + voucher_t_recommend + '\'' +
                ", voucher_t_gettype='" + voucher_t_gettype + '\'' +
                ", voucher_t_gettype_key='" + voucher_t_gettype_key + '\'' +
                ", voucher_t_gettype_text='" + voucher_t_gettype_text + '\'' +
                ", voucher_t_mgradelimit='" + voucher_t_mgradelimit + '\'' +
                ", voucher_t_mgradelimittext='" + voucher_t_mgradelimittext + '\'' +
                ", voucher_t_sc_name='" + voucher_t_sc_name + '\'' +
                '}';
    }
}
