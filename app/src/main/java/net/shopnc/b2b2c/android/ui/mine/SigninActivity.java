package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.SigninListViewAdapter;
import net.shopnc.b2b2c.android.bean.SigninInfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 签到
 *
 * @author dqw
 * @Time 2015-8-18
 */
public class SigninActivity extends BaseActivity {
    private MyShopApplication myApplication;

    private TextView tvPoint;
    private boolean isSignin = false;
    private LinearLayout llSigninAdd;
    private TextView tvSigninAddTitle;
    private TextView tvSigninAddSubTitle;
    private Button btnLookMyPoints;
    private LinearLayout llAvtivity;

    private PopupWindow popupWindow;
    private View viewPopScreen;

    ArrayList<SigninInfo> signinInfoArrayList;
    SigninListViewAdapter signinListViewAdapter;
    ListView lvSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        myApplication = (MyShopApplication) getApplicationContext();
        MyExceptionHandler.getInstance().setContext(this);
        tvPoint = (TextView) findViewById(R.id.tvPoint);
        //签到按钮
        llSigninAdd = (LinearLayout) findViewById(R.id.llSigninAdd);
        llSigninAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSignin) {
                    signinAdd();
                } else {
                    disableSignin();
                }
            }
        });
        tvSigninAddTitle = (TextView) findViewById(R.id.tvSigninAddTitle);
        tvSigninAddSubTitle = (TextView) findViewById(R.id.tvSigninAddSubTitle);
        btnLookMyPoints = (Button) findViewById(R.id.btnLookMyPoints);
        btnLookMyPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this,PointLogActivity.class));
            }
        });

        llAvtivity = (LinearLayout)findViewById(R.id.llAvtivity);
        llAvtivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 提示框
                showPopWindow();
            }
        });

        signinInfoArrayList = new ArrayList<SigninInfo>();
        signinListViewAdapter = new SigninListViewAdapter(SigninActivity.this);
        signinListViewAdapter.setList(signinInfoArrayList);
        lvSignin = (ListView) findViewById(R.id.lvSignin);
        lvSignin.setAdapter(signinListViewAdapter);

        //检查是否可以签到
        signinCheck();
        //读取积分
        loadPoint();
        //读取签到日志
        loadSigninList();

        setCommonHeader("签到领积分");
        setListEmpty(R.drawable.nc_icon_eval, "您还没有任何签到记录", "每日签到可获得会员积分奖励");
    }


    /**
     * 活动说明
     */

    private void showPopWindow(){
        if(popupWindow == null){
            viewPopScreen = LayoutInflater.from(SigninActivity.this).inflate(R.layout.nc_activity_popwindow, null);
            popupWindow = new PopupWindow(viewPopScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popupWindow.setTouchable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable(SigninActivity.this.getResources(), (Bitmap) null));
            popupWindow.update();

            //初始化popwindow的控件
            TextView tvContent = viewPopScreen.findViewById(R.id.tvContent);
            StringBuilder sb = new StringBuilder();
            sb.append("1、每人每天最多签到一次，签到后有机会获得积分\n" +
                    "2、网站可根据活动举办的实际情况，在法律允许的范围内，对本活动规则变动或调整。\n" +
                    "3、对不正当手段（包括但不限于作弊、扰乱系统、实施网络攻击等）参与活动的用户，网站有权禁止其参与活动，取消其获奖资格（如奖励已发放，网站有权追回）。\n" +
                    "4、活动期间，如遭遇自然灾害、网络攻击或系统故障等不可抗拒因素导致活动暂停举办，网站无需承担赔偿责任或进行补偿。");
            tvContent.setText(sb.toString());

            Button btnConfirm = viewPopScreen.findViewById(R.id.btnConfirm);
            FrameLayout flBack = viewPopScreen.findViewById(R.id.flBack);
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });

        }
        //设置出现位置
        popupWindow.showAtLocation(viewPopScreen, Gravity.CENTER, 0, 0);

    }

    /**
     * 判断是否可以签到
     */
    private void signinCheck() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_SIGNIN_CHECK, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        enableSignin(obj.optString("points_signin"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    disableSignin();
                }
            }
        });
    }

    /**
     * 设置为可以签到
     */
    private void enableSignin(String point) {
        isSignin = true;
        tvSigninAddTitle.setText("签到");
        tvSigninAddSubTitle.setText("+" + point + "积分");
    }

    /**
     * 设置为不可以签到
     */
    private void disableSignin() {
        isSignin = false;
        tvSigninAddTitle.setText("已签到");
        tvSigninAddSubTitle.setText("坚持哦!");
    }

    /**
     * 读取积分
     */
    private void loadPoint() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_MY_ASSET + "&fields=point", params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        tvPoint.setText(obj.optString("point"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 签到
     */
    private void signinAdd() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_SIGNIN_ADD, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    disableSignin();
                    loadSigninList();
                } else {
                    ShopHelper.showApiError(SigninActivity.this, json);
                }
            }
        });
    }

    /**
     * 读取签到列表
     */
    private void loadSigninList() {
        signinInfoArrayList.clear();
        String url = Constants.URL_MEMBER_SIGNIN_LIST + "&page=" + Constants.PAGESIZE + "&curpage=1";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        ArrayList<SigninInfo> list = SigninInfo.newInstanceList(obj.getString("signin_list"));
                        if (list.size() > 0) {
                            signinInfoArrayList.addAll(list);
                            signinListViewAdapter.notifyDataSetChanged();
                            loadPoint();
                            hideListEmpty();
                        } else {
                            showListEmpty();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(SigninActivity.this, json);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signin, menu);
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
