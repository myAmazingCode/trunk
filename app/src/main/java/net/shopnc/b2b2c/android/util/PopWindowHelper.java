package net.shopnc.b2b2c.android.util;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.PopupWindow;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.Common;

/**
 * Created by snm on 2016/8/18.
 */
public class PopWindowHelper {

    /**
     * 初始化popupwindow
     */
    public static PopupWindow initPopupWindow(final Context context, View mPopupWindowView){
        PopupWindow popupWindow ;

        popupWindow = new PopupWindow(mPopupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT ,true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.update();
        popupWindow.setAnimationStyle(R.anim.popup_window_enter);
        popupWindow.setAnimationStyle(R.anim.popup_window_exit);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Common.backgroundAlpha(context,1f);
            }
        });
        return popupWindow;
    }
}
