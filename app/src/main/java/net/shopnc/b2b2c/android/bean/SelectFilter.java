package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/31 11:06.
 */
public class SelectFilter implements Serializable{
    private int gift;
    private List<Object> searchCheckedFilterList;
    private int own;
    private List<GoodCategory> categoryList;
    private int express;
    private int promotion;
    private List<Attribute> attributeList;
    private List<Brand> brandList;

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    public List<Object> getSearchCheckedFilterList() {
        return searchCheckedFilterList;
    }

    public void setSearchCheckedFilterList(List<Object> searchCheckedFilterList) {
        this.searchCheckedFilterList = searchCheckedFilterList;
    }

    public int getOwn() {
        return own;
    }

    public void setOwn(int own) {
        this.own = own;
    }

    public List<GoodCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<GoodCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public int getExpress() {
        return express;
    }

    public void setExpress(int express) {
        this.express = express;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
}
