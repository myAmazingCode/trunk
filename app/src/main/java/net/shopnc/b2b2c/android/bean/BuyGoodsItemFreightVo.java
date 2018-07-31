package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;

/**
 * @Description 配送规则Vo[单个商品]
 * @Author qyf
 *
 * Created 2016/5/4 17:08.
 */
public class BuyGoodsItemFreightVo {
    private Integer goodsId;
    /**
     * 商品小计
     */
    private BigDecimal goodsAmount;
    /**
     * 商品运费
     */
    private BigDecimal goodsFreightAmount;
    /**
     * 是否允许配送
     */
    private Integer allowSend;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getGoodsFreightAmount() {
        return goodsFreightAmount;
    }

    public void setGoodsFreightAmount(BigDecimal goodsFreightAmount) {
        this.goodsFreightAmount = goodsFreightAmount;
    }

    public Integer getAllowSend() {
        return allowSend;
    }

    public void setAllowSend(Integer allowSend) {
        this.allowSend = allowSend;
    }
}
