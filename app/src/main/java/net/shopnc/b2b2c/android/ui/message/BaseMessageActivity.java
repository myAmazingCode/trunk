package net.shopnc.b2b2c.android.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.ui.mine.MineDepositActivity;
import net.shopnc.b2b2c.android.ui.order.OrderDetailsActivity;
import net.shopnc.b2b2c.android.ui.order.OrderRefundAndReturnDetailsActivity;
import net.shopnc.b2b2c.android.ui.promotion.CommissionActivity;
import net.shopnc.b2b2c.android.ui.trys.MyTryListActivity;

/**
 * @author dqw
 *         Created 2017/4/18 16:47
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 消息父类
 */
public class BaseMessageActivity extends BaseTencentX5Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 跳转到系统消息列表
     */
    @JavascriptInterface
    public void navigateToMemberMessage(final int tplClass) {
        LogHelper.d("navigateToMemberMessage tplClass:" + tplClass);
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, MemberMessageActivity.class);
            intent.putExtra("tplClass", tplClass);
            startActivity(intent);
        }
    }

    /**
     * 跳转到消息接收设置
     */
    @JavascriptInterface
    public void navigateToMemberMessageSite() {
        if (ShopHelper.isLogin(context)) {
            startActivity(new Intent(context, MemberMessageSiteActivity.class));
        }
    }

    /**
     * 跳转到订单详情
     */
    @JavascriptInterface
    public void navigateToOrderDetail(final int orderId) {
        LogHelper.d("navigateToOrderDetail orderId:" + orderId);
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, OrderDetailsActivity.class);
            intent.putExtra(OrderDetailsActivity.ORDERSID, orderId);
            startActivity(intent);
        }
    }

    /**
     * 跳转余额提现列表
     */
    @JavascriptInterface
    public void navigateToPdCashList() {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(activity, MineDepositActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 跳转到预存款列表
     */
    @JavascriptInterface
    public void navigateToPredepositLog() {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(activity, MineDepositActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 跳转到佣金余额提现
     */
    @JavascriptInterface
    public void navigateToDistributionCashList() {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, CommissionActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 跳转到退款详情
     */
    @JavascriptInterface
    public void navigateToRefundInfo(final int refundId) {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, OrderRefundAndReturnDetailsActivity.class);
            intent.putExtra("refundId", refundId);
            intent.putExtra("flag", "refund");
            startActivity(intent);
        }
    }

    /**
     * 跳转到退货详情
     */
    @JavascriptInterface
    public void navigateToReturnInfo(final int refundId) {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, OrderRefundAndReturnDetailsActivity.class);
            intent.putExtra("refundId", refundId);
            intent.putExtra("flag", "return");
            startActivity(intent);
        }
    }

    /**
     * 跳转到试用列表
     */
    @JavascriptInterface
    public void navigateToTrysList() {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, MyTryListActivity.class);
            startActivity(intent);
        }
    }
}
