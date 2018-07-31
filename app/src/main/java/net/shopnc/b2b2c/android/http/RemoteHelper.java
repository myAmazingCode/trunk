package net.shopnc.b2b2c.android.http;

import com.squareup.okhttp.Request;


import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.BaseFragmentActivity;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonFastUtil;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.OkHttpUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.xrefresh.XRefreshView;

import org.apache.http.HttpStatus;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 刷新帮助类
 *
 * @author huting
 * @date 2016/3/3
 * @email 495773046@qq.com
 */
public class RemoteHelper {

    /**===================================================第一版的网络请求封装========================================================================*/


    /**
     * 执行异步get请求(适用于activity)
     *
     * @param activity
     * @param xRefreshView
     * @param url
     * @return
     */
    public static void loadListDataGet(final BaseActivity activity, final XRefreshView xRefreshView, String url) {
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                activity.initData(json);
            }
        });
    }


    /**
     * 异步get请求（适用于FragmentActivity）
     *
     * @param fragmentActivity
     * @param xRefreshView
     * @param url
     */
    public static void loadListDataGet(final BaseFragmentActivity fragmentActivity, final XRefreshView xRefreshView, String url) {
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                fragmentActivity.initData(json);
            }
        });
    }


    /**
     * 执行异步post请求   一般的参数请求（适用于activity）
     *
     * @param activity
     * @param xRefreshView
     * @param url
     * @param params
     * @param myShopApplication
     * @return
     */
    public static void loadListDataPost(final BaseActivity activity, final XRefreshView xRefreshView, String url, final HashMap<String, String> params,
                                        MyShopApplication myShopApplication) {
        RemoteDataHandler.asyncLoginPostDataString(url, params, myShopApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                //xRefreshView.stopLoadMore();
                // xRefreshView.stopRefresh();

                String json = data.getJson();

                activity.initData(json);
            }
        });
    }

    /**
     * 执行异步post请求   一般的参数请求（适用于BaseFragmentActivity）
     *
     * @param fragmentActivity
     * @param xRefreshView
     * @param url
     * @param params
     * @param myShopApplication
     */
    public static void loadListDataPost(final BaseFragmentActivity fragmentActivity, final XRefreshView xRefreshView, String url, final HashMap<String, String> params,
                                        MyShopApplication myShopApplication) {
        RemoteDataHandler.asyncLoginPostDataString(url, params, myShopApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                // xRefreshView.stopLoadMore();
                // xRefreshView.stopRefresh();

                String json = data.getJson();
                fragmentActivity.initData(json);
            }
        });
    }


    /**
     * 用于文件上传的异步post请求（适用于activity）
     * @param activity
     * @param url
     * @param params
     * @param fileMap
     */

    public static void asyncMultipartPostString(final BaseActivity activity,  final String url,final HashMap<String, String> params,
                                                final HashMap<String, File> fileMap) {
        RemoteDataHandler.asyncMultipartPostString(Constants.URL_ORDER_EVALUATE_UPLOAD_IAMGE, params, fileMap, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                activity.initData(json);
            }
        });
    }

    /**
     * 用于文件上传的异步post请求（适用于BaseFragmentActivity）
     * @param fragmentActivity
     * @param url
     * @param params
     * @param fileMap
     */
    public static void asyncMultipartPostString(final BaseFragmentActivity fragmentActivity,  final String url,final HashMap<String, String> params,
                                                final HashMap<String, File> fileMap) {
        RemoteDataHandler.asyncMultipartPostString(Constants.URL_ORDER_EVALUATE_UPLOAD_IAMGE, params, fileMap, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                fragmentActivity.initData(json);
            }
        });
    }


    /**====================================================第二版的okhttp请求封装==========================================================*/

    /**
     * OkHttp的异步get请求(用于activity)
     * @param activity
     * @param xRefreshView
     * @param url
     */
    public static void okHttpGetString(final BaseActivity activity, final XRefreshView xRefreshView, String url){
        OkHttpUtil.getAsyn(activity, url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogHelper.e("error", "请求失败了.....");
            }

            @Override
            public void onResponse(String response) {
                ResponseData data = JsonFastUtil.fromJsonFast(response, ResponseData.class);
                String json = data.getDatas();

                if (data.getCode() == HttpStatus.SC_OK){
                    if (xRefreshView != null){

                        if (!data.isHasMore()) {
                            xRefreshView.setPullLoadEnable(false);
                        } else {
                            xRefreshView.setPullLoadEnable(true);
                        }
                        activity.initData(json);

                    }else{
                        activity.initData(json);
                    }
                }else{
                    ShopHelper.showApiError(activity, data.getDatas());
                }
            }
        });
    }

    /**
     * OkHttp的异步get请求(用于FragmentActivity)
     * @param fragmentActivity
     * @param xRefreshView
     * @param url
     */
    public static void okHttpGetString(final BaseFragmentActivity fragmentActivity, final XRefreshView xRefreshView, String url){
        OkHttpUtil.getAsyn(fragmentActivity, url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogHelper.e("error", "请求失败了.....");
            }

            @Override
            public void onResponse(String response) {
                ResponseData data = JsonFastUtil.fromJsonFast(response, ResponseData.class);
                String json = data.getDatas();

                if (data.getCode() == HttpStatus.SC_OK){
                    if (xRefreshView != null){

                        if (!data.isHasMore()) {
                            xRefreshView.setPullLoadEnable(false);
                        } else {
                            xRefreshView.setPullLoadEnable(true);
                        }
                        fragmentActivity.initData(json);

                    }else{
                        fragmentActivity.initData(json);
                    }
                }else{
                    ShopHelper.showApiError(fragmentActivity, data.getDatas());
                }
            }
        });
    }


    /**
     * okHttp的异步post请求（用于activity）
     * @param activity
     * @param url
     * @param params
     */
    public static void okHttpPostString(final BaseActivity activity,String url,Map<String, String> params,final XRefreshView xRefreshView){
        OkHttpUtil.postAsyn(activity, url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogHelper.e("error", "请求失败了.....");
            }

            @Override
            public void onResponse(String response) {
                ResponseData data = JsonFastUtil.fromJsonFast(response, ResponseData.class);
                String json = data.getDatas();

                if (data.getCode() == HttpStatus.SC_OK){
                    if (xRefreshView != null){

                        if (!data.isHasMore()) {
                            xRefreshView.setPullLoadEnable(false);
                        } else {
                            xRefreshView.setPullLoadEnable(true);
                        }
                        activity.initData(json);

                    }else{
                        activity.initData(json);
                    }
                }else{
                    ShopHelper.showApiError(activity, data.getDatas());
                }
            }
        }, params);
    }

    /**
     * okHttp的异步post请求（用于FragmentActivity）
     * @param fragmentActivity
     * @param url
     * @param params
     */
    public static void okHttpPostString(final BaseFragmentActivity fragmentActivity,String url,Map<String, String> params,final XRefreshView xRefreshView){
        OkHttpUtil.postAsyn(fragmentActivity, url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogHelper.e("error","请求失败了.....");
            }

            @Override
            public void onResponse(String response) {
                ResponseData data = JsonFastUtil.fromJsonFast(response, ResponseData.class);
                String json = data.getDatas();

                if (data.getCode() == HttpStatus.SC_OK){
                    if (xRefreshView != null){

                        if (!data.isHasMore()) {
                            xRefreshView.setPullLoadEnable(false);
                        } else {
                            xRefreshView.setPullLoadEnable(true);
                        }
                        fragmentActivity.initData(json);

                    }else{
                        fragmentActivity.initData(json);
                    }
                }else{
                    ShopHelper.showApiError(fragmentActivity, data.getDatas());
                }
            }
        },params);
    }
}
