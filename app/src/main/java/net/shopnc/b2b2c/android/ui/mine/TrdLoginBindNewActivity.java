package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.ImageCodeHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

public class TrdLoginBindNewActivity extends BaseActivity implements ImageCodeHelper.OnImageCodeKeyListener {

    @Bind(R.id.btnBindNew)
    Button btnBindNew;
    @Bind(R.id.btnBindOld)
    Button btnBindOld;
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.ivCode)
    ImageView ivCode;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    private String mCaptchaKey;
    private String mOpenid;
    private String mAccessToken;
    private String mPhoneNum;
    private int bindType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("绑定帐号");

        btnBindNew.setActivated(true);
        ImageCodeHelper.loadImageCode(ivCode, this);
        getLoginInfo();
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etPhone.getText().toString()) || TextUtils.isEmpty(etCode.getText().toString())) {
                    btnSubmit.setActivated(false);
                } else {
                    btnSubmit.setActivated(true);
                }
            }
        };
        etPhone.addTextChangedListener(watcher);
        etCode.addTextChangedListener(watcher);
    }

    //获取登录页传递的信息
    private void getLoginInfo() {
        Intent intent = getIntent();
        mOpenid = intent.getStringExtra("openId");
        mAccessToken = intent.getStringExtra("accessToken");
        bindType = getIntent().getIntExtra("bindType", 0);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_trd_login_bind_new);
    }

    @OnClick({R.id.btnSubmit, R.id.btnBindOld, R.id.ivCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivCode:
                ImageCodeHelper.loadImageCode(ivCode, this);
                break;
            case R.id.btnBindOld:
                Intent intent = new Intent(this, TrdLoginBindOldActivity.class);
                putInfo(intent);
                startActivity(intent);
                finish();
                break;
            case R.id.btnSubmit:
                ShopHelper.hideInputKeyboard(this, view);
                btnRegSubmitClick();
                break;
        }
    }

    /*
     * 获取验证码
     */
    public void btnRegSubmitClick() {

        if (!btnSubmit.isActivated()) return;

        mPhoneNum = etPhone.getText().toString();
        final String code = etCode.getText().toString();

        //验证手机号
        if (!Common.isMobileNO(mPhoneNum)) {
            TToast.showShort(this, "请填写正确的手机号");
            return;
        }

        NCDialog ncDialog = new NCDialog(this);
        ncDialog.setText1("我们将发送验证码短信至:");
        ncDialog.setText2(mPhoneNum);
        ncDialog.showPopupWindow();

        //获取短信动态码（并验证图片验证码）
        ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
            @Override
            public void onDialogConfirm() {

                verify(mPhoneNum, code);
            }
        });
    }

    /**
     * 获取验证码。sendType String 发送类型（1表示注册 2表示登录 3表示找回密码 4表示绑定手机 5表示手机安全认证）
     *
     * @param phone
     * @param code
     */
    private void verify(String phone, String code) {
        String url = ConstantUrl.URL_API + "/loginconnect/smscode/send?" +
                "mobile=" + phone + "&captchaKey=" + mCaptchaKey + "&captchaVal=" + code + "&sendType=1";
        Log.d("LoginActivity", "verify: " + url);
        OkHttpUtil.getAsyn(this, url, new BeanCallback<String>() {

            @Override
            public void error(Call call, Exception e, int i) {
                super.error(call, e, i);
                ImageCodeHelper.loadImageCode(ivCode, TrdLoginBindNewActivity.this);
            }

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int i) {
                Log.d(TAG, "onResponse: response = " + response);
                ImageCodeHelper.loadImageCode(ivCode, TrdLoginBindNewActivity.this);
                parseCode(response);
            }
        });
    }

    public static final String TAG = "TrdBindNew";

    /**
     * 解析，
     *
     * @param response
     */
    private void parseCode(String response) {
//        {"code":200,"datas":{"authCodeValidTime":10,"authCodeResendTime":60}}
        try {
            JSONObject jsonObject = new JSONObject(response);
            int code = jsonObject.getInt("code");
            JSONObject datas = jsonObject.getJSONObject("datas");
            if (datas != null) {
                if (200 == code) {
                    int authCodeResendTime = datas.getInt("authCodeResendTime");
                    int authCodeValidTime = datas.getInt("authCodeValidTime");
                    mobileBind(authCodeResendTime, authCodeValidTime);
                } else {
                    String error = datas.getString("error");
                    TToast.showShort(this, error);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //绑定手机号，并设置密码，
    private void mobileBind(int codeResendTime, int authCodeValidTime) {
        Intent intent = new Intent(this, TrdLoginBindStep2Activity.class);
        intent.putExtra("phone", mPhoneNum);
        intent.putExtra("sms_time", codeResendTime);
        intent.putExtra("valid_time", authCodeValidTime);//分钟
        putInfo(intent);
        startActivity(intent);
        finish();
    }

    private void putInfo(Intent intent) {
        intent.putExtra("accessToken", mAccessToken);
        intent.putExtra("openId", mOpenid);
        intent.putExtra("bindType", bindType);
    }

    @Override
    public void onImageCodeKey(String captchaKey) {
        mCaptchaKey = captchaKey;
    }
}
