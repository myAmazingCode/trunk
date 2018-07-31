package net.shopnc.b2b2c.android.ui.im;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.autobahn2.WebSocket;
import com.autobahn2.WebSocketConnection;
import com.autobahn2.WebSocketException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.BuildConfig;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.bean.IMMessage;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.http.JsonUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IMService extends Service {
    private IMServiceBinder binder = new IMServiceBinder();
    private int sid;
    private MyShopApplication application;
    private long mid;

    public static final String TAG = "WebSocketConnection";
    private WebSocketConnection mConnection = new WebSocketConnection();

    public IMService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        sid = intent.getIntExtra("sid", 0);
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = MyShopApplication.getInstance();

        init();
    }

    private void init() {
        final String wsUri = BuildConfig.IM;
        try {
            mConnection.connect(new URI(wsUri), new WebSocket.WebSocketConnectionObserver() {
                @Override
                public void onOpen() {
                    sureBuyer();
                    Log.d(TAG, "onOpen: wsUri = " + wsUri);
                }

                @Override
                public void onClose(WebSocketCloseNotification code, String reason) {
                    Log.d(TAG, "onClose: code = " + code + ",reason = " + reason);
                }

                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "onTextMessage: payload = " + payload);
                    if (JsonUtil.toString(payload, "type").equals("202")) {
                        sendBuyerMessageState(payload);
                    } else if (JsonUtil.toString(payload, "type").equals("203")) {  //推送聊天消息
                        sendSellerMessageState(payload);
                    }
                }

                @Override
                public void onRawTextMessage(byte[] payload) {
                    Log.d(TAG, "onRawTextMessage: payload = " + new String(payload));
                }

                @Override
                public void onBinaryMessage(byte[] payload) {
                    Log.d(TAG, "onBinaryMessage: payload = " + new String(payload));
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    private void sureBuyer() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("sid", sid + "");
        params.put("mType", "sub");
        params.put("cType", "android");
        mConnection.sendTextMessage(new Gson().toJson(params));

    }

    public void sendBuyerMessage(String content) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("sid", sid + "");
        params.put("mType", "send");
        params.put("cType", "android");
        params.put("message_content", content);
        mid = System.currentTimeMillis();
        params.put("mid", mid + "");
        String json = new Gson().toJson(params);
        Log.d("json", "sendBuyerMessage: json = " + json);
        mConnection.sendTextMessage(json);
    }

    private void sendBuyerMessageState(String payload) {
        IMMessage imMessage = JsonUtil.toBean(payload, "datas", "msg", new TypeToken<IMMessage>() {
        }.getType());
        EventBus.getDefault().post(new EventObj(EventObj.IMMESSAGESENDSUCCESS, imMessage));
    }

    private void sendSellerMessageState(String payload) {
        List<IMMessage> imMessages = JsonUtil.toBean(payload, "datas", "msgs", new TypeToken<ArrayList<IMMessage>>() {
        }.getType());
        EventBus.getDefault().post(new EventObj(EventObj.IMMESSAGEGETSUCCESS, imMessages));
    }

    class IMServiceBinder extends Binder {
        public IMService getService() {
            return IMService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mConnection.isConnected()) {
            mConnection.disconnect();
        }
    }
}