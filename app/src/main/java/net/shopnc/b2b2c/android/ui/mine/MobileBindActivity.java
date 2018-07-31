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
import android.widget.LinearLayout;
import android.widget.TextView;


import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.ImageCodeHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class MobileBindActivity extends BaseActivity implements ImageCodeHelper.OnImageCodeKeyListener {

    @Bind(R.id.etPhone)
    EditText mEtPhone;
    @Bind(R.id.etCode)
    EditText mEtCode;
    @Bind(R.id.ivCode)
    ImageView mIvCode;
    @Bind(R.id.btnSubmit)
    Button mBtnSubmit;
    @Bind(R.id.llEtPhone)
    LinearLayout mLlEtPhone;
    @Bind(R.id.tvPhone)
    TextView mTvPhone;
    @Bind(R.id.llTvPhone)
    LinearLayout mLlTvPhone;
    private String captchaKey;
    private int mMobileIsBind;
    private String mMobile;
    private String mEncrypt;
    private int oldSmsAuthCode;//旧手机验证码
    private int operationFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMobileIsBind = getIntent().getIntExtra("mMobileIsBind", -1);
        oldSmsAuthCode = getIntent().getIntExtra("oldSmsAuthCode", -1);
        setCommonHeader(mMobileIsBind == 1 ? "手机安全验证" : "绑定手机");

        if (mMobileIsBind == 1) {
            mLlTvPhone.setVisibility(View.VISIBLE);
            mLlEtPhone.setVisibility(View.GONE);
            mEncrypt = getIntent().getStringExtra("mMobileEncrypt");
            mMobile = getIntent().getStringExtra("mMobile");
            operationFlag = getIntent().getIntExtra("operationFlag", -1);
            mTvPhone.setText(mEncrypt);
        } else {
            mLlTvPhone.setVisibility(View.GONE);
            mLlEtPhone.setVisibility(View.VISIBLE);
        }

        ImageCodeHelper.loadImageCode(mIvCode, this);
        TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = mEtCode.getText().toString();
                String s2 = mEtPhone.getText().toString();
                if (TextUtils.isEmpty(s1) || (mMobileIsBind == 0 && TextUtils.isEmpty(s2))) {
                    mBtnSubmit.setActivated(false);
                } else {
                    mBtnSubmit.setActivated(true);
                }
            }
        };
        mEtCode.addTextChangedListener(watcher);
        if (mMobileIsBind == 0)
            mEtPhone.addTextChangedListener(watcher);
    }

    @OnClick({R.id.ivCode})
    public void loadImage() {
        ImageCodeHelper.loadImageCode(mIvCode, MobileBindActivity.this);
    }

    @OnClick({R.id.btnSubmit,})
    public void submit() {
        if (!mBtnSubmit.isActivated()) return;

        String s1 = mEtCode.getText().toString();
        final String s2 = mEtPhone.getText().toString();

        if (s1.length() != 4) {
            TToast.showShort(application, "验证码长度错误");
            return;
        }

        if (mMobileIsBind == 0 && s2.length() != 11) {
            TToast.showShort(application, "手机号长度错误");
            return;
        }

        ShopHelper.hideInputKeyboard(this, mBtnSubmit);
        /**
         * mobile String 手机号
         * captchaKey String 图片验证码标识
         * captchaVal String 图片验证码值
         * sendType String 发送类型（1表示注册 2表示登录 3表示找回密码 4表示绑定手机 5表示手机安全认证）
         */
        String url = ConstantUrl.URL_API + "/loginconnect/smscode/send" +
                "?mobile=" + (mMobileIsBind == 1 ? mMobile : s2) + "&captchaKey=" + captchaKey + "&captchaVal=" + s1 + "&sendType=" + (mMobileIsBind == 0 ? 4 : 5);

        OkHttpUtil.getAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (200 == JsonUtil.toInteger(response, "code")) {
                    //{"code":200,"datas":{"authCodeValidTime":10,"authCodeResendTime":60}}
                    Intent intent = new Intent(MobileBindActivity.this, MobileBindStep2Activity.class);
                    String authCodeValidTime = JsonUtil.toString(response, "datas", "authCodeValidTime");
                    String authCodeResendTime = JsonUtil.toString(response, "datas", "authCodeResendTime");
                    intent.putExtra("authCodeValidTime", authCodeValidTime);
                    intent.putExtra("authCodeResendTime", authCodeResendTime);
                    intent.putExtra("phone", mMobileIsBind == 1 ? mEncrypt : s2);
                    intent.putExtra("mMobileIsBind", mMobileIsBind);
                    intent.putExtra("operationFlag", operationFlag);
                    //传递旧手机验证码
                    if (oldSmsAuthCode != -1)
                        intent.putExtra("oldSmsAuthCode", oldSmsAuthCode);
                    startActivity(intent);
                    finish();
                } else {
                    ImageCodeHelper.loadImageCode(mIvCode, MobileBindActivity.this);
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        });

    }

    public static final String TAG = "MobileBindActivity";

    @Override
    protected void setView() {
        setContentView(R.layout.activity_mobile_bind2);
    }

    @Override
    public void onImageCodeKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }
}
