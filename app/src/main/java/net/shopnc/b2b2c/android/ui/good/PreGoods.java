package net.shopnc.b2b2c.android.ui.good;

/**
 * @Description 购物清单bean
 * @Author qyf
 *
 * Created 2016/4/26 14:39.
 */
public class PreGoods {
    private int buyNum;
    private int goodsId;
    private String specName;

    public PreGoods( int goodsId,int buyNum,String specName) {//int colorId, String specName,
        this.goodsId = goodsId;
        this.buyNum = buyNum;
        this.specName=specName;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public String getSpecName() {
        return specName;
    }

    public int getCount() {
        return buyNum;
    }

}
