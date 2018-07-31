package net.shopnc.b2b2c.android.ui.store;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.StoreCredit;
import net.shopnc.b2b2c.android.bean.StoreIndex;
import net.shopnc.b2b2c.android.bean.StoreIntroduce;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonFastUtil;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.OkHttpUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 店铺介绍
 *
 * Created by 胡婷 on 2016/1/6.
 */
public class StoreIntroduceActivity extends BaseActivity implements View.OnClickListener{
    private MyShopApplication myApplication;

    private ImageView imgStore;
    private TextView textStoreName;
    private TextView textStoreType;
    private TextView textStoreTy;
    private TextView textNoCollect;
    private TextView textCollect;
    private TextView textFans;

    private TextView textInfo;
    private TextView textFuwu;
    private TextView textW;

    private TextView textComPanyName;
    private TextView textComPanyAddr;
    private TextView textComPanyTime;

    private TextView textPhone;
    private ImageView imgPhone;
    private TextView textWorkTime;

    private int count = 0;
    private TimerTask task;
    private Timer delayTimer;
    private long interval = 500;
    private long firstTime = 0;

    private String store_id;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 点击事件结束后的事件处理
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (count == 1) {
            } else if (count > 1) {
            }
            delayTimer.cancel();
            count = 0;
            String key = myApplication.getLoginKey();
            int id = msg.arg1;
            if (id == 0) {
                AddFav();
            } else {
                DeleteFav();
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_introduce);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("店铺介绍");
        myApplication = (MyShopApplication) getApplicationContext();
        store_id = getIntent().getStringExtra("store_id");

