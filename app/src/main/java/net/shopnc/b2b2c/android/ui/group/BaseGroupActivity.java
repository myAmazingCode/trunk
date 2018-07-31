package net.shopnc.b2b2c.android.ui.group;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.ui.good.GoodHelper;
import net.shopnc.b2b2c.android.util.Constants;

public abstract class BaseGroupActivity extends BaseTencentX5Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 开团购买
     *
     * @param groupId
     * @param buyDataString
     */
    @JavascriptInterface
    public void navigateToCreateGroupBuyDataString(final int groupId, final String buyDataString) {
        LogHelper.d("navigateToCreateGroupBuyDataString groupId:" + groupId);
        LogHelper.d("navigateToCreateGroupBuyDataString buyDataString:" + buyDataString);
        if (ShopHelper.isLogin(context)) {
            GoodHelper.buyNow(context, application.getToken(), buyDataString, Constants.NO, Constants.YES, groupId, 0);
        }
    }

    /**
     * 参团购买
     *
     * @param groupId
     * @param goId
     * @param buyDataString
     */
    @JavascriptInterface
    public void navigateToBuyJoinGroupGoIdBuyDataString(final int groupId, final int goId, final String buyDataString) {
        if (ShopHelper.isLogin(context)) {
            GoodHelper.buyNow(context, application.getToken(), buyDataString, Constants.NO, Constants.YES, groupId, goId);
        }
    }

    /**
     * 跳转到参团页面
     *
     * @param goId
     */
    @JavascriptInterface
    public void navigateToJoinGroup(final int goId) {
        LogHelper.d("navigateToJoinGroup goId:" + goId);

        Intent intent = new Intent(context, GroupShareActivity.class);
        intent.putExtra("goId", goId);
        startActivity(intent);
    }

    /**
     * 跳转到团购规则
     *
     * @param id
     */
    @JavascriptInterface
    public void navigateToGroupRule(final int id) {
        LogHelper.d("navigateToGroupRule");

        Intent intent = new Intent(context, GroupRuleActivity.class);
        intent.putExtra("groupId", id);
        startActivity(intent);
    }

    /**
     * 跳转开团页面，等同于跳转到拼团详情
     */
    @JavascriptInterface
    public void navigateToJoinGroupGoId(final int commonId, final int goId) {
        LogHelper.d("navigateToJoinGroupGoId");

        Intent intent = new Intent(context, GroupDetailActivity.class);
        intent.putExtra("commonId", commonId);
        intent.putExtra("goId", goId);
        startActivity(intent);
    }

    /**
     * 跳转开团页面，等同于跳转到拼团详情
     */
    @JavascriptInterface
    public void navigateToCreateGroup(final int commonId) {
        LogHelper.d("navigateToCreateGroup");

        Intent intent = new Intent(context, GroupDetailActivity.class);
        intent.putExtra("commonId", commonId);
        startActivity(intent);
    }

}
