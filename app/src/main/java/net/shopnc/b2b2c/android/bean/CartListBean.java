package net.shopnc.b2b2c.android.bean;


import java.util.List;

public class CartListBean {

    /**
     * store_id : 1
     * store_name : 平台自营
     * goods : [{"cart_id":"234","buyer_id":"259","store_id":"1","store_name":"平台自营","goods_id":"100453","goods_name":"GIVENCHY纪梵希黑色牛皮材质纯色男士腰带","goods_price":"3380.00","goods_num":"1","goods_image":"1_04757800515473198.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100232","gc_id":"187","transport_id":"0","goods_freight":"0.00","goods_trans_v":"0.00","goods_inv":"1","goods_vat":"0","goods_voucher":"1","goods_storage":"5","goods_storage_alarm":"0","is_fcode":"0","have_gift":"0","groupbuy_info":[],"xianshi_info":[],"promotion_info":[],"is_chain":"1","is_dis":"0","is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","sole_info":[],"contractlist":[],"goods_image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/1/1_04757800515473198_240.jpg","goods_total":"3380.00"}]
     */

    private String store_id;
    private String store_name;
    private List<GoodsBean> goods;

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

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * cart_id : 234
         * buyer_id : 259
         * store_id : 1
         * store_name : 平台自营
         * goods_id : 100453
         * goods_name : GIVENCHY纪梵希黑色牛皮材质纯色男士腰带
         * goods_price : 3380.00
         * goods_num : 1
         * goods_image : 1_04757800515473198.jpg
         * bl_id : 0
         * state : true
         * storage_state : true
         * goods_commonid : 100232
         * gc_id : 187
         * transport_id : 0
         * goods_freight : 0.00
         * goods_trans_v : 0.00
         * goods_inv : 1
         * goods_vat : 0
         * goods_voucher : 1
         * goods_storage : 5
         * goods_storage_alarm : 0
         * is_fcode : 0
         * have_gift : 0
         * groupbuy_info : []
         * xianshi_info : []
         * promotion_info : []
         * is_chain : 1
         * is_dis : 0
         * is_book : 0
         * book_down_payment : 0.00
         * book_final_payment : 0.00
         * book_down_time : 0
         * sole_info : []
         * contractlist : []
         * goods_image_url : http://test.shopnctest.com/data/upload/shop/store/goods/1/1_04757800515473198_240.jpg
         * goods_total : 3380.00
         */

        private String cart_id;
        private String buyer_id;
        private String store_id;
        private String store_name;
        private String goods_id;
        private String goods_name;
        private String goods_price;
        private String goods_num;
        private String goods_image;
        private String bl_id;
        private boolean state;
        private boolean storage_state;
        private String goods_commonid;
        private String gc_id;
        private String transport_id;
        private String goods_freight;
        private String goods_trans_v;
        private String goods_inv;
        private String goods_vat;
        private String goods_voucher;
        private String goods_storage;
        private String goods_storage_alarm;
        private String is_fcode;
        private String have_gift;
        private String is_chain;
        private String is_dis;
        private String is_book;
        private String book_down_payment;
        private String book_final_payment;
        private String book_down_time;
        private String goods_image_url;
        private String goods_total;
        private List<String> groupbuy_info;
        private List<String> xianshi_info;
        private List<String> promotion_info;
        private List<String> sole_info;
        private List<String> contractlist;
        private String goods_spec;

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
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

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getBl_id() {
            return bl_id;
        }

