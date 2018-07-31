package net.shopnc.b2b2c.android.ui.good;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.SearchGoodListAdapter;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.bean.SelectFilter;
import net.shopnc.b2b2c.android.bean.SortString;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.PopupWindowHelper;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.EndLessOnScrollListener;
import net.shopnc.b2b2c.android.ui.mine.MineFootPrintActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchGoodsShowActivity extends Activity {

    @Bind(R.id.etSearchText)
    TextView etSearchText;
    @Bind(R.id.title_bar_search)
    ImageView title_bar_search;

    @Bind(R.id.btnListType)
    ImageView btnListType;

    @Bind(R.id.rvGoods)
    RecyclerView rvGoods;

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


    @Bind(R.id.btnSort)
    TextView btnSort;
    @Bind(R.id.ivSort)
    ImageView ivSort;
    @Bind(R.id.btnSale)
    TextView btnSale;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.ivPriceUp)
    ImageView ivPriceUp;
    @Bind(R.id.ivPriceDown)
    ImageView ivPriceDown;
    @Bind(R.id.btnScreen)
    TextView btnScreen;
    @Bind(R.id.ivScreen)
    ImageView ivScreen;
    @Bind(R.id.ivFoot)
    ImageView ivFoot;
    @Bind(R.id.ivTop)
    ImageView ivTop;


    private Context context;
    private MyShopApplication application;
    private PopupWindow popSort;
    private SearchGoodSelectDialog selectDialog;

    //排序条件
    private String keyword;//搜索关键字
    private int cat = -1;//分类
    private String sort = "";
    private String selectValue = "";
    private int page = 1;
    private int brandId = -1;

    //排序按钮
    private TextView btnSortDefault;
    private TextView btnSortView;

    private List<GoodsVo> goodses = new ArrayList<>();
    private SelectFilter filter;
    private PageEntity pageEntity;

    private SearchGoodListAdapter adapter;

    //list grid视图模式
    private boolean isList;
    private int currentItemPosition = 0;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_search_good_show);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        context = this;
        application = MyShopApplication.getInstance();
        keyword = getIntent().getStringExtra("keyword");
        brandId = getIntent().getIntExtra("brandId", -1);
        cat = getIntent().getIntExtra("cat", -1);
        etSearchText.setText(keyword);
        initViews();
        setLayoutEmpty();
    }

    private void initViews() {
        //初始化为list模式
        btnListType.setSelected(true);
        isList = true;
        sortSelected(true, false, "", false);
        sort = "";
        page = 1;
        selectDialog = new SearchGoodSelectDialog(context);

        initRecyclerView();
        loadGoods();

        title_bar_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchGoodsShowActivity.this, SearchGoodActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void initRecyclerView() {
        if (isList) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvGoods.setLayoutManager(layoutManager);
            manager = layoutManager;
            adapter = new SearchGoodListAdapter(context, R.layout.recyclerview_searchgood_list_item);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
            rvGoods.setLayoutManager(layoutManager);
            manager = layoutManager;
            adapter = new SearchGoodListAdapter(context, R.layout.recyclerview_searchgood_grid_item);
        }
//        adapter.setHasStableIds(true);
        rvGoods.setAdapter(adapter);
        setRvGoodsScroll();
    }

    public void setLayoutEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.search_w);
        tvEmptyTitle.setText("没有找到任何相关信息");
        tvEmptyBody.setText("选择或搜索其它商品分类/名称...");
        btnChoose.setVisibility(View.VISIBLE);
        btnChoose.setText("重新选择");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showLayoutEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popSort != null) {
            popSort.dismiss();
            popSort = null;
        }
        selectDialog.dismiss();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    public static final String TAG = "SearchShow";

    private void loadGoods() {
        String url = ConstantUrl.URL_SEARCH_GOOD + "?page=" + page + "&pageSize=" + 10;

        if (Common.isNotEmpty(keyword)) {
            url = url + "&keyword=" + keyword;
        }


        if (brandId != -1) {
            url = url + "&brand=" + brandId;
        }

        if (Common.isNotEmpty(sort)) {
            url = url + "&sort=" + sort;
        }

        if (Common.isNotEmpty(selectValue)) {
            url = url + selectValue;
        }
        if (cat != -1) {
            url = url + "&cat=" + cat;
        }

        Log.d(TAG, "loadGoods: url = " + url);

        OkHttpUtil.getAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());
                List<GoodsVo> goodsVoList = JsonUtil.toBean(data, "goodsList", new TypeToken<List<GoodsVo>>() {
                }.getType());
                if (JsonUtil.toString(data, "filter", "searchCheckedFilterList").equals("[]")) {
                    filter = JsonUtil.toBean(data, "filter", new TypeToken<SelectFilter>() {
                    }.getType());
                }

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
                    showLayoutEmpty();
                }
            }
        });
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
                    //关闭黑色背景的提示文字【2/6】
