package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.WuLiuCompany;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderExchangeSendGoodsActivity extends BaseActivity {
    private MyShopApplication myApplication;
 private Spinner spinner1;
    private EditText etNum;
    private Button btnSure;
    private String id;
    public String wuliu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_exchange_send_goods);
        myApplication = (MyShopApplication) getApplicationContext();
        MyExceptionHandler.getInstance().setContext(this);
        id=getIntent().getStringExtra("id");
        setCommonHeader("退货发货");
        initView();
        loadDatas();
    }


    public void initView(){
        spinner1=(Spinner)findViewById(R.id.spinner1);
        etNum=(EditText)findViewById(R.id.textNum);
        btnSure=(Button)findViewById(R.id.btnSure);
    }

    public void loadDatas(){
        String url= Constants.URL_ORDER_EXCHANGE_GOODS_SEND+"&key="+myApplication.getLoginKey()+"&return_id="+id;

        RemoteDataHandler.asyncDataStringGet(url,new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json=data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try{
                        JSONObject obj=new JSONObject(json);
                        final ArrayList<WuLiuCompany> reasons= new ArrayList<WuLiuCompany>(com.alibaba.fastjson.JSONObject.parseArray(obj.getString("express_list"), WuLiuCompany.class));
                        final ArrayList<String> list=new ArrayList();
                        for (WuLiuCompany r:reasons){
                            list.add(r.getExpress_name());
                        }
                        ArrayAdapter adapterSp=new ArrayAdapter(OrderExchangeSendGoodsActivity.this,android.R.layout.simple_spinner_dropdown_item,list);
                        spinner1.setAdapter(adapterSp);
                        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String info=list.get(i);
                                for (WuLiuCompany r:reasons){
                                    if(r.getExpress_name().equals(info)){
                                        wuliu_id=r.getExpress_id();
                                    }else{
                                        continue;
                                    }
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                        btnSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String number=etNum.getText().toString().trim();
                                btnSureSend(number);
                            }
                        });
                    }catch (JSONException e){
                        e.printStackTrace();
                    }


                }else{
                    T.showShort(OrderExchangeSendGoodsActivity.this, json);
                }

            }
        });

    }

public void btnSureSend(String number){

    String url=Constants.URL_ORDER_EXCHANGE_GOODS_SAVA;
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("key", myApplication.getLoginKey());
    params.put("return_id", id);
    params.put("express_id",wuliu_id);
    params.put("invoice_no",number);

    RemoteDataHandler.asyncLoginPostDataString(url,params,myApplication,new RemoteDataHandler.Callback() {
        @Override
        public void dataLoaded(ResponseData data) {
            String json=data.getJson();
            if(data.getCode()== HttpStatus.SC_OK){
                if (json.equals("1")) {
                    finish();
                    T.showShort(OrderExchangeSendGoodsActivity.this,"提交成功");
                }else{
                    T.showShort(OrderExchangeSendGoodsActivity.this,"提交失败");
                }

            }else{
                    ShopHelper.showApiError(OrderExchangeSendGoodsActivity.this, json);
            }
        }
    });

}

}
