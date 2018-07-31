package net.shopnc.b2b2c.android.ui.promotion;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.WithdrawDetail;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class WithdrawDetailActivity extends BaseActivity {

    public static final String TAG = "WithdrawDetail";
    @Bind(R.id.tvNo)
    TextView mTvNo;
    @Bind(R.id.tvNum)
    TextView mTvNum;
    @Bind(R.id.tvAccountType)
    TextView mTvAccountType;
    @Bind(R.id.tvAccount)
    TextView mTvAccount;
    @Bind(R.id.tvName)
    TextView mTvName;
    @Bind(R.id.tvBankName)
    TextView mTvBankName;
    @Bind(R.id.tvCreateTime)
    TextView mTvCreateTime;
    @Bind(R.id.tvStatus)
    TextView mTvStatus;
    @Bind(R.id.tvPayTime)
    TextView mTvPayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("提现详情");
        String cashId = getIntent().getStringExtra("cashId");
        loadData(cashId);
    }

    private void loadData(final String cashId) {
        String url = ConstantUrl.URL_API + "/member/distributor/commission/cash/info";

        Map<String, String> map = new HashMap<>();
        map.put("cashId", cashId);
        map.put("token", application.getToken());

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);

                if (200 == JsonUtil.toInteger(response, "code")) {
                    WithdrawDetail withdrawDetail = new Gson().fromJson(response, WithdrawDetail.class);
                    WithdrawDetail.DatasBean datas = withdrawDetail.getDatas();
                    WithdrawDetail.DatasBean.CashInfoBean cashInfo = datas.getCashInfo();
                    mTvNo.setText(cashInfo.getCashSn());
                    mTvNum.setText(cashInfo.getAmount() + "元");
                    mTvAccountType.setText(cashInfo.getAccountType().equals("bank") ? "银行" : "其他");
                    mTvAccount.setText(cashInfo.getBankAccountNumber());
                    mTvName.setText(cashInfo.getPayPerson());
                    mTvBankName.setText(cashInfo.getBankAccountName());
                    mTvCreateTime.setText(cashInfo.getAddTime());
                    int state = cashInfo.getState();
                    String desc = "";
                    switch (state) {
                        case 0:
                            desc = "未处理";
                            break;
                        case 1:
                            desc = "提现成功";
                            break;
                        case 2:
                            desc = "提现失败";
                            break;
                    }

                    mTvStatus.setText(desc);
                    mTvPayTime.setText(cashInfo.getPayTime());
                } else {
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, map);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_withdraw_detail);
    }
}
