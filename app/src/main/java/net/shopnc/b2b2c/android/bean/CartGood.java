package net.shopnc.b2b2c.android.bean;

/**
 * 
 * @author KingKong-HE
 * @Time 2015-2-1
 * @Email KingKong@QQ.COM
 */
public class CartGood {

	private double goodsPrice;
	
	private double goodsAllPrice;
	
	private int goodsNum;
	
	private String goodsID;
	
	private boolean flag = false;
	
	public CartGood() {
	}
	
	public CartGood(double goodsPrice, double goodsAllPrice, int goodsNum,
			String goodsID, boolean flag) {
		super();
		this.goodsPrice = goodsPrice;
		this.goodsAllPrice = goodsAllPrice;
		this.goodsNum = goodsNum;
		this.goodsID = goodsID;
		this.flag = flag;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public double getGoodsAllPrice() {
		return goodsAllPrice;
	}
	public void setGoodsAllPrice(double goodsAllPrice) {
		this.goodsAllPrice = goodsAllPrice;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	@Override
	public String toString() {
		return "CartGood [goodsPrice=" + goodsPrice + ", goodsAllPrice="
				+ goodsAllPrice + ", goodsNum=" + goodsNum + ", goodsID="
				+ goodsID + ", flag=" + flag + "]";
	}
	
}
