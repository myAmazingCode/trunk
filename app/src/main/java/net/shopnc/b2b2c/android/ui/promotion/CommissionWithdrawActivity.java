package net.shopnc.b2b2c.android.ui.promotion;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.WithdrawApply;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class CommissionWithdrawActivity extends BaseActivity {

    @Bind(R.id.tvBalance)
    TextView mTvBalance;
    @Bind(R.id.tvAccountType)
    TextView mTvAccountType;
    @Bind(R.id.tvAccountName)
    TextView mTvAccountName;
    @Bind(R.id.tvAccountNum)
    TextView mTvAccountNum;
    @Bind(R.id.etNum)
    EditText mEtNum;
    @Bind(R.id.etPwd)
    EditText mEtPwd;
    @Bind(R.id.tvApply)
    TextView mTvApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("推广佣金提现");
        initData();

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = mEtNum.getText().toString();
                String s2 = mEtPwd.getText().toString();

                if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)) {
                    mTvApply.setActivated(false);
                } else {
                    mTvApply.setActivated(true);
                }
            }
        };
        mEtNum.addTextChangedListener(watcher);
        mEtPwd.addTextChangedListener(watcher);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String url = ConstantUrl.URL_API + "/member/distributor/commission/cash/apply";
        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

                WithdrawApply withdrawApply = JsonUtil.toBean(data, WithdrawApply.class);
                WithdrawApply.DistributorBean distributor = withdrawApply.getDistributor();

                if (distributor != null) {
                    String accountType = distributor.getAccountType();
                    mTvAccountType.setText("alipay".equals(accountType) ? "支付宝" : "银行");
                    mTvAccountName.setText(distributor.getPayPerson());
                    mTvAccountNum.setText(distributor.getBankAccountNumber());

                    double commissionAvailable = distributor.getCommissionAvailable();
                    mTvBalance.setText(String.format("%.2f", commissionAvailable));

                }
            }
        }, new OkHttpUtil.Param("token", application.getToken()));
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_commission_withdraw);
    }

    @OnClick({R.id.tvApply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvApply:
                apply();
                break;
        }
    }

    private void apply() {
        String url = ConstantUrl.URL_API + "/member/distributor/commission/cash/save";
        if (!mTvApply.isActivated())return;

        String payPwd = mEtPwd.getText().toString();
        String amount = mEtNum.getText().toString();

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

                TToast.showShort(application,"申请提现成功");
                String cashId = JsonUtil.toString(data, "cashId");
                Intent intent = new Intent(CommissionWithdrawActivity.this, WithdrawDetailActivity.class);
                intent.putExtra("cashId",cashId);
                startActivity(intent);
                finish();

            }
        }, new OkHttpUtil.Param("token", application.getToken())
        ,new OkHttpUtil.Param("payPwd",payPwd),new OkHttpUtil.Param("amount",amount));
    }
}
