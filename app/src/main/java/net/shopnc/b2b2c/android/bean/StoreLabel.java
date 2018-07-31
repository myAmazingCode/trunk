package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺分类
 *
 * @author huting
 * @date 2016/5/10
 */
public class StoreLabel implements Serializable{
    /**
     * 店内商品分类编号
     */
    private int storeLabelId;
    /**
     * 店内商品分类名称
     */
    private String storeLabelName;
    /**
     * 店内商品分类排序
     */
    private int storeLabelSort = 0;
    /**
     * 父级分类编号
     */
    private int parentId = 0;
    /**
     * 店铺编号
     */
    private int storeId;
    /**
     * 是否折叠
     */
    private int isFold;
    /**
     * 图片
     */
    private String image = "";
    /**
     * 图片路径
     */
    private String imageSrc;
    /**
     * 子集实体
     */
    private List<StoreLabel> storeLabelList;

    public int getStoreLabelId() {
        return storeLabelId;
    }

    public void setStoreLabelId(int storeLabelId) {
        this.storeLabelId = storeLabelId;
    }

    public String getStoreLabelName() {
        return storeLabelName;
    }

    public void setStoreLabelName(String storeLabelName) {
        this.storeLabelName = storeLabelName;
    }

    public int getStoreLabelSort() {
        return storeLabelSort;
    }

    public void setStoreLabelSort(int storeLabelSort) {
        this.storeLabelSort = storeLabelSort;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getIsFold() {
        return isFold;
    }

    public void setIsFold(int isFold) {
        this.isFold = isFold;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public List<StoreLabel> getStoreLabelList() {
        return storeLabelList;
    }

    public void setStoreLabelList(List<StoreLabel> storeLabelList) {
        this.storeLabelList = storeLabelList;
    }

    @Override
    public String toString() {
        return "StoreLabel{" +
                "storeLabelId=" + storeLabelId +
                ", storeLabelName='" + storeLabelName + '\'' +
                ", storeLabelSort=" + storeLabelSort +
                ", parentId=" + parentId +
                ", storeId=" + storeId +
                ", isFold=" + isFold +
                ", image='" + image + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                ", storeLabelList=" + storeLabelList +
                '}';
    }
}
