package net.shopnc.b2b2c.android.ui.trys;

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

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.MyTryListAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.bean.TryListBean;
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
public class MyTryListFragment extends BaseFragment {
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
    private MyTryListAdapter adapter;
    private List<TryListBean> tryListList;
    private String url;

    public static MyTryListFragment newInstance() {
        MyTryListFragment fragment = new MyTryListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refund_money_list, container, false);
        ButterKnife.bind(this, view);
        tryListList = new ArrayList<>();
        initRecyclerView();
        url = ConstantUrl.URL_MY_TRY_LIST;
        setLayoutEmpty();
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
    }


    private void initRecyclerView() {
        rvRefund.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvRefund.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRefund.setLayoutManager(layoutManager);
        adapter = new MyTryListAdapter(context);
        rvRefund.setAdapter(adapter);
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

    private void setLayoutEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.no_data_f);
        tvEmptyTitle.setText("亲，您还没有任何试用信息哦~");
        tvEmptyBody.setText("");
        btnChoose.setVisibility(View.GONE);
    }


    private void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("page", page + "");
        params.put("pageSize", "10");

        OkHttpUtil.postAsyn(getActivity(),url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                List<TryListBean> refunds = JsonUtil.toBean(data, "trysList", new TypeToken<ArrayList<TryListBean>>() {
                }.getType());
                PageEntity pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (refunds == null || pageEntity == null || layoutEmpty == null) {
                    return;
                }

                if (pageEntity.isHasMore()) {
                    rvRefund.setLoadingMoreEnabled(true);
                } else {
                    rvRefund.setLoadingMoreEnabled(false);
                }

                if (page == 1) {
                    tryListList.clear();
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
                    tryListList.addAll(refunds);
                    adapter.setDatas(tryListList);
                    adapter.notifyDataSetChanged();
                }
            }
        }, params);
    }

}

