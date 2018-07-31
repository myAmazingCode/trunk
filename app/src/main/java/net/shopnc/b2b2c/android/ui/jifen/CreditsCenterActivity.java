package net.shopnc.b2b2c.android.ui.jifen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.MultiCreditsAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.CreditsCenterBean;
import net.shopnc.b2b2c.android.bean.Login;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.StringUtils;
import net.shopnc.b2b2c.android.custom.CircleImageView;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.mine.SigninActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 积分中心首页类，采用一个recyclerview，多item类型，加头view布局
 */
public class CreditsCenterActivity extends BaseActivity {

    @Bind(R.id.credits_recycler)
    RecyclerView creditsRecycler;

    private boolean isRefresh = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCommonHeader("积分中心");
        setBtnMoreVisible();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_credits_center);
    }

    public void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("act", "points");
        params.put("op", "index");
        if (!application.getLoginKey().equals("")) {
            params.put("key", application.getLoginKey());
        }
        RemoteDataHandler.asyncPostDataString(Constants.URL_CREDITS_CENTER, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String json = data.getJson();
                    Gson gson = new Gson();
                    CreditsCenterBean bean = gson.fromJson(json, CreditsCenterBean.class);
                    MultiCreditsAdapter adapter = new MultiCreditsAdapter(CreditsCenterActivity.this, bean,application.getLoginKey());
                    creditsRecycler.setLayoutManager(new LinearLayoutManager(CreditsCenterActivity.this, LinearLayoutManager.VERTICAL, false));
//                    creditsRecycler.setAdapter(adapter);
                    //添加头布局
                    View view = View.inflate(CreditsCenterActivity.this, R.layout.credits_center_header, null);
                    if (!application.getLoginKey().equals("")) { //登录状态
                        CircleImageView ivPict = view.findViewById(R.id.iv_header);
                        Glide.with(CreditsCenterActivity.this)
                                .load(bean.getMember_info().getAvatar())
                                .into(ivPict);
                        TextView tvMyCredits = view.findViewById(R.id.tv_my_credits);
                        TextView tvMyVoucher = view.findViewById(R.id.tv_my_voucher);
                        TextView tvMyPacket = view.findViewById(R.id.tv_my_redpacket);
                        tvMyCredits.setText(bean.getMember_info().getMember_points());
                        tvMyVoucher.setText(String.valueOf(bean.getVouchercount()));
                        tvMyPacket.setText(String.valueOf(bean.getRedpacketcount()));
                        TextView tvExperience = view.findViewById(R.id.tv_experience);
                        tvExperience.setText("经验值：" + bean.getMember_info().getMember_exppoints());
                        TextView tvLevel = view.findViewById(R.id.tv_level);
                        tvLevel.setText("Lv."+bean.getMember_info().getLevel());
                        TextView tvName = view.findViewById(R.id.tv_name);
                        tvName.setText(bean.getMember_info().getUser_name());


                    } else {  //未登录状态
                        RelativeLayout layoutLogin = view.findViewById(R.id.layout_login);
                        layoutLogin.setVisibility(View.GONE);
                        TextView tvLoginNow = view.findViewById(R.id.tv_login_now);
                        tvLoginNow.setVisibility(View.VISIBLE);
                        tvLoginNow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //登录
                                ShopHelper.isLogin(CreditsCenterActivity.this, application.getLoginKey());
                                isRefresh = true;
                            }
                        });
                    }
                    LinearLayout layoutMyCredits = view.findViewById(R.id.layout_my_credits);
                    LinearLayout layoutVoucher = view.findViewById(R.id.layout_voucher);
                    LinearLayout layoutRedPacket = view.findViewById(R.id.layout_red_packet);
                    layoutMyCredits.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, CreditsRecordActivity.class);
                            startActivity(intent);
                        }
                    });
                    layoutVoucher.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    layoutRedPacket.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                    HeaderAndFooterWrapper head = new HeaderAndFooterWrapper(adapter);
                    head.addHeaderView(view);
                    creditsRecycler.setAdapter(head);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isRefresh) {
            isRefresh = false;
            initData();

        }
    }
}
