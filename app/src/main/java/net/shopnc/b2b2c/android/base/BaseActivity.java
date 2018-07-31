package net.shopnc.b2b2c.android.base;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.DialogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.TToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lulei
 *         Created 2017/5/24 11:26
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * 新的BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.rlHeader)
    RelativeLayout rlHeader;
    //    @Nullable
    @Bind(R.id.btnMore)
    ImageButton btnMore;
    @Nullable
    @Bind(R.id.btnClear)
    TextView btnClear;
    //    @Nullable
    @Bind(R.id.btnBack)
    ImageButton btnBack;
    //    @Nullable
    @Bind(R.id.tvCommonTitle)
    TextView tvCommonTitle;
    @Nullable
    @Bind(R.id.ivSearch)
    ImageView ivSearch;
    @Nullable
    @Bind(R.id.rlMore)
    RelativeLayout rlMore;
    @Nullable
    @Bind(R.id.vhintMenu)
    View vhintMenu;

    private RelativeLayout layoutEmpty;

    protected MyShopApplication application;
    protected Context context;
    protected Activity activity;
    protected String rmb;

    @OnClick(R.id.btnBack)
    void btnBack(View view) {
        finish();
        TToast.cancleToast();
    }

    //    更多加载
    private PopupWindow popupWindow;

    /**
     * 显示popupwindow
     */
    private void showPopupWindow() {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(btnMore, btnMore.getLayoutParams().width / 2, 0);
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setView();
        ButterKnife.bind(this);
        context = this;
        application = MyShopApplication.getInstance();
        activity = this;
        initPopupWindow();
        /**
         * 异常捕获
         */
        MyExceptionHandler.getInstance().setContext(this);
        rmb = getResources().getString(R.string.money_rmb);
    }

    protected abstract void setView();

    /**
     * 设置更多可见
     */
    protected void setBtnMoreVisible() {
        if (btnMore != null) {
            btnMore.setVisibility(View.VISIBLE);
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                ChooseDialog dialog = new ChooseDialog(context, application, btnMore);
                    if (ShopHelper.isLogin(context, MyShopApplication.getInstance().getLoginKey())) {
                        showPopupWindow();
                    } else {
                        T.showShort(context, "请登录");
                    }
                }
            });
        }

    }

    /**
     * 设置返回不可见
     */
    protected void setBtnBackGone() {
        btnBack.setVisibility(View.GONE);
    }


    /**
     * 设置空标记的显示
     */
    protected void setLayoutEmpty(int resId, String emptyTitle, String emptyBody) {
        layoutEmpty = (RelativeLayout) findViewById(R.id.layoutEmpty);
        ImageView imgEmptyLogo = (ImageView) findViewById(R.id.imgEmptyLogo);
        TextView tvEmptyTitle = (TextView) findViewById(R.id.tvEmptyTitle);
        TextView tvEmptyBody = (TextView) findViewById(R.id.tvEmptyBody);
        Button btnChoose = (Button) findViewById(R.id.btnChoose);
        imgEmptyLogo.setImageResource(resId);
        tvEmptyTitle.setText(emptyTitle);
        tvEmptyBody.setText(emptyBody);
    }

    /**
     * 设置空标记的显示
     */
    protected void setLayoutEmpty(int resId, String emptyTitle, String emptyBody, String btnText, View.OnClickListener listener) {
        layoutEmpty = (RelativeLayout) findViewById(R.id.layoutEmpty);
        ImageView imgEmptyLogo = (ImageView) findViewById(R.id.imgEmptyLogo);
        TextView tvEmptyTitle = (TextView) findViewById(R.id.tvEmptyTitle);
        TextView tvEmptyBody = (TextView) findViewById(R.id.tvEmptyBody);
        Button btnChoose = (Button) findViewById(R.id.btnChoose);
        btnChoose.setVisibility(View.VISIBLE);
        btnChoose.setText(btnText);
        btnChoose.setOnClickListener(listener);
        imgEmptyLogo.setImageResource(resId);
        tvEmptyTitle.setText(emptyTitle);
        tvEmptyBody.setText(emptyBody);
    }

    /**
     * 显示空标志
     */
    protected void showLayoutEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    protected void hideLayoutEmpty() {
        layoutEmpty.setVisibility(View.GONE);
    }

    /**
     * 设置Activity通用标题文字
     */
    protected void setCommonHeader(String title) {
//        if (tvCommonTitle != null)
            tvCommonTitle.setText(title);
    }


    /**
     * 隐藏header
     */
    protected void hideHeader() {
        if (rlHeader != null)
            rlHeader.setVisibility(View.GONE);
    }

    /**
     * 手动释放资源
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            TToast.cancleToast();
        }
        return super.onKeyDown(keyCode, event);
    }

    //是否拒绝网络请求的响应；true表示拒绝；false表示接收，默认false，在onDestroy中设置为true。
    protected boolean isOnDestroy;

    public boolean isOnDestroy() {
        return isOnDestroy;
    }

    public void setOnDestroy(boolean onDestroy) {
        isOnDestroy = onDestroy;
    }


    @Override
    protected void onDestroy() {
        //1.取消请求
//        OkHttpUtils.getInstance().cancelTag(this);
        //2.拒绝响应
        setOnDestroy(true);
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
