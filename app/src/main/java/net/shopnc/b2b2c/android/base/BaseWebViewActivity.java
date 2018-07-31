package net.shopnc.b2b2c.android.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.shopnc.b2b2c.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * WebView Base类
 * 使用Android自带WebView内核，但是不同手机的使用的内核不同造成兼容性不好
 * 通常应该使用基于Tencent X5内核的BaseTencentX5Activity
 *
 * @Author dqw
 * Created 2017/3/24 16:28
 */
public abstract class BaseWebViewActivity extends BaseActivity {

    @Bind(R.id.wvContent)
    WebView wvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        WebSettings webSettings = wvContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //默认模式、读缓存可用并不过时，否则网络请求
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //存储
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        //不显示缩放按钮
        webSettings.setDisplayZoomControls(false);
        //缓存
        //webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //不显示滚动条
        wvContent.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        wvContent.setWebViewClient(new WebViewClient());
        wvContent.addJavascriptInterface(this, "NcApp");

        wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("WebView Log:", consoleMessage.message() + " -- From line "
                        + consoleMessage.lineNumber() + " of "
                        + consoleMessage.sourceId());
                return super.onConsoleMessage(consoleMessage);
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (wvContent != null) {
            wvContent.destroy();
        }
        super.onDestroy();
    }

    /**
     * 获取APP内用户token
     *
     * @return
     */
    @JavascriptInterface
    public String getMemberToken() {
        return application.getToken();
    }

    /**
     * 获取客户端类型，固定返回android
     *
     * @return
     */
    @JavascriptInterface
    public String getClientType() {
        return "android";
    }
}

