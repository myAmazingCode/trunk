package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 店铺信息
 * @author dqw
 * @Time 2015/8/24
 */

public class StoreInfo {
    public static class Attr {
        public static final String STORE_ID = "store_id";
        public static final String STORE_NAME = "store_name";
        public static final String MEMBER_ID = "member_id";
        public static final String MEMBER_NAME = "member_name";
        public static final String GOODS_COUNT = "goods_count";
        public static final String IS_OWN_SHOP = "is_own_shop";
        public static final String STORE_CREDIT = "store_credit";
    }

    private String storeId;
    private String storeName;
    private String memberId;
    private String memberName;
    private String goodsCount;
    private String isOwnShop;
    private String storeCredit;

    public StoreInfo(String storeId, String storeName, String memberId, String memberName, String goodsCount, String isOwnShop, String storeCredit) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.memberId = memberId;
        this.memberName = memberName;
        this.goodsCount = goodsCount;
        this.isOwnShop = isOwnShop;
        this.storeCredit = storeCredit;
    }

    public static StoreInfo newInstanceList(String json) {
        StoreInfo bean = null;
        try {
            JSONObject obj = new JSONObject(json);
            String storeId = obj.optString(Attr.STORE_ID);
            String storeName = obj.optString(Attr.STORE_NAME);
            String memberId = obj.optString(Attr.MEMBER_ID);
            String memberName = obj.optString(Attr.MEMBER_NAME);
            String goodsCount = obj.optString(Attr.GOODS_COUNT);
            String isOwnShop = obj.optString(Attr.IS_OWN_SHOP);
            String storeCredit = obj.optString(Attr.STORE_CREDIT);
            bean = new StoreInfo(storeId, storeName, memberId, memberName, goodsCount, isOwnShop, storeCredit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
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

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getIsOwnShop() {
        return isOwnShop;
    }

    public void setIsOwnShop(String isOwnShop) {
        this.isOwnShop = isOwnShop;
    }

    public String getStoreCredit() {
        return storeCredit;
    }

    public void setStoreCredit(String storeCredit) {
        this.storeCredit = storeCredit;
    }

    @Override
    public String toString() {
        return "StoreInfo{" +
                "storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", goodsCount='" + goodsCount + '\'' +
                ", isOwnShop='" + isOwnShop + '\'' +
                ", storeCredit='" + storeCredit + '\'' +
                '}';
    }
}
