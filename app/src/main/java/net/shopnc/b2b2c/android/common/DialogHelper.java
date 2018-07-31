package net.shopnc.b2b2c.android.common;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.ui.home.SearchActivity;
import net.shopnc.b2b2c.android.ui.mine.IMNewListActivity;
import net.shopnc.b2b2c.android.ui.mine.LoginActivity;

/**
 * Created by snm on 2016/5/27.
 */
public class DialogHelper {


    /**
     * 初始化popupwindow
     */
    public static PopupWindow initPopupWindow(Context context){
        PopupWindow popupWindow ;
        View mPopupWindowView = initPopupWindowView(context);
        popupWindow = new PopupWindow(mPopupWindowView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.nc_icon_setting));
        popupWindow.update();
        popupWindow.setAnimationStyle(R.anim.popup_window_enter);
        popupWindow.setAnimationStyle(R.anim.popup_window_exit);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        return popupWindow;
    }


    /**
     * 初始化popupwindowView
     */
    public static View initPopupWindowView(Context context){
        View mPopupWindowView = LayoutInflater.from(context).inflate(R.layout.menu_store, null);
        TextView textview_home = mPopupWindowView.findViewById(R.id.textview_home);
        textview_home.setOnClickListener(new SetOnclickListener(context,R.id.textview_home));

        TextView textview_search = mPopupWindowView.findViewById(R.id.textview_search);
        textview_search.setOnClickListener(new SetOnclickListener(context,R.id.textview_search));

        TextView textview_cart = mPopupWindowView.findViewById(R.id.textview_cart);
        textview_cart.setOnClickListener(new SetOnclickListener(context,R.id.textview_cart));
        TextView textview_mime = mPopupWindowView.findViewById(R.id.textview_mime);
        textview_mime.setOnClickListener(new SetOnclickListener(context,R.id.textview_mime));
        TextView textview_msg = mPopupWindowView.findViewById(R.id.textview_msg);
        textview_msg.setOnClickListener(new SetOnclickListener(context,R.id.textview_msg));
        return mPopupWindowView;
    }

    public static class SetOnclickListener implements View.OnClickListener{
        public Context mContent;
        public int id;
        public SetOnclickListener(Context context,int clickId){
            this.mContent = context;
            this.id = clickId;
        }
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (id){
                case R.id.textview_home:
                    intent = new Intent(mContent, MainFragmentManager.class);
                    MyShopApplication.getInstance().sendBroadcast(new Intent(Constants.SHOW_HOME_URL));
                    mContent.startActivity(intent);
                    break;
                case R.id.textview_mime:
                    intent = new Intent(mContent, MainFragmentManager.class);
                    MyShopApplication.getInstance().sendBroadcast(new Intent(Constants.SHOW_Mine_URL));
                    mContent.startActivity(intent);
                    break;
                case R.id.textview_cart:
                    intent = new Intent(mContent, MainFragmentManager.class);
                    MyShopApplication.getInstance().sendBroadcast(new Intent(Constants.SHOW_CART_URL));
                    mContent.startActivity(intent);
//                    popupWindow.dismiss();
                    break;
                case R.id.textview_msg:
                    if (ShopHelper.isLogin(mContent, MyShopApplication.getInstance().getLoginKey())) {
//                        startActivity(new Intent(GoodsDetailsActivity.this, IMFriendsListActivity.class));
                        mContent.startActivity(new Intent(mContent, IMNewListActivity.class));

                    }else{
                        mContent.startActivity(new Intent(mContent, LoginActivity.class));
                    }
                    break;
                case R.id.textview_search:
                    mContent.startActivity(new Intent(mContent, SearchActivity.class));
                    break;
            }
        }
    }
    public static PopupWindow getAllPopupWindow(final Context context,View view) {
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

//        new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, (int) (Utils.getScreenHeight(context) * (2.0 / 5)));
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setAnimationStyle(R.style.dialogAnimation);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                setAlpha(context,1.0f);
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }

    public static PopupWindow getPopupWindow(final Context context,View view) {
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, (int) (Utils.getScreenHeight(context) * (2.0 / 5)));
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setAnimationStyle(R.style.dialogAnimation);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                setAlpha(context,1.0f);
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }

    public static void setAlpha(Context context,float alpha){
        WindowManager.LayoutParams layoutParams = ((Activity)context).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((Activity)context).getWindow().setAttributes(layoutParams);
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = alpha; //0.0-1.0
//        context.getWindow().setAttributes(lp);
    }

}
