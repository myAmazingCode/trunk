package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 作者：Jie on 2016/6/17 17:58
 */
public class MemberFavGoods implements Serializable {


    int favoritesId;// 关注编号
    int memberId;// 会员编码
    int commonId;//  商品SPU编码
    int storeId;// 店铺编码
    String addTime;// 关注时间
    double favGoodsPrice;// 商品关注时价格
    String goodsName;// 商品名称
    GoodsVo goodsCommon;

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

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
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

    public double getFavGoodsPrice() {
        return favGoodsPrice;
    }

    public void setFavGoodsPrice(double favGoodsPrice) {
        this.favGoodsPrice = favGoodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public GoodsVo getGoodsCommon() {
        return goodsCommon;
    }

    public void setGoodsCommon(GoodsVo goodsCommon) {
        this.goodsCommon = goodsCommon;
    }
}