        public void setBl_id(String bl_id) {
            this.bl_id = bl_id;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public boolean isStorage_state() {
            return storage_state;
        }

        public void setStorage_state(boolean storage_state) {
            this.storage_state = storage_state;
        }

        public String getGoods_commonid() {
            return goods_commonid;
        }

        public void setGoods_commonid(String goods_commonid) {
            this.goods_commonid = goods_commonid;
        }

        public String getGc_id() {
            return gc_id;
        }

        public void setGc_id(String gc_id) {
            this.gc_id = gc_id;
        }

        public String getTransport_id() {
            return transport_id;
        }

        public void setTransport_id(String transport_id) {
            this.transport_id = transport_id;
        }

        public String getGoods_freight() {
            return goods_freight;
        }

        public void setGoods_freight(String goods_freight) {
            this.goods_freight = goods_freight;
        }

        public String getGoods_trans_v() {
            return goods_trans_v;
        }

        public void setGoods_trans_v(String goods_trans_v) {
            this.goods_trans_v = goods_trans_v;
        }

        public String getGoods_inv() {
            return goods_inv;
        }

        public void setGoods_inv(String goods_inv) {
            this.goods_inv = goods_inv;
        }

        public String getGoods_vat() {
            return goods_vat;
        }

        public void setGoods_vat(String goods_vat) {
            this.goods_vat = goods_vat;
        }

        public String getGoods_voucher() {
            return goods_voucher;
        }

        public void setGoods_voucher(String goods_voucher) {
            this.goods_voucher = goods_voucher;
        }

        public String getGoods_storage() {
            return goods_storage;
        }

        public void setGoods_storage(String goods_storage) {
            this.goods_storage = goods_storage;
        }

        public String getGoods_storage_alarm() {
            return goods_storage_alarm;
        }

        public void setGoods_storage_alarm(String goods_storage_alarm) {
            this.goods_storage_alarm = goods_storage_alarm;
        }

        public String getIs_fcode() {
            return is_fcode;
        }

        public void setIs_fcode(String is_fcode) {
            this.is_fcode = is_fcode;
        }

        public String getHave_gift() {
            return have_gift;
        }

        public void setHave_gift(String have_gift) {
            this.have_gift = have_gift;
        }

        public String getIs_chain() {
            return is_chain;
        }

        public void setIs_chain(String is_chain) {
            this.is_chain = is_chain;
        }

        public String getIs_dis() {
            return is_dis;
        }

        public void setIs_dis(String is_dis) {
            this.is_dis = is_dis;
        }

        public String getIs_book() {
            return is_book;
        }

        public void setIs_book(String is_book) {
            this.is_book = is_book;
        }

        public String getBook_down_payment() {
            return book_down_payment;
        }

        public void setBook_down_payment(String book_down_payment) {
            this.book_down_payment = book_down_payment;
        }

        public String getBook_final_payment() {
            return book_final_payment;
        }

        public void setBook_final_payment(String book_final_payment) {
            this.book_final_payment = book_final_payment;
        }

        public String getBook_down_time() {
            return book_down_time;
        }

        public void setBook_down_time(String book_down_time) {
            this.book_down_time = book_down_time;
        }

        public String getGoods_image_url() {
            return goods_image_url;
        }

        public void setGoods_image_url(String goods_image_url) {
            this.goods_image_url = goods_image_url;
        }

        public String getGoods_total() {
            return goods_total;
        }

        public void setGoods_total(String goods_total) {
            this.goods_total = goods_total;
        }

        public List<String> getGroupbuy_info() {
            return groupbuy_info;
        }

        public void setGroupbuy_info(List<String> groupbuy_info) {
            this.groupbuy_info = groupbuy_info;
        }

        public List<String> getXianshi_info() {
            return xianshi_info;
        }

        public void setXianshi_info(List<String> xianshi_info) {
            this.xianshi_info = xianshi_info;
        }

        public List<String> getPromotion_info() {
            return promotion_info;
        }

        public void setPromotion_info(List<String> promotion_info) {
            this.promotion_info = promotion_info;
        }

        public List<String> getSole_info() {
            return sole_info;
        }

        public void setSole_info(List<String> sole_info) {
            this.sole_info = sole_info;
        }

        public List<String> getContractlist() {
            return contractlist;
        }

        public void setContractlist(List<String> contractlist) {
            this.contractlist = contractlist;
        }

        public String getGoods_spec() {
            return goods_spec;
        }

        public void setGoods_spec(String goods_spec) {
            this.goods_spec = goods_spec;
        }
    }
}
