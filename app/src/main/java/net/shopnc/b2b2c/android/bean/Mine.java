/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:Login.java
 */
package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 我的商城BEAN
 *
 * @author dqw
 * @Time 2015/7/7
 */
public class Mine {

    private String memberName;
    private String memberAvatar;
    private String levelName;
    private String favStore;
    private String favGoods;
    private String orderNopay;
    private String orderNoreceipt;
    private String orderNotakes;
    private String orderNoeval;
    private String orderReturn;
    private String level;
    private String is_distr;
    private String is_movie;
    private String is_movie_msg;

    public Mine(String memberName, String memberAvatar, String levelName, String favStore, String favGoods, String orderNopay, String orderNoreceipt, String orderNotakes,
                String orderNoeval, String orderReturn, String level, String is_distr,String is_movie,String is_movie_msg) {
        this.memberName = memberName;
        this.memberAvatar = memberAvatar;
        this.levelName = levelName;
        this.favStore = favStore;
        this.favGoods = favGoods;
        this.orderNopay = orderNopay;
        this.orderNoreceipt = orderNoreceipt;
        this.orderNotakes = orderNotakes;
        this.orderNoeval = orderNoeval;
        this.orderReturn = orderReturn;
        this.level = level;
        this.is_distr = is_distr;
        this.is_movie = is_movie;
        this.is_movie_msg = is_movie_msg;
    }

    public static Mine newInstanceList(String json) {
        Mine bean = null;
        try {
            JSONObject obj = new JSONObject(json);
            if (obj.length() > 0) {
                String memberName = obj.optString("user_name");
                String memberAvatar = obj.optString("avatar");
                String levelName = obj.optString("level_name");
                String favStore = obj.optString("favorites_store");
                String favGoods = obj.optString("favorites_goods");
                String orderNopay = obj.optString("order_nopay_count");
                String orderNoreceipt = obj.optString("order_noreceipt_count");
                String orderNotakes = obj.optString("order_notakes_count");
                String orderNoeval = obj.optString("order_noeval_count");
                String orderReturn = obj.optString("return");
                String level = obj.optString("level");
                String is_distr = obj.optString("is_distri");
                String is_movie = obj.optString("is_movie");
                String is_movie_msg = obj.optString("is_movie_msg");
                bean = new Mine(memberName, memberAvatar, levelName, favStore, favGoods, orderNopay, orderNoreceipt, orderNotakes, orderNoeval, orderReturn,level,is_distr,is_movie,is_movie_msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getFavStore() {
        return favStore;
    }

    public void setFavStore(String favStore) {
        this.favStore = favStore;
    }

    public String getFavGoods() {
        return favGoods;
    }

    public void setFavGoods(String favGoods) {
        this.favGoods = favGoods;
    }

    public String getOrderNopay() {
        return orderNopay;
    }

    public void setOrderNopay(String orderNopay) {
        this.orderNopay = orderNopay;
    }

    public String getOrderNoreceipt() {
        return orderNoreceipt;
    }

    public void setOrderNoreceipt(String orderNoreceipt) {
        this.orderNoreceipt = orderNoreceipt;
    }

    public String getOrderNotakes() {
        return orderNotakes;
    }

    public void setOrderNotakes(String orderNotakes) {
        this.orderNotakes = orderNotakes;
    }

    public String getOrderNoeval() {
        return orderNoeval;
    }

    public void setOrderNoeval(String orderNoeval) {
        this.orderNoeval = orderNoeval;
    }

    public String getOrderReturn() {
        return orderReturn;
    }

    public void setOrderReturn(String orderReturn) {
        this.orderReturn = orderReturn;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIs_distr() {
        return is_distr;
    }

    public void setIs_distr(String is_distr) {
        this.is_distr = is_distr;
    }

    public String getIs_movie() {
        return is_movie;
    }

    public void setIs_movie(String is_movie) {
        this.is_movie = is_movie;
    }

    public String getIs_movie_msg() {
        return is_movie_msg;
    }

    public void setIs_movie_msg(String is_movie_msg) {
        this.is_movie_msg = is_movie_msg;
    }

    @Override
    public String toString() {
        return "Mine{" +
                "memberName='" + memberName + '\'' +
                ", memberAvatar='" + memberAvatar + '\'' +
                ", levelName='" + levelName + '\'' +
                ", favStore='" + favStore + '\'' +
                ", favGoods='" + favGoods + '\'' +
                ", orderNopay='" + orderNopay + '\'' +
                ", orderNoreceipt='" + orderNoreceipt + '\'' +
                ", orderNotakes='" + orderNotakes + '\'' +
                ", orderNoeval='" + orderNoeval + '\'' +
                ", orderReturn='" + orderReturn + '\'' +
                ", level='" + level + '\'' +
                ", is_distr='" + is_distr + '\'' +
                ", is_movie='" + is_movie + '\'' +
                ", is_movie_msg='" + is_movie_msg + '\'' +
                '}';
    }
}
