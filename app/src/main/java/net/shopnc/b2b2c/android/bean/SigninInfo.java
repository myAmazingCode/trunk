package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 签到信息
 *
 * @author dqw
 * @Time 2015-8-18
 */
public class SigninInfo {
    private String slPoints;
    private String slAddtimeText;

    public SigninInfo(String slPoints, String slAddtimeText) {
        this.slPoints = slPoints;
        this.slAddtimeText = slAddtimeText;
    }

    public static ArrayList<SigninInfo> newInstanceList(String json) {
        ArrayList<SigninInfo> list = new ArrayList<SigninInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String slPoints = obj.optString("sl_points");
                String slAddtimeText = obj.optString("sl_addtime_text");
                list.add(new SigninInfo(slPoints,slAddtimeText));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getSlPoints() {
        return slPoints;
    }

    public void setSlPoints(String slPoints) {
        this.slPoints = slPoints;
    }

    public String getSlAddtimeText() {
        return slAddtimeText;
    }

    public void setSlAddtimeText(String slAddtimeText) {
        this.slAddtimeText = slAddtimeText;
    }

    @Override
    public String toString() {
        return "SigninInfo{" +
                "slPoints='" + slPoints + '\'' +
                ", slAddtimeText='" + slAddtimeText + '\'' +
                '}';
    }
}
