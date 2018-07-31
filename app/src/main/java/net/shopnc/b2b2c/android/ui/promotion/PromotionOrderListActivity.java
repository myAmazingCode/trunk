package net.shopnc.b2b2c.android.ui.promotion;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ScrollView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.PromotionOrderListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.PromotionOrder;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.xrefresh.XRefreshView;
import net.shopnc.b2b2c.android.xrefresh.XScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class PromotionOrderListActivity extends BaseActivity {

    @Bind(R.id.btnOrderAll)
    RadioButton mBtnOrderAll;
    @Bind(R.id.btnOrderOngoing)
    RadioButton mBtnOrderOngoing;
    @Bind(R.id.btnOrderExchange)
    RadioButton mBtnOrderExchange;
    @Bind(R.id.btnOrderCanceled)
    RadioButton mBtnOrderCanceled;
    @Bind(R.id.btnOrderPayed)
    RadioButton mBtnOrderPayed;
    @Bind(R.id.listView)
    MyListView mListView;
    @Bind(R.id.xrefreshview)
    XRefreshView mXrefreshview;
    @Bind(R.id.xscrollview)
    XScrollView mXscrollview;
    @Bind(R.id.emptyView)
    View emptyView;

    //订单类型，默认全部
    private String ordersState = "-1";
    //当前页，默认为1
    private int curpage = 1;
    private int mTotalPage;
    private List<PromotionOrder.DistributorOrdersListBean> mDatas = new ArrayList<PromotionOrder.DistributorOrdersListBean>();
    private PromotionOrderListAdapter mAdapter;
    private RadioButton lastRb;//上次选中的按钮

    public static final String TAG = "PromotionList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("推广订单列表");

        mBtnOrderAll.setChecked(true);

        mXrefreshview();

        mAdapter = new PromotionOrderListAdapter(this, mDatas, R.layout.promotion_order_list_item);
        mListView.setAdapter(mAdapter);
        mListView.setDividerHeight(0);

        lastRb = (RadioButton) findViewById(R.id.btnOrderAll);
    }

    private void mXrefreshview() {

        mXrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                mDatas.clear();
                mAdapter.notifyDataSetChanged();
                curpage = 1;
                loadData();
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                if (curpage < mTotalPage) {
                    curpage += 1;
                    loadData();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    /**
     * 加载订单数据
     */
    private void loadData() {
        String url = ConstantUrl.URL_API + "/member/distributor/orders/list";
        Map<String, String> map = new HashMap<>();
        map.put("token", application.getToken());
        map.put("ordersState", ordersState);
        map.put("page", String.valueOf(curpage));
        Log.d(TAG, "loadData: curpage = " + curpage);

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                if(mXrefreshview == null) {
                    return;
                }

                mXrefreshview.stopRefresh();
                mXrefreshview.endLoadMore();

                PromotionOrder promotionOrder = JsonUtil.toBean(data, PromotionOrder.class);
                PromotionOrder.PageEntityBean pageEntity = promotionOrder.getPageEntity();

                //分页信息
                mTotalPage = pageEntity.getTotalPage();
                if (pageEntity.isHasMore()) {
                    mXrefreshview.setPullLoadEnable(true);
                } else {
                    mXrefreshview.setPullLoadEnable(false);
                }

                //订单数据
                List<PromotionOrder.DistributorOrdersListBean> ordersList = promotionOrder.getDistributorOrdersList();

                if (ordersList != null && ordersList.size() != 0) {
                    mDatas.addAll(ordersList);
                    mAdapter.notifyDataSetChanged();
                }

                //数据是否为空
                if (mDatas.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                    mXrefreshview.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    mXrefreshview.setVisibility(View.VISIBLE);
                }

            }
        }, map);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_promotion_order_list);
    }

    @OnClick({R.id.btnOrderAll, R.id.btnOrderOngoing, R.id.btnOrderExchange, R.id.btnOrderCanceled, R.id.btnOrderPayed})
    public void onViewClicked(View view) {
        if (lastRb == view) {
            return;
        }

        switch (view.getId()) {
            case R.id.btnOrderAll:
                ordersState = "-1";
                break;
            case R.id.btnOrderOngoing:
                ordersState = "10";
                break;
            case R.id.btnOrderExchange:
                ordersState = "4";
                break;
            case R.id.btnOrderCanceled:
                ordersState = "0";
                break;
            case R.id.btnOrderPayed:
                ordersState = "5";
                break;
        }
//        Log.d(TAG, "onViewClicked: ordersState = " + ordersState);
        mDatas.clear();
        mAdapter.notifyDataSetChanged();
        curpage = 1;
        loadData();
        mXscrollview.post(new Runnable() {
            @Override
            public void run() {
                mXscrollview.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        lastRb = (RadioButton) view;
    }
}
