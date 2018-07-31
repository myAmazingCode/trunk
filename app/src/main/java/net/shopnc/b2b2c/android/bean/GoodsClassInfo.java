/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:AdvertList.java
 */
package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品分类Bean
 *
 * @author dqw
 * @Time 2015-7-3
 */
public class GoodsClassInfo {
    private String gcId;
    private String gcName;
    private String child;

    public GoodsClassInfo() {
    }

    public GoodsClassInfo(String gc_id, String gc_name, String child) {
        super();
        this.gcId = gc_id;
        this.gcName = gc_name;
        this.child = child;
    }

    public static ArrayList<GoodsClassInfo> newInstanceList(String jsonDatas) {
        ArrayList<GoodsClassInfo> goodsClassList = new ArrayList<GoodsClassInfo>();

        try {
            JSONArray arr = new JSONArray(jsonDatas);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String gc_id = obj.optString("gc_id");
                String gc_name = obj.optString("gc_name");
                String child = obj.optString("child");
                GoodsClassInfo t = new GoodsClassInfo(gc_id, gc_name, child);
                goodsClassList.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return goodsClassList;
    }

    public String getGcId() {
        return gcId;
    }

    public void setGcId(String gcId) {
        this.gcId = gcId;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "GoodsClass{" +
                "gcId='" + gcId + '\'' +
                ", gcName='" + gcName + '\'' +
                ", child='" + child + '\'' +
                '}';
    }
}
