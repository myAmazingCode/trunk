package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by xws on 2017/2/10.
 */

public class MyRepoList {


    /**
     * code : 200
     * datas : {"favList":[{"distributorFavoritesId":35,"distributorId":7,"distributorFavoritesName":"默认文件夹","createTime":1486694431000,"distributionGoodsVoList":[{"commonId":1,"goodsName":"意大利费列罗榛果威化进口巧克力48粒装端午节节零食礼盒婚庆喜糖","createTime":"1970-01-01 08:00:00","isCommend":1,"jingle":"","batchNum0":10,"batchNum0End":29,"batchNum1":30,"batchNum1End":99,"batchNum2":100,"goodsPrice":0,"batchPrice0":125,"batchPrice1":120,"batchPrice2":105.5,"webPriceMin":58.02,"appPriceMin":58.02,"wechatPriceMin":58.02,"webUsable":1,"appUsable":1,"wechatUsable":1,"promotionType":0,"promotionTypeText":"","goodsModal":2,"goodsSaleNum":0,"goodsStorage":0,"goodsStatus":1,"imageSrc":"https://java.bizpower.com/upload/image/c5/ed/c5edbe0d30cffdc57d520cc662e5b9a0.jpg","imageName":"image/c5/ed/c5edbe0d30cffdc57d520cc662e5b9a0.jpg","unitName":"盒","priceRange":"105.50-125.00","isGift":0,"goodsRate":0,"evaluateNum":0,"storage":0,"batchNumPriceVoList":[],"commissionRate":5,"addTime":"2017-02-10 11:11:36","distributorGoodsId":346,"storeId":2,"storeName":"天天食品旗舰店","distributorId":7,"memberId":30,"memberName":"lzz123","monthlyOrdersCount":0,"monthlyCommission":0,"ordersCount":0,"commissionTotal":null,"distributionCount":0},{"commonId":174,"goodsName":"华为（HUAWEI）华为揽阅M2青春版 7.0英寸通话小平板 珍珠白","createTime":"1970-01-01 08:00:00","isCommend":0,"jingle":"","batchNum0":1,"batchNum0End":0,"batchNum1":0,"batchNum1End":0,"batchNum2":0,"goodsPrice":0,"batchPrice0":1799,"batchPrice1":0,"batchPrice2":1599,"webPriceMin":1599,"appPriceMin":1599,"wechatPriceMin":1599,"webUsable":1,"appUsable":1,"wechatUsable":1,"promotionType":0,"promotionTypeText":"","goodsModal":1,"goodsSaleNum":11,"goodsStorage":0,"goodsStatus":1,"imageSrc":"https://java.bizpower.com/upload/image/cd/6c/cd6c3c180e7a7180cd5bdc7cd7bfa09c.jpg","imageName":"image/cd/6c/cd6c3c180e7a7180cd5bdc7cd7bfa09c.jpg","unitName":"部","priceRange":"1599.00-1799.00","isGift":0,"goodsRate":0,"evaluateNum":0,"storage":0,"batchNumPriceVoList":[],"commissionRate":5,"addTime":"2017-02-10 10:42:36","distributorGoodsId":345,"storeId":6,"storeName":"华为旗舰店","distributorId":7,"memberId":30,"memberName":"lzz123","monthlyOrdersCount":0,"monthlyCommission":0,"ordersCount":3,"commissionTotal":239.85,"distributionCount":0}],"goodsCount":2,"isDefault":1},{"distributorFavoritesId":36,"distributorId":7,"distributorFavoritesName":"新选品","createTime":1486697600000,"distributionGoodsVoList":[],"goodsCount":0,"isDefault":0}]}
     */

