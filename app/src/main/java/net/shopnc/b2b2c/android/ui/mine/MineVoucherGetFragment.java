package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import net.shopnc.b2b2c.R;
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
import butterknife.ButterKnife;

/**
 * 领券
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineVoucherGetFragment.java
 * @author: Jie
 * @date: 2016-05-30 11:06
 */
public class MineVoucherGetFragment extends MineBaseFragment {

    @Bind(R.id.etPwdCode)
    EditText etPwdCode;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.ivCode)
    ImageView ivCode;
    @Bind(R.id.btnSubmit)
    Button button;

    String key;
    @Bind(R.id.ivPhoto)
    ImageView ivPhoto;

    public static MineVoucherGetFragment newInstance() {
        return new MineVoucherGetFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        myApplication.getToken();
        loadSeccodeCode();
        ivCode.setOnClickListener(onClickListener);
        button.setOnClickListener(onClickListener);
        LoadImage.loadImageRotated(getActivity(), ivPhoto, 30f, R.drawable.mcc_08_w);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_get_voucher_fragment;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ivCode:
                    loadSeccodeCode();
                    break;
                case R.id.btnSubmit:
                    getVoucherByPwd();
                    break;
            }
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cur_fragment", 2);
    }

    /**
     *
     */
    private void getVoucherByPwd() {
        String value = etPwdCode.getText().toString();
        String captchaVa = etCode.getText().toString();

        if (TextUtils.isEmpty(value) || TextUtils.isEmpty(captchaVa)) {
            TToast.showShort(getActivity(), "输入不能为空！");
            return;
        }

        Map<String, String> param = new HashMap<>();
        param.put("token", myApplication.getToken());
        param.put("pwdCode", value);
        param.put("captchaKey", key);
        param.put("captchaVal", captchaVa);
        OkHttpUtil.postAsyn(getActivity(), ConstantUrl.URL_VOUCHER_PWD, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int i) {
//                Log.d(TAG, "onResponse: response = "+response);
                if (JsonUtil.toInteger(response, "code") == 200) {
                    TToast.showShort(getActivity(), "代金券领取成功");
                    etCode.setText("");
                    etPwdCode.setText("");
                } else {
                    loadSeccodeCode();
                    TToast.showShort(getActivity(), JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, param);
    }

    /**
     * 加载图片验证码标识
     */
    private void loadSeccodeCode() {
        etCode.setText("");
        OkHttpUtil.getAsyn(getActivity(), ConstantUrl.URL_DEPOSIT_CAPTCHAKEY, new BeanCallback<String>() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}