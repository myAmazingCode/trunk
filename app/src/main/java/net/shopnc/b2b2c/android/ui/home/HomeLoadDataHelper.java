package net.shopnc.b2b2c.android.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.bean.ApiSpecialItem;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.ui.good.SearchGoodsShowActivity;
import net.shopnc.b2b2c.android.ui.good.SearchStoresShowActivity;
import net.shopnc.b2b2c.android.ui.group.GroupListActivity;
import net.shopnc.b2b2c.android.ui.mine.SettingActivity;
import net.shopnc.b2b2c.android.ui.points.PointsCenterActivity;
import net.shopnc.b2b2c.android.ui.points.SigninActivity;
import net.shopnc.b2b2c.android.ui.promotion.PromotionListActivity;
import net.shopnc.b2b2c.android.ui.showorders.ShowOrdersListActivity;
import net.shopnc.b2b2c.android.ui.store.StoreInfoFragmentActivity;
import net.shopnc.b2b2c.android.ui.trys.TryGoodShowActivity;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;

import java.util.List;

/**
 * 首页获取数据帮助类
 *
 * @author huting
 * @date 2016/5/4
 */
public class HomeLoadDataHelper {

    private static MyShopApplication application;

    public HomeLoadDataHelper(Context context) {
        application = (MyShopApplication) context.getApplicationContext();
    }


    /**
     * 判断显示模块
     *
     * @param homeHelper
     * @param isSpecial  是否从专题页面跳转过来
     */
    public static void indgeJump(HomeShowViewHelper homeHelper, List<ApiSpecialItem> apiSpecialItemList, boolean isSpecial, RelativeLayout cycleView) {
        int count = 0;
        for (ApiSpecialItem apiSpecialItem : apiSpecialItemList) {
            //如果Android端不显示则跳过
            if(apiSpecialItem.getAndroid() == Constants.NO) {
                continue;
            }

            //显示首页布局
            if (apiSpecialItem.getItemType().equals(Constants.AD)) {
                count++;

                if (apiSpecialItem.getItemData() != null && !apiSpecialItem.getItemData().equals("[]") && !apiSpecialItem.getItemData().equals("")) {
                    homeHelper.showAD(apiSpecialItem);
                } else {
                    if (!isSpecial) {
                        cycleView.setVisibility(View.VISIBLE);
                        homeHelper.showAD();
                    } else {
                        cycleView.setVisibility(View.GONE);
                    }
                }
            } else if (apiSpecialItem.getItemType().equals(Constants.TEXT)) {//显示滚动文字
                homeHelper.showText(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.GOODS)) {//显示商品
                homeHelper.showGoods(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME1)) {//显示home1
                homeHelper.showHome1(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME2)) {//显示home2
                homeHelper.showHome2(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME3)) {//显示home3
                homeHelper.showHome3(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME4)) {//显示home4
                homeHelper.showHome4(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME5)) {//显示home5
                homeHelper.showHome5(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME6)) {//显示home6
                homeHelper.showHome6(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME7)) {//显示home7
                homeHelper.showHome7(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME8)) {//显示home8
                homeHelper.showHome8(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME9)) {//显示home9
                homeHelper.showHome9(apiSpecialItem);
            } else if (apiSpecialItem.getItemType().equals(Constants.HOME10)) {//显示home10
                homeHelper.showHome10(apiSpecialItem);
            }
        }

        if (count == 0) {//不包含轮播
            if (!isSpecial) {
                cycleView.setVisibility(View.VISIBLE);
                homeHelper.showAD();
            } else {
                cycleView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 首页判断跳转界面
     *
     * @param type
     * @param data 专题商品店铺  当其data为空时，不跳转
     */
    public static void doClick(final Context context, final String type, final String data) {

        if (type.equals("keyword") || type.equals("storeKeyword")) {//跳转到搜索商品页

            Intent intent = new Intent(context, SearchGoodsShowActivity.class);
            intent.putExtra("keyword", data);
            context.startActivity(intent);

        } else if (type.equals("special")) {//跳转到专题页
            if (Common.isEmpty(data)) {
                return;
            }

            Intent intent = new Intent(context, SpecialActivity.class);
            intent.putExtra("url", ConstantUrl.URL_INDEX_SPECIAL + "?specialId=" + data);
            context.startActivity(intent);

        } else if (type.equals("goods")) {//跳转到商品详情页
            if (Common.isEmpty(data)) {
                return;
            }

            Intent intent = new Intent(context, GoodDetailsActivity.class);
            try {
                intent.putExtra("commonId", Integer.valueOf(data));
            } catch (NumberFormatException e) {
                TToast.showShort(context, "参数错误！");
                return;
            }
            context.startActivity(intent);

        } else if (type.equals("setting")) {//跳转到设置
            if (ShopHelper.isLogin(context)) {
                Intent intent = new Intent(context, SettingActivity.class);
                context.startActivity(intent);
            }
        } else if (type.equals("store")) {//跳转到店铺页
            if (Common.isEmpty(data)) {
                return;
            }

            Intent intent = new Intent(context, StoreInfoFragmentActivity.class);
            intent.putExtra("storeId", Integer.valueOf(data));
            context.startActivity(intent);

        } else if (type.equals("home")) {//跳到首页
            Intent intent = new Intent(context, MainFragmentManager.class);

            application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
            context.startActivity(intent);

        } else if (type.equals("category")) {//跳转到分类页
            Intent intent = new Intent(context, MainFragmentManager.class);
            application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_ClASSIFY_URL));
            context.startActivity(intent);

        } else if (type.equals("cart")) {//跳转到购物车
            Intent intent = new Intent(context, MainFragmentManager.class);
            application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_CART_URL));
            context.startActivity(intent);

        } else if (type.equals("my")) {//跳转到我的商城
            Intent intent = new Intent(context, MainFragmentManager.class);
            application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_MINE_URL));
            context.startActivity(intent);

        } else if ("storeSpecial".equals(type)) {//跳转到店铺专题

            if (Common.isEmpty(data)) {
                return;
            }

            Intent intent = new Intent(context, SpecialActivity.class);
            intent.putExtra("url", ConstantUrl.URL_API + "/store/special?specialId=" + data);
            context.startActivity(intent);
        } else if (type.equals("distPage")) {//跳转到推广
            Intent intent = new Intent(context, PromotionListActivity.class);
            context.startActivity(intent);
        } else if (type.equals("trys")) {//跳转到试用
            Intent intent = new Intent(context, TryGoodShowActivity.class);
            context.startActivity(intent);
        } else if (type.equals("group")) {//跳转到拼团
            Intent intent = new Intent(context, GroupListActivity.class);
            context.startActivity(intent);
        } else if (type.equals("showOrders")) {//跳转到晒宝
            Intent intent = new Intent(context, ShowOrdersListActivity.class);
            context.startActivity(intent);
        } else if (type.equals("signin")) {//跳转签到
            if (ShopHelper.isLogin(context)) {
                Intent intent = new Intent(context, SigninActivity.class);
                context.startActivity(intent);
            }
        } else if (type.equals("pointsCenter")) {//跳转到积分中心
            Intent intent = new Intent(context, PointsCenterActivity.class);
            context.startActivity(intent);
        } else if (type.equals("storeList")) {//跳转到店铺列表
            Intent intent = new Intent(context, SearchStoresShowActivity.class);
            intent.putExtra("keyword", "");
            context.startActivity(intent);
        } else {
            LogHelper.i("首页操作", "没有操作类型");
        }
    }
}
