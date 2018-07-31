package net.shopnc.b2b2c.android.bean;

/**
 * Created by snm on 2016/9/2.
 */
public class DemandInfo {


    /**
     * demand_id : 4
     * store_id : 1
     * demand_sort : 0
     * add_time : 2016-09-05
     * cate_id : 1066
     * promote_video :
     * promote_text :
     * promote_image : 05263860016930877.jpg
     * recommend_goods : null
     * page_view : 23
     * promote_image_url : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/default_goods_image_240.gif
     * promote_video_url :
     * store_info : {"store_id":"1","store_name":"ShopNC_B2B2C_Distribute","grade_id":"0","member_id":"1","member_name":"buyer","seller_name":"shopnc_seller","sc_id":"0","store_company_name":null,"province_id":"0","area_info":"","store_address":"","store_zip":"","store_state":"1","store_close_info":null,"store_sort":"0","store_time":"1471310860","store_end_time":null,"store_label":"http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/default_store_logo.gif","store_banner":null,"store_avatar":"http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/default_store_avatar.png","store_keywords":"","store_description":"","store_qq":null,"store_ww":null,"store_phone":null,"store_zy":null,"store_domain":null,"store_domain_times":"0","store_recommend":"0","store_theme":"default","store_credit":{"store_desccredit":{"text":"描述","credit":"5.0"},"store_servicecredit":{"text":"服务","credit":"5.0"},"store_deliverycredit":{"text":"物流","credit":"5.0"}},"store_desccredit":"0","store_servicecredit":"0","store_deliverycredit":"0","store_collect":"1","store_slide":null,"store_slide_url":null,"store_stamp":null,"store_printdesc":null,"store_sales":"0","store_presales":null,"store_aftersales":null,"store_workingtime":null,"store_free_price":"0.00","store_decoration_switch":"0","store_decoration_only":"0","store_decoration_image_count":"0","is_own_shop":"1","bind_all_gc":"1","store_vrcode_prefix":null,"mb_title_img":null,"mb_sliders":null,"left_bar_type":"1","deliver_region":null,"goods_count":"14","store_credit_average":"5.0","store_credit_percent":100}
     * is_favorate : true
     */

    private String demand_id;
    private String store_id;
    private String demand_sort;
    private String add_time;
    private String cate_id;
    private String promote_video;
    private String promote_text;
    private String promote_image;
    private Object recommend_goods;
    private String page_view;
    private String promote_image_url;
    private String promote_video_url;
    private String demand_video_url;
    private String demand_video;
    private String movie_rand;
    private String sort;
    private String video_id;
    private String movie_state;
    private String video_identity_type;
    private String video_identity;
    private String news_name;

    /**
     * store_id : 1
     * store_name : ShopNC_B2B2C_Distribute
     * grade_id : 0
     * member_id : 1
     * member_name : buyer
     * seller_name : shopnc_seller
     * sc_id : 0
     * store_company_name : null
     * province_id : 0
     * area_info :
     * store_address :
     * store_zip :
     * store_state : 1
     * store_close_info : null
     * store_sort : 0
     * store_time : 1471310860
     * store_end_time : null
     * store_label : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/default_store_logo.gif
     * store_banner : null
     * store_avatar : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/default_store_avatar.png
     * store_keywords :
     * store_description :
     * store_qq : null
     * store_ww : null
     * store_phone : null
     * store_zy : null
     * store_domain : null
     * store_domain_times : 0
     * store_recommend : 0
     * store_theme : default
     * store_credit : {"store_desccredit":{"text":"描述","credit":"5.0"},"store_servicecredit":{"text":"服务","credit":"5.0"},"store_deliverycredit":{"text":"物流","credit":"5.0"}}
     * store_desccredit : 0
     * store_servicecredit : 0
     * store_deliverycredit : 0
     * store_collect : 1
     * store_slide : null
     * store_slide_url : null
     * store_stamp : null
     * store_printdesc : null
     * store_sales : 0
     * store_presales : null
     * store_aftersales : null
     * store_workingtime : null
     * store_free_price : 0.00
     * store_decoration_switch : 0
     * store_decoration_only : 0
     * store_decoration_image_count : 0
     * is_own_shop : 1
     * bind_all_gc : 1
     * store_vrcode_prefix : null
     * mb_title_img : null
     * mb_sliders : null
     * left_bar_type : 1
     * deliver_region : null
     * goods_count : 14
     * store_credit_average : 5.0
     * store_credit_percent : 100
     */

