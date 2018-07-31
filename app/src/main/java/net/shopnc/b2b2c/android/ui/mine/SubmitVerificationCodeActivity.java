package net.shopnc.b2b2c.android.ui.mine;


import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.AuthCode;
import net.shopnc.b2b2c.android.bean.MemberInfo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.http.ImageCodeHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.good.GoodHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;

import static net.shopnc.b2b2c.android.common.http.JsonUtil.gson;

/**
 * @author lulei
 *         Created 2017/5/9 17:18
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * 提交验证码
 */
public class SubmitVerificationCodeActivity extends BaseActivity implements ImageCodeHelper.OnImageCodeKeyListener {

    @Bind(R.id.tvGet)
    TextView tvGet;
    @Bind(R.id.llSMSdynamiccode)
    LinearLayout llSMSdynamiccode;
    @Bind(R.id.etSMSdynamiccode)
    EditText etSMSdynamiccode;
    @Bind(R.id.tvGetCodeAgain)
    TextView tvGetCodeAgain;
    @Bind(R.id.ivCode)
    ImageView ivCode;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.llCode)
    LinearLayout llCode;
    @Bind(R.id.etSetPwd)
    EditText etSetPwd;
    @Bind(R.id.etSurePwd)
    EditText etSurePwd;
    @Bind(R.id.llSubmitCode)
    LinearLayout llSubmitCode;
    @Bind(R.id.btnNext)
    Button btnNext;
    @Bind(R.id.tvTimer)
    TextView tvTimer;
    @Bind(R.id.llSetPwd)
    LinearLayout llSetPwd;
    @Bind(R.id.llSurePwd)
    LinearLayout llSurePwd;

    private String mCaptchaKey;
    private String mCaptchaVal;

    int authCodeValidTime;//动态码的有效时间（分钟）
    int authCodeResendTime;//动态码的重发间隔秒数

    String SMSdynamiccode;//动态码
    String mobile;    //手机号
    String captchaKey;//验证码标识
    String captchaVal;//验证码值
    String password;//设置密码
    String password_confirm;//确认密码

    public static final int TYPE_REGISTER = 1;
    public static final int TYPE_LOGIN = 2;
    int type = 0;
    boolean isReg = false;//是否注册
    boolean isCode = false;//是否重新获取动态码

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //计时器
            authCodeResendTime--;
            tvTimer.setText(Html.fromHtml("（等待<font color=\"#ED5968\">" + authCodeResendTime + "</font>秒后)"));
            if (authCodeResendTime == 0) {
                llCode.setVisibility(View.VISIBLE);
                llSMSdynamiccode.setVisibility(View.GONE);
                handler.removeCallbacks(runnable);
                isCode = true;
            }
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("提交验证码");
        initView();
        initETWatcher();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_submit_verification_code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        type = getIntent().getIntExtra("type", 0);
        mobile = getIntent().getStringExtra("mobile");
        String phoneNumber = mobile;
        authCodeValidTime = getIntent().getIntExtra("authCodeValidTime", 0);
        authCodeResendTime = getIntent().getIntExtra("authCodeResendTime", 0);
        tvGet.setText("短信已发送至" + phoneNumber.replace(phoneNumber.substring(3, 7), "****") + "需" + authCodeValidTime + "分钟内完成验证");
        llCode.setVisibility(View.GONE);
        tvTimer.setText(Html.fromHtml("（等待<font color=\"#ED5968\">" + authCodeResendTime + "</font>秒后)"));
        llSMSdynamiccode.setVisibility(View.VISIBLE);
        ImageCodeHelper.loadImageCode(ivCode, this);
        handler.postDelayed(runnable, 1000);
        if (type == SubmitVerificationCodeActivity.TYPE_REGISTER) {
            llSetPwd.setVisibility(View.VISIBLE);
            llSurePwd.setVisibility(View.VISIBLE);
            btnNext.setText("下一步");
            isReg = true;
        } else if (type == SubmitVerificationCodeActivity.TYPE_LOGIN) {
            llSetPwd.setVisibility(View.GONE);
            llSurePwd.setVisibility(View.GONE);
            btnNext.setText("登录");
            isReg = false;
        }

        btnNext.setSelected(false);
    }

    private void initETWatcher() {
        etSMSdynamiccode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SMSdynamiccode = s.toString();
                if (isReg) {
                    setBtnRegSubmitSelected();
                } else {
                    setBtnLoginSelected();
                }
            }
        });

        etSetPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
                setBtnRegSubmitSelected();
            }
        });

        etSurePwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password_confirm = s.toString();
                setBtnRegSubmitSelected();
            }
        });

    }


    /**
     * 下一步按钮是否可以点击
     */
    private void setBtnRegSubmitSelected() {
        if (Common.isNotEmpty(SMSdynamiccode, password, password_confirm)) {
            btnNext.setSelected(true);
        } else {
            btnNext.setSelected(false);
        }
    }

    /**
     * 登录按钮是否可以点击
     */
    private void setBtnLoginSelected() {
        if (Common.isNotEmpty(SMSdynamiccode)) {
            btnNext.setSelected(true);
        } else {
            btnNext.setSelected(false);
        }
    }

    /**
     * 输入数据格式验证（注册）
     */
    public void btnRegSubmitClick() {

        if (password.length() < 6 || password.length() > 20) {
            TToast.showShort(context, "请输入6-20位密码");
            return;
        }

        if (!password.equals(password_confirm)) {
            TToast.showShort(context, "两次密码不同");
            return;
        }

        if (TextUtils.isEmpty(SMSdynamiccode)) {
            TToast.showShort(context, "请输入动态码");
            return;
        }

        sendMobileReg(mobile, SMSdynamiccode, password, password_confirm);
        ImageCodeHelper.loadImageCode(ivCode, this);
    }

    /**
     * 输入数据格式验证（登录）
     */
    public void btnLoginClick() {
        if (mobile.length() != 11) {
            TToast.showShort(context, "请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(SMSdynamiccode)) {
            TToast.showShort(context, "请输入动态码");
            return;
        }
        sendMobileLogin(mobile, SMSdynamiccode);
        ImageCodeHelper.loadImageCode(ivCode, this);
    }


    @OnClick({R.id.tvGetCodeAgain, R.id.btnNext, R.id.ivCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetCodeAgain:
                mCaptchaVal = etCode.getText().toString();
                if (TextUtils.isEmpty(mCaptchaVal)) {
                    TToast.showShort(context, "请输入验证码");
                    ImageCodeHelper.loadImageCode(ivCode, this);
                } else {
                    sendPhoneData(mobile, mCaptchaKey, mCaptchaVal);
                    llCode.setVisibility(View.GONE);
                    llSMSdynamiccode.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnNext:
                if (isReg) {
                    btnRegSubmitClick();
                } else {
                    btnLoginClick();
                }

                break;
            case R.id.ivCode:
                ImageCodeHelper.loadImageCode(ivCode, this);
                break;
        }
    }

    @Override
    public void onImageCodeKey(String captchaKey) {
        mCaptchaKey = captchaKey;
    }


    /**
     * 手机获取短信动态码
     *
     * @param mobile
     * @param captchaKey
     * @param captchaVal
     */
    public void sendPhoneData(String mobile, String captchaKey, String captchaVal) {
        String url = ConstantUrl.URL_PHONE_AND_KEY + "?mobile=" + mobile + "&captchaKey=" + captchaKey + "&captchaVal=" + captchaVal + "&sendType=" + type;
        OkHttpUtil.getAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                AuthCode authCode = gson.fromJson(data, AuthCode.class);
                authCodeValidTime = authCode.getAuthCodeValidTime();
                authCodeResendTime = authCode.getAuthCodeResendTime();
                handler.removeCallbacks(runnable);
                tvTimer.setText(Html.fromHtml("（等待<font color=\"#ED5968\">" + authCodeResendTime + "</font>秒后)"));
                handler.postDelayed(runnable, 1000);

//                Intent intent = new Intent(context, SubmitVerificationCodeActivity.class);
//                intent.putExtra("authCodeValidTime", authCodeValidTime);
//                intent.putExtra("authCodeResendTime", authCodeResendTime);
//                startActivity(intent);
//                finish();
            }
        });
    }

    /**
     * 手机注册
     *
     * @param mobile
     * @param smsAuthCode
     * @param memberPwd
     * @param memberPwdRepeat
     */
    public void sendMobileReg(String mobile, String smsAuthCode, String memberPwd, String memberPwdRepeat) {
        String url = ConstantUrl.URL_MOBILE_REGISTER;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("mobile", mobile);
        params.put("smsAuthCode", smsAuthCode);
        params.put("memberPwd", memberPwd);
        params.put("memberPwdRepeat", memberPwdRepeat);
        params.put("clientType", "android");

        OkHttpUtil.postAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                MemberInfo memberInfo = JsonUtil.toBean(data, new TypeToken<MemberInfo>() {
                }.getType());
                application.setMemberInfo(memberInfo);

                //同步推广记录
                if (!"".equals(application.getDistribution())) {
                    GoodHelper.distributorMerge(SubmitVerificationCodeActivity.this,memberInfo.getToken(), application.getDistribution());
                    application.setDistribution("");
                }

                TToast.showShort(SubmitVerificationCodeActivity.this, "注册成功");
                finish();
            }
        }, params);
    }

    /**
     * 手机登录
     *
     * @param mobile
     * @param smsAuthCode
     */
    public void sendMobileLogin(String mobile, String smsAuthCode) {
        String url = ConstantUrl.URL_MOBILE_LOGIN;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("mobile", mobile);
        params.put("smsAuthCode", smsAuthCode);
        params.put("clientType", "android");

        OkHttpUtil.postAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                MemberInfo memberInfo = JsonUtil.toBean(data, new TypeToken<MemberInfo>() {
                }.getType());
                application.setMemberInfo(memberInfo);

                //同步推广记录
                if (!"".equals(application.getDistribution())) {
                    GoodHelper.distributorMerge(SubmitVerificationCodeActivity.this,memberInfo.getToken(), application.getDistribution());
                    application.setDistribution("");
                }

                TToast.showShort(SubmitVerificationCodeActivity.this, "登录成功");
                finish();
            }
        }, params);
    }
}