//                    TToast.showBlackShort(context, pageNumber + "/" + pageEntity.getTotalPage());
                    if (pageNumber > 1) {
                        ivTop.setVisibility(View.VISIBLE);
                    } else {
                        ivTop.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    @OnClick({R.id.etSearchText, R.id.flListType, R.id.btnListType, R.id.btnBack, R.id.ivFoot, R.id.ivTop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etSearchText:
//                Intent i = new Intent(SearchGoodsShowActivity.this, SearchGoodActivity.class);
//                startActivity(i);
                finish();
                break;
            case R.id.flListType:
            case R.id.btnListType:
                btnListType.setSelected(!btnListType.isSelected());
                isList = !isList;
                currentItemPosition = manager.findFirstVisibleItemPosition();
                initRecyclerView();
                adapter.setDatas(goodses);
                //TODO  比较好的应该是计算位置  而不是大概模拟  有时间再计算吧
                rvGoods.smoothScrollToPosition(currentItemPosition + 3);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.ivFoot:
                if (ShopHelper.isLogin(context)) {
                    Intent i = new Intent(context, MineFootPrintActivity.class);
                    startActivity(i);
                }

                break;
            case R.id.ivTop:
                rvGoods.smoothScrollToPosition(0);
                break;
        }
    }

    @OnClick({R.id.llSort, R.id.btnSale, R.id.rlPrice, R.id.llScreen})
    public void onSortClick(View view) {
        switch (view.getId()) {
            case R.id.llSort:
                sortSelected(true, false, "", false);
                showSortPopWindow(view);
                sort = btnSortDefault.isSelected() ? "" : "comment_desc";
                page = 1;
                loadGoods();
                break;
            case R.id.btnSale:
                sortSelected(false, true, "", false);
                sort = "sale_desc";
                page = 1;
                loadGoods();
                break;
            case R.id.rlPrice:
                if (tvPrice.isSelected()) {
                    if (ivPriceUp.isSelected()) { //已是升序  变降序
                        sortSelected(false, false, "price_desc", false);
                    } else {//已是降序  变升序
                        sortSelected(false, false, "price_asc", false);
                    }
                } else {//第一次点击为升序显示
                    sortSelected(false, false, "price_asc", false);
                }
                page = 1;
                loadGoods();
                break;
            case R.id.llScreen:
                sortSelected(false, false, "", true);
//                Intent intent = new Intent(context, SearchGoodSelectActivity.class);
//                intent.putExtra("selectValue", selectValue);
//                intent.putExtra("filter",filter);
//                startActivity(intent);
                selectDialog.setFilter(filter);
                selectDialog.show();
                break;
        }
    }


    /**
     * 显示排序弹出窗口
     */
    private void showSortPopWindow(View view) {
        if (popSort == null) {
            View viewPopSort = LayoutInflater.from(context).inflate(R.layout.nc_popwindow_goods_sort, null);
            popSort = new PopupWindow(viewPopSort, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popSort.setTouchable(true);
            popSort.setOutsideTouchable(true);
            popSort.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
            viewPopSort.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (popSort != null && popSort.isShowing()) {
                        popSort.dismiss();
                    }
                    return false;
                }
            });

            //选择综合排序
            btnSortDefault = viewPopSort.findViewById(R.id.btnSortDefault);
            btnSortDefault.setSelected(true);
            btnSortDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (btnSortDefault.isSelected()) {
                        popSort.dismiss();
                        return;
                    }
                    btnSortDefault.setSelected(true);
                    btnSortView.setSelected(false);
                    btnSort.setText("综合");
                    sortSelected(true, false, "", false);
                    sort = "";
                    page = 1;
                    loadGoods();
                    popSort.dismiss();
                }
            });

            //选择人气排序
            btnSortView = viewPopSort.findViewById(R.id.btnSortView);
            btnSortView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (btnSortView.isSelected()) {
                        popSort.dismiss();
                        return;
                    }
                    btnSortView.setSelected(true);
                    btnSortDefault.setSelected(false);
                    btnSort.setText("人气");
                    sortSelected(true, false, "", false);
                    sort = "comment_desc";
                    page = 1;
                    loadGoods();
                    popSort.dismiss();
                }
            });

        }

        PopupWindowHelper.showAsDropDown(SearchGoodsShowActivity.this, popSort, view);
    }

    private void sortSelected(boolean sortFlag, boolean sale, String price, boolean screen) {
        btnSort.setSelected(sortFlag);
        ivSort.setSelected(sortFlag);
        btnSale.setSelected(sale);
        if (price.equals("")) { //未选中
            tvPrice.setSelected(false);
            ivPriceUp.setSelected(false);
            ivPriceDown.setSelected(false);
        } else if (price.equals("price_asc")) { //升序
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
        btnScreen.setSelected(screen);
        ivScreen.setSelected(screen);
    }

    //筛选页回调
    public void onEventMainThread(SortString values) {
        selectValue = values.getKeyword();
        LogHelper.e("筛选传参", selectValue);
        page = 1;
        loadGoods();
    }

}