    private String code;
    private DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * distributorFavoritesId : 35
         * distributorId : 7
         * distributorFavoritesName : 默认文件夹
         * createTime : 1486694431000
         * distributionGoodsVoList : [{"commonId":1,"goodsName":"意大利费列罗榛果威化进口巧克力48粒装端午节节零食礼盒婚庆喜糖","createTime":"1970-01-01 08:00:00","isCommend":1,"jingle":"","batchNum0":10,"batchNum0End":29,"batchNum1":30,"batchNum1End":99,"batchNum2":100,"goodsPrice":0,"batchPrice0":125,"batchPrice1":120,"batchPrice2":105.5,"webPriceMin":58.02,"appPriceMin":58.02,"wechatPriceMin":58.02,"webUsable":1,"appUsable":1,"wechatUsable":1,"promotionType":0,"promotionTypeText":"","goodsModal":2,"goodsSaleNum":0,"goodsStorage":0,"goodsStatus":1,"imageSrc":"https://java.bizpower.com/upload/image/c5/ed/c5edbe0d30cffdc57d520cc662e5b9a0.jpg","imageName":"image/c5/ed/c5edbe0d30cffdc57d520cc662e5b9a0.jpg","unitName":"盒","priceRange":"105.50-125.00","isGift":0,"goodsRate":0,"evaluateNum":0,"storage":0,"batchNumPriceVoList":[],"commissionRate":5,"addTime":"2017-02-10 11:11:36","distributorGoodsId":346,"storeId":2,"storeName":"天天食品旗舰店","distributorId":7,"memberId":30,"memberName":"lzz123","monthlyOrdersCount":0,"monthlyCommission":0,"ordersCount":0,"commissionTotal":null,"distributionCount":0},{"commonId":174,"goodsName":"华为（HUAWEI）华为揽阅M2青春版 7.0英寸通话小平板 珍珠白","createTime":"1970-01-01 08:00:00","isCommend":0,"jingle":"","batchNum0":1,"batchNum0End":0,"batchNum1":0,"batchNum1End":0,"batchNum2":0,"goodsPrice":0,"batchPrice0":1799,"batchPrice1":0,"batchPrice2":1599,"webPriceMin":1599,"appPriceMin":1599,"wechatPriceMin":1599,"webUsable":1,"appUsable":1,"wechatUsable":1,"promotionType":0,"promotionTypeText":"","goodsModal":1,"goodsSaleNum":11,"goodsStorage":0,"goodsStatus":1,"imageSrc":"https://java.bizpower.com/upload/image/cd/6c/cd6c3c180e7a7180cd5bdc7cd7bfa09c.jpg","imageName":"image/cd/6c/cd6c3c180e7a7180cd5bdc7cd7bfa09c.jpg","unitName":"部","priceRange":"1599.00-1799.00","isGift":0,"goodsRate":0,"evaluateNum":0,"storage":0,"batchNumPriceVoList":[],"commissionRate":5,"addTime":"2017-02-10 10:42:36","distributorGoodsId":345,"storeId":6,"storeName":"华为旗舰店","distributorId":7,"memberId":30,"memberName":"lzz123","monthlyOrdersCount":0,"monthlyCommission":0,"ordersCount":3,"commissionTotal":239.85,"distributionCount":0}]
         * goodsCount : 2
         * isDefault : 1
         */

        private List<FavListBean> favList;

        public List<FavListBean> getFavList() {
            return favList;
        }

        public void setFavList(List<FavListBean> favList) {
            this.favList = favList;
        }

        public static class FavListBean {
            private String distributorFavoritesId;
            private String distributorId;
            private String distributorFavoritesName;
            private long createTime;
            private String goodsCount;
            private String isDefault;
            /**
             * commonId : 1
             * goodsName : 意大利费列罗榛果威化进口巧克力48粒装端午节节零食礼盒婚庆喜糖
             * createTime : 1970-01-01 08:00:00
             * isCommend : 1
             * jingle :
             * batchNum0 : 10
             * batchNum0End : 29
             * batchNum1 : 30
             * batchNum1End : 99
             * batchNum2 : 100
             * goodsPrice : 0
             * batchPrice0 : 125
             * batchPrice1 : 120
             * batchPrice2 : 105.5
             * webPriceMin : 58.02
             * appPriceMin : 58.02
             * wechatPriceMin : 58.02
             * webUsable : 1
             * appUsable : 1
             * wechatUsable : 1
             * promotionType : 0
             * promotionTypeText :
             * goodsModal : 2
             * goodsSaleNum : 0
             * goodsStorage : 0
             * goodsStatus : 1
             * imageSrc : https://java.bizpower.com/upload/image/c5/ed/c5edbe0d30cffdc57d520cc662e5b9a0.jpg
             * imageName : image/c5/ed/c5edbe0d30cffdc57d520cc662e5b9a0.jpg
             * unitName : 盒
             * priceRange : 105.50-125.00
             * isGift : 0
             * goodsRate : 0
             * evaluateNum : 0
             * storage : 0
             * batchNumPriceVoList : []
             * commissionRate : 5
             * addTime : 2017-02-10 11:11:36
             * distributorGoodsId : 346
             * storeId : 2
             * storeName : 天天食品旗舰店
             * distributorId : 7
             * memberId : 30
             * memberName : lzz123
             * monthlyOrdersCount : 0
             * monthlyCommission : 0
             * ordersCount : 0
             * commissionTotal : null
             * distributionCount : 0
             */

            private List<DistributionGoodsVoListBean> distributionGoodsVoList;

            public String getDistributorFavoritesId() {
                return distributorFavoritesId;
            }

            public void setDistributorFavoritesId(String distributorFavoritesId) {
                this.distributorFavoritesId = distributorFavoritesId;
            }

            public String getDistributorId() {
                return distributorId;
            }

            public void setDistributorId(String distributorId) {
                this.distributorId = distributorId;
            }

            public String getDistributorFavoritesName() {
                return distributorFavoritesName;
            }

