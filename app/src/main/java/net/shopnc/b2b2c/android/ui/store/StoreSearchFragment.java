package net.shopnc.b2b2c.android.ui.store;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.StoreSearchAdapter;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 店铺搜索列表fragment
 *
 * @author huting
 * @date 2016/5/11
 */
public class StoreSearchFragment extends Fragment {

    @Bind(R.id.btnListType)
    ImageView btnListType;

    @Bind(R.id.rvGoods)
    XRecyclerView rvGoods;

    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;
    @Bind(R.id.btnChoose)
    Button btnChoose;

    @Bind(R.id.tvSort)
    TextView tvSort;
    @Bind(R.id.tvSale)
    RadioButton tvSale;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.ivPriceUp)
    ImageView ivPriceUp;
    @Bind(R.id.ivPriceDown)
    ImageView ivPriceDown;

    private boolean isList = true;
    private String storeId;
    private String labelId;
    private String keyword;
    private String sort = "default_desc";
    public int page = 1;

    private PageEntity pageEntity;
    private StoreSearchAdapter adapter;
    private List<GoodsVo> goodsVoList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewLayout = inflater.inflate(R.layout.fragment_store_search, container, false);
        ButterKnife.bind(this, viewLayout);

        storeId = getArguments().getString("storeId");
        labelId = getArguments().getString("labelId");
        keyword = getArguments().getString("keyword");

        setLayoutEmpty();
        initView();

        return viewLayout;
    }

    /**
     * 初始化view
     */
    private void initView() {
        goodsVoList = new ArrayList<>();

        tvSort.setSelected(true);
        btnListType.setSelected(true);

        initRecyclerView();
        rvGoods.setPullRefreshEnabled(false);
        rvGoods.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvGoods.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvGoods.setFocusable(false);

        rvGoods.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadGoods();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadGoods();
            }
        });
    }

    /**
     * 排序点击事件
     *
     * @param v
     */
    @OnClick({R.id.btnListType})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btnListType:
                btnListType.setSelected(!btnListType.isSelected());
                isList = !isList;
                page = 1;
                initRecyclerView();
                break;
        }
    }

    @OnClick({R.id.tvSort, R.id.tvSale, R.id.rlPrice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSort:
                sortSelected(true, false, "");
                sort = "default_desc";
                page = 1;
                loadGoods();
                break;
            case R.id.tvSale:
                sortSelected(false, true, "");
                sort = "sale_desc";
                page = 1;
                loadGoods();
                break;
            case R.id.rlPrice:
                if (tvPrice.isSelected()) {
                    if (ivPriceUp.isSelected()) { //已是升序  变降序
                        sortSelected(false, false, "price_desc");
                    } else {//已是降序  变升序
                        sortSelected(false, false, "price_asc");
                    }
                } else {//第一次点击为升序显示
                    sortSelected(false, false, "price_asc");
                }
                page = 1;
                loadGoods();
                break;
        }
    }

    private void sortSelected(boolean sortFlag, boolean sale, String price) {
        tvSort.setSelected(sortFlag);
        tvSale.setSelected(sale);
        if (price.equals("")) {
            tvPrice.setSelected(false);
            ivPriceUp.setSelected(false);
            ivPriceDown.setSelected(false);
        } else if (price.equals("price_asc")) {  //升序
            sort = price;
            tvPrice.setSelected(true);
            ivPriceUp.setSelected(true);
            ivPriceDown.setSelected(false);
        } else {  //price_desc  降序
            sort = price;
            tvPrice.setSelected(true);
            ivPriceUp.setSelected(false);
            ivPriceDown.setSelected(true);
        }
    }

    private void initRecyclerView() {
        if (isList) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvGoods.setLayoutManager(layoutManager);
            adapter = new StoreSearchAdapter(getActivity(), R.layout.recyclerview_store_search_goods_list_item);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
            rvGoods.setLayoutManager(layoutManager);
            adapter = new StoreSearchAdapter(getActivity(), R.layout.recyclerview_store_search_goods_grid_item);
        }
        adapter.setHasStableIds(true);
        rvGoods.setAdapter(adapter);
        loadGoods();
    }

    private void loadGoods() {
        //拼接url
        String url = ConstantUrl.URL_STORE_SEARCH_GOODS;
        if (Common.isNotEmpty(storeId)) {
            url = url + "?storeId=" + storeId;
        }
        if (Common.isNotEmpty(labelId)) {
            url = url + "&labelId=" + labelId;
        }
        if (Common.isNotEmpty(keyword)) {
            url = url + "&keyword=" + keyword;
        }
        if (Common.isNotEmpty(sort)) {
            url = url + "&sort=" + sort;
        }
        url += "&page=" + page + "&pageSize=" + 10;

        OkHttpUtil.getAsyn(getActivity(), url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());
                List<GoodsVo> newGoodsVoList = JsonUtil.toBean(data, "goodsCommonList", new TypeToken<List<GoodsVo>>() {
                }.getType());

                if (newGoodsVoList == null || pageEntity == null || layoutEmpty == null) {
                    return;
                }

                if (pageEntity != null && pageEntity.isHasMore()) {
                    rvGoods.setLoadingMoreEnabled(true);
                } else {
                    rvGoods.setLoadingMoreEnabled(false);
                }

                if (page == 1) {
                    goodsVoList.clear();
                }

                //隐藏加载动画
                rvGoods.refreshComplete();
                rvGoods.loadMoreComplete();

                if (newGoodsVoList.isEmpty() && page == 1) {
                    rvGoods.setVisibility(View.GONE);
                    showLayoutEmpty();
                } else {
                    rvGoods.setVisibility(View.VISIBLE);
                    layoutEmpty.setVisibility(View.GONE);
                    goodsVoList.addAll(newGoodsVoList);
                    adapter.setDatas(goodsVoList);
                    adapter.notifyDataSetChanged();
                }
            }

        });
    }

    public void setLayoutEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.search_w);
        tvEmptyTitle.setText("没有找到任何相关信息");
        tvEmptyBody.setText("搜索其它商品名或筛选项...");
        btnChoose.setVisibility(View.VISIBLE);
        btnChoose.setText("查看全部店铺商品");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelId = "";
                keyword = "";
                loadGoods();
            }
        });
    }

    public void showLayoutEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
