package net.shopnc.b2b2c.android.custom.dialog;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.TrySortBean;
import net.shopnc.b2b2c.android.common.PopupWindowHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.ui.trys.TryGoodShowFragment;
import net.shopnc.b2b2c.android.util.Global;

import java.util.List;

/**
 * @author lulei
 *         Created 2017/5/22 15:01
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 导航栏dialog
 */
public class HorizontalNavigationDialog {

    private Context context;
    public PopupWindow popupWindow;
    private RecyclerView rvSort;
    private RRecyclerAdapter<TrySortBean> adapter;
    private TrySortBean preSortBean;
    private HorizontalNavigationDialog.OnItemClick onItemClick;

    public HorizontalNavigationDialog(Context context) {
        this.context = context;
        if (popupWindow == null) {
            initPopupWindowView();
        } else {
            popupWindow.dismiss();
        }
    }

    public void setOnItemClick(HorizontalNavigationDialog.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void initPopupWindowView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_horizontal_navigation, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
//        popupWindow.setAnimationStyle(R.style.AnimationFade);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                return false;
            }
        });
        rvSort = view.findViewById(R.id.rvSort);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        rvSort.setLayoutManager(layoutManager);
        adapter = new RRecyclerAdapter<TrySortBean>(context, R.layout.dialog_horizontal_navigation_recycleview_item) {
            @Override
            public void convert(RecyclerHolder holder, final TrySortBean trySortBean) {
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText(trySortBean.getCategoryName());
                if (trySortBean.getCategoryId() == Global.trySortBean.getCategoryId()) {
                    trySortBean.setSelected(true);
                } else {
                    trySortBean.setSelected(false);
                }

                tvName.setSelected(trySortBean.isSelected());

                if (trySortBean.isSelected()) {
                    tvName.setBackgroundResource(R.drawable.btn_red_oval);
                } else {
                    tvName.setBackgroundResource(R.drawable.btn_gray_oval);
                }

                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trySortBean.setSelected(true);
                        preSortBean.setSelected(false);
                        preSortBean = trySortBean;
                        if (onItemClick != null) {
                            onItemClick.onItemClick(trySortBean);
                        }
                        notifyDataSetChanged();
                        popupWindow.dismiss();
                        Global.trySortBean = trySortBean;
                    }
                });
            }
        };
        rvSort.setAdapter(adapter);
        LinearLayout llDialog = view.findViewById(R.id.llDialog);
        llDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void setDatas(List<TrySortBean> trySortBeanList) {
        adapter.setDatas(trySortBeanList);
        preSortBean = trySortBeanList.get(0);
        adapter.notifyDataSetChanged();
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    public void show(Activity context, View view) {
        PopupWindowHelper.showAsDropDown(context, popupWindow, view);
    }

    public interface OnItemClick {
        void onItemClick(TrySortBean trySortBean);
    }

    public interface RefreshView {

    }

}
