package net.shopnc.b2b2c.android.ui.good;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.android.bean.BuyData;
import net.shopnc.b2b2c.android.bean.CartItemVo;
import net.shopnc.b2b2c.android.bean.GoodDetailVo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.buy.BuyStep1Activity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 商品详情页所需要的一些方法
 * @Author qyf
 * <p>
 * Created 2016/4/20 9:03.
 */
public class GoodHelper {
    /**
     * 根据商品数量判断商品处于哪个价格段并返回此单价
     *
     * @param goodDetail 商品详细信息
     * @param numAll     商品数量
     * @return 商品单价
     */
    public static String getSinglePrice(GoodDetailVo goodDetail, int numAll) {
        BigDecimal price = goodDetail.getAppPrice0();
        if (goodDetail.getGoodsModal() == 2) {
            if (numAll < goodDetail.getBatchNum0()) {
                price = goodDetail.getAppPrice0();
            } else if (goodDetail.getBatchNum0End() == 0) {
                price = goodDetail.getAppPrice0();
            } else if (goodDetail.getBatchNum1End() == 0) {
                if (numAll >= goodDetail.getBatchNum1()) {
                    price = goodDetail.getAppPrice1();
                } else {
                    price = goodDetail.getAppPrice0();
                }
            } else {
                if (numAll >= goodDetail.getBatchNum2()) {
                    price = goodDetail.getAppPrice2();
                } else if (numAll >= goodDetail.getBatchNum1()) {
                    price = goodDetail.getAppPrice1();
                } else {
                    price = goodDetail.getAppPrice0();
                }
            }
        }
        return ShopHelper.getPriceString(price);
    }

    public static String getSinglePrice(CartItemVo goodDetail, int numAll) {
        BigDecimal price = goodDetail.getAppPrice0();
        if (goodDetail.getGoodsModal() == 2) {
            if (numAll < goodDetail.getBatchNum0()) {
                price = goodDetail.getAppPrice0();
            } else if (goodDetail.getBatchNum0End() == 0) {
                price = goodDetail.getAppPrice0();
            } else if (goodDetail.getBatchNum1End() == 0) {
                if (numAll >= goodDetail.getBatchNum1()) {
                    price = goodDetail.getAppPrice1();
                } else {
                    price = goodDetail.getAppPrice0();
                }
            } else {
                if (numAll >= goodDetail.getBatchNum2()) {
                    price = goodDetail.getAppPrice2();
                } else if (numAll >= goodDetail.getBatchNum1()) {
                    price = goodDetail.getAppPrice1();
                } else {
                    price = goodDetail.getAppPrice0();
                }
            }
        }
        return ShopHelper.getPriceString(price);
    }

    public static String getOriginPrice(CartItemVo goodDetail, int numAll) {
        BigDecimal price = goodDetail.getGoodsPrice0();
        if (goodDetail.getGoodsModal() == 2) {
            if (numAll < goodDetail.getBatchNum0()) {
                price = goodDetail.getGoodsPrice0();
            } else if (goodDetail.getBatchNum0End() == 0) {
                price = goodDetail.getGoodsPrice0();
            } else if (goodDetail.getBatchNum1End() == 0) {
                if (numAll >= goodDetail.getBatchNum1()) {
                    price = goodDetail.getGoodsPrice1();
                } else {
                    price = goodDetail.getGoodsPrice0();
                }
            } else {
                if (numAll >= goodDetail.getBatchNum2()) {
                    price = goodDetail.getGoodsPrice2();
                } else if (numAll >= goodDetail.getBatchNum1()) {
                    price = goodDetail.getGoodsPrice1();
                } else {
                    price = goodDetail.getGoodsPrice0();
                }
            }
        }
        return ShopHelper.getPriceString(price);
    }

    /**
     * 商品详情弹出规格页面显示商品价格
     *
     * @param goodDetail 商品详情
     * @return XX
     */
    public static String getPriceStringAllShow(GoodDetailVo goodDetail, int allGoodNum) {
        String initPrice = "";
        if (goodDetail.getGoodsModal() == 1) {
            initPrice = ShopHelper.getPriceString(goodDetail.getAppPrice0());
        } else if (goodDetail.getGoodsModal() == 2) {
            initPrice = getSinglePrice(goodDetail, allGoodNum);
        }
        return initPrice;
    }

