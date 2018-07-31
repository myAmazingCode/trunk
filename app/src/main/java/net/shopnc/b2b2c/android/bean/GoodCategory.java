package net.shopnc.b2b2c.android.bean;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类
 *
 * @author huting
 * @date 2016/4/7
 */
public class GoodCategory implements Serializable {
    /**
     * 商品分类编号
     */
    private int categoryId;
    /**
     * 商品分类名称
     */
    private String categoryName;
    /**
     * 父级分类编号
     */
    private int parentId = 0;
    /**
     * 排序
     */
    private int categorySort;
    /**
     * 深度
     */
    private int deep = 0;

    /**
     * 移动端分类图片
     */
    private String appImage;
    /**
     * 移动端分类图片Url
     */
    private String appImageUrl;

    /**
     * 置灰图片icon
     */
    private Bitmap bitmap;

    private List<GoodCategory> categoryList = new ArrayList<GoodCategory>();

    public GoodCategory() {
    }

    public GoodCategory(int categoryId, String categoryName, String appImageUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.appImageUrl = appImageUrl;
    }

    public GoodCategory(int categoryId, String categoryName, int parentId, int categorySort, int deep, List<GoodCategory> categoryList) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.categorySort = categorySort;
        this.deep = deep;
        this.categoryList = categoryList;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(int categorySort) {
        this.categorySort = categorySort;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public List<GoodCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<GoodCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public String getAppImage() {
        return appImage;
    }

    public void setAppImage(String appImage) {
        this.appImage = appImage;
    }

    public String getAppImageUrl() {
        return appImageUrl;
    }

    public void setAppImageUrl(String appImageUrl) {
        this.appImageUrl = appImageUrl;
    }

    @Override
    public String toString() {
        return "GoodCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", parentId=" + parentId +
                ", categorySort=" + categorySort +
                ", deep=" + deep +
                ", appImage='" + appImage + '\'' +
                ", appImageUrl='" + appImageUrl + '\'' +
                ", categoryList=" + categoryList +
                '}';
    }
}
