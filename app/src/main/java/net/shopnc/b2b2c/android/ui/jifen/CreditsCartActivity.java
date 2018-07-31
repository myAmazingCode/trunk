package net.shopnc.b2b2c.android.ui.jifen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.CreditsCartAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.CreditsCartBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.utils.SpaceItemTopDecoration;

import java.util.HashMap;

import butterknife.Bind;

public class CreditsCartActivity extends BaseActivity {

    @Bind(R.id.cart_recycler)
    RecyclerView cartRecycler;
    @Bind(R.id.tv_all_points)
    TextView tvAllPoints;
    @Bind(R.id.btn_exchange)
    Button btnExchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBtnMoreVisible();
        setCommonHeader("已选择的兑换礼品");
        initCartData();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_credits_cart);
    }

    public void initCartData(){
        HashMap<String, String> params = new HashMap<>();
        params.put("key", application.getLoginKey());
        RemoteDataHandler.asyncPostDataString(Constants.URL_CREDITS_CART, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String res = data.getJson();
                    CreditsCartBean bean = new Gson().fromJson(res, CreditsCartBean.class);

                    LinearLayoutManager manager = new LinearLayoutManager(context);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    cartRecycler.setLayoutManager(manager);
                    CreditsCartAdapter adapter = new CreditsCartAdapter(context, bean);
                    cartRecycler.setAdapter(adapter);
                    SpaceItemTopDecoration decoration = new SpaceItemTopDecoration(20);
                    cartRecycler.addItemDecoration(decoration);
                }
            }
        });
    }
}