            public void setDistributorFavoritesName(String distributorFavoritesName) {
                this.distributorFavoritesName = distributorFavoritesName;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(String goodsCount) {
                this.goodsCount = goodsCount;
            }

            public String getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(String isDefault) {
                this.isDefault = isDefault;
            }

            public List<DistributionGoodsVoListBean> getDistributionGoodsVoList() {
                return distributionGoodsVoList;
            }

            public void setDistributionGoodsVoList(List<DistributionGoodsVoListBean> distributionGoodsVoList) {
                this.distributionGoodsVoList = distributionGoodsVoList;
            }

            public static class DistributionGoodsVoListBean {
                private String commonId;
                private String goodsName;
                private String createTime;
                private String isCommend;
                private String jingle;
                private String batchNum0;
                private String batchNum0End;
                private String batchNum1;
                private String batchNum1End;
                private String batchNum2;
                private String goodsPrice;
                private String batchPrice0;
                private String batchPrice1;
                private double batchPrice2;
                private double webPriceMin;
                private double appPriceMin;
                private double wechatPriceMin;
                private String webUsable;
                private String appUsable;
                private String wechatUsable;
                private String promotionType;
                private String promotionTypeText;
                private String goodsModal;
                private String goodsSaleNum;
                private String goodsStorage;
                private String goodsStatus;
                private String imageSrc;
                private String imageName;
                private String unitName;
                private String priceRange;
                private String isGift;
                private String goodsRate;
                private String evaluateNum;
                private String storage;
                private String commissionRate;
                private String addTime;
                private String distributorGoodsId;
                private String storeId;
                private String storeName;
                private String distributorId;
                private String memberId;
                private String memberName;
                private String monthlyOrdersCount;
                private String monthlyCommission;
                private String ordersCount;
                private String commissionTotal;
                private String distributionCount;
                private List<Object> batchNumPriceVoList;

                public String getCommonId() {
                    return commonId;
                }

                public void setCommonId(String commonId) {
                    this.commonId = commonId;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getIsCommend() {
                    return isCommend;
                }

                public void setIsCommend(String isCommend) {
                    this.isCommend = isCommend;
                }

                public String getJingle() {
                    return jingle;
                }

                public void setJingle(String jingle) {
                    this.jingle = jingle;
                }

                public String getBatchNum0() {
                    return batchNum0;
                }

                public void setBatchNum0(String batchNum0) {
                    this.batchNum0 = batchNum0;
                }

                public String getBatchNum0End() {
                    return batchNum0End;
                }

                public void setBatchNum0End(String batchNum0End) {
                    this.batchNum0End = batchNum0End;
                }

                public String getBatchNum1() {
                    return batchNum1;
                }

                public void setBatchNum1(String batchNum1) {
                    this.batchNum1 = batchNum1;
                }

                public String getBatchNum1End() {
                    return batchNum1End;
                }

                public void setBatchNum1End(String batchNum1End) {
                    this.batchNum1End = batchNum1End;
                }

                public String getBatchNum2() {
                    return batchNum2;
                }

                public void setBatchNum2(String batchNum2) {
                    this.batchNum2 = batchNum2;
                }

                public String getGoodsPrice() {
                    return goodsPrice;
                }

                public void setGoodsPrice(String goodsPrice) {
                    this.goodsPrice = goodsPrice;
                }

                public String getBatchPrice0() {
                    return batchPrice0;
                }

                public void setBatchPrice0(String batchPrice0) {
                    this.batchPrice0 = batchPrice0;
                }

                public String getBatchPrice1() {
                    return batchPrice1;
                }

                public void setBatchPrice1(String batchPrice1) {
                    this.batchPrice1 = batchPrice1;
                }

                public double getBatchPrice2() {
                    return batchPrice2;
                }

                public void setBatchPrice2(double batchPrice2) {
                    this.batchPrice2 = batchPrice2;
                }

                public double getWebPriceMin() {
                    return webPriceMin;
                }

                public void setWebPriceMin(double webPriceMin) {
                    this.webPriceMin = webPriceMin;
                }

                public double getAppPriceMin() {
                    return appPriceMin;
                }

                public void setAppPriceMin(double appPriceMin) {
                    this.appPriceMin = appPriceMin;
                }

                public double getWechatPriceMin() {
                    return wechatPriceMin;
                }

                public void setWechatPriceMin(double wechatPriceMin) {
                    this.wechatPriceMin = wechatPriceMin;
                }

                public String getWebUsable() {
                    return webUsable;
                }

                public void setWebUsable(String webUsable) {
                    this.webUsable = webUsable;
                }

                public String getAppUsable() {
                    return appUsable;
                }

                public void setAppUsable(String appUsable) {
                    this.appUsable = appUsable;
                }

                public String getWechatUsable() {
                    return wechatUsable;
                }

                public void setWechatUsable(String wechatUsable) {
                    this.wechatUsable = wechatUsable;
                }

                public String getPromotionType() {
                    return promotionType;
                }

                public void setPromotionType(String promotionType) {
                    this.promotionType = promotionType;
                }

                public String getPromotionTypeText() {
                    return promotionTypeText;
                }

                public void setPromotionTypeText(String promotionTypeText) {
                    this.promotionTypeText = promotionTypeText;
                }

                public String getGoodsModal() {
                    return goodsModal;
                }

                public void setGoodsModal(String goodsModal) {
                    this.goodsModal = goodsModal;
                }

                public String getGoodsSaleNum() {
                    return goodsSaleNum;
                }

                public void setGoodsSaleNum(String goodsSaleNum) {
                    this.goodsSaleNum = goodsSaleNum;
                }

                public String getGoodsStorage() {
                    return goodsStorage;
                }

                public void setGoodsStorage(String goodsStorage) {
                    this.goodsStorage = goodsStorage;
                }

                public String getGoodsStatus() {
                    return goodsStatus;
                }

                public void setGoodsStatus(String goodsStatus) {
                    this.goodsStatus = goodsStatus;
                }

                public String getImageSrc() {
                    return imageSrc;
                }

                public void setImageSrc(String imageSrc) {
                    this.imageSrc = imageSrc;
                }

                public String getImageName() {
                    return imageName;
                }

                public void setImageName(String imageName) {
                    this.imageName = imageName;
                }

                public String getUnitName() {
                    return unitName;
                }

                public void setUnitName(String unitName) {
                    this.unitName = unitName;
                }

                public String getPriceRange() {
                    return priceRange;
                }

                public void setPriceRange(String priceRange) {
                    this.priceRange = priceRange;
                }

                public String getIsGift() {
                    return isGift;
                }

                public void setIsGift(String isGift) {
                    this.isGift = isGift;
                }

                public String getGoodsRate() {
                    return goodsRate;
                }

                public void setGoodsRate(String goodsRate) {
                    this.goodsRate = goodsRate;
                }

                public String getEvaluateNum() {
                    return evaluateNum;
                }

                public void setEvaluateNum(String evaluateNum) {
                    this.evaluateNum = evaluateNum;
                }

                public String getStorage() {
                    return storage;
                }

                public void setStorage(String storage) {
                    this.storage = storage;
                }

                public String getCommissionRate() {
                    return commissionRate;
                }

                public void setCommissionRate(String commissionRate) {
                    this.commissionRate = commissionRate;
                }

                public String getAddTime() {
                    return addTime;
                }

                public void setAddTime(String addTime) {
                    this.addTime = addTime;
                }

                public String getDistributorGoodsId() {
                    return distributorGoodsId;
                }

                public void setDistributorGoodsId(String distributorGoodsId) {
                    this.distributorGoodsId = distributorGoodsId;
                }

                public String getStoreId() {
                    return storeId;
                }

                public void setStoreId(String storeId) {
                    this.storeId = storeId;
                }

                public String getStoreName() {
                    return storeName;
                }

                public void setStoreName(String storeName) {
                    this.storeName = storeName;
                }

                public String getDistributorId() {
                    return distributorId;
                }

                public void setDistributorId(String distributorId) {
                    this.distributorId = distributorId;
                }

                public String getMemberId() {
                    return memberId;
                }

                public void setMemberId(String memberId) {
                    this.memberId = memberId;
                }

                public String getMemberName() {
                    return memberName;
                }

                public void setMemberName(String memberName) {
                    this.memberName = memberName;
                }

                public String getMonthlyOrdersCount() {
                    return monthlyOrdersCount;
                }

                public void setMonthlyOrdersCount(String monthlyOrdersCount) {
                    this.monthlyOrdersCount = monthlyOrdersCount;
                }

                public String getMonthlyCommission() {
                    return monthlyCommission;
                }

                public void setMonthlyCommission(String monthlyCommission) {
                    this.monthlyCommission = monthlyCommission;
                }

                public String getOrdersCount() {
                    return ordersCount;
                }

                public void setOrdersCount(String ordersCount) {
                    this.ordersCount = ordersCount;
                }

                public String getCommissionTotal() {
                    return commissionTotal;
                }

                public void setCommissionTotal(String commissionTotal) {
                    this.commissionTotal = commissionTotal;
                }

                public String getDistributionCount() {
                    return distributionCount;
                }

                public void setDistributionCount(String distributionCount) {
                    this.distributionCount = distributionCount;
                }

                public List<Object> getBatchNumPriceVoList() {
                    return batchNumPriceVoList;
                }

                public void setBatchNumPriceVoList(List<Object> batchNumPriceVoList) {
                    this.batchNumPriceVoList = batchNumPriceVoList;
                }
            }
        }
    }
}
