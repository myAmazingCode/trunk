package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class OrderWuLiuActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private TextView textWuLiuCompany;
    private TextView textWuLiuId;
    private TextView textWuLiuInfo;
    private String orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_wu_liu);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("物流信息");
        myApplication = (MyShopApplication) getApplicationContext();
        orderId=getIntent().getStringExtra("order_id");
        initViews();
        loadWuLiuInfo();
    }

    public void initViews(){
        textWuLiuCompany=(TextView)findViewById(R.id.textWuLiuCompany);
        textWuLiuId=(TextView)findViewById(R.id.textWuLiuId);
        textWuLiuInfo=(TextView)findViewById(R.id.textWuLiuInfo);
    }

    public void loadWuLiuInfo(){
        String url= Constants.URL_ORDER_WULIU_INFO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("order_id", orderId);
        Log.i("QIN",orderId);
        RemoteDataHandler.asyncLoginPostDataString(url,params,myApplication,new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json=data.getJson();
                Log.i("QIN",json);
                if(data.getCode()== HttpStatus.SC_OK){
                    try{
                        JSONObject obj=new JSONObject(json);
                        textWuLiuCompany.setText(obj.getString("express_name"));
                        textWuLiuId.setText(obj.getString("shipping_code"));
                        textWuLiuInfo.setText(obj.getString("deliver_info"));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }else{
//                    ShopHelper.showApiError(OrderWuLiuActivity.this, json);
                }
            }
        });
    }


}
