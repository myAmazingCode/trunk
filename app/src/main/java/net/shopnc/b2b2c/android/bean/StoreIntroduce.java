package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 店铺简介
 *
 * @author  huting
 * @date 2016-01-07
 */
public class StoreIntroduce implements Serializable{
    private String store_id;//店铺编号
    private String store_name;// 店铺名称
    private String grade_id; //等级
    private String member_id; //会员ID
    private String member_name; //会员名称
    private String seller_name; //店主名
    private String sc_id; //店铺分类ID
    private String sc_name; //店铺分类名称
    private String store_company_name; //公司名称
    private String province_id; //省份ID
    private String area_info; //地区详情
    private String store_address; //店铺地址
    private String store_zip; //店铺邮编
    private String store_state; //店铺状态
    private String store_time; //开店时间
    private String store_end_time; //结束时间
    private String store_label; //店铺logo
    private String store_banner; //店铺横幅
    private String store_avatar; //店铺头像
    private String store_keywords; //店铺seo关键字
    private String store_description; //店铺seo描述
    private String store_qq; //店铺QQ
    private String store_ww; //阿里旺旺
    private String store_phone; //商家电话
    private String store_zy; //主营商品
    private String store_domain; //店铺二级域名
    private String store_recommend; //推荐
    private String store_theme; //店铺当前主题
    private String store_credit; //店铺信用(还有单独的类)
    private String store_collect; //店铺收藏数
    private String store_slide; //幻灯片
    private String store_slide_url; //店铺幻灯片链接
    private String store_sales; //店铺销量
    private String store_presales; //售前客服
    private String store_aftersales; //售后客服
    private String store_workingtime; //工作时间
    private String store_free_price; //超出该金额免运费
    private String is_own_shop; //是否自营店铺
    private String mb_title_img; //手机店铺页头背景图
    private String mb_sliders; //手机店铺轮播图链接地址
    private String store_time_text;

    public StoreIntroduce(){}

