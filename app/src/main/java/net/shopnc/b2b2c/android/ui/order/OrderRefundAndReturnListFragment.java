package net.shopnc.b2b2c.android.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.OrderComplaintListAdapter;
import net.shopnc.b2b2c.android.adapter.OrderRefundAndReturnListAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.bean.Complain;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.bean.RefundItemVo;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/8/3 11:25.
 */
public class OrderRefundAndReturnListFragment extends BaseFragment {

    @Bind(R.id.rvRefund)
    XRecyclerView rvRefund;
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

    private int page = 1;
    private OrderRefundAndReturnListAdapter adapter;
    private OrderComplaintListAdapter orderComplaintListAdapter;
    private List<RefundItemVo> refundItemVoList;
    private List<Complain> complainList = new ArrayList<>();
    private String flag;
    private String url;

    public static OrderRefundAndReturnListFragment newInstance(String flag) {
        Bundle bundle = new Bundle();
        bundle.putString("flag", flag);
        OrderRefundAndReturnListFragment fragment = new OrderRefundAndReturnListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refund_money_list, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        flag = getArguments().getString("flag");
        refundItemVoList = new ArrayList<>();
        initRecyclerView();
        switchRefundOrReturn();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    private void switchRefundOrReturn() {
        if (flag.equals("refund")) {
            url = ConstantUrl.URL_ORDER_REFUND_MONEY_LIST;
            setRefundLayoutEmpty();
        } else if (flag.equals("return")) {
            url = ConstantUrl.URL_ORDER_RETURN_GOOD_LIST;
            setReturnLayoutEmpty();
        } else {
            url = ConstantUrl.URL_MEMBER_COMPLAIN_LIST;
            setComplaintLayoutEmpty();
        }
    }

    private void initRecyclerView() {
        rvRefund.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvRefund.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRefund.setLayoutManager(layoutManager);
        adapter = new OrderRefundAndReturnListAdapter(context);
        orderComplaintListAdapter = new OrderComplaintListAdapter(context);
        adapter.setFlag(flag);
        if (flag.equals("complaint")) {
            rvRefund.setAdapter(orderComplaintListAdapter);
        } else {
            rvRefund.setAdapter(adapter);
        }

        rvRefund.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadData();
            }
        });
    }

    private void setRefundLayoutEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.no_data_a);
        tvEmptyTitle.setText("亲，您尚未有退款信息哦~");
        tvEmptyBody.setText("");
        btnChoose.setVisibility(View.GONE);
    }

    private void setReturnLayoutEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.no_data_a);
        tvEmptyTitle.setText("亲，您还没有退货信息哦~");
        tvEmptyBody.setText("");
        btnChoose.setVisibility(View.GONE);
    }

    private void setComplaintLayoutEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.no_data_a);
        tvEmptyTitle.setText("亲，您还没有投诉信息哦~");
        tvEmptyBody.setText("");
        btnChoose.setVisibility(View.GONE);
    }

    private void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("page", page + "");

        OkHttpUtil.postAsyn(getActivity(),url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                if (flag.equals("complaint")) {
                    List<Complain> complains = JsonUtil.toBean(data, "complainList", new TypeToken<ArrayList<Complain>>() {
                    }.getType());

                    PageEntity pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                    }.getType());

                    if (complains == null || pageEntity == null || layoutEmpty == null) {
                        return;
                    }

                    if (pageEntity != null && pageEntity.isHasMore()) {
                        rvRefund.setLoadingMoreEnabled(true);
                    } else {
                        rvRefund.setLoadingMoreEnabled(false);
                    }

                    if (page == 1) {
                        complainList.clear();
                    }

                    //隐藏加载动画
                    rvRefund.refreshComplete();
                    rvRefund.loadMoreComplete();

                    if (complains.isEmpty() && page == 1) {
                        layoutEmpty.setVisibility(View.VISIBLE);
                        rvRefund.setVisibility(View.GONE);
                    } else {
                        layoutEmpty.setVisibility(View.GONE);
                        rvRefund.setVisibility(View.VISIBLE);
                        complainList.addAll(complains);
                        orderComplaintListAdapter.setDatas(complainList);
                        orderComplaintListAdapter.notifyDataSetChanged();
                    }

                } else {
                    List<RefundItemVo> refunds = JsonUtil.toBean(data, "refundItemVoList", new TypeToken<ArrayList<RefundItemVo>>() {
                    }.getType());

                    PageEntity pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                    }.getType());

                    if (refunds == null || pageEntity == null || layoutEmpty == null) {
                        return;
                    }

                    if (pageEntity != null && pageEntity.isHasMore()) {
                        rvRefund.setLoadingMoreEnabled(true);
                    } else {
                        rvRefund.setLoadingMoreEnabled(false);
                    }

                    if (page == 1) {
                        refundItemVoList.clear();
                    }

                    //隐藏加载动画
                    rvRefund.refreshComplete();
                    rvRefund.loadMoreComplete();

                    if (refunds.isEmpty() && page == 1) {
                        layoutEmpty.setVisibility(View.VISIBLE);
                        rvRefund.setVisibility(View.GONE);
                    } else {
                        layoutEmpty.setVisibility(View.GONE);
                        rvRefund.setVisibility(View.VISIBLE);
                        refundItemVoList.addAll(refunds);
                        adapter.setDatas(refundItemVoList);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }, params);
    }

    public void onEventMainThread(EventObj obj) {
        if (obj.getFlag().equals(EventObj.ORDERREFUNDSENDDELAY)) {
            loadData();
        }
    }

}

