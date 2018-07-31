package net.shopnc.b2b2c.android.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuanshuo on 2017/9/21.
 */

public class KillSecondListBean {

    /**
     * code : 200
     * hasmore : true
     * page_total : 2
     * datas : {"spike_list":[{"spike_id":"8","spike_name":"夏季护肤","spike_title":"夏季护肤","start_time":"1495597560","end_time":"1525104000","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/7/05489415937378760.png","spike_state_text":"正常","editable":true},{"spike_id":"7","spike_name":"家居节","spike_title":"家居节","start_time":"1495593960","end_time":"1525142760","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/27/05489379875842127.png","spike_state_text":"正常","editable":true},{"spike_id":"6","spike_name":"调用商品","spike_title":"","start_time":"1495592880","end_time":"1527091200","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/1/05489369552719365.png","spike_state_text":"正常","editable":true},{"spike_id":"5","spike_name":"电视","spike_title":"视听专场","start_time":"1495592100","end_time":"1527696000","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/1/05489361383519922.png","spike_state_text":"正常","editable":true},{"spike_id":"4","spike_name":"厨房用品","spike_title":"低至1折起","start_time":"1495590720","end_time":"1527696000","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/1/05489347869793749.png","spike_state_text":"正常","editable":true}],"recommend_goods":{"8":[{"spike_id":"8","goods_id":"100114","store_id":"7","goods_name":"雅诗兰黛唇膏正品 花漾唇膏炫慕系列 多色口红 丰唇滋润保湿 紫红","goods_price":"300.00","spike_price":"198.00","goods_image":"7_04625581176730028.jpg","upper_limit":"0","spike_amount":"8888","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625581176730028_360.jpg","spike_percent":0},{"spike_id":"8","goods_id":"100124","store_id":"7","goods_name":"雅诗兰黛粉底液 水凝润颜粉底液SPF15/PA++ 30ml保湿控油遮瑕防晒 64","goods_price":"450.00","spike_price":"380.00","goods_image":"7_04625594719936406.jpg","upper_limit":"0","spike_amount":"8888","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625594719936406_360.jpg","spike_percent":0},{"spike_id":"8","goods_id":"100125","store_id":"7","goods_name":"雅诗兰黛睫毛膏 不晕染 凝彩纤长睫毛膏6ml 纤长 防水","goods_price":"330.00","spike_price":"280.00","goods_image":"7_04625603984352970.jpg","upper_limit":"0","spike_amount":"8888","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625603984352970_360.jpg","spike_percent":0}],"7":[{"spike_id":"7","goods_id":"100664","store_id":"27","goods_name":"Harbor House Hemingway Pharmacy 台灯 铁艺 灯杆可调节","goods_price":"1680.00","spike_price":"577.00","goods_image":"27_05279934694296462.jpg","upper_limit":"0","spike_amount":"10","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/27/27_05279934694296462_360.jpg","spike_percent":0},{"spike_id":"7","goods_id":"100658","store_id":"27","goods_name":"Harbor House Dupont 台灯 美式家居 简约 铁艺台灯","goods_price":"1380.00","spike_price":"786.00","goods_image":"27_05279918320596660.jpg","upper_limit":"0","spike_amount":"80","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/27/27_05279918320596660_360.jpg","spike_percent":0},{"spike_id":"7","goods_id":"100670","store_id":"27","goods_name":"Harbor House Kimmel 铁制六头吊灯 餐厅客厅灯","goods_price":"4980.00","spike_price":"3857.00","goods_image":"27_05280407774858712.jpg","upper_limit":"0","spike_amount":"100","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/27/27_05280407774858712_360.jpg","spike_percent":0}],"5":[{"spike_id":"5","goods_id":"100011","store_id":"1","goods_name":"TCL LE50D8900 50吋镭射安卓智能液晶电视 高清LED 电视 珠光黑 官方标配","goods_price":"3149.00","spike_price":"1547.00","goods_image":"1_04624741433161976.png","upper_limit":"0","spike_amount":"500","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/1/1_04624741433161976_360.png","spike_percent":0}]}}
     */

