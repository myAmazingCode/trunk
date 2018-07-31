package net.shopnc.b2b2c.android.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySuccessActivity extends AppCompatActivity {

    @Bind(R.id.tvDesc)
    TextView tvDesc;
    @Bind(R.id.tvAmount)
    TextView tvAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);

        //0支付成功,1货到付款
        int pay_type = getIntent().getIntExtra("pay_type", 0);

        if (pay_type == 0) {
            //若为支付成功，获取订单金额
            tvDesc.setText("订单支付成功");
            String payId = getIntent().getStringExtra("payId");
            if (TextUtils.isEmpty(payId)) {
                tvAmount.setVisibility(View.GONE);
                return;
            }
            OkHttpUtil.postAsyn(this,ConstantUrl.URL_API + "/member/buy/pay/success", new BeanCallback<String>() {
                @Override
                public void response(String data) {
                    tvAmount.setText(getResources().getString(R.string.money_rmb) + JsonUtil.toString(data, "ordersOnlinePayAmount"));
                }
            }, new OkHttpUtil.Param("token", MyShopApplication.getInstance().getToken()), new OkHttpUtil.Param("payId", payId));

        } else {
            tvDesc.setText("下单成功，我们会尽快发货，请保持电话畅通！");
            tvAmount.setVisibility(View.GONE);
        }

    }


    @OnClick({R.id.tvGoAhead, R.id.tvSeeDetails})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGoAhead:
                //返回app首页
                Intent intent2 = new Intent(this, MainFragmentManager.class);
                sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
                startActivity(intent2);
                finish();
                break;
            case R.id.tvSeeDetails:
                //查看已付款订单列表
                Intent intent = new Intent(this, OrderListActivity.class);
//                intent.putExtra(OrderListActivity.STATE, "pay");
                intent.putExtra(OrderListActivity.STATE, "");//all
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
