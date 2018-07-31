package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 专题
 *
 * @author huting
 * @date 2016/5/3
 */
public class ApiSpecial implements Serializable{
    /**
     * 专题编号
     */
    private int specialId;
    /**
     * 专题描述
     */
    private String specialDesc;

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public String getSpecialDesc() {
        return specialDesc;
    }

    public void setSpecialDesc(String specialDesc) {
        this.specialDesc = specialDesc;
    }

    @Override
    public String toString() {
        return "ApiSpecial{" +
                "specialId=" + specialId +
                ", specialDesc='" + specialDesc + '\'' +
                '}';
    }
}
