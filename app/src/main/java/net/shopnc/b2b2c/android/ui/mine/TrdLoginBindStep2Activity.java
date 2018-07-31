package net.shopnc.b2b2c.android.ui.mine;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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

import net.shopnc.b2b2c.BuildConfig;
import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.MemberInfo;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.ImageCodeHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class TrdLoginBindStep2Activity extends BaseActivity implements ShopHelper.OnCountDownFinishListenser, ImageCodeHelper.OnImageCodeKeyListener {

    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.etSmsCaptcha)
    EditText etSmsCaptcha;
    @Bind(R.id.btnGetSmsCaptcha)
    Button btnGetSmsCaptcha;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.ivCode)
    ImageView ivCode;
    @Bind(R.id.llSecCode)
    LinearLayout llSecCode;
    //    @Bind(R.id.etUsername)
//    EditText etUsername;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.etPasswordConfirm)
    EditText etPasswordConfirm;
    @Bind(R.id.btnRegNext)
    Button btnRegNext;

    private String phoneNumber;
    private int smsTime;
    private String mOpenid;
    private String mAccessToken;
    private String mCaptchaKey;
    private int bindType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        phoneNumber = getIntent().getStringExtra("phone");
        smsTime = getIntent().getIntExtra("sms_time", 0);
        int authCodeValidTime = getIntent().getIntExtra("valid_time", 0);
        tvPhone.setText(String.format("请输入%s收到的验证码，在%s分钟内有效。", phoneNumber, "" + authCodeValidTime));

        btnRegNext = (Button) findViewById(R.id.btnRegNext);
//        tvPhone.setText("请输入" + phoneNumber + "收到的短信验证码");
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(etSmsCaptcha.getText().toString())
                        || TextUtils.isEmpty(etPassword.getText().toString())
                        || TextUtils.isEmpty(etPasswordConfirm.getText().toString())) {
                    btnRegNext.setActivated(false);
                } else {
                    btnRegNext.setActivated(true);
                }

            }
        };
        etSmsCaptcha.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);
//        etUsername.addTextChangedListener(watcher);
        etPasswordConfirm.addTextChangedListener(watcher);

        ShopHelper.btnSmsCaptchaCountDown(TrdLoginBindStep2Activity.this, btnGetSmsCaptcha, smsTime, this);
        setCommonHeader("提交验证码");
        getLoginInfo();
    }

    private void getLoginInfo() {
        mOpenid = getIntent().getStringExtra("openId");
        mAccessToken = getIntent().getStringExtra("accessToken");
        bindType = getIntent().getIntExtra("bindType", 0);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_trd_login_bind_step2);
    }

    @OnClick({R.id.btnGetSmsCaptcha, R.id.ivCode, R.id.btnRegNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGetSmsCaptcha:
                btnGetSmsCaptchaClick();
                break;
            case R.id.ivCode:
                ImageCodeHelper.loadImageCode(ivCode, TrdLoginBindStep2Activity.this);
                break;
            case R.id.btnRegNext:
                bindClick();
                break;
        }
    }

    private void bindClick() {
        if (!btnRegNext.isActivated()) return;

        ShopHelper.hideInputKeyboard(this, btnRegNext);

        String code = etSmsCaptcha.getText().toString();
//        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String password_confirm = etPasswordConfirm.getText().toString();

        // 微信绑定手机号
        // accessToken String android和ios使用友盟返回的accessToken
        //openId String android和ios使用友盟返回的openId
        String url_wx = ConstantUrl.URL_API + "/loginconnect/umeng/weixin/register/mobile/bind";
        String url_qq = ConstantUrl.URL_API + "/loginconnect/umeng/qq/register/mobile/bind";

        String url = url_wx;

        Map<String, String> map = new HashMap<>();
        map.put("accessToken", mAccessToken);
        map.put("openId", mOpenid);
        //绑定参数
        map.put("authCode", code);
        map.put("mobile", phoneNumber);
//        map.put("memberName", username);//用户名，
        map.put("memberPwd", password);
        map.put("repeatMemberPwd", password_confirm);//重复密码
        map.put("clientType", "android");
        if (bindType == LoginActivity.BIND_QQ) {
            url = url_qq;
            map.put("appId", BuildConfig.QQ_APP_ID);
        }


        OkHttpUtil.postAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response, int i) {
                Log.d(TAG, "onResponse: response = " + response);
                register(response);
            }
        }, map);

    }

    private void register(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            int code = jsonObject.getInt("code");
            JSONObject datas = jsonObject.getJSONObject("datas");
            if (200 == code) {
                String memberName = datas.getString("memberName");
                String memberId = datas.getString("memberId");
                String token = datas.getString("token");

                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setMemberId(Integer.parseInt(memberId));
                memberInfo.setMemberName(memberName);
                memberInfo.setToken(token);

                application.setMemberInfo(memberInfo);
                TToast.showShort(this, "绑定成功");
                finish();
            } else if (400 == code) {
                String error = datas.getString("error");
                TToast.showShort(this, error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void btnGetSmsCaptchaClick() {
        //当可见时候，点击需要判断是否输入...
        String code = etCode.getText().toString();

        if (llSecCode.getVisibility() == View.VISIBLE && TextUtils.isEmpty(code)) {
            TToast.showShort(this, "请输入图片验证码");
            return;
        }


        String url = ConstantUrl.URL_API + "/loginconnect/smscode/send?" +
                "mobile=" + phoneNumber + "&captchaKey=" + mCaptchaKey + "&captchaVal=" + code + "&sendType=1";
        OkHttpUtil.getAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int i) {
                parseCode(response);
                Log.d(TAG, "onResponse: " + response);
            }
        });
    }

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
                    tvPhone.setText(String.format("请输入%s收到的验证码，在%s分钟内有效。", phoneNumber, "" + authCodeValidTime));
                    ShopHelper.btnSmsCaptchaCountDown(TrdLoginBindStep2Activity.this, btnGetSmsCaptcha, authCodeResendTime, TrdLoginBindStep2Activity.this);
                    hideSecCode();
                } else {
                    String error = datas.getString("error");
                    TToast.showShort(context, error);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //获取验证码成功后，隐藏图片验证码
    private void hideSecCode() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(llSecCode, "translationX", 0, -1200).setDuration(1000);
//        animator.setInterpolator(new FastOutSlowInInterpolator());
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

    public static final String TAG = "TrdLoginStep2";

    @Override
    public void onCountDownFinish() {
        if (llSecCode != null) {
            llSecCode.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(llSecCode, "translationX", -1200, 0).setDuration(1000);
//            animator.setInterpolator(new BounceInterpolator());
            animator.start();
            ImageCodeHelper.loadImageCode(ivCode, this);
        }
    }


    @Override
    public void onImageCodeKey(String captchaKey) {
        mCaptchaKey = captchaKey;
    }
}
