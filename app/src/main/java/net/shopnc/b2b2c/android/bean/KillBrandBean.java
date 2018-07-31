package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by yuanshuo on 2017/9/22.
 */

public class KillBrandBean {

    /**
     * goods_list : [{"goods_id":"100125","goods_image":"7_04625603984352970.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625603984352970_360.jpg","goods_name":"雅诗兰黛睫毛膏 不晕染 凝彩纤长睫毛膏6ml 纤长 防水","goods_price":"330.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100125","had_spiked_count":"0","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625603984352970_360.jpg","spike_amount":"8888","spike_discount":"8.5折","spike_percent":0,"spike_price":"280.00","store_id":"7","upper_limit":"0"},{"goods_id":"100124","goods_image":"7_04625594719936406.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625594719936406_360.jpg","goods_name":"雅诗兰黛粉底液 水凝润颜粉底液SPF15/PA++ 30ml保湿控油遮瑕防晒 64","goods_price":"450.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100124","had_spiked_count":"0","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625594719936406_360.jpg","spike_amount":"8888","spike_discount":"8.4折","spike_percent":0,"spike_price":"380.00","store_id":"7","upper_limit":"0"},{"goods_id":"100114","goods_image":"7_04625581176730028.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625581176730028_360.jpg","goods_name":"雅诗兰黛唇膏正品 花漾唇膏炫慕系列 多色口红 丰唇滋润保湿 紫红","goods_price":"300.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100114","had_spiked_count":"0","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625581176730028_360.jpg","spike_amount":"8888","spike_discount":"6.6折","spike_percent":0,"spike_price":"198.00","store_id":"7","upper_limit":"0"},{"goods_id":"100079","goods_image":"7_04625577847348092.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625577847348092_360.jpg","goods_name":"雅诗兰黛红石榴CC霜 鲜养焕亮修颜霜30mlSPF20/PA+ 防晒","goods_price":"460.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100079","had_spiked_count":"0","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625577847348092_360.jpg","spike_amount":"8888","spike_discount":"7.0折","spike_percent":0,"spike_price":"320.00","store_id":"7","upper_limit":"0"},{"goods_id":"100078","goods_image":"7_04625574844405667.jpg","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625574844405667_360.jpg","goods_name":"雅诗兰黛红石榴面霜 鲜养焕亮凝霜50ml 补水亮肤 抗氧化","goods_price":"590.00","goods_url":"http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100078","had_spiked_count":"0","image_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625574844405667_360.jpg","spike_amount":"8888","spike_discount":"6.8折","spike_percent":0,"spike_price":"400.00","store_id":"7","upper_limit":"0"}]
     * spike_info : {"end_time":"1525104000","spike_banner":"http://test.shopnctest.com/data/upload/shop/store/7/05489415937113808.png","spike_explain":"","spike_id":"8","spike_name":"夏季护肤","spike_state":true,"spike_title":"夏季护肤","start_time":"1495597560","to_time":19062817,"upper_limit":"0"}
     */

    private SpikeInfoBean spike_info;
    private List<GoodsListBean> goods_list;

    public SpikeInfoBean getSpike_info() {
        return spike_info;
    }

    public void setSpike_info(SpikeInfoBean spike_info) {
        this.spike_info = spike_info;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class SpikeInfoBean {
        /**
         * end_time : 1525104000
         * spike_banner : http://test.shopnctest.com/data/upload/shop/store/7/05489415937113808.png
         * spike_explain :
         * spike_id : 8
         * spike_name : 夏季护肤
         * spike_state : true
         * spike_title : 夏季护肤
         * start_time : 1495597560
         * to_time : 19062817
         * upper_limit : 0
         */

        private String end_time;
        private String spike_banner;
        private String spike_explain;
        private String spike_id;
        private String spike_name;
        private boolean spike_state;
        private String spike_title;
        private String start_time;
        private int to_time;
        private String upper_limit;

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getSpike_banner() {
            return spike_banner;
        }

        public void setSpike_banner(String spike_banner) {
            this.spike_banner = spike_banner;
        }

        public String getSpike_explain() {
            return spike_explain;
        }

        public void setSpike_explain(String spike_explain) {
            this.spike_explain = spike_explain;
        }

        public String getSpike_id() {
            return spike_id;
        }

        public void setSpike_id(String spike_id) {
            this.spike_id = spike_id;
        }

        public String getSpike_name() {
            return spike_name;
        }

        public void setSpike_name(String spike_name) {
            this.spike_name = spike_name;
        }

        public boolean isSpike_state() {
            return spike_state;
        }

        public void setSpike_state(boolean spike_state) {
            this.spike_state = spike_state;
        }

        public String getSpike_title() {
            return spike_title;
        }

        public void setSpike_title(String spike_title) {
            this.spike_title = spike_title;
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
         * goods_id : 100125
         * goods_image : 7_04625603984352970.jpg
         * goods_img_url : http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625603984352970_360.jpg
         * goods_name : 雅诗兰黛睫毛膏 不晕染 凝彩纤长睫毛膏6ml 纤长 防水
         * goods_price : 330.00
         * goods_url : http://test.shopnctest.com/shop/index.php?act=goods&op=index&goods_id=100125
         * had_spiked_count : 0
         * image_url : http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625603984352970_360.jpg
         * spike_amount : 8888
         * spike_discount : 8.5折
         * spike_percent : 0
         * spike_price : 280.00
         * store_id : 7
         * upper_limit : 0
         */

        private String goods_id;
        private String goods_image;
        private String goods_img_url;
        private String goods_name;
        private String goods_price;
        private String goods_url;
        private String had_spiked_count;
        private String image_url;
        private String spike_amount;
        private String spike_discount;
        private int spike_percent;
        private String spike_price;
        private String store_id;
        private String upper_limit;

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

        public String getHad_spiked_count() {
            return had_spiked_count;
        }

        public void setHad_spiked_count(String had_spiked_count) {
            this.had_spiked_count = had_spiked_count;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getSpike_amount() {
            return spike_amount;
        }

        public void setSpike_amount(String spike_amount) {
            this.spike_amount = spike_amount;
        }

        public String getSpike_discount() {
            return spike_discount;
        }

        public void setSpike_discount(String spike_discount) {
            this.spike_discount = spike_discount;
        }

        public int getSpike_percent() {
            return spike_percent;
        }

        public void setSpike_percent(int spike_percent) {
            this.spike_percent = spike_percent;
        }

        public String getSpike_price() {
            return spike_price;
        }

        public void setSpike_price(String spike_price) {
            this.spike_price = spike_price;
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
