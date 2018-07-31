package net.shopnc.b2b2c.android.bean;

import net.shopnc.b2b2c.android.common.Markable;

import java.util.List;

/**
 * Created by xws on 2017/2/6.
 */

public class PromotionSearch {


    private String code;

    private PromotionSearch.DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PromotionSearch.DatasBean getDatas() {
        return datas;
    }

    public void setDatas(PromotionSearch.DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {


        private PromotionSearch.DatasBean.FilterBean filter;
        /**
         * hasMore : false
         * totalPage : 1
         */

        private PromotionSearch.DatasBean.PageEntityBean pageEntity;

        private List<PromotionSearch.DatasBean.GoodsListBean> goodsList;

        public PromotionSearch.DatasBean.FilterBean getFilter() {
            return filter;
        }

        public void setFilter(PromotionSearch.DatasBean.FilterBean filter) {
            this.filter = filter;
        }

        public PromotionSearch.DatasBean.PageEntityBean getPageEntity() {
            return pageEntity;
        }

        public void setPageEntity(PromotionSearch.DatasBean.PageEntityBean pageEntity) {
            this.pageEntity = pageEntity;
        }

        public List<PromotionSearch.DatasBean.GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<PromotionSearch.DatasBean.GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class FilterBean {
            private String gift;
            private String voucher;
            private String own;
            private String express;
            /**
             * sort : desc
             * order : commission
             */

            private PromotionSearch.DatasBean.FilterBean.SortBean sort;
            private String sellNum;
            private String promotion;
            /**
             * categoryId : 1035
             * categoryName : 手机
             * parentId : 0
             * categorySort : 0
             * deep : 0
             * appImage :
             * appImageUrl : https://java.bizpower.com/public/img/default_category_app_image.png
             */

            private List<PromotionSearch.DatasBean.FilterBean.CategoryListBean> categoryList;
            /**
             * categoryId : 256
             * categoryName : 数码办公
             * parentId : 0
             * categorySort : 2
             * deep : 1
             * appImage : null
             * appImageUrl : null
             * categoryList : [{"categoryId":1,"categoryName":"服饰鞋帽","parentId":0,"categorySort":1,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":256,"categoryName":"数码办公","parentId":0,"categorySort":2,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":2,"categoryName":"礼品箱包","parentId":0,"categorySort":3,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":3,"categoryName":"家居家装","parentId":0,"categorySort":4,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":888,"categoryName":"厨房餐饮","parentId":0,"categorySort":5,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":470,"categoryName":"个护化妆","parentId":0,"categorySort":6,"deep":1,"appImage":"image/2b/b9/2bb9f8de7312248c7c9238fb3042ef98.png","appImageUrl":"https://java.bizpower.com/upload/image/2b/b9/2bb9f8de7312248c7c9238fb3042ef98.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":593,"categoryName":"食品饮料","parentId":0,"categorySort":7,"deep":1,"appImage":"image/0d/ae/0daef4879fd6ab6cd5817a4961f94eef.png","appImageUrl":"https://java.bizpower.com/upload/image/0d/ae/0daef4879fd6ab6cd5817a4961f94eef.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":530,"categoryName":"珠宝手表","parentId":0,"categorySort":8,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":730,"categoryName":"汽车用品","parentId":0,"categorySort":8,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":308,"categoryName":"家用电器","parentId":0,"categorySort":9,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":662,"categoryName":"运动健康","parentId":0,"categorySort":11,"deep":1,"appImage":"","appImageUrl":"https://java.bizpower.com/public/img/default_category_app_image.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":825,"categoryName":"玩具乐器","parentId":0,"categorySort":12,"deep":1,"appImage":"image/06/01/0601d7579c3bc51f843e0a1a2597c66d.png","appImageUrl":"https://java.bizpower.com/upload/image/06/01/0601d7579c3bc51f843e0a1a2597c66d.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":959,"categoryName":"母婴用品","parentId":0,"categorySort":13,"deep":1,"appImage":"image/e3/fd/e3fdd20540e11279ea8c2008189d7215.png","appImageUrl":"https://java.bizpower.com/upload/image/e3/fd/e3fdd20540e11279ea8c2008189d7215.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null},{"categoryId":1037,"categoryName":"虚拟充值","parentId":0,"categorySort":14,"deep":1,"appImage":"image/86/23/8623487d2833c2066bd6f5a222e7937e.png","appImageUrl":"https://java.bizpower.com/upload/image/86/23/8623487d2833c2066bd6f5a222e7937e.png","categoryList":[],"channelId":0,"category":null,"indexCategoryNav":null}]
             * channelId : 0
             * category : null
             * indexCategoryNav : null
             */

            private List<PromotionSearch.DatasBean.FilterBean.CategoryCrumbsListBean> categoryCrumbsList;
            private List<Object> searchCheckedFilterList;
            /**
             * attributeId : 3557
             * attributeName : 价格区间
             * categoryId : 1035
             * attributeSort : 0
             * isShow : 1
             * attributeValueNames : 0-200，201-400，401-600，601-800，801-1000，1001以上
             * attributeValueList : [{"attributeValueId":16892,"attributeValueName":"0-200"},{"attributeValueId":16893,"attributeValueName":"201-400"},{"attributeValueId":16894,"attributeValueName":"401-600"},{"attributeValueId":16895,"attributeValueName":"601-800"},{"attributeValueId":16896,"attributeValueName":"801-1000"},{"attributeValueId":16897,"attributeValueName":"1001以上"}]
             */

            private List<PromotionSearch.DatasBean.FilterBean.AttributeListBean> attributeList;
            /**
             * brandId : 360
             * brandName : 华为
             * brandEnglish : HUAWEI
             * brandInitial : H
             * brandImage : image/e4/57/e4573dcb9d087745fdb4f75f95d05a14.jpg
             * brandImageSrc : https://java.bizpower.com/upload/image/e4/57/e4573dcb9d087745fdb4f75f95d05a14.jpg
             * storeId : 0
             * brandSort : 0
             * isRecommend : 1
             * applyState : 1
             * showType : 1
             */

            private List<PromotionSearch.DatasBean.FilterBean.BrandListBean> brandList;

            public String getGift() {
                return gift;
            }

            public void setGift(String gift) {
                this.gift = gift;
            }

            public String getVoucher() {
                return voucher;
            }

            public void setVoucher(String voucher) {
                this.voucher = voucher;
            }

            public String getOwn() {
                return own;
            }

            public void setOwn(String own) {
                this.own = own;
            }

            public String getExpress() {
                return express;
            }

            public void setExpress(String express) {
                this.express = express;
            }

            public PromotionSearch.DatasBean.FilterBean.SortBean getSort() {
                return sort;
            }

            public void setSort(PromotionSearch.DatasBean.FilterBean.SortBean sort) {
                this.sort = sort;
            }

            public String getSellNum() {
                return sellNum;
            }

            public void setSellNum(String sellNum) {
                this.sellNum = sellNum;
            }

            public String getPromotion() {
                return promotion;
            }

            public void setPromotion(String promotion) {
                this.promotion = promotion;
            }

            public List<PromotionSearch.DatasBean.FilterBean.CategoryListBean> getCategoryList() {
                return categoryList;
            }

            public void setCategoryList(List<PromotionSearch.DatasBean.FilterBean.CategoryListBean> categoryList) {
                this.categoryList = categoryList;
            }

            public List<PromotionSearch.DatasBean.FilterBean.CategoryCrumbsListBean> getCategoryCrumbsList() {
                return categoryCrumbsList;
            }

            public void setCategoryCrumbsList(List<PromotionSearch.DatasBean.FilterBean.CategoryCrumbsListBean> categoryCrumbsList) {
                this.categoryCrumbsList = categoryCrumbsList;
            }

            public List<Object> getSearchCheckedFilterList() {
                return searchCheckedFilterList;
            }

            public void setSearchCheckedFilterList(List<Object> searchCheckedFilterList) {
                this.searchCheckedFilterList = searchCheckedFilterList;
            }

            public List<PromotionSearch.DatasBean.FilterBean.AttributeListBean> getAttributeList() {
                return attributeList;
            }

            public void setAttributeList(List<PromotionSearch.DatasBean.FilterBean.AttributeListBean> attributeList) {
                this.attributeList = attributeList;
            }

            public List<PromotionSearch.DatasBean.FilterBean.BrandListBean> getBrandList() {
                return brandList;
            }

            public void setBrandList(List<PromotionSearch.DatasBean.FilterBean.BrandListBean> brandList) {
                this.brandList = brandList;
            }

            public static class SortBean {
                private String sort;
                private String order;

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getOrder() {
                    return order;
                }

                public void setOrder(String order) {
                    this.order = order;
                }
            }

            public static class CategoryListBean {
                private String categoryId;
                private String categoryName;
                private String parentId;
                private String categorySort;
                private String deep;
                private String appImage;
                private String appImageUrl;

                public String getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(String categoryId) {
                    this.categoryId = categoryId;
                }

                public String getCategoryName() {
                    return categoryName;
                }

                public void setCategoryName(String categoryName) {
                    this.categoryName = categoryName;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public String getCategorySort() {
                    return categorySort;
                }

                public void setCategorySort(String categorySort) {
                    this.categorySort = categorySort;
                }

                public String getDeep() {
                    return deep;
                }

                public void setDeep(String deep) {
                    this.deep = deep;
                }

                public String getAppImage() {
                    return appImage;
                }

                public void setAppImage(String appImage) {
                    this.appImage = appImage;
                }

                public String getAppImageUrl() {
                    return appImageUrl;
                }

                public void setAppImageUrl(String appImageUrl) {
                    this.appImageUrl = appImageUrl;
                }
            }

            public static class CategoryCrumbsListBean {
                private String categoryId;
                private String categoryName;
                private String parentId;
                private String categorySort;
                private String deep;
                private String appImage;
                private String appImageUrl;
                private String channelId;
                private String category;
                private String indexCategoryNav;
                /**
                 * categoryId : 1
                 * categoryName : 服饰鞋帽
                 * parentId : 0
                 * categorySort : 1
                 * deep : 1
                 * appImage :
                 * appImageUrl : https://java.bizpower.com/public/img/default_category_app_image.png
                 * categoryList : []
                 * channelId : 0
                 * category : null
                 * indexCategoryNav : null
                 */

                private List<PromotionSearch.DatasBean.FilterBean.CategoryCrumbsListBean.CategoryListBean> categoryList;

                public String getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(String categoryId) {
                    this.categoryId = categoryId;
                }

                public String getCategoryName() {
                    return categoryName;
                }

                public void setCategoryName(String categoryName) {
                    this.categoryName = categoryName;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public String getCategorySort() {
                    return categorySort;
                }

                public void setCategorySort(String categorySort) {
                    this.categorySort = categorySort;
                }

                public String getDeep() {
                    return deep;
                }

                public void setDeep(String deep) {
                    this.deep = deep;
                }

                public String getAppImage() {
                    return appImage;
                }

                public void setAppImage(String appImage) {
                    this.appImage = appImage;
                }

                public String getAppImageUrl() {
                    return appImageUrl;
                }

                public void setAppImageUrl(String appImageUrl) {
                    this.appImageUrl = appImageUrl;
                }

                public String getChannelId() {
                    return channelId;
                }

                public void setChannelId(String channelId) {
                    this.channelId = channelId;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getIndexCategoryNav() {
                    return indexCategoryNav;
                }

                public void setIndexCategoryNav(String indexCategoryNav) {
                    this.indexCategoryNav = indexCategoryNav;
                }

                public List<PromotionSearch.DatasBean.FilterBean.CategoryCrumbsListBean.CategoryListBean> getCategoryList() {
                    return categoryList;
                }

                public void setCategoryList(List<PromotionSearch.DatasBean.FilterBean.CategoryCrumbsListBean.CategoryListBean> categoryList) {
                    this.categoryList = categoryList;
                }

                public static class CategoryListBean {
                    private String categoryId;
                    private String categoryName;
                    private String parentId;
                    private String categorySort;
                    private String deep;
                    private String appImage;
                    private String appImageUrl;
                    private String channelId;
                    private String category;
                    private String indexCategoryNav;
                    private List<?> categoryList;

                    public String getCategoryId() {
                        return categoryId;
                    }

                    public void setCategoryId(String categoryId) {
                        this.categoryId = categoryId;
                    }

                    public String getCategoryName() {
                        return categoryName;
                    }

                    public void setCategoryName(String categoryName) {
                        this.categoryName = categoryName;
                    }

                    public String getParentId() {
                        return parentId;
                    }

                    public void setParentId(String parentId) {
                        this.parentId = parentId;
                    }

                    public String getCategorySort() {
                        return categorySort;
                    }

                    public void setCategorySort(String categorySort) {
                        this.categorySort = categorySort;
                    }

                    public String getDeep() {
                        return deep;
                    }

                    public void setDeep(String deep) {
                        this.deep = deep;
                    }

                    public String getAppImage() {
                        return appImage;
                    }

                    public void setAppImage(String appImage) {
                        this.appImage = appImage;
                    }

                    public String getAppImageUrl() {
                        return appImageUrl;
                    }

                    public void setAppImageUrl(String appImageUrl) {
                        this.appImageUrl = appImageUrl;
                    }

                    public String getChannelId() {
                        return channelId;
                    }

                    public void setChannelId(String channelId) {
                        this.channelId = channelId;
                    }

                    public String getCategory() {
                        return category;
                    }

                    public void setCategory(String category) {
                        this.category = category;
                    }

                    public String getIndexCategoryNav() {
                        return indexCategoryNav;
                    }

                    public void setIndexCategoryNav(String indexCategoryNav) {
                        this.indexCategoryNav = indexCategoryNav;
                    }

                    public List<?> getCategoryList() {
                        return categoryList;
                    }

                    public void setCategoryList(List<?> categoryList) {
                        this.categoryList = categoryList;
                    }
                }
            }

            public static class AttributeListBean {
                private String attributeId;
                private String attributeName;
                private String categoryId;
                private String attributeSort;
                private String isShow;
                private String attributeValueNames;
                /**
                 * attributeValueId : 16892
                 * attributeValueName : 0-200
                 */

                private List<PromotionSearch.DatasBean.FilterBean.AttributeListBean.AttributeValueListBean> attributeValueList;

                public String getAttributeId() {
                    return attributeId;
                }

                public void setAttributeId(String attributeId) {
                    this.attributeId = attributeId;
                }

                public String getAttributeName() {
                    return attributeName;
                }

                public void setAttributeName(String attributeName) {
                    this.attributeName = attributeName;
                }

                public String getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(String categoryId) {
                    this.categoryId = categoryId;
                }

                public String getAttributeSort() {
                    return attributeSort;
                }

                public void setAttributeSort(String attributeSort) {
                    this.attributeSort = attributeSort;
                }

                public String getIsShow() {
                    return isShow;
                }

                public void setIsShow(String isShow) {
                    this.isShow = isShow;
                }

                public String getAttributeValueNames() {
                    return attributeValueNames;
                }

                public void setAttributeValueNames(String attributeValueNames) {
                    this.attributeValueNames = attributeValueNames;
                }

                public List<PromotionSearch.DatasBean.FilterBean.AttributeListBean.AttributeValueListBean> getAttributeValueList() {
                    return attributeValueList;
                }

                public void setAttributeValueList(List<PromotionSearch.DatasBean.FilterBean.AttributeListBean.AttributeValueListBean> attributeValueList) {
                    this.attributeValueList = attributeValueList;
                }

                public static class AttributeValueListBean {
                    private String attributeValueId;
                    private String attributeValueName;

                    public String getAttributeValueId() {
                        return attributeValueId;
                    }

                    public void setAttributeValueId(String attributeValueId) {
                        this.attributeValueId = attributeValueId;
                    }

                    public String getAttributeValueName() {
                        return attributeValueName;
                    }

                    public void setAttributeValueName(String attributeValueName) {
                        this.attributeValueName = attributeValueName;
                    }
                }
            }

            public static class BrandListBean {
                private String brandId;
                private String brandName;
                private String brandEnglish;
                private String brandInitial;
                private String brandImage;
                private String brandImageSrc;
                private String storeId;
                private String brandSort;
                private String isRecommend;
                private String applyState;
                private String showType;

                public String getBrandId() {
                    return brandId;
                }

                public void setBrandId(String brandId) {
                    this.brandId = brandId;
                }

                public String getBrandName() {
                    return brandName;
                }

                public void setBrandName(String brandName) {
                    this.brandName = brandName;
                }

                public String getBrandEnglish() {
                    return brandEnglish;
                }

                public void setBrandEnglish(String brandEnglish) {
                    this.brandEnglish = brandEnglish;
                }

                public String getBrandInitial() {
                    return brandInitial;
                }

                public void setBrandInitial(String brandInitial) {
                    this.brandInitial = brandInitial;
                }

                public String getBrandImage() {
                    return brandImage;
                }

                public void setBrandImage(String brandImage) {
                    this.brandImage = brandImage;
                }

                public String getBrandImageSrc() {
                    return brandImageSrc;
                }

                public void setBrandImageSrc(String brandImageSrc) {
                    this.brandImageSrc = brandImageSrc;
                }

                public String getStoreId() {
                    return storeId;
                }

                public void setStoreId(String storeId) {
                    this.storeId = storeId;
                }

                public String getBrandSort() {
                    return brandSort;
                }

                public void setBrandSort(String brandSort) {
                    this.brandSort = brandSort;
                }

                public String getIsRecommend() {
                    return isRecommend;
                }

                public void setIsRecommend(String isRecommend) {
                    this.isRecommend = isRecommend;
                }

                public String getApplyState() {
                    return applyState;
                }

                public void setApplyState(String applyState) {
                    this.applyState = applyState;
                }

                public String getShowType() {
                    return showType;
                }

                public void setShowType(String showType) {
                    this.showType = showType;
                }
            }
        }

        public static class PageEntityBean {
            private boolean hasMore;
            private String totalPage;

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public String getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(String totalPage) {
                this.totalPage = totalPage;
            }
        }

        public static class GoodsListBean extends Markable {
            private String commonId;
            private String goodsName;
            private String jingle;
            private String categoryId;
            private String storeId;
            private String brandId;
            private String goodsState;
            private String goodsVerify;
            private String goodsStatus;
            private String areaInfo;
            private String goodsFreight;
            private String freightTemplateId;
            private String batchNum0;
            private String batchNum0End;
            private String batchNum1;
            private String batchNum1End;
            private String batchNum2;
            private String batchPrice0;
            private String batchPrice1;
            private String batchPrice2;
            private String webPrice0;
            private String webPrice1;
            private String webPrice2;
            private String webPriceMin;
            private String webUsable;
            private String appPrice0;
            private String appPrice1;
            private String appPrice2;
            private String appPriceMin;
            private String appUsable;
            private String wechatPrice0;
            private String wechatPrice1;
            private String wechatPrice2;
            private String wechatPriceMin;
            private String wechatUsable;
            private String unitName;
            private String promotionId;
            private String promotionStartTime;
            private String promotionEndTime;
            private String promotionState;
            private String promotionType;
            private String promotionTypeText;
            private String goodsModal;
            private String goodsFavorite;
            private String evaluateNum;
            private String goodsRate;
            private String goodsSaleNum;
            private String imageName;
            private String imageSrc;
            private String isGift;
            private String categoryName;
            private String storeName;
            private String isOwnShop;
            private String sellerId;
            private String commissionRate;
            private double webCommission;
            private double appCommission;
            private double wechatCommission;
            private String usableVoucher;
            private String ordersCount;
            private double commissionTotal;
            private String isOnline;
            /**
             * imageId : 3938
             * commonId : 174
             * colorId : 15
             * imageName : image/cd/6c/cd6c3c180e7a7180cd5bdc7cd7bfa09c.jpg
             * imageSort : 0
             * isDefault : 1
             * imageSrc : https://java.bizpower.com/upload/image/cd/6c/cd6c3c180e7a7180cd5bdc7cd7bfa09c.jpg
             */

            private List<PromotionSearch.DatasBean.GoodsListBean.GoodsImageListBean> goodsImageList;
            private List<?> goodsList;
            /**
             * num : ≥1
             * price : 1599.00
             */

            private List<PromotionSearch.DatasBean.GoodsListBean.BatchNumPriceVoListBean> batchNumPriceVoList;

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

            public String getJingle() {
                return jingle;
            }

            public void setJingle(String jingle) {
                this.jingle = jingle;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getBrandId() {
                return brandId;
            }

            public void setBrandId(String brandId) {
                this.brandId = brandId;
            }

            public String getGoodsState() {
                return goodsState;
            }

            public void setGoodsState(String goodsState) {
                this.goodsState = goodsState;
            }

            public String getGoodsVerify() {
                return goodsVerify;
            }

            public void setGoodsVerify(String goodsVerify) {
                this.goodsVerify = goodsVerify;
            }

            public String getGoodsStatus() {
                return goodsStatus;
            }

            public void setGoodsStatus(String goodsStatus) {
                this.goodsStatus = goodsStatus;
            }

            public String getAreaInfo() {
                return areaInfo;
            }

            public void setAreaInfo(String areaInfo) {
                this.areaInfo = areaInfo;
            }

            public String getGoodsFreight() {
                return goodsFreight;
            }

            public void setGoodsFreight(String goodsFreight) {
                this.goodsFreight = goodsFreight;
            }

            public String getFreightTemplateId() {
                return freightTemplateId;
            }

            public void setFreightTemplateId(String freightTemplateId) {
                this.freightTemplateId = freightTemplateId;
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

            public String getBatchPrice2() {
                return batchPrice2;
            }

            public void setBatchPrice2(String batchPrice2) {
                this.batchPrice2 = batchPrice2;
            }

            public String getWebPrice0() {
                return webPrice0;
            }

            public void setWebPrice0(String webPrice0) {
                this.webPrice0 = webPrice0;
            }

            public String getWebPrice1() {
                return webPrice1;
            }

            public void setWebPrice1(String webPrice1) {
                this.webPrice1 = webPrice1;
            }

            public String getWebPrice2() {
                return webPrice2;
            }

            public void setWebPrice2(String webPrice2) {
                this.webPrice2 = webPrice2;
            }

            public String getWebPriceMin() {
                return webPriceMin;
            }

            public void setWebPriceMin(String webPriceMin) {
                this.webPriceMin = webPriceMin;
            }

            public String getWebUsable() {
                return webUsable;
            }

            public void setWebUsable(String webUsable) {
                this.webUsable = webUsable;
            }

            public String getAppPrice0() {
                return appPrice0;
            }

            public void setAppPrice0(String appPrice0) {
                this.appPrice0 = appPrice0;
            }

            public String getAppPrice1() {
                return appPrice1;
            }

            public void setAppPrice1(String appPrice1) {
                this.appPrice1 = appPrice1;
            }

            public String getAppPrice2() {
                return appPrice2;
            }

            public void setAppPrice2(String appPrice2) {
                this.appPrice2 = appPrice2;
            }

            public String getAppPriceMin() {
                return appPriceMin;
            }

            public void setAppPriceMin(String appPriceMin) {
                this.appPriceMin = appPriceMin;
            }

            public String getAppUsable() {
                return appUsable;
            }

            public void setAppUsable(String appUsable) {
                this.appUsable = appUsable;
            }

            public String getWechatPrice0() {
                return wechatPrice0;
            }

            public void setWechatPrice0(String wechatPrice0) {
                this.wechatPrice0 = wechatPrice0;
            }

            public String getWechatPrice1() {
                return wechatPrice1;
            }

            public void setWechatPrice1(String wechatPrice1) {
                this.wechatPrice1 = wechatPrice1;
            }

            public String getWechatPrice2() {
                return wechatPrice2;
            }

            public void setWechatPrice2(String wechatPrice2) {
                this.wechatPrice2 = wechatPrice2;
            }

            public String getWechatPriceMin() {
                return wechatPriceMin;
            }

            public void setWechatPriceMin(String wechatPriceMin) {
                this.wechatPriceMin = wechatPriceMin;
            }

            public String getWechatUsable() {
                return wechatUsable;
            }

            public void setWechatUsable(String wechatUsable) {
                this.wechatUsable = wechatUsable;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getPromotionId() {
                return promotionId;
            }

            public void setPromotionId(String promotionId) {
                this.promotionId = promotionId;
            }

            public String getPromotionStartTime() {
                return promotionStartTime;
            }

            public void setPromotionStartTime(String promotionStartTime) {
                this.promotionStartTime = promotionStartTime;
            }

            public String getPromotionEndTime() {
                return promotionEndTime;
            }

            public void setPromotionEndTime(String promotionEndTime) {
                this.promotionEndTime = promotionEndTime;
            }

            public String getPromotionState() {
                return promotionState;
            }

            public void setPromotionState(String promotionState) {
                this.promotionState = promotionState;
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

            public String getGoodsFavorite() {
                return goodsFavorite;
            }

            public void setGoodsFavorite(String goodsFavorite) {
                this.goodsFavorite = goodsFavorite;
            }

            public String getEvaluateNum() {
                return evaluateNum;
            }

            public void setEvaluateNum(String evaluateNum) {
                this.evaluateNum = evaluateNum;
            }

            public String getGoodsRate() {
                return goodsRate;
            }

            public void setGoodsRate(String goodsRate) {
                this.goodsRate = goodsRate;
            }

            public String getGoodsSaleNum() {
                return goodsSaleNum;
            }

            public void setGoodsSaleNum(String goodsSaleNum) {
                this.goodsSaleNum = goodsSaleNum;
            }

            public String getImageName() {
                return imageName;
            }

            public void setImageName(String imageName) {
                this.imageName = imageName;
            }

            public String getImageSrc() {
                return imageSrc;
            }

            public void setImageSrc(String imageSrc) {
                this.imageSrc = imageSrc;
            }

            public String getIsGift() {
                return isGift;
            }

            public void setIsGift(String isGift) {
                this.isGift = isGift;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getIsOwnShop() {
                return isOwnShop;
            }

            public void setIsOwnShop(String isOwnShop) {
                this.isOwnShop = isOwnShop;
            }

            public String getSellerId() {
                return sellerId;
            }

            public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
            }

            public String getCommissionRate() {
                return commissionRate;
            }

            public void setCommissionRate(String commissionRate) {
                this.commissionRate = commissionRate;
            }

            public double getWebCommission() {
                return webCommission;
            }

            public void setWebCommission(double webCommission) {
                this.webCommission = webCommission;
            }

            public double getAppCommission() {
                return appCommission;
            }

            public void setAppCommission(double appCommission) {
                this.appCommission = appCommission;
            }

            public double getWechatCommission() {
                return wechatCommission;
            }

            public void setWechatCommission(double wechatCommission) {
                this.wechatCommission = wechatCommission;
            }

            public String getUsableVoucher() {
                return usableVoucher;
            }

            public void setUsableVoucher(String usableVoucher) {
                this.usableVoucher = usableVoucher;
            }

            public String getOrdersCount() {
                return ordersCount;
            }

            public void setOrdersCount(String ordersCount) {
                this.ordersCount = ordersCount;
            }

            public double getCommissionTotal() {
                return commissionTotal;
            }

            public void setCommissionTotal(double commissionTotal) {
                this.commissionTotal = commissionTotal;
            }

            public String getIsOnline() {
                return isOnline;
            }

            public void setIsOnline(String isOnline) {
                this.isOnline = isOnline;
            }

            public List<PromotionSearch.DatasBean.GoodsListBean.GoodsImageListBean> getGoodsImageList() {
                return goodsImageList;
            }

            public void setGoodsImageList(List<PromotionSearch.DatasBean.GoodsListBean.GoodsImageListBean> goodsImageList) {
                this.goodsImageList = goodsImageList;
            }

            public List<?> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<?> goodsList) {
                this.goodsList = goodsList;
            }

            public List<PromotionSearch.DatasBean.GoodsListBean.BatchNumPriceVoListBean> getBatchNumPriceVoList() {
                return batchNumPriceVoList;
            }

            public void setBatchNumPriceVoList(List<PromotionSearch.DatasBean.GoodsListBean.BatchNumPriceVoListBean> batchNumPriceVoList) {
                this.batchNumPriceVoList = batchNumPriceVoList;
            }

            public static class GoodsImageListBean {
                private String imageId;
                private String commonId;
                private String colorId;
                private String imageName;
                private String imageSort;
                private String isDefault;
                private String imageSrc;

                public String getImageId() {
                    return imageId;
                }

                public void setImageId(String imageId) {
                    this.imageId = imageId;
                }

                public String getCommonId() {
                    return commonId;
                }

                public void setCommonId(String commonId) {
                    this.commonId = commonId;
                }

                public String getColorId() {
                    return colorId;
                }

                public void setColorId(String colorId) {
                    this.colorId = colorId;
                }

                public String getImageName() {
                    return imageName;
                }

                public void setImageName(String imageName) {
                    this.imageName = imageName;
                }

                public String getImageSort() {
                    return imageSort;
                }

                public void setImageSort(String imageSort) {
                    this.imageSort = imageSort;
                }

                public String getIsDefault() {
                    return isDefault;
                }

                public void setIsDefault(String isDefault) {
                    this.isDefault = isDefault;
                }

                public String getImageSrc() {
                    return imageSrc;
                }

                public void setImageSrc(String imageSrc) {
                    this.imageSrc = imageSrc;
                }
            }

            public static class BatchNumPriceVoListBean {
                private String num;
                private String price;

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }
    }
}
