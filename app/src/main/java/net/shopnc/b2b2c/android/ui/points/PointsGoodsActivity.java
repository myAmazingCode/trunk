package net.shopnc.b2b2c.android.ui.points;

import android.os.Bundle;

import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.util.ConstantUrl;

public class PointsGoodsActivity extends BasePointsActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int commonId = getIntent().getIntExtra("commonId", 0);
        if (commonId == 0) {
            finish();
        }

        String url = ConstantUrl.URL_WAP + "/tmpl/points_goods.html?commonId=" + commonId;

        LogHelper.d("PointsGoods url:" + url);
        loadUrl(url);
    }
}
