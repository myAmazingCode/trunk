package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * spu vo
 * Created by hbj on 2016/4/26.
 */
public class BuyGoodsSpuVo {
    /**
     * sku 列表
     */
    private List<BuyGoodsItemVo> buyGoodsItemVoList;
    /**
     * spu 名称
     */
    private String goodsName;
    /**
     * spu id
     */
    private Integer commonId;
    /**
     * 图片路径
     */
    private String imageSrc;

    public List<BuyGoodsItemVo> getBuyGoodsItemVoList() {
        return buyGoodsItemVoList;
    }

    public void setBuyGoodsItemVoList(List<BuyGoodsItemVo> buyGoodsItemVoList) {
        this.buyGoodsItemVoList = buyGoodsItemVoList;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCommonId() {
        return commonId;
    }

    public void setCommonId(Integer commonId) {
        this.commonId = commonId;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
