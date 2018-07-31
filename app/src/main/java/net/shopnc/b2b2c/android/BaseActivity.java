package net.shopnc.b2b2c.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.android.common.Global;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.StringUtils;
import net.shopnc.b2b2c.android.custom.dialog.CustomProgressDialog;

import butterknife.ButterKnife;

/**
 * Created by dqw on 2015/5/25.
 */
public class BaseActivity extends AppCompatActivity {

    protected ImageButton btnBack;
    protected TextView tvCommonTitle;
    protected TextView tvCommonTitleBorder;
    private LinearLayout llListEmpty;

    protected MyShopApplication application;
    protected Context context;
    protected Activity activity;
    private Gson gson;
    private CustomProgressDialog dialogWait = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        context = this;
        application = MyShopApplication.getInstance();
        activity = this;
    }

    /**
     * 添加布局文件
     */
    public void setView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (!StringUtils.isEmpty(application.getLoginKey())) {
//            Global.cartnum = 0;
//            if (!TextUtils.isEmpty(Global.cartlist)) {
//                String str = Global.cartlist.substring(0, Global.cartlist.length() - 1);
//                ShopHelper.addCartBatch(context, application, str);
//            }
//        }
    }

    /**
     * 设置Activity通用标题文字
     */
    protected void setCommonHeader(String title) {
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        tvCommonTitle = (TextView) findViewById(R.id.tvCommonTitle);
        tvCommonTitleBorder = (TextView) findViewById(R.id.tvCommonTitleBorder);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvCommonTitle.setText(title);
    }

    /**
     * 空列表背景
     */
    protected void setListEmpty(int resId, String title, String subTitle) {
        llListEmpty = (LinearLayout) findViewById(R.id.llListEmpty);
        ImageView ivListEmpty = (ImageView) findViewById(R.id.ivListEmpty);
        ivListEmpty.setImageResource(resId);
        TextView tvListEmptyTitle = (TextView) findViewById(R.id.tvListEmptyTitle);
        TextView tvListEmptySubTitle = (TextView) findViewById(R.id.tvListEmptySubTitle);
        tvListEmptyTitle.setText(title);
        tvListEmptySubTitle.setText(subTitle);
    }

    /**
     * 显示空列表背景
     */
    protected void showListEmpty() {
        llListEmpty.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏空列表背景
     */
    protected void hideListEmpty() {
        llListEmpty.setVisibility(View.GONE);
    }

    /**
     * 隐藏返回按钮
     */
    protected void hideBack() {
        btnBack.setVisibility(View.INVISIBLE);
    }

    /**
     * 隐藏分隔线
     */
    protected void hideCommonHeaderBorder() {
        tvCommonTitleBorder.setVisibility(View.INVISIBLE);
    }

    public void initData(String json) {
    }

    /**
     * 网络请求dialog
     *
     * @param context
     * @param text
     */
    protected void showDialog(Context context, String text) {
        if (dialogWait != null && dialogWait.isShowing()) {
            return;
        }
        dialogWait = CustomProgressDialog.createDialog(context);
        dialogWait.setMessage(text);// 暂时显示文字
        dialogWait.setCanceledOnTouchOutside(false);
        dialogWait.setCancelable(true);
        if (!isFinishing()) dialogWait.show();
    }

    /**
     * 关闭dialog
     */
    protected void dissMissDialog() {
        if (dialogWait != null && dialogWait.isShowing()) {
            dialogWait.dismiss();
        }
    }


}
