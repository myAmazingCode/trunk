package net.shopnc.b2b2c.android.ui.trys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.TrysInfoBean;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.ChooseDialog;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TryGoodDetailsActivity extends AppCompatActivity {

    @Bind(R.id.ivGoodImg)
    ImageView ivGoodImg;
    @Bind(R.id.ivBack)
    ImageView ivBack;
    @Bind(R.id.ivMore)
    ImageView ivMore;
    @Bind(R.id.tvGoodName)
    TextView tvGoodName;
    @Bind(R.id.tvTryNum)
    TextView tvTryNum;
    @Bind(R.id.tvTryPeople)
    TextView tvTryPeople;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.llInfo)
    LinearLayout llInfo;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvRequest)
    TextView tvRequest;

    private Context context;
    private MyShopApplication application;

    private int trysId;
    private String trysRule;
    private TrysInfoBean trysInfo;

    private CountDownTimer timer;

    public static void onStartActivity(Context c, int trysId) {
        Intent intent = new Intent(c, TryGoodDetailsActivity.class);
        intent.putExtra("trysId", trysId);
        c.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_good_details);
        ButterKnife.bind(this);
        context = this;
        application = MyShopApplication.getInstance();
        trysId = getIntent().getIntExtra("trysId", 0);
        loadData();
    }

    private void loadData() {
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_TRY_GOOD_DETAILS, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                trysInfo = JsonUtil.toBean(data, "trysInfo", TrysInfoBean.class);
                trysRule = JsonUtil.toString(data, "trysRule");
                LoadImage.loadRemoteImg(context, ivGoodImg, trysInfo.getImageSrc());
                tvGoodName.setText(trysInfo.getGoodsName());
                tvTryNum.setText(Html.fromHtml("<font color=\"#ED5968\">" + trysInfo.getProvideNum() + "份</font> 试用品"));
                tvTryPeople.setText(Html.fromHtml("已有 <font color=\"#ED5968\">" + trysInfo.getCurrentNum() + "</font> 人申请"));
                tvPrice.setText(ShopHelper.getPriceString(trysInfo.getGoodsPrice()) + " 元");
                tvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                initWebView(trysRule);
                initTime();
            }
        }, new OkHttpUtil.Param("trysId", trysId + ""));
    }

    private void initWebView(String imgSrc) {
        String head = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title></title>" +
                "</head>" +
                "<body>";
        String imgHead = "<div style=\"text-align: center;\">";
        String imgEnd = "</div>";
        String end = "</body></html>";
        String s = head + imgHead + imgSrc + imgEnd + end;
        webview.loadDataWithBaseURL(null, s, "text/html", "utf-8", null);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebChromeClient(new WebChromeClient());
    }

    @OnClick({R.id.ivGoodImg, R.id.ivBack, R.id.ivMore, R.id.tvGoodName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivGoodImg:
            case R.id.tvGoodName:
                Intent intent = new Intent(context, GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, trysInfo.getCommonId());
                context.startActivity(intent);
                break;
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivMore:
                ChooseDialog chooseDialog = new ChooseDialog(context, application, ivMore);
                break;
        }
    }

    @OnClick(R.id.tvRequest)
    public void onClick() {
        if (ShopHelper.isLogin(context)) {
            HashMap<String, String> params = new HashMap<>();
            params.put("token", application.getToken());
            params.put("trysId", trysInfo.getTrysId() + "");
            OkHttpUtil.postAsyn(this,ConstantUrl.URL_TRY_REQUEST, new BeanCallback<String>() {
                @Override
                public void response(String data) {
                    String success = JsonUtil.toString(data, "success");
                    String message = JsonUtil.toString(data, "message");
                    if (success.equals("")) {
                        TToast.showShort(context, message);
                    } else {
                        TToast.showShort(context, success);
                    }
                }
            }, params);
        }
    }

    private void initTime() {
        switch (trysInfo.getTrysState()) {
            case 1:
                startCountDownTime(new Long(ShopHelper.date2TimeStamp(trysInfo.getEndTime())) - System.currentTimeMillis());
                break;
            case 2:
                tvTime.setText("即将开始");
                break;
            case 3:
                tvTime.setText("已结束");
                break;
        }
    }

    private void startCountDownTime(long time) {
        /**
         * 最简单的倒计时类，实现了官方的CountDownTimer类（没有特殊要求的话可以使用）
         * 即使退出activity，倒计时还能进行，因为是创建了后台的线程。
         * 有onTick，onFinsh、cancel和start方法
         */
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每隔countDownInterval秒会回调一次onTick()方法
                tvTime.setText(getTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                tvTime.setText("已结束");
            }
        };
        timer.start();// 开始计时
    }

    private String getTime(long millisUntilFinished) {
        long s = millisUntilFinished / 1000;
        int day = (int) s / 60 / 60 / 24;
        int honor = (int) (s - day * 24 * 60 * 60) / 60 / 60;
        int second = (int) (s - day * 24 * 60 * 60 - honor * 60 * 60) / 60;
        int min = (int) s - day * 60 * 60 * 24 - honor * 60 * 60 - second * 60;
        return ShopHelper.getTwoString(day) + "天" + ShopHelper.getTwoString(honor) + "时" + ShopHelper.getTwoString(second) + "分" + ShopHelper.getTwoString(min) + "秒";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webview.removeAllViews();
        webview.destroy();
        webview = null;
        if (timer != null)
            timer.cancel();// 取消
        ButterKnife.unbind(this);
    }
}
