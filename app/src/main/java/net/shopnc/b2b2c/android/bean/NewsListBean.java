package net.shopnc.b2b2c.android.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snm on 2016/8/31.
 */
public class NewsListBean {


    /**
     * video_id : 1
     * news_name : null
     * add_time : 2016-09-26
     * news_pic : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/default_goods_image_240.gif
     * page_view : 6
     * identity : demand
     * store_id : 2
     * store_name : 第三方店铺
     * store_logo : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/default_store_logo.gif
     * store_avatar : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/common/default_store_logo.gif
     * promote_video : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/mobile/demand/05282270483439530.mp4
     * demand_video : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/mobile/demand/05282270483601402.mp4
     * promote_text : sfasfadfa
     * promote_image :
     * member_id : 0
     * member_name :
     * movie_play_url :
     * movie_title :
     * movie_cover_img :
     * recommend_goods : [{"goods_id":"100030","goods_name":"ewewewee","goods_price":"10.00","goods_image":"http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/store/goods/2/2_05265619855412824_240.gif"}]
     * is_favorate : false
     */

    private String video_id;
    private String news_name;
    private String add_time;
    private String news_pic;
    private int page_view;
    private String identity;
    private String store_id;
    private String store_name;
    private String store_logo;
    private String store_avatar;
    private String promote_video;
    private String demand_video;
    private String promote_text;
    private String promote_image;
    //    private String member_id;
//    private String member_avater;
//    private String member_name;
//    private String movie_play_url;
//    private String movie_title;
//    private String movie_cover_img;
    private boolean is_favorate;

    private ArrayList<FocusListBean> cateList;//广告条
    private boolean isfooder;

    /**
     * goods_id : 100030
     * goods_name : ewewewee
     * goods_price : 10.00
     * goods_image : http://192.168.1.24/shopnc_b2b2c_distribute/www/data/upload/shop/store/goods/2/2_05265619855412824_240.gif
     */
    public ArrayList<FocusListBean> getCateList() {
        return cateList;
    }

    public void setCateList(ArrayList<FocusListBean> cateList) {
        this.cateList = cateList;
    }

    public boolean isfooder() {
        return isfooder;
    }

    public void setIsfooder(boolean isfooder) {
        this.isfooder = isfooder;
    }

    private List<RecommendGoodsBean> recommend_goods;

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getNews_name() {
        return news_name;
    }

    public void setNews_name(String news_name) {
        this.news_name = news_name;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getNews_pic() {
        return news_pic;
    }

    public void setNews_pic(String news_pic) {
        this.news_pic = news_pic;
    }

    public int getPage_view() {
        return page_view;
    }

    public void setPage_view(int page_view) {
        this.page_view = page_view;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public String getStore_logo() {
        return store_logo;
    }

    public void setStore_logo(String store_logo) {
        this.store_logo = store_logo;
    }

    public String getStore_avatar() {
        return store_avatar;
    }

    public void setStore_avatar(String store_avatar) {
        this.store_avatar = store_avatar;
    }

    public String getPromote_video() {
        return promote_video;
    }

    public void setPromote_video(String promote_video) {
        this.promote_video = promote_video;
    }

    public String getDemand_video() {
        return demand_video;
    }

    public void setDemand_video(String demand_video) {
        this.demand_video = demand_video;
    }

    public String getPromote_text() {
        return promote_text;
    }

    public void setPromote_text(String promote_text) {
        this.promote_text = promote_text;
    }

    public String getPromote_image() {
        return promote_image;
    }

    public void setPromote_image(String promote_image) {
        this.promote_image = promote_image;
    }
//
//    public String getMember_id() {
//        return member_id;
//    }
//
//    public void setMember_id(String member_id) {
//        this.member_id = member_id;
//    }
//
//    public String getMember_name() {
//        return member_name;
//    }
//
//    public void setMember_name(String member_name) {
//        this.member_name = member_name;
//    }
//
//    public String getMovie_play_url() {
//        return movie_play_url;
//    }
//
//    public void setMovie_play_url(String movie_play_url) {
//        this.movie_play_url = movie_play_url;
//    }
//
//    public String getMovie_title() {
//        return movie_title;
//    }
//
//    public void setMovie_title(String movie_title) {
//        this.movie_title = movie_title;
//    }
//
//    public String getMovie_cover_img() {
//        return movie_cover_img;
//    }
//
//    public void setMovie_cover_img(String movie_cover_img) {
//        this.movie_cover_img = movie_cover_img;
//    }

    public boolean isIs_favorate() {
        return is_favorate;
    }

    public void setIs_favorate(boolean is_favorate) {
        this.is_favorate = is_favorate;
    }

    public List<RecommendGoodsBean> getRecommend_goods() {
        return recommend_goods;
    }

    public void setRecommend_goods(List<RecommendGoodsBean> recommend_goods) {
        this.recommend_goods = recommend_goods;
    }

//    public String getMember_avater() {
//        return member_avater;
//    }
//
//    public void setMember_avater(String member_avater) {
//        this.member_avater = member_avater;
//    }

    public static class RecommendGoodsBean {
        private String goods_id;
        private String goods_commonid;
        private String goods_name;
        private String goods_price;
        private String goods_image_path;

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

        public String getGoods_image_path() {
            return goods_image_path;
        }

        public void setGoods_image_path(String goods_image_path) {
            this.goods_image_path = goods_image_path;
        }

        public String getGoods_commonid() {
            return goods_commonid;
        }

        public void setGoods_commonid(String goods_commonid) {
            this.goods_commonid = goods_commonid;
        }
    }
}
