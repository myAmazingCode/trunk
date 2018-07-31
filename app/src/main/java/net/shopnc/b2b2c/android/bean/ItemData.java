package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 图片项目实体
 */
public class ItemData implements Serializable{

    private String imageUrl;
    private String type;  //操作类型
    private String data;  //操作数据

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
