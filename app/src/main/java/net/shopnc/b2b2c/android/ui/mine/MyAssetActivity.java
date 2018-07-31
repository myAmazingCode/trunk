package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MyAssetActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private TextView tvPredepoit;
    private TextView tvAvailableRcBalance;
    private TextView tvVoucher;
    private TextView tvRedpacket;
    private TextView tvPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_asset);

        setCommonHeader("我的财产");

        myApplication = (MyShopApplication) getApplicationContext();
        MyExceptionHandler.getInstance().setContext(this);
        tvPredepoit = (TextView) findViewById(R.id.tvPredepoit);
        tvAvailableRcBalance = (TextView) findViewById(R.id.tvAvailableRcBalance);
        tvVoucher = (TextView) findViewById(R.id.tvVoucher);
        tvRedpacket = (TextView) findViewById(R.id.tvRedpacket);
        tvPoint = (TextView) findViewById(R.id.tvPoint);

        //预存款
        RelativeLayout rlPredeposit = (RelativeLayout) findViewById(R.id.rlPredeposit);
        rlPredeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(MyAssetActivity.this, myApplication.getLoginKey())) {
                    startActivity(new Intent(MyAssetActivity.this, PredepositActivity.class));
                }
            }
        });

        //充值卡
        RelativeLayout rlRechargeCard = (RelativeLayout) findViewById(R.id.rlRechargeCard);
        rlRechargeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(MyAssetActivity.this, myApplication.getLoginKey())) {
                    startActivity(new Intent(MyAssetActivity.this, RechargeCardLogActivity.class));
                }
            }
        });

        //代金券
        RelativeLayout rlVoucherList = (RelativeLayout) findViewById(R.id.rlVoucherList);
        rlVoucherList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(MyAssetActivity.this, myApplication.getLoginKey())) {
                    startActivity(new Intent(MyAssetActivity.this, VoucherListActivity.class));
                }
            }
        });

        //红包
        RelativeLayout rlRedpacket = (RelativeLayout) findViewById(R.id.rlRedpacketList);
        rlRedpacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(MyAssetActivity.this, myApplication.getLoginKey())) {
                    startActivity(new Intent(MyAssetActivity.this, RedpacketListActivity.class));
                }
            }
        });

        //积分
        RelativeLayout rlPointLog = (RelativeLayout) findViewById(R.id.rlPointLog);
        rlPointLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(MyAssetActivity.this, myApplication.getLoginKey())) {
                    startActivity(new Intent(MyAssetActivity.this, PointLogActivity.class));
                }
            }
        });

        loadMyAsset();
    }

    private void loadMyAsset() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_MY_ASSET, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {

                    try {
                        JSONObject obj = new JSONObject(json);
                        tvPredepoit.setText(obj.optString("predepoit") + "元");
                        tvAvailableRcBalance.setText(obj.optString("available_rc_balance") + "元");
                        tvVoucher.setText(obj.optString("voucher") + "张");
                        tvRedpacket.setText(obj.optString("redpacket") + "个");
                        tvPoint.setText(obj.optString("point") + "分");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(MyAssetActivity.this, json);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_asset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
