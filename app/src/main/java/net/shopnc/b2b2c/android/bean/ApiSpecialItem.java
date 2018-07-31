package net.shopnc.b2b2c.android.bean;

import net.shopnc.b2b2c.android.util.Constants;

import java.io.Serializable;

/**
 * 首页专题项目
 *
 * @author huting
 * @date 2016.5.3
 */
public class ApiSpecialItem implements Serializable{
    /**
     * 专题项目编号
     */
    private int itemId;
    /**
     * 专题编号
     */
    private int specialId;
    /**
     * 项目类型
     */
    private String itemType;
    /**
     * 项目数据
     */
    private String itemData = "";
    /**
     * android是否显示改项目
     */
    private int android = Constants.YES;
    /**
     * 项目排序
     */
    private int itemSort = 999;

    private String itemTypeText;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemData() {
        return itemData;
    }

    public void setItemData(String itemData) {
        this.itemData = itemData;
    }

    public int getAndroid() {
        return android;
    }

    public void setAndroid(int android) {
        this.android = android;
    }

    public int getItemSort() {
        return itemSort;
    }

    public void setItemSort(int itemSort) {
        this.itemSort = itemSort;
    }

    public String getItemTypeText() {
        return itemTypeText;
    }

    public void setItemTypeText(String itemTypeText) {
        this.itemTypeText = itemTypeText;
    }

    @Override
    public String toString() {
        return "ApiSpecialItem{" +
                "itemId=" + itemId +
                ", specialId=" + specialId +
                ", itemType='" + itemType + '\'' +
                ", itemData='" + itemData + '\'' +
                ", android=" + android +
                ", itemSort=" + itemSort +
                ", itemTypeText='" + itemTypeText + '\'' +
                '}';
    }
}