    private int code;
    private boolean hasmore;
    private int page_total;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * spike_list : [{"spike_id":"8","spike_name":"夏季护肤","spike_title":"夏季护肤","start_time":"1495597560","end_time":"1525104000","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/7/05489415937378760.png","spike_state_text":"正常","editable":true},{"spike_id":"7","spike_name":"家居节","spike_title":"家居节","start_time":"1495593960","end_time":"1525142760","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/27/05489379875842127.png","spike_state_text":"正常","editable":true},{"spike_id":"6","spike_name":"调用商品","spike_title":"","start_time":"1495592880","end_time":"1527091200","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/1/05489369552719365.png","spike_state_text":"正常","editable":true},{"spike_id":"5","spike_name":"电视","spike_title":"视听专场","start_time":"1495592100","end_time":"1527696000","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/1/05489361383519922.png","spike_state_text":"正常","editable":true},{"spike_id":"4","spike_name":"厨房用品","spike_title":"低至1折起","start_time":"1495590720","end_time":"1527696000","spike_state":"1","spike_common_bg":"http://test.shopnctest.com/data/upload/shop/store/1/05489347869793749.png","spike_state_text":"正常","editable":true}]
         * recommend_goods : {"8":[{"spike_id":"8","goods_id":"100114","store_id":"7","goods_name":"雅诗兰黛唇膏正品 花漾唇膏炫慕系列 多色口红 丰唇滋润保湿 紫红","goods_price":"300.00","spike_price":"198.00","goods_image":"7_04625581176730028.jpg","upper_limit":"0","spike_amount":"8888","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625581176730028_360.jpg","spike_percent":0},{"spike_id":"8","goods_id":"100124","store_id":"7","goods_name":"雅诗兰黛粉底液 水凝润颜粉底液SPF15/PA++ 30ml保湿控油遮瑕防晒 64","goods_price":"450.00","spike_price":"380.00","goods_image":"7_04625594719936406.jpg","upper_limit":"0","spike_amount":"8888","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625594719936406_360.jpg","spike_percent":0},{"spike_id":"8","goods_id":"100125","store_id":"7","goods_name":"雅诗兰黛睫毛膏 不晕染 凝彩纤长睫毛膏6ml 纤长 防水","goods_price":"330.00","spike_price":"280.00","goods_image":"7_04625603984352970.jpg","upper_limit":"0","spike_amount":"8888","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625603984352970_360.jpg","spike_percent":0}],"7":[{"spike_id":"7","goods_id":"100664","store_id":"27","goods_name":"Harbor House Hemingway Pharmacy 台灯 铁艺 灯杆可调节","goods_price":"1680.00","spike_price":"577.00","goods_image":"27_05279934694296462.jpg","upper_limit":"0","spike_amount":"10","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/27/27_05279934694296462_360.jpg","spike_percent":0},{"spike_id":"7","goods_id":"100658","store_id":"27","goods_name":"Harbor House Dupont 台灯 美式家居 简约 铁艺台灯","goods_price":"1380.00","spike_price":"786.00","goods_image":"27_05279918320596660.jpg","upper_limit":"0","spike_amount":"80","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/27/27_05279918320596660_360.jpg","spike_percent":0},{"spike_id":"7","goods_id":"100670","store_id":"27","goods_name":"Harbor House Kimmel 铁制六头吊灯 餐厅客厅灯","goods_price":"4980.00","spike_price":"3857.00","goods_image":"27_05280407774858712.jpg","upper_limit":"0","spike_amount":"100","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/27/27_05280407774858712_360.jpg","spike_percent":0}],"5":[{"spike_id":"5","goods_id":"100011","store_id":"1","goods_name":"TCL LE50D8900 50吋镭射安卓智能液晶电视 高清LED 电视 珠光黑 官方标配","goods_price":"3149.00","spike_price":"1547.00","goods_image":"1_04624741433161976.png","upper_limit":"0","spike_amount":"500","had_spiked_count":"0","goods_img_url":"http://test.shopnctest.com/data/upload/shop/store/goods/1/1_04624741433161976_360.png","spike_percent":0}]}
         */

        private RecommendGoodsBean recommend_goods;
        private List<SpikeListBean> spike_list;

        public RecommendGoodsBean getRecommend_goods() {
            return recommend_goods;
        }

        public void setRecommend_goods(RecommendGoodsBean recommend_goods) {
            this.recommend_goods = recommend_goods;
        }

