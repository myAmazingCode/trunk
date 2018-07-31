package net.shopnc.b2b2c.android.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.bean.CartStoreVo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.ui.good.GoodBusBean;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class CartActivity extends BaseActivity {

    @Bind(R.id.btnModify)
    TextView btnModify;
    @Bind(R.id.btnFinish)
    TextView btnFinish;
    @Bind(R.id.llFragment)
    LinearLayout llFragment;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;
    @Bind(R.id.btnChoose)
    Button btnChoose;
    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;

    private FragmentManager fragmentManager;
    private CartShowFragment showFragment;
    private CartEditFragment editFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        fragmentManager = getSupportFragmentManager();
        showFragment = new CartShowFragment();
        editFragment = new CartEditFragment();
        fragmentManager.beginTransaction()
                .add(R.id.llFragment, showFragment)
                .add(R.id.llFragment, editFragment)
                .commitAllowingStateLoss();
        setLoginEmpty();
        turnPage(showFragment, editFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        turnPage(showFragment, editFragment);
        btnModify.setVisibility(View.VISIBLE);
        btnFinish.setVisibility(View.GONE);
        requestData();
    }

    private void setLoginEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.no_data_d);
        tvEmptyTitle.setText("亲，您的购物车还没有宝贝哦~");
        tvEmptyBody.setText("");
        btnChoose.setVisibility(View.GONE);
        btnChoose.setText("随便逛逛");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainFragmentManager.class);
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
                context.startActivity(intent);
            }
        });
    }

    private void turnPage(Fragment fragment1, Fragment fragment2) {
        fragmentManager.beginTransaction()
                .show(fragment1)
                .hide(fragment2)
                .commit();
    }

    /**
     * 加载数据
     */
    private void requestData() {
        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("clientType", "android");
        if (Common.isEmpty(application.getToken()))
            params.put("cartData", application.getCartData());

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_CART_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                List<CartStoreVo> cartStoreVoList = JsonUtil.toBean(data, "cartStoreVoList", new TypeToken<List<CartStoreVo>>() {
                }.getType());

                if (cartStoreVoList == null || cartStoreVoList.size() == 0) {
                    setLoginEmpty();
                    layoutEmpty.setVisibility(View.VISIBLE);
                    llFragment.setVisibility(View.GONE);
                    btnModify.setVisibility(View.GONE);
                    btnFinish.setVisibility(View.GONE);
                } else {
                    layoutEmpty.setVisibility(View.GONE);
                    llFragment.setVisibility(View.VISIBLE);
                    EventBus.getDefault().post(new EventObj(EventObj.CARTDATA, cartStoreVoList));
                }

                EventBus.getDefault().post(new GoodBusBean(GoodBusBean.REFRESH_CART_COUNT));
            }
        }, params);
    }

    @OnClick({R.id.btnModify, R.id.btnFinish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnModify:
                turnPage(editFragment, showFragment);
                btnModify.setVisibility(View.GONE);
                btnFinish.setVisibility(View.VISIBLE);
                break;
            case R.id.btnFinish:
                turnPage(showFragment, editFragment);
                btnFinish.setVisibility(View.GONE);
                btnModify.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 接收EventBus传递的数据
     *
     * @param event
     */
    public void onEventMainThread(EventObj event) {
        if (event.getFlag().equals(EventObj.CARTREFRESH)) {
            requestData();
        }
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_cart2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
