package net.shopnc.b2b2c.android.ui.promotion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.WithdrawAdapter;
import net.shopnc.b2b2c.android.bean.CommissionWithdraw;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.EndLessOnScrollListener;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithdrawFragment extends Fragment {

    @Bind(R.id.tvEmpty)
    TextView mTvEmpty;
    @Bind(R.id.rv)
    RecyclerView mRv;
    private int page = 1;
    private boolean mHasMore;
    private List<CommissionWithdraw.DatasBean.CashListBean> mDatas = new ArrayList<>();
    private WithdrawAdapter mAdapter;

    public WithdrawFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_withdraw, container, false);
        ButterKnife.bind(this, view);
        initRV();
        initData();
        return view;
    }

    //提现按钮
    @OnClick({R.id.tvWithDraw})
    public void onclick() {
        startActivity(new Intent(getActivity(), CommissionWithdrawActivity.class));
    }

    private void initRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(layoutManager);
        mRv.addOnScrollListener(new EndLessOnScrollListener() {
            @Override
            public void onLoadMore(Loading loading) {
                if (mHasMore) {
                    page++;
                    initData();
                    loading.setLoading(true);
                } else {
                    loading.setLoading(false);
                }
            }
        });

        mAdapter = new WithdrawAdapter(getActivity());
        mAdapter.setDatas(mDatas);
        mRv.setAdapter(mAdapter);
    }

    public static final String TAG = "WithdrawFragment";

    private void initData() {
        String url = ConstantUrl.URL_API + "/member/distributor/commission/cash/list";
        Map<String, String> map = new HashMap<>();
        map.put("token", MyShopApplication.getInstance().getToken());
        map.put("page", String.valueOf(page));
        map.put("pageSize", "10");

        OkHttpUtil.postAsyn(getActivity(), url, new BeanCallback<String>() {


            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (200 == JsonUtil.toInteger(response, "code")) {

                    CommissionWithdraw commissionWithdraw = new Gson().fromJson(response, CommissionWithdraw.class);
                    CommissionWithdraw.DatasBean datas = commissionWithdraw.getDatas();
                    mHasMore = datas.getPageEntity().isHasMore();
                    List<CommissionWithdraw.DatasBean.CashListBean> cashList = datas.getCashList();
                    if (cashList != null && cashList.size() != 0) {
                        mDatas.addAll(cashList);
                        mAdapter.notifyDataSetChanged();
                    }

                    //为空时，显示空数据
                    if (mDatas.size() == 0) {
                        mTvEmpty.setVisibility(View.VISIBLE);
                    } else {
                        mTvEmpty.setVisibility(View.GONE);
                    }
                } else {
                    TToast.showShort(getActivity(), JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, map);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
