package net.shopnc.b2b2c.android.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * 商品评价Bean
 */
public class GoodsEvalInfo {

    private ArrayList<GoodsEvalListBean> goods_eval_list;

    public ArrayList<GoodsEvalListBean> getGoods_eval_list() {
        return goods_eval_list;
    }

    public void setGoods_eval_list(ArrayList<GoodsEvalListBean> goods_eval_list) {
        this.goods_eval_list = goods_eval_list;
    }

    public static class GoodsEvalListBean {
        /**
         * geval_scores : 3
         * geval_content : 挺好的的，傅雷家书马上就了了
         * geval_addtime : 1492673220
         * geval_frommemberid : 10
         * geval_frommembername : lele123456
         * geval_explain : null
         * geval_content_again :
         * geval_addtime_again : 0
         * geval_explain_again :
         * member_avatar : http://b2b2c.shopnctest.com/test20170413/data/upload/shop/avatar/avatar_10.jpg
         * geval_addtime_date : 2017-04-20
         * geval_image_240 : []
         * geval_image_1024 : []
         * geval_addtime_again_date : 1970-01-01
         * geval_image_again_240 : []
         * geval_image_again_1024 : []
         */

        private String geval_scores;
        private String geval_content;
        private String geval_addtime;
        private String geval_frommemberid;
        private String geval_frommembername;
        private Object geval_explain;
        private String geval_content_again;
        private String geval_addtime_again;
        private String geval_explain_again;
        private String member_avatar;
        private String geval_addtime_date;
        private String geval_addtime_again_date;
        private ArrayList<String> geval_image_240;
        private ArrayList<String> geval_image_1024;
        private ArrayList<String> geval_image_again_240;
        private ArrayList<String> geval_image_again_1024;

        public String getGeval_scores() {
            return geval_scores;
        }

        public void setGeval_scores(String geval_scores) {
            this.geval_scores = geval_scores;
        }

        public String getGeval_content() {
            return geval_content;
        }

        public void setGeval_content(String geval_content) {
            this.geval_content = geval_content;
        }

        public String getGeval_addtime() {
            return geval_addtime;
        }

        public void setGeval_addtime(String geval_addtime) {
            this.geval_addtime = geval_addtime;
        }

        public String getGeval_frommemberid() {
            return geval_frommemberid;
        }

        public void setGeval_frommemberid(String geval_frommemberid) {
            this.geval_frommemberid = geval_frommemberid;
        }

        public String getGeval_frommembername() {
            return geval_frommembername;
        }

        public void setGeval_frommembername(String geval_frommembername) {
            this.geval_frommembername = geval_frommembername;
        }

        public Object getGeval_explain() {
            return geval_explain;
        }

        public void setGeval_explain(Object geval_explain) {
            this.geval_explain = geval_explain;
        }

        public String getGeval_content_again() {
            return geval_content_again;
        }

        public void setGeval_content_again(String geval_content_again) {
            this.geval_content_again = geval_content_again;
        }

        public String getGeval_addtime_again() {
            return geval_addtime_again;
        }

        public void setGeval_addtime_again(String geval_addtime_again) {
            this.geval_addtime_again = geval_addtime_again;
        }

        public String getGeval_explain_again() {
            return geval_explain_again;
        }

        public void setGeval_explain_again(String geval_explain_again) {
            this.geval_explain_again = geval_explain_again;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getGeval_addtime_date() {
            return geval_addtime_date;
        }

        public void setGeval_addtime_date(String geval_addtime_date) {
            this.geval_addtime_date = geval_addtime_date;
        }

        public String getGeval_addtime_again_date() {
            return geval_addtime_again_date;
        }

        public void setGeval_addtime_again_date(String geval_addtime_again_date) {
            this.geval_addtime_again_date = geval_addtime_again_date;
        }

        public ArrayList<String> getGeval_image_240() {
            return geval_image_240;
        }

        public void setGeval_image_240(ArrayList<String> geval_image_240) {
            this.geval_image_240 = geval_image_240;
        }

        public ArrayList<String> getGeval_image_1024() {
            return geval_image_1024;
        }

        public void setGeval_image_1024(ArrayList<String> geval_image_1024) {
            this.geval_image_1024 = geval_image_1024;
        }

        public ArrayList<String> getGeval_image_again_240() {
            return geval_image_again_240;
        }

        public void setGeval_image_again_240(ArrayList<String> geval_image_again_240) {
            this.geval_image_again_240 = geval_image_again_240;
        }

        public ArrayList<String> getGeval_image_again_1024() {
            return geval_image_again_1024;
        }

        public void setGeval_image_again_1024(ArrayList<String> geval_image_again_1024) {
            this.geval_image_again_1024 = geval_image_again_1024;
        }
    }
}
