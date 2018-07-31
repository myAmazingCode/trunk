package net.shopnc.b2b2c.android.ui.good;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.SearchStoresAdapter;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.dialog.ChooseDialog;
import net.shopnc.b2b2c.android.ui.home.HomeLoadDataHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Global;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchStoresShowActivity extends Activity {

    @Bind(R.id.etSearchText)
    TextView etSearchText;
    @Bind(R.id.imgClassify)
    ImageView imgClassify;
    @Bind(R.id.imgMenu)
    ImageView imgMenu;
    @Bind(R.id.rvGoods)
    XRecyclerView rvGoods;
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
    @Bind(R.id.tvDefault)
    TextView tvDefault;
    @Bind(R.id.tvSale)
    TextView tvSale;
    @Bind(R.id.tvFavirate)
    TextView tvFavirate;
    @Bind(R.id.btnBack)
    ImageView btnBack;
    @Bind(R.id.title_bar_search)
    ImageView titleBarSearch;
    @Bind(R.id.vhintMenu)
    View vhintMenu;

    private Context context;
    private MyShopApplication application;

    private String sort = "";
    private String keyword;
    private int page = 1;
    private SearchStoresAdapter adapter;
    private List<StoreInfo> storeInfos;
    private PageEntity pageEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stores_show);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        context = this;
        application = MyShopApplication.getInstance();
        keyword = getIntent().getStringExtra("keyword");
        etSearchText.setText(keyword);
        LoadImage.loadLocalGreyImg(this, imgClassify, R.drawable.stiore_categroy_b);
        LoadImage.loadLocalGreyImg(this, imgMenu, R.drawable.nc_icon_more_dot);
        setLayoutEmpty();
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCartCount();
    }

    public void setLayoutEmpty() {
        imgEmptyLogo.setImageResource(R.drawable.search_w);
        tvEmptyTitle.setText("没有找到任何相关信息");
        tvEmptyBody.setText("选择或搜索其它店铺分类/名称...");
        btnChoose.setVisibility(View.VISIBLE);
        btnChoose.setText("重新选择");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        sort = "";
        setSortSelected(true, false, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvGoods.setLayoutManager(layoutManager);
        rvGoods.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvGoods.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvGoods.setLoadingListener(listener);

        adapter = new SearchStoresAdapter(context);
        rvGoods.setAdapter(adapter);
        storeInfos = new ArrayList<>();
        requestData();
        requestCartCount();

    }

    /**
     * 实现上下拉刷新方法接口
     */
    private XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {

        @Override
        public void onRefresh() {
            page = 1;
            requestData();
        }

        @Override
        public void onLoadMore() {
            ++page;
            requestData();
        }
    };

    private void requestData() {
        String url = ConstantUrl.URL_SEARCH_STORE + "?keyword=" + keyword + "&sort=" + sort + "&page=" + page;

        OkHttpUtil.getAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                List<StoreInfo> storeList = JsonUtil.toBean(data, "storeList", new TypeToken<ArrayList<StoreInfo>>() {
                }.getType());
                pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (storeList == null || pageEntity == null || layoutEmpty == null) {
                    return;
                }

                if (pageEntity != null && pageEntity.isHasMore()) {
                    rvGoods.setLoadingMoreEnabled(true);
                } else {
                    rvGoods.setLoadingMoreEnabled(false);
                }

                if (page == 1) {
                    storeInfos.clear();
                    rvGoods.scrollToPosition(0);
                }

                //隐藏加载动画
                rvGoods.refreshComplete();
                rvGoods.loadMoreComplete();

                if (storeList != null && !storeList.isEmpty()) {
                    rvGoods.setVisibility(View.VISIBLE);
                    layoutEmpty.setVisibility(View.GONE);
                    storeInfos.addAll(storeList);
                    adapter.setDatas(storeInfos);
                    adapter.notifyDataSetChanged();
                } else {
                    rvGoods.setVisibility(View.GONE);
                    layoutEmpty.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @OnClick({R.id.btnBack, R.id.etSearchText, R.id.imgClassify, R.id.imgMenu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.etSearchText:
                finish();
                break;
            case R.id.imgClassify:
                HomeLoadDataHelper.doClick(context, "category", null);
                break;
            case R.id.imgMenu:
                ChooseDialog chooseDialog = new ChooseDialog(context, application, imgMenu);
                break;
        }
    }

    @OnClick({R.id.tvDefault, R.id.tvSale, R.id.tvFavirate})
    public void onSortClick(View view) {
        switch (view.getId()) {
            case R.id.tvDefault:
                if (tvDefault.isSelected())
                    return;
                setSortSelected(true, false, false);
                sort = "";
                break;
            case R.id.tvSale:
                if (tvSale.isSelected())
                    return;
                setSortSelected(false, true, false);
                sort = "sale_desc";
                break;
            case R.id.tvFavirate:
                if (tvFavirate.isSelected())
                    return;
                setSortSelected(false, false, true);
                sort = "collect_desc";
                break;
        }
        page = 1;
        requestData();
    }

    private void setSortSelected(boolean defaultFlag, boolean saleFlag, boolean favirateFlag) {
        tvDefault.setSelected(defaultFlag);
        tvSale.setSelected(saleFlag);
        tvFavirate.setSelected(favirateFlag);
    }

    /**
     * 获取购物车商品种类数量
     */
    private void requestCartCount() {
        Map<String, String> map = new HashMap<>();
        map.put("cartData", "");
        map.put("clientType", "android");
        map.put("token", application.getToken());
        OkHttpUtil.postAsyn(this, ConstantUrl.URL_CART_AMOUNT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                String cartCount = JsonUtil.toString(data, "cartCount");
                Global.isCart = !TextUtils.isEmpty(cartCount) && !cartCount.equals("0");
                requestUnreadMessageCount();
            }
        }, map);
    }

    /**
     * 获取站内未读消息数量
     */
    private void requestUnreadMessageCount() {
        Map<String, String> map = new HashMap<>();
        map.put("clientType", "android");
        map.put("token", application.getToken());
        OkHttpUtil.postAsyn(this, ConstantUrl.URL_UNREAD_MESSAGE_COUNT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                String cartCount = JsonUtil.toString(data, "count");
                Global.isUnreadMessage = !TextUtils.isEmpty(cartCount) && !cartCount.equals("0");

                if (Global.isCart || Global.isUnreadMessage) {
                    vhintMenu.setVisibility(View.VISIBLE);
                } else {
                    vhintMenu.setVisibility(View.INVISIBLE);
                }

            }
        }, map);
    }

}
