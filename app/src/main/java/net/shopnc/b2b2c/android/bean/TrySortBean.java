package net.shopnc.b2b2c.android.bean;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/2/8 16:14.
 */

public class TrySortBean {
    private int categoryId;
    private String categoryName;
    private int categorySort;
    private String image;
    private String imageUrl;
    private String image1;
    private String imageUrl1;
    private boolean isSelected = false;

    public TrySortBean() {
    }

    public TrySortBean(int categoryId, String categoryName, boolean isSelected) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.isSelected = isSelected;
    }

    public TrySortBean(int categoryId, String categoryName, int categorySort, String image, String imageUrl, String image1, String imageUrl1, boolean isSelected) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categorySort = categorySort;
        this.image = image;
        this.imageUrl = imageUrl;
        this.image1 = image1;
        this.imageUrl1 = imageUrl1;
        this.isSelected = isSelected;
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

    public int getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(int categorySort) {
        this.categorySort = categorySort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