    public StoreIntroduce(String store_id, String store_name, String grade_id, String member_id,
                          String member_name, String seller_name, String sc_id, String sc_name,
                          String store_company_name, String province_id, String area_info,
                          String store_address, String store_zip, String store_state,
                          String store_time, String store_end_time, String store_label,
                          String store_banner, String store_avatar, String store_keywords,
                          String store_description, String store_qq, String store_ww,
                          String store_phone, String store_zy, String store_domain,
                          String store_recommend, String store_theme, String store_credit,
                          String store_collect, String store_slide, String store_slide_url,
                          String store_sales, String store_presales, String store_aftersales,
                          String store_workingtime, String store_free_price, String is_own_shop,
                          String mb_title_img, String mb_sliders, String store_time_text) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.grade_id = grade_id;
        this.member_id = member_id;
        this.member_name = member_name;
        this.seller_name = seller_name;
        this.sc_id = sc_id;
        this.sc_name = sc_name;
        this.store_company_name = store_company_name;
        this.province_id = province_id;
        this.area_info = area_info;
        this.store_address = store_address;
        this.store_zip = store_zip;
        this.store_state = store_state;
        this.store_time = store_time;
        this.store_end_time = store_end_time;
        this.store_label = store_label;
        this.store_banner = store_banner;
        this.store_avatar = store_avatar;
        this.store_keywords = store_keywords;
        this.store_description = store_description;
        this.store_qq = store_qq;
        this.store_ww = store_ww;
        this.store_phone = store_phone;
        this.store_zy = store_zy;
        this.store_domain = store_domain;
        this.store_recommend = store_recommend;
        this.store_theme = store_theme;
        this.store_credit = store_credit;
        this.store_collect = store_collect;
        this.store_slide = store_slide;
        this.store_slide_url = store_slide_url;
        this.store_sales = store_sales;
        this.store_presales = store_presales;
        this.store_aftersales = store_aftersales;
        this.store_workingtime = store_workingtime;
        this.store_free_price = store_free_price;
        this.is_own_shop = is_own_shop;
        this.mb_title_img = mb_title_img;
        this.mb_sliders = mb_sliders;
        this.store_time_text = store_time_text;
    }

    public String getStore_time_text() {
        return store_time_text;
    }

    public void setStore_time_text(String store_time_text) {
        this.store_time_text = store_time_text;
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

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSc_id() {
        return sc_id;
    }

    public void setSc_id(String sc_id) {
        this.sc_id = sc_id;
    }

    public String getSc_name() {
        return sc_name;
    }

    public void setSc_name(String sc_name) {
        this.sc_name = sc_name;
    }

    public String getStore_company_name() {
        return store_company_name;
    }

    public void setStore_company_name(String store_company_name) {
        this.store_company_name = store_company_name;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getArea_info() {
        return area_info;
    }

    public void setArea_info(String area_info) {
        this.area_info = area_info;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_zip() {
        return store_zip;
    }

    public void setStore_zip(String store_zip) {
        this.store_zip = store_zip;
    }

    public String getStore_state() {
        return store_state;
    }

    public void setStore_state(String store_state) {
        this.store_state = store_state;
    }

    public String getStore_time() {
        return store_time;
    }

    public void setStore_time(String store_time) {
        this.store_time = store_time;
    }

    public String getStore_end_time() {
        return store_end_time;
    }

    public void setStore_end_time(String store_end_time) {
        this.store_end_time = store_end_time;
    }

    public String getStore_label() {
        return store_label;
    }

    public void setStore_label(String store_label) {
        this.store_label = store_label;
    }

    public String getStore_banner() {
        return store_banner;
    }

    public void setStore_banner(String store_banner) {
        this.store_banner = store_banner;
    }

    public String getStore_avatar() {
        return store_avatar;
    }

    public void setStore_avatar(String store_avatar) {
        this.store_avatar = store_avatar;
    }

    public String getStore_keywords() {
        return store_keywords;
    }

    public void setStore_keywords(String store_keywords) {
        this.store_keywords = store_keywords;
    }

    public String getStore_description() {
        return store_description;
    }

    public void setStore_description(String store_description) {
        this.store_description = store_description;
    }

    public String getStore_qq() {
        return store_qq;
    }

    public void setStore_qq(String store_qq) {
        this.store_qq = store_qq;
    }

    public String getStore_ww() {
        return store_ww;
    }

    public void setStore_ww(String store_ww) {
        this.store_ww = store_ww;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public String getStore_zy() {
        return store_zy;
    }

    public void setStore_zy(String store_zy) {
        this.store_zy = store_zy;
    }

    public String getStore_domain() {
        return store_domain;
    }

    public void setStore_domain(String store_domain) {
        this.store_domain = store_domain;
    }

    public String getStore_recommend() {
        return store_recommend;
    }

    public void setStore_recommend(String store_recommend) {
        this.store_recommend = store_recommend;
    }

    public String getStore_theme() {
        return store_theme;
    }

    public void setStore_theme(String store_theme) {
        this.store_theme = store_theme;
    }

    public String getStore_credit() {
        return store_credit;
    }

    public void setStore_credit(String store_credit) {
        this.store_credit = store_credit;
    }

    public String getStore_collect() {
        return store_collect;
    }

    public void setStore_collect(String store_collect) {
        this.store_collect = store_collect;
    }

    public String getStore_slide() {
        return store_slide;
    }

    public void setStore_slide(String store_slide) {
        this.store_slide = store_slide;
    }

    public String getStore_slide_url() {
        return store_slide_url;
    }

    public void setStore_slide_url(String store_slide_url) {
        this.store_slide_url = store_slide_url;
    }

    public String getStore_sales() {
        return store_sales;
    }

    public void setStore_sales(String store_sales) {
        this.store_sales = store_sales;
    }

    public String getStore_presales() {
        return store_presales;
    }

    public void setStore_presales(String store_presales) {
        this.store_presales = store_presales;
    }

    public String getStore_aftersales() {
        return store_aftersales;
    }

    public void setStore_aftersales(String store_aftersales) {
        this.store_aftersales = store_aftersales;
    }

    public String getStore_workingtime() {
        return store_workingtime;
    }

    public void setStore_workingtime(String store_workingtime) {
        this.store_workingtime = store_workingtime;
    }

    public String getStore_free_price() {
        return store_free_price;
    }

    public void setStore_free_price(String store_free_price) {
        this.store_free_price = store_free_price;
    }

    public String getIs_own_shop() {
        return is_own_shop;
    }

    public void setIs_own_shop(String is_own_shop) {
        this.is_own_shop = is_own_shop;
    }

    public String getMb_title_img() {
        return mb_title_img;
    }

    public void setMb_title_img(String mb_title_img) {
        this.mb_title_img = mb_title_img;
    }

    public String getMb_sliders() {
        return mb_sliders;
    }

    public void setMb_sliders(String mb_sliders) {
        this.mb_sliders = mb_sliders;
    }

    @Override
    public String toString() {
        return "StoreIntroduce{" +
                "store_id='" + store_id + '\'' +
                ", store_name='" + store_name + '\'' +
                ", grade_id='" + grade_id + '\'' +
                ", member_id='" + member_id + '\'' +
                ", member_name='" + member_name + '\'' +
                ", seller_name='" + seller_name + '\'' +
                ", sc_id='" + sc_id + '\'' +
                ", sc_name='" + sc_name + '\'' +
                ", store_company_name='" + store_company_name + '\'' +
                ", province_id='" + province_id + '\'' +
                ", area_info='" + area_info + '\'' +
                ", store_address='" + store_address + '\'' +
                ", store_zip='" + store_zip + '\'' +
                ", store_state='" + store_state + '\'' +
                ", store_time='" + store_time + '\'' +
                ", store_end_time='" + store_end_time + '\'' +
                ", store_label='" + store_label + '\'' +
                ", store_banner='" + store_banner + '\'' +
                ", store_avatar='" + store_avatar + '\'' +
                ", store_keywords='" + store_keywords + '\'' +
                ", store_description='" + store_description + '\'' +
                ", store_qq='" + store_qq + '\'' +
                ", store_ww='" + store_ww + '\'' +
                ", store_phone='" + store_phone + '\'' +
                ", store_zy='" + store_zy + '\'' +
                ", store_domain='" + store_domain + '\'' +
                ", store_recommend='" + store_recommend + '\'' +
                ", store_theme='" + store_theme + '\'' +
                ", store_credit='" + store_credit + '\'' +
                ", store_collect='" + store_collect + '\'' +
                ", store_slide='" + store_slide + '\'' +
                ", store_slide_url='" + store_slide_url + '\'' +
                ", store_sales='" + store_sales + '\'' +
                ", store_presales='" + store_presales + '\'' +
                ", store_aftersales='" + store_aftersales + '\'' +
                ", store_workingtime='" + store_workingtime + '\'' +
                ", store_free_price='" + store_free_price + '\'' +
                ", is_own_shop='" + is_own_shop + '\'' +
                ", mb_title_img='" + mb_title_img + '\'' +
                ", mb_sliders='" + mb_sliders + '\'' +
                ", store_time_text='" + store_time_text + '\'' +
                '}';
    }
}