        public List<SpikeListBean> getSpike_list() {
            return spike_list;
        }

        public void setSpike_list(List<SpikeListBean> spike_list) {
            this.spike_list = spike_list;
        }

        public static class RecommendGoodsBean {
            @SerializedName("8")
            private List<_$8Bean> _$8;
            @SerializedName("7")
            private List<_$7Bean> _$7;
            @SerializedName("5")
            private List<_$5Bean> _$5;

            public List<_$8Bean> get_$8() {
                return _$8;
            }

            public void set_$8(List<_$8Bean> _$8) {
                this._$8 = _$8;
            }

            public List<_$7Bean> get_$7() {
                return _$7;
            }

            public void set_$7(List<_$7Bean> _$7) {
                this._$7 = _$7;
            }

            public List<_$5Bean> get_$5() {
                return _$5;
            }

            public void set_$5(List<_$5Bean> _$5) {
                this._$5 = _$5;
            }

            public static class _$8Bean {
                /**
                 * spike_id : 8
                 * goods_id : 100114
                 * store_id : 7
                 * goods_name : 雅诗兰黛唇膏正品 花漾唇膏炫慕系列 多色口红 丰唇滋润保湿 紫红
                 * goods_price : 300.00
                 * spike_price : 198.00
                 * goods_image : 7_04625581176730028.jpg
                 * upper_limit : 0
                 * spike_amount : 8888
                 * had_spiked_count : 0
                 * goods_img_url : http://test.shopnctest.com/data/upload/shop/store/goods/7/7_04625581176730028_360.jpg
                 * spike_percent : 0
                 */

                private String spike_id;
                private String goods_id;
                private String store_id;
                private String goods_name;
                private String goods_price;
                private String spike_price;
                private String goods_image;
                private String upper_limit;
                private String spike_amount;
                private String had_spiked_count;
                private String goods_img_url;
                private int spike_percent;

                public String getSpike_id() {
                    return spike_id;
                }

