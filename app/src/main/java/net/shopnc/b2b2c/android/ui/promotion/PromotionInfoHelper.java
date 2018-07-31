package net.shopnc.b2b2c.android.ui.promotion;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import net.shopnc.b2b2c.android.bean.JoininMessage;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.ui.mine.LoginActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

/**
 * Created by xws on 2017/2/8.
 */

public class PromotionInfoHelper {

    public static final String TAG = "PromotionInfoHelper";

    public interface OnEventListener {
        void onEvent();
    }


    public static void getJoinInfo(final Context context, final OnEventListener listener) {

        String token = MyShopApplication.getInstance().getToken();
        Log.d(TAG, "getJoinInfo: token = " + token);

        if (TextUtils.isEmpty(token)) {
            context.startActivity(new Intent(context, LoginActivity.class));
            return;
        }

        OkHttpUtil.postAsyn(context,ConstantUrl.DISTRIBUTOR_JOIN_INFO, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);

                int code = JsonUtil.toInteger(response, "code");
                if (code == 200) {
                    JoininMessage joinin = new Gson().fromJson(response, JoininMessage.class);
                    //审核状态 (10:新增，20：不同意，30 ：同意，90：清退)
                    int state = joinin.getDatas().getDistributorJoin().getState();

                    if (30 == state) {
                        if (listener != null) {
                            listener.onEvent();
                        }
                    } else {
                        PromotionDialog dialog = new PromotionDialog(context);
                        dialog.show();
                        dialog.setUserMessage("您已经提交了推广分享申请", "查看");
                    }
                } else if (code == 400) {
                    String error = JsonUtil.toString(response, "datas", "error");
                    if ("获取推广会员申请信息失败，申请信息错误".equals(error)) {
                        //显示对话框
                        new PromotionDialog(context).show();
                    }
                } else if (code == 401) {
                    //{"code":401,"datas":{"error":"授权失败"}}
                }
            }
        }, new OkHttpUtil.Param("token", token));
    }
}
