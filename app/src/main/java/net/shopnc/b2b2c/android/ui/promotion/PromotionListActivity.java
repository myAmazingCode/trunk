package net.shopnc.b2b2c.android.ui.promotion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.adapter.PromotionListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.MyRepoList;
import net.shopnc.b2b2c.android.bean.PromotionSearch;
import net.shopnc.b2b2c.android.common.PopupWindowHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.EndLessOnScrollListener;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.good.SearchGoodActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class PromotionListActivity extends BaseActivity implements PromotionListAdapter.OnItemSelectedListener, PromotionGoodsScreenDialog.OnConditionConfirmedListener {

    @Bind(R.id.tvSearchText)
    TextView tvSearchText;
    @Bind(R.id.llCategory)
    LinearLayout mLlCategory;
    @Bind(R.id.tvSort)
    TextView mTvSort;
    @Bind(R.id.ivSort)
    ImageView mIvSort;
    @Bind(R.id.llSort)
    LinearLayout mLlSort;
    @Bind(R.id.tvSale)
    TextView mTvSale;
    @Bind(R.id.tvScreen)
    TextView mTvScreen;
    @Bind(R.id.ivScreen)
    ImageView mIvScreen;
    @Bind(R.id.llScreen)
    LinearLayout mLlScreen;

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
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.btnMyRepo)
    Button mBtnExist;
    @Bind(R.id.btnAdd)
    Button mBtnAdd;
    @Bind(R.id.layoutSearch)
    RelativeLayout mLayoutSearch;
    @Bind(R.id.tvGoodsInfo)
    TextView mTvGoodsInfo;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.ivPriceUp)
    ImageView ivPriceUp;
    @Bind(R.id.ivPriceDown)
    ImageView ivPriceDown;
    @Bind(R.id.rlPrice)
    RelativeLayout rlPrice;
    private int page = 1;
    private String keyword = "";
    private String sort = "commission_desc";//排序方式
    private PopupWindow mSortWindow;
    private View lastSelectedView;
    private List<PromotionSearch.DatasBean.GoodsListBean> mDatas = new ArrayList<>();
    private PromotionListAdapter mAdapter;
    private boolean mHasMore;

    private int choosedNum = 0;
    private int totalNum = 200;//
    private boolean isSwitch;//切换到销量或者筛选排序
    private RRecyclerAdapter<PromotionSearch.DatasBean.GoodsListBean> mSelectedListAdapter;
    private PromotionGoodsScreenDialog mPromotionGoodsScreenDialog;//筛选dialog
    private List<PromotionSearch.DatasBean.FilterBean.CategoryListBean> mCategoryList;//筛选的分类列表
    private List<PromotionSearch.DatasBean.FilterBean.AttributeListBean> mAttributeList;//属性
    private List<PromotionSearch.DatasBean.FilterBean.BrandListBean> mBrandList;//品牌
    private String mScreenCondition;//筛选过滤参数
    private String mBrandParam = "";//品牌或分类参数
    private List<PromotionSearch.DatasBean.GoodsListBean> mSelectedGoodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTvSort.setSelected(true);
        mIvSort.setSelected(true);


        initBrandParam();

        loadData();
        setLayoutEmpty();

        mTvGoodsInfo.setText(Html.fromHtml("已选取<font color=\"#FF0000\"><b>0</b></font>/" + totalNum + "个商品"));
        initRV();

    }

    //分类或者品牌参数
    private void initBrandParam() {
        int cat = getIntent().getIntExtra("cat", -1);
        int brand = getIntent().getIntExtra("brand", -1);
        Log.d(TAG, "onCreate: brand = " + brand + ",cat = " + cat);

        if (cat != -1) {
            mBrandParam += "&cat=" + cat;
        }

        if (brand != -1) {
            mBrandParam += "&brand=" + brand;
        }
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

        mAdapter = new PromotionListAdapter(this, mDatas);
        mAdapter.setOnItemSelectedListener(this);
        mRv.setAdapter(mAdapter);
    }

    @OnClick({R.id.tvSearchText, R.id.llCategory, R.id.llSort, R.id.tvSale, R.id.llScreen,
            R.id.tvGoodsInfo, R.id.btnMyRepo, R.id.btnAdd, R.id.rlPrice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlPrice:
                if (tvPrice.isSelected()) {
                    if (ivPriceUp.isSelected()) { //已是升序  变降序
                        sortSelected("price_desc");
                    } else {//已是降序  变升序
                        sortSelected("price_asc");
                    }
                } else {//第一次点击为升序显示
                    sortSelected("price_asc");
                }
                mDatas.clear();
                mAdapter.notifyDataSetChanged();
                page = 1;
                loadData();
                break;
            case R.id.btnMyRepo:
                PromotionInfoHelper.getJoinInfo(this, new PromotionInfoHelper.OnEventListener() {
                    @Override
                    public void onEvent() {
                        startActivity(new Intent(PromotionListActivity.this, MyRepoActivity.class));
                    }
                });
                break;
            case R.id.btnAdd:
                PromotionInfoHelper.getJoinInfo(this, new PromotionInfoHelper.OnEventListener() {
                    @Override
                    public void onEvent() {
                        mSelectedGoodsList = mAdapter.getSelectedList();
                        if (mSelectedGoodsList == null || mSelectedGoodsList.size() == 0) return;
                        showAddPopWindow();
                    }
                });
                break;
            case R.id.tvGoodsInfo:
                showSelectedPopWindow();
                break;
            case R.id.tvSearchText:
                Intent intent = new Intent(this, SearchGoodActivity.class);
                intent.putExtra("promotion", -1);
                startActivityForResult(intent, 100);
                break;
            case R.id.llCategory:
                //分类选择。使用sp标记，&bug
                SharedPreferences sharedPreferences = getSharedPreferences("promotion", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("promotion", -1);
                editor.apply();

                sendBroadcast(new Intent(ConstantBroadCast.SHOW_ClASSIFY_URL));
                Intent it = new Intent(this, MainFragmentManager.class);
                startActivity(it);
                finish();
                break;
            case R.id.llSort://选择排序方式
                mTvSort.setSelected(true);
                mIvSort.setSelected(true);
                mTvScreen.setSelected(false);
                mIvScreen.setSelected(false);
                mTvSale.setSelected(false);

                tvPrice.setSelected(false);
                ivPriceDown.setSelected(false);
                ivPriceUp.setSelected(false);
                showSortPopWindow(view);
                break;
            case R.id.tvSale://销量排序
                sort = "sale_desc";
                mDatas.clear();
                mAdapter.notifyDataSetChanged();
                page = 1;
                loadData();

                isSwitch = true;
                mTvSort.setSelected(false);
                mIvSort.setSelected(false);
                mTvSale.setSelected(true);
                mTvScreen.setSelected(false);
                mIvScreen.setSelected(false);

                tvPrice.setSelected(false);
                ivPriceDown.setSelected(false);
                ivPriceUp.setSelected(false);
                break;
            case R.id.llScreen://筛选
                isSwitch = true;
                mTvSort.setSelected(false);
                mIvSort.setSelected(false);
                mTvSale.setSelected(false);
                mTvScreen.setSelected(true);
                mIvScreen.setSelected(true);

                tvPrice.setSelected(false);
                ivPriceDown.setSelected(false);
                ivPriceUp.setSelected(false);

                showScreenDialog();
                break;

        }
    }

    private void sortSelected(String price) {
        mTvSort.setSelected(false);
        mIvSort.setSelected(false);
        mTvSale.setSelected(false);
        mTvScreen.setSelected(false);
        mIvScreen.setSelected(false);
        tvPrice.setSelected(true);

        if (price.equals("price_asc")) { //升序
            ivPriceUp.setSelected(true);
            ivPriceDown.setSelected(false);
        } else if (price.equals("price_desc")) {  //price_desc  降序
            ivPriceUp.setSelected(false);
            ivPriceDown.setSelected(true);
        }
        sort = price;
    }

    private void showAddPopWindow() {

        Log.d(TAG, "showAddPopWindow: mSelectedGoodsList = " + mSelectedGoodsList);
        View rootView = LayoutInflater.from(context).inflate(R.layout.promotion_selected_goods_popwindow, null);

        final PopupWindow selectedGoodsWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, 1200, true);
        selectedGoodsWindow.setTouchable(true);
        selectedGoodsWindow.setOutsideTouchable(true);
        selectedGoodsWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        selectedGoodsWindow.setAnimationStyle(R.style.AnimBottom);


        selectedGoodsWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1.0f;
                getWindow().setAttributes(attributes);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });


        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (selectedGoodsWindow.isShowing()) {
                    selectedGoodsWindow.dismiss();
                }
                return true;
            }
        });

        final TextView tvEmpty = rootView.findViewById(R.id.tvEmpty);
        final TextView tvTitle = rootView.findViewById(R.id.tvTitle);
        tvTitle.setText("选择选品库分组");
        rootView.findViewById(R.id.ivClear).setVisibility(View.GONE);
        final RecyclerView rvSelected = rootView.findViewById(R.id.rvSelected);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