        initView();
        initData();
    }

    private void initView(){
        imgStore =(ImageView)this.findViewById(R.id.imgStore);
        textStoreName =(TextView)this.findViewById(R.id.textStoreName);
        textStoreType =(TextView)this.findViewById(R.id.textStoreType);
        textStoreTy =(TextView)this.findViewById(R.id.textStoreTy);
        textCollect =(TextView)this.findViewById(R.id.textCollect);
        textNoCollect =(TextView)this.findViewById(R.id.textNoCollect);
        textFans =(TextView)this.findViewById(R.id.textFans);

        textInfo =(TextView)this.findViewById(R.id.textInfo);
        textFuwu =(TextView)this.findViewById(R.id.textFuwu);
        textW =(TextView)this.findViewById(R.id.textW);

        textComPanyName =(TextView)this.findViewById(R.id.textComPanyName);
        textComPanyAddr =(TextView)this.findViewById(R.id.textComPanyAddr);
        textComPanyTime =(TextView)this.findViewById(R.id.textComPanyTime);

        textPhone =(TextView)this.findViewById(R.id.textPhone);
        textWorkTime =(TextView)this.findViewById(R.id.textWorkTime);
        imgPhone =(ImageView)this.findViewById(R.id.imgPhone);

        textCollect.setOnClickListener(this);
        textNoCollect.setOnClickListener(this);
    }


    private void initData(){
        OkHttpUtil.postAsyn(this, Constants.URL_STORE_INTRODUCE, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                ShopHelper.showMessage(StoreIntroduceActivity.this, "加载失败");
            }

            @Override
            public void onResponse(String response) {
                List<StoreCredit> creditsList = new ArrayList<StoreCredit>();
                ResponseData data = JsonFastUtil.fromJsonFast(response, ResponseData.class);
                if (data.getCode() == HttpStatus.SC_OK) {
                    String datas = data.getDatas();
                    if (!datas.equals("") && !datas.equals("{}") && datas != null) {
                        try {
                            JSONObject obj = new JSONObject(datas);
                            String store_info = obj.getString("store_info");

                            StoreIntroduce storeIntroduce = JsonFastUtil.fromJsonFast(store_info, StoreIntroduce.class);

                            JSONObject credit = new JSONObject(storeIntroduce.getStore_credit());
                            String store_desccredit = credit.getString("store_desccredit");//描述
                            String store_deliverycredit = credit.getString("store_deliverycredit");//物流
                            String store_servicecredit = credit.getString("store_servicecredit");//服务

                            StoreCredit desccredit = JsonFastUtil.fromJsonFast(store_desccredit, StoreCredit.class);
                            StoreCredit deliverycredit = JsonFastUtil.fromJsonFast(store_deliverycredit, StoreCredit.class);
                            StoreCredit servicecredit = JsonFastUtil.fromJsonFast(store_servicecredit, StoreCredit.class);

                            creditsList.add(desccredit);
                            creditsList.add(servicecredit);
                            creditsList.add(deliverycredit);

                            LogHelper.d("huting--storeIntroduce", storeIntroduce.toString());
                            LogHelper.d("huting--desccredit", desccredit.toString());
                            LogHelper.d("huting--deliverycredit", deliverycredit.toString());
                            LogHelper.d("huting--servicecredit", servicecredit.toString());

                            //显示店铺简介
                            showStoreIntro(storeIntroduce, creditsList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    ShopHelper.showMessage(StoreIntroduceActivity.this, "暂时没有店铺信息");
                }
            }
        }, new OkHttpUtil.Param("store_id", store_id));
    }


    /**
     * 店铺简介
     * @param storeIntroduce
     * @param creditsList
     */
    private void showStoreIntro(StoreIntroduce storeIntroduce,List<StoreCredit> creditsList){
        LoadImage.loadImg(this,imgStore,storeIntroduce.getStore_avatar());

        textStoreName.setText(storeIntroduce.getStore_name());
        textStoreType.setText("类型："+storeIntroduce.getSc_name());
        if (storeIntroduce.getIs_own_shop().equals("false")){
            textStoreTy.setText("普通店铺");
        }else{
            textStoreTy.setText("营业店铺");
        }

        textFans.setText(storeIntroduce.getStore_collect() + "位粉丝");

        textInfo.setText(creditsList.get(0).getCredit() + "    与同行业" + creditsList.get(0).getPercent_text() + "   " +creditsList.get(0).getPercent());
        textFuwu.setText(creditsList.get(1).getCredit() + "    与同行业" + creditsList.get(1).getPercent_text() + "   " +creditsList.get(1).getPercent());
        textW.setText(creditsList.get(2).getCredit() + "    与同行业" + creditsList.get(2).getPercent_text() + "   " +creditsList.get(2).getPercent());

        textComPanyName.setText(storeIntroduce.getStore_company_name());
        textComPanyAddr.setText(storeIntroduce.getArea_info());

        textComPanyTime.setText(storeIntroduce.getStore_time_text());

        textPhone.setText(storeIntroduce.getStore_phone());
        if (!textPhone.getText().equals("")){
            doCallClick(textPhone.getText().toString().trim());
        }

        textWorkTime.setText(storeIntroduce.getStore_workingtime());

        getIsCollect();
    }

    /**
     * 判断用户是否收藏店铺
     */
    private void getIsCollect(){
        String url = Constants.URL_STORE_INFO + "&store_id=" + store_id + "&key=" + myApplication.getLoginKey();

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String store_info = obj.getString("store_info");
                        StoreIndex storeInFo = StoreIndex.newInstanceList(store_info);

                        if (storeInFo != null) {
                            //显示店铺是否收藏
                            if (storeInFo.getIs_favorate().equals("true")) {
                                textCollect.setVisibility(View.GONE);
                                textNoCollect.setVisibility(View.VISIBLE);
                            } else {
                                textCollect.setVisibility(View.VISIBLE);
                                textNoCollect.setVisibility(View.GONE);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                }
            }
        });
    }

    private void doCallClick(final String phone){//启动拨号
        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);

            }
        });
    }


    /**
     * 添加店铺收藏
     */
    public void AddFav() {
        String url = Constants.URL_STORE_ADD_FAVORITES;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("store_id", store_id);
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        Toast.makeText(StoreIntroduceActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        initData();
                    }
                } else {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String error = obj2.getString("error");
                        if (error != null) {
                            Toast.makeText(StoreIntroduceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 删除店铺收藏
     */
    public void DeleteFav() {
        String url = Constants.URL_STORE_DELETE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("store_id", store_id);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        Toast.makeText(StoreIntroduceActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                        initData();
                    }
                } else {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String error = obj2.getString("error");
                        if (error != null) {
                            Toast.makeText(StoreIntroduceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textCollect:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime <= interval) {
                    ++count;
                } else {
                    count = 1;
                }
                delay(0);
                firstTime = secondTime;

                break;

            case R.id.textNoCollect:
                long secondTime2 = System.currentTimeMillis();
                if (secondTime2 - firstTime <= interval) {
                    ++count;
                } else {
                    count = 1;
                }
                delay(1);
                firstTime = secondTime2;
                break;
        }
    }

    //延迟时间是连击的时间间隔有效范围
    private void delay(final int cns_id) {
        if (task != null)
            task.cancel();

        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = cns_id;
                handler.sendMessage(message);
            }
        };
        delayTimer = new Timer();
        delayTimer.schedule(task, interval);
    }
}
