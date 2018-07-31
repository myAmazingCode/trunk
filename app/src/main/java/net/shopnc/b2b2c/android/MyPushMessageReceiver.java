package net.shopnc.b2b2c.android;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.umeng.socialize.utils.Log;


import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.ui.home.SubjectWebActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsListFragmentManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by snm on 2016/6/6.
 * 主要对 NotificationSound 点击处理
 */
public class MyPushMessageReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int errorCode, String appid,
                       String userId, String channelId, String requestId) {
        String responseString = "onBind errorCode=" + errorCode + " appid="
                + appid + " userId=" + userId + " channelId=" + channelId
                + " requestId=" + requestId;
        Log.d(responseString);

        if (errorCode == 0) {
            // 绑定成功
            Log.d("绑定成功");
        }
    }

    @Override
    public void onUnbind(Context context, int errorCode, String requestId) {
        String responseString = "onUnbind errorCode=" + errorCode
                + " requestId = " + requestId;
        Log.d(TAG, responseString);

        if (errorCode == 0) {
            // 解绑定成功
            Log.d(TAG, "解绑成功");
        }
        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
    }

    @Override
    public void onSetTags(Context context, int errorCode,
                          List<String> sucessTags, List<String> failTags, String requestId) {
        String responseString = "onSetTags errorCode=" + errorCode
                + " sucessTags=" + sucessTags + " failTags=" + failTags
                + " requestId=" + requestId;
        Log.d(TAG, responseString);

        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑

    }
    @Override
    public void onDelTags(Context context, int errorCode,
                          List<String> sucessTags, List<String> failTags, String requestId) {
        String responseString = "onDelTags errorCode=" + errorCode
                + " sucessTags=" + sucessTags + " failTags=" + failTags
                + " requestId=" + requestId;
        Log.d(TAG, responseString);

        // Demo更新界面展示代码，应用请在这里加入自己的处理逻辑

    }

    @Override
    public void onListTags(Context context, int i, List<String> strings, String s) {

    }

    @Override
    public void onMessage(Context context, String message,
                          String customContentString) {
        String messageString = "透传消息 onMessage=\"" + message
                + "\" customContentString=" + customContentString;
        Log.d(TAG, messageString);

        // 自定义内容获取方式，mykey和myvalue对应透传消息推送时自定义内容中设置的键和值
        if (!TextUtils.isEmpty(customContentString)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(customContentString);
                String myvalue = null;
                if (!customJson.isNull("mykey")) {
                    myvalue = customJson.getString("mykey");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
// * 接收通知点击的函数。
    @Override
    public void onNotificationClicked(Context context, String title,
                                      String description, String customContentString) {
        String notifyString = "通知点击 onNotificationClicked title=\"" + title + "\" description=\""
                + description + "\" customContent=" + customContentString;
        Log.d(TAG, notifyString);

        // 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
        if (!TextUtils.isEmpty(customContentString)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(customContentString);
                String myvalue = null;
                String type_v = null;
                if (!customJson.isNull("type")) {
                    myvalue = customJson.getString("type");
                    type_v = customJson.getString("type_v");
//                    type 推送类型(1为关键字,2为专题编号,3为商品编号)
//                    type_v 类型值
                    if (myvalue.equals("1")) {//搜索关键字
                        Intent intent = new Intent(context, GoodsListFragmentManager.class);
                        intent.putExtra("keyword", type_v);
                        intent.putExtra("gc_name", type_v);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.getApplicationContext().startActivity(intent);

                    } else if (myvalue.equals("2")) {//专题编号
                        Intent intent = new Intent(context, SubjectWebActivity.class);
                        intent.putExtra("data", Constants.URL_SPECIAL + "&special_id=" + type_v + "&type=html");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.getApplicationContext().startActivity(intent);
                    } else if (myvalue.equals("3")) {//商品编号
                        Intent intent = new Intent(context, GoodsDetailsActivity.class);
                        intent.putExtra("goods_id", type_v);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.getApplicationContext().startActivity(intent);
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
//        T.showShort(context,title);
    }
    //    * 接收通知到达的函数。
    @Override
    public void onNotificationArrived(Context context, String title,
                                      String description, String customContentString) {

        String notifyString = "通知到达 onNotificationArrived  title=\"" + title
                + "\" description=\"" + description + "\" customContent="
                + customContentString;
        Log.d(TAG, notifyString);

        // 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
        if (!TextUtils.isEmpty(customContentString)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(customContentString);
                String myvalue = null;
                if (!customJson.isNull("mykey")) {
                    myvalue = customJson.getString("mykey");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
