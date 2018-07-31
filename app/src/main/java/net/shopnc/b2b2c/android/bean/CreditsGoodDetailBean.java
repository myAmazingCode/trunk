package net.shopnc.b2b2c.android.bean;

/**
 * Created by yuanshuo on 2017/10/18.
 * 积分商品详情接口
 */

public class CreditsGoodDetailBean {

    /**
     * member_info : {"avatar":"http://test.shopnctest.com/data/upload/shop/avatar/avatar_.jpg","level":0,"level_name":"V0","member_exppoints":0}
     * pgoods_info : {"ex_state":"going","pgoods_add_time":"2014-09-02","pgoods_body":"","pgoods_close_reason":"","pgoods_commend":"1","pgoods_description":"","pgoods_endtime":"0","pgoods_id":"9","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04629867864024255_mid.jpg","pgoods_image_max":"http://test.shopnctest.com/data/upload/shop/pointprod/04629867864024255.jpg","pgoods_image_old":"04629867864024255.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04629867864024255_small.jpg","pgoods_islimit":"0","pgoods_islimittime":"0","pgoods_keywords":"","pgoods_limitgradename":"V1","pgoods_limitmgrade":"1","pgoods_limitnum":"0","pgoods_name":"联想ThinkPad无线激光鼠标 经典小黑无线版 游戏办公家用","pgoods_points":"4800","pgoods_price":"49.00","pgoods_salenum":"0","pgoods_serial":"TMJ21254525","pgoods_show":"1","pgoods_sort":"0","pgoods_starttime":"0","pgoods_state":"0","pgoods_storage":"10000","pgoods_tag":"","pgoods_view":"13"}
     * pointcart_count : 0
     * pointordercount : 0
     * redpacketcount : 0
     * vouchercount : 0
     */

    private MemberInfoBean member_info;
    private PgoodsInfoBean pgoods_info;
    private int pointcart_count;
    private int pointordercount;
    private int redpacketcount;
    private int vouchercount;

    public MemberInfoBean getMember_info() {
        return member_info;
    }

    public void setMember_info(MemberInfoBean member_info) {
        this.member_info = member_info;
    }

    public PgoodsInfoBean getPgoods_info() {
        return pgoods_info;
    }

    public void setPgoods_info(PgoodsInfoBean pgoods_info) {
        this.pgoods_info = pgoods_info;
    }

    public int getPointcart_count() {
        return pointcart_count;
    }

    public void setPointcart_count(int pointcart_count) {
        this.pointcart_count = pointcart_count;
    }

    public int getPointordercount() {
        return pointordercount;
    }

    public void setPointordercount(int pointordercount) {
        this.pointordercount = pointordercount;
    }

    public int getRedpacketcount() {
        return redpacketcount;
    }

    public void setRedpacketcount(int redpacketcount) {
        this.redpacketcount = redpacketcount;
    }

    public int getVouchercount() {
        return vouchercount;
    }

    public void setVouchercount(int vouchercount) {
        this.vouchercount = vouchercount;
    }

    public static class MemberInfoBean {
        /**
         * avatar : http://test.shopnctest.com/data/upload/shop/avatar/avatar_.jpg
         * level : 0
         * level_name : V0
         * member_exppoints : 0
         */

        private String avatar;
        private int level;
        private String level_name;
        private int member_exppoints;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public int getMember_exppoints() {
            return member_exppoints;
        }

        public void setMember_exppoints(int member_exppoints) {
            this.member_exppoints = member_exppoints;
        }
    }

    public static class PgoodsInfoBean {
        /**
         * ex_state : going
         * pgoods_add_time : 2014-09-02
         * pgoods_body :
         * pgoods_close_reason :
         * pgoods_commend : 1
         * pgoods_description :
         * pgoods_endtime : 0
         * pgoods_id : 9
         * pgoods_image : http://test.shopnctest.com/data/upload/shop/pointprod/04629867864024255_mid.jpg
         * pgoods_image_max : http://test.shopnctest.com/data/upload/shop/pointprod/04629867864024255.jpg
         * pgoods_image_old : 04629867864024255.jpg
         * pgoods_image_small : http://test.shopnctest.com/data/upload/shop/pointprod/04629867864024255_small.jpg
         * pgoods_islimit : 0
         * pgoods_islimittime : 0
         * pgoods_keywords :
         * pgoods_limitgradename : V1
         * pgoods_limitmgrade : 1
         * pgoods_limitnum : 0
         * pgoods_name : 联想ThinkPad无线激光鼠标 经典小黑无线版 游戏办公家用
         * pgoods_points : 4800
         * pgoods_price : 49.00
         * pgoods_salenum : 0
         * pgoods_serial : TMJ21254525
         * pgoods_show : 1
         * pgoods_sort : 0
         * pgoods_starttime : 0
         * pgoods_state : 0
         * pgoods_storage : 10000
         * pgoods_tag :
         * pgoods_view : 13
         */

        private String ex_state;
        private String pgoods_add_time;
        private String pgoods_body;
        private String pgoods_close_reason;
        private String pgoods_commend;
        private String pgoods_description;
        private String pgoods_endtime;
        private String pgoods_id;
        private String pgoods_image;
        private String pgoods_image_max;
        private String pgoods_image_old;
        private String pgoods_image_small;
        private String pgoods_islimit;
        private String pgoods_islimittime;
        private String pgoods_keywords;
        private String pgoods_limitgradename;
        private String pgoods_limitmgrade;
        private String pgoods_limitnum;
        private String pgoods_name;
        private String pgoods_points;
        private String pgoods_price;
        private String pgoods_salenum;
        private String pgoods_serial;
        private String pgoods_show;
        private String pgoods_sort;
        private String pgoods_starttime;
        private String pgoods_state;
        private String pgoods_storage;
        private String pgoods_tag;
        private String pgoods_view;

