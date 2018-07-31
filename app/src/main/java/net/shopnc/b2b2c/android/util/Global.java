package net.shopnc.b2b2c.android.util;

import net.shopnc.b2b2c.android.bean.TrySortBean;

/**
 * @author lulei
 *         Created 2017/5/15 15:28
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * 全局变量
 */
public class Global {
    public static boolean isCart = false;//购物车提示
    public static boolean isUnreadMessage = false;//未读站内消息提示
    public static final int TABLEFT = 0;
    public static final int TABRIGHT = 1;
    public static final int TABMIDDLE = 2;

    public static TrySortBean trySortBean;//试用选择分类

    public static final int OPERATIONFLAG_WITHDRAWDEPOSIT = 300;//手机安全验证操作标记

}
