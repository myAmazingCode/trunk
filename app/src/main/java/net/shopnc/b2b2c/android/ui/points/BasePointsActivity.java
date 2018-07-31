package net.shopnc.b2b2c.android.ui.points;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.ui.mine.MinePointsActivity;

public abstract class BasePointsActivity extends BaseTencentX5Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 跳转到积分订单列表
     *
     * @return
     */
    @JavascriptInterface
    public void navigateToPointsLogList() {
        startActivity(new Intent(context, MinePointsActivity.class));
    }

    /**
     * 跳转积分商品详情页面
     *
     * @param commonId
     */
    @JavascriptInterface
    public void navigateToPointsGoodsDetail(final int commonId) {
        Intent intent = new Intent(context, PointsGoodsActivity.class);
        intent.putExtra("commonId", commonId);
        startActivity(intent);
    }

    /**
     * 跳转积分购买
     *
     * @param pointsGoodsId
     * @param goodsId
     * @param buyNum
     */
    @JavascriptInterface
    public void navigateToPointsGoodsBuyGoodsIdBuyNum(final int pointsGoodsId, final int goodsId, final int buyNum) {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, PointsGoodsBuyActivity.class);
            intent.putExtra("pointsGoodsId", pointsGoodsId);
            intent.putExtra("goodsId", goodsId);
            intent.putExtra("buyNum", buyNum);
            startActivity(intent);
        }
    }

    /**
     * 跳转到积分订单列表
     */
    @JavascriptInterface
    public void navigateToMemberPointsGoodsList() {
        if (ShopHelper.isLogin(context)) {
            //跳转到积分订单前关闭确认支付窗口
            finish();

            Intent intent = new Intent(context, PointOrdersListActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 跳转到积分订单详情
     */
    @JavascriptInterface
    public void navigateToPointOrdersDetail(final int pointsOrdersId) {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, PointOrdersDetailActivity.class);
            intent.putExtra("pointsOrdersId", pointsOrdersId);
            startActivity(intent);
        }
    }
}
