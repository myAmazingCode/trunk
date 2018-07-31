package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by yuanshuo on 2017/10/20.
 */

public class CreditsCartBean {

    /**
     * cart_list : [{"pcart_id":"9","pgoods_choosenum":"1","pgoods_id":"10","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244_mid.jpg","pgoods_image_max":"http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244.jpg","pgoods_image_old":"04629877761622244.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244_small.jpg","pgoods_name":"酸梅汤 山楂乌梅茶 原料浓缩 速溶茶饮料","pgoods_pointone":1600,"pgoods_points":"1600","pmember_id":"220"},{"pcart_id":"10","pgoods_choosenum":"1","pgoods_id":"6","pgoods_image":"http://test.shopnctest.com/data/upload/shop/pointprod/04626497200595517_mid.jpg","pgoods_image_max":"http://test.shopnctest.com/data/upload/shop/pointprod/04626497200595517.jpg","pgoods_image_old":"04626497200595517.jpg","pgoods_image_small":"http://test.shopnctest.com/data/upload/shop/pointprod/04626497200595517_small.jpg","pgoods_name":"糖果枕 颈椎病专用枕头 护颈椎保健枕 竹炭枕头 颈椎治疗枕","pgoods_pointone":2000,"pgoods_points":"2000","pmember_id":"220"}]
     * pgoods_pointall : 3600
     */

    private int pgoods_pointall;
    private List<CartListBean> cart_list;

    public int getPgoods_pointall() {
        return pgoods_pointall;
    }

    public void setPgoods_pointall(int pgoods_pointall) {
        this.pgoods_pointall = pgoods_pointall;
    }

    public List<CartListBean> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<CartListBean> cart_list) {
        this.cart_list = cart_list;
    }

    public static class CartListBean {
        /**
         * pcart_id : 9
         * pgoods_choosenum : 1
         * pgoods_id : 10
         * pgoods_image : http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244_mid.jpg
         * pgoods_image_max : http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244.jpg
         * pgoods_image_old : 04629877761622244.jpg
         * pgoods_image_small : http://test.shopnctest.com/data/upload/shop/pointprod/04629877761622244_small.jpg
         * pgoods_name : 酸梅汤 山楂乌梅茶 原料浓缩 速溶茶饮料
         * pgoods_pointone : 1600
         * pgoods_points : 1600
         * pmember_id : 220
         */

        private String pcart_id;
        private String pgoods_choosenum;
        private String pgoods_id;
        private String pgoods_image;
        private String pgoods_image_max;
        private String pgoods_image_old;
        private String pgoods_image_small;
        private String pgoods_name;
        private int pgoods_pointone;
        private String pgoods_points;
        private String pmember_id;

        public String getPcart_id() {
            return pcart_id;
        }

        public void setPcart_id(String pcart_id) {
            this.pcart_id = pcart_id;
        }

        public String getPgoods_choosenum() {
            return pgoods_choosenum;
        }

        public void setPgoods_choosenum(String pgoods_choosenum) {
            this.pgoods_choosenum = pgoods_choosenum;
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

        public String getPgoods_name() {
            return pgoods_name;
        }

        public void setPgoods_name(String pgoods_name) {
            this.pgoods_name = pgoods_name;
        }

        public int getPgoods_pointone() {
            return pgoods_pointone;
        }

        public void setPgoods_pointone(int pgoods_pointone) {
            this.pgoods_pointone = pgoods_pointone;
        }

        public String getPgoods_points() {
            return pgoods_points;
        }

        public void setPgoods_points(String pgoods_points) {
            this.pgoods_points = pgoods_points;
        }

        public String getPmember_id() {
            return pmember_id;
        }

        public void setPmember_id(String pmember_id) {
            this.pmember_id = pmember_id;
        }
    }
}
