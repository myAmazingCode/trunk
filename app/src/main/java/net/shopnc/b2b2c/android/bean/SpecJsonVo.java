package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * @Description
 * @Author qyf
 *
 * Created 2016/4/18 16:48.
 */
public class SpecJsonVo {
    private int specId;
    private String specName;
    private List<SpecValueListVo> specValueList;

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public List<SpecValueListVo> getSpecValueList() {
        return specValueList;
    }

    public void setSpecValueList(List<SpecValueListVo> specValueList) {
        this.specValueList = specValueList;
    }
}
