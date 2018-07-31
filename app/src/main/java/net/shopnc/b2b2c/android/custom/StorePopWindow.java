package net.shopnc.b2b2c.android.custom;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.ui.good.SearchGoodActivity;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;

/**
 * 店铺页面的下拉溢出菜单
 *
 * @author htuing
 * @date 2016/4/12
 */
public class StorePopWindow implements View.OnClickListener {

    private Context context;
    private static PopupWindow popupWindow;
    private View mPopupWindowView;
    private MyShopApplication application;
    private Intent intent;

    public StorePopWindow(Context context) {
        this.context = context;
        application = (MyShopApplication) context.getApplicationContext();
        mPopupWindowView = LayoutInflater.from(context).inflate(R.layout.menu_store, null);
        initPopupWindow(mPopupWindowView);
    }

    /**
     * 初始化popWindowView
     *
     * @param mPopupWindowView
     */
    private void initPopupWindowView(View mPopupWindowView) {
        TextView textview_home = mPopupWindowView.findViewById(R.id.textview_home);
        textview_home.setOnClickListener(this);

        TextView textview_search = mPopupWindowView.findViewById(R.id.textview_search);
        textview_search.setOnClickListener(this);

        TextView textview_cart = mPopupWindowView.findViewById(R.id.textview_cart);
        textview_cart.setOnClickListener(this);

        TextView textview_msg = mPopupWindowView.findViewById(R.id.textview_msg);
        textview_msg.setOnClickListener(this);
    }


    /**
     * 显示popupwindow
     */
    public static void showPopupWindow(ImageView imgMenu) {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(imgMenu, imgMenu.getLayoutParams().width / 2, 0);
        } else {
            popupWindow.dismiss();
        }
    }

    /**
     * 初始化popupwindow
     */
    private void initPopupWindow(View view) {
        initPopupWindowView(view);
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
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //菜单点击事件
            case R.id.textview_home:
                TToast.showShort(context, "首页");
                intent = new Intent(context, MainFragmentManager.class);
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
                context.startActivity(intent);
                popupWindow.dismiss();
                break;

            case R.id.textview_search:
                context.startActivity(new Intent(context, SearchGoodActivity.class));
                popupWindow.dismiss();
                break;

            case R.id.textview_cart:
                intent = new Intent(context, MainFragmentManager.class);
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_CART_URL));
                context.startActivity(intent);
                popupWindow.dismiss();
                break;

            case R.id.textview_msg:
                TToast.showShort(context, "消息");

              /*if (ShopHelper.isLogin(newStoreInFoActivity.this, myApplication.getLoginKey())) {
                  startActivity(new Intent(newStoreInFoActivity.this, IMFriendsListActivity.class));
              }else{
                  startActivity(new Intent(newStoreInFoActivity.this,LoginActivity.class));
              }
              popupWindow.dismiss();*/
                break;
        }
    }
}
