package net.shopnc.b2b2c.android.ui.mine.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.adapter.MyGoodsFavoriteAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.MemberFavGoods;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavGoodFragment extends BaseFragment {
    @Bind(R.id.rvFav)
    XRecyclerView rvFav;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;
    @Bind(R.id.btnChoose)
    Button btnChoose;
    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;

    private int page = 1;
    private List<MemberFavGoods> stores = new ArrayList<>();
    private MyGoodsFavoriteAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_list, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        loadData();
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

    private void setLayoutEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.no_data_c);
        tvEmptyTitle.setText("亲，您还没有收藏任何商品哦~");
        tvEmptyBody.setText("");
//        btnChoose.setVisibility(View.VISIBLE);
        btnChoose.setVisibility(View.GONE);
        btnChoose.setText("随便逛逛");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainFragmentManager.class);
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_HOME_URL));
                context.startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        rvFav.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvFav.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        rvFav.setLayoutManager(layoutManager);
        adapter = new MyGoodsFavoriteAdapter(context);
        rvFav.setAdapter(adapter);
        rvFav.setLoadingListener(new XRecyclerView.LoadingListener() {
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

    private void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("page", page + "");

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_MEMBER_GOODS_FAVORITE_KIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                List<MemberFavGoods> list = JsonUtil.toBean(data, "favGoodsList", new TypeToken<List<MemberFavGoods>>() {
                }.getType());
                PageEntity pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (list == null || pageEntity == null || layoutEmpty == null) {
                    return;
                }

                if (pageEntity.isHasMore()) {
                    rvFav.setLoadingMoreEnabled(true);
                } else {
                    rvFav.setLoadingMoreEnabled(false);
                }

                if (page == 1) {
                    stores.clear();
                }

                //隐藏加载动画
                rvFav.refreshComplete();
                rvFav.loadMoreComplete();

                if (list.isEmpty() && page == 1) {
                    layoutEmpty.setVisibility(View.VISIBLE);
                    rvFav.setVisibility(View.GONE);
                } else {
                    layoutEmpty.setVisibility(View.GONE);
                    rvFav.setVisibility(View.VISIBLE);
                    stores.addAll(list);
                    adapter.setDatas(stores);
                    adapter.notifyDataSetChanged();
                }
            }
        }, params);
    }

}
