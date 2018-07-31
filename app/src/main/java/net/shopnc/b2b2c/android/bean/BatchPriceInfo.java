package net.shopnc.b2b2c.android.bean;

/**
 * @copyright  Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license    http://www.shopnc.net
 * @link       http://www.shopnc.net
 *
 * 多批次价格bean
 *
 * @author lulei
 * Created 2017/4/24 10:06
 */
public class BatchPriceInfo {

    private String batch;//批次
    private String price;//价格

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
