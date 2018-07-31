package net.shopnc.b2b2c.android.common.http;

import com.google.gson.Gson;

import net.shopnc.b2b2c.android.bean.BaseData;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by qyf on 2016/3/1.
 */
public class JsonUtil {
    public static final Gson gson = new Gson();

    public static <T> T toBean(String json, String urg1, String urg2, Type type) {
        try {
            JSONObject object = new JSONObject(json);
            String s1 = object.getString(urg1);
            JSONObject obj = new JSONObject(s1);
            String s2 = obj.getString(urg2);
            return gson.fromJson(s2, type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static <T> T toBean(String json, String urg1, Type type) {
        try {
            JSONObject object = new JSONObject(json);
            String s1 = object.optString(urg1);
            return gson.fromJson(s1, type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static <T> T toBean(String json, Type type) {
        return gson.fromJson(json, type);
    }


    public static Integer toInteger(String json, String urg1) {
        try {
            JSONObject object = new JSONObject(json);
            return Integer.valueOf(object.optString(urg1));
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String toString(String json, String urg1) {
        try {
            JSONObject object = new JSONObject(json);
            return object.optString(urg1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String toString(String json, String urg1, String urg2) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject obj1 = new JSONObject(object.optString(urg1));
            return obj1.optString(urg2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String getError(String json) {
        String error = null;
        try {
            JSONObject object = new JSONObject(json);
            error = object.optString("error");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return error;
    }


    public static BaseData getBaseData(String json) {
        try {
            JSONObject object = new JSONObject(json);
            BaseData baseData = new BaseData();
            baseData.setCode(object.optInt("code"));
            baseData.setDatas(object.optString("datas"));
            return baseData;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSuccess(String json) {
        String success = null;
        try {
            JSONObject object = new JSONObject(json);
            success = object.optString("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return success;
    }


}
