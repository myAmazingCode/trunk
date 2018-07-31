package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by yuanshuo on 2017/10/19.
 */

public class CreditsOrderListBean {

    private List<OrderListBean> order_list;

    public List<OrderListBean> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderListBean> order_list) {
        this.order_list = order_list;
    }

    public static class OrderListBean {
        /**
         * member_avatar : http://test.shopnctest.com/data/upload/shop/avatar/avatar_42.jpg
         * member_name : lzp***ake
         * point_addtime : 2017-09-28
         * point_goodsnum : 1
         */

        private String member_avatar;
        private String member_name;
        private String point_addtime;
        private String point_goodsnum;

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getPoint_addtime() {
            return point_addtime;
        }

        public void setPoint_addtime(String point_addtime) {
            this.point_addtime = point_addtime;
        }

        public String getPoint_goodsnum() {
            return point_goodsnum;
        }

        public void setPoint_goodsnum(String point_goodsnum) {
            this.point_goodsnum = point_goodsnum;
        }
    }
}
