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
 * 店铺代金券卡密领取
 * <p/>
 * dqw
 * 2015/8/27
 */
public class VoucherPasswordAddActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private EditText etPwdCode;
    private EditText etCode;
    private ImageView ivCode;
    private Button btnSubmit;
    private String codeKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_password_add);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("");
        setTabButton();

        myApplication = (MyShopApplication) getApplicationContext();

        etPwdCode = (EditText) findViewById(R.id.etPwdCode);
        etCode = (EditText) findViewById(R.id.etCode);
        ivCode = (ImageView) findViewById(R.id.ivCode);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        ivCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSeccodeCode();
            }
        });

        initSubmitButton();
        loadSeccodeCode();
    }

    /**
     * 提交按钮状态
     */
    private void initSubmitButton() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etPwdCode.getText().toString().equals("") || etCode.getText().toString().equals("")) {
                    btnSubmit.setActivated(false);
                } else {
                    btnSubmit.setActivated(true);
                }
            }
        };
        etPwdCode.addTextChangedListener(textWatcher);
        etCode.addTextChangedListener(textWatcher);
    }

    /**
     * 设置头部切换按钮
     */
    private void setTabButton() {
        Button btnVoucherList = (Button) findViewById(R.id.btnVoucherList);
        Button btnVoucherPasswordAdd = (Button) findViewById(R.id.btnVoucherPasswordAdd);

        btnVoucherPasswordAdd.setActivated(true);
        btnVoucherList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VoucherPasswordAddActivity.this, VoucherListActivity.class));
                finish();
            }
        });
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
                    ShopHelper.showApiError(VoucherPasswordAddActivity.this, json);
                }
            }
        });
    }

    /**
     * 提交
     */
    public void btnSubmitClick(View view) {
        if (btnSubmit.isActivated()) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("key", myApplication.getLoginKey());
            params.put("pwd_code", etPwdCode.getText().toString());
            params.put("codekey", codeKey);
            params.put("captcha", etCode.getText().toString());
            RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_VOUCHER_PASSWORD_ADD, params, myApplication, new RemoteDataHandler.Callback() {
                @Override
                public void dataLoaded(ResponseData data) {
                    String json = data.getJson();
                    if (data.getCode() == HttpStatus.SC_OK) {
                        ShopHelper.showMessage(VoucherPasswordAddActivity.this, "代金券领取成功");
                        startActivity(new Intent(VoucherPasswordAddActivity.this, VoucherListActivity.class));
                        finish();
                    } else {
                        ShopHelper.showApiError(VoucherPasswordAddActivity.this, json);
                        loadSeccodeCode();
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voucher_password_add, menu);
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
