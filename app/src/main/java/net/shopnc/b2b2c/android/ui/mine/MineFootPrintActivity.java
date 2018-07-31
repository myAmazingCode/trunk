package net.shopnc.b2b2c.android.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.bean.MemberHistory;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 作者：Jie on 2016/6/6 16:44
 */
public class MineFootPrintActivity extends BaseActivity {


    @Bind(R.id.my_footprint_recycler)
    XRecyclerView recyclerView;

    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;

    private List<GoodsVo> historyList = new ArrayList<>();

    private printAdapter adapter;

    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("我的足迹");

        if (btnClear != null) {
            btnClear.setVisibility(View.VISIBLE);
            btnClear.setTextColor(context.getResources().getColor(R.color.nc_red));
            btnClear.setText("清空");
        }

        initRecycler();
        getFootPrint();
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMyPrint();
            }
        });
    }

    private void initRecycler() {
        //设定为垂直布局
        LinearLayoutManager manager = new LinearLayoutManager(MineFootPrintActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //设置下拉刷新动画
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置加载动画
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getFootPrint();
            }

            @Override
            public void onLoadMore() {
                ++page;
                getFootPrint();
            }
        });
        adapter = new printAdapter(MineFootPrintActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.mine_foot_print);
    }

    /**
     * 清空足迹
     */
    private void clearMyPrint() {
        Map<String, String> p = new HashMap<>();
        p.put("token", application.getToken());

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_CLEAR_FOOTPRINT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                historyList.clear();
                adapter.notifyDataSetChanged();
                layoutEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                imgEmptyLogo.setImageResource(R.drawable.no_data_a);
                tvEmptyTitle.setText("亲，您还没有任何浏览记录哦~");
                tvEmptyBody.setText("");
            }
        }, p);
    }

    private void getFootPrint() {
        Map<String, String> p = new HashMap<>();
        p.put("token", application.getToken());
        p.put("page", page + "");

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_MEMBER_HISTORY, new BeanCallback<String>() {
            @Override
            public void response(String data) {

                List<MemberHistory> list = JsonUtil.toBean(data, "browseList", new TypeToken<List<MemberHistory>>() {
                }.getType());
                PageEntity pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (list == null || pageEntity == null || layoutEmpty == null) {
                    return;
                }

                List<GoodsVo> goodsVos = new ArrayList<>();

                if (pageEntity.isHasMore()) {
                    recyclerView.setLoadingMoreEnabled(true);
                } else {
                    recyclerView.setLoadingMoreEnabled(false);
                }

                if (page == 1) {
                    historyList.clear();
                }

                //隐藏加载动画
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();

                if (list.isEmpty() && page == 1) {
                    layoutEmpty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    imgEmptyLogo.setImageResource(R.drawable.no_data_a);
                    tvEmptyTitle.setText("亲，您还没有任何浏览记录哦~");
                    tvEmptyBody.setText("");
                } else {
                    layoutEmpty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    for (int i = 0; i < list.size(); i++) {
                        goodsVos.add(list.get(i).getGoodsCommon());
                    }
                    historyList.addAll(goodsVos);
                    adapter.setDatas(historyList);
                    adapter.notifyDataSetChanged();
                }
                page++;
            }
        }, p);

    }

    private class printAdapter extends RRecyclerAdapter<GoodsVo> {

        public printAdapter(Context context) {
            super(context, R.layout.footprint_item);
        }

        @Override
        public void convert(RecyclerHolder holder, final GoodsVo goodsVo) {
            holder.setText(R.id.footprint_name, goodsVo.getGoodsName())
                    .setText(R.id.footprint_price, context.getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(goodsVo.getAppPriceMin()))
                    .setImage(R.id.footprint_image, goodsVo.getImageSrc());

            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GoodDetailsActivity.class);
                    intent.putExtra(GoodDetailsActivity.COMMONID, goodsVo.getCommonId());
                    context.startActivity(intent);
                }
            });
        }
    }

}
