package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.NewGoodsDetailVoucherListViewAdapter;
import net.shopnc.b2b2c.android.bean.StoreVoucher;

import java.util.ArrayList;

/**
 * 商品详细页代金券选择弹出窗口
 *
 * @author dqw
 * @date 2015/6/30.
 */
public class NCNewStoreVoucherPopupWindow {
    Context context;

    View popupView;
    PopupWindow mPopupWindow;
    TextView tvStoreName;
    ListView lvStoreVoucher;
    NewGoodsDetailVoucherListViewAdapter adapter;

    public NCNewStoreVoucherPopupWindow(Context context) {
        this.context = context;

        popupView = LayoutInflater.from(context).inflate(R.layout.popupwindow_goods_detail_voucher_view, null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

        //半透明背景
        FrameLayout flBack = popupView.findViewById(R.id.flBack);
        flBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        tvStoreName = popupView.findViewById(R.id.tvStoreName);
        lvStoreVoucher = popupView.findViewById(R.id.lvStoreVoucher);
        adapter = new NewGoodsDetailVoucherListViewAdapter(context);
        lvStoreVoucher.setAdapter(adapter);

    }

    public void setStoreName(String storeName) {
        tvStoreName.setText(storeName);
    }

    public void setVoucherList(ArrayList<StoreVoucher> voucherList) {
        adapter.setVoucherLists(voucherList);
        adapter.notifyDataSetChanged();
    }

    public void showPopupWindow() {
        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
}
