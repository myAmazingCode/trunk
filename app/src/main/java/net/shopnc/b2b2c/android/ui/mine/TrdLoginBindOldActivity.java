package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.shopnc.b2b2c.BuildConfig;
import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.MemberInfo;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class TrdLoginBindOldActivity extends BaseActivity {


    @Bind(R.id.btnBindOld)
    Button btnBindOld;
    @Bind(R.id.etUsername)
    EditText etUsername;
    @Bind(R.id.etPassword)
    EditText etPassword;

    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    private String mOpenid;
    private String mAccessToken;
    private int bindType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("绑定帐号");

        btnBindOld.setActivated(true);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etUsername.getText().toString()) || TextUtils.isEmpty(etUsername.getText().toString())) {
                    btnSubmit.setActivated(false);
                } else {
                    btnSubmit.setActivated(true);
                }
            }
        };
        etUsername.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);

        getLoginInfo();
    }

    private void getLoginInfo() {
        mOpenid = getIntent().getStringExtra("openId");
        mAccessToken = getIntent().getStringExtra("accessToken");
        bindType = getIntent().getIntExtra("bindType", 0);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_trd_login_bind_old);
    }

    @OnClick({R.id.btnBindNew, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBindNew:
                Intent intent = new Intent(this, TrdLoginBindNewActivity.class);
                putInfo(intent);
                startActivity(intent);
                finish();
                break;
            case R.id.btnSubmit:
                submit();
                break;
        }
    }

    private void putInfo(Intent intent) {
        intent.putExtra("accessToken", mAccessToken);
        intent.putExtra("openId", mOpenid);
    }

    private void submit() {
        if (!btnSubmit.isActivated()) return;

        String memberName = etUsername.getText().toString();
        String memberPwd = etPassword.getText().toString();
        ShopHelper.hideInputKeyboard(this, btnSubmit);
        String url_wx = ConstantUrl.URL_API + "/loginconnect/umeng/weixin/login/bind";
        String url_qq = ConstantUrl.URL_API + "/loginconnect/umeng/qq/login/bind";
        String url = url_wx;
        Map<String, String> map = new HashMap<>();
        map.put("memberName", memberName);
        map.put("memberPwd", memberPwd);
        map.put("accessToken", mAccessToken);
        map.put("openId", mOpenid);
        map.put("clientType", "android");
        if (bindType == LoginActivity.BIND_QQ) {
            url = url_qq;
            map.put("appId", BuildConfig.QQ_APP_ID);
        }

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                MemberInfo memberInfo = JsonUtil.toBean(data, MemberInfo.class);
                application.setMemberInfo(memberInfo);
                TToast.showShort(application, "绑定成功");
                finish();
            }
        }, map);
    }
}
