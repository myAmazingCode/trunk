package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by yuanshuo on 2017/9/22.
 */

public class FlashBean {

    private List<FlashListBean> flash_list;

    public List<FlashListBean> getFlash_list() {
        return flash_list;
    }

    public void setFlash_list(List<FlashListBean> flash_list) {
        this.flash_list = flash_list;
    }

    public static class FlashListBean {
        /**
         * editable : true
         * end_time : 1506280801
         * exp_day : 2
         * exp_second : 211562
         * flash_brand : 9/05489502843599392.png
         * flash_brand_url : http://test.shopnctest.com/data/upload/shop/store/9/05489502843599392.png
         * flash_explain :
         * flash_id : 1
         * flash_name : 劲霸男装专场
         * flash_pic : 9/05489505391146450.png
         * flash_pic_url : http://test.shopnctest.com/data/upload/shop/store/9/05489505391146450.png
         * flash_state : 1
         * flash_state_text : 正常
         * flash_title : 全场包邮 低至1折
         * start_time : 1506021601
         */

        private boolean editable;
        private String end_time;
        private int exp_day;
        private int exp_second;
        private String flash_brand;
        private String flash_brand_url;
        private String flash_explain;
        private String flash_id;
        private String flash_name;
        private String flash_pic;
        private String flash_pic_url;
        private String flash_state;
        private String flash_state_text;
        private String flash_title;
        private String start_time;

        public boolean isEditable() {
            return editable;
        }

        public void setEditable(boolean editable) {
            this.editable = editable;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getExp_day() {
            return exp_day;
        }

        public void setExp_day(int exp_day) {
            this.exp_day = exp_day;
        }

        public int getExp_second() {
            return exp_second;
        }

        public void setExp_second(int exp_second) {
            this.exp_second = exp_second;
        }

        public String getFlash_brand() {
            return flash_brand;
        }

        public void setFlash_brand(String flash_brand) {
            this.flash_brand = flash_brand;
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

        public String getFlash_pic() {
            return flash_pic;
        }

        public void setFlash_pic(String flash_pic) {
            this.flash_pic = flash_pic;
        }

        public String getFlash_pic_url() {
            return flash_pic_url;
        }

        public void setFlash_pic_url(String flash_pic_url) {
            this.flash_pic_url = flash_pic_url;
        }

        public String getFlash_state() {
            return flash_state;
        }

        public void setFlash_state(String flash_state) {
            this.flash_state = flash_state;
        }

        public String getFlash_state_text() {
            return flash_state_text;
        }

        public void setFlash_state_text(String flash_state_text) {
            this.flash_state_text = flash_state_text;
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
    }
}
