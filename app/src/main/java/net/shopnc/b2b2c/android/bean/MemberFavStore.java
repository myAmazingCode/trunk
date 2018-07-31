package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 作者：Jie on 2016/6/17 17:32
 */
public class MemberFavStore implements Serializable {

    int favoritesId;//  关注编号
    int memberId;// 会员id
    int storeId;// 店铺id
    String addTime;//  关注时间
    long goodsCommonCount;//  店铺拥有商品总数
    StoreInfo store;// 店铺信息

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public long getGoodsCommonCount() {
        return goodsCommonCount;
    }

    public void setGoodsCommonCount(long goodsCommonCount) {
        this.goodsCommonCount = goodsCommonCount;
    }

    public StoreInfo getStore() {
        return store;
    }

    public void setStore(StoreInfo store) {
        this.store = store;
    }
}
