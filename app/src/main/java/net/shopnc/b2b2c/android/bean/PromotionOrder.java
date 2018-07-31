package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/5 9:28
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 推广订单实体类
 */
public class PromotionOrder {


    /**
     * pageEntity : {"hasMore":true,"totalPage":2}
     * distributorOrdersList : [{"distributionOrdersId":72557,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-22 14:08:18","ordersCreateTime":"2016-12-22 14:08:22","commissionRate":5,"ordersGoodsId":72561,"finishTime":"2016-12-22 16:05:02","distributionOrdersType":5,"distributionOrdersTypeText":"推广单结算完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":0,"ordersSn":"1170000007255900","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":11.5,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"},{"distributionOrdersId":71502,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-22 14:03:21","ordersCreateTime":"2016-12-22 14:03:24","commissionRate":5,"ordersGoodsId":71506,"finishTime":"2016-12-22 16:05:02","distributionOrdersType":5,"distributionOrdersTypeText":"推广单结算完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":0,"ordersSn":"1840000007150400","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":11.5,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"},{"distributionOrdersId":71451,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-22 13:54:55","ordersCreateTime":"2016-12-22 13:55:47","commissionRate":5,"ordersGoodsId":71455,"finishTime":"2016-12-22 16:05:02","distributionOrdersType":5,"distributionOrdersTypeText":"推广单结算完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":0,"ordersSn":"1380000007145300","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":11.5,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"},{"distributionOrdersId":71389,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-22 11:58:20","ordersCreateTime":"2016-12-22 11:58:23","commissionRate":5,"ordersGoodsId":71393,"finishTime":"2016-12-22 11:59:00","distributionOrdersType":3,"distributionOrdersTypeText":"订单完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":0,"ordersSn":"1530000007139100","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":11.5,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"},{"distributionOrdersId":71351,"memberId":17820,"distributorId":45417,"commonId":23072,"goodsName":"万人往","addTime":"2016-12-22 11:45:26","ordersCreateTime":"2016-12-22 11:45:36","commissionRate":5,"ordersGoodsId":71356,"finishTime":null,"distributionOrdersType":2,"distributionOrdersTypeText":"订单进行中","goodsFullSpecs":"颜色：白色；规格：xl.","goodsPrice":150,"goodsPayAmount":150,"buyNum":1,"refundPrice":0,"ordersSn":"1230000007135400","ordersState":20,"ordersStateText":"待发货","storeId":14361,"storeName":"经典","commission":7.5,"imageSrc":"http://192.168.1.232/upload/image/20/17/2017d72711e5d98e362b72fe6cec3bd7.jpg","unitName":"件"},{"distributionOrdersId":51028,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-14 17:35:22","ordersCreateTime":"2016-12-14 17:35:42","commissionRate":5,"ordersGoodsId":51033,"finishTime":"2016-12-14 17:36:27","distributionOrdersType":5,"distributionOrdersTypeText":"推广单结算完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":230,"ordersSn":"1300000005103100","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":0,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"},{"distributionOrdersId":49868,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-14 16:01:29","ordersCreateTime":"2016-12-14 16:01:32","commissionRate":5,"ordersGoodsId":49872,"finishTime":"2016-12-14 16:02:15","distributionOrdersType":5,"distributionOrdersTypeText":"推广单结算完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":0,"ordersSn":"1540000004987000","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":11.5,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"},{"distributionOrdersId":48802,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-14 15:45:03","ordersCreateTime":"2016-12-14 15:45:07","commissionRate":5,"ordersGoodsId":48806,"finishTime":"2016-12-14 15:47:50","distributionOrdersType":5,"distributionOrdersTypeText":"推广单结算完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":0,"ordersSn":"1260000004880400","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":11.5,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"},{"distributionOrdersId":47722,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-14 15:12:53","ordersCreateTime":"2016-12-14 15:12:57","commissionRate":5,"ordersGoodsId":47726,"finishTime":"2016-12-14 15:13:27","distributionOrdersType":5,"distributionOrdersTypeText":"推广单结算完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":0,"ordersSn":"1130000004772400","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":11.5,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"},{"distributionOrdersId":47701,"memberId":17820,"distributorId":45417,"commonId":17886,"goodsName":"兰芝致美紧颜眼霜 紧致轮廓 改善皱纹","addTime":"2016-12-14 15:05:19","ordersCreateTime":"2016-12-14 15:05:24","commissionRate":5,"ordersGoodsId":47705,"finishTime":"2017-01-20 11:46:55","distributionOrdersType":5,"distributionOrdersTypeText":"推广单结算完成","goodsFullSpecs":"颜色：20ml","goodsPrice":230,"goodsPayAmount":230,"buyNum":1,"refundPrice":0,"ordersSn":"1810000004770300","ordersState":40,"ordersStateText":"交易完成","storeId":1,"storeName":"官方店铺","commission":11.5,"imageSrc":"http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg","unitName":"瓶"}]
     */

    private PageEntityBean pageEntity;
    private List<DistributorOrdersListBean> distributorOrdersList;

    public PageEntityBean getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(PageEntityBean pageEntity) {
        this.pageEntity = pageEntity;
    }

