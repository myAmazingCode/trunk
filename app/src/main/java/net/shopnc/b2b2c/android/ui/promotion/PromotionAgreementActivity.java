package net.shopnc.b2b2c.android.ui.promotion;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.PromotionAgreement;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

public class PromotionAgreementActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("推广申请协议");
        init();
    }

    private void init() {

        String url = ConstantUrl.URL_API + "/member/distributor/join/get_agreement";

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {

            @Override
            public void onResponse(String response,int i) {
                Log.d("推广协议申请", "onResponse: response = " + response);
                if (JsonUtil.toInteger(response, "code") == 200) {
                    PromotionAgreement agreement = new Gson().fromJson(response, PromotionAgreement.class);
                    PromotionAgreement.DatasBean.JoinAgreementBean joinAgreement = agreement.getDatas().getJoinAgreement();

                    //标题、时间和内容
                    String content = joinAgreement.getContent();
                    String title = joinAgreement.getTitle();
                    String createTime = joinAgreement.getCreateTime();

                    // TODO: 2017/2/15 显示协议内容

                } else {
                    TToast.showShort(application,JsonUtil.toString(response,"datas","error"));
                }
            }

            @Override
            public void response(String data) {

            }
        }, new OkHttpUtil.Param("token", application.getToken()));
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_promotion_agreement);
    }
}
