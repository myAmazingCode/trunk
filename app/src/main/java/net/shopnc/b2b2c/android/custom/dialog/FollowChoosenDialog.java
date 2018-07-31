package net.shopnc.b2b2c.android.custom.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import java.util.List;

/**
 * Created by qyf on 2016/3/14.
 */
public class FollowChoosenDialog extends PopupWindow {
    private List<String> datas;
    private Context context;
    private ListView llChoosens;
    private PopupWindow popupWindow;


    public FollowChoosenDialog(Context context,View view,List<String> datas) {
        this.context=context;
        this.datas=datas;
        if(popupWindow==null){
            initmPopupWindowView();
            popupWindow.showAsDropDown(view);
        }else{
            popupWindow.dismiss();
        }
    }

    public void initmPopupWindowView() {
        View customView = LayoutInflater.from(context).inflate(R.layout.follow_bottom_dialog, null);
        popupWindow = new PopupWindow(customView, 300,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.AnimationFade);
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

        llChoosens.setAdapter(new CommonAdapter<String>(context,datas,R.layout.follow_item_dialog) {
            @Override
            public void convert(ViewHolder holder, String s) {
                if (s.equals(datas.get(0))){
                    holder.getConvertView().setSelected(true);
                }
                holder.setText(R.id.tvToast,s);
            }
        });

    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        llChoosens.setOnItemClickListener(listener);
    }

}
