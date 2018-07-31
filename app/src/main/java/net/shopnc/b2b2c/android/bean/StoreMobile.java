package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * Created by xws on 2016/11/17.
 */

public class StoreMobile implements Serializable {


    /**
     * storeId : 1
     * storeBanner : imagedev/98/17/9817ae130767e83bc5e15780155915bf.jpg
     * decorationState : 1
     * decorationOnly : 0
     * storeBannerUrl : http://pic5.cnrmall.com/imagedev/98/17/9817ae130767e83bc5e15780155915bf.jpg
     */

    private int storeId;
    private String storeBanner;
    private int decorationState;
    private int decorationOnly;
    private String storeBannerUrl;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreBanner() {
        return storeBanner;
    }

    public void setStoreBanner(String storeBanner) {
        this.storeBanner = storeBanner;
    }

    public int getDecorationState() {
        return decorationState;
    }

    public void setDecorationState(int decorationState) {
        this.decorationState = decorationState;
    }

    public int getDecorationOnly() {
        return decorationOnly;
    }

    public void setDecorationOnly(int decorationOnly) {
        this.decorationOnly = decorationOnly;
    }

    public String getStoreBannerUrl() {
        return storeBannerUrl;
    }

    public void setStoreBannerUrl(String storeBannerUrl) {
        this.storeBannerUrl = storeBannerUrl;
    }

    @Override
    public String toString() {
        return "StoreMobile{" +
                "storeId=" + storeId +
                ", storeBanner='" + storeBanner + '\'' +
                ", decorationState=" + decorationState +
                ", decorationOnly=" + decorationOnly +
                ", storeBannerUrl='" + storeBannerUrl + '\'' +
                '}';
    }
}
