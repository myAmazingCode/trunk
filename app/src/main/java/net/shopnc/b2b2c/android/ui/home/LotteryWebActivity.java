package net.shopnc.b2b2c.android.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.DialogHelper;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.ui.mine.LoginActivity;

/**
 * @author lulei
 *         Created 2017/5/12 13:38
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 大转盘抽奖Activity
 */
public class LotteryWebActivity extends BaseActivity {

    private ImageButton moremenu;
    private WebView webviewID;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_web);
        MyExceptionHandler.getInstance().setContext(this);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = Constants.URL_LOTTERY_DIAL + "?lot_id=" + data + "&key=" + application.getLoginKey() + "&username=" + application.getUserName() + "&from=app";
        webviewID.loadUrl(url);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        moremenu = (ImageButton) findViewById(R.id.moremenu);
        moremenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(context, MyShopApplication.getInstance().getLoginKey())) {
                    showPopupWindow();
                } else {
                    T.showShort(context, "请登录");
                }
            }
        });
        webviewID = (WebView) findViewById(R.id.webview);
        webviewID.getSettings().setSupportZoom(true);
        webviewID.getSettings().setBuiltInZoomControls(true);
        WebSettings settings = webviewID.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptEnabled(true);
//        webviewID.loadUrl(Constants.URL_HELP);
        data = getIntent().getStringExtra("data");
        String url = Constants.URL_LOTTERY_DIAL + "?lot_id=" + data + "&key=" + application.getLoginKey() + "&username=" + application.getUserName() + "&from=app";
        webviewID.loadUrl(url);
        webviewID.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (ShopHelper.isLogin(context, application.getLoginKey())) {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                view.loadUrl("file:///android_asset/error.html");
            }
        });

//        webviewID.setWebChromeClient(new WebChromeClient() {
//
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message,
//                                     final JsResult result) {
//                Log.i("aaa", "onJsAlert" + "," + "url: " + url);
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage(message)
//                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface arg0, int arg1) {
//                                arg0.dismiss();
//                                finish();
//                            }
//                        }).show();
//                result.cancel();
//                return true;
//            }
//
//            @Override
//            public boolean onJsConfirm(WebView view, String url,
//                                       String message, final JsResult result) {
//                // TODO Auto-generated method stub
//                Log.i("aaa", "onJsConfirm" + "," + "url: " + url);
//
////                DialogUtils.dialogBuilder(mContext, "温馨提示", message,
////                        new DialogCallBack() {
////
////                            @Override
////                            public void onCompate() {
////                                Log.i(TAG, "onJsConfirm,onCompate");
////                                result.confirm();
////                            }
////
////                            @Override
////                            public void onCancel() {
////                                Log.i(TAG, "onJsConfirm,onCancel");
////                                result.cancel();
////                            }
////                        });
//                return true;
//            }
//        });

        initPopupWindow();
    }

    /**
     * 返回按钮点击
     */
    public void btnBackClick(View view) {
        finish();
    }

    //更多加载
    private PopupWindow popupWindow;

    /**
     * 显示popupwindow
     */
    private void showPopupWindow() {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(moremenu, moremenu.getLayoutParams().width / 2, 0);
        } else {
            popupWindow.dismiss();
        }
    }

    /**
     * 初始化popupwindow
     */
    private void initPopupWindow() {
        popupWindow = DialogHelper.initPopupWindow(this);
    }


}
