package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 作者：Jie on 2016/6/17 08:23
 */
public class MobileBodyVoList implements Serializable {


    /**
     * type : image
     * value : http://java.shopnctest.com/upload/image/db/a9/dba9b094508331368ae5e93284757ede.jpg
     */

    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MobileBodyVoList(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
