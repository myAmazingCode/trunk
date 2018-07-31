package net.shopnc.b2b2c.android.custom.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.shopnc.b2b2c.R;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/9/7 9:40.
 */
public class SearchSortDialog extends PopupWindow {
    private Context context;
    private PopupWindow popupWindow;
    private TextView tvShowSort;
    private View locationView;

    public SearchSortDialog(Context context, View locationView, TextView tvShowSort) {
        this.context = context;
        this.tvShowSort = tvShowSort;
        this.locationView=locationView;
        initmPopupWindowView();
    }

    public void initmPopupWindowView() {
        View customView = LayoutInflater.from(context).inflate(R.layout.search_sort_dialog, null);
        popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
//        popupWindow.setAnimationStyle(R.style.AnimationFade);
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                return false;
            }
        });
        TextView tvGood = customView.findViewById(R.id.tvGood);
        TextView tvStore = customView.findViewById(R.id.tvStore);
        tvGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowSort.setText("商品");
                popupWindow.dismiss();
            }
        });
        tvStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowSort.setText("店铺");
                popupWindow.dismiss();
            }
        });
    }

    public void show(){
        popupWindow.showAsDropDown(locationView);
    }
}
