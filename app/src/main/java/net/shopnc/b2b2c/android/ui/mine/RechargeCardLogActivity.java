package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.RechargeCardLogListViewAdapter;
import net.shopnc.b2b2c.android.bean.RechargeCardLogInfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.MyListEmpty;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 充值卡余额
 *
 * dqw
 * 2015/8/26
 */
public class RechargeCardLogActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private TextView tvRechargeCard;
    private ListView lvRechargeCardLog;
    private ArrayList<RechargeCardLogInfo> rechargeCardLogInfoArrayList;
    private RechargeCardLogListViewAdapter rechargeCardLogListViewAdapter;
    MyListEmpty myListEmpty;

    int currentPage = 1;
    boolean isHasMore = true;
    boolean isLastRow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_card_log);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("");
        initTabButton();

        myApplication = (MyShopApplication) getApplicationContext();
        tvRechargeCard = (TextView) findViewById(R.id.tvRechargeCard);
        lvRechargeCardLog = (ListView) findViewById(R.id.lvRechargeCardLog);
        lvRechargeCardLog.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (isHasMore && isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    isLastRow = false;
                    currentPage += 1;
                    loadRechargeCodeLog();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    isLastRow = true;
                }
            }
        });

        rechargeCardLogInfoArrayList = new ArrayList<RechargeCardLogInfo>();
        rechargeCardLogListViewAdapter = new RechargeCardLogListViewAdapter(RechargeCardLogActivity.this);
        lvRechargeCardLog.setAdapter(rechargeCardLogListViewAdapter);
        myListEmpty = (MyListEmpty) findViewById(R.id.myListEmpty);
        myListEmpty.setListEmpty(R.drawable.nc_icon_predeposit_white, "您尚未充值卡使用信息", "使用充值卡充值余额结算更方便");

        loadRechargeCard();
        loadRechargeCodeLog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRechargeCard();
        loadRechargeCodeLog();
    }

    private void initTabButton() {
        Button btnRechargeCardLog = (Button) findViewById(R.id.btnRechargeCardLog);
        Button btnRechargeCardAdd = (Button) findViewById(R.id.btnRechargeCardAdd);
        btnRechargeCardLog.setActivated(true);
        btnRechargeCardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RechargeCardLogActivity.this, RechargeCardAddActivity.class));
                finish();
            }
        });
    }

    /**
     * 读取预存款
     */
    private void loadRechargeCard() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_MY_ASSET + "&fields=available_rc_balance", params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        tvRechargeCard.setText("¥" + obj.optString("available_rc_balance"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 读取充值明细
     */
    private void loadRechargeCodeLog() {
        String url = Constants.URL_MEMBER_FUND_RECHARGECARDLOG + "&curpage=" + currentPage + "&page=" + Constants.PAGESIZE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    isHasMore = data.isHasMore();

                    if (currentPage == 1) {
                        rechargeCardLogInfoArrayList.clear();
                        myListEmpty.setVisibility(View.GONE);
                    }

                    try {
                        JSONObject obj = new JSONObject(json);
                        ArrayList<RechargeCardLogInfo> list = RechargeCardLogInfo.newInstanceList(obj.getString("log_list"));
                        if (list.size() > 0) {
                            rechargeCardLogInfoArrayList.addAll(list);
                            rechargeCardLogListViewAdapter.setList(rechargeCardLogInfoArrayList);
                            rechargeCardLogListViewAdapter.notifyDataSetChanged();
                        } else {
                            myListEmpty.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(RechargeCardLogActivity.this, json);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recharge_card_log, menu);
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
