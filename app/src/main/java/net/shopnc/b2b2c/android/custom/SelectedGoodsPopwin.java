package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.ui.good.GoodBusBean;
import net.shopnc.b2b2c.android.ui.good.PreGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 购物清单页
 * 1.可使用dialog  为更好控制显示位置，故选择popwin
 * 2.ButterKnife绑定没效果，故使用原始方法
 * @Author qyf
 *
 * Created 2016/4/26 16:36.
 */
public class SelectedGoodsPopwin extends PopupWindow {

    private TextView btnCancle;
    private TextView btnDelete;
    private RecyclerView rvSelectedGoods;
    private PopupWindow popupWindow;

    private Context context;
    private HashMap<Integer, PreGoods> preGoodsHashMap;
    private List<PreGoods> preGoodsList;
    private List<Integer> goodsIds;
    private String unitName;

    private RRecyclerAdapter<PreGoods> selectedGoodsAdapter;

    public SelectedGoodsPopwin(Context context, HashMap<Integer, PreGoods> preGoodsHashMap,String unitName) {
        this.context = context;
        this.preGoodsHashMap = preGoodsHashMap;
        this.unitName=unitName;
        if (popupWindow == null) {
            initmPopupWindowView();
        } else {
            popupWindow.dismiss();
        }
    }

    public void initmPopupWindowView() {
        View customView = LayoutInflater.from(context).inflate(R.layout.follow_selectedgoods_pickerview, null);
        popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        btnCancle = customView.findViewById(R.id.btnCancle);
        btnDelete = customView.findViewById(R.id.btnDelete);
        rvSelectedGoods = customView.findViewById(R.id.rvSelectedGoods);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.CommonDialog);
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    EventBus.getDefault().unregister(this);
                    popupWindow = null;
                }
                return false;
            }
        });

        initView();
        onClick();
    }

    private void initView() {
        preGoodsList = new ArrayList<>();
        translateMapValueToList();
        goodsIds = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvSelectedGoods.setLayoutManager(linearLayoutManager);
//        rvSelectedGoods.addItemDecoration(new ItemDecorationDivider(context, R.drawable.item_divider, LinearLayoutManager.VERTICAL));
        selectedGoodsAdapter = new RRecyclerAdapter<PreGoods>(context, R.layout.recyclerview_goodsdetails_selectedgoods_item) {
            @Override
            public void convert(final RecyclerHolder holder, final PreGoods preGoods) {
                holder.setText(R.id.tvGoodsCount,preGoods.getCount()+unitName)
                        .setText(R.id.tvGoodsSpec, preGoods.getSpecName());

                holder.getView(R.id.btnSelectSpu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (goodsIds.contains(preGoods.getGoodsId())) {
                            holder.getView(R.id.btnSelectSpu).setSelected(false);
                            goodsIds.remove(new Integer(preGoods.getGoodsId()));
                        } else {
                            holder.getView(R.id.btnSelectSpu).setSelected(true);
                            goodsIds.add(preGoods.getGoodsId());
                        }

                    }
                });
            }
        };
        selectedGoodsAdapter.setDatas(preGoodsList);
        rvSelectedGoods.setAdapter(selectedGoodsAdapter);
    }

    private void translateMapValueToList() {
        for (PreGoods preGoods : preGoodsHashMap.values()) {
            preGoodsList.add(preGoods);
        }
    }

    private void onClick() {
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodDelete, goodsIds));
                popupWindow.dismiss();
            }
        });
    }


    //location[1]:控件的全屏的Y位置
    //show显示的Y是相对于dialog的Y值
    public void show(View view) {
//        int[] location = new int[2];
//        view.getLocationOnScreen(location);
//        popupWindow.showAtLocation(view,Gravity.NO_GRAVITY,0, location[1]-DensityUtil.dip2px(context,300));
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
    }

}