    public List<DistributorOrdersListBean> getDistributorOrdersList() {
        return distributorOrdersList;
    }

    public void setDistributorOrdersList(List<DistributorOrdersListBean> distributorOrdersList) {
        this.distributorOrdersList = distributorOrdersList;
    }

    public static class PageEntityBean {
        /**
         * hasMore : true
         * totalPage : 2
         */

        private boolean hasMore;
        private int totalPage;

        public boolean isHasMore() {
            return hasMore;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }
    }

    public static class DistributorOrdersListBean {
        /**
         * distributionOrdersId : 72557
         * memberId : 17820
         * distributorId : 45417
         * commonId : 17886
         * goodsName : 兰芝致美紧颜眼霜 紧致轮廓 改善皱纹
         * addTime : 2016-12-22 14:08:18
         * ordersCreateTime : 2016-12-22 14:08:22
         * commissionRate : 5
         * ordersGoodsId : 72561
         * finishTime : 2016-12-22 16:05:02
         * distributionOrdersType : 5
         * distributionOrdersTypeText : 推广单结算完成
         * goodsFullSpecs : 颜色：20ml
         * goodsPrice : 230
         * goodsPayAmount : 230
         * buyNum : 1
         * refundPrice : 0
         * ordersSn : 1170000007255900
         * ordersState : 40
         * ordersStateText : 交易完成
         * storeId : 1
         * storeName : 官方店铺
         * commission : 11.5
         * imageSrc : http://192.168.1.232/upload/image/19/7e/197eaed875ad9d3958d8adbb002dd5e6.jpg
         * unitName : 瓶
         */

        private int distributionOrdersId;
        private int memberId;
        private int distributorId;
        private int commonId;
        private String goodsName;
        private String addTime;
        private String ordersCreateTime;
        private float commissionRate;
        private int ordersGoodsId;
        private String finishTime;
        private int distributionOrdersType;
        private String distributionOrdersTypeText;
        private String goodsFullSpecs;
        private float goodsPrice;
        private float goodsPayAmount;
        private int buyNum;
        private int refundPrice;
        private String ordersSn;
        private int ordersState;
        private String ordersStateText;
        private int storeId;
        private String storeName;
        private double commission;
        private String imageSrc;
        private String unitName;

        public int getDistributionOrdersId() {
            return distributionOrdersId;
        }

        public void setDistributionOrdersId(int distributionOrdersId) {
            this.distributionOrdersId = distributionOrdersId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getDistributorId() {
            return distributorId;
        }

        public void setDistributorId(int distributorId) {
            this.distributorId = distributorId;
        }

        public int getCommonId() {
            return commonId;
        }

        public void setCommonId(int commonId) {
            this.commonId = commonId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getOrdersCreateTime() {
            return ordersCreateTime;
        }

        public void setOrdersCreateTime(String ordersCreateTime) {
            this.ordersCreateTime = ordersCreateTime;
        }

        public float getCommissionRate() {
            return commissionRate;
        }

        public void setCommissionRate(float commissionRate) {
            this.commissionRate = commissionRate;
        }

        public int getOrdersGoodsId() {
            return ordersGoodsId;
        }

        public void setOrdersGoodsId(int ordersGoodsId) {
            this.ordersGoodsId = ordersGoodsId;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public int getDistributionOrdersType() {
            return distributionOrdersType;
        }

        public void setDistributionOrdersType(int distributionOrdersType) {
            this.distributionOrdersType = distributionOrdersType;
        }

        public String getDistributionOrdersTypeText() {
            return distributionOrdersTypeText;
        }

        public void setDistributionOrdersTypeText(String distributionOrdersTypeText) {
            this.distributionOrdersTypeText = distributionOrdersTypeText;
        }

        public String getGoodsFullSpecs() {
            return goodsFullSpecs;
        }

        public void setGoodsFullSpecs(String goodsFullSpecs) {
            this.goodsFullSpecs = goodsFullSpecs;
        }

        public float getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(float goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public float getGoodsPayAmount() {
            return goodsPayAmount;
        }

        public void setGoodsPayAmount(float goodsPayAmount) {
            this.goodsPayAmount = goodsPayAmount;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public int getRefundPrice() {
            return refundPrice;
        }

        public void setRefundPrice(int refundPrice) {
            this.refundPrice = refundPrice;
        }

        public String getOrdersSn() {
            return ordersSn;
        }

        public void setOrdersSn(String ordersSn) {
            this.ordersSn = ordersSn;
        }

        public int getOrdersState() {
            return ordersState;
        }

        public void setOrdersState(int ordersState) {
            this.ordersState = ordersState;
        }

        public String getOrdersStateText() {
            return ordersStateText;
        }

        public void setOrdersStateText(String ordersStateText) {
            this.ordersStateText = ordersStateText;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public double getCommission() {
            return commission;
        }

        public void setCommission(double commission) {
            this.commission = commission;
        }

        public String getImageSrc() {
            return imageSrc;
        }

        public void setImageSrc(String imageSrc) {
            this.imageSrc = imageSrc;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }
    }
}
