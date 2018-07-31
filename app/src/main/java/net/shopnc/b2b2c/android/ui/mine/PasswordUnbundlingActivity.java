package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
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
import net.shopnc.b2b2c.android.common.LogHelper;
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
 * Created by wj on 2016/1/12.
 */
public class PasswordUnbundlingActivity extends BaseActivity {

    private EditText etPassword,etCode;
    private Button btnSubmit;
    private ImageView ivCode;
    private String codeKey;//用于记录验证码
    private MyShopApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_unbind_password);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("输入支付密码");
        myApplication = (MyShopApplication) getApplicationContext();
        initView();
    }


    private void initView(){


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String captcha = etCode.getText().toString();
                String passwordCaptcha = etPassword.getText().toString();
                if (captcha.length() > 0 && passwordCaptcha.length() > 0) {
                    btnSubmit.setActivated(true);
                } else {
                    btnSubmit.setActivated(false);
                }
            }
        };

        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword.addTextChangedListener(textWatcher);
        etCode = (EditText) findViewById(R.id.etCode);
        etCode.addTextChangedListener(textWatcher);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
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
                    ShopHelper.showApiError(PasswordUnbundlingActivity.this, json);
                }
            }

        });

    }


    /**
     * 验证支付密码
     */
    public void btnSubmitClick(View view) {
        if (!btnSubmit.isActivated()) {
            return;
        }
        String captcha = etCode.getText().toString();
        if (captcha.equals("")) {
            ShopHelper.showMessage(PasswordUnbundlingActivity.this, "请输入图形验证码");
            return;
        }
        String password = etPassword.getText().toString();
        if (password.equals("")) {
            ShopHelper.showMessage(PasswordUnbundlingActivity.this, "请输入支付密码");
            return;
        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("captcha", captcha);
        params.put("codekey", codeKey);
        params.put("password",password);
        LogHelper.e("params",params.toString());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URl_UNDIND_PASSWORD, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                LogHelper.e("json", json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    setResult(Constants.RESULT_FLAG_BIND_MOBILE, new Intent(PasswordUnbundlingActivity.this, SettingActivity.class));
                    startActivity(new Intent(PasswordUnbundlingActivity.this, BindMobileActivity.class));
                    finish();
                } else {
                    ShopHelper.showApiError(PasswordUnbundlingActivity.this, json);
                    loadSeccodeCode();
                }
            }

        });
    }
}
