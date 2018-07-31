package net.shopnc.b2b2c.android.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.AuthCode;
import net.shopnc.b2b2c.android.bean.MemberInfo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
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

public class RegisterActivity extends BaseActivity implements ImageCodeHelper.OnImageCodeKeyListener {


    @Bind(R.id.ivRegisterNormal)
    ImageView ivRegisterNormal;
    @Bind(R.id.llNormal)
    LinearLayout llNormal;
    @Bind(R.id.viewNormal)
    View viewNormal;
    @Bind(R.id.rlRegisterNormal)
    RelativeLayout rlRegisterNormal;
    @Bind(R.id.ivRegisterPhone)
    ImageView ivRegisterPhone;
    @Bind(R.id.llPhone)
    LinearLayout llPhone;
    @Bind(R.id.viewPhone)
    View viewPhone;
    @Bind(R.id.rlRegisterPhone)
    RelativeLayout rlRegisterPhone;
    @Bind(R.id.llRegister)
    LinearLayout llRegister;
    @Bind(R.id.editUserName)
    EditText editUserName;
    @Bind(R.id.editPassword)
    EditText editPassword;
    @Bind(R.id.editPasswordConfirm)
    EditText editPasswordConfirm;
    @Bind(R.id.editEmail)
    EditText editEmail;
    @Bind(R.id.ivCodeNormal)
    ImageView ivCodeNormal;
    @Bind(R.id.etCodeNormal)
    EditText etCodeNormal;
    @Bind(R.id.llRegisterNormal)
    LinearLayout llRegisterNormal;
    @Bind(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @Bind(R.id.ivCodePhone)
    ImageView ivCodePhone;
    @Bind(R.id.etCodePhone)
    EditText etCodePhone;
    @Bind(R.id.llRegisterPhone)
    LinearLayout llRegisterPhone;
    @Bind(R.id.btnAgree)
    ImageButton btnAgree;
    @Bind(R.id.btnMemberDocument)
    TextView btnMemberDocument;
    @Bind(R.id.btnRegSubmit)
    Button btnRegSubmit;
    @Bind(R.id.tvBindPhone)
    TextView tvBindPhone;
    @Bind(R.id.tvNormal)
    TextView tvNormal;
    @Bind(R.id.tvPhone)
    TextView tvPhone;
    private MyShopApplication application;
    private Context context;

    private String username;
    private String password;
    private String password_confirm;
    private String email;

    private String mCaptchaKey;
    private String mCaptchaVal;

    private String phonenumber;
    private String mCaptchaVal_phone;
    private boolean isPhoneReg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initETWatcher();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_register);
    }

    /**
     * view的一些初始化操作
     */
    private void initViews() {
        setCommonHeader("会员注册");
        application = MyShopApplication.getInstance();
        context = this;
        btnAgree.setSelected(true);
        btnRegSubmit.setSelected(false);
        btnRegSubmit.setText("注册");
        //加载验证码
        ImageCodeHelper.loadImageCode(ivCodeNormal, this);
        ImageCodeHelper.loadImageCode(ivCodePhone, this);
        tvBindPhone.setVisibility(View.GONE);
        btnClear.setVisibility(View.VISIBLE);
        btnClear.setText("登录");
        btnClear.setTextColor(ContextCompat.getColor(context, R.color.nc_btn_bg));
        viewNormal.setVisibility(View.VISIBLE);
        viewPhone.setVisibility(View.GONE);
        tvNormal.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
        tvPhone.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        ivRegisterNormal.setImageResource(R.drawable.user);
        ivRegisterPhone.setImageResource(R.drawable.mobile_gray);
    }

    private void initETWatcher() {
        editUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                username = s.toString();
                setBtnRegSubmitSelected();
            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {
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

        editPasswordConfirm.addTextChangedListener(new TextWatcher() {
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

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                email = s.toString();
                setBtnRegSubmitSelected();
            }
        });

        etCodeNormal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCaptchaVal = s.toString();
                setBtnRegSubmitSelected();
            }
        });

        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phonenumber = s.toString();
                setBtnRegisterPhoneSelected();
            }
        });

        etCodePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCaptchaVal_phone = s.toString();
                setBtnRegisterPhoneSelected();
            }
        });

    }

    /**
     * 清理普通注册填入信息
     */
    private void clearRegNormal() {
        etCodeNormal.setText("");
        editUserName.setText("");
        editPassword.setText("");
        editPasswordConfirm.setText("");
        editEmail.setText("");
        viewNormal.setVisibility(View.VISIBLE);
        viewPhone.setVisibility(View.GONE);
        tvNormal.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
        tvPhone.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        ivRegisterNormal.setImageResource(R.drawable.user);
        ivRegisterPhone.setImageResource(R.drawable.mobile_gray);
    }

    /**
     * 清理手机注册填入信息
     */
    private void clearRegPhone() {
        etCodePhone.setText("");
        etPhoneNumber.setText("");
        viewNormal.setVisibility(View.GONE);
        viewPhone.setVisibility(View.VISIBLE);
        tvNormal.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        tvPhone.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
        ivRegisterNormal.setImageResource(R.drawable.user_gray);
        ivRegisterPhone.setImageResource(R.drawable.mobile);
    }

    /**
     * 注册按钮是否可以点击
     */
    private void setBtnRegSubmitSelected() {
        if (Common.isNotEmpty(username, password, password_confirm, email, mCaptchaVal) && btnAgree.isSelected()) {
            btnRegSubmit.setSelected(true);
        } else {
            btnRegSubmit.setSelected(false);
        }
    }

    /**
     * 获取动态码按钮是否可以点击
     */
    private void setBtnRegisterPhoneSelected() {
        if (Common.isNotEmpty(phonenumber, mCaptchaVal_phone) && btnAgree.isSelected()) {
            btnRegSubmit.setSelected(true);
        } else {
            btnRegSubmit.setSelected(false);
        }
    }

    /**
     * 输入数据格式验证
     */
    public void btnRegSubmitClick() {
        if (username.length() < 6 || username.length() > 20) {
            TToast.showShort(context, "用户名为6-20个字符");
            return;
        }

        if (Common.isNumeric(username)) {
            TToast.showShort(context, "用户名不能为纯数字");
            return;
        }

        if (password.length() < 6 || password.length() > 20) {
            TToast.showShort(context, "请输入6-20位密码");
            return;
        }

        if (!password.equals(password_confirm)) {
            TToast.showShort(context, "两次密码不同");
            return;
        }

        if (!Common.isEmailIdValid(email)) {
            TToast.showShort(context, "邮箱格式错误");
            return;
        }

        sendData(username, password, password_confirm, email, mCaptchaKey, mCaptchaVal);
        ImageCodeHelper.loadImageCode(ivCodeNormal, this);
    }

    /**
     * 输入数据格式验证
     */
    public void btnRegisterPhoneClick() {

        if (phonenumber.length() != 11) {
            TToast.showShort(context, "请输入正确的手机号码格式");
            return;
        }

        if (TextUtils.isEmpty(mCaptchaVal_phone)) {
            return;
        }

        sendPhoneData(phonenumber, mCaptchaKey, mCaptchaVal_phone);
        ImageCodeHelper.loadImageCode(ivCodePhone, this);
    }

    /**
     * 普通注册
     *
     * @param username
     * @param password
     * @param password_confirm
     * @param email
     * @param captchaKey
     * @param captchaVal
     */
    public void sendData(String username, String password, String password_confirm, String email, String captchaKey, String captchaVal) {
        String url = ConstantUrl.URL_REGISTER;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("memberName", username);
        params.put("password", password);
        params.put("password_confirm", password_confirm);
        params.put("email", email);
        params.put("captchaKey", captchaKey);
        params.put("captchaVal", captchaVal);
        params.put("clientType", "android");

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                MemberInfo memberInfo = JsonUtil.toBean(data, new TypeToken<MemberInfo>() {
                }.getType());
                application.setMemberInfo(memberInfo);

                //同步推广记录
                if (!"".equals(application.getDistribution())) {
                    GoodHelper.distributorMerge(RegisterActivity.this,memberInfo.getToken(), application.getDistribution());
                    application.setDistribution("");
                }

                TToast.showShort(RegisterActivity.this, "注册成功");
                finish();
            }
        }, params);
    }

    /**
     * 手机注册获取短信动态码
     *
     * @param mobile
     * @param captchaKey
     * @param captchaVal
     */
    public void sendPhoneData(final String mobile, String captchaKey, String captchaVal) {
        String url = ConstantUrl.URL_PHONE_AND_KEY + "?mobile=" + mobile + "&captchaKey=" + captchaKey + "&captchaVal=" + captchaVal + "&sendType=1";
        OkHttpUtil.getAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                AuthCode authCode = gson.fromJson(data, AuthCode.class);
                int authCodeValidTime = authCode.getAuthCodeValidTime();
                int authCodeResendTime = authCode.getAuthCodeResendTime();

                Intent intent = new Intent(context, SubmitVerificationCodeActivity.class);
                intent.putExtra("type", SubmitVerificationCodeActivity.TYPE_REGISTER);
                intent.putExtra("mobile", mobile);
                intent.putExtra("authCodeValidTime", authCodeValidTime);
                intent.putExtra("authCodeResendTime", authCodeResendTime);
                startActivity(intent);
                finish();
            }
        });
    }


    @OnClick({R.id.btnAgree, R.id.btnMemberDocument, R.id.btnRegSubmit, R.id.btnClear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAgree:
                btnAgree.setSelected(!btnAgree.isSelected());
                if (isPhoneReg) {
                    setBtnRegisterPhoneSelected();
                } else {
                    setBtnRegSubmitSelected();
                }
                break;

            case R.id.btnMemberDocument:
                Intent intent = new Intent(RegisterActivity.this, User2Konw.class);
                startActivity(intent);
                break;

            case R.id.btnRegSubmit:
                if (btnRegSubmit.isSelected()) {
                    if (isPhoneReg) {
                        btnRegisterPhoneClick();
                    } else {
                        btnRegSubmitClick();
                    }
                }
                break;

            case R.id.btnClear:
                Common.gotoActivity(RegisterActivity.this, LoginActivity.class, false, null);
                finish();
                break;

        }
    }

    @OnClick({R.id.rlRegisterNormal, R.id.rlRegisterPhone, R.id.ivCodeNormal, R.id.ivCodePhone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlRegisterNormal:
                llRegisterNormal.setVisibility(View.VISIBLE);
                llRegisterPhone.setVisibility(View.GONE);
                tvBindPhone.setVisibility(View.GONE);
                btnRegSubmit.setText("注册");
                clearRegNormal();
                setBtnRegSubmitSelected();
                isPhoneReg = false;
                ImageCodeHelper.loadImageCode(ivCodeNormal, this);
                break;
            case R.id.rlRegisterPhone:
                llRegisterNormal.setVisibility(View.GONE);
                llRegisterPhone.setVisibility(View.VISIBLE);
                tvBindPhone.setVisibility(View.VISIBLE);
                btnRegSubmit.setText("获取动态码");
                clearRegPhone();
                setBtnRegisterPhoneSelected();
                isPhoneReg = true;
                ImageCodeHelper.loadImageCode(ivCodePhone, this);
                break;
            case R.id.ivCodeNormal:
                ImageCodeHelper.loadImageCode(ivCodeNormal, this);
                break;
            case R.id.ivCodePhone:
                ImageCodeHelper.loadImageCode(ivCodePhone, this);
                break;
        }
    }

    @Override
    public void onImageCodeKey(String captchaKey) {
        mCaptchaKey = captchaKey;
    }
}
