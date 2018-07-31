package net.shopnc.b2b2c.android.ui.mine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.ImHistoryListViewAdapter;
import net.shopnc.b2b2c.android.bean.IMHistoryList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.SmiliesData;
import net.shopnc.b2b2c.android.custom.XListView;
import net.shopnc.b2b2c.android.custom.XListView.IXListViewListener;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 聊天记录列表界面
 *
 * @author KingKong-HE
 * @Time 2015-2-6
 * @Email KingKong@QQ.COM
 */
public class IMHistoryListActivity extends Activity implements OnClickListener, IXListViewListener {

    private MyShopApplication myApplication;

    private Handler mXLHandler;

    public int pageno = 1;

    private ArrayList<IMHistoryList> historyLists;

    private ImHistoryListViewAdapter adapter;

    private XListView listViewID;

    private String t_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_history_view);
        MyExceptionHandler.getInstance().setContext(this);
        t_id = getIntent().getStringExtra("t_id");

        myApplication = (MyShopApplication) getApplicationContext();

        mXLHandler = new Handler();

        initViewID();
    }

    /**
     * 初始化注册控件ID
     */
    public void initViewID() {

        ImageView imageBack = findViewById(R.id.imageBack);

        listViewID = findViewById(R.id.listViewID);

        historyLists = new ArrayList<IMHistoryList>();

        adapter = new ImHistoryListViewAdapter(IMHistoryListActivity.this);

        adapter.setSmiliesLists(SmiliesData.smiliesLists);

        listViewID.setAdapter(adapter);

        imageBack.setOnClickListener(this);

        listViewID.setXListViewListener(this);

        loadingListData();//加载列表数据
    }


    /**
     * 加载列表数据
     */
    public void loadingListData() {
        String url = Constants.URL_MEMBER_CHAT_GET_LOG_INFO + "&curpage=" + pageno + "&page=" + Constants.PAGESIZE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("t_id", t_id);
        params.put("t", "30");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String updataTime = sdf.format(new Date(System.currentTimeMillis()));
        listViewID.setRefreshTime(updataTime);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                listViewID.stopLoadMore();
                listViewID.stopRefresh();

                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {

                    if (!data.isHasMore()) {
                        listViewID.setPullLoadEnable(false);
                    } else {
                        listViewID.setPullLoadEnable(true);
                    }
                    if (pageno == 1) {
                        historyLists.clear();
                    }

                    try {

                        JSONObject obj = new JSONObject(json);

                        String objJson = obj.getString("list");

                        ArrayList<IMHistoryList> fList = IMHistoryList.newInstanceList(objJson);

                        historyLists.addAll(fList);
                        adapter.setHistoryLists(historyLists);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String error = obj2.getString("error");
                        if (error != null) {
                            Toast.makeText(IMHistoryListActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBack:

                finish();

                break;

            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        mXLHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageno = 1;
                listViewID.setPullLoadEnable(true);
                loadingListData();
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        //上拉加载
        mXLHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageno = pageno + 1;
                loadingListData();
            }
        }, 1000);
    }

}
