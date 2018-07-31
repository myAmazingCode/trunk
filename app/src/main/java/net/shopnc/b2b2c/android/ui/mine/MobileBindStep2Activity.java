package net.shopnc.b2b2c.android.ui.mine;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
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
import net.shopnc.b2b2c.android.util.Global;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class MobileBindStep2Activity extends BaseActivity implements ShopHelper.OnCountDownFinishListenser, ImageCodeHelper.OnImageCodeKeyListener {

    @Bind(R.id.tvPhone)
    TextView mTvPhone;
    @Bind(R.id.etVerifyCode)
    EditText mEtVerifyCode;
    @Bind(R.id.btnGetSmsCaptcha)
    Button mBtnGetSmsCaptcha;
    @Bind(R.id.etCode)
    EditText mEtCode;
    @Bind(R.id.ivCode)
    ImageView mIvCode;
    @Bind(R.id.llSecCode)
    LinearLayout llSecCode;
    @Bind(R.id.btnLogin)
    Button mBtnLogin;

    private String mCaptchaKey;
    private String phoneNumber;
    private int mMobileIsBind;
    private int oldSmsAuthCode;
    private int operationFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMobileIsBind = getIntent().getIntExtra("mMobileIsBind", -1);
        operationFlag = getIntent().getIntExtra("operationFlag", -1);
        oldSmsAuthCode = getIntent().getIntExtra("oldSmsAuthCode", -1);
        setCommonHeader(mMobileIsBind == 1 ? "手机安全验证" : "绑定手机");
        mBtnLogin.setText(mMobileIsBind == 1 ? "下一步" : "确认提交");


        ////{"code":200,"datas":{"authCodeValidTime":10,"authCodeResendTime":60}}
        String authCodeValidTime = getIntent().getStringExtra("authCodeValidTime");
        String authCodeResendTime = getIntent().getStringExtra("authCodeResendTime");
        phoneNumber = getIntent().getStringExtra("phone");

        ShopHelper.btnSmsCaptchaCountDown(this, mBtnGetSmsCaptcha, Integer.parseInt(authCodeResendTime), this);
        mTvPhone.setText(String.format("请输入%s收到的验证码，在%s分钟内有效。", phoneNumber, "" + authCodeValidTime));

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean codeEmpty = TextUtils.isEmpty(mEtVerifyCode.getText().toString());

                if (codeEmpty) {
                    mBtnLogin.setActivated(false);
                } else {
                    mBtnLogin.setActivated(true);
                }
            }
        };
        mEtVerifyCode.addTextChangedListener(watcher);


    }

    public void loadCode(View view) {
        ImageCodeHelper.loadImageCode(mIvCode, this);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_mobile_bind_step2);
    }

    @OnClick({R.id.btnLogin})
    public void submit() {
        if (mBtnLogin.isActivated()) {

            String code = mEtVerifyCode.getText().toString();
            if (code.length() != 6) {
                TToast.showShort(application, "验证码长度错误");
                return;
            }

            ShopHelper.hideInputKeyboard(this, mBtnLogin);

            if (mMobileIsBind == 0) {

                bind(code);
            } else {

                if (operationFlag == -1) {
                    Intent intent = new Intent(this, MobileBindActivity.class);
                    intent.putExtra("mMobileIsBind", 0);
                    intent.putExtra("oldSmsAuthCode", Integer.parseInt(code));
                    startActivity(intent);
                } else if (operationFlag == Global.OPERATIONFLAG_WITHDRAWDEPOSIT) {
                    Intent intent = new Intent(this, MineWithDrawDepositActivity.class);
                    intent.putExtra("smsAuthCode", code);
                    intent.putExtra("operationFlag", operationFlag);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, PasswordModifyActivity.class);
                    intent.putExtra("smsAuthCode", code);
                    intent.putExtra("operationFlag", operationFlag);
                    startActivity(intent);
                }
                finish();
            }
        }
    }

    private void bind(String code) {
        String url = ConstantUrl.URL_API + "/member/security/edit/mobile";
        Map<String, String> map = new HashMap<>();
        map.put("token", application.getToken());
        map.put("oldSmsAuthCode", oldSmsAuthCode == -1 ? "" : "" + oldSmsAuthCode);
        map.put("newMemberMobile", phoneNumber);
        map.put("newSmsAuthCode", code);

        OkHttpUtil.postAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int i) {
                //{"code":400,"datas":{"error":"动态码错误或已过期，重新输入"}}
                //{"code":200,"datas":{"success":"操作成功"}}
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

    @OnClick({R.id.btnGetSmsCaptcha})
    public void getSMS() {
        String code = mEtCode.getText().toString();
        Log.d(TAG, "btnGetSmsCaptchaClick2: code = " + code);
        if (llSecCode.getVisibility() == View.VISIBLE && TextUtils.isEmpty(code)) {
            TToast.showShort(application, "请输入图片验证码");
            return;
        }

        String url = ConstantUrl.URL_API + "/loginconnect/smscode/send?" +
                "mobile=" + phoneNumber + "&captchaKey=" + mCaptchaKey + "&captchaVal=" + code + "&sendType=4";
        Log.d(TAG, "btnGetSmsCaptchaClick2: url = " + url);
        OkHttpUtil.getAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
                parseCode(response);
//                    Log.d("MobileLoginActivity", "onResponse: " + response);
            }
        });
    }

    /**
     * 重新获取验证码按钮点击
     * /loginconnect/smscode/send/simple
     * <p>
     * mobile String 手机号
     * sendType String 发送类型（1表示注册 2表示登录 3表示找回密码 4表示绑定手机 5表示手机安全认证）
     */

    public static final String TAG = "Bind";

    //重新获取验证码
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
                    mTvPhone.setText(String.format("请输入%s收到的验证码，在%s分钟内有效。", phoneNumber, "" + authCodeValidTime));
                    ShopHelper.btnSmsCaptchaCountDown(this, mBtnGetSmsCaptcha, authCodeResendTime, this);
                    hideSecCode();//隐藏验证码模块
                } else {
                    String error = datas.getString("error");
                    TToast.showShort(application, error);
                    ImageCodeHelper.loadImageCode(mIvCode, MobileBindStep2Activity.this);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //获取验证码成功后，隐藏图片验证码
    private void hideSecCode() {
        mEtCode.setText("");
        ObjectAnimator animator = ObjectAnimator.ofFloat(llSecCode, "translationX", 0, -1200).setDuration(1000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getCurrentPlayTime() >= 1000) {
                    llSecCode.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onCountDownFinish() {
        showImgCode();
    }

    //显示图片验证码
    private void showImgCode() {
        if (llSecCode != null) {
            llSecCode.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(llSecCode, "translationX", -1200, 0).setDuration(1000);
            animator.setInterpolator(new BounceInterpolator());
            animator.start();
            ImageCodeHelper.loadImageCode(mIvCode, this);
        }
    }

    @Override
    public void onImageCodeKey(String captchaKey) {
        mCaptchaKey = captchaKey;
    }
}
