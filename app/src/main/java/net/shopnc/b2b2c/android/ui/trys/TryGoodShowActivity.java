package net.shopnc.b2b2c.android.ui.trys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Global;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lulei
 *         Created 2017/5/22 10:59
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 免费试用
 */
public class TryGoodShowActivity extends BaseActivity {

    @Bind(R.id.content)
    LinearLayout content;
    @Bind(R.id.btnHome)
    RadioButton btnHome;
    @Bind(R.id.btnFee)
    RadioButton btnFee;
    @Bind(R.id.btnVoucher)
    RadioButton btnVoucher;
    @Bind(R.id.btnReport)
    RadioButton btnReport;
    @Bind(R.id.rgBottom)
    RadioGroup rgBottom;
    private FragmentManager fragmentManager;
    private TryGoodShowFragment homeFragment;
    private TryGoodShowFragment feeFragment;
    private TryGoodShowFragment voucherFragment;
    private TryGoodReportFragment reportFragment;
    private int mCurrentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        application = MyShopApplication.getInstance();
//        LoadImage.loadLocalGreyImg(this, imgClassify, R.drawable.stiore_categroy_b);
//        LoadImage.loadLocalGreyImg(this, imgMenu, R.drawable.nc_icon_more_dot);
        setCommonHeader("试用");
        setBtnMoreVisible();
        getMoreMessage();
        fragmentManager = getSupportFragmentManager();
        requestCartCount();
        initViews();
        goHome();
        changeFragment(0);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_try_good_show);
    }

    private void initViews() {
//        titleBarSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(TryGoodShowActivity.this, SearchGoodActivity.class);
//                startActivity(i);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCartCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

//    @OnClick({R.id.btnBack, R.id.imgMenu, R.id.imgClassify})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnBack:
//                finish();
//                break;
//            case R.id.imgMenu:
//                ChooseDialog dialog = new ChooseDialog(context, application, vhintMenu);
//                break;
//            case R.id.imgClassify:
//                Intent intent = new Intent(context, MainFragmentManager.class);
//                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_ClASSIFY_URL));
//                context.startActivity(intent);
//                break;
//        }
//    }


    public void goHome() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (homeFragment == null) {
            homeFragment = TryGoodShowFragment.newInstance(-1);
            transaction.add(R.id.content, homeFragment);
        } else {
            transaction.show(homeFragment);
        }
        mCurrentItem = 0;
        btnHome.setChecked(true);
        transaction.commitAllowingStateLoss();
    }

    public void goFee() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (feeFragment == null) {
            feeFragment = TryGoodShowFragment.newInstance(1);
            transaction.add(R.id.content, feeFragment);
        } else {
            transaction.show(feeFragment);
        }
        mCurrentItem = 1;
        btnFee.setChecked(true);
        transaction.commitAllowingStateLoss();
    }

    public void goVoucher() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (voucherFragment == null) {
            voucherFragment = TryGoodShowFragment.newInstance(2);
            transaction.add(R.id.content, voucherFragment);
        } else {
            transaction.show(voucherFragment);
        }
        mCurrentItem = 3;
        btnVoucher.setChecked(true);
        btnVoucher.setVisibility(View.VISIBLE);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 设置开启的tab购物车页面
     */
    public void goReport() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (reportFragment == null) {
            reportFragment = new TryGoodReportFragment();
            transaction.add(R.id.content, reportFragment);
        } else {
            transaction.show(reportFragment);
        }
        mCurrentItem = 2;
        btnReport.setChecked(true);
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (feeFragment != null) {
            transaction.hide(feeFragment);
        }
        if (voucherFragment != null) {
            transaction.hide(voucherFragment);
        }
        if (reportFragment != null) {
            transaction.hide(reportFragment);
        }
    }

    @OnClick({R.id.btnHome, R.id.btnFee, R.id.btnVoucher, R.id.btnReport})
    public void onBottomClick(View view) {
        switch (view.getId()) {
            case R.id.btnHome:
                goHome();
                break;
            case R.id.btnFee:
                goFee();
                break;
            case R.id.btnVoucher:
                goVoucher();
                break;
            case R.id.btnReport:
                goReport();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("index", mCurrentItem);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int savedIndex = savedInstanceState.getInt("index");
        changeFragment(savedIndex);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        int index = intent.getIntExtra("index", -1);
//        if (index >= 0 && index <= 3) {
        //为了使报告详情中的更多试用方便操作
        changeFragment(0);
//        }
    }

    private void changeFragment(int i) {
        switch (i) {
            case 0:
                goHome();
                break;
            case 1:
                goFee();
                break;
            case 2:
                goVoucher();
                break;
            case 3:
                goReport();
                break;
        }
    }

    /**
     * 获取购物车商品种类数量
     */
    private void requestCartCount() {
        Map<String, String> map = new HashMap<>();
        map.put("cartData", "");
        map.put("clientType", "android");
        map.put("token", application.getToken());
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_CART_AMOUNT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                String cartCount = JsonUtil.toString(data, "cartCount");
                Global.isCart = !TextUtils.isEmpty(cartCount) && !cartCount.equals("0");
                requestUnreadMessageCount();
            }
        }, map);
    }

    /**
     * 获取站内未读消息数量
     */
    private void requestUnreadMessageCount() {
        Map<String, String> map = new HashMap<>();
        map.put("clientType", "android");
        map.put("token", application.getToken());
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_UNREAD_MESSAGE_COUNT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                String cartCount = JsonUtil.toString(data, "count");
                Global.isUnreadMessage = !TextUtils.isEmpty(cartCount) && !cartCount.equals("0");

                if (Global.isCart || Global.isUnreadMessage) {
                    vhintMenu.setVisibility(View.VISIBLE);
                } else {
                    vhintMenu.setVisibility(View.INVISIBLE);
                }
            }
        }, map);
    }


}
