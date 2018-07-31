package net.shopnc.b2b2c.android.ui.fenxiao;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.KillBrandAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.KillBrandBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class SecondKillBrandActivity extends BaseActivity {

    @Bind(R.id.recycler_kill)
    XRecyclerView recyclerKill;
//    @Bind(R.id.kill_refresh)
//    XRefreshView killRefresh;
    private String brand_id = null;
    private int curpage = 1;
    private KillBrandAdapter adapter;
    private List<KillBrandBean.GoodsListBean> goods_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ButterKnife.bind(this);
        goods_list = new ArrayList<>();

        initRecyclerView();

        brand_id = getIntent().getStringExtra("brand_id");
        if (brand_id != null) {
            initData(true);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        setBtnMoreVisible();
        setCommonHeader("秒杀活动详情");
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_second_kill_brand);
    }

    public void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new KillBrandAdapter(this,goods_list);
        recyclerKill.setLayoutManager(linearLayoutManager);
        recyclerKill.setAdapter(adapter);

        recyclerKill.setLoadingMoreEnabled(true);
        recyclerKill.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curpage = 1;
                recyclerKill.setLoadingMoreEnabled(true);
                goods_list.clear();
                initData(true);
            }

            @Override
            public void onLoadMore() {
                initData(false);
            }
        });

    }

    public void refresh(KillBrandBean bean, boolean isRefresh) {
        if (isRefresh) {
            View header = LayoutInflater.from(this).inflate(R.layout.kill_brand_header, null);
            ImageView view = header.findViewById(R.id.iv_header);
            Glide.with(this)
                    .load(bean.getSpike_info().getSpike_banner())
                    .into(view);


//            recyclerKill.addHeaderView(header);

//
            HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(adapter);
            wrapper.addHeaderView(header);


            recyclerKill.setAdapter(wrapper);
            wrapper.notifyDataSetChanged();
        }




        goods_list.addAll(bean.getGoods_list());

        adapter.notifyDataSetChanged();
        if (isRefresh) {
            recyclerKill.refreshComplete();
        } else {
            recyclerKill.loadMoreComplete();
        }

    }


    public void initData(final boolean isRefresh) {

        RemoteDataHandler.asyncDataStringGet(Constants.URL_BRAND_SPIKE + "&brand_id=" + brand_id + "&curpage=" + curpage, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.isHasMore()) {
                    curpage++;
                } else {
                    recyclerKill.setLoadingMoreEnabled(false);
                }
                String info = data.getJson();
                KillBrandBean bean = new Gson().fromJson(info, KillBrandBean.class);

                refresh(bean, isRefresh);


            }
        });
    }


}