    private StoreInfoBean store_info;
    private boolean is_favorate;

    public String getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(String demand_id) {
        this.demand_id = demand_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getDemand_sort() {
        return demand_sort;
    }

    public void setDemand_sort(String demand_sort) {
        this.demand_sort = demand_sort;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getPromote_video() {
        return promote_video;
    }

    public void setPromote_video(String promote_video) {
        this.promote_video = promote_video;
    }

    public String getPromote_text() {
        return promote_text;
    }

    public void setPromote_text(String promote_text) {
        this.promote_text = promote_text;
    }

    public String getPromote_image() {
        return promote_image;
    }

    public void setPromote_image(String promote_image) {
        this.promote_image = promote_image;
    }

    public Object getRecommend_goods() {
        return recommend_goods;
    }

    public void setRecommend_goods(Object recommend_goods) {
        this.recommend_goods = recommend_goods;
    }

    public String getPage_view() {
        return page_view;
    }

    public void setPage_view(String page_view) {
        this.page_view = page_view;
    }

    public String getPromote_image_url() {
        return promote_image_url;
    }

    public void setPromote_image_url(String promote_image_url) {
        this.promote_image_url = promote_image_url;
    }

    public String getPromote_video_url() {
        return promote_video_url;
    }

    public void setPromote_video_url(String promote_video_url) {
        this.promote_video_url = promote_video_url;
    }

    public StoreInfoBean getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfoBean store_info) {
        this.store_info = store_info;
    }

    public boolean isIs_favorate() {
        return is_favorate;
    }

    public void setIs_favorate(boolean is_favorate) {
        this.is_favorate = is_favorate;
    }

    public String getDemand_video_url() {
        return demand_video_url;
    }

    public void setDemand_video_url(String demand_video_url) {
        this.demand_video_url = demand_video_url;
    }

    public String getDemand_video() {
        return demand_video;
    }

    public void setDemand_video(String demand_video) {
        this.demand_video = demand_video;
    }

    public String getMovie_rand() {
        return movie_rand;
    }

    public void setMovie_rand(String movie_rand) {
        this.movie_rand = movie_rand;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getMovie_state() {
        return movie_state;
    }

    public void setMovie_state(String movie_state) {
        this.movie_state = movie_state;
    }

    public String getVideo_identity_type() {
        return video_identity_type;
    }

    public void setVideo_identity_type(String video_identity_type) {
        this.video_identity_type = video_identity_type;
    }

    public String getVideo_identity() {
        return video_identity;
    }

    public void setVideo_identity(String video_identity) {
        this.video_identity = video_identity;
    }

    public String getNews_name() {
        return news_name;
    }

    public void setNews_name(String news_name) {
        this.news_name = news_name;
    }

    public boolean is_favorate() {
        return is_favorate;
    }

    public static class StoreInfoBean {
        private String store_id;
        private String store_name;
        private String grade_id;
        private String member_id;
        private String member_name;
        private String seller_name;
        private String sc_id;
        private Object store_company_name;
        private String province_id;
        private String area_info;
        private String store_address;
        private String store_zip;
        private String store_state;
        private Object store_close_info;
        private String store_sort;
        private String store_time;
        private Object store_end_time;
        private String store_label;
        private Object store_banner;
        private String store_avatar;
        private String store_keywords;
        private String store_description;
        private Object store_qq;
        private Object store_ww;
        private Object store_phone;
        private Object store_zy;
        private Object store_domain;
        private String store_domain_times;
        private String store_recommend;
        private String store_theme;
        /**
         * store_desccredit : {"text":"描述","credit":"5.0"}
         * store_servicecredit : {"text":"服务","credit":"5.0"}
         * store_deliverycredit : {"text":"物流","credit":"5.0"}
         */

        private StoreCreditBean store_credit;
        private String store_desccredit;
        private String store_servicecredit;
        private String store_deliverycredit;
        private String store_collect;
        private Object store_slide;
        private Object store_slide_url;
        private Object store_stamp;
        private Object store_printdesc;
        private String store_sales;
        private Object store_presales;
        private Object store_aftersales;
        private Object store_workingtime;
        private String store_free_price;
        private String store_decoration_switch;
        private String store_decoration_only;
        private String store_decoration_image_count;
        private String is_own_shop;
        private String bind_all_gc;
        private Object store_vrcode_prefix;
        private Object mb_title_img;
        private Object mb_sliders;
        private String left_bar_type;
        private Object deliver_region;
        private String goods_count;
        private String store_credit_average;
        private int store_credit_percent;

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

        public Object getStore_company_name() {
            return store_company_name;
        }

        public void setStore_company_name(Object store_company_name) {
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

        public Object getStore_close_info() {
            return store_close_info;
        }

        public void setStore_close_info(Object store_close_info) {
            this.store_close_info = store_close_info;
        }

        public String getStore_sort() {
            return store_sort;
        }

        public void setStore_sort(String store_sort) {
            this.store_sort = store_sort;
        }

        public String getStore_time() {
            return store_time;
        }

        public void setStore_time(String store_time) {
            this.store_time = store_time;
        }

        public Object getStore_end_time() {
            return store_end_time;
        }

        public void setStore_end_time(Object store_end_time) {
            this.store_end_time = store_end_time;
        }

        public String getStore_label() {
            return store_label;
        }

        public void setStore_label(String store_label) {
            this.store_label = store_label;
        }

        public Object getStore_banner() {
            return store_banner;
        }

        public void setStore_banner(Object store_banner) {
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

        public Object getStore_qq() {
            return store_qq;
        }

        public void setStore_qq(Object store_qq) {
            this.store_qq = store_qq;
        }

        public Object getStore_ww() {
            return store_ww;
        }

        public void setStore_ww(Object store_ww) {
            this.store_ww = store_ww;
        }

        public Object getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(Object store_phone) {
            this.store_phone = store_phone;
        }

        public Object getStore_zy() {
            return store_zy;
        }

        public void setStore_zy(Object store_zy) {
            this.store_zy = store_zy;
        }

        public Object getStore_domain() {
            return store_domain;
        }

        public void setStore_domain(Object store_domain) {
            this.store_domain = store_domain;
        }

        public String getStore_domain_times() {
            return store_domain_times;
        }

        public void setStore_domain_times(String store_domain_times) {
            this.store_domain_times = store_domain_times;
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

        public StoreCreditBean getStore_credit() {
            return store_credit;
        }

        public void setStore_credit(StoreCreditBean store_credit) {
            this.store_credit = store_credit;
        }

        public String getStore_desccredit() {
            return store_desccredit;
        }

        public void setStore_desccredit(String store_desccredit) {
            this.store_desccredit = store_desccredit;
        }

        public String getStore_servicecredit() {
            return store_servicecredit;
        }

        public void setStore_servicecredit(String store_servicecredit) {
            this.store_servicecredit = store_servicecredit;
        }

        public String getStore_deliverycredit() {
            return store_deliverycredit;
        }

        public void setStore_deliverycredit(String store_deliverycredit) {
            this.store_deliverycredit = store_deliverycredit;
        }

        public String getStore_collect() {
            return store_collect;
        }

        public void setStore_collect(String store_collect) {
            this.store_collect = store_collect;
        }

        public Object getStore_slide() {
            return store_slide;
        }

        public void setStore_slide(Object store_slide) {
            this.store_slide = store_slide;
        }

        public Object getStore_slide_url() {
            return store_slide_url;
        }

        public void setStore_slide_url(Object store_slide_url) {
            this.store_slide_url = store_slide_url;
        }

        public Object getStore_stamp() {
            return store_stamp;
        }

        public void setStore_stamp(Object store_stamp) {
            this.store_stamp = store_stamp;
        }

        public Object getStore_printdesc() {
            return store_printdesc;
        }

        public void setStore_printdesc(Object store_printdesc) {
            this.store_printdesc = store_printdesc;
        }

        public String getStore_sales() {
            return store_sales;
        }

        public void setStore_sales(String store_sales) {
            this.store_sales = store_sales;
        }

        public Object getStore_presales() {
            return store_presales;
        }

        public void setStore_presales(Object store_presales) {
            this.store_presales = store_presales;
        }

        public Object getStore_aftersales() {
            return store_aftersales;
        }

        public void setStore_aftersales(Object store_aftersales) {
            this.store_aftersales = store_aftersales;
        }

        public Object getStore_workingtime() {
            return store_workingtime;
        }

        public void setStore_workingtime(Object store_workingtime) {
            this.store_workingtime = store_workingtime;
        }

        public String getStore_free_price() {
            return store_free_price;
        }

        public void setStore_free_price(String store_free_price) {
            this.store_free_price = store_free_price;
        }

        public String getStore_decoration_switch() {
            return store_decoration_switch;
        }

        public void setStore_decoration_switch(String store_decoration_switch) {
            this.store_decoration_switch = store_decoration_switch;
        }

        public String getStore_decoration_only() {
            return store_decoration_only;
        }

        public void setStore_decoration_only(String store_decoration_only) {
            this.store_decoration_only = store_decoration_only;
        }

        public String getStore_decoration_image_count() {
            return store_decoration_image_count;
        }

        public void setStore_decoration_image_count(String store_decoration_image_count) {
            this.store_decoration_image_count = store_decoration_image_count;
        }

        public String getIs_own_shop() {
            return is_own_shop;
        }

        public void setIs_own_shop(String is_own_shop) {
            this.is_own_shop = is_own_shop;
        }

        public String getBind_all_gc() {
            return bind_all_gc;
        }

        public void setBind_all_gc(String bind_all_gc) {
            this.bind_all_gc = bind_all_gc;
        }

        public Object getStore_vrcode_prefix() {
            return store_vrcode_prefix;
        }

        public void setStore_vrcode_prefix(Object store_vrcode_prefix) {
            this.store_vrcode_prefix = store_vrcode_prefix;
        }

        public Object getMb_title_img() {
            return mb_title_img;
        }

        public void setMb_title_img(Object mb_title_img) {
            this.mb_title_img = mb_title_img;
        }

        public Object getMb_sliders() {
            return mb_sliders;
        }

        public void setMb_sliders(Object mb_sliders) {
            this.mb_sliders = mb_sliders;
        }

        public String getLeft_bar_type() {
            return left_bar_type;
        }

        public void setLeft_bar_type(String left_bar_type) {
            this.left_bar_type = left_bar_type;
        }

        public Object getDeliver_region() {
            return deliver_region;
        }

        public void setDeliver_region(Object deliver_region) {
            this.deliver_region = deliver_region;
        }

        public String getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }

        public String getStore_credit_average() {
            return store_credit_average;
        }

        public void setStore_credit_average(String store_credit_average) {
            this.store_credit_average = store_credit_average;
        }

        public int getStore_credit_percent() {
            return store_credit_percent;
        }

        public void setStore_credit_percent(int store_credit_percent) {
            this.store_credit_percent = store_credit_percent;
        }

        public static class StoreCreditBean {
            /**
             * text : 描述
             * credit : 5.0
             */

            private StoreDesccreditBean store_desccredit;
            /**
             * text : 服务
             * credit : 5.0
             */

            private StoreServicecreditBean store_servicecredit;
            /**
             * text : 物流
             * credit : 5.0
             */

            private StoreDeliverycreditBean store_deliverycredit;

            public StoreDesccreditBean getStore_desccredit() {
                return store_desccredit;
            }

            public void setStore_desccredit(StoreDesccreditBean store_desccredit) {
                this.store_desccredit = store_desccredit;
            }

            public StoreServicecreditBean getStore_servicecredit() {
                return store_servicecredit;
            }

            public void setStore_servicecredit(StoreServicecreditBean store_servicecredit) {
                this.store_servicecredit = store_servicecredit;
            }

            public StoreDeliverycreditBean getStore_deliverycredit() {
                return store_deliverycredit;
            }

            public void setStore_deliverycredit(StoreDeliverycreditBean store_deliverycredit) {
                this.store_deliverycredit = store_deliverycredit;
            }

            public static class StoreDesccreditBean {
                private String text;
                private String credit;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getCredit() {
                    return credit;
                }

                public void setCredit(String credit) {
                    this.credit = credit;
                }
            }

            public static class StoreServicecreditBean {
                private String text;
                private String credit;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getCredit() {
                    return credit;
                }

                public void setCredit(String credit) {
                    this.credit = credit;
                }
            }

            public static class StoreDeliverycreditBean {
                private String text;
                private String credit;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getCredit() {
                    return credit;
                }

                public void setCredit(String credit) {
                    this.credit = credit;
                }
            }
        }
    }
}
