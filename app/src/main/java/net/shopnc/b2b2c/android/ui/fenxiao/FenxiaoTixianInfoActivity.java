package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.Cashinfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SwpipeListViewOnScrollListener;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.custom.XListView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by snm on 2016/9/23.
 */
public class FenxiaoTixianInfoActivity extends BaseActivity {

    String tradc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianxian_info);
        setCommonHeader("提现详细");
        Intent intent = getIntent();
        tradc_id = intent.getStringExtra("id");

        LoadDate();
    }

    private void LoadDate() {
        String url = Constants.URL_CASH_INFO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("tradc_id", tradc_id);
        Logger.d(params.toString());
        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                String json = data.getJson();
                Logger.d(data.getJson());
                if (data.getCode() == HttpStatus.SC_OK) {
                    Cashinfo cashinfo = JsonUtil.getBean(json,Cashinfo.class);
                    setText2Id(R.id.tv_danhao,cashinfo.getTradc_sn());
                    setText2Id(R.id.tv_jiner,cashinfo.getTradc_amount());
                    setText2Id(R.id.tv_bank,cashinfo.getTradc_bank_name());
                    setText2Id(R.id.tv_zhanghu,cashinfo.getTradc_bank_no());
                    setText2Id(R.id.tv_skr,cashinfo.getTradc_bank_user());
                    setText2Id(R.id.tv_time,SystemHelper.getTimeStr(cashinfo.getTradc_add_time()));
//                    setText2Id(R.id.tv_state,cashinfo.);
                } else {
                    showListEmpty();
                    ShopHelper.showApiError(getApplicationContext(), json);
                }

            }
        });
    }

    public void setText2Id(int ids,String txt){
        TextView textView = (TextView)findViewById(ids);
        textView.setText(txt);
    }


}
