package net.shopnc.b2b2c.android.ui.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.IMFriendsEListViewAdapter;
import net.shopnc.b2b2c.android.adapter.IMNewListViewAdapter;
import net.shopnc.b2b2c.android.bean.IMFriendsG;
import net.shopnc.b2b2c.android.bean.IMFriendsList;
import net.shopnc.b2b2c.android.bean.IMMsgList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.StringUtils;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.MyExpandableListView;
import net.shopnc.b2b2c.android.custom.XListView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.pulltorefresh.library.PullToRefreshScrollView;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by snm on 2016/5/31.
 */
public class IMNewListActivity extends BaseActivity implements XListView.IXListViewListener {

    private XListView common_listview;
    private IMNewListViewAdapter adapter;
    private HashMap<String, Integer> message_num = new HashMap<String, Integer>();
    private LinearLayout llNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_new_list_view);
        setCommonHeader("消息列表");
        initViewID();
        loadingFriendsListData();//加载列表数据
    }

    /**
     * 初始化注册控件ID
     */
    public void initViewID() {
        common_listview = (XListView) findViewById(R.id.common_listview);
        adapter = new IMNewListViewAdapter(IMNewListActivity.this);
        llNoData = (LinearLayout) findViewById(R.id.llNoData);
        llNoData.setVisibility(View.GONE);
        common_listview.setAdapter(adapter);

        common_listview.setXListViewListener(this);
        common_listview.setPullRefreshEnable(true);
        common_listview.setPullLoadEnable(false);
        common_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                XListView listView = (XListView) adapterView;
                IMFriendsList bean = (IMFriendsList) listView.getItemAtPosition(i);

                if (bean != null) {
                    if (MyShopApplication.getInstance().getMemberID().equals(bean.getU_id())) {
                        T.showShort(IMNewListActivity.this, "对不起，您不可以和自己聊天");
                        return;
                    }
                    if (MyShopApplication.getInstance().isIMConnect()) {
                        if (adapter.userState != null && adapter.userState.size() > 0) {
                            if (adapter.userState.get(bean.getU_id()) != null && adapter.userState.get(bean.getU_id()).equals("1")) {
                                message_num.remove(bean.getU_id());
                                adapter.setMessageNum(message_num);
                                adapter.notifyDataSetChanged();
                                MyShopApplication.getInstance().setShowNum(false);
                                Intent intent = new Intent(IMNewListActivity.this, IMSendMsgActivity.class);
                                intent.putExtra("t_id", bean.getU_id());
                                intent.putExtra("t_name", bean.getU_name());
                                IMNewListActivity.this.startActivity(intent);
                            } else {
                                T.showShort(IMNewListActivity.this, "对不起，您的好友不在线");
                            }
                        } else {
                            T.showShort(IMNewListActivity.this, "对不起，您的好友不在线");
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(IMNewListActivity.this);
                        builder.setTitle("IM离线通知").setMessage("您的IM账号已经离线，点击确定重新登录");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MyShopApplication.getInstance().getmSocket().connect();
                            }
                        }).create().show();
                    }
                }
            }
        });
    }

    /**
     * 加载列表数据
     */
    public void loadingFriendsListData() {

        String url = Constants.URL_MEMBER_CHAT_GET_USER_LIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();

                    ArrayList<IMFriendsList> friendsC0List = new ArrayList<IMFriendsList>();
                    JSONObject object = new JSONObject();//记录UID
                    try {
                        JSONObject obj = new JSONObject(json);
                        String uList = obj.getString("list");
                        JSONObject arr = new JSONObject(uList);
                        int size = null == arr ? 0 : arr.length();
                        if (size < 1) {
                            llNoData.setVisibility(View.VISIBLE);
                        } else {
                            Iterator<?> itName = arr.keys();
                            while (itName.hasNext()) {
                                String ID = itName.next().toString();
                                String str = arr.getString(ID);
                                IMFriendsList bean = IMFriendsList.newInstanceList(str);
//                                if (bean.getFriend() != null && bean.getFriend().equals("1")) {
                                friendsC0List.add(bean);
//                                }
                                object.put(bean.getU_id(), "0");
                            }
                            adapter.setFriendsGList(friendsC0List);
                            adapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        llNoData.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    }
                    MyShopApplication.getInstance().UpDateUser();

                    MyShopApplication.getInstance().getmSocket().emit("get_state", object);
                } else {
                    llNoData.setVisibility(View.VISIBLE);
                    Toast.makeText(IMNewListActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 删除好友
     *
     * @param m_id 会员编号
     */
    public void deleteFriends(String m_id) {
        String url = Constants.URL_IM_FRIENDS_DELETE;

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("m_id", m_id);
        params.put("key", MyShopApplication.getInstance().getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        T.showShort(IMNewListActivity.this, "删除成功");

                        loadingFriendsListData();//初始化加载数据
                    }
                } else {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            T.showShort(IMNewListActivity.this, error);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 记录信息数量
     */
    public void getNewMessageNumber(String message) {
        try {
            JSONObject obj = new JSONObject(message);
            Iterator<?> itName = obj.keys();
            while (itName.hasNext()) {
                String ID = itName.next().toString();
                String str = obj.getString(ID);
                IMMsgList bean = IMMsgList.newInstanceList(str);
                if (message_num.containsKey(bean.getF_id())) {
                    int count = message_num.get(bean.getF_id());
                    count++;
                    message_num.put(bean.getF_id(), count);
                } else {
                    message_num.put(bean.getF_id(), 1);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.setMessageNum(message_num);
        adapter.notifyDataSetChanged();
    }


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.IM_UPDATA_UI)) {
                String message = intent.getStringExtra("message");
                if (message != null && !TextUtils.isEmpty(message)) {
                    if (MyShopApplication.getInstance().isShowNum()) {
                        getNewMessageNumber(message);
                    }
                }
            } else if (action.equals(Constants.IM_FRIENDS_LIST_UPDATA_UI)) {
                String get_state = intent.getStringExtra("get_state");
                if (get_state != null && !TextUtils.isEmpty(get_state)) {
                    adapter.userState.clear();
                    try {
                        JSONObject getStateObj = new JSONObject(new JSONObject(get_state).getString("u_state"));
                        Iterator<?> itName = getStateObj.keys();
                        while (itName.hasNext()) {
                            String ID = itName.next().toString();
                            String State = getStateObj.getString(ID);
                            adapter.userState.put(ID, State);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constants.IM_UPDATA_UI);
        myIntentFilter.addAction(Constants.IM_FRIENDS_LIST_UPDATA_UI);
        registerReceiver(mBroadcastReceiver, myIntentFilter); //注册广播
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBoradcastReceiver();
        MyShopApplication.getInstance().setIMNotification(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
        MyShopApplication.getInstance().setIMNotification(true);
    }

    @Override
    public void onRefresh() {
        loadingFriendsListData();//加载列表数据
        common_listview.stopRefresh();
    }

    @Override
    public void onLoadMore() {

    }
}
