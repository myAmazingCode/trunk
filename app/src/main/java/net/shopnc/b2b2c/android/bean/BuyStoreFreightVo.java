package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 配送规则Vo[单个店铺]
 * @Author qyf
 *
 * Created 2016/5/4 17:07.
 */
public class BuyStoreFreightVo {
    /**
     * 店铺Id
     */
    private Integer storeId;
    /**
     * 商品总价格
     */
    private BigDecimal storeGoodsAmount;
    /**
     * 店铺运费
     */
    private BigDecimal storeFreightAmount;
    /**
     * 店铺总金额(商品金额 + 店铺金额)
     */
//    private BigDecimal storeAmount;
    /**
     * 商品列表
     */
    private List<BuyGoodsItemFreightVo> goodsList;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getStoreGoodsAmount() {
        return storeGoodsAmount;
    }

    public void setStoreGoodsAmount(BigDecimal storeGoodsAmount) {
        this.storeGoodsAmount = storeGoodsAmount;
    }

    public BigDecimal getStoreFreightAmount() {
        return storeFreightAmount;
    }

    public void setStoreFreightAmount(BigDecimal storeFreightAmount) {
        this.storeFreightAmount = storeFreightAmount;
    }

    public List<BuyGoodsItemFreightVo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<BuyGoodsItemFreightVo> goodsList) {
        this.goodsList = goodsList;
    }
}
