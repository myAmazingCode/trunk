package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.Login;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import java.util.HashMap;

/**
 * 用户注册
 *
 * @author dqw
 * @Time 2015-6-25
 */
public class RegisteredActivity extends BaseActivity {

    private EditText editUserName, editPassword, editPasswordConfirm, editEmail;
    private Button btnReg, btnRegMb, btnRegSubmit;
    private ImageButton btnAgree;

    private MyShopApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) RegisteredActivity.this.getApplication();

        editUserName = (EditText) findViewById(R.id.editUserName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editPasswordConfirm = (EditText) findViewById(R.id.editPasswordConfirm);
        editEmail = (EditText) findViewById(R.id.editEmail);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnRegMb = (Button) findViewById(R.id.btnRegMb);
        btnRegSubmit = (Button) findViewById(R.id.btnRegSubmit);
        btnAgree = (ImageButton) findViewById(R.id.btnAgree);

        btnReg.setActivated(true);
        btnRegMb.setActivated(false);
        btnAgree.setSelected(true);
        btnRegSubmit.setActivated(true);

        //检查是否开启手机注册
        String url = Constants.URL_CONNECT_STATE + "&t=connect_sms_reg";
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    LinearLayout llRegTab = (LinearLayout) findViewById(R.id.llRegTab);

                    String result = data.getJson();
                    if (result.equals("1")) {
                        llRegTab.setVisibility(View.VISIBLE);
                    } else {
                        llRegTab.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(RegisteredActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
                }
            }
        });

        setCommonHeader("会员注册");
    }

    /*
    * 手机注册按钮
    */
    public void btnRegMbClick(View v) {
        Intent intent = new Intent(RegisteredActivity.this, RegisterMobileActivity.class);
        startActivity(intent);
        finish();
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

    /*
     * 普通注册提交
     */
    public void btnRegSubmitClick(View v) {
        if (!btnRegSubmit.isActivated()) {
            return;
        }

        String username = editUserName.getText().toString();
        String password = editPassword.getText().toString();
        String password_confirm = editPasswordConfirm.getText().toString();
        String email = editEmail.getText().toString();
        if (username.equals("") || username == null) {
            Toast.makeText(RegisteredActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (username.length() < 6 || username.length() > 20) {
            T.showShort(RegisteredActivity.this, "用户名为6-20个字符");
            return;
        }

        if (StringUtils.isNumeric(username)) {
            T.showShort(RegisteredActivity.this, "用户名不能为纯数字");
            return;
        }

        if (password.equals("") || password == null) {
            Toast.makeText(RegisteredActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6 || password.length() > 20) {
            T.showShort(RegisteredActivity.this, "请输入6-20位密码");
            return;
        }

        if (password_confirm.equals("") || password_confirm == null) {
            Toast.makeText(RegisteredActivity.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(password_confirm)) {
            Toast.makeText(RegisteredActivity.this, "两次密码不同", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.equals("") || email == null) {
            Toast.makeText(RegisteredActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        SendData(username, password, password_confirm, email);
    }

    /**
     * 注册用户
     */
    public void SendData(String username, String password, String password_confirm, String email) {
        String url = Constants.URL_REGISTER;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        params.put("password_confirm", password_confirm);
        params.put("email", email);
        params.put("client", "android");
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {

                    Login login = Login.newInstanceList(json);
                    myApplication.setUserName(login.getUsername());
                    myApplication.setLoginKey(login.getKey());
                    myApplication.setMemberID(login.getUserid());

                    myApplication.loadingUserInfo(login.getKey(), login.getUserid());

                    myApplication.getmSocket().connect();
                    myApplication.UpDateUser();

                    Intent mIntent = new Intent(Constants.LOGIN_SUCCESS_URL);
                    sendBroadcast(mIntent);
                    finish();
                } else {
                    ShopHelper.showApiError(RegisteredActivity.this, json);
                }
            }
        });
    }

    /**
     * 用户注册协议
     */
    public void btnMemberDocumentClick(View v) {
        Intent intent = new Intent(context, RegisterMemberDocumentActivity.class);
        startActivity(intent);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(Constants.WAP_MEMBER_DOCUMENT));
//        startActivity(intent);
    }
}