//        rvSelected.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,20,getResources().getColor(R.color.nc_bg_dk)));
        rvSelected.setLayoutManager(layoutManager);

        //获取选品库列表
        String url = ConstantUrl.URL_API + "/member/distributor/favorites/list";
        OkHttpUtil.postAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response, int i) {
                Log.d(TAG, "onResponse: response = " + response);

                if (200 == JsonUtil.toInteger(response, "code")) {
                    MyRepoList myRepoList = new Gson().fromJson(response, MyRepoList.class);
                    List<MyRepoList.DatasBean.FavListBean> favList = myRepoList.getDatas().getFavList();
                    //列表数据
                    if (favList != null && favList.size() != 0) {
                        initAdapter(favList, rvSelected, selectedGoodsWindow);

                    } else {
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText("您还没有相关选品库");
                        rvSelected.setVisibility(View.GONE);
                    }
                } else {
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, new OkHttpUtil.Param("token", application.getToken()));

        selectedGoodsWindow.showAsDropDown(mTvGoodsInfo, 0, 20);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private void initAdapter(final List<MyRepoList.DatasBean.FavListBean> favList, RecyclerView rvSelected, final PopupWindow selectedGoodsWindow) {
        RRecyclerAdapter<MyRepoList.DatasBean.FavListBean> adapter = new RRecyclerAdapter<MyRepoList.DatasBean.FavListBean>(this, R.layout.repo_grid_item, favList) {
            @Override
            public void convert(RecyclerHolder holder, final MyRepoList.DatasBean.FavListBean favListBean) {
                holder.setText(R.id.tvRepoName, favListBean.getDistributorFavoritesName())
                        .setText(R.id.tvGoodsNum, "商品数量：" + favListBean.getGoodsCount());
                List<MyRepoList.DatasBean.FavListBean.DistributionGoodsVoListBean> goodsVoList = favListBean.getDistributionGoodsVoList();
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        save(favListBean.getDistributorFavoritesId());
                        selectedGoodsWindow.dismiss();
                    }
                });

                for (int i = 0; i < goodsVoList.size(); i++) {
                    MyRepoList.DatasBean.FavListBean.DistributionGoodsVoListBean bean = goodsVoList.get(i);
                    if (i == 0) {
                        ImageView view1 = holder.getView(R.id.ivGoodPic1);
                        LoadImage.loadRemoteImg(PromotionListActivity.this, view1, bean.getImageSrc());
                    }

                    if (i == 1) {
                        ImageView view1 = holder.getView(R.id.ivGoodPic2);
                        LoadImage.loadRemoteImg(PromotionListActivity.this, view1, bean.getImageSrc());
                    }

                    if (i == 2) {
                        ImageView view1 = holder.getView(R.id.ivGoodPic3);
                        LoadImage.loadRemoteImg(PromotionListActivity.this, view1, bean.getImageSrc());
                    }

                    if (i == 3) {
                        ImageView view1 = holder.getView(R.id.ivGoodPic4);
                        LoadImage.loadRemoteImg(PromotionListActivity.this, view1, bean.getImageSrc());
                    }
                }
            }
        };


        rvSelected.setAdapter(adapter);
    }

    private void save(String favoritesId) {
        String url = ConstantUrl.URL_API + "/member/distributor/goods/addmulti";
        String commonIds = getGoodsCommonId();
        Map<String, String> map = new HashMap<>();
        map.put("token", application.getToken());
        map.put("favoritesId", favoritesId);
        map.put("commonIds", commonIds);

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int c) {
                Log.d(TAG, "onResponse: response = " + response);
                if (JsonUtil.toInteger(response, "code") == 200) {
                    TToast.showShort(application, "操作成功");
                    for (int i = 0; i < mDatas.size(); i++) {
                        PromotionSearch.DatasBean.GoodsListBean goodsListBean = mDatas.get(i);
                        goodsListBean.setMarked(false);
                    }
                    mAdapter.notifyDataSetChanged();
                    mSelectedGoodsList.clear();
                    mTvGoodsInfo.setText(Html.fromHtml("已选取<font color=\"#FF0000\"><b>0</b></font>/" + totalNum + "个商品"));
                } else {
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, map);
    }

    private String getGoodsCommonId() {
        int size = mSelectedGoodsList.size();
        if (size == 1) return mSelectedGoodsList.get(0).getCommonId();

        String val = "";
        for (int i = 0; i < size; i++) {
            PromotionSearch.DatasBean.GoodsListBean goodsListBean = mSelectedGoodsList.get(i);
            val += goodsListBean.getCommonId();
            if (i != size - 1) {
                val += ",";
            }
        }
        return val;
    }


    private void showScreenDialog() {
        if (mPromotionGoodsScreenDialog == null) {
            mPromotionGoodsScreenDialog = new PromotionGoodsScreenDialog(this);
            mPromotionGoodsScreenDialog.setOnConditionConfirmedListener(this);
        }

        mPromotionGoodsScreenDialog.setCategoryList(mCategoryList);
        mPromotionGoodsScreenDialog.setBrandList(mBrandList);
        mPromotionGoodsScreenDialog.setAttributeList(mAttributeList);

        mPromotionGoodsScreenDialog.show();
    }

    private void showSelectedPopWindow() {

        View rootView = LayoutInflater.from(context).inflate(R.layout.promotion_selected_goods_popwindow, null);


        final PopupWindow selectedGoodsWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, 1200, true);
        selectedGoodsWindow.setTouchable(true);
        selectedGoodsWindow.setOutsideTouchable(true);
        selectedGoodsWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        selectedGoodsWindow.setAnimationStyle(R.style.AnimBottom);


        selectedGoodsWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1.0f;
                getWindow().setAttributes(attributes);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });


        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (selectedGoodsWindow.isShowing()) {
                    selectedGoodsWindow.dismiss();
                }
                return true;
            }
        });

        final TextView tvEmpty = rootView.findViewById(R.id.tvEmpty);
        final RecyclerView rvSelected = rootView.findViewById(R.id.rvSelected);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSelected.setLayoutManager(layoutManager);
        mSelectedGoodsList = mAdapter.getSelectedList();
        //删除
        mSelectedListAdapter = new RRecyclerAdapter<PromotionSearch.DatasBean.GoodsListBean>(this, R.layout.promotion_selected_list_item) {

            @Override
            public void convert(RecyclerHolder holder, final PromotionSearch.DatasBean.GoodsListBean goodsListBean) {
                final TextView tvName = holder.getView(R.id.tvGoodName);
                tvName.setText(goodsListBean.getGoodsName());
                TextView tvRate = holder.getView(R.id.tvRate);
                String commissionRate = goodsListBean.getCommissionRate();
                tvRate.setText(String.format("佣金比例：%s", commissionRate) + "%");
                holder.setText(R.id.tvGoodPrice, String.format("￥%s", goodsListBean.getAppPriceMin()));
                ImageView ivGoodPic = holder.getView(R.id.ivGoodPic);
                LoadImage.loadRemoteImg(context, ivGoodPic, goodsListBean.getImageSrc());

                holder.getView(R.id.tvDelete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //删除
                        goodsListBean.setMarked(false);
                        mSelectedGoodsList.remove(goodsListBean);
                        mSelectedListAdapter.notifyDataSetChanged();

                        choosedNum--;
                        mTvGoodsInfo.setText(Html.fromHtml("已选取<font color=\"#FF0000\"><b>" + choosedNum + "</b></font>/" + totalNum + "个商品"));
                        if (mSelectedGoodsList.size() == 0) {
                            tvEmpty.setVisibility(View.VISIBLE);
                            rvSelected.setVisibility(View.GONE);
                        }
                    }
                });
            }
        };
        rvSelected.setAdapter(mSelectedListAdapter);
        mSelectedListAdapter.setDatas(mSelectedGoodsList);
        mSelectedListAdapter.notifyDataSetChanged();


        rootView.findViewById(R.id.ivClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < mSelectedGoodsList.size(); i++) {
                    PromotionSearch.DatasBean.GoodsListBean goodsListBean = mSelectedGoodsList.get(i);
                    goodsListBean.setMarked(false);
                }

                //清空
                if (mSelectedGoodsList != null) {
                    mSelectedGoodsList.clear();
                }
                mSelectedListAdapter.notifyDataSetChanged();
                tvEmpty.setVisibility(View.VISIBLE);
                rvSelected.setVisibility(View.GONE);

                choosedNum = 0;
                mTvGoodsInfo.setText(Html.fromHtml("已选取<font color=\"#FF0000\"><b>" + choosedNum + "</b></font>/" + totalNum + "个商品"));
            }
        });

        if (mAdapter.getSelectedList() == null || mAdapter.getSelectedList().size() == 0) {
            //空数据
            tvEmpty.setVisibility(View.VISIBLE);
            rvSelected.setVisibility(View.GONE);
        }


        selectedGoodsWindow.showAsDropDown(mTvGoodsInfo, 0, 20);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    private void showSortPopWindow(View view) {
        if (mSortWindow == null) {

            View viewPopSort = LayoutInflater.from(context).inflate(R.layout.promotion_search_goods_sort, null);
            mSortWindow = new PopupWindow(viewPopSort, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            mSortWindow.setTouchable(true);
            mSortWindow.setOutsideTouchable(true);
            mSortWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            viewPopSort.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (mSortWindow != null && mSortWindow.isShowing()) {
                        mSortWindow.dismiss();
                    }
                    return true;
                }
            });

            //选择综合排序
            final TextView defaultSort = viewPopSort.findViewById(R.id.defaultSort);
            defaultSort.setSelected(true);
            lastSelectedView = defaultSort;
            initSortEvent(defaultSort, "综合", "commission_desc");

            //评论由多到少排序
            final TextView tvEvaluateCount = viewPopSort.findViewById(R.id.tvEvaluateCount);
            initSortEvent(tvEvaluateCount, "评论", "comment_desc");

            //收入比率
            final TextView incomeRate = viewPopSort.findViewById(R.id.incomeRate);
            initSortEvent(incomeRate, "收入比率", "income_desc");

            //推广量高到低
            final TextView promotionDesc = viewPopSort.findViewById(R.id.promotionDesc);
            initSortEvent(promotionDesc, "推广量", "diffusion_desc");


            //支付佣金高到低
            final TextView payDesc = viewPopSort.findViewById(R.id.payDesc);
            initSortEvent(payDesc, "支出佣金", "commission_desc");
        }

        PopupWindowHelper.showAsDropDown(this, mSortWindow, view);
    }

    private void initSortEvent(final TextView view, final String desc, final String sortKey) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSwitch && view.isSelected()) {
                    mSortWindow.dismiss();
                    return;
                }
                lastSelectedView.setSelected(false);
                view.setSelected(true);
                lastSelectedView = view;
                mTvSort.setText(desc);
                sort = sortKey;
                mDatas.clear();
                mAdapter.notifyDataSetChanged();
                page = 1;
                loadData();
                mSortWindow.dismiss();
                isSwitch = false;
            }
        });
    }

    public static final String TAG = PromotionListActivity.class.getSimpleName();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            keyword = data.getStringExtra("keyword");
            Log.d(TAG, "onActivityResult: keyword = " + keyword);
            tvSearchText.setText(keyword);

            mDatas.clear();
            mAdapter.notifyDataSetChanged();
            page = 1;
            sort = "";
            loadData();
        }
    }

    private void loadData() {
        String url = ConstantUrl.URL_API + "/distribution/search?client=app&page=" +
                page + "&pageSize=10" + "&sort=" + sort;

        //过滤条件
        if (!TextUtils.isEmpty(mScreenCondition)) {
            url += mScreenCondition;
        }

        //品牌或分类
        if (!TextUtils.isEmpty(mBrandParam)) {
            url += mBrandParam;
        }

        //搜索
        if (!TextUtils.isEmpty(keyword)) {
            url += "&keyword=" + keyword;
        }

        Log.d(TAG, "loadData: url = " + url);
        OkHttpUtil.getAsyn(this, url, new BeanCallback<String>() {


            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response, int i) {
                Log.d(TAG, "onResponse: response = " + response);

                if (JsonUtil.toInteger(response, "code") == 200) {
//                    PromotionSearch promotionSearch = JsonUtil.toBean(response, PromotionSearch.class);
                    PromotionSearch promotionSearch = new Gson().fromJson(response, PromotionSearch.class);
                    PromotionSearch.DatasBean datas = promotionSearch.getDatas();
//                    mTotalPage = Integer.parseInt(datas.getPageEntity().getTotalPage());
                    mHasMore = datas.getPageEntity().isHasMore();
                    PromotionSearch.DatasBean.FilterBean filter = datas.getFilter();
                    mCategoryList = filter.getCategoryList();
                    mBrandList = filter.getBrandList();
                    mAttributeList = filter.getAttributeList();
                    List<PromotionSearch.DatasBean.GoodsListBean> goodsList = datas.getGoodsList();

                    if (goodsList != null && goodsList.size() != 0) {
                        mDatas.addAll(goodsList);
                        mAdapter.setDatas(mDatas);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        //内容为空时候显示
                        mRv.setVisibility(View.GONE);
                        layoutEmpty.setVisibility(View.VISIBLE);
                    }

                } else {
                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        });
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


    @Override
    protected void setView() {
        setContentView(R.layout.activity_promotion_list);
    }

    public void onBackClick(View view) {
        finish();
    }

    @Override
    public void onItemSelected(int count) {
        choosedNum++;
        mTvGoodsInfo.setText(Html.fromHtml("已选取<font color=\"#FF0000\"><b>" + choosedNum + "</b></font>/" + totalNum + "个商品"));
    }


    @Override
    public void onConditionConfirmed(String val) {
        mScreenCondition = val;
        mDatas.clear();
        mAdapter.notifyDataSetChanged();
        page = 1;
        loadData();
    }
}
