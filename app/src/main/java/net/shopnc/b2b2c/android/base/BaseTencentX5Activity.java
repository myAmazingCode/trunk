package net.shopnc.b2b2c.android.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.common.PermissionHelper;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.ShareDialog;
import net.shopnc.b2b2c.android.interfac.INCOnDialogCancel;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.ui.group.GroupDetailActivity;
import net.shopnc.b2b2c.android.ui.group.GroupListActivity;
import net.shopnc.b2b2c.android.ui.im.IMDetailsActivity;
import net.shopnc.b2b2c.android.ui.message.ChatListActivity;
import net.shopnc.b2b2c.android.ui.points.PointsCenterActivity;
import net.shopnc.b2b2c.android.ui.showorders.ShowOrdersDetailActivity;
import net.shopnc.b2b2c.android.ui.showorders.ShowOrdersMineActivity;
import net.shopnc.b2b2c.android.ui.store.StoreInfoFragmentActivity;
import net.shopnc.b2b2c.android.ui.trys.TryGoodShowActivity;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;
import net.shopnc.b2b2c.android.util.Constants;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * WebView Base类
 * 基于Tencent X5内核
 *
 * @Author dqw
 * Created 2017/3/24 16:28
 */
public abstract class BaseTencentX5Activity extends BaseActivity {

    //图片选择对话框
    private PhotoBottomDialog dialog;
    //选择的图片路径
    private String imagePath;

