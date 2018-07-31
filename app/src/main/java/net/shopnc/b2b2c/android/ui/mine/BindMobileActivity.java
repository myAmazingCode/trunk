package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 绑定手机
 *
 * @author dqw
 * @date 2015/9/1
 */
public class BindMobileActivity extends BaseActivity {
    MyShopApplication myApplication;
    private EditText etMobile, etCode, etSmsCaptcha;
    private Button btnGetSmsCaptcha, btnSubmit;
    private ImageView ivCode;
    private String codeKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_mobile);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("手机验证");
        myApplication = (MyShopApplication) getApplicationContext();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String mobile = etMobile.getText().toString();
                String captcha = etCode.getText().toString();
                String smsCaptcha = etSmsCaptcha.getText().toString();
                if (mobile.length() > 0 && captcha.length() > 0 && smsCaptcha.length() > 0) {
                    btnSubmit.setActivated(true);
                } else {
                    btnSubmit.setActivated(false);
                }
            }
        };

        etMobile = (EditText) findViewById(R.id.etMobile);
        etMobile.addTextChangedListener(textWatcher);
        etCode = (EditText) findViewById(R.id.etCode);
        etCode.addTextChangedListener(textWatcher);
        etSmsCaptcha = (EditText) findViewById(R.id.etSmsCaptcha);
        etSmsCaptcha.addTextChangedListener(textWatcher);
        btnGetSmsCaptcha = (Button) findViewById(R.id.btnGetSmsCaptcha);
        btnGetSmsCaptcha.setActivated(true);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setActivated(false);
        ivCode = (ImageView) findViewById(R.id.ivCode);
        ivCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSeccodeCode();
            }
        });

        loadSeccodeCode();
    }

    /**
     * 加载图片验证码
     */
    private void loadSeccodeCode() {
        etCode.setText("");
        RemoteDataHandler.asyncDataStringGet(Constants.URL_SECCODE_MAKECODEKEY, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        codeKey = obj.getString("codekey");
                        ShopHelper.loadImage(ivCode, Constants.URL_SECCODE_MAKECODE + "&k=" + codeKey);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(BindMobileActivity.this, json);
                }
            }

        });
    }

    /**
     * 获取短信验证码点击
     */
    public void btnGetSmsCaptchaClick(View view) {
        if (!btnGetSmsCaptcha.isActivated()) {
            return;
        }

        String mobile = etMobile.getText().toString();
        String captcha = etCode.getText().toString();
        if (mobile.equals("") || mobile.length() != 11) {
            ShopHelper.showMessage(BindMobileActivity.this, "请输入正确的手机号");
            return;
        }
        if (captcha.equals("")) {
            ShopHelper.showMessage(BindMobileActivity.this, "请输入图形验证码");
            return;
        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("mobile", mobile);
        params.put("captcha", captcha);
        params.put("codekey", codeKey);
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_ACCOUNT_BIND_MOBILE_STEP1, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        int smsTime = jsonObject.optInt("sms_time");
                        ShopHelper.showMessage(BindMobileActivity.this, "验证码发送成功");
                        ShopHelper.btnSmsCaptchaCountDown(BindMobileActivity.this, btnGetSmsCaptcha, smsTime);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(BindMobileActivity.this, json);
                }
            }

        });
    }

    /**
     * 验证手机
     */
    public void btnSubmitClick(View view) {
        if (!btnSubmit.isActivated()) {
            return;
        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("auth_code", etSmsCaptcha.getText().toString());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_ACCOUNT_BIND_MOBILE_STEP2, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    ShopHelper.showMessage(BindMobileActivity.this, "手机验证成功");
                    finish();
                } else {
                    ShopHelper.showApiError(BindMobileActivity.this, json);
                }
            }

        });
    }
}
