package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.FenxiaoGoods;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by snm on 2016/9/23.
 */
public class FenxiaoWithdrawalsActivity extends BaseActivity {

    private TextView tv_keyong,tv_box_applay;
    private EditText etjiner,etpass;
    private String price = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawals_main);
        Intent intent = getIntent();
        price = intent.getStringExtra("price");

        setCommonHeader("申请提现");
        initView();
    }
    public void initView(){
        tv_keyong = (TextView)findViewById(R.id.tv_keyong);
        tv_box_applay = (TextView)findViewById(R.id.tv_box_applay);
        etjiner = (EditText) findViewById(R.id.etjiner);
        etpass = (EditText)findViewById(R.id.etpass);
        tv_box_applay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jiner = etjiner.getText().toString().trim();
                String pass = etpass.getText().toString().trim();
                if(isImplay(jiner,pass)){
                    cash_apply(jiner,pass);
                }
            }
        });
        tv_keyong.setText("可提现金额 ￥" + price);
    }
    public Boolean isImplay(String jiner,String pass){
        if(StringUtils.isEmpty(jiner)){
            T.showShort(getApplicationContext(),"输入的金额不能为空");
            return false;
        }
        if(StringUtils.isEmpty(pass)){
            T.showShort(getApplicationContext(),"输入的密码不能为空");
            return false;
        }
        return true;
    }
    public void cash_apply(String jiner,String pass){
        String url = Constants.URL_CASH_APPLY;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("cash_amount", jiner);
        params.put("pay_pwd", pass);
        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                String json = data.getJson();
                Logger.d(data.toString());
                if (data.getCode() == HttpStatus.SC_OK) {
                    if("1".equals(json)){
                        T.showShort(getApplicationContext(),"提现成功");
                        finish();
                    }else {
                        T.showShort(getApplicationContext(),"提现失败");
                    }
                } else {
                    ShopHelper.showApiError(getApplicationContext(), json);
                }
            }
        });
    }

}
