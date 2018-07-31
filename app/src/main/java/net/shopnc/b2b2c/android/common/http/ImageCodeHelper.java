package net.shopnc.b2b2c.android.common.http;

import android.widget.ImageView;


import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.*;
import net.shopnc.b2b2c.android.util.BeanCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xws on 2016/11/17.
 */

public class ImageCodeHelper {

    private ImageCodeHelper() {
    }

    public static void loadImageCode(final ImageView view, final OnImageCodeKeyListener l) {
        net.shopnc.b2b2c.android.util.OkHttpUtil.getAsyn(view.getContext(), ConstantUrl.URL_API + "/captcha/makecaptchakey", new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String resp, int i) {
                super.onResponse(resp, i);
                parseJSON(resp, view, l);
            }
        });
    }

    private static void parseJSON(String response, ImageView view, OnImageCodeKeyListener l) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            int code = jsonObject.getInt("code");
            JSONObject datas = jsonObject.getJSONObject("datas");
            if (200 == code) {
                String captchaKey = datas.getString("captchaKey");
                if (l != null) {
                    l.onImageCodeKey(captchaKey);
                }
                requestSceCode(captchaKey, view);
            } else if (400 == code) {
                String error = datas.getString("error");
                TToast.showShort(MyShopApplication.getInstance(), error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //请求验证码
    private static void requestSceCode(String captchaKey, ImageView view) {
        String url = ConstantUrl.URL_API + "/captcha/makecaptcha?captchaKey=" + captchaKey + "&clientType=android";
        LoadImage.loadRemoteImg(MyShopApplication.getInstance(), view, url);
    }

    public interface OnImageCodeKeyListener {
        void onImageCodeKey(String captchaKey);
    }
}
