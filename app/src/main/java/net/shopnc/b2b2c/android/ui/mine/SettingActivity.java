package net.shopnc.b2b2c.android.ui.mine;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.NCDialog;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ncinterface.INCOnDialogConfirm;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

/**
 * 设置页面
 *
 * @author dqw
 * @Time 2015/8/17
 */
public class SettingActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private Button btnLogout;
    private TextView tvMobile, tvPaypwd;
    private RelativeLayout rlModifyPassword, rlBindMobile, rlModifyPayPassword, rlFeed;

    private Boolean isBindMobile = false;
    private String mobile = "";

    private NCDialog ncDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_view);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("设置");
        myApplication = (MyShopApplication) getApplicationContext();

        tvMobile = (TextView) findViewById(R.id.tvMobile);
        tvPaypwd = (TextView) findViewById(R.id.tvPaypwd);

        initModifyPassword();
        initBindMobile();
        initModifyPayPassword();
        initFeed();
        initLogout();
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadMobile();
        loadPaypwdInfo();
    }

    /**
     * 获取绑定手机信息
     */
    private void loadMobile() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_ACCOUNT_GET_MOBILE_INFO, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject object = new JSONObject(json);
                        if (object.optBoolean("state")) {
                            isBindMobile = true;
                            mobile = object.optString("mobile");
                            tvMobile.setText(object.optString("mobile"));
                        } else {
                            isBindMobile = false;
                            tvMobile.setText("未绑定");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(SettingActivity.this, json);
                }
            }
        });
    }

    /**
     * 获得是否设置支付密码信息
     */
    private void loadPaypwdInfo() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_ACCOUNT_GET_PAYPWD_INFO, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
//                Log.d("dqw", json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject object = new JSONObject(json);
                        if (object.optBoolean("state")) {
                            tvPaypwd.setVisibility(View.INVISIBLE);
                        } else {
                            tvPaypwd.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(SettingActivity.this, json);
                }
            }
        });
    }

    /**
     * 初始化修改密码
     */
    private void initModifyPassword() {
        rlModifyPassword = (RelativeLayout) findViewById(R.id.rlModifyPassword);
        rlModifyPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBindMobile) {
                    Intent intent = new Intent(SettingActivity.this, ModifyPasswordStep1Activity.class);
                    intent.putExtra("mobile", mobile);
                    startActivity(intent);
                } else {
                    startActivityForResult(new Intent(SettingActivity.this, BindMobileActivity.class), Constants.RESULT_FLAG_BIND_MOBILE);
                }
            }
        });
    }

    /**
     * 初始化绑定手机
     */
    private void initBindMobile() {
        rlBindMobile = (RelativeLayout) findViewById(R.id.rlBindMobile);
        rlBindMobile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBindMobile) {
                    Intent intent = new Intent(SettingActivity.this, UnbindMobileActivity.class);
                    intent.putExtra("mobile", mobile);
                    startActivityForResult(intent, Constants.RESULT_FLAG_BIND_MOBILE);
                } else {
                    startActivityForResult(new Intent(SettingActivity.this, BindMobileActivity.class), Constants.RESULT_FLAG_BIND_MOBILE);
                }
            }
        });
    }


    /**
     * 初始化修改支付密码
     */
    private void initModifyPayPassword() {
        rlModifyPayPassword = (RelativeLayout) findViewById(R.id.rlModifyPayPassword);
        rlModifyPayPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBindMobile) {
                    Intent intent = new Intent(SettingActivity.this, ModifyPaypwdStep1Activity.class);
                    intent.putExtra("mobile", mobile);
                    startActivityForResult(intent, Constants.RESULT_FLAG_SET_PAYPWD);
                } else {
                    startActivityForResult(new Intent(SettingActivity.this, BindMobileActivity.class), Constants.RESULT_FLAG_BIND_MOBILE);
                }
            }
        });
    }

    /**
     * 初始化反馈
     */
    private void initFeed() {
        rlFeed = (RelativeLayout) findViewById(R.id.rlFeed);
        rlFeed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SettingActivity.this, FeekBaskActivity.class));
            }
        });
    }

    /**
     * 初始化注销按钮
     */
    private void initLogout() {
        btnLogout = (Button) findViewById(R.id.btnLogout);

        String loginKey = myApplication.getLoginKey();
        if (loginKey != null && !loginKey.equals("")) {
            btnLogout.setVisibility(View.VISIBLE);
        } else {
            btnLogout.setVisibility(View.GONE);
        }

        btnLogout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("功能选择")
                        .setMessage("您确定注销当前帐号吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        myApplication.setLoginKey("");
                        myApplication.setMemberID("");
                        myApplication.setMemberAvatar("");
                        myApplication.setUserName("");
                        myApplication.getmSocket().disconnect();
                        myApplication.getmSocket().io().reconnection(false);
                        btnLogout.setVisibility(View.GONE);
                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
                        SettingActivity.this.finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
            }
        });
    }


    /**
     * 清除缓存
     */
    public void btnCleanClick(View view) {

        ncDialog = new NCDialog(SettingActivity.this);
        ncDialog.setText1("确认清除缓存?");
        ncDialog.setOnDialogConfirm(new INCOnDialogConfirm() {
            @Override
            public void onDialogConfirm() {
                MyFileAsyncTask fileAsyncTask = new MyFileAsyncTask();
                fileAsyncTask.execute();
            }
        });
        ncDialog.showPopupWindow();
    }

    /**
     * 意见反馈
     */
    public void btnHelpClick(View view) {
        startActivity(new Intent(SettingActivity.this, HelpActivity.class));
    }

    /**
     * 关于我们
     */
    public void btnAboutClick(View view) {
        startActivity(new Intent(SettingActivity.this, AboutActivity.class));
    }

    /**
     * 清除缓存
     */
    private class MyFileAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(SettingActivity.this);

        @Override
        protected String doInBackground(String... params) {
            delAllFile(Constants.CACHE_DIR_IMAGE);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 设置进度条风格，风格为圆形，旋转的
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // 设置ProgressDialog 标题
            dialog.setTitle("提示");
            // 设置ProgressDialog提示信息
            dialog.setMessage("正在删除...");
            dialog.show();
        }
    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path String 文件夹路径 如 c:/fqf
     */
    private void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
            }
        }
    }

}
