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
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.ui.mine.FavGoodAndStoreActivity;
import net.shopnc.b2b2c.android.ui.mine.MineFootPrintActivity;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/7/18 11:55.
 */
public class GoodDetailMoreDialog extends PopupWindow {
    private List<ShowItem> datas;
    private Context context;
    private MyShopApplication application;
    private ListView llChoosens;
    private PopupWindow popupWindow;


    public GoodDetailMoreDialog(Context context, MyShopApplication application, View view) {
        this.context = context;
        this.application = application;
        this.datas = new ArrayList<>();
        datas.add(new ShowItem(R.drawable.home_w, "首页"));
        datas.add(new ShowItem(R.drawable.search_w_meitu_4, "分类"));
        datas.add(new ShowItem(R.drawable.nc_icon_fav_goods, "我的关注"));
        datas.add(new ShowItem(R.drawable.nc_icon_goods_browse, "浏览记录"));
        if (popupWindow == null) {
            initmPopupWindowView();
            popupWindow.showAsDropDown(view);
        } else {
            popupWindow.dismiss();
        }
    }

    public void initmPopupWindowView() {
        View customView = LayoutInflater.from(context).inflate(R.layout.choose_dialog, null);
        popupWindow = new PopupWindow(customView, 400, LinearLayout.LayoutParams.WRAP_CONTENT);
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
                        .setText(R.id.tvText, item.getItemText());
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

        switch (id) {
            case R.drawable.home_w:
                Intent intent = new Intent(context, MainFragmentManager.class);
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
                context.startActivity(intent);
                popupWindow.dismiss();
                break;
            case R.drawable.search_w_meitu_4:
                Intent intent1 = new Intent(context, MainFragmentManager.class);
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_ClASSIFY_URL));
                context.startActivity(intent1);
                popupWindow.dismiss();
                break;
            case R.drawable.nc_icon_fav_goods:
                if (!ShopHelper.isLogin(context)) {
                    return;
                }
                Intent i = new Intent(context, FavGoodAndStoreActivity.class);
                i.putExtra("flag", "good");
                context.startActivity(i);
                break;
            case R.drawable.nc_icon_goods_browse:
                if (!ShopHelper.isLogin(context)) {
                    return;
                }
                Intent iFoot = new Intent(context, MineFootPrintActivity.class);
                context.startActivity(iFoot);
                break;
        }

    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }


    private class ShowItem {
        private int imgUrl;
        private String itemText;

        public ShowItem(int imgUrl, String itemText) {
            this.imgUrl = imgUrl;
            this.itemText = itemText;
        }

        public int getImgUrl() {
            return imgUrl;
        }

        public String getItemText() {
            return itemText;
        }
    }

}
