package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/19 11:51
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 购物车推荐组合item
 */
public class CartBundlingVo {


    /**
     * cartId : 304
     * bundlingId : 11
     * bundlingName : 优惠套装
     * goodsPrice : 8037.2
     * imageName : http://192.168.1.234/upload/image/f9/3a/f93a52fc409ca14320647e9e1243e3fb.jpg
     * buyNum : 1
     * itemAmount : 8037.2
     * goodsStorage : 1996
     * goodsStatus : 1
     * storeId : 23
     * storeName : 美的官方旗舰店
     * storageStatus : 1
     * memberId : 1
     * buyBundlingItemVoList : [{"cartId":304,"goodsId":1018,"commonId":243,"goodsName":"美的冰箱BCD-482WGPM 482升 变频节能 玻璃面板 家用静音十字对开多门电冰箱","goodsFullSpecs":"颜色：铂金；容积：482L","goodsPrice":7199,"imageName":"image/f9/3a/f93a52fc409ca14320647e9e1243e3fb.jpg","buyNum":1,"itemAmount":7199,"goodsStorage":1996,"goodsStatus":1,"storeId":23,"storeName":"美的官方旗舰店","memberId":1,"imageSrc":"http://192.168.1.234/upload/image/f9/3a/f93a52fc409ca14320647e9e1243e3fb.jpg","unitName":"个","spuImageSrc":"http://192.168.1.234/upload/image/f9/3a/f93a52fc409ca14320647e9e1243e3fb.jpg","webPrice0":7499,"appPrice0":7499,"wechatPrice0":7499,"isGift":1,"giftVoList":[{"giftId":171,"goodsId":1042,"commonId":255,"giftNum":1,"giftType":2,"itemId":1018,"itemCommonId":255,"goodsName":"美的(Midea) MJ-BL80Y21 搅拌机营养破壁 营养食疗 一键触控 钛合金破壁刀","unitName":"台","goodsFullSpecs":"颜色：银灰色","imageSrc":"http://192.168.1.234/upload/image/2f/67/2f6776f57d2722d0df3ee7c418f66ebb.jpg"}],"contractItem1":0,"contractItem2":0,"contractItem3":0,"contractItem4":0,"contractItem5":0,"contractItem6":0,"contractItem7":0,"contractItem8":0,"contractItem9":0,"contractItem10":0,"goodsContractVoList":[]},{"cartId":304,"goodsId":1037,"commonId":251,"goodsName":"Midea/美的 12PCH502A 电压力锅 机械式 高压锅 饭煲 家用 多功能","goodsFullSpecs":"颜色：黑晶色；容积：4L","goodsPrice":219,"imageName":"image/96/c7/96c7b56457c8bcc3f05dfd95262fefdc.jpg","buyNum":1,"itemAmount":219,"goodsStorage":2996,"goodsStatus":1,"storeId":23,"storeName":"美的官方旗舰店","memberId":1,"imageSrc":"http://192.168.1.234/upload/image/96/c7/96c7b56457c8bcc3f05dfd95262fefdc.jpg","unitName":"个","spuImageSrc":"http://192.168.1.234/upload/image/96/c7/96c7b56457c8bcc3f05dfd95262fefdc.jpg","webPrice0":259,"appPrice0":259,"wechatPrice0":259,"isGift":0,"giftVoList":[],"contractItem1":0,"contractItem2":0,"contractItem3":0,"contractItem4":0,"contractItem5":0,"contractItem6":0,"contractItem7":0,"contractItem8":0,"contractItem9":0,"contractItem10":0,"goodsContractVoList":[]},{"cartId":304,"goodsId":1044,"commonId":257,"goodsName":"美的（Midea）MJ-WBL1021S 破壁料理机 加热家用全自动多功能料理机搅拌机","goodsFullSpecs":"颜色：银灰色","goodsPrice":619.2,"imageName":"image/d0/fa/d0fa4d8a8f519b4219f691a96ba11767.jpg","buyNum":1,"itemAmount":619.2,"goodsStorage":4987,"goodsStatus":1,"storeId":23,"storeName":"美的官方旗舰店","memberId":1,"imageSrc":"http://192.168.1.234/upload/image/d0/fa/d0fa4d8a8f519b4219f691a96ba11767.jpg","unitName":"台","spuImageSrc":"http://192.168.1.234/upload/image/d0/fa/d0fa4d8a8f519b4219f691a96ba11767.jpg","webPrice0":719.2,"appPrice0":719.2,"wechatPrice0":719.2,"isGift":0,"giftVoList":[],"contractItem1":0,"contractItem2":0,"contractItem3":0,"contractItem4":0,"contractItem5":0,"contractItem6":0,"contractItem7":0,"contractItem8":0,"contractItem9":0,"contractItem10":0,"goodsContractVoList":[]}]
     * startTime : 2017-05-03 14:42:47
     * endTime : 2018-04-28 23:59:59
     */

