package net.shopnc.b2b2c.android.ui.trys;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.TryGoodShowAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.bean.TryGoodBean;
import net.shopnc.b2b2c.android.bean.TrySortBean;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.EndLessOnScrollListener;
import net.shopnc.b2b2c.android.custom.dialog.HorizontalNavigationDialog;
import net.shopnc.b2b2c.android.custom.dialog.TrySortDialog;
import net.shopnc.b2b2c.android.custom.horizontalnavigationbar.HorizontalNavigationBar;
import net.shopnc.b2b2c.android.custom.horizontalnavigationbar.NCHorizontalNavigationBar;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/8/3 11:25.
 */
public class TryGoodShowFragment extends BaseFragment implements HorizontalNavigationBar.OnHorizontalNavigationSelectListener {

    @Bind(R.id.btnSort)
    TextView btnSort;
    @Bind(R.id.llSort)
    LinearLayout llSort;
    @Bind(R.id.btnState)
    TextView btnState;
    @Bind(R.id.llState)
    LinearLayout llState;
    @Bind(R.id.rvGoods)
    RecyclerView rvGoods;
    @Bind(R.id.ivTop)
    ImageView ivTop;
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
    @Bind(R.id.horizontal_navigation)
    NCHorizontalNavigationBar horizontalNavigation;
    @Bind(R.id.ivHorizontalNavigation)
    ImageButton ivHorizontalNavigation;
    @Bind(R.id.rlHorizontalNavigation)
    RelativeLayout rlHorizontalNavigation;

    private TrySortDialog trySortDialog;
    private TrySortDialog tryStateDialog;
    private HorizontalNavigationDialog horizontalNavigationDialog;
    private ArrayList<TrySortBean> trySortBeanList;
    private List<TrySortBean> tryStateBeanList;

    //排序条件
    private int page = 1;
    private int categoryId = -1;
    private int trysState = -1;
    private int trysType = -1;

    private List<TryGoodBean> goodses = new ArrayList<>();
    private PageEntity pageEntity;

    private TryGoodShowAdapter adapter;
    private LinearLayoutManager manager;

    public static TryGoodShowFragment newInstance(int trysType) {
        Bundle bundle = new Bundle();
        TryGoodShowFragment fragment = new TryGoodShowFragment();
        bundle.putInt("trysType", trysType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_try_good_show, container, false);
        ButterKnife.bind(this, view);
        trysType = getArguments().getInt("trysType");
        initRecyclerView();
        setLayoutEmpty();
        trySortDialog = new TrySortDialog(context);
        tryStateDialog = new TrySortDialog(context);
        horizontalNavigationDialog = new HorizontalNavigationDialog(context);
        trySortBeanList = new ArrayList<>();
        tryStateBeanList = new ArrayList<>();
        loadGoods();
        loadSortInfo();
        loadStateInfo();
        return view;
    }

    private void initView() {
        horizontalNavigation.setChannelSplit(true);//需要设置在数据之前
        horizontalNavigation.setItems(trySortBeanList);
        horizontalNavigation.setSplitColor(R.color.nc_red);
        horizontalNavigation.addOnHorizontalNavigationSelectListener(this);
        horizontalNavigation.setCurrentChannelItem(0);
        Global.trySortBean = trySortBeanList.get(0);
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        rvGoods.setLayoutManager(layoutManager);
        manager = layoutManager;
        adapter = new TryGoodShowAdapter(context);
        rvGoods.setAdapter(adapter);
        setRvGoodsScroll();
    }

    public void setLayoutEmpty() {
//        layoutSearch.setVisibility(View.GONE);
        imgEmptyLogo.setImageResource(R.drawable.no_data_a);
        tvEmptyTitle.setText("亲，没有相关商品哦~");
        tvEmptyBody.setText("");
    }

    private void loadGoods() {
        HashMap<String, String> params = new HashMap();
        params.put("page", page + "");

        if (categoryId != -1)
            params.put("categoryId", categoryId + "");

        if (trysState != -1)
            params.put("trysState", trysState + "");

        if (trysType != -1)
            params.put("trysType", trysType + "");

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_TRY_GOOD_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());
                List<TryGoodBean> goodsVoList = JsonUtil.toBean(data, "trysList", new TypeToken<List<TryGoodBean>>() {
                }.getType());

                if (page == 1) {
                    goodses.clear();
                }

                if (goodsVoList != null) {
                    goodses.addAll(goodsVoList);
                }

