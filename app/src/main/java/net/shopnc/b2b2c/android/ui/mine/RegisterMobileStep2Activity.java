package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterMobileStep2Activity extends BaseActivity {

    private String phoneNumber;
    private int smsTime;

    private TextView tvPhone;
    private EditText etSmsCaptcha;
    private Button btnGetSmsCaptcha;
    private Button btnRegNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mobile_step2);
        MyExceptionHandler.getInstance().setContext(this);
        tvPhone = (TextView) findViewById(R.id.tvPhone);

        phoneNumber = getIntent().getStringExtra("phone");
        smsTime = Integer.parseInt(getIntent().getStringExtra("sms_time"));
        etSmsCaptcha = (EditText) findViewById(R.id.etSmsCaptcha);
        btnGetSmsCaptcha = (Button) findViewById(R.id.btnGetSmsCaptcha);
        btnRegNext = (Button) findViewById(R.id.btnRegNext);

        tvPhone.setText("请输入" + phoneNumber + "收到的短信验证码");
        etSmsCaptcha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etSmsCaptcha.getText().toString().length() > 0) {
                    btnRegNext.setActivated(true);
                } else {
                    btnRegNext.setActivated(false);
                }

            }
        });
        btnRegNext.setActivated(false);
        ShopHelper.btnSmsCaptchaCountDown(RegisterMobileStep2Activity.this, btnGetSmsCaptcha, smsTime);

        setCommonHeader("提交验证码");
    }

    /**
     * 重新获取验证码按钮点击
     */
    public void btnGetSmsCaptchaClick(View v) {
        if (btnGetSmsCaptcha.isActivated()) {
            String url = Constants.URL_CONNECT_GET_SMS_CAPTCHA + "&phone=" + phoneNumber + "&type=1";
            RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
                @Override
                public void dataLoaded(ResponseData data) {
                    String json = data.getJson();
                    if (data.getCode() == HttpStatus.SC_OK) {
                        try {
                            JSONObject obj = new JSONObject(json);
                            smsTime = Integer.parseInt(obj.getString("sms_time"));
                            ShopHelper.btnSmsCaptchaCountDown(RegisterMobileStep2Activity.this, btnGetSmsCaptcha, smsTime);
                        } catch (JSONException e) {
                            Toast.makeText(RegisterMobileStep2Activity.this, "短信发送失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        ShopHelper.showApiError(RegisterMobileStep2Activity.this, json);
                    }
                }
            });
        }
    }

    /**
     * 下一步按钮单击，验证手机验证码
     */
    public void btnRegNextClick(View v) {
        if (btnRegNext.isActivated()) {
            String url = Constants.URL_CONNECT_CHECK_SMS_CAPTCHA + "&phone=" + phoneNumber + "&captcha=" + etSmsCaptcha.getText().toString() + "&type=1";
            RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
                @Override
                public void dataLoaded(ResponseData data) {
                    String json = data.getJson();
                    if (data.getCode() == HttpStatus.SC_OK) {
                        Intent intent = new Intent(RegisterMobileStep2Activity.this, RegisterMobileStep3Activity.class);
                        intent.putExtra("phone", phoneNumber);
                        intent.putExtra("captcha", etSmsCaptcha.getText().toString());
                        startActivity(intent);
                        RegisterMobileStep2Activity.this.finish();
                    } else {
                        ShopHelper.showApiError(RegisterMobileStep2Activity.this, json);
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_mobile_step2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
