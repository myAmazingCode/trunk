package net.shopnc.b2b2c.android.common;

import net.shopnc.b2b2c.android.bean.CartListBean;

import java.util.ArrayList;

/**
 * @author lulei
 *         Created 2017/5/3 14:15
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 全局变量
 */
public class Global {
    public static ArrayList<CartListBean> cartListBeanArrayList = new ArrayList<>();//非登录状态时购物车列表
    public static String cartListString = "";//非登录状态时购物车json数据
    public static int cartnum = 0;//非登录状态时购物车列表商品数量
    public static String cartlist = "";//非登录状态时购物车列表商品id和数量统计


}
