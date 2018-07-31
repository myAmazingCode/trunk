package net.shopnc.b2b2c.android.ui.order;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.OrderDeliverInfoAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.OrderDeliverInfoBean;
import net.shopnc.b2b2c.android.bean.OrderDeliverInfoItemBean;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

public class OrderDeliveryInfoActivity extends BaseActivity {
    public static String SHIPSN = "shipSn";
    public static String SHIPCODE = "shipCode";


    @Bind(R.id.textShipCompany)
    TextView textShipCompany;
    @Bind(R.id.textShipId)
    TextView textShipId;
    @Bind(R.id.lvShipPath)
    MyListView lvShipPath;

    private String shipSn;
    private String shipCode;

    private OrderDeliverInfoBean deliverInfo;
    private OrderDeliverInfoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("物流详情");
        shipSn=getIntent().getStringExtra(SHIPSN);
        shipCode=getIntent().getStringExtra(SHIPCODE);

        adapter = new OrderDeliverInfoAdapter(context);
        lvShipPath.setAdapter(adapter);

        requestDeliveryInfo();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_delivery_info);
    }

    private void requestDeliveryInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("shipSn", shipSn);
        params.put("shipCode", shipCode);

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_ORDER_SHIP, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                deliverInfo = JsonUtil.toBean(data, new TypeToken<OrderDeliverInfoBean>() {
                }.getType());
                List<OrderDeliverInfoItemBean> items=deliverInfo.getExpressVoList();
                Collections.reverse(items);
                adapter.setmDatas(items);
                adapter.notifyDataSetChanged();

                textShipCompany.setText("物流公司："+deliverInfo.getShipName());
                textShipId.setText("运单号码："+deliverInfo.getShipSn());
            }
        }, params);
    }
}
