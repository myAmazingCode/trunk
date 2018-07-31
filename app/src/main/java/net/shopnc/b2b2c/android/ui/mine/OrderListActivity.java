package net.shopnc.b2b2c.android.ui.mine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.OrderGroupListViewAdapter;
import net.shopnc.b2b2c.android.bean.OrderGroupList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.XListView;
import net.shopnc.b2b2c.android.custom.XListView.IXListViewListener;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 订单列表
 *
 * @author dqw
 * @Time 2015/8/13
 */
public class OrderListActivity extends BaseActivity implements IXListViewListener {

    private MyShopApplication myApplication;

    private String stateType;
    private String orderKey;
    private int pageno = 1;

    private EditText etOrderSearch;

    private Button btnOrderAll;
    private Button btnOrderNew;
    private Button btnOrderSend;
    private Button btnOrderNotakes;
    private Button btnOrderNoeval;

    private XListView listViewID;
    private Handler mXLHandler;
    private ArrayList<OrderGroupList> orderLists;
    private OrderGroupListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();

        etOrderSearch = (EditText) findViewById(R.id.etOrderSearch);

        listViewID = (XListView) findViewById(R.id.listViewID);
        orderLists = new ArrayList<OrderGroupList>();
        adapter = new OrderGroupListViewAdapter(OrderListActivity.this);
        mXLHandler = new Handler();
        listViewID.setAdapter(adapter);
        listViewID.setXListViewListener(this);

        initStateTabButton();
        setCommonHeader("");
        setCombinButton();
        setListEmpty(R.drawable.nc_icon_order, "您还没有相关订单", "可以去看看哪些想要买的");

        setOrderStateType(getIntent().getStringExtra("state_type"));
    }

    /**
     * 设置头部切换按钮
     */
    private void setCombinButton() {
        Button btnOrder = (Button) findViewById(R.id.btnOrder);
        btnOrder.setActivated(true);
        Button btnVirtualOrder = (Button) findViewById(R.id.btnVirtualOrder);
        btnVirtualOrder.setActivated(false);
        btnVirtualOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderListActivity.this, VirtualListActivity.class));
                finish();
            }
        });
    }

    /**
     * 初始化订单状态切换按钮
     */
    private void initStateTabButton() {
        btnOrderAll = (Button) findViewById(R.id.btnOrderAll);
        btnOrderAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrderStateType("");
            }
        });
        btnOrderNew = (Button) findViewById(R.id.btnOrderNew);
        btnOrderNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrderStateType("state_new");
            }
        });
        btnOrderSend = (Button) findViewById(R.id.btnOrderSend);
        btnOrderSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrderStateType("state_send");
            }
        });
        btnOrderNotakes = (Button) findViewById(R.id.btnOrderNotakes);
        btnOrderNotakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrderStateType("state_notakes");
            }
        });
        btnOrderNoeval = (Button) findViewById(R.id.btnOrderNoeval);
        btnOrderNoeval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrderStateType("state_noeval");
            }
        });
    }

    /**
     * 设置订状态
     */
    private void setOrderStateType(String type) {
        stateType = type;
        pageno = 1;
        stateType = type;
        btnOrderAll.setActivated(false);
        btnOrderNew.setActivated(false);
        btnOrderSend.setActivated(false);
        btnOrderNotakes.setActivated(false);
        btnOrderNoeval.setActivated(false);

        if (stateType == null || stateType.equals("")) {
            btnOrderAll.setActivated(true);
        } else if (stateType.equals("state_new")) {
            btnOrderNew.setActivated(true);
        } else if (stateType.equals("state_send")) {
            btnOrderSend.setActivated(true);
        } else if (stateType.equals("state_notakes")) {
            btnOrderNotakes.setActivated(true);
        } else if (stateType.equals("state_noeval")) {
            btnOrderNoeval.setActivated(true);
        }

        loadingListData();
    }

    /**
     * 订单搜索
     */
    public void btnOrderSearchClick(View view) {
        pageno = 1;
        orderKey = etOrderSearch.getText().toString();
        loadingListData();
    }

    /**
     * 初始化加载数据
     */
    public void loadingListData() {
        String url = Constants.URL_ORDER_LIST + "&curpage=" + pageno + "&page=" + Constants.PAGESIZE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("state_type", stateType);
        if (orderKey != null && !orderKey.equals("")) {
            params.put("order_key", orderKey);
        }

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
                        orderLists.clear();
                        hideListEmpty();
                    }
                    try {
                        JSONObject obj = new JSONObject(json);
                        String order_group_list = obj.getString("order_group_list");
                        ArrayList<OrderGroupList> groupList = OrderGroupList.newInstanceList(order_group_list);
                        if (groupList.size() > 0) {
                            orderLists.addAll(groupList);
                            adapter.setOrderLists(orderLists);
                            adapter.notifyDataSetChanged();
                        } else {
                            showListEmpty();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    ShopHelper.showApiError(OrderListActivity.this, json);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerBoradcastReceiver();
        loadingListData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.PAYMENT_SUCCESS)) {
                pageno = 1;
                listViewID.setPullLoadEnable(true);
                loadingListData();//初始化加载我的信息
            }
        }
    };

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constants.PAYMENT_SUCCESS);
        registerReceiver(mBroadcastReceiver, myIntentFilter);  //注册广播       
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
