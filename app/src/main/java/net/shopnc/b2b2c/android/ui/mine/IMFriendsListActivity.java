package net.shopnc.b2b2c.android.ui.mine;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ScrollView;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.IMFriendsEListViewAdapter;
import net.shopnc.b2b2c.android.bean.IMFriendsG;
import net.shopnc.b2b2c.android.bean.IMFriendsList;
import net.shopnc.b2b2c.android.bean.IMMsgList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.custom.MyExpandableListView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.pulltorefresh.library.PullToRefreshBase;
import net.shopnc.b2b2c.android.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import net.shopnc.b2b2c.android.pulltorefresh.library.PullToRefreshScrollView;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * IM好友列表
 *
 * @author KingKong-HE
 * @Time 2015-2-4
 * @Email KingKong@QQ.COM
 */
public class IMFriendsListActivity extends BaseActivity {

    private PullToRefreshScrollView mPullRefreshScrollView;

    private MyShopApplication myApplication;

    private HashMap<String, Integer> message_num = new HashMap<String, Integer>();

    private IMFriendsEListViewAdapter adapter;

    private MyExpandableListView listViewID;

    private HashMap<Integer, ArrayList<IMFriendsList>> mapList = new HashMap<Integer, ArrayList<IMFriendsList>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_friends_list_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();

        initViewID();

        setCommonHeader("好友列表");

    }

    /**
     * 初始化注册控件ID
     */
    public void initViewID() {
        listViewID = (MyExpandableListView) findViewById(R.id.listViewID);

        mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_scrollview);

        adapter = new IMFriendsEListViewAdapter(IMFriendsListActivity.this);

        listViewID.setAdapter(adapter);

        myApplication.getmNotificationManager().cancelAll();//清除APP所有通知

        loadingFriendsListData();//加载列表数据

        //二级分类点击监听
        listViewID.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        listViewID.collapseGroup(i);
                    }
                }
                IMFriendsG bean = (IMFriendsG) adapter.getGroup(groupPosition);

                if (bean != null) {

                    ArrayList<IMFriendsList> cList = mapList.get(bean.getId());

                    if (cList != null && cList.size() > 0) {

                        adapter.setFriendsCList(cList);
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        });

        //点击监听
        listViewID.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                IMFriendsList bean = (IMFriendsList) adapter.getChild(groupPosition, childPosition);
                if (bean != null) {
                    if (myApplication.getMemberID().equals(bean.getU_id())) {
                        Toast.makeText(IMFriendsListActivity.this, "对不起，您不可以和自己聊天", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    if (myApplication.isIMConnect()) {

                        if (adapter.userState != null && adapter.userState.size() > 0) {
                            if (adapter.userState.get(bean.getU_id()) != null && adapter.userState.get(bean.getU_id()).equals("1")) {
                                message_num.remove(bean.getU_id());
                                adapter.setMessageNum(message_num);
                                adapter.notifyDataSetChanged();
                                myApplication.setShowNum(false);
                                Intent intent = new Intent(IMFriendsListActivity.this, IMSendMsgActivity.class);
                                intent.putExtra("t_id", bean.getU_id());
                                intent.putExtra("t_name", bean.getU_name());
                                IMFriendsListActivity.this.startActivity(intent);
                            } else {
                                Toast.makeText(IMFriendsListActivity.this, "对不起，您的好友不在线", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(IMFriendsListActivity.this, "对不起，您的好友不在线", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(IMFriendsListActivity.this);
                        builder.setTitle("IM离线通知").setMessage("您的IM账号已经离线，点击确定重新登录");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                myApplication.getmSocket().connect();
                            }
                        }).create().show();
                    }
                }

                return false;
            }
        });

        //下拉刷新监听
        mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                loadingFriendsListData();
            }
        });

//		listViewID.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				
//				final IMFriendsList bean = (IMFriendsList) listViewID.getItemAtPosition(arg2);
//				
//				if(bean != null){
//					
//					AlertDialog.Builder builder = new AlertDialog.Builder(IMFriendsListActivity.this);
//					builder.setTitle("功能选择")
//					.setMessage("删除好友，您确定操作的功能？");  
//					builder.setPositiveButton("删除",new DialogInterface.OnClickListener() {
//							@Override
//		                    public void onClick(DialogInterface dialog, int whichButton) { 
//								deleteFriends(bean.getU_id());
//							}  
//		                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								
//							}
//						}).create().show();
//					
//				}
//				
//				return false;
//			}
//		});
    }


    /**
     * 加载列表数据
     */
    public void loadingFriendsListData() {
        String url = Constants.URL_MEMBER_CHAT_GET_USER_LIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                mPullRefreshScrollView.onRefreshComplete();//加载完成下拉控件取消显示

                if (data.getCode() == HttpStatus.SC_OK) {

                    String json = data.getJson();

                    message_num.clear();

                    mapList.clear();

                    ArrayList<IMFriendsG> friendsGList = new ArrayList<IMFriendsG>();

                    friendsGList.add(new IMFriendsG("好友列表", 0)); //name列表名称 id列表id 0是好友列表 1 是最近联系人
                    friendsGList.add(new IMFriendsG("最近联系人", 1)); //name列表名称 id列表id 0是好友列表 1 是最近联系人

                    ArrayList<IMFriendsList> friendsC0List = new ArrayList<IMFriendsList>();
                    ArrayList<IMFriendsList> friendsC1List = new ArrayList<IMFriendsList>();

                    JSONObject object = new JSONObject();//记录UID

                    try {
                        JSONObject obj = new JSONObject(json);
                        String uList = obj.getString("list");
                        JSONObject arr = new JSONObject(uList);
                        Iterator<?> itName = arr.keys();
                        while (itName.hasNext()) {
                            String ID = itName.next().toString();
                            String str = arr.getString(ID);
                            IMFriendsList bean = IMFriendsList.newInstanceList(str);
                            if (bean.getFriend() != null && bean.getFriend().equals("1")) {
                                friendsC0List.add(bean);
                            }

                            if (bean.getRecent() != null && bean.getRecent().equals("1")) {
                                friendsC1List.add(bean);
                            }

                            object.put(bean.getU_id(), "0");
                        }

                        mapList.put(0, friendsC0List);
                        mapList.put(1, friendsC1List);

                        adapter.setFriendsGList(friendsGList);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    myApplication.UpDateUser();

                    myApplication.getmSocket().emit("get_state", object);
                } else {
                    Toast.makeText(IMFriendsListActivity.this,R.string.load_error,Toast.LENGTH_SHORT).show();
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
        params.put("key", myApplication.getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        Toast.makeText(IMFriendsListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        loadingFriendsListData();//初始化加载数据
                    }
                } else {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            Toast.makeText(IMFriendsListActivity.this, error, Toast.LENGTH_SHORT).show();
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
                    if (myApplication.isShowNum()) {
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
        myApplication.setIMNotification(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
        myApplication.setIMNotification(true);
    }
}
