package net.shopnc.b2b2c.android.ui.mine;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.SendMsgListViewAdapter;
import net.shopnc.b2b2c.android.adapter.SmiliesViewAdapter;
import net.shopnc.b2b2c.android.bean.IMMemberInFo;
import net.shopnc.b2b2c.android.bean.IMMsgList;
import net.shopnc.b2b2c.android.bean.SmiliesList;
import net.shopnc.b2b2c.android.common.ComparatorMsg;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.SmiliesData;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.XListView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 发送消息界面
 *
 * @author KingKong-HE
 * @Time 2015-2-5
 * @Email KingKong@QQ.COM
 */
public class IMSendMsgActivity extends Activity implements OnClickListener {

    private boolean simFlag = false;//记录是否显示表情

    private MyGridView gridViewID;

    private EditText chatEditmessage;

    private MyShopApplication myApplication;

    private ArrayList<IMMsgList> imMsgLists;

    private String t_id, t_name;

    private MediaPlayer mediaPlayer;

    private SendMsgListViewAdapter adapter;

    private XListView listViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_send_msg);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();

        t_id = getIntent().getStringExtra("t_id");
        t_name = getIntent().getStringExtra("t_name");

        initViewUIID();

    }


    /**
     * 初始化控件ID
     */
    public void initViewUIID() {
        gridViewID = findViewById(R.id.gridViewID);
        ImageView imageBack = findViewById(R.id.imageBack);
        Button buttonSimilies = findViewById(R.id.buttonSimilies);
        Button chatSend = findViewById(R.id.chat_send);
        TextView historyButtonID = findViewById(R.id.historyButtonID);
        TextView textUserName = findViewById(R.id.textUserName);
        listViewID = findViewById(R.id.listViewID);
        chatEditmessage = findViewById(R.id.chat_editmessage);


        mediaPlayer = MediaPlayer.create(IMSendMsgActivity.this, R.raw.new_msg001);

        adapter = new SendMsgListViewAdapter(IMSendMsgActivity.this);

        imMsgLists = new ArrayList<IMMsgList>();

        listViewID.setAdapter(adapter);

        listViewID.setPullLoadEnable(false);

        listViewID.setPullRefreshEnable(false);

        historyButtonID.setOnClickListener(this);

        textUserName.setText(t_name == null ? "" : t_name);

        SmiliesViewAdapter smiliesViewAdapter = new SmiliesViewAdapter(IMSendMsgActivity.this);
        gridViewID.setAdapter(smiliesViewAdapter);
        smiliesViewAdapter.setSmiliesLists(SmiliesData.smiliesLists);
        adapter.setSmiliesLists(SmiliesData.smiliesLists);
        smiliesViewAdapter.notifyDataSetChanged();

        imageBack.setOnClickListener(this);
        chatSend.setOnClickListener(this);
        buttonSimilies.setOnClickListener(this);

        myApplication.UpDateUser();

        GetUserInFo(myApplication.getLoginKey(), t_id, "member_id");

        gridViewID.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                SmiliesList bean = (SmiliesList) gridViewID.getItemAtPosition(arg2);
                if (bean != null) {
                    Bitmap bitmap = null;
                    bitmap = BitmapFactory.decodeResource(IMSendMsgActivity.this.getResources(), bean.getPath());
                    ImageSpan imageSpan = new ImageSpan(IMSendMsgActivity.this, bitmap);
                    SpannableString spannableString = new SpannableString(bean.getTitle());
                    spannableString.setSpan(imageSpan, 0, bean.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//					chat_editmessage.append(spannableString);
                    int index = chatEditmessage.getSelectionStart();
                    Editable editable = chatEditmessage.getText();
                    editable.insert(index, spannableString);
                }
            }
        });

        listViewID.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                simFlag = false;
                gridViewID.setVisibility(View.GONE);

                return false;
            }
        });

        chatEditmessage.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                simFlag = false;
                gridViewID.setVisibility(View.GONE);

                return false;
            }
        });

    }

    /**
     * 发送消息
     *
     * @param key    登录标识
     * @param t_id   收消息ID
     * @param t_name 名字
     * @param t_msg  内容
     *               *
     */
    public void loadSendMsg(String key, String t_id, final String t_name, String t_msg) {
        String url = Constants.URL_MEMBER_CHAT_SEND_MSG;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", key);
        params.put("t_id", t_id);
        params.put("t_name", t_name);
        params.put("t_msg", t_msg);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String scanningTime = formatter.format(curDate);
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String msg = obj2.getString("msg");

                        myApplication.getmSocket().emit("send_msg", new JSONObject(msg));
                        String ures = "{\"disconnect_time\":\"\",\"s_name\":\"\",\"update_time\":\"\",\"connected\":\"\",\"s_id\":\"\",\"avatar\":\"" + myApplication.getMemberAvatar() + "\",\"u_id\":\"\",\"u_name\":\"\",\"online\":\"\"}";
                        IMMsgList l = new IMMsgList("0", "", "", "", myApplication.getUserName(), chatEditmessage.getText().toString(), scanningTime, ures);
                        l.setViewFlag(false);
                        imMsgLists.add(l);
                        adapter.setIMMsgList(imMsgLists);
                        adapter.notifyDataSetChanged();
                        listViewID.setSelection(imMsgLists.size());
                        chatEditmessage.setText("");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String error = obj2.getString("error");
                        if (error != null) {
                            Toast.makeText(IMSendMsgActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 获取用户资料
     */
    public void GetUserInFo(String key, String t_id, final String t) {
        String url = Constants.URL_MEMBER_CHAT_GET_USER_INFO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", key);
        params.put("u_id", myApplication.getMemberID());
        params.put("t", t);
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String msg = obj2.getString("member_info");
                        IMMemberInFo imMemberInFo = IMMemberInFo.newInstanceList(msg);
                        adapter.setImMemberInFo(imMemberInFo);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String error = obj2.getString("error");
                        if (error != null) {
                            Toast.makeText(IMSendMsgActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.IM_UPDATA_UI)) {
                String message = intent.getStringExtra("message");
                if (message != null && !TextUtils.isEmpty(message)) {
                    getMessageData(message);
                }
            }
        }
    };

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constants.IM_UPDATA_UI);
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        myApplication.setIMNotification(true);
        myApplication.setShowNum(true);
    }


    /**
     * 获取显示聊天列表
     */
    public void getMessageData(String message) {

        try {
            mediaPlayer.prepare();//提示音准备
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setLooping(false); //提示音不循环

        try {
            ArrayList<IMMsgList> datas = new ArrayList<IMMsgList>();

            int count = 1;

            JSONObject obj = new JSONObject(message);

            Iterator<?> itName = obj.keys();
            while (itName.hasNext()) {
                String ID = itName.next().toString();
                String str = obj.getString(ID);
                IMMsgList bean = IMMsgList.newInstanceList(str);
                if (t_id.equals(bean.getF_id())) {
                    datas.add(bean);
                    if (count == 1) {
                        mediaPlayer.start();//提示音播放
                        Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        long[] pattern = {100, 400, 100, 400}; // 停止 开启 停止 开启
                        vib.vibrate(pattern, -1); //重复两次上面的pattern 如果只想震动一次，index设
                        count++;
                    }
                }
            }
            ComparatorMsg comparatorMsg = new ComparatorMsg();
            Collections.sort(datas, comparatorMsg);
            imMsgLists.addAll(datas);
            adapter.setIMMsgList(imMsgLists);
            adapter.notifyDataSetChanged();
            listViewID.setSelection(imMsgLists.size());
            if (datas.size() != 0) {
                myApplication.getmSocket().emit("del_msg", new JSONObject("{'max_id':" + datas.get(datas.size() - 1).getM_id() + ",'f_id':" + t_id + "}"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBack:

                finish();

                break;
            case R.id.historyButtonID:

                Intent intent = new Intent(IMSendMsgActivity.this, IMHistoryListActivity.class);
                intent.putExtra("t_id", t_id);
                startActivity(intent);

                break;

            case R.id.chat_send:

                if (myApplication.isIMConnect()) {
                    String t_msg = chatEditmessage.getText().toString();
                    if (t_msg.equals("null") || t_msg.equals("") || t_msg == null) {
                        Toast.makeText(IMSendMsgActivity.this, "请输入发送的内容", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    loadSendMsg(myApplication.getLoginKey(), t_id, t_name, t_msg);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(IMSendMsgActivity.this);
                    builder.setTitle("IM离线通知").setMessage("您的IM账号已经离线，点击确定重新登录");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            myApplication.getmSocket().connect();
                        }
                    }).create().show();
                }

                break;
            case R.id.buttonSimilies:

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                if (simFlag) {
                    simFlag = false;
                    gridViewID.setVisibility(View.GONE);
                } else {
                    simFlag = true;
                    gridViewID.setVisibility(View.VISIBLE);
                }

                break;
            default:
                break;
        }
    }

}
