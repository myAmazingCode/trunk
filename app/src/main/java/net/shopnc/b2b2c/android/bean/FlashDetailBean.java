package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by yuanshuo on 2017/9/25.
 */

public class FlashDetailBean {

    /**
     * flash_info : {"end_time":"1506540001","flash_banner_url":"http://test.shopnctest.com/data/upload/shop/store/8/05489545506098705.png","flash_brand_url":"http://test.shopnctest.com/data/upload/shop/store/8/05489545506084144.png","flash_explain":"满198元,减20元","flash_id":"2","flash_name":"双立人厨具","flash_pic_url":"http://test.shopnctest.com/data/upload/shop/store/8/05489545506080120.png","flash_state":true,"flash_title":"低至4折","start_time":"1506280801","to_time":217723,"upper_limit":"1"}
     * goods_list : [{"flash_amount":1,"flash_discount":"6.5折","flash_price":"1580.00","goods_id":"100271","goods_image":"8_04626399896475745.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626399896475745_360.jpg","goods_name":"Zwilling双立人Twin Chef 刀具6件套装 厨师刀中片刀蔬果刀剪刀 不锈钢","goods_price":"2438.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100271","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626399896475745_360.jpg","price_pencent":"6.5","store_id":"8","upper_limit":"1"},{"flash_amount":1,"flash_discount":"6.0折","flash_price":"2400.00","goods_id":"100294","goods_image":"8_04626426315093178.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626426315093178_360.jpg","goods_name":"德国双立人Twin Nova III 中式炒锅9件套 厨房锅具刀具不锈钢厨具 不锈钢色","goods_price":"3984.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100294","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626426315093178_360.jpg","price_pencent":"6.0","store_id":"8","upper_limit":"1"},{"flash_amount":1,"flash_discount":"6.6折","flash_price":"4370.00","goods_id":"100301","goods_image":"8_04626452824309931.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626452824309931_360.jpg","goods_name":"德国双立人Twin Olymp系列锅具3件套装厨房锅具烧锅炒锅蒸笼 不锈钢色","goods_price":"6588.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100301","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626452824309931_360.jpg","price_pencent":"6.6","store_id":"8","upper_limit":"1"},{"flash_amount":1,"flash_discount":"2.8折","flash_price":"1225.00","goods_id":"100308","goods_image":"8_04626461755114769.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626461755114769_360.jpg","goods_name":"德国双立人Zwilling Prime 锅具四件套装 不锈钢煎炒锅深烧锅炖锅 不锈钢色","goods_price":"4392.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100308","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626461755114769_360.jpg","price_pencent":"2.8","store_id":"8","upper_limit":"1"},{"flash_amount":1,"flash_discount":"8.6折","flash_price":"3571.00","goods_id":"100310","goods_image":"8_04626472849583761.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626472849583761_360.jpg","goods_name":"德国双立人Twin Gourmet锅具3件套装 28cm中式炒锅蒸笼深烧锅厨具 不锈钢色","goods_price":"4148.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100310","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626472849583761_360.jpg","price_pencent":"8.6","store_id":"8","upper_limit":"1"}]
     */

    private FlashInfoBean flash_info;
    private List<GoodsListBean> goods_list;

    public FlashInfoBean getFlash_info() {
        return flash_info;
    }

