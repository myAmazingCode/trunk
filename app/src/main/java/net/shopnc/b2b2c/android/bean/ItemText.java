package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 文字项目实体
 */
public class ItemText implements Serializable{

    private String text;
    private String type;  //操作类型
    private String data;  //操作数据

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
