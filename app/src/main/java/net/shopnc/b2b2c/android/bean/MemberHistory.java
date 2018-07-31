package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 作者：Jie on 2016/6/7 15:07
 */

public class MemberHistory implements Serializable {

    int browseId;// 自增编码
    int commonId;// 商品SPU编号
    int memberId;//会员ID
    String addTime;//  浏览时间
    String addTimeText;// 浏览时间文字
    int goodsCategoryId;// 商品分类ID
    int goodsCategoryId1;//  商品一级分类
    int goodsCategoryId2;//  商品二级分类
    int goodsCategoryId3;// 商品三级分类
    String goodsName;// 商品名称
    GoodsVo goodsCommon ;

    public int getBrowseId() {
        return browseId;
    }

    public void setBrowseId(int browseId) {
        this.browseId = browseId;
    }

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddTimeText() {
        return addTimeText;
    }

    public void setAddTimeText(String addTimeText) {
        this.addTimeText = addTimeText;
    }

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public int getGoodsCategoryId1() {
        return goodsCategoryId1;
    }

    public void setGoodsCategoryId1(int goodsCategoryId1) {
        this.goodsCategoryId1 = goodsCategoryId1;
    }

    public int getGoodsCategoryId2() {
        return goodsCategoryId2;
    }

    public void setGoodsCategoryId2(int goodsCategoryId2) {
        this.goodsCategoryId2 = goodsCategoryId2;
    }

    public int getGoodsCategoryId3() {
        return goodsCategoryId3;
    }

    public void setGoodsCategoryId3(int goodsCategoryId3) {
        this.goodsCategoryId3 = goodsCategoryId3;
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
