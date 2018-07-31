package net.shopnc.b2b2c.android.ui.promotion;


import android.content.Context;
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
import net.shopnc.b2b2c.android.adapter.CmsBalanceAdapter;
import net.shopnc.b2b2c.android.bean.CommissionBalance;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class BalanceFragment extends Fragment {


    @Bind(R.id.tvEmpty)
    TextView mTvEmpty;
    @Bind(R.id.rv)
    RecyclerView mRv;
    private int page = 1;
    private boolean mHasMore;
    private CmsBalanceAdapter mAdapter;
    private List<CommissionBalance.DatasBean.LogListBean> mDatas = new ArrayList<>();

    public BalanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnSumListener = (CommissionActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        ButterKnife.bind(this, view);
        initRV();
        initData();
        return view;
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

        mAdapter = new CmsBalanceAdapter(getActivity());
        mAdapter.setDatas(mDatas);
        mRv.setAdapter(mAdapter);
    }

    private void initData() {
        String url = ConstantUrl.URL_API + "/member/distributor/commission/list";
        Map<String, String> map = new HashMap<>();
        map.put("token", MyShopApplication.getInstance().getToken());
        map.put("page", String.valueOf(page));

        OkHttpUtil.postAsyn(getActivity(),url, new BeanCallback<String>() {


            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (200 == JsonUtil.toInteger(response, "code")) {

                    CommissionBalance commissionBalance = new Gson().fromJson(response, CommissionBalance.class);
                    CommissionBalance.DatasBean datas = commissionBalance.getDatas();

                    CommissionBalance.DatasBean.PageEntityBean pageEntity = datas.getPageEntity();
                    mHasMore = pageEntity.isHasMore();
                    List<CommissionBalance.DatasBean.LogListBean> logList = datas.getLogList();

                    if (logList != null && logList.size() != 0) {
                        mDatas.addAll(logList);
                        mAdapter.notifyDataSetChanged();
                    }


                    String sum = JsonUtil.toString(response, "datas", "sum");
                    mOnSumListener.onSum(sum);
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

    public static final String TAG = BalanceFragment.class.getSimpleName();

    private OnSumListener mOnSumListener;

    public void setOnSumListener(OnSumListener onSumListener) {
        mOnSumListener = onSumListener;
    }

    public interface OnSumListener {
        void onSum(String sum);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
