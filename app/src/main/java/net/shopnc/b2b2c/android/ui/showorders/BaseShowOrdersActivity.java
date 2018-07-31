package net.shopnc.b2b2c.android.ui.showorders;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;

public abstract class BaseShowOrdersActivity extends BaseTencentX5Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 跳转到晒宝列表
     */
    @JavascriptInterface
    public void navigateToShowOrdersList() {
        LogHelper.d("navigateToShowOrdersList");

        Intent intent = new Intent(context, ShowOrdersListActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到晒宝编辑页
     */
    @JavascriptInterface
    public void navigateToEditShowOrders(final int id) {
        LogHelper.d("navigateToEditShowOrders");

        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, ShowOrdersEditActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    @JavascriptInterface
    public void navigateToGoodsDistributonId(final int commonId, final int distributionGoodsId) {
        Intent intent = new Intent(context, GoodDetailsActivity.class);
        intent.putExtra(GoodDetailsActivity.COMMONID, commonId);
        intent.putExtra(GoodDetailsActivity.DISTRIBUTION_ID, distributionGoodsId);
        startActivity(intent);
    }

}