        public String getEx_state() {
            return ex_state;
        }

        public void setEx_state(String ex_state) {
            this.ex_state = ex_state;
        }

        public String getPgoods_add_time() {
            return pgoods_add_time;
        }

        public void setPgoods_add_time(String pgoods_add_time) {
            this.pgoods_add_time = pgoods_add_time;
        }

        public String getPgoods_body() {
            return pgoods_body;
        }

        public void setPgoods_body(String pgoods_body) {
            this.pgoods_body = pgoods_body;
        }

        public String getPgoods_close_reason() {
            return pgoods_close_reason;
        }

        public void setPgoods_close_reason(String pgoods_close_reason) {
            this.pgoods_close_reason = pgoods_close_reason;
        }

        public String getPgoods_commend() {
            return pgoods_commend;
        }

        public void setPgoods_commend(String pgoods_commend) {
            this.pgoods_commend = pgoods_commend;
        }

        public String getPgoods_description() {
            return pgoods_description;
        }

        public void setPgoods_description(String pgoods_description) {
            this.pgoods_description = pgoods_description;
        }

        public String getPgoods_endtime() {
            return pgoods_endtime;
        }

        public void setPgoods_endtime(String pgoods_endtime) {
            this.pgoods_endtime = pgoods_endtime;
        }

        public String getPgoods_id() {
            return pgoods_id;
        }

        public void setPgoods_id(String pgoods_id) {
            this.pgoods_id = pgoods_id;
        }

        public String getPgoods_image() {
            return pgoods_image;
        }

        public void setPgoods_image(String pgoods_image) {
            this.pgoods_image = pgoods_image;
        }

        public String getPgoods_image_max() {
            return pgoods_image_max;
        }

        public void setPgoods_image_max(String pgoods_image_max) {
            this.pgoods_image_max = pgoods_image_max;
        }

        public String getPgoods_image_old() {
            return pgoods_image_old;
        }

        public void setPgoods_image_old(String pgoods_image_old) {
            this.pgoods_image_old = pgoods_image_old;
        }

        public String getPgoods_image_small() {
            return pgoods_image_small;
        }

        public void setPgoods_image_small(String pgoods_image_small) {
            this.pgoods_image_small = pgoods_image_small;
        }

        public String getPgoods_islimit() {
            return pgoods_islimit;
        }

        public void setPgoods_islimit(String pgoods_islimit) {
            this.pgoods_islimit = pgoods_islimit;
        }

        public String getPgoods_islimittime() {
            return pgoods_islimittime;
        }

        public void setPgoods_islimittime(String pgoods_islimittime) {
            this.pgoods_islimittime = pgoods_islimittime;
        }

        public String getPgoods_keywords() {
            return pgoods_keywords;
        }

        public void setPgoods_keywords(String pgoods_keywords) {
            this.pgoods_keywords = pgoods_keywords;
        }

        public String getPgoods_limitgradename() {
            return pgoods_limitgradename;
        }

        public void setPgoods_limitgradename(String pgoods_limitgradename) {
            this.pgoods_limitgradename = pgoods_limitgradename;
        }

        public String getPgoods_limitmgrade() {
            return pgoods_limitmgrade;
        }

        public void setPgoods_limitmgrade(String pgoods_limitmgrade) {
            this.pgoods_limitmgrade = pgoods_limitmgrade;
        }

        public String getPgoods_limitnum() {
            return pgoods_limitnum;
        }

        public void setPgoods_limitnum(String pgoods_limitnum) {
            this.pgoods_limitnum = pgoods_limitnum;
        }

        public String getPgoods_name() {
            return pgoods_name;
        }

        public void setPgoods_name(String pgoods_name) {
            this.pgoods_name = pgoods_name;
        }

        public String getPgoods_points() {
            return pgoods_points;
        }

        public void setPgoods_points(String pgoods_points) {
            this.pgoods_points = pgoods_points;
        }

        public String getPgoods_price() {
            return pgoods_price;
        }

        public void setPgoods_price(String pgoods_price) {
            this.pgoods_price = pgoods_price;
        }

        public String getPgoods_salenum() {
            return pgoods_salenum;
        }

        public void setPgoods_salenum(String pgoods_salenum) {
            this.pgoods_salenum = pgoods_salenum;
        }

        public String getPgoods_serial() {
            return pgoods_serial;
        }

        public void setPgoods_serial(String pgoods_serial) {
            this.pgoods_serial = pgoods_serial;
        }

        public String getPgoods_show() {
            return pgoods_show;
        }

        public void setPgoods_show(String pgoods_show) {
            this.pgoods_show = pgoods_show;
        }

        public String getPgoods_sort() {
            return pgoods_sort;
        }

        public void setPgoods_sort(String pgoods_sort) {
            this.pgoods_sort = pgoods_sort;
        }

        public String getPgoods_starttime() {
            return pgoods_starttime;
        }

        public void setPgoods_starttime(String pgoods_starttime) {
            this.pgoods_starttime = pgoods_starttime;
        }

        public String getPgoods_state() {
            return pgoods_state;
        }

        public void setPgoods_state(String pgoods_state) {
            this.pgoods_state = pgoods_state;
        }

        public String getPgoods_storage() {
            return pgoods_storage;
        }

        public void setPgoods_storage(String pgoods_storage) {
            this.pgoods_storage = pgoods_storage;
        }

        public String getPgoods_tag() {
            return pgoods_tag;
        }

        public void setPgoods_tag(String pgoods_tag) {
            this.pgoods_tag = pgoods_tag;
        }

        public String getPgoods_view() {
            return pgoods_view;
        }

        public void setPgoods_view(String pgoods_view) {
            this.pgoods_view = pgoods_view;
        }
    }
}
