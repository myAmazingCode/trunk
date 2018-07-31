package net.shopnc.b2b2c.android.common;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 *
 * 弹出窗口辅助类
 *
 * @author dqw
 * Created 2017/5/10 14:29
 */
public class PopupWindowHelper {

    /**
     * Android7.0后showAsDropDown异常兼容处理
     * 如果PopupWindow宽高都是MATCH_PARENT会全屏显示而不是下拉显示
     * @param context
     * @param pw
     * @param view
     */
    public static void showAsDropDown(Activity context, PopupWindow pw, View view) {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            int[] position = new int[2];
            view.getLocationInWindow(position);
            pw.showAtLocation(context.getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, position[1] + view.getHeight());
        } else {
            pw.showAsDropDown(view);
        }
    }
}
