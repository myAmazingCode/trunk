package net.shopnc.b2b2c.android.util;

import android.content.Context;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lzz on 2017/6/6.
 */
public class OkHttpUtil {

    public static final String TAG = "OkHttpUtil";

    /**
     * 请求参数key-value
     */
    public static class Param {
        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Param{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }

        String key;
        String value;
    }


    /**
     * get 请求
     *
     * @param activity
     * @param url
     * @param callback
     */
    public static void getAsyn(final Context activity, String url, BeanCallback callback) {
        addContext(activity, callback);
        OkHttpUtils
                .get()
                .url(url)
                .tag(activity)
                .build()
                .execute(callback);
    }

    /**
     * get 请求带参数
     *
     * @param activity
     * @param url
     * @param callback
     * @param params
     */
    public static void getAsyn(final Context activity, String url, BeanCallback callback, Map<String, String> params) {
        addContext(activity, callback);
        OkHttpUtils
                .get()
                .url(url)
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }

    /**
     * get 请求带参数
     *
     * @param activity
     * @param url
     * @param callback
     * @param params
     */
    public static void getAsyn(final Context activity, String url, BeanCallback callback, Param... params) {
        if (params == null) {
            params = new Param[0];
        }
        Map<String, String> map = new HashMap<>();
        for (Param param : params) {
            map.put(param.key, param.value);
        }
        getAsyn(activity, url, callback, map);
    }


    /**
     * post 请求
     *
     * @param activity
     * @param url
     * @param callback
     * @param params
     */
    public static void postAsyn(final Context activity, String url, BeanCallback callback, Map<String, String> params) {
        addContext(activity, callback);
        OkHttpUtils
                .post()
                .url(url)
                .tag(activity)
                .params(params)
                .build()
                .execute(callback);
    }

    /**
     * post 请求
     *
     * @param activity
     * @param url
     * @param callback
     * @param params
     */
    public static void postAsyn(final Context activity, String url, BeanCallback callback, Param... params) {
        if (params == null) {
            params = new Param[0];
        }
        Map<String, String> map = new HashMap<>();
        for (Param param : params) {
            map.put(param.key, param.value);
        }
        postAsyn(activity, url, callback, map);
    }

    /**
     * post单个文件上传
     *
     * @param activity
     * @param url
     * @param callback
     * @param fileKey
     * @param file
     * @param params
     */
    public static void postAsyn(final Context activity, String url, BeanCallback callback, String fileKey, File file, Map<String, String> params) {
        addContext(activity, callback);
        OkHttpUtils
                .post()
                .url(url)
                .addFile(fileKey, file.getName(), file)
                .tag(activity)
                .params(params)
                .build()
                .execute(callback);
    }

    /**
     * post单个文件上传
     *
     * @param activity
     * @param url
     * @param callback
     * @param fileKey
     * @param file
     * @param params
     */
    public static void postAsyn(final Context activity, String url, BeanCallback callback, String fileKey, File file, Param... params) {
        if (params == null) {
            params = new Param[0];
        }
        Map<String, String> map = new HashMap<>();
        for (Param param : params) {
            map.put(param.key, param.value);
        }
        postAsyn(activity, url, callback, fileKey, file, map);
    }


    /**
     * 多个文件上传
     *
     * @param activity
     * @param url
     * @param callback
     * @param fileKeys
     * @param files
     * @param params
     */
    public static void postAsyn(final Context activity, String url, BeanCallback callback, String[] fileKeys, File[] files, Map<String, String> params) {
        addContext(activity, callback);
        PostFormBuilder builder = OkHttpUtils
                .post()
                .url(url)
                .tag(activity)
                .params(params);

        for (int i = 0; i < fileKeys.length; i++) {
            String fileKey = fileKeys[i];
            File file = files[i];
            builder.addFile(fileKey, fileKey, file);
        }

        builder.build()
                .execute(callback);
    }

    /**
     * 为当前callback传入Context上下文对象
     *
     * @param activity
     * @param callback
     */
    private static void addContext(Context activity, BeanCallback callback) {
        callback.setContext(activity);
    }


}



