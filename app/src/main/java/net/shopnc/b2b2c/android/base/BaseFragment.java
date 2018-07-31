package net.shopnc.b2b2c.android.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.custom.dialog.CustomProgressDialog;
import net.shopnc.b2b2c.android.event.LogoutEvent;
import net.shopnc.b2b2c.android.event.SimpleCardEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseFragment extends Fragment {

    protected MyShopApplication application;
    protected Context context;
    protected Gson gson;
    private CustomProgressDialog dialogWait = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = MyShopApplication.getInstance();
        context = this.getActivity();
        gson = new Gson();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 网络请求dialog
     *
     * @param context
     * @param text
     */
    protected void showDialog(Context context, String text) {
        if (dialogWait != null && dialogWait.isShowing()) {
            return;
        }
        dialogWait = CustomProgressDialog.createDialog(context);
        dialogWait.setMessage(text);// 暂时显示文字
        dialogWait.setCanceledOnTouchOutside(false);
        dialogWait.setCancelable(true);
        dialogWait.show();
    }

    /**
     * 关闭dialog
     */
    protected void dissMissDialog() {
        if (dialogWait != null && dialogWait.isShowing()) {
            dialogWait.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {

    }

}
