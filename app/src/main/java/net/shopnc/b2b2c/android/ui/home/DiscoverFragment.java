package net.shopnc.b2b2c.android.ui.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.ui.group.GroupListActivity;
import net.shopnc.b2b2c.android.ui.points.PointsCenterActivity;
import net.shopnc.b2b2c.android.ui.promotion.PromotionListActivity;
import net.shopnc.b2b2c.android.ui.showorders.ShowOrdersDetailActivity;
import net.shopnc.b2b2c.android.ui.showorders.ShowOrdersListActivity;
import net.shopnc.b2b2c.android.ui.trys.TryGoodShowActivity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment {

    private MyShopApplication application;
    private Context context;

    @Bind(R.id.wvContent)
    WebView wvContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewLayout = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, viewLayout);

        context = getActivity();
        application = (MyShopApplication) getActivity().getApplicationContext();

        WebSettings webSettings = wvContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //缓存
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //存储
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        //不显示缩放按钮
        webSettings.setDisplayZoomControls(false);
        //不显示滚动条
        wvContent.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        wvContent.setWebViewClient(new WebViewClient() {
            //加载本地文件
            private WebResourceResponse loadLocalFile(String url) {
                try {
                    if (url.contains("jquery.min")) {
                        return new WebResourceResponse("application/x-javascript", "utf-8", context.getAssets().open("jquery.min.js"));
                    }
                    if (url.contains("vue.min")) {
                        return new WebResourceResponse("application/x-javascript", "utf-8", context.getAssets().open("vue.min.js"));
                    }
                    if (url.contains("eruda.min")) {
                        return new WebResourceResponse("application/x-javascript", "utf-8", context.getAssets().open("eruda.min.js"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            //API<21部分js文件从本地加载
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                WebResourceResponse response = loadLocalFile(url);
                if (response != null) {
                    return response;
                }
                return super.shouldInterceptRequest(view, url);
            }

            //API>=21部分js文件从本地加载
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if (uri != null) {
                    WebResourceResponse response = loadLocalFile(uri.toString());
                    if (response != null) {
                        return response;
                    }
                }
                return super.shouldInterceptRequest(view, request);
            }
        });
        wvContent.addJavascriptInterface(this, "NcApp");

        wvContent.loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/discover.html?client=android");

        return viewLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

        if (wvContent != null) {
            wvContent.destroy();
        }
    }

    /**
     * 跳转到晒宝列表
     */
    @JavascriptInterface
    public void navigateToShowOrdersList() {
        LogHelper.d("navigateToShowOrdersList");

        Intent intent = new Intent(context, ShowOrdersListActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到晒宝详情页
     *
     * @param id
     */
    @JavascriptInterface
    public void navigateToShowOrdersDetail(final int id) {
        LogHelper.d("navigateToShowOrdersDetail id:" + id);

        Intent intent = new Intent(context, ShowOrdersDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    /**
     * 跳转到推广商品列表
     */
    @JavascriptInterface
    public void navigateToDistProductList() {
        Intent intent = new Intent(context, PromotionListActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到拼团列表
     */
    @JavascriptInterface
    public void navigateToGroupList() {
        LogHelper.d("navigateToGroupList");

        Intent intent = new Intent(context, GroupListActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到试用列表
     */
    @JavascriptInterface
    public void navigateToTrysList() {
        Intent intent = new Intent(context, TryGoodShowActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到积分中心
     */
    @JavascriptInterface
    public void navigateToPointsCenter() {
        Intent intent = new Intent(context, PointsCenterActivity.class);
        context.startActivity(intent);
    }

}
