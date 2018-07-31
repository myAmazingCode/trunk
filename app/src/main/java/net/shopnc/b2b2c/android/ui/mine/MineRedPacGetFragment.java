package net.shopnc.b2b2c.android.ui.mine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineRedPacGetFragment.java
 * @author: Jie
 * @date: 2016-05-31 11:18
 */
public class MineRedPacGetFragment extends MineBaseFragment {

    private static final String TAG = "MineRedPacGetFragment";
    @Bind(R.id.etPwdCode)
    EditText etPwdCode;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.ivCode)
    ImageView ivCode;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.ivMoney)
    ImageView ivMoney;

    String key;
    private Context context;

    public static MineRedPacGetFragment newInstance() {
        return new MineRedPacGetFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadSeccodeCode();

        btnSubmit.setSelected(false);
        etPwdCode.addTextChangedListener(watcher);
        etCode.addTextChangedListener(watcher);
        setMoneyImage();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cur_fragment", 2);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_get_redpac_fragment;
    }

    @OnClick({R.id.ivCode, R.id.btnSubmit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivCode:
                loadSeccodeCode();
                break;
            case R.id.btnSubmit:
                if (btnSubmit.isSelected()) {
                    commitInfo();
                } else {
                    TToast.showShort(context, "输入不能为空！");
                }
                break;
        }
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            setSubmitSelected();
        }
    };

    private void setSubmitSelected() {
        String s1 = etPwdCode.getText().toString();
        String s2 = etCode.getText().toString();
        if (s1.length() > 0 && s2.length() > 0) {
            btnSubmit.setSelected(true);
        } else {
            btnSubmit.setSelected(false);
        }
    }

    private void setMoneyImage() {
        LoadImage.loadImageRotated(context, ivMoney, 30f, R.drawable.mcc_09_w);
    }

    /**
     * 加载图片验证码标识
     */
    private void loadSeccodeCode() {
        etCode.setText("");
        OkHttpUtil.getAsyn(getActivity(),ConstantUrl.URL_DEPOSIT_CAPTCHAKEY, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    key = jsonObject.getString("captchaKey");
                } catch (Exception e) {
                }
                LoadImage.loadRemoteImg(getActivity(), ivCode, ConstantUrl.URL_PHNOE_CAPTCHER_IMAGE + "?captchaKey=" + key + "&clientType=android");
            }
        });
    }

    /**
     * 提交
     */
    private void commitInfo() {
        Map<String, String> p = new HashMap<>();
        p.put("token", myApplication.getToken());
        p.put("pwdCode", etPwdCode.getText().toString());
        p.put("captchaKey", key);
        p.put("captchaVal", etCode.getText().toString());

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_RED_PACKAGE_GET, new BeanCallback<String>() {


            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (JsonUtil.toInteger(response, "code") == 200) {
                    TToast.showShort(getActivity(), "红包领取成功");
                    EventBus.getDefault().post(new EventObj(EventObj.REDPACKAGEGETSUCCESS));
                } else {
                    loadSeccodeCode();
                    TToast.showShort(getActivity(), JsonUtil.toString(response, "datas", "error"));
                }
            }

            @Override
            public void response(String data) {

            }
        }, p);
    }

}