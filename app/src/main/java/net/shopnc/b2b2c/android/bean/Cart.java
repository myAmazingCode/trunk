package net.shopnc.b2b2c.android.bean;

/**
 * @Description 立即购买提交实体
 * @Author qyf
 *
 * Created 2016/4/29 9:42.
 */
public class Cart {
    /**
     * 购物车编号</br>
     * 主键、自增
     */
    private int cartId;
    /**
     * 会员ID
     */
    private int memberId;
    /**
     * 商品(sku)ID
     */
    private int goodsId;
    /**
     * 购买数量
     */
    private int buyNum;
    /**
     * 商品(spu)ID
     */
    private int commonId;

    public Cart() {
    }

    public Cart(int cartId, int memberId, int goodsId, int buyNum, int commonId) {
        this.cartId = cartId;
        this.memberId = memberId;
        this.goodsId = goodsId;
        this.buyNum = buyNum;
        this.commonId = commonId;
    }

    public Cart(int memberId, int goodsId, int buyNum, int commonId) {
        this.memberId = memberId;
        this.goodsId = goodsId;
        this.buyNum = buyNum;
        this.commonId = commonId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
    }
}
