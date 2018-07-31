package net.shopnc.b2b2c.android.bean;

/**
 * @author lulei
 *         Created 2017/5/17 15:45
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 投诉bean
 */
public class Complain {

    private int complainId;//投诉id
    private String accusedName;//店铺名
    private String complainStateName;//投诉状态
    private String accuserTime;//投诉时间
    private String subjectTitle;//投诉主题
    private String imageSrc;//商品图片url
    private String goodsName;//商品名
    private String goodsFullSpecs;//规格
    private int showMemberClose;//是否可撤销投诉(1-是/0-否)

    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }

    public String getAccusedName() {
        return accusedName;
    }

    public void setAccusedName(String accusedName) {
        this.accusedName = accusedName;
    }

    public String getComplainStateName() {
        return complainStateName;
    }

    public void setComplainStateName(String complainStateName) {
        this.complainStateName = complainStateName;
    }

    public String getAccuserTime() {
        return accuserTime;
    }

    public void setAccuserTime(String accuserTime) {
        this.accuserTime = accuserTime;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsFullSpecs() {
        return goodsFullSpecs;
    }

    public void setGoodsFullSpecs(String goodsFullSpecs) {
        this.goodsFullSpecs = goodsFullSpecs;
    }

    public int getShowMemberClose() {
        return showMemberClose;
    }

    public void setShowMemberClose(int showMemberClose) {
        this.showMemberClose = showMemberClose;
    }
}