                if (goodses.size() > 0) {
                    rvGoods.setVisibility(View.VISIBLE);
                    layoutEmpty.setVisibility(View.GONE);
                    adapter.setDatas(goodses);
                    adapter.notifyDataSetChanged();
                } else {
                    rvGoods.setVisibility(View.GONE);
                    layoutEmpty.setVisibility(View.VISIBLE);
                }
            }
        }, params);
    }

    private void setRvGoodsScroll() {
        rvGoods.addOnScrollListener(new EndLessOnScrollListener() {
            @Override
            public void onLoadMore(Loading loading) {
                if (pageEntity.isHasMore()) {
                    page++;
                    loadGoods();
                    loading.setLoading(true);
                } else {
                    loading.setLoading(false);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int pageNumber = manager.findLastVisibleItemPosition() / 10 + 1;
                    if (pageNumber > 1) {
                        ivTop.setVisibility(View.VISIBLE);
                    } else {
                        ivTop.setVisibility(View.INVISIBLE);
                    }
                }
            }

        });
    }

    private void loadSortInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("", "");  //post  但是又无参数的权宜之计
        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_TRY_GOOD_SORT_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                TrySortBean trySortBean = new TrySortBean();
                trySortBean.setCategoryName("全部分类");
                trySortBean.setCategoryId(-1);
                trySortBean.setSelected(true);
                trySortBeanList.add(trySortBean);
                ArrayList<TrySortBean> trySortBeans = JsonUtil.toBean(data, "trysCategoryList", new TypeToken<ArrayList<TrySortBean>>() {
                }.getType());
                if (trySortBeans != null && !trySortBeans.isEmpty()) {
                    trySortBeanList.addAll(trySortBeans);
                }
                trySortDialog.setDatas(trySortBeanList);
                horizontalNavigationDialog.setDatas(trySortBeanList);
                initView();
            }
        }, params);

        trySortDialog.setOnItemClick(new TrySortDialog.OnItemClick() {
            @Override
            public void onItemClick(TrySortBean trySortBean) {
                categoryId = trySortBean.getCategoryId();
                btnSort.setText(trySortBean.getCategoryName());
                page = 1;
                loadGoods();
                setRvGoodsScroll();
            }
        });

        horizontalNavigationDialog.setOnItemClick(new HorizontalNavigationDialog.OnItemClick() {
            @Override
            public void onItemClick(TrySortBean trySortBean) {
                categoryId = trySortBean.getCategoryId();
                btnSort.setText(trySortBean.getCategoryName());
                page = 1;
                loadGoods();
                setRvGoodsScroll();
                Global.trySortBean = trySortBean;
                for (int i = 0; i < trySortBeanList.size(); i++) {
                    if (Global.trySortBean.getCategoryId() == trySortBeanList.get(i).getCategoryId()) {
                        horizontalNavigation.setCurrentChannelItem(i);
                        break;
                    }
                }
            }
        });
    }


    private void loadStateInfo() {
        tryStateBeanList.add(new TrySortBean(-1, "全部试用", true));
        tryStateBeanList.add(new TrySortBean(1, "正在进行", false));
        tryStateBeanList.add(new TrySortBean(2, "即将开始", false));
        tryStateBeanList.add(new TrySortBean(3, "已经结束", false));
        tryStateDialog.setDatas(tryStateBeanList);

        tryStateDialog.setOnItemClick(new TrySortDialog.OnItemClick() {
            @Override
            public void onItemClick(TrySortBean trySortBean) {
                trysState = trySortBean.getCategoryId();
                btnState.setText(trySortBean.getCategoryName());
                page = 1;
                loadGoods();
                setRvGoodsScroll();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (trySortDialog != null) {
            trySortDialog.popupWindow.dismiss();
            trySortDialog.popupWindow = null;
            trySortDialog = null;
        }
        if (tryStateDialog != null) {
            tryStateDialog.popupWindow.dismiss();
            tryStateDialog.popupWindow = null;
            tryStateDialog = null;
        }
        if (horizontalNavigationDialog != null) {
            horizontalNavigationDialog.popupWindow.dismiss();
            horizontalNavigationDialog.popupWindow = null;
            horizontalNavigationDialog = null;
        }
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.llSort, R.id.llState, R.id.ivTop, R.id.ivHorizontalNavigation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llSort:
                trySortDialog.show(getActivity(), btnSort);
                break;
            case R.id.llState:
                tryStateDialog.show(getActivity(), btnState);
                break;
            case R.id.ivTop:
                rvGoods.smoothScrollToPosition(0);
                break;
            case R.id.ivHorizontalNavigation:
                horizontalNavigationDialog.show(getActivity(), rlHorizontalNavigation);
                break;
        }
    }

    @Override
    public void select(int index) {
        TrySortBean trySortBean = trySortBeanList.get(index);
        categoryId = trySortBean.getCategoryId();
        btnSort.setText(trySortBean.getCategoryName());
        page = 1;
        loadGoods();
        setRvGoodsScroll();
        Global.trySortBean = trySortBean;
        horizontalNavigationDialog.refresh();
    }

}

