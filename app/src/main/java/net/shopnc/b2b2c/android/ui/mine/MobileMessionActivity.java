package net.shopnc.b2b2c.android.ui.mine;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.util.HashMap;
import java.util.Map;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MobileMessionActivity.java
 * @author: Jie
 * @date: 2016-05-26 15:42
 */
public abstract class MobileMessionActivity extends BaseActivity {

    /**
     * 加载图片验证码
     */
    protected void loadVerifyCode(EditText etCode,ImageView ivCode) {
        etCode.setText("");
        LoadImage.loadRemoteImg(this, ivCode, "");//TODO 后期加上接口url
    }

    /**
     * 获取短信验证码
     */
    protected void btnGetSmsCaptchaClick(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("", "");
        params.put("", "");
        params.put("", "");

        try {
            OkHttpUtil.postAsyn(this,"", new BeanCallback() {


                @Override
                public void onResponse(Object o, int i) {

                }

                @Override
                public void response(Object data) {

                }

            }, params);
        } catch (Exception e) {
            LogHelper.i("验证码加载错误", e.getMessage());
        }
    }

    /**
     * 验证手机
     */
    protected void btnSubmitClick(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("", "");
        params.put("", "");
        params.put("", "");

        try {
            OkHttpUtil.postAsyn(this,"", new BeanCallback<String>() {


                @Override
                public void response(String data) {

                }

                @Override
                public void onResponse(String resp, int i) {
                    super.onResponse(resp, i);
                    int code = JsonUtil.toInteger(resp, "code");
                    if (code == 200) {
                        TToast.showShort(getBaseContext(), "验证成功");
                    }
                }
            }, params);
        } catch (Exception e) {
            LogHelper.i("手机验证出错", e.getMessage());
        }
    }

}
