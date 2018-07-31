package net.shopnc.b2b2c.android.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snm on 2016/9/22.
 */
public class FenxiaoOrderlist {


    /**
     * goods_pay_price : 4000.00
     * dis_commis_amount : 120
     * goods_price : 4000.00
     * goods_num : 1
     * order_state : 20
     * dis_commis_rate : 3
     * goods_id : 100606
     * goods_image_url : http://test.shopnctest.com/data/upload/shop/store/goods/1/1_04758452705384739_60.jpg
     * store_id : 20
     * goods_name : Sony/索尼ILCE-5100L A5100单镜（16-50mm）微单数码相机
     * order_state_txt : 待发货
     * add_time : 1474524872
     * store_name : tao的分销测试店
     */

    private ArrayList<OrderListBean> order_list;

    public ArrayList<OrderListBean> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(ArrayList<OrderListBean> order_list) {
        this.order_list = order_list;
    }

    public static class OrderListBean {
        private String goods_pay_price;
        private String dis_commis_amount;
        private String goods_price;
        private String goods_num;
        private String order_state;
        private String dis_commis_rate;
        private String goods_id;
        private String goods_image_url;
        private String store_id;
        private String goods_name;
        private String order_state_txt;
        private String add_time;
        private String store_name;

        public String getGoods_pay_price() {
            return goods_pay_price;
        }

        public void setGoods_pay_price(String goods_pay_price) {
            this.goods_pay_price = goods_pay_price;
        }

        public String getDis_commis_amount() {
            return dis_commis_amount;
        }

        public void setDis_commis_amount(String dis_commis_amount) {
            this.dis_commis_amount = dis_commis_amount;
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

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getDis_commis_rate() {
            return dis_commis_rate;
        }

        public void setDis_commis_rate(String dis_commis_rate) {
            this.dis_commis_rate = dis_commis_rate;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_image_url() {
            return goods_image_url;
        }

        public void setGoods_image_url(String goods_image_url) {
            this.goods_image_url = goods_image_url;
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

        public String getOrder_state_txt() {
            return order_state_txt;
        }

        public void setOrder_state_txt(String order_state_txt) {
            this.order_state_txt = order_state_txt;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }
    }
}
