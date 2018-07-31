package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 地区
 *
 * @author huting
 * @date 2016/4/11
 */
public class Area implements Serializable {
    /**
     * 地区编号</br>
     * 主键、自增
     */
    private int areaId;
    /**
     * 地区名称
     */
    private String areaName;
    /**
     * 上级地区ID
     */
    private int areaParentId;
    /**
     * 深度<br/>
     * 一级地区深度为1，二级深度为2，依次类推。。。
     */
    private int areaDeep;
    /**
     * 所在大区</br>
     * 如华北、华中等
     */
    private String areaRegion;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(int areaParentId) {
        this.areaParentId = areaParentId;
    }

    public int getAreaDeep() {
        return areaDeep;
    }

    public void setAreaDeep(int areaDeep) {
        this.areaDeep = areaDeep;
    }

    public String getAreaRegion() {
        return areaRegion;
    }

    public void setAreaRegion(String areaRegion) {
        this.areaRegion = areaRegion;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", areaParentId=" + areaParentId +
                ", areaDeep=" + areaDeep +
                ", areaRegion='" + areaRegion + '\'' +
                '}';
    }
}