    @Bind(R.id.wvContent)
    WebView wvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        //默认隐藏header
        hideHeader();

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
                        return new WebResourceResponse("application/x-javascript", "utf-8", getAssets().open("jquery.min.js"));
                    }
                    if (url.contains("vue.min")) {
                        return new WebResourceResponse("application/x-javascript", "utf-8", getAssets().open("vue.min.js"));
                    }
                    if (url.contains("eruda.min")) {
                        return new WebResourceResponse("application/x-javascript", "utf-8", getAssets().open("eruda.min.js"));
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
    }

    @Override
    protected void onDestroy() {
        if (wvContent != null) {
            wvContent.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_tencent_x5);
    }

    /**
     * WebView加载Url
     *
     * @param url
     */
    protected void loadUrl(String url) {
        //
        if (url.indexOf('?') > 0) {
            url += "&client=android";
        } else {
            url += "?client=android";
        }
        LogHelper.d("webview loading:" + url);
        wvContent.loadUrl(url);
    }

    @JavascriptInterface
    public void uploadImage() {
        LogHelper.d("uploadImage");
        choosePicture();
    }

    /**
     * 获取APP内用户token
     *
     * @return
     */
    @JavascriptInterface
    public String getMemberToken() {
        LogHelper.d(application.getToken());
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

    /**
     * 获取购物车数据
     *
     * @return
     */
    @JavascriptInterface
    public String getVisitorCartData() {
        LogHelper.d("getVisitorCartData:" + application.getCartData());
        return application.getCartData();
    }

    /**
     * 修改游客（未登录）的购物车信息
     *
     * @param cookie
     */
    @JavascriptInterface
    public void setVisitorCartData(String cookie) {
        LogHelper.d("setVisitorCartData:" + cookie);
        application.setCartData(cookie);
    }

    /**
     * 跳转到登录
     */
    @JavascriptInterface
    public void navigateToLogin() {
        ShopHelper.isLogin(context);
    }

    /**
     * 跳转到首页
     */
    @JavascriptInterface
    public void navigateToHomePage() {
        Intent intent = new Intent(context, MainFragmentManager.class);
        application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
        startActivity(intent);
    }

    /**
     * 跳转到分类
     */
    @JavascriptInterface
    public void navigateToCategory() {
        Intent intent = new Intent(context, MainFragmentManager.class);
        application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_ClASSIFY_URL));
        startActivity(intent);
    }

    /**
     * 跳转到购物车
     */
    @JavascriptInterface
    public void navigateToCart() {
        Intent intent = new Intent(context, MainFragmentManager.class);
        application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_CART_URL));
        startActivity(intent);
    }

    /**
     * 跳转到我的商城
     */
    @JavascriptInterface
    public void navigateToMyShop() {
        Intent intent = new Intent(context, MainFragmentManager.class);
        application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_MINE_URL));
        startActivity(intent);
    }

    /**
     * 关闭当前窗口
     */
    @JavascriptInterface
    public void navigateToBack() {
        finish();
    }

    /**
     * 跳转到店铺
     *
     * @param storeId
     */
    @JavascriptInterface
    public void navigateToStore(final int storeId) {
        LogHelper.d("navigateToStore storeId:" + storeId);
        Intent intent = new Intent(context, StoreInfoFragmentActivity.class);
        intent.putExtra("storeId", storeId);
        startActivity(intent);
    }

    /**
     * 跳转到商品详情
     *
     * @param commonId
     */
    @JavascriptInterface
    public void navigateToGoodsDetail(final int commonId) {
        Intent intent = new Intent(context, GoodDetailsActivity.class);
        intent.putExtra("commonId", commonId);
        startActivity(intent);
    }

    /**
     * 跳转到IM列表
     */
    @JavascriptInterface
    public void navigateToMessageList() {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, ChatListActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 跳转到IM对话
     *
     * @param storeId
     */
    @JavascriptInterface
    public void navigateToImLinkId(final int storeId, final int linkId) {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, IMDetailsActivity.class);
            intent.putExtra("sid", storeId);
            intent.putExtra("lid", linkId);
            startActivity(intent);
        }
    }

    /**
     * 跳转到IM对话
     *
     * @param storeId
     * @param commonId
     */
    @JavascriptInterface
    public void navigateToImCommonId(final int storeId, final int commonId) {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, IMDetailsActivity.class);
            intent.putExtra("sid", storeId);
            intent.putExtra("gid", commonId);
            intent.putExtra("flag", "details");
            startActivity(intent);
        }
    }

    /**
     * 图片分享
     *
     * @param title
     * @param pic
     * @param price
     * @param url
     */
    @JavascriptInterface
    public void navigateToSharePicPriceUrl(final String title, final String pic, final String price, final String url) {
        UMImage image = new UMImage(context, pic);
        ShareDialog shareDialog = new ShareDialog(context, context.getResources().getString(R.string.app_name), title + "     " + url + "     (" + getString(R.string.app_name) + ")", url, image, umShareListener);
        shareDialog.show();
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
     * 跳转到我的晒宝页
     */
    @JavascriptInterface
    public void navigateToShowOrdersMine() {
        LogHelper.d("navigateToShowOrdersMine");

        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, ShowOrdersMineActivity.class);
            startActivity(intent);
        }
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
     * 跳转到团购详细页
     *
     * @param commonId
     */
    @JavascriptInterface
    public void navigateToGroupDetail(final int commonId) {
        LogHelper.d("navigateToGroupDetail commonId:" + commonId);

        Intent intent = new Intent(context, GroupDetailActivity.class);
        intent.putExtra("commonId", commonId);
        startActivity(intent);
    }

    /**
     * 友盟分享
     */
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                TToast.showShort(context, platform + " 收藏成功啦");
            } else {
                TToast.showShort(context, platform + " 分享成功啦");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            TToast.showShort(context, platform + " 分享失败啦");
            if (t != null) {
                LogHelper.d("throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            TToast.showShort(context, platform + " 分享取消了");
        }
    };

    /**
     * 打开图片选择对话框
     */
    private void choosePicture() {
        if (dialog == null) {
            dialog = new PhotoBottomDialog(this, new INCOnDialogCancel() {
                @Override
                public void onDialogCancel() {
                    wvContent.loadUrl("javascript:uploadImageEndForAndroid('','')");
                }
            });
        }

        dialog.show();
    }

    /**
     * 图片选择返回处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoBottomDialog.SELELCT_FILE_TO_UPLOAD_ITEM:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    LogHelper.d("SELELCT_FILE_TO_UPLOAD_ITEM");
                    LogHelper.d(uri.toString());
                    imagePath = LoadImage.getPath(context, uri);
                    fileToUpload();
                } else {
                    wvContent.loadUrl("javascript:uploadImageEndForAndroid('','')");
                }
                break;
            case PhotoBottomDialog.FLAG_CHOOSE_PHONE:
                LogHelper.d("FLAG_CHOOSE_PHONE");
                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        imagePath = Constants.APP_DIR + "/" + dialog.getImageName();
                        fileToUpload();
                    }
                } else {
                    wvContent.loadUrl("javascript:uploadImageEndForAndroid('','')");
                }
                break;
        }
    }

    /**
     * 权限检查回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionHelper.WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dialog.showFileChooser();
            } else {
                TToast.showShort(context, "暂无法选择图片");
            }
        } else if (requestCode == PermissionHelper.USE_CAMERA) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                dialog.doGoToPhone();
            } else {
                TToast.showShort(context, "暂无法使用摄像头");
            }
        }
    }

    /**
     * 上传图片并通知WebView页面
     */
    private void fileToUpload() {
        File file = new File(imagePath);
        ShopHelper.postImageWithCompress(context, file, new ShopHelper.PostImage() {
            @Override
            public void postImageSuccess(ImageFile imageFile) {
                LogHelper.d(imageFile.getName());
                wvContent.loadUrl("javascript:uploadImageEndForAndroid('" + imageFile.getName() + "','" + imageFile.getUrl() + "')");
            }
        });
    }

}

