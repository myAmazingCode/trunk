package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * @Description
 * @Author qyf
 *
 * Created 2016/4/18 16:50.
 */
public class GoodsSpecValueJsonVo {
    private int goodsId;
    private List<Integer> specValueIds;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public List<Integer> getSpecValueIds() {
        return specValueIds;
    }

    public void setSpecValueIds(List<Integer> specValueIds) {
        this.specValueIds = specValueIds;
    }
}
