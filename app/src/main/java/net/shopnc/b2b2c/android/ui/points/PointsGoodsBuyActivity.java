package net.shopnc.b2b2c.android.ui.points;

import android.os.Bundle;

import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.util.ConstantUrl;

public class PointsGoodsBuyActivity extends BasePointsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pointsGoodsId = getIntent().getIntExtra("pointsGoodsId", 0);
        int goodsId = getIntent().getIntExtra("goodsId", 0);
        int buyNum = getIntent().getIntExtra("buyNum", 0);
        if (pointsGoodsId == 0 | goodsId == 0 | buyNum == 0) {
            finish();
        }

        String url = ConstantUrl.URL_WAP + "/tmpl/points_goods_buy.html?pointsGoodsId=" + pointsGoodsId + "&goodsId=" + goodsId + "&buyNum=" + buyNum;

        LogHelper.d("PointsGoodsBuy url:" + url);
        loadUrl(url);
    }
}
