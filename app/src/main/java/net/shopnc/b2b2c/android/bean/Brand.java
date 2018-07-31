package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 品牌实体类
 *
 * @author huting
 * @date 2016/4/11
 */
public class Brand implements Serializable {
    /**
     * 品牌编号
     */
    private int brandId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 英文名称
     */
    private String brandEnglish;
    /**
     * 品牌首字母
     */

    private String brandInitial;
    /**
     * 品牌图片名称
     */
    private String brandImage;
    /**
     * 品牌图片地址
     */
    private String brandImageSrc;
    /**
     * 店铺编号
     */

    private int storeId = 0;
    /**
     * 品牌排序
     */

    private int brandSort = 0;
    /**
     * 是否推荐<br>
     * 1是 0否
     */

    private int isRecommend = 0;
    /**
     * 申请状态<br>
     * 店铺申请添加品牌使用，0为申请中，1为通过，10为申请失败<br>
     * 店铺添加品牌为0，平台添加品牌为1
     */

    private int applyState = 1;
    /**
     * 展示方式<br>
     * 1图片，0文字
     */
    private int showType;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandEnglish() {
        return brandEnglish;
    }

    public void setBrandEnglish(String brandEnglish) {
        this.brandEnglish = brandEnglish;
    }

    public String getBrandInitial() {
        return brandInitial;
    }

    public void setBrandInitial(String brandInitial) {
        this.brandInitial = brandInitial;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    public String getBrandImageSrc() {
        return brandImageSrc;
    }

    public void setBrandImageSrc(String brandImageSrc) {
        this.brandImageSrc = brandImageSrc;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getBrandSort() {
        return brandSort;
    }

    public void setBrandSort(int brandSort) {
        this.brandSort = brandSort;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getApplyState() {
        return applyState;
    }

    public void setApplyState(int applyState) {
        this.applyState = applyState;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", brandEnglish='" + brandEnglish + '\'' +
                ", brandInitial='" + brandInitial + '\'' +
                ", brandImage='" + brandImage + '\'' +
                ", brandImageSrc='" + brandImageSrc + '\'' +
                ", storeId=" + storeId +
                ", brandSort=" + brandSort +
                ", isRecommend=" + isRecommend +
                ", applyState=" + applyState +
                ", showType=" + showType +
                '}';
    }
}
