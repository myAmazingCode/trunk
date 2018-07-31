package net.shopnc.b2b2c.android.ui.promotion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;

public class AuthenticationStep2Activity extends BaseActivity {

    @Bind(R.id.tvIndicatorStep2)
    TextView mTvIndicatorStep2;
    @Bind(R.id.tvStep1)
    TextView mTvStep1;
    @Bind(R.id.tvStep2)
    TextView mTvStep2;
    @Bind(R.id.rbBank)
    RadioButton mRbBank;
    @Bind(R.id.rbAlipay)
    RadioButton mRbAlipay;
    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.etAccount)
    EditText mEtAccount;
    @Bind(R.id.etBankName)
    EditText mEtBankName;
    @Bind(R.id.llBank)
    View llBank;
    @Bind(R.id.tvToStep3)
    TextView mTvNextStep;

    private String realName;
    private String bindPhone;
    private String idCartNumber;
    private String idCartFrontImage;
    private String idCartBackImage;
    private String idCartHandImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCommonHeader("个人实名认证");
        mTvStep2.setSelected(true);
        mTvIndicatorStep2.setVisibility(View.VISIBLE);

        getAuthInfo();

        mRbAlipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llBank.setVisibility(View.GONE);
                    mRbBank.setChecked(false);
                } else {
                    llBank.setVisibility(View.VISIBLE);
                    mRbBank.setChecked(true);
                }

                verify();
            }
        });

        initTextWatcher();

        retainInput();
    }

    private void retainInput() {
        SharedPreferences sp = getSharedPreferences("auth", MODE_PRIVATE);
        payPerson = sp.getString("payPerson", "");
        bankAccountNumber = sp.getString("bankAccountNumber", "");
        bankAccountName = sp.getString("bankAccountName", "");
        accountType = sp.getString("accountType", "bank");

        mEtName.setText(payPerson);
        mEtAccount.setText(bankAccountNumber);
        mEtBankName.setText(bankAccountName);
        mRbBank.setChecked("bank".equals(accountType));
        mRbAlipay.setChecked("alipay".equals(accountType));
    }

    private void initTextWatcher() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                verify();

            }
        };

        mEtName.addTextChangedListener(watcher);
        mEtAccount.addTextChangedListener(watcher);
        mEtBankName.addTextChangedListener(watcher);
    }

    public static final String TAG = "AuthStep2";

    private void verify() {

        boolean b = TextUtils.isEmpty(mEtName.getText().toString()) ||
                TextUtils.isEmpty(mEtAccount.getText().toString());

        if (mRbBank.isChecked()) {

            boolean empty = TextUtils.isEmpty(mEtBankName.getText().toString());
            if (b || empty) {
                mTvNextStep.setEnabled(false);
            } else {
                mTvNextStep.setEnabled(true);
            }
        } else {

            if (b) {
                mTvNextStep.setEnabled(false);
            } else {
                mTvNextStep.setEnabled(true);
            }
        }
    }

    private void getAuthInfo() {
        Intent intent = getIntent();
        realName = intent.getStringExtra("realName");
        bindPhone = intent.getStringExtra("bindPhone");
        idCartNumber = intent.getStringExtra("idCartNumber");
        idCartFrontImage = intent.getStringExtra("idCartFrontImage");
        idCartBackImage = intent.getStringExtra("idCartBackImage");
        idCartHandImage = intent.getStringExtra("idCartHandImage");
    }

    @OnClick({R.id.tvStep1, R.id.tvToStep3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvStep1:
                getInput();
                saveAuthInfo();
                finish();
                break;
            case R.id.tvToStep3:
                getInput();
                submit();
                break;
        }
    }

    /*
    token String 当前登录令牌
bindPhone string 申请时填写的手机号
realName string 申请时填写的真实姓名
idCartNumber string 身份证号
idCartFrontImage string 身份证正面照（文件相对路径，如：“/image/xxxx.jpg”）
idCartBackImage string 身份证反面照（文件相对路径，如：“/image/xxxx.jpg”）
idCartHandImage string 手持身份证照（文件相对路径，如：“/image/xxxx.jpg”）
payPerson string 银行收款人姓名
bankAccountName string 开户银行名
bankAccountNumber string 银行账号
accountType string 账户类型 (“bank” : 银行 ， “alipay”:支付宝)
     */
    private void submit() {
        String url = ConstantUrl.URL_API + "/member/distributor/join/save";

        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("bindPhone", bindPhone);
        params.put("realName", realName);
        params.put("idCartNumber", idCartNumber);
        params.put("idCartFrontImage", idCartFrontImage);
        params.put("idCartBackImage", idCartBackImage);
        params.put("idCartHandImage", idCartHandImage);
        params.put("payPerson", payPerson);
        params.put("bankAccountName", bankAccountName);
        params.put("bankAccountNumber", bankAccountNumber);
        params.put("accountType", accountType);

        Log.d(TAG, "submit: url = " + url);
        Set<String> keys = params.keySet();
        for (String key : keys) {
            Log.d(TAG, "submit: key = " + key + ",val = " + params.get(key));
        }

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);

                if (200 == JsonUtil.toInteger(response, "code")) {
                    startActivity(new Intent(AuthenticationStep2Activity.this, AuthenticationStep3Activity.class));
                    finish();
                } else {
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, params);
    }

    private String payPerson;
    private String bankAccountNumber;
    private String bankAccountName;
    private String accountType = "alipay";


    private void getInput() {
        payPerson = mEtName.getText().toString();
        bankAccountNumber = mEtAccount.getText().toString();

        if (mRbBank.isChecked()) {
            bankAccountName = mEtBankName.getText().toString();
            accountType = "bank";
        } else {
            bankAccountName = "";
            accountType = "alipay";
        }

    }

    private void saveAuthInfo() {
        SharedPreferences sp = getSharedPreferences("auth", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("payPerson", payPerson);
        editor.putString("bankAccountNumber", bankAccountNumber);
        if (mRbBank.isChecked())
            editor.putString("bankAccountName", bankAccountName);
        editor.putString("accountType", accountType);

        editor.apply();
    }


    @Override
    protected void setView() {
        setContentView(R.layout.activity_authentication_step2);
    }
}
