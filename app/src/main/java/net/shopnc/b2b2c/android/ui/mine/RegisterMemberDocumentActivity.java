package net.shopnc.b2b2c.android.ui.mine;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.Constants;

import butterknife.Bind;

public class RegisterMemberDocumentActivity extends BaseActivity {


    @Bind(R.id.wvContent)
    WebView wvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("注册协议");
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
        wvContent.loadUrl(Constants.WAP_MEMBER_DOCUMENT);

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
    protected void setView() {
        setContentView(R.layout.activity_register_member_document);
    }

}
