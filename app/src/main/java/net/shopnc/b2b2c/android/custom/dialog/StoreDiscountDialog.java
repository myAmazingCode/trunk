package net.shopnc.b2b2c.android.custom.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;

/**
 * @Description 优惠券页面
 * @Author qyf
 *
 * Created 2016/5/5 10:41.
 */
public class StoreDiscountDialog extends PopupWindow {
    private Context context;
    private ListView llChoosens;
    private PopupWindow popupWindow;

    public StoreDiscountDialog(Context context,View view) {
        this.context=context;
        if(popupWindow==null){
            initmPopupWindowView();
            popupWindow.showAsDropDown(view);
        }else{
            popupWindow.dismiss();
        }
    }

    public void initmPopupWindowView() {
        View customView = LayoutInflater.from(context).inflate(R.layout.follow_bottom_dialog, null);
        popupWindow = new PopupWindow(customView, 400, 400);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.dialog);
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        llChoosens= customView.findViewById(R.id.llChoosens);

    }

    public void setAdapter(CommonAdapter adapter){
        llChoosens.setAdapter(adapter);
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }
}
