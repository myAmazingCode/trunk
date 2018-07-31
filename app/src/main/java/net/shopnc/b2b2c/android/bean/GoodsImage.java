package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 商品图片实体
 *
 * @author huting
 * @date 2016/4/14
 */
public class GoodsImage implements Serializable {
    /**
     * 商品图片编号
     */
    private int imageId;
    /**
     * 商品SPU编号
     */
    private int commonId;
    /**
     * 颜色规格值编号<br>
     * 编号为1的规格对应的规格值的编号
     */
    private int colorId;
    /**
     * 图片名称
     */
    private String imageName;
    /**
     * 图片排序
     */
    private int imageSort;
    /**
     * 默认主图<br>
     * 1是，0否
     */
    private int isDefault;
    /**
     * 图片路径
     */
    private String imageSrc;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImageSort() {
        return imageSort;
    }

    public void setImageSort(int imageSort) {
        this.imageSort = imageSort;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }


}
