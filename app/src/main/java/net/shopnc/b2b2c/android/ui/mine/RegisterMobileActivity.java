package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.custom.NCDialog;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ncinterface.INCOnDialogConfirm;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterMobileActivity extends BaseActivity {

    private Button btnReg, btnRegMb, btnRegSubmit;
    private EditText etPhone;
    private ImageButton btnAgree;
    private NCDialog ncDialog;
    private EditText etCode;
    private ImageView ivCode;
    private String codeKey;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mobile);
        MyExceptionHandler.getInstance().setContext(this);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnRegMb = (Button) findViewById(R.id.btnRegMb);
        btnRegSubmit = (Button) findViewById(R.id.btnRegSubmit);
        btnAgree = (ImageButton) findViewById(R.id.btnAgree);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etCode = (EditText) findViewById(R.id.etCode);
        ivCode = (ImageView) findViewById(R.id.ivCode);
        ivCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSeccodeCode();
            }
        });

        btnReg.setActivated(false);
        btnRegMb.setActivated(true);
        btnAgree.setSelected(true);
        btnRegSubmit.setActivated(true);

        loadSeccodeCode();

        setCommonHeader("会员注册");
    }

    /*
    * 普通注册按钮
    */
    public void btnRegClick(View v) {
        Intent intent = new Intent(RegisterMobileActivity.this, RegisteredActivity.class);
        startActivity(intent);
        finish();
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
                        imageLoader.displayImage(Constants.URL_SECCODE_MAKECODE + "&k=" + codeKey, ivCode, options, animateFirstListener);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(RegisterMobileActivity.this, json);
                }
            }

        });
    }

    /*
     * 获取验证码
     */

    public void btnRegSubmitClick(View v) {
        if (btnRegSubmit.isActivated()) {

            final String phone = etPhone.getText().toString();
            final String code = etCode.getText().toString();

            //验证手机号
            if (phone.equals("") || phone.length() != 11) {
                Toast.makeText(RegisterMobileActivity.this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }

            //验证验证码
            if (code.equals("")) {
                Toast.makeText(RegisterMobileActivity.this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }

            ncDialog = new NCDialog(RegisterMobileActivity.this);
            ncDialog.setText1("我们将发送验证码短信至:");
            ncDialog.setText2(phone);
            ncDialog.setOnDialogConfirm(new INCOnDialogConfirm() {
                @Override
                public void onDialogConfirm() {
                    String url = Constants.URL_CONNECT_GET_SMS_CAPTCHA + "&phone=" + phone + "&type=1" + "&sec_key=" + codeKey + "&sec_val=" + code;
                    RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
                        @Override
                        public void dataLoaded(ResponseData data) {
                            String json = data.getJson();
                            if (data.getCode() == HttpStatus.SC_OK) {
                                try {
                                    JSONObject obj = new JSONObject(json);
                                    String smsTime = obj.getString("sms_time");
                                    Intent intent = new Intent(RegisterMobileActivity.this, RegisterMobileStep2Activity.class);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("sms_time", smsTime);
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                ShopHelper.showApiError(RegisterMobileActivity.this, json);
                                loadSeccodeCode();
                            }
                        }
                    });
                }
            });
            ncDialog.showPopupWindow();
        }
    }

    /*
     * 同意协议按钮
     */
    public void btnAgreeClick(View v) {
        if (btnAgree.isSelected()) {
            btnAgree.setSelected(false);
            btnRegSubmit.setActivated(false);
        } else {
            btnAgree.setSelected(true);
            btnRegSubmit.setActivated(true);
        }
    }

    /**
     * 用户注册协议
     */
    public void btnMemberDocumentClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(Constants.WAP_MEMBER_DOCUMENT));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_mobile, menu);
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
