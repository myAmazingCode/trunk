package net.shopnc.b2b2c.android.ui.order;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.OrderListAdapter;
import net.shopnc.b2b2c.android.base.BaseMoreMenuActivity;
import net.shopnc.b2b2c.android.bean.OrdersPayVo;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.common.Common;
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

import butterknife.Bind;
import butterknife.OnClick;

public class OrderListActivity extends BaseMoreMenuActivity {

    public static String STATE = "state";
    @Bind(R.id.etOrderSearch)
    EditText etOrderSearch;
    @Bind(R.id.btnOrderAll)
    RadioButton btnOrderAll;
    @Bind(R.id.btnOrderNew)
    RadioButton btnOrderNew;
    @Bind(R.id.btnOrderPay)
    RadioButton btnOrderPay;
    @Bind(R.id.btnOrderSend)
    RadioButton btnOrderSend;
    @Bind(R.id.btnOrderNoeval)
    RadioButton btnOrderNoeval;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.layoutSearch)
    RelativeLayout layoutSearch;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;
    @Bind(R.id.btnChoose)
    Button btnChoose;
    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.lvOrderList)
    MyListView lvOrderList;
    @Bind(R.id.xscrollview)
    XScrollView xscrollview;
    @Bind(R.id.xrefreshview)
    XRefreshView xrefreshview;
    @Bind(R.id.btnOrderSearch)
    Button btnOrderSearch;
    @Bind(R.id.llOrderSearch)
    LinearLayout llOrderSearch;
    @Bind(R.id.rgOrder)
    RadioGroup rgOrder;

    private String ordersState;
    private int page = 1;
    private int pageSize = 5;
    private String keyword;

    private PageEntity pageEntity;
    private List<OrdersPayVo> ordersPayVoList = new ArrayList<>();
    private OrderListAdapter adapter;

    private boolean isllSearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        ordersState = getIntent().getStringExtra(OrderListActivity.STATE);
        setItemChecked();
        setCommonHeader("商品订单");
        setBtnMoreVisible();
//        setLayoutEmpty(R.drawable.order_w, "您还没有相关的订单", "可以去看看哪些想要买的", "随便逛逛", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, MainFragmentManager.class);
//                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
//                context.startActivity(intent);
//                finish();
//            }
//        });
        setLayoutEmpty(R.drawable.no_data_f, "亲，还没有相关订单哦~", "");

        adapter = new OrderListAdapter(context, application);
        lvOrderList.setAdapter(adapter);
        initView();
        initRecyclerView(xrefreshview);
        requestData(xrefreshview, false);
        setEtSearchWatcher();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        requestData(xrefreshview);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ivSearch.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView(final XRefreshView customView) {
        customView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                requestData(customView, true);
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (page < pageEntity.getTotalPage()) {
                            page = page + 1;
                            requestData(customView, false);
                        }
                    }
                }, 500);
            }
        });
    }

    private void requestData(final XRefreshView customView, boolean isRefresh) {

        if (isRefresh) {

        } else {
            if (page == 1) {
                showDialog(context, "");
            }
        }

        String url = ConstantUrl.URL_ORDER_LIST;
        final HashMap<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("pageSize", pageSize + "");
        params.put("token", application.getToken());
        params.put("ordersState", ordersState);
        if (Common.isNotEmpty(keyword)) {
            params.put("keyword", keyword);
        }

        OkHttpUtil.postAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                dissMissDialog();
                List<OrdersPayVo> ordersPayVos = JsonUtil.toBean(data, "ordersPayVoList", new TypeToken<List<OrdersPayVo>>() {
                }.getType());
                pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (customView == null) {
                    return;
                }

                if (pageEntity != null && !pageEntity.isHasMore()) {
                    customView.setPullLoadEnable(false);
                } else {
                    customView.setPullLoadEnable(true);
                }

                if (ordersPayVos != null) {
//                    if (page == 1) {
//                        ordersPayVoList = ordersPayVos;
//                    } else {
//                        ordersPayVoList.addAll(ordersPayVos);
//                    }
                    if (page == 1) {
                        ordersPayVoList.clear();
                    }
                    ordersPayVoList.addAll(ordersPayVos);
                }

                if (null != ordersPayVoList && ordersPayVoList.size() > 0) {
                    hideLayoutEmpty();
                    adapter.setmDatas(ordersPayVoList);
                    adapter.notifyDataSetChanged();
                } else {
                    showLayoutEmpty();
                }

                customView.stopRefresh();
                customView.endLoadMore();
            }
        }, params);

    }


    @OnClick({R.id.btnOrderAll, R.id.btnOrderNew, R.id.btnOrderPay, R.id.btnOrderSend, R.id.btnOrderNoeval})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOrderAll:
                ordersState = "";
                break;
            case R.id.btnOrderNew:
                ordersState = "new";
                break;
            case R.id.btnOrderPay:
                ordersState = "pay";
                break;
            case R.id.btnOrderSend:
                ordersState = "send";
                break;
            case R.id.btnOrderNoeval:
                ordersState = "noeval";
                break;
        }
        setItemChecked();
        page = 1;
        requestData(xrefreshview, false);
        xscrollview.post(new Runnable() {
            @Override
            public void run() {
                xscrollview.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    //设置状态
    public void setItemChecked() {
        if (ordersState.equals("")) {
            btnOrderAll.setChecked(true);
        } else if (ordersState.equals("new")) {
            btnOrderNew.setChecked(true);
        }
        if (ordersState.equals("pay")) {
            btnOrderPay.setChecked(true);
        } else if (ordersState.equals("send")) {
            btnOrderSend.setChecked(true);
        }
//        else if (ordersState.equals("finish")) {
//            btnOrderFinish.setChecked(true);
//        }
        else if (ordersState.equals("noeval")) {
            btnOrderNoeval.setChecked(true);
        }
//        else if (ordersState.equals("cancel")) {
//            btnOrderCancel.setChecked(true);
//        }
    }

    public void onEventMainThread(Integer i) {
        requestData(xrefreshview, false);
    }

    @OnClick(R.id.btnOrderSearch)
    public void onClick() {
        isllSearch = false;
        llSearchShow(isllSearch);
        requestData(xrefreshview, false);
    }

    private void setEtSearchWatcher() {
        etOrderSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyword = s.toString();
            }
        });
    }

    /**
     * 搜索框显隐
     *
     * @param isllSearch
     */
    private void llSearchShow(boolean isllSearch) {
        if (isllSearch) {
            llOrderSearch.setVisibility(View.VISIBLE);
            rgOrder.setVisibility(View.GONE);
        } else {
            llOrderSearch.setVisibility(View.GONE);
            rgOrder.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.ivSearch)
    public void onViewClicked() {
        isllSearch = !isllSearch;
        llSearchShow(isllSearch);
    }

}
