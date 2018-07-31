package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by snm on 2016/8/29.
 */
public class HomeVideo {


    /**
     * video_modules_name : 我的视频
     * wap_display : 0
     * video_isuse : 1
     * video_modules_logo : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/05255173409621699.jpg
     * item : [{"cate_parent_id":"0","cate_sort":"0","cate_image":"http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/mobile/video_category/05255351772430572.png","type_name":"","cate_id":"1057","is_recommend":"1","type_id":"0","cate_description":"精选精选精选","cate_name":"精选"},{"cate_parent_id":"0","cate_sort":"0","cate_image":"http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/mobile/video_category/05255354385476564.jpg","type_name":"","cate_id":"1058","is_recommend":"1","type_id":"0","cate_description":"热门热门","cate_name":"热门"},{"cate_parent_id":"0","cate_sort":"0","cate_image":"http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/mobile/video_category/05255374166123351.jpg","type_name":"","cate_id":"1059","is_recommend":"1","type_id":"0","cate_description":"APPLE","cate_name":"电脑"}]
     */

    private String video_modules_name;
    private int wap_display;
    private String video_isuse;
    private String video_modules_logo;
    /**
     * cate_parent_id : 0
     * cate_sort : 0
     * cate_image : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/mobile/video_category/05255351772430572.png
     * type_name :
     * cate_id : 1057
     * is_recommend : 1
     * type_id : 0
     * cate_description : 精选精选精选
     * cate_name : 精选
     */

    private List<ItemBean> item;

    public String getVideo_modules_name() {
        return video_modules_name;
    }

    public void setVideo_modules_name(String video_modules_name) {
        this.video_modules_name = video_modules_name;
    }

    public int getWap_display() {
        return wap_display;
    }

    public void setWap_display(int wap_display) {
        this.wap_display = wap_display;
    }

    public String getVideo_isuse() {
        return video_isuse;
    }

    public void setVideo_isuse(String video_isuse) {
        this.video_isuse = video_isuse;
    }

    public String getVideo_modules_logo() {
        return video_modules_logo;
    }

    public void setVideo_modules_logo(String video_modules_logo) {
        this.video_modules_logo = video_modules_logo;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        private String cate_parent_id;
        private String cate_sort;
        private String cate_image;
        private String type_name;
        private String cate_id;
        private String is_recommend;
        private String type_id;
        private String cate_description;
        private String cate_name;

        public String getCate_parent_id() {
            return cate_parent_id;
        }

        public void setCate_parent_id(String cate_parent_id) {
            this.cate_parent_id = cate_parent_id;
        }

        public String getCate_sort() {
            return cate_sort;
        }

        public void setCate_sort(String cate_sort) {
            this.cate_sort = cate_sort;
        }

        public String getCate_image() {
            return cate_image;
        }

        public void setCate_image(String cate_image) {
            this.cate_image = cate_image;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(String is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getCate_description() {
            return cate_description;
        }

        public void setCate_description(String cate_description) {
            this.cate_description = cate_description;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
