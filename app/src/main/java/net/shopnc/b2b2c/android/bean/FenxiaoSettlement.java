package net.shopnc.b2b2c.android.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snm on 2016/9/23.
 */
public class FenxiaoSettlement {


    /**
     * dis_pay_time : 0
     * bill_state : 0
     * dis_commis_rate : 5
     * goods_id : 100606
     * goods_image_url : http://test.shopnctest.com/data/upload/shop/store/goods/1/1_04758452705384739_60.jpg
     * goods_name : Sony/索尼ILCE-5100L A5100单镜（16-50mm）微单数码相机
     * order_sn : 8000000000000201
     * refund_amount : 0.00
     * bill_state_txt : 未结算
     * dis_pay_amount : 200.00
     * pay_goods_amount : 4000.00
     */

    private ArrayList<BillListBean> bill_list;

    public ArrayList<BillListBean> getBill_list() {
        return bill_list;
    }

    public void setBill_list(ArrayList<BillListBean> bill_list) {
        this.bill_list = bill_list;
    }

    public static class BillListBean {
        private String dis_pay_time;
        private String bill_state;
        private String dis_commis_rate;
        private String goods_id;
        private String goods_image_url;
        private String goods_name;
        private String order_sn;
        private String refund_amount;
        private String bill_state_txt;
        private String dis_pay_amount;
        private String pay_goods_amount;

        public String getDis_pay_time() {
            return dis_pay_time;
        }

        public void setDis_pay_time(String dis_pay_time) {
            this.dis_pay_time = dis_pay_time;
        }

        public String getBill_state() {
            return bill_state;
        }

        public void setBill_state(String bill_state) {
            this.bill_state = bill_state;
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

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getRefund_amount() {
            return refund_amount;
        }

        public void setRefund_amount(String refund_amount) {
            this.refund_amount = refund_amount;
        }

        public String getBill_state_txt() {
            return bill_state_txt;
        }

        public void setBill_state_txt(String bill_state_txt) {
            this.bill_state_txt = bill_state_txt;
        }

        public String getDis_pay_amount() {
            return dis_pay_amount;
        }

        public void setDis_pay_amount(String dis_pay_amount) {
            this.dis_pay_amount = dis_pay_amount;
        }

        public String getPay_goods_amount() {
            return pay_goods_amount;
        }

        public void setPay_goods_amount(String pay_goods_amount) {
            this.pay_goods_amount = pay_goods_amount;
        }
    }
}
