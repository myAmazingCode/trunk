package net.shopnc.b2b2c.android.custom.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;
import net.shopnc.b2b2c.android.util.Global;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 下拉的选择框
 * @Author qyf
 * <p>
 * Created 2016/6/29 17:20.
 */
public class ChooseDialog extends PopupWindow {

    private List<ShowItem> datas;
    private Context context;
    private MyShopApplication application;
    private ListView llChoosens;
    private PopupWindow popupWindow;

    public ChooseDialog(Context context, MyShopApplication application, View view) {
        this.context = context;
        this.application = application;
        this.datas = new ArrayList<>();
        datas.add(new ShowItem(R.drawable.home_w, "首页", false));
        datas.add(new ShowItem(R.drawable.search_w_meitu_4, "分类", false));
        datas.add(new ShowItem(R.drawable.cart_w_meitu_2, "购物车", Global.isCart));
        datas.add(new ShowItem(R.drawable.member_w, "我的", false));
        if (popupWindow == null) {
            initmPopupWindowView();
            popupWindow.showAsDropDown(view, 2, 0);
        } else {
            popupWindow.dismiss();
        }
    }

    public void initmPopupWindowView() {
        View customView = LayoutInflater.from(context).inflate(R.layout.choose_dialog, null);
        customView.getBackground().setAlpha(245);
        popupWindow = new PopupWindow(customView, 400, 500);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
//        popupWindow.setAnimationStyle(R.style.AnimationFade);
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

        llChoosens = customView.findViewById(R.id.llChoosens);

        llChoosens.setAdapter(new CommonAdapter<ShowItem>(context, datas, R.layout.follow_item_dialog) {
            @Override
            public void convert(ViewHolder holder, final ShowItem item) {
                holder.setImage(R.id.ivFlag, item.getImgUrl())
                        .setText(R.id.tvText, item.getItemText())
                        .setVisibleIn(R.id.vhint, item.isHintShow());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOnClick(item.getImgUrl());
                    }
                });
            }
        });

    }

    private void setOnClick(int id) {
        Intent intent = new Intent(context, MainFragmentManager.class);
        switch (id) {
            case R.drawable.home_w:
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
                break;
            case R.drawable.search_w_meitu_4:
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_ClASSIFY_URL));
                break;
            case R.drawable.cart_w_meitu_2:
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_CART_URL));
                break;
            case R.drawable.member_w:
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_MINE_URL));
                break;
        }
        context.startActivity(intent);
        popupWindow.dismiss();
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    private class ShowItem {
        private int imgUrl;
        private String itemText;
        private boolean isHintShow;

        public ShowItem(int imgUrl, String itemText, boolean isHintShow) {
            this.imgUrl = imgUrl;
            this.itemText = itemText;
            this.isHintShow = isHintShow;
        }

        public int getImgUrl() {
            return imgUrl;
        }

        public String getItemText() {
            return itemText;
        }

        public boolean isHintShow() {
            return isHintShow;
        }
    }

}
