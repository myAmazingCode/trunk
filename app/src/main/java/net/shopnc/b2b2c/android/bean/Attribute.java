package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/9/1 10:53.
 */
public class Attribute {
    private int attributeId;
    private String attributeName;
    private int attributeSort;
    private List<AttributeValue> attributeValueList;
    private String attributeValueNames;
    private int categoryId;
    private int isShow;

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getAttributeSort() {
        return attributeSort;
    }

    public void setAttributeSort(int attributeSort) {
        this.attributeSort = attributeSort;
    }

    public List<AttributeValue> getAttributeValueList() {
        return attributeValueList;
    }

    public void setAttributeValueList(List<AttributeValue> attributeValueList) {
        this.attributeValueList = attributeValueList;
    }

    public String getAttributeValueNames() {
        return attributeValueNames;
    }

    public void setAttributeValueNames(String attributeValueNames) {
        this.attributeValueNames = attributeValueNames;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }
}