    private int cartId;
    private int bundlingId;
    private String bundlingName;
    private double goodsPrice;
    private String imageName;
    private int buyNum;
    private double itemAmount;
    private int goodsStorage;
    private int goodsStatus;
    private int storeId;
    private String storeName;
    private int storageStatus;
    private int memberId;
    private String startTime;
    private String endTime;
    private List<BuyBundlingItemVoListBean> buyBundlingItemVoList;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getBundlingId() {
        return bundlingId;
    }

    public void setBundlingId(int bundlingId) {
        this.bundlingId = bundlingId;
    }

    public String getBundlingName() {
        return bundlingName;
    }

    public void setBundlingName(String bundlingName) {
        this.bundlingName = bundlingName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public int getGoodsStorage() {
        return goodsStorage;
    }

    public void setGoodsStorage(int goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
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

    public int getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(int storageStatus) {
        this.storageStatus = storageStatus;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<BuyBundlingItemVoListBean> getBuyBundlingItemVoList() {
        return buyBundlingItemVoList;
    }

    public void setBuyBundlingItemVoList(List<BuyBundlingItemVoListBean> buyBundlingItemVoList) {
        this.buyBundlingItemVoList = buyBundlingItemVoList;
    }

    public static class BuyBundlingItemVoListBean {
        /**
         * cartId : 304
         * goodsId : 1018
         * commonId : 243
         * goodsName : 美的冰箱BCD-482WGPM 482升 变频节能 玻璃面板 家用静音十字对开多门电冰箱
         * goodsFullSpecs : 颜色：铂金；容积：482L
         * goodsPrice : 7199
         * imageName : image/f9/3a/f93a52fc409ca14320647e9e1243e3fb.jpg
         * buyNum : 1
         * itemAmount : 7199
         * goodsStorage : 1996
         * goodsStatus : 1
         * storeId : 23
         * storeName : 美的官方旗舰店
         * memberId : 1
         * imageSrc : http://192.168.1.234/upload/image/f9/3a/f93a52fc409ca14320647e9e1243e3fb.jpg
         * unitName : 个
         * spuImageSrc : http://192.168.1.234/upload/image/f9/3a/f93a52fc409ca14320647e9e1243e3fb.jpg
         * webPrice0 : 7499
         * appPrice0 : 7499
         * wechatPrice0 : 7499
         * isGift : 1
         * giftVoList : [{"giftId":171,"goodsId":1042,"commonId":255,"giftNum":1,"giftType":2,"itemId":1018,"itemCommonId":255,"goodsName":"美的(Midea) MJ-BL80Y21 搅拌机营养破壁 营养食疗 一键触控 钛合金破壁刀","unitName":"台","goodsFullSpecs":"颜色：银灰色","imageSrc":"http://192.168.1.234/upload/image/2f/67/2f6776f57d2722d0df3ee7c418f66ebb.jpg"}]
         * contractItem1 : 0
         * contractItem2 : 0
         * contractItem3 : 0
         * contractItem4 : 0
         * contractItem5 : 0
         * contractItem6 : 0
         * contractItem7 : 0
         * contractItem8 : 0
         * contractItem9 : 0
         * contractItem10 : 0
         * goodsContractVoList : []
         */

        private int cartId;
        private int goodsId;
        private int commonId;
        private String goodsName;
        private String goodsFullSpecs;
        private String imageName;
        private int buyNum;
        private double goodsPrice;
        private double itemAmount;
        private int goodsStorage;
        private int goodsStatus;
        private int storeId;
        private String storeName;
        private int memberId;
        private String imageSrc;
        private String unitName;
        private String spuImageSrc;
        private double webPrice0;
        private double appPrice0;
        private double wechatPrice0;
        private int isGift;
        private int contractItem1;
        private int contractItem2;
        private int contractItem3;
        private int contractItem4;
        private int contractItem5;
        private int contractItem6;
        private int contractItem7;
        private int contractItem8;
        private int contractItem9;
        private int contractItem10;
        private List<GiftVoListBean> giftVoList;
        private List<?> goodsContractVoList;

        public int getCartId() {
            return cartId;
        }

        public void setCartId(int cartId) {
            this.cartId = cartId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
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

        public String getGoodsFullSpecs() {
            return goodsFullSpecs;
        }

        public void setGoodsFullSpecs(String goodsFullSpecs) {
            this.goodsFullSpecs = goodsFullSpecs;
        }

        public double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public double getItemAmount() {
            return itemAmount;
        }

        public void setItemAmount(double itemAmount) {
            this.itemAmount = itemAmount;
        }

        public int getGoodsStorage() {
            return goodsStorage;
        }

        public void setGoodsStorage(int goodsStorage) {
            this.goodsStorage = goodsStorage;
        }

        public int getGoodsStatus() {
            return goodsStatus;
        }

        public void setGoodsStatus(int goodsStatus) {
            this.goodsStatus = goodsStatus;
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

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
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

        public String getSpuImageSrc() {
            return spuImageSrc;
        }

        public void setSpuImageSrc(String spuImageSrc) {
            this.spuImageSrc = spuImageSrc;
        }

        public double getWebPrice0() {
            return webPrice0;
        }

        public void setWebPrice0(double webPrice0) {
            this.webPrice0 = webPrice0;
        }

        public double getAppPrice0() {
            return appPrice0;
        }

        public void setAppPrice0(double appPrice0) {
            this.appPrice0 = appPrice0;
        }

        public double getWechatPrice0() {
            return wechatPrice0;
        }

        public void setWechatPrice0(double wechatPrice0) {
            this.wechatPrice0 = wechatPrice0;
        }

        public int getIsGift() {
            return isGift;
        }

        public void setIsGift(int isGift) {
            this.isGift = isGift;
        }

        public int getContractItem1() {
            return contractItem1;
        }

        public void setContractItem1(int contractItem1) {
            this.contractItem1 = contractItem1;
        }

        public int getContractItem2() {
            return contractItem2;
        }

        public void setContractItem2(int contractItem2) {
            this.contractItem2 = contractItem2;
        }

        public int getContractItem3() {
            return contractItem3;
        }

        public void setContractItem3(int contractItem3) {
            this.contractItem3 = contractItem3;
        }

        public int getContractItem4() {
            return contractItem4;
        }

        public void setContractItem4(int contractItem4) {
            this.contractItem4 = contractItem4;
        }

        public int getContractItem5() {
            return contractItem5;
        }

        public void setContractItem5(int contractItem5) {
            this.contractItem5 = contractItem5;
        }

        public int getContractItem6() {
            return contractItem6;
        }

        public void setContractItem6(int contractItem6) {
            this.contractItem6 = contractItem6;
        }

        public int getContractItem7() {
            return contractItem7;
        }

        public void setContractItem7(int contractItem7) {
            this.contractItem7 = contractItem7;
        }

        public int getContractItem8() {
            return contractItem8;
        }

        public void setContractItem8(int contractItem8) {
            this.contractItem8 = contractItem8;
        }

        public int getContractItem9() {
            return contractItem9;
        }

        public void setContractItem9(int contractItem9) {
            this.contractItem9 = contractItem9;
        }

        public int getContractItem10() {
            return contractItem10;
        }

        public void setContractItem10(int contractItem10) {
            this.contractItem10 = contractItem10;
        }

        public List<GiftVoListBean> getGiftVoList() {
            return giftVoList;
        }

        public void setGiftVoList(List<GiftVoListBean> giftVoList) {
            this.giftVoList = giftVoList;
        }

        public List<?> getGoodsContractVoList() {
            return goodsContractVoList;
        }

        public void setGoodsContractVoList(List<?> goodsContractVoList) {
            this.goodsContractVoList = goodsContractVoList;
        }

        public static class GiftVoListBean {
            /**
             * giftId : 171
             * goodsId : 1042
             * commonId : 255
             * giftNum : 1
             * giftType : 2
             * itemId : 1018
             * itemCommonId : 255
             * goodsName : 美的(Midea) MJ-BL80Y21 搅拌机营养破壁 营养食疗 一键触控 钛合金破壁刀
             * unitName : 台
             * goodsFullSpecs : 颜色：银灰色
             * imageSrc : http://192.168.1.234/upload/image/2f/67/2f6776f57d2722d0df3ee7c418f66ebb.jpg
             */

            private int giftId;
            private int goodsId;
            private int commonId;
            private int giftNum;
            private int giftType;
            private int itemId;
            private int itemCommonId;
            private String goodsName;
            private String unitName;
            private String goodsFullSpecs;
            private String imageSrc;

            public int getGiftId() {
                return giftId;
            }

            public void setGiftId(int giftId) {
                this.giftId = giftId;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getCommonId() {
                return commonId;
            }

            public void setCommonId(int commonId) {
                this.commonId = commonId;
            }

            public int getGiftNum() {
                return giftNum;
            }

            public void setGiftNum(int giftNum) {
                this.giftNum = giftNum;
            }

            public int getGiftType() {
                return giftType;
            }

            public void setGiftType(int giftType) {
                this.giftType = giftType;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public int getItemCommonId() {
                return itemCommonId;
            }

            public void setItemCommonId(int itemCommonId) {
                this.itemCommonId = itemCommonId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getGoodsFullSpecs() {
                return goodsFullSpecs;
            }

            public void setGoodsFullSpecs(String goodsFullSpecs) {
                this.goodsFullSpecs = goodsFullSpecs;
            }

            public String getImageSrc() {
                return imageSrc;
            }

            public void setImageSrc(String imageSrc) {
                this.imageSrc = imageSrc;
            }
        }
    }
}
