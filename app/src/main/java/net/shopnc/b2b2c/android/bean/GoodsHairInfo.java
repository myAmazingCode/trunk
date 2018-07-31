package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 商品配送区域Bean
 */
public class GoodsHairInfo {

    //运费信息
    private String content;
    //是否配送
    private String ifStore;
    //是否配送文字
    private String ifStoreCn;
    //地区描述
    private String areaName;

    public GoodsHairInfo(String content, String ifStore, String ifStoreCn, String areaName) {
        this.content = content;
        this.ifStore = ifStore;
        this.ifStoreCn = ifStoreCn;
        this.areaName = areaName;
    }

    public static GoodsHairInfo newInstance(String json) {
        String content = "", ifStore = "", ifStoreCn = "", areaName = "";
        try {
            JSONObject obj = new JSONObject(json);
            content = obj.optString("content");
            ifStore = obj.optString("if_store");
            ifStoreCn = obj.optString("if_store_cn");
            areaName = obj.optString("area_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new GoodsHairInfo(content, ifStore, ifStoreCn, areaName);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIfStore() {
        return ifStore;
    }

    public void setIfStore(String ifStore) {
        this.ifStore = ifStore;
    }

    public String getIfStoreCn() {
        return ifStoreCn;
    }

    public void setIfStoreCn(String ifStoreCn) {
        this.ifStoreCn = ifStoreCn;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "GoodsHairInfo{" +
                "content='" + content + '\'' +
                ", ifStore='" + ifStore + '\'' +
                ", ifStoreCn='" + ifStoreCn + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