    public void setFlash_info(FlashInfoBean flash_info) {
        this.flash_info = flash_info;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class FlashInfoBean {
        /**
         * end_time : 1506540001
         * flash_banner_url : http://test.shopnctest.com/data/upload/shop/store/8/05489545506098705.png
         * flash_brand_url : http://test.shopnctest.com/data/upload/shop/store/8/05489545506084144.png
         * flash_explain : 满198元,减20元
         * flash_id : 2
         * flash_name : 双立人厨具
         * flash_pic_url : http://test.shopnctest.com/data/upload/shop/store/8/05489545506080120.png
         * flash_state : true
         * flash_title : 低至4折
         * start_time : 1506280801
         * to_time : 217723
         * upper_limit : 1
         */

        private String end_time;
        private String flash_banner_url;
        private String flash_brand_url;
        private String flash_explain;
        private String flash_id;
        private String flash_name;
        private String flash_pic_url;
        private boolean flash_state;
        private String flash_title;
        private String start_time;
        private int to_time;
        private String upper_limit;

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getFlash_banner_url() {
            return flash_banner_url;
        }

        public void setFlash_banner_url(String flash_banner_url) {
            this.flash_banner_url = flash_banner_url;
        }

        public String getFlash_brand_url() {
            return flash_brand_url;
        }

        public void setFlash_brand_url(String flash_brand_url) {
            this.flash_brand_url = flash_brand_url;
        }

        public String getFlash_explain() {
            return flash_explain;
        }

        public void setFlash_explain(String flash_explain) {
            this.flash_explain = flash_explain;
        }

        public String getFlash_id() {
            return flash_id;
        }

        public void setFlash_id(String flash_id) {
            this.flash_id = flash_id;
        }

        public String getFlash_name() {
            return flash_name;
        }

        public void setFlash_name(String flash_name) {
            this.flash_name = flash_name;
        }

        public String getFlash_pic_url() {
            return flash_pic_url;
        }

        public void setFlash_pic_url(String flash_pic_url) {
            this.flash_pic_url = flash_pic_url;
        }

        public boolean isFlash_state() {
            return flash_state;
        }

        public void setFlash_state(boolean flash_state) {
            this.flash_state = flash_state;
        }

        public String getFlash_title() {
            return flash_title;
        }

        public void setFlash_title(String flash_title) {
            this.flash_title = flash_title;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getTo_time() {
            return to_time;
        }

        public void setTo_time(int to_time) {
            this.to_time = to_time;
        }

        public String getUpper_limit() {
            return upper_limit;
        }

        public void setUpper_limit(String upper_limit) {
            this.upper_limit = upper_limit;
        }
    }

    public static class GoodsListBean {
        /**
         * flash_amount : 1
         * flash_discount : 6.5折
         * flash_price : 1580.00
         * goods_id : 100271
         * goods_image : 8_04626399896475745.jpg
         * goods_img_url : http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626399896475745_360.jpg
         * goods_name : Zwilling双立人Twin Chef 刀具6件套装 厨师刀中片刀蔬果刀剪刀 不锈钢
         * goods_price : 2438.00
         * goods_url : http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100271
         * image_url : http://test.shopnctest.com/data/upload/shop/store/goods/8/8_04626399896475745_360.jpg
         * price_pencent : 6.5
         * store_id : 8
         * upper_limit : 1
         */

        private int flash_amount;
        private String flash_discount;
        private String flash_price;
        private String goods_id;
        private String goods_image;
        private String goods_img_url;
        private String goods_name;
        private String goods_price;
        private String goods_url;
        private String image_url;
        private String price_pencent;
        private String store_id;
        private String upper_limit;

        public int getFlash_amount() {
            return flash_amount;
        }

        public void setFlash_amount(int flash_amount) {
            this.flash_amount = flash_amount;
        }

        public String getFlash_discount() {
            return flash_discount;
        }

        public void setFlash_discount(String flash_discount) {
            this.flash_discount = flash_discount;
        }

        public String getFlash_price() {
            return flash_price;
        }

        public void setFlash_price(String flash_price) {
            this.flash_price = flash_price;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getGoods_img_url() {
            return goods_img_url;
        }

        public void setGoods_img_url(String goods_img_url) {
            this.goods_img_url = goods_img_url;
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

        public String getGoods_url() {
            return goods_url;
        }

        public void setGoods_url(String goods_url) {
            this.goods_url = goods_url;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getPrice_pencent() {
            return price_pencent;
        }

        public void setPrice_pencent(String price_pencent) {
            this.price_pencent = price_pencent;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getUpper_limit() {
            return upper_limit;
        }

        public void setUpper_limit(String upper_limit) {
            this.upper_limit = upper_limit;
        }
    }
}
