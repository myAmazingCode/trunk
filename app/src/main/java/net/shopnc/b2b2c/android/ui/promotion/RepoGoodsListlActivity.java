package net.shopnc.b2b2c.android.ui.promotion;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.RepoGoodsListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.RepoGoodsList;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.EndLessOnScrollListener;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class RepoGoodsListlActivity extends BaseActivity {

    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.tvEmpty)
    TextView mTvEmpty;
    private String favId;
    private int page = 1;
    private boolean mHasMore;
    private List<RepoGoodsList.DatasBean.DistributorGoodsListBean> mDatas = new ArrayList<>();
    private RepoGoodsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCommonHeader(getIntent().getStringExtra("repoName"));
        favId = getIntent().getStringExtra("favId");
        initRV();

        loadData();
    }

    public static final String TAG = "RepoGoodsListlActivity";

    private void loadData() {
        String url = ConstantUrl.URL_API + "/member/distributor/goods/list";
        Map<String, String> map = new HashMap<>();
        map.put("favId", favId);
        map.put("token", application.getToken());
        map.put("page", String.valueOf(page));
        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (200 == JsonUtil.toInteger(response, "code")) {
                    RepoGoodsList repoGoodsList = new Gson().fromJson(response, RepoGoodsList.class);
                    RepoGoodsList.DatasBean datas = repoGoodsList.getDatas();
                    mHasMore = datas.getPageEntity().isHasMore();
                    List<RepoGoodsList.DatasBean.DistributorGoodsListBean> goodsList = datas.getDistributorGoodsList();
                    mDatas.addAll(goodsList);
                    mAdapter.notifyDataSetChanged();

                    if (mDatas.size() == 0) {
                        mTvEmpty.setVisibility(View.VISIBLE);
                        mRv.setVisibility(View.GONE);
                    } else {
                        mTvEmpty.setVisibility(View.GONE);
                        mRv.setVisibility(View.VISIBLE);
                    }

                } else {
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, map);
    }

    private void initRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(layoutManager);
        mRv.addOnScrollListener(new EndLessOnScrollListener() {
            @Override
            public void onLoadMore(Loading loading) {
                if (mHasMore) {
                    page++;
                    loadData();
                    loading.setLoading(true);
                } else {
                    loading.setLoading(false);
                }
            }
        });

        mAdapter = new RepoGoodsListAdapter(this);
        mAdapter.setDatas(mDatas);
        Log.d(TAG, "initRV: favId = "+favId);
        mAdapter.setRepo(favId);

        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_repo_detail);
    }
}