    /**
     * 添加至购物车
     *
     * @param context
     * @param application
     * @param buydatas
     */
    public static void addCart(final Context context, final MyShopApplication application, Collection<BuyData> buydatas) {
        String url = ConstantUrl.URL_ADD_CART;
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("buyData", new Gson().toJson(buydatas));
        params.put("clientType", "android");
        //未登录状态，
        if (Common.isEmpty(application.getToken())) {
            params.put("cartData", application.getCartData());
        }

        OkHttpUtil.postAsyn(context,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                application.setCartData(JsonUtil.toString(data, "cartData"));
                //更新购物车数量
                EventBus.getDefault().post(new GoodBusBean(GoodBusBean.REFRESH_CART_COUNT));
                TToast.showShort(context, "添加商品成功");
            }
        }, params);
    }


    /**
     * 购买，跳转至下订单页面
     *
     * @param context
     * @param token    用户token
     * @param buydatas 购买数据：[{"goodsId":66,"buyNum":"2"},{"goodsId":67,"buyNum":"2"},{"goodsId":68,"buyNum":"3"}]
     * @param isCart   购物车：1   |   商品详情：0
     * @param isGroup  是否团购商品
     * @param groupId  拼团编号
     * @param goId     开团编号
     */
    public static void buyNow(final Context context, String token, String buydatas, int isCart, final int isGroup, final int groupId, final int goId) {
        String url = ConstantUrl.URL_BUY_STEP1;
        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("buyData", buydatas);
        params.put("isCart", String.valueOf(isCart));
        params.put("isGroup", String.valueOf(isGroup));
//        params.put("isExistBundling", String.valueOf(1));
        params.put("clientType", "android");
        OkHttpUtil.postAsyn(context,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.d("buyNow data:" + data);
                Intent intent = new Intent(context, BuyStep1Activity.class);
                intent.putExtra(BuyStep1Activity.DATA, data);
                intent.putExtra(BuyStep1Activity.IS_GROUP, isGroup);
                intent.putExtra(BuyStep1Activity.GROUP_ID, groupId);
                intent.putExtra(BuyStep1Activity.GO_ID, goId);
                context.startActivity(intent);

            }
        }, params);

    }

    /**
     * 包含优惠套装的购买，跳转至下订单页面
     *
     * @param context
     * @param token           用户token
     * @param buydatas        购买数据：[{"goodsId":66,"buyNum":"2"},{"goodsId":67,"buyNum":"2"},{"goodsId":68,"buyNum":"3"}]
     * @param isCart          购物车：1   |   商品详情：0
     * @param isGroup         是否团购商品
     * @param groupId         拼团编号
     * @param goId            开团编号
     * @param isExistBundling 是否优惠套装1是0否
     */
    public static void buyNow(final Context context, String token, String buydatas, int isCart, final int isGroup, final int groupId, final int goId, final int isExistBundling) {
        String url = ConstantUrl.URL_BUY_STEP1;
        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("buyData", buydatas);
        params.put("isCart", String.valueOf(isCart));
        params.put("isGroup", String.valueOf(isGroup));
        params.put("isExistBundling", String.valueOf(isExistBundling));
        params.put("clientType", "android");
        OkHttpUtil.postAsyn(context,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.d("buyNow data:" + data);
                Intent intent = new Intent(context, BuyStep1Activity.class);
                intent.putExtra(BuyStep1Activity.DATA, data);
                intent.putExtra(BuyStep1Activity.IS_GROUP, isGroup);
                intent.putExtra(BuyStep1Activity.GROUP_ID, groupId);
                intent.putExtra(BuyStep1Activity.GO_ID, goId);
                //是否优惠套装
                intent.putExtra(BuyStep1Activity.ISEXISTBUNDLING, isExistBundling);
                context.startActivity(intent);

            }
        }, params);

    }

    /**
     * 登录后同步推广记录
     *
     * @param token
     * @param cookie
     */
    public static void distributorMerge(Context context,String token, String cookie) {
        LogHelper.d("Distribution merge");
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("cookie", cookie);//["198-3-1495705082"]
        OkHttpUtil.postAsyn(context,ConstantUrl.URL_DISTRIBUTION_MERGE_GOODS, new BeanCallback<String>() {


            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
            }

            @Override
            public void response(String data) {

            }

        }, params);
    }

    public static final String TAG = ShopHelper.class.getSimpleName();
}
