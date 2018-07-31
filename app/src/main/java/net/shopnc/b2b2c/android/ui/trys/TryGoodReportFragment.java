package net.shopnc.b2b2c.android.ui.trys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.TryGoodReportAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.bean.TryGoodReportBean;
import net.shopnc.b2b2c.android.common.DensityUtil;
import net.shopnc.b2b2c.android.common.HItemDecoration;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.EndLessOnScrollListener;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/3 11:25.
 */
public class TryGoodReportFragment extends BaseFragment {

    @Bind(R.id.rvReport)
    RecyclerView rvReport;

    private TryGoodReportAdapter adapter;
    private List<TryGoodReportBean> reportBeanList;
    private int page;
    private PageEntity pageEntity;

    public static TryGoodReportFragment newInstance() {
        TryGoodReportFragment fragment = new TryGoodReportFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_try_good_report, container, false);
        ButterKnife.bind(this, view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvReport.setLayoutManager(manager);
        adapter = new TryGoodReportAdapter(context);
        rvReport.setAdapter(adapter);
        reportBeanList = new ArrayList<>();
        rvReport.addItemDecoration(new HItemDecoration(DensityUtil.dip2px(context, 10)));
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        HashMap<String, String> params = new HashMap();
        params.put("page", page + "");

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_TRY_REPORT_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());
                List<TryGoodReportBean> goodsVoList = JsonUtil.toBean(data, "reportList", new TypeToken<List<TryGoodReportBean>>() {
                }.getType());

                if (page == 1) {
                    reportBeanList.clear();
                }

                if (goodsVoList != null) {
                    reportBeanList.addAll(goodsVoList);
                }
                if (reportBeanList.size() > 0) {
                    adapter.setDatas(reportBeanList);
                    setRvGoodsScroll();
                    adapter.notifyDataSetChanged();
                }
            }
        }, params);
    }

    private void setRvGoodsScroll() {
        rvReport.addOnScrollListener(new EndLessOnScrollListener() {
            @Override
            public void onLoadMore(Loading loading) {
                if (pageEntity.isHasMore()) {
                    page++;
                    loadData();
                    loading.setLoading(true);
                } else {
                    loading.setLoading(false);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}

