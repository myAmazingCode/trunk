package net.shopnc.b2b2c.android.bean;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/4/19 9:26.
 */
public class BuyData {
    private int goodsId;
    private int buyNum;
    private int cartId;

    public BuyData(int goodsId, int buyNum) {
        this.goodsId = goodsId;
        this.buyNum = buyNum;
    }

    public BuyData(int goodsId, int cartId, int buyNum) {
        this.goodsId = goodsId;
        this.buyNum = buyNum;
        this.cartId = cartId;
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

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "BuyData{" +
                "goodsId=" + goodsId +
                ", buyNum=" + buyNum +
                ", cartId=" + cartId +
                '}';
    }
}
