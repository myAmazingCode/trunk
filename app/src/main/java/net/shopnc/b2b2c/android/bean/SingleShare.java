package net.shopnc.b2b2c.android.bean;

/**
 * Created by xws on 2017/2/17.
 */

public class SingleShare {


    /**
     * code : 200
     * datas : {"distributionGoods":{"distributorGoodsId":356,"commonId":179,"storeId":6,"storeName":"华为旗舰店","distributorId":7,"memberId":30,"memberName":"lzz123","addTime":"2017-02-15 14:00:44","distributionCount":0,"distributorFavoritesId":35,"goodsImageSrc":"https://java.bizpower.com/upload/image/84/bc/84bcec6131b9f51e2e0532e6012837f9.jpg","goodsImageName":"","goodsName":"华为（HUAWEI） WS550 450M无线路由器","wapShareUrl":"https://java.bizpower.com/wap/tmpl/product_detail.html?commonId=179&distributionGoodsId=356"}}
     */

    private int code;
    /**
     * distributionGoods : {"distributorGoodsId":356,"commonId":179,"storeId":6,"storeName":"华为旗舰店","distributorId":7,"memberId":30,"memberName":"lzz123","addTime":"2017-02-15 14:00:44","distributionCount":0,"distributorFavoritesId":35,"goodsImageSrc":"https://java.bizpower.com/upload/image/84/bc/84bcec6131b9f51e2e0532e6012837f9.jpg","goodsImageName":"","goodsName":"华为（HUAWEI） WS550 450M无线路由器","wapShareUrl":"https://java.bizpower.com/wap/tmpl/product_detail.html?commonId=179&distributionGoodsId=356"}
     */

    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * distributorGoodsId : 356
         * commonId : 179
         * storeId : 6
         * storeName : 华为旗舰店
         * distributorId : 7
         * memberId : 30
         * memberName : lzz123
         * addTime : 2017-02-15 14:00:44
         * distributionCount : 0
         * distributorFavoritesId : 35
         * goodsImageSrc : https://java.bizpower.com/upload/image/84/bc/84bcec6131b9f51e2e0532e6012837f9.jpg
         * goodsImageName :
         * goodsName : 华为（HUAWEI） WS550 450M无线路由器
         * wapShareUrl : https://java.bizpower.com/wap/tmpl/product_detail.html?commonId=179&distributionGoodsId=356
         */

        private DistributionGoodsBean distributionGoods;

        public DistributionGoodsBean getDistributionGoods() {
            return distributionGoods;
        }

        public void setDistributionGoods(DistributionGoodsBean distributionGoods) {
            this.distributionGoods = distributionGoods;
        }

        public static class DistributionGoodsBean {
            private int distributorGoodsId;
            private int commonId;
            private int storeId;
            private String storeName;
            private int distributorId;
            private int memberId;
            private String memberName;
            private String addTime;
            private int distributionCount;
            private int distributorFavoritesId;
            private String goodsImageSrc;
            private String goodsImageName;
            private String goodsName;
            private String wapShareUrl;

            public int getDistributorGoodsId() {
                return distributorGoodsId;
            }

            public void setDistributorGoodsId(int distributorGoodsId) {
                this.distributorGoodsId = distributorGoodsId;
            }

            public int getCommonId() {
                return commonId;
            }

            public void setCommonId(int commonId) {
                this.commonId = commonId;
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

            public int getDistributorId() {
                return distributorId;
            }

            public void setDistributorId(int distributorId) {
                this.distributorId = distributorId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public int getDistributionCount() {
                return distributionCount;
            }

            public void setDistributionCount(int distributionCount) {
                this.distributionCount = distributionCount;
            }

            public int getDistributorFavoritesId() {
                return distributorFavoritesId;
            }

            public void setDistributorFavoritesId(int distributorFavoritesId) {
                this.distributorFavoritesId = distributorFavoritesId;
            }

            public String getGoodsImageSrc() {
                return goodsImageSrc;
            }

            public void setGoodsImageSrc(String goodsImageSrc) {
                this.goodsImageSrc = goodsImageSrc;
            }

            public String getGoodsImageName() {
                return goodsImageName;
            }

            public void setGoodsImageName(String goodsImageName) {
                this.goodsImageName = goodsImageName;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getWapShareUrl() {
                return wapShareUrl;
            }

            public void setWapShareUrl(String wapShareUrl) {
                this.wapShareUrl = wapShareUrl;
            }
        }
    }
}
