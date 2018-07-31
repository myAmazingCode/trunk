package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.PredepositWithdrawDetail;
import net.shopnc.b2b2c.android.bean.WithdrawDetail;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * @author lulei
 *         Created 2017/5/31 11:28
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 提现详情
 */
public class PredepositWithdrawDetailActivity extends BaseActivity {

    @Bind(R.id.tvNo)
    TextView tvNo;
    @Bind(R.id.tvNum)
    TextView tvNum;
    @Bind(R.id.tvBankName)
    TextView tvBankName;
    @Bind(R.id.tvAccount)
    TextView tvAccount;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvCreateTime)
    TextView tvCreateTime;
    @Bind(R.id.tvStatus)
    TextView tvStatus;
    public static final String TAG = "WithdrawDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("提现详情");
        String cashId = getIntent().getStringExtra("cashId");
        loadData(cashId);
    }

    /**
     * 会员预存款提现详情
     *
     * @param cashId
     */
    private void loadData(String cashId) {
        String url = ConstantUrl.URL_WITHDRAW_DEPOSIT_CASH_INFO;

        Map<String, String> map = new HashMap<>();
        map.put("cashId", cashId);
        map.put("token", application.getToken());

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {

            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);

                if (200 == JsonUtil.toInteger(response, "code")) {
                    PredepositWithdrawDetail withdrawDetail = new Gson().fromJson(response, PredepositWithdrawDetail.class);
                    PredepositWithdrawDetail.DatasBean datas = withdrawDetail.getDatas();
                    PredepositWithdrawDetail.DatasBean.CashInfoBean cashInfo = datas.getCashInfo();

                    tvNo.setText(cashInfo.getCashSn());
                    tvNum.setText(ShopHelper.getPriceString(cashInfo.getAmount()) + " 元");
                    tvBankName.setText(cashInfo.getReceiveCompany());
                    tvAccount.setText(cashInfo.getReceiveAccount());
                    tvName.setText(cashInfo.getReceiveUser());
                    tvCreateTime.setText(cashInfo.getAddTime());
                    tvStatus.setText(cashInfo.getStateText());

                } else {
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }

            @Override
            public void response(String data) {

            }
        }, map);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_predeposit_withdraw_detail);
    }
}