                public void setSpike_id(String spike_id) {
                    this.spike_id = spike_id;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
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

                public String getSpike_price() {
                    return spike_price;
                }

                public void setSpike_price(String spike_price) {
                    this.spike_price = spike_price;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getUpper_limit() {
                    return upper_limit;
                }

                public void setUpper_limit(String upper_limit) {
                    this.upper_limit = upper_limit;
                }

                public String getSpike_amount() {
                    return spike_amount;
                }

                public void setSpike_amount(String spike_amount) {
                    this.spike_amount = spike_amount;
                }

                public String getHad_spiked_count() {
                    return had_spiked_count;
                }

                public void setHad_spiked_count(String had_spiked_count) {
                    this.had_spiked_count = had_spiked_count;
                }

                public String getGoods_img_url() {
                    return goods_img_url;
                }

                public void setGoods_img_url(String goods_img_url) {
                    this.goods_img_url = goods_img_url;
                }

                public int getSpike_percent() {
                    return spike_percent;
                }

                public void setSpike_percent(int spike_percent) {
                    this.spike_percent = spike_percent;
                }
            }

            public static class _$7Bean {
                /**
                 * spike_id : 7
                 * goods_id : 100664
                 * store_id : 27
                 * goods_name : Harbor House Hemingway Pharmacy 台灯 铁艺 灯杆可调节
                 * goods_price : 1680.00
                 * spike_price : 577.00
                 * goods_image : 27_05279934694296462.jpg
                 * upper_limit : 0
                 * spike_amount : 10
                 * had_spiked_count : 0
                 * goods_img_url : http://test.shopnctest.com/data/upload/shop/store/goods/27/27_05279934694296462_360.jpg
                 * spike_percent : 0
                 */

                private String spike_id;
                private String goods_id;
                private String store_id;
                private String goods_name;
                private String goods_price;
                private String spike_price;
                private String goods_image;
                private String upper_limit;
                private String spike_amount;
                private String had_spiked_count;
                private String goods_img_url;
                private int spike_percent;

                public String getSpike_id() {
                    return spike_id;
                }

                public void setSpike_id(String spike_id) {
                    this.spike_id = spike_id;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
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

                public String getSpike_price() {
                    return spike_price;
                }

                public void setSpike_price(String spike_price) {
                    this.spike_price = spike_price;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getUpper_limit() {
                    return upper_limit;
                }

                public void setUpper_limit(String upper_limit) {
                    this.upper_limit = upper_limit;
                }

                public String getSpike_amount() {
                    return spike_amount;
                }

                public void setSpike_amount(String spike_amount) {
                    this.spike_amount = spike_amount;
                }

                public String getHad_spiked_count() {
                    return had_spiked_count;
                }

                public void setHad_spiked_count(String had_spiked_count) {
                    this.had_spiked_count = had_spiked_count;
                }

                public String getGoods_img_url() {
                    return goods_img_url;
                }

                public void setGoods_img_url(String goods_img_url) {
                    this.goods_img_url = goods_img_url;
                }

                public int getSpike_percent() {
                    return spike_percent;
                }

                public void setSpike_percent(int spike_percent) {
                    this.spike_percent = spike_percent;
                }
            }

            public static class _$5Bean {
                /**
                 * spike_id : 5
                 * goods_id : 100011
                 * store_id : 1
                 * goods_name : TCL LE50D8900 50吋镭射安卓智能液晶电视 高清LED 电视 珠光黑 官方标配
                 * goods_price : 3149.00
                 * spike_price : 1547.00
                 * goods_image : 1_04624741433161976.png
                 * upper_limit : 0
                 * spike_amount : 500
                 * had_spiked_count : 0
                 * goods_img_url : http://test.shopnctest.com/data/upload/shop/store/goods/1/1_04624741433161976_360.png
                 * spike_percent : 0
                 */

                private String spike_id;
                private String goods_id;
                private String store_id;
                private String goods_name;
                private String goods_price;
                private String spike_price;
                private String goods_image;
                private String upper_limit;
                private String spike_amount;
                private String had_spiked_count;
                private String goods_img_url;
                private int spike_percent;

                public String getSpike_id() {
                    return spike_id;
                }

                public void setSpike_id(String spike_id) {
                    this.spike_id = spike_id;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
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

                public String getSpike_price() {
                    return spike_price;
                }

                public void setSpike_price(String spike_price) {
                    this.spike_price = spike_price;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getUpper_limit() {
                    return upper_limit;
                }

                public void setUpper_limit(String upper_limit) {
                    this.upper_limit = upper_limit;
                }

                public String getSpike_amount() {
                    return spike_amount;
                }

                public void setSpike_amount(String spike_amount) {
                    this.spike_amount = spike_amount;
                }

                public String getHad_spiked_count() {
                    return had_spiked_count;
                }

                public void setHad_spiked_count(String had_spiked_count) {
                    this.had_spiked_count = had_spiked_count;
                }

                public String getGoods_img_url() {
                    return goods_img_url;
                }

                public void setGoods_img_url(String goods_img_url) {
                    this.goods_img_url = goods_img_url;
                }

                public int getSpike_percent() {
                    return spike_percent;
                }

                public void setSpike_percent(int spike_percent) {
                    this.spike_percent = spike_percent;
                }
            }
        }

        public static class SpikeListBean {
            /**
             * spike_id : 8
             * spike_name : 夏季护肤
             * spike_title : 夏季护肤
             * start_time : 1495597560
             * end_time : 1525104000
             * spike_state : 1
             * spike_common_bg : http://test.shopnctest.com/data/upload/shop/store/7/05489415937378760.png
             * spike_state_text : 正常
             * editable : true
             */

            private String spike_id;
            private String spike_name;
            private String spike_title;
            private String start_time;
            private String end_time;
            private String spike_state;
            private String spike_common_bg;
            private String spike_state_text;
            private boolean editable;

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

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getSpike_state() {
                return spike_state;
            }

            public void setSpike_state(String spike_state) {
                this.spike_state = spike_state;
            }

            public String getSpike_common_bg() {
                return spike_common_bg;
            }

            public void setSpike_common_bg(String spike_common_bg) {
                this.spike_common_bg = spike_common_bg;
            }

            public String getSpike_state_text() {
                return spike_state_text;
            }

            public void setSpike_state_text(String spike_state_text) {
                this.spike_state_text = spike_state_text;
            }

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
            }
        }
    }
}
