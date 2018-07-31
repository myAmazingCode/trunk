package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 订单支付Vo
 * @Author qyf
 *
 * Created 2016/5/11 18:52.
 */
public class OrdersPayVo {
    private List<OrdersVo> ordersVoList;
    /**
     * 支付单ID
     */
    private int payId;
    /**
     * 支付单号
     */
    private long paySn;
    /**
     * 在线待支付订单总金额(选择在线支付的订单且未支付状态的订单总金额)
     */
    private BigDecimal ordersOnlineAmount = new BigDecimal(0);
    /**
     * 货到付款待支付订单总金额(未取消、未收货)
     */
    private BigDecimal ordersOfflineAmount = new BigDecimal(0);
    /**
     * 还需要在线支付金额(选择在线支付的订单，使用站内余额方式支付后还在线支付的差额)
     */
    private BigDecimal ordersOnlineDiffAmount = new BigDecimal(0);
    /**
     * 使用预存款支付金额
     */
    private BigDecimal predepositAmount = new BigDecimal(0);
    /**
     * 是否存在在线支付且未支付的订单
     */
    private int isExistOnlineNoPay = 0;

    public List<OrdersVo> getOrdersVoList() {
        return ordersVoList;
    }

    public void setOrdersVoList(List<OrdersVo> ordersVoList) {
        this.ordersVoList = ordersVoList;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public long getPaySn() {
        return paySn;
    }

    public void setPaySn(long paySn) {
        this.paySn = paySn;
    }

    public BigDecimal getOrdersOnlineAmount() {
        return ordersOnlineAmount;
    }

    public void setOrdersOnlineAmount(BigDecimal ordersOnlineAmount) {
        this.ordersOnlineAmount = ordersOnlineAmount;
    }

    public BigDecimal getOrdersOfflineAmount() {
        return ordersOfflineAmount;
    }

    public void setOrdersOfflineAmount(BigDecimal ordersOfflineAmount) {
        this.ordersOfflineAmount = ordersOfflineAmount;
    }

    public BigDecimal getOrdersOnlineDiffAmount() {
        return ordersOnlineDiffAmount;
    }

    public void setOrdersOnlineDiffAmount(BigDecimal ordersOnlineDiffAmount) {
        this.ordersOnlineDiffAmount = ordersOnlineDiffAmount;
    }

    public BigDecimal getPredepositAmount() {
        return predepositAmount;
    }

    public void setPredepositAmount(BigDecimal predepositAmount) {
        this.predepositAmount = predepositAmount;
    }

    public int getIsExistOnlineNoPay() {
        return isExistOnlineNoPay;
    }

    public void setIsExistOnlineNoPay(int isExistOnlineNoPay) {
        this.isExistOnlineNoPay = isExistOnlineNoPay;
    }
}
