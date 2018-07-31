package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 品牌列表
 *
 */
public class BrandInfo {
    private String brandId;
    private String brandName;
    private String brandPic;

    public BrandInfo(String brandId, String brandName, String brandPic) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandPic = brandPic;
    }

    public static ArrayList<BrandInfo> newInstanceList(String jsonDatas) {
        ArrayList<BrandInfo> brandArray = new ArrayList<BrandInfo>();

        try {
            JSONArray arr = new JSONArray(jsonDatas);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String brandId = obj.optString("brand_id");
                String brandName = obj.optString("brand_name");
                String brandPic = obj.optString("brand_pic");
                brandArray.add(new BrandInfo(brandId, brandName, brandPic));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return brandArray;
    }

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

    public String getBrandPic() {
        return brandPic;
    }

    public void setBrandPic(String brandPic) {
        this.brandPic = brandPic;
    }

    @Override
    public String toString() {
        return "BrandInfo{" +
                "brandId='" + brandId + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandPic='" + brandPic + '\'' +
                '}';
    }
}
