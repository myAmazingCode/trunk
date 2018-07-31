package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.squareup.okhttp.Request;
import com.tencent.tauth.Tencent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.LoginState;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.Global;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.OkHttpUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.StringUtils;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录页面
 *
 * @author wj
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private MyShopApplication myApplication;

    private EditText etUsername, etPassword;
    private ImageButton btnAutoLogin;
    private Button btnLogin;
    private LinearLayout ThreebtnLogin;
    private ImageView ivThreeLogin;


    private ImageButton btnQQ, btnWeiXin, btnSina;

    //weibo
    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;

    //qq
    public static Tencent mTencent;

    //QQ
    private String token;
    private String openid;
    private String unionID;

    private UMShareAPI mShareAPI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        mShareAPI = UMShareAPI.get(this);

        myApplication = (MyShopApplication) getApplicationContext();
        MyExceptionHandler.getInstance().setContext(this);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setBtnLoginState();
            }
        };

        etUsername = (EditText) findViewById(R.id.etUsername);
        etUsername.addTextChangedListener(textWatcher);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword.addTextChangedListener(textWatcher);
        btnAutoLogin = (ImageButton) findViewById(R.id.btnAutoLogin);
        btnAutoLogin.setSelected(true);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setActivated(false);

        btnQQ = (ImageButton) findViewById(R.id.btnQQ);
        btnQQ.setOnClickListener(this);
        btnWeiXin = (ImageButton) findViewById(R.id.btnWeiXin);
        btnWeiXin.setOnClickListener(this);
        btnSina = (ImageButton) findViewById(R.id.btnSina);
        btnSina.setOnClickListener(this);

        ThreebtnLogin = (LinearLayout) findViewById(R.id.ThreebtnLogin);
        ivThreeLogin = (ImageView) findViewById(R.id.ivThreeLogin);
        if (Constants.APP_ID.equals("") || Constants.APP_SECRET.equals("") || Constants.WEIBO_APP_KEY.equals("") || Constants.WEIBO_APP_SECRET.equals("") || Constants.QQ_APP_ID.equals("") || Constants.QQ_APP_KEY.equals("")) {
            ThreebtnLogin.setVisibility(View.INVISIBLE);
            ivThreeLogin.setVisibility(View.INVISIBLE);
        }

        getThirdLoginState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!StringUtils.isEmpty(application.getLoginKey())) {
            Global.cartnum = 0;
            if (!TextUtils.isEmpty(Global.cartlist)) {
                String str = Global.cartlist.substring(0, Global.cartlist.length() - 1);
                ShopHelper.addCartBatch(context, application, str);
            }
        }
    }

    public static final String TAG = "LoginActivity";

    private void getThirdLoginState() {
        String url = Constants.URL_CONTEXTPATH + "act=connect&op=get_state";
        Log.d(TAG, "getThirdLoginState: url = " + url);
        OkHttpUtil.getAsyn(this, url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d(TAG, "onError: e = " + e);
            }

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: response = " + response);
                if ("200".equals(JsonUtil.getString(response, "code"))) {
                    LoginState loginState = new Gson().fromJson(response, LoginState.class);
                    LoginState.DatasBean datasBean = loginState.getDatas();
                    btnQQ.setVisibility(View.VISIBLE);
//                    btnQQ.setVisibility("1".equals(datasBean.getConnect_qq()) ? View.VISIBLE : View.GONE);
                    btnSina.setVisibility("1".equals(datasBean.getConnect_sn()) ? View.VISIBLE : View.GONE);
                    btnWeiXin.setVisibility("1".equals(datasBean.getConnect_wx()) ? View.VISIBLE : View.GONE);
                } else {
                    Toast.makeText(myApplication, JsonUtil.getString(JsonUtil.getString(response, "datas"), "error"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //返回按钮
    public void btnBackClick(View v) {
        finish();
    }

    //注册按钮
    public void btnRegisterClick(View v) {
        startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
        finish();
    }

    //自动登录选择
    public void btnAutoLoginClick(View v) {
        if (btnAutoLogin.isSelected()) {
            btnAutoLogin.setSelected(false);
        } else {
            btnAutoLogin.setSelected(true);
        }
    }

    //登录
    public void btnLoginClick(View v) {
        if (btnLogin.isActivated()) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username == null || username.trim().equals("")) {
                Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password == null || password.trim().equals("")) {
                Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            login(username, password);
        }
    }

    //处理登录按钮状态
    private void setBtnLoginState() {
        if (etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
            btnLogin.setActivated(false);
        } else {
            btnLogin.setActivated(true);
        }
    }

    //用户登录
    private void login(String username, String password) {
        String url = Constants.URL_LOGIN;
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("client", "android");
        RemoteDataHandler.asyncPostDataString(url, params, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();

                Logger.json( json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    ShopHelper.login(LoginActivity.this, myApplication, json);
                    LoginActivity.this.finish();
                } else {
                    ShopHelper.showApiError(LoginActivity.this, json);
                }
            }
        });
    }

    /**
     * 找回密码按钮点击
     */
    public void btnFindPasswordClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(Constants.WAP_FIND_PASSWORD));
        startActivity(intent);
    }

    /**
     * 获取QQUnionId
     *
     * @param token
     */
    private void getQQUnionId(final String token, final String openid, final String nickname, final String avatar) {
        String url = "https://graph.qq.com/oauth2.0/me?access_token=" + token + "&unionid=1";
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String s = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    Log.e("QQ", s);
                    String[] split = s.split(":");
                    s = split[split.length - 1];
                    split = s.split("\"");
                    s = split[1];
                    unionID = s;
                    Log.e("QQ", unionID);
                    loginQq(token, openid, nickname, avatar, unionID);
                } else {
//                    ShopHelper.showApiError(LoginActivity.this, json);
                }
            }
        });
    }


    /**
     * QQ同步登录
     *
     * @param token
     */
    private void loginQq(String token, String openid, String nickname, String avatar, String unionid) {
        String url = Constants.URL_CONNECT_QQ + "&token=" + token + "&open_id=" + openid + "&nickname=" + nickname + "&avatar=" + avatar + "&unionid=" + unionid + "&client=android";
        Log.e("qq_login_url", url);
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    ShopHelper.login(LoginActivity.this, myApplication, json);
                    LoginActivity.this.finish();
                } else {
                    ShopHelper.showApiError(LoginActivity.this, json);
                }
            }
        });
    }


    /**
     * 微博同步登录
     *
     * @param accessToken
     * @param userId
     */
    private void loginWeibo(String accessToken, String userId) {
        String url = Constants.URL_CONNECT_WEIBO + "&accessToken=" + accessToken + "&userID=" + userId + "&client=android";
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                LogHelper.e("json", json);
                LogHelper.e("data", data.toString());
                if (data.getCode() == HttpStatus.SC_OK) {
                    ShopHelper.login(LoginActivity.this, myApplication, json);
                    LoginActivity.this.finish();
                } else {
                    ShopHelper.showApiError(LoginActivity.this, json);
                }
            }
        });
    }

    /**
     * 微信登录
     */
    private void loginWx(String access_token, String openid) {
        String url = Constants.URL_CONNECT_WX + "&access_token=" + access_token + "&openid=" + openid + "&client=android";
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    ShopHelper.login(LoginActivity.this, myApplication, json);
                    LoginActivity.this.finish();
                } else {
                    ShopHelper.showApiError(LoginActivity.this, json);
                }
            }
        });
    }


    //授权
    private UMAuthListener umAuthListener = new UMAuthListener() {

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data != null) {
                if (platform == SHARE_MEDIA.QQ) {
                    for (Map.Entry<String, String> entry : data.entrySet()) {
                        Log.e("QQ", entry.getKey() + "--->" + entry.getValue());
                    }
                    token = data.get("access_token");
                    openid = data.get("openid");
                    unionID = data.get("unionid");
                    mShareAPI.getPlatformInfo(LoginActivity.this, platform, userinfo);
                } else if (platform == SHARE_MEDIA.WEIXIN) {
                    String access_token = data.get("access_token");
                    String openid = data.get("openid");
                    loginWx(access_token, openid);
                } else if (platform == SHARE_MEDIA.SINA) {
                    String accessToken = data.get("access_token");
                    String userId = data.get("uid");
                    loginWeibo(accessToken, userId);
                }
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "取消授权", Toast.LENGTH_SHORT).show();
        }
    };


    //获取用户信息
    private UMAuthListener userinfo = new UMAuthListener() {

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            String nickname = map.get("screen_name");
            String avatar = map.get("profile_image_url");
//            getQQUnionId(token, openid, nickname, avatar);
            loginQq(token, openid, nickname, avatar, unionID);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(getApplicationContext(), "获取用户信息失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View view) {
        SHARE_MEDIA platform = null;
        switch (view.getId()) {
            case R.id.btnQQ:
                platform = SHARE_MEDIA.QQ;
                break;
            case R.id.btnWeiXin:
                platform = SHARE_MEDIA.WEIXIN;
                break;
            case R.id.btnSina:
                platform = SHARE_MEDIA.SINA;
                break;
        }
        mShareAPI.doOauthVerify(LoginActivity.this, platform, umAuthListener);
    }
}
