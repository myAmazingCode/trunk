package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/5/17 11:07.
 */
public class OrderDeliverInfoBean {
    private List<OrderDeliverInfoItemBean> expressVoList;
    private String shipName;
    private String shipSn;

    public List<OrderDeliverInfoItemBean> getExpressVoList() {
        return expressVoList;
    }

    public void setExpressVoList(List<OrderDeliverInfoItemBean> expressVoList) {
        this.expressVoList = expressVoList;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipSn() {
        return shipSn;
    }

    public void setShipSn(String shipSn) {
        this.shipSn = shipSn;
    }
}
