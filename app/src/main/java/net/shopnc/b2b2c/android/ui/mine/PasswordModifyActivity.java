package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class PasswordModifyActivity extends BaseActivity {

    @Bind(R.id.etPassword)
    EditText mEtPassword;
    @Bind(R.id.etPasswordConfirm)
    EditText mEtPasswordConfirm;
    @Bind(R.id.btnSubmit)
    Button mBtnSubmit;
    private int mOperationFlag;
    private String mPwd;
    private String mPwdRepeat;
    private String smsAuthCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOperationFlag = getIntent().getIntExtra("operationFlag", -1);
        smsAuthCode = getIntent().getStringExtra("smsAuthCode");
        setCommonHeader(mOperationFlag == 100 ? "修改登录密码" : "设置支付密码");

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPwd = mEtPassword.getText().toString();
                mPwdRepeat = mEtPasswordConfirm.getText().toString();
                if (TextUtils.isEmpty(mPwd) || TextUtils.isEmpty(mPwdRepeat)) {
                    mBtnSubmit.setActivated(false);
                } else {
                    mBtnSubmit.setActivated(true);
                }
            }
        };

        mEtPassword.addTextChangedListener(textWatcher);
        mEtPasswordConfirm.addTextChangedListener(textWatcher);

    }

    @OnClick({R.id.btnSubmit})
    public void submit() {
        if (!mBtnSubmit.isActivated()) return;

        if (!mPwd.equals(mPwdRepeat)) {
            TToast.showShort(application, "密码输入不一致");
            return;
        }

        ShopHelper.hideInputKeyboard(this,mBtnSubmit);

        String url = ConstantUrl.URL_API + "/member/security/edit/" + (mOperationFlag == 100 ? "pwd" : "payPwd");

        Map<String, String> map = new HashMap<>();
        map.put("token", application.getToken());
        map.put("smsAuthCode", smsAuthCode);
        if (mOperationFlag == 100) {
            map.put("memberPwd", mPwd);
            map.put("memberPwdRepeat", mPwdRepeat);
        } else {
            map.put("payPwd", mPwd);
            map.put("payPwdRepeat", mPwdRepeat);
        }

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (200 == JsonUtil.toInteger(response, "code")) {
                    TToast.showShort(application, "操作成功");
                    finish();

                } else {
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, map);

    }

    public static final String TAG = "ModifyPWD";

    @Override
    protected void setView() {
        setContentView(R.layout.activity_password_modify);
    }
}
