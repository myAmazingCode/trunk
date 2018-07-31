package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.Mine;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by snm on 2016/9/23.
 */
public class FenxiaoAllActivity extends BaseActivity {

    RelativeLayout rlPointLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenxiao_all);
        setCommonHeader("分销中心");
        rlPointLog = (RelativeLayout) findViewById(R.id.rlPointLog);
    }

    public void relatGoodsClick(View view) {
        startActivity(new Intent(getApplicationContext(), FenxiaoGoodsActivity.class));
    }

    public void relatTijiaoClick(View view) {
        startActivity(new Intent(getApplicationContext(), FenxiaoSettlementActivity.class));
    }

    public void relatOrderClick(View view) {
        startActivity(new Intent(getApplicationContext(), FenxiaoOrderActivity.class));
    }

    public void relattxClick(View view) {
        startActivity(new Intent(getApplicationContext(), FenxiaoTixianActivity.class));
    }

    public void relatLiveClick(View view) {
        if (!StringUtils.isEmpty(movie_msg)) {
            T.showShort(getApplicationContext(), movie_msg);
        } else {
//            ApplyVerifyMovie();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadMemberInfo();
    }

    String movie_msg = "";

    /**
     * 初始化加载我的信息
     */
    public void loadMemberInfo() {
        String url = Constants.URL_MYSTOIRE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                String json = data.getJson();
                Logger.d(json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String objJson = obj.getString("member_info");
                        Mine bean = Mine.newInstanceList(objJson);

                        if (bean != null) {
                            if (bean.getIs_movie() != null) {
//                                if ("1".equals(bean.getIs_movie())) {
//                                    rlPointLog.setVisibility(View.VISIBLE);
//                                } else {
//                                    rlPointLog.setVisibility(View.GONE);
//                                }
                            }
                            movie_msg = bean.getIs_movie_msg();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(getApplicationContext(), json);
                }
            }
        });
    }
}
