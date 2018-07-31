package net.shopnc.b2b2c.android.ui.points;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class PointOrdersDetailActivity extends BasePointsActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pointsOrdersId = getIntent().getIntExtra("pointsOrdersId", 0);
        if (pointsOrdersId == 0) {
            finish();
        }

        String url = ConstantUrl.URL_WAP + "/tmpl/member/point_orders_detail.html?ordersId=" + pointsOrdersId;
        loadUrl(url);
    }
}
