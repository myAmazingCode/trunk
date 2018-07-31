package net.shopnc.b2b2c.android.common;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by qyf on 2016/1/7.
 */
public class JsonUtil {
    public static  <T> T getBean(String json,String urg1,String urg2,Type type){
        try{
            Gson gson=new Gson();
            JSONObject object=new JSONObject(json);
            String s1=object.getString(urg1);
            JSONObject obj=new JSONObject(s1);
            String s2=obj.getString(urg2);
                return gson.fromJson(s2,type);
        }catch (JSONException e){
            e.printStackTrace();
        }
    return null;

    }

    public static <T> T getBean(String json,String urg1,String urg2,String urg3,Type type){
        try{
            Gson gson=new Gson();
            JSONObject object=new JSONObject(json);
            String s1=object.getString(urg1);
            JSONObject obj=new JSONObject(s1);
            String s2=obj.getString(urg2);
            JSONObject o=new JSONObject(s2);
            String s3=o.getString(urg3);
            return gson.fromJson(s3,type);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;

    }

    public static <T> T getBean(String json,String urg1,Type type){
        try{
            Gson gson=new Gson();
            JSONObject object=new JSONObject(json);
            String s1=object.getString(urg1);
            return gson.fromJson(s1,type);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;

    }


    public static String getString(String json,String urg1){
        try{
            JSONObject object=new JSONObject(json);
            return object.getString(urg1);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;

    }

    public static <T> T getBean(String json,Type type){
            Gson gson=new Gson();
            return gson.fromJson(json,type);
    }


}
