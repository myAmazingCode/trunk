package net.shopnc.b2b2c.android.ui.good;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.TabStopSpan;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.GoodsPageAdapter;
import net.shopnc.b2b2c.android.adapter.VoucherAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.bean.BaseData;
import net.shopnc.b2b2c.android.bean.BookBean;
import net.shopnc.b2b2c.android.bean.BundlingList;
import net.shopnc.b2b2c.android.bean.ComboGoodsVo;
import net.shopnc.b2b2c.android.bean.Conform;
import net.shopnc.b2b2c.android.bean.ConsultList;
import net.shopnc.b2b2c.android.bean.ContractVo;
import net.shopnc.b2b2c.android.bean.Discount;
import net.shopnc.b2b2c.android.bean.EvaluateStoreVo;
import net.shopnc.b2b2c.android.bean.GoodDetailVo;
import net.shopnc.b2b2c.android.bean.GoodGift;
import net.shopnc.b2b2c.android.bean.Goods;
import net.shopnc.b2b2c.android.bean.GoodsDetailBean;
import net.shopnc.b2b2c.android.bean.GoodsEvaluate;
import net.shopnc.b2b2c.android.bean.GoodsImage;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.bean.GroupBean;
import net.shopnc.b2b2c.android.bean.MobileBodyVoList;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.bean.VoucherBean;
import net.shopnc.b2b2c.android.bean.VoucherList;
import net.shopnc.b2b2c.android.bean.VoucherTemplate;
import net.shopnc.b2b2c.android.circlelibrary.ImageCycleView;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.CountDownHelper;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.ComboGoodsView;
import net.shopnc.b2b2c.android.custom.GiftItemView;
import net.shopnc.b2b2c.android.custom.LineBreakLayout;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.custom.MyScrollView;
import net.shopnc.b2b2c.android.custom.VoucherTemplateView;
import net.shopnc.b2b2c.android.custom.dialog.AddressDialog;
import net.shopnc.b2b2c.android.custom.dialog.ClickBigImageDialog;
import net.shopnc.b2b2c.android.custom.dialog.GoodDetailsSpecDialog;
import net.shopnc.b2b2c.android.ui.group.GroupDetailActivity;
import net.shopnc.b2b2c.android.ui.im.IMDetailsActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class GoodDetailsFragment extends BaseFragment {


    private List<Address> addressList;
    @Bind(R.id.bannerGoodImages)
    ImageCycleView bannerGoodImages;
    @Bind(R.id.tvGoodsNameID)
    TextView tvGoodsNameID;
    @Bind(R.id.tvGoodsJingleID)
    TextView tvGoodsJingleID;
    @Bind(R.id.rlContainer)
    RelativeLayout rlContainer;

    @Bind(R.id.svBottomDetails)
    MyScrollView svBottomDetails;
    @Bind({R.id.llDownScroll})
    LinearLayout llDownScroll;
    @Bind({R.id.llBottom})
    LinearLayout llBottom;


    @Bind(R.id.tvSaleLimit)
    TextView tvSaleLimit;
    @Bind(R.id.tvSalePrice)
    TextView tvSalePrice;
    @Bind(R.id.llBatch0PriceModel)
    LinearLayout llBatch0PriceModel;
    @Bind(R.id.goodsSaleNumBegin1)
    TextView goodsSaleNumBegin1;
    @Bind(R.id.goodsSaleNumBeginPrice1)
    TextView goodsSaleNumBeginPrice1;
    @Bind(R.id.model2First)
    LinearLayout model2First;
    @Bind(R.id.fblVoucher)
    FlexboxLayout fblVoucher;

    @Bind(R.id.llGoodsQuery)
    LinearLayout llGoodsQuery;
    @Bind(R.id.tvQueryContent)
    TextView tvQueryContent;
    @Bind(R.id.goodsSaleNumBegin2)
    TextView goodsSaleNumBegin2;
    @Bind(R.id.goodsSaleNumBeginPrice2)
    TextView goodsSaleNumBeginPrice2;
    @Bind(R.id.model2Second)
    LinearLayout model2Second;

    @Bind(R.id.llBindList)
    LinearLayout llBindList;
    @Bind(R.id.llBindContainer)
    LinearLayout llBindContainer;
    @Bind(R.id.llComboGoodsVoList)
    LinearLayout llComboGoodsVoList;
    @Bind(R.id.tvCombCount)
    TextView tvCombCount;


    @Bind(R.id.llPullUp)
    LinearLayout llPullUp;
    @Bind(R.id.tvBindCount)
    TextView tvBindCount;
    @Bind(R.id.llComboContainer)
    LinearLayout llComboContainer;
    @Bind(R.id.goodsSaleNumBegin0)
    TextView goodsSaleNumBegin0;
    @Bind(R.id.goodsSaleNumBeginPrice0)
    TextView goodsSaleNumBeginPrice0;
    @Bind(R.id.model2Third)
    LinearLayout model2Third;
    @Bind(R.id.model2)
    LinearLayout model2;
    @Bind(R.id.tvMemberAddress)
    TextView tvMemberAddress;
    @Bind(R.id.tvWeight)
    TextView tvWeight;
    @Bind(R.id.tvVol)
    TextView tvVol;
    @Bind(R.id.tvSaleNum)
    TextView tvSaleNum;
    @Bind(R.id.tvSelectSpec)
    TextView tvSelectSpec;
    @Bind(R.id.llSpecChoose)
    LinearLayout llSpecChoose;

    @Bind(R.id.vp)
    ViewPager mViewPager;
    @Bind(R.id.llIndicators)
    LinearLayout llIndicators;
    @Bind(R.id.llStoreHot)
    LinearLayout llStoreHot;

    @Bind(R.id.vp2)
    ViewPager mViewPager2;
    @Bind(R.id.llIndicators2)
    LinearLayout llIndicators2;
    @Bind(R.id.llStoreRec)
    LinearLayout llStoreRec;

    @Bind(R.id.tvStoreName)
    TextView tvStoreName;
    @Bind(R.id.ivStoreImg)
    ImageView ivStoreImg;
    @Bind(R.id.tvGoodsLevel)
    TextView tvGoodsLevel;
    @Bind(R.id.tvServiceLevel)
    TextView tvServiceLevel;
    @Bind(R.id.tvDeliveryLevel)
    TextView tvDeliveryLevel;

    @Bind(R.id.tvStoreLevel)
    TextView tvStoreLevel;
    @Bind(R.id.tvGoodsCount)
    TextView tvGoodsCount;
    @Bind(R.id.tvLikeCount)
    TextView tvLikeCount;

    @Bind(R.id.tvRank)
    TextView tvRank;
    @Bind(R.id.tvRec)
    TextView tvRec;

    //商品详情tabs
    @Bind(R.id.tvIntro)
    TextView tvIntro;
    @Bind(R.id.tvSpec)
    TextView tvSpec;
    @Bind(R.id.dividerSpec)
    View dividerSpec;
    @Bind(R.id.dividerProtection)
    View dividerProtection;
    @Bind(R.id.dividerCommitment)
    View dividerCommitment;
    @Bind(R.id.tvProtection)
    TextView tvProtection;
    @Bind(R.id.tvCommitment)
    TextView tvCommitment;

    //    @Bind(R.id.llStore)
//    LinearLayout llStore;
    //    @Bind(R.id.tvStoreDescPoint)
//    TextView tvStoreDescPoint;
//    @Bind(R.id.tvStoreServicePoint)
//    TextView tvStoreServicePoint;
//    @Bind(R.id.tvStoreDeliveryPoint)
//    TextView tvStoreDeliveryPoint;
    @Bind(R.id.lblContract)
    LineBreakLayout lblContract;
    @Bind(R.id.llSellerPromise)
    LinearLayout llSellerPromise;
    @Bind(R.id.tvGoodEvaluatePercent)
    TextView tvGoodEvaluatePercent;
    @Bind(R.id.tvEvaluateCount)
    TextView tvEvaluateCount;
    @Bind(R.id.tvEvaDesc)
    TextView tvEvaDesc;
    @Bind(R.id.llGoodEvaluate)
    LinearLayout llGoodEvaluate;
    @Bind(R.id.llGetVoucher)
    LinearLayout llGetVoucher;
    @Bind(R.id.llGoodDiscount)
    LinearLayout llGoodDiscount;

    @Bind(R.id.tvDiscount)
    TextView tvDiscount;
    @Bind(R.id.llDiscount)
    LinearLayout llDiscount;
    @Bind(R.id.tvGroup)
    TextView tvGroup;
    @Bind(R.id.llGroup)
    LinearLayout llGroup;
    @Bind(R.id.tvGiftDesc)
    TextView tvGiftDesc;
    @Bind(R.id.llGift)
    LinearLayout llGift;

    //促销折扣
    @Bind(R.id.llXDiscount)
    LinearLayout llXDiscount;
    @Bind(R.id.tvXDiscount)
    TextView tvXDiscount;

    @Bind(R.id.rvEvaluate)
    MyListView rvEvaluate;
    @Bind(R.id.wvImg)
    WebView wvImg;
    @Bind(R.id.wvParam)
    WebView wvParam;
    @Bind(R.id.wvProtection)
    WebView wvProtection;
    @Bind(R.id.wvPromise)
    WebView wvPromise;

    @Bind(R.id.tvPreTime)
    TextView tvPreTime;
    @Bind(R.id.llPreSell)
    LinearLayout llPreSell;
    @Bind(R.id.tvPreFlag)
    TextView tvPreFlag;
    @Bind(R.id.god_pc_detail)
    TextView godPcDetail;
    @Bind(R.id.llTryInfo)
    LinearLayout llTryInfo;
    @Bind(R.id.tvTryInfo)
    TextView tvTryInfo;

    @Bind(R.id.tvLoading)
    TextView mTvLoading;
    @Bind(R.id.myScrollView)
    MyScrollView myScrollView;
    @Bind(R.id.scrollContainer)
    LinearLayout scrollContainer;

    /*****************限时折扣区域*****************/
    @Bind(R.id.tvDiscountPrice)
    TextView mTvDiscountPrice;
    @Bind(R.id.tvDiscountPriceFormat)
    TextView mTvDiscountPriceFormat;
    @Bind(R.id.tvOriginPrice)
    TextView mTvOriginPrice;
    @Bind(R.id.rlDiscountMain)
    RelativeLayout mRlDiscountMain;
    @Bind(R.id.tvStateDesc)
    TextView mTvStateDesc;
    @Bind(R.id.tvDay)
    TextView mTvDay;
    @Bind(R.id.tvHour)
    TextView mTvHour;
    @Bind(R.id.tvMinute)
    TextView mTvMinute;
    @Bind(R.id.tvSec)
    TextView mTvSec;
    @Bind(R.id.llDiscountModule)
    LinearLayout mLlDiscountModule;

    /*******价格区域*****/
    @Bind(R.id.tvPrice1)
    TextView tvPrice1;
    @Bind(R.id.tvDesc1)
    TextView tvDesc1;
    @Bind(R.id.tvTime1)
    TextView tvTime1;
    @Bind(R.id.llLimitDiscount)
    LinearLayout llLimitDiscount;

    @Bind(R.id.rlPresellMain)
    RelativeLayout rlPresellMain;
    @Bind(R.id.tvPresellPrice)
    TextView tvPresellPrice;
    @Bind(R.id.tvPresellDesc)
    TextView tvPresellDesc;
    @Bind(R.id.tvPresellPriceFormat)
    TextView tvPresellPriceFormat;
    @Bind(R.id.llAllPresell)
    LinearLayout llAllPresell;
    @Bind(R.id.tvPrice2)
    TextView tvPrice2;
    @Bind(R.id.tvDesc2)
    TextView tvDesc2;
    @Bind(R.id.tvPeroid2)
    TextView tvPeroid2;


    @Bind(R.id.llPayPresell)
    LinearLayout llPayPresell;
    @Bind(R.id.tvPayPrice3)
    TextView tvPayPrice3;
    @Bind(R.id.tvPayDesc3)
    TextView tvPayDesc3;
    @Bind(R.id.tvPrice3)
    TextView tvPrice3;
    @Bind(R.id.tvDesc3)
    TextView tvDesc3;
    @Bind(R.id.tvPeroid3)
    TextView tvPeroid3;

    /*****************限时折扣区域*****************/

    private String data;

    private String moneyRmb;
    private int moneyRmbLength;

    private int commonId;
    private int goodsId;
    private int trysType;
    private GoodDetailVo goodDetailVo;
    private StoreInfo storeInfo;
    private EvaluateStoreVo evaluateStoreVo;
    private ArrayList<ContractVo> contractVos;
    private ArrayList<VoucherBean> voucherTemplateList;
    private ArrayList<VoucherList.VoucherTemplateListBean> voucherTemplateListAll = new ArrayList<>();
    private ArrayList<GoodsVo> hotGoodsVos;
    private ArrayList<GoodsVo> storeRankingsList;
    private List<Conform> conforms;
    private List<GoodsEvaluate> evaluateGoodsVoList;

    private HashMap<Integer, PreGoods> preGoodsMap;
    private Goods selectedGoods;
    private int selectedNum = 1;
    private BookBean selectedBook = null;

    private String areaText;
    private int areaId;

    private List<Goods> goodsList;

    //    private GoodsDetailsHotGridAdapter hotGridAdapter;
    private SimpleEvaluateAdapter simpleEvaluateAdapter;

    //    private GoodGiftDialog giftDialog;
    private List<BundlingList> bundlingList;
    private List<ComboGoodsVo> comboGoodsVoList;
    private List<ConsultList> consultList;
    private GroupBean mGroupBean;
    private List<GoodGift> mGifts;
    private String mIntroHtml;
    private String mProtectionHtml;
    private String mCommitmentHtml;
    private String mSpecHtml;
    private String mEvaluateGoodsTotal;
    private GoodsPageAdapter mHotAdapter;
    private GoodsPageAdapter mRecAdapter;

    //上拉部分
    private Rect rect = null;//记录scrollView包裹组件的位置
    private int fullScroll;//scrollView滚动到底部时ScrollView的scrollY值
    private float mDeltaY;//（上拉模块）拖动的距离
    private int downScrollY;//开始拖动的ScrollView的scrollY值
    private float startY;//开始拖动的MotionEvent的y值
    private LinearLayout.LayoutParams mLayoutParams;

    //下拉部分
    private float startY2;
    private float downScrollY2;
    private float mDeltaY2;
    private boolean touched;//是否触摸
    protected boolean hasDefaultAddr;//是否有默认地址,默认无

    public static GoodDetailsFragment newInstance(int commonId) {
        GoodDetailsFragment fragment = new GoodDetailsFragment();
        Bundle b = new Bundle();
        b.putInt("commonId", commonId);
        fragment.setArguments(b);
        return fragment;
    }

    public static GoodDetailsFragment newInstance(int commonId, int goodsId, int trysType) {
        GoodDetailsFragment fragment = new GoodDetailsFragment();
        Bundle b = new Bundle();
        b.putInt("commonId", commonId);
        b.putInt("goodsId", goodsId);
        b.putInt("trysType", trysType);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gooddetails, container, false);
        ButterKnife.bind(this, view);
        commonId = getArguments().getInt(GoodDetailsActivity.COMMONID);
        goodsId = getArguments().getInt("goodsId");
        trysType = getArguments().getInt("trysType");

        EventBus.getDefault().register(this);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        moneyRmbLength = moneyRmb.length();
        preGoodsMap = new HashMap<>();
        initHotGoodsAdapter();
//        loadGood();

        //滚动到顶部的滑动事件
        initPullUp();
        //下拉隐藏详情
        initPullDown();
        //加载商品介绍、规格参数、售后保障、商家承诺
        loadGoodsDetail();


        String token = application.getToken();
        if (TextUtils.isEmpty(token)) {
            //未登录状态下的默认值
            areaText = "北京 北京市 东城区";
            areaId = 36;
            requestFreight();
        } else {
            OkHttpUtil.postAsyn(getActivity(), ConstantUrl.URL_ADDRESS_LIST, new BeanCallback<String>() {

                @Override
                public void response(String data) {
                    addressList = JsonUtil.toBean(data, "addressList", new TypeToken<List<Address>>() {
                    }.getType());

                    if (addressList != null && addressList.size() != 0) {
                        for (int i = 0; i < addressList.size(); i++) {
                            Address address = addressList.get(i);
                            //如果有默认地址
                            if (address.getIsDefault() == 1) {
                                areaText = address.getAreaInfo() + " " + address.getAddress();
                                areaId = address.getAreaId2();
                                hasDefaultAddr = true;
                                break;
                            }
                        }

                        //若没有默认地址，读取第一个
                        if (!hasDefaultAddr) {
                            Address address = addressList.get(0);
                            areaText = address.getAreaInfo() + " " + address.getAddress();
                            areaId = address.getAreaId2();
                        }
                    } else {
                        //无地址
                        areaText = "北京 北京市 东城区";
                        areaId = 36;
                    }
                    //请求运费
                    requestFreight();
                }
            }, new OkHttpUtil.Param("token", token));
        }

        addGoodsBrowse();

        bannerGoodImages.setFocusable(true);
        bannerGoodImages.setFocusableInTouchMode(true);
        bannerGoodImages.requestFocus();
        bannerGoodImages.requestFocusFromTouch();
        return view;
    }

    /*******************监听详情的隐藏和显示*******************/

    private OnGoodsDetailsListener mOnGoodsDetailsListener;

    public void setOnGoodsDetailsListener(OnGoodsDetailsListener onGoodsDetailsListener) {
        mOnGoodsDetailsListener = onGoodsDetailsListener;
    }

    public interface OnGoodsDetailsListener {
        void onShow(boolean showDetails);

        void onArrivalNotice(boolean notice);
    }

    /**
     * Activity中隐藏详情
     */
    public void hideDetails() {
        //显示上部
        myScrollView.setVisibility(View.VISIBLE);
        myScrollView.smoothScrollTo(0, fullScroll);//滚动到底部
        //隐藏详情
        llBottom.setVisibility(View.GONE);
//        svBottomDetails.setVisibility(View.GONE);
    }

    //下拉隐藏详情
    private void initPullDown() {
        mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        svBottomDetails.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        break;

                    case MotionEvent.ACTION_UP:

                        if (mDeltaY2 > 120 && downScrollY2 == 0) {
                            //显示上部
                            myScrollView.setVisibility(View.VISIBLE);
                            myScrollView.smoothScrollTo(0, fullScroll);//滚动到底部
                            //隐藏详情
                            llBottom.setVisibility(View.GONE);
//                            svBottomDetails.setVisibility(View.GONE);
                            mOnGoodsDetailsListener.onShow(false);
                        }

                        mLayoutParams.setMargins(0, (int) getResources().getDimension(R.dimen.pulldown_head_margin), 0, 0);
                        llDownScroll.setLayoutParams(mLayoutParams);

                        //重置
                        mDeltaY2 = 0;
                        downScrollY2 = 0;
                        startY2 = 0;
                        touched = false;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!touched) {
                            startY2 = event.getY();
                            downScrollY2 = svBottomDetails.getScrollY();
                        }
                        touched = true;
                        mDeltaY2 = 0.5f * (event.getY() - startY2);

                        if (downScrollY2 == 0 && mDeltaY2 > 0) {
                            int top = (int) (-120 + mDeltaY2);
                            mLayoutParams.setMargins(0, top, 0, 0);
                            llDownScroll.setLayoutParams(mLayoutParams);
                        }

                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    /**
     * 上拉查看详情
     */
    private void initPullUp() {

        myScrollView.setOnScrollToBottomLintener(new MyScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                if (isBottom) {
                    fullScroll = myScrollView.getScrollY();
                    Log.d(TAG, "onScrollBottomListener: scrollY = " + fullScroll);
                }
            }
        });

        myScrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        //记录按下时的Y值
                        startY = event.getY();
                        downScrollY = myScrollView.getScrollY();
                        Log.d(TAG, "onTouch: startY = " + startY + " , downScrollY = " + downScrollY);
                        if (rect == null) {
                            rect = new Rect(scrollContainer.getLeft(), scrollContainer.getTop(), scrollContainer.getRight(), scrollContainer.getBottom());
                        }

                        break;

                    case MotionEvent.ACTION_UP:

                        //拖动距离大于120
                        if (Math.abs(mDeltaY) > 120) {
                            Log.d(TAG, "onTouch: 显示详情了");
                            //显示详情
                            llBottom.setVisibility(View.VISIBLE);
//                            svBottomDetails.setVisibility(View.VISIBLE);
                            svBottomDetails.smoothScrollTo(0, 0);

                            //隐藏上部
                            myScrollView.setVisibility(View.GONE);
                            mOnGoodsDetailsListener.onShow(true);
                        }

                        // 恢复原有高度
                        if (rect != null) {
                            scrollContainer.layout(rect.left, rect.top, rect.right, rect.bottom);
                            Log.d(TAG, "onTouch: 松手了");
                        }
                        //重置
                        mDeltaY = 0;
                        downScrollY = 0;
                        startY = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (downScrollY != 0 && fullScroll > 0 && downScrollY >= fullScroll - 20) {
                            mDeltaY = 0.5f * (event.getY() - startY);
                            if (rect != null) {
                                scrollContainer.layout(rect.left, (int) (rect.top + mDeltaY), rect.right, (int) (rect.bottom + mDeltaY));
                            }
                        }

                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }


    private void loadVouchers() {
        if (TextUtils.isEmpty(application.getToken())) return;

        OkHttpUtil.postAsyn(getActivity(), ConstantUrl.URL_USER_GOT_VOUCHER, new BeanCallback<String>() {
                    @Override
                    public void response(String data) {
                        VoucherList voucherList = new Gson().fromJson(data, VoucherList.class);
                        List<VoucherList.VoucherTemplateListBean> voucherTemplateList = voucherList.getVoucherTemplateList();
                        if (voucherTemplateList != null) {
                            voucherTemplateListAll.addAll(voucherTemplateList);
                        }
                    }
                }, new OkHttpUtil.Param("storeId", "" + storeInfo.getStoreId()),
                new OkHttpUtil.Param("token", application.getToken()));
    }

    //商品介绍、规格参数、售后保障、商家承诺
    @OnClick({R.id.tvIntro, R.id.tvSpec, R.id.tvCommitment, R.id.tvProtection,})
    public void tabClick(View view) {
        switch (view.getId()) {
            case R.id.tvIntro:
                showWebView(wvImg, mIntroHtml);
                break;
            case R.id.tvSpec:
                showWebView(wvParam, mSpecHtml);
                break;
            case R.id.tvCommitment:
                showWebView(wvPromise, mCommitmentHtml);
                break;
            case R.id.tvProtection:
                showWebView(wvProtection, mProtectionHtml);
                break;
        }
        setTabStatus(view);
    }

    /**
     * 显示当前隐藏其他
     *
     * @param webView
     * @param data
     */
    private void showWebView(WebView webView, String data) {
        wvImg.setVisibility(View.GONE);
        wvParam.setVisibility(View.GONE);
        wvProtection.setVisibility(View.GONE);
        wvPromise.setVisibility(View.GONE);

        webView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
        webView.setVisibility(View.VISIBLE);
    }

    /**
     * 设置选中状态
     *
     * @param v
     */
    private void setTabStatus(View v) {
        tvIntro.setSelected(false);
        tvSpec.setSelected(false);
        tvProtection.setSelected(false);
        tvCommitment.setSelected(false);

        v.setSelected(true);
    }

    //商品详情等
    private void loadGoodsDetail() {
        String url = ConstantUrl.URL_API + "/goods/extend/" + commonId;
        OkHttpUtil.getAsyn(getActivity(), url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                GoodsDetailBean goodsDetailBean = JsonUtil.toBean(data, GoodsDetailBean.class);
                //售后保障
                mProtectionHtml = goodsDetailBean.getProtection();
                if (TextUtils.isEmpty(mProtectionHtml)) {
                    tvProtection.setVisibility(View.GONE);
                    dividerProtection.setVisibility(View.GONE);
                }
                //商家承诺
                mCommitmentHtml = goodsDetailBean.getCommitment();
                if (TextUtils.isEmpty(mCommitmentHtml)) {
                    tvCommitment.setVisibility(View.GONE);
                    dividerCommitment.setVisibility(View.GONE);
                }
                //商品介绍
                List<GoodsDetailBean.GoodsMobileBodyVoListBean> goodsMobileBodyVoList = goodsDetailBean.getGoodsMobileBodyVoList();
                mIntroHtml = "";
                if (goodsMobileBodyVoList != null) {
                    for (int i = 0; i < goodsMobileBodyVoList.size(); i++) {
                        GoodsDetailBean.GoodsMobileBodyVoListBean bean = goodsMobileBodyVoList.get(i);
                        String value = bean.getValue();
                        mIntroHtml += "<img src=\"" + value + "\" width=\"100%\"/><br>";
                    }
                } else {
                    tvIntro.setVisibility(View.GONE);
                }

                showWebView(wvImg, mIntroHtml);
                tvIntro.setSelected(true);
                //规格参数
                List<GoodsDetailBean.GoodsAttrVoListBean> goodsAttrVoList = goodsDetailBean.getGoodsAttrVoList();
                mSpecHtml = "";
                if (goodsAttrVoList != null && goodsAttrVoList.size() != 0) {
                    mSpecHtml += "<table border=\"1px\" width=\"100%\" style=\"margin-bottom:" + ScreenUtil.px2dip(context, ScreenUtil.getScreenSize(context).y) + "px;border-collapse: collapse\">";
                    for (int i = 0; i < goodsAttrVoList.size(); i++) {
                        GoodsDetailBean.GoodsAttrVoListBean goodsAttrVoListBean = goodsAttrVoList.get(i);
                        String name = goodsAttrVoListBean.getName();
                        String value = goodsAttrVoListBean.getValue();
                        mSpecHtml += "<tr>";
                        mSpecHtml += "<td>";
                        mSpecHtml += name;
                        mSpecHtml += "</td>";

                        mSpecHtml += "<td>";
                        mSpecHtml += value;
                        mSpecHtml += "</td>";

                        mSpecHtml += "</tr>";
                    }
                    mSpecHtml += "</table>";
                    Log.d(TAG, "response: mSpecHtml = " + mSpecHtml);
                } else {
                    tvSpec.setVisibility(View.GONE);
                    dividerSpec.setVisibility(View.GONE);
                }

            }
        });
    }

    private void addGoodsBrowse() {
        if (Common.isEmpty(application.getToken())) {
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("commonId", commonId + "");
        OkHttpUtil.postAsyn(getActivity(), ConstantUrl.URL_MINE_FOOTPRINT, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }
        }, params);

    }


    private void initHotGoodsAdapter() {

        mHotAdapter = new GoodsPageAdapter(getActivity(), llIndicators);
        mRecAdapter = new GoodsPageAdapter(getActivity(), llIndicators2);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager2.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mHotAdapter);
        mViewPager2.setAdapter(mRecAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                List<View> indicators = ((GoodsPageAdapter) mViewPager.getAdapter()).getIndicators();
                for (int i = 0; i < indicators.size(); i++) {
                    indicators.get(i).setSelected(i == position);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                List<View> indicators = ((GoodsPageAdapter) mViewPager2.getAdapter()).getIndicators();
                for (int i = 0; i < indicators.size(); i++) {
                    indicators.get(i).setSelected(i == position);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void loadGood() {
        tvRec.setSelected(true);
        mTvLoading.setVisibility(View.GONE);
        myScrollView.setVisibility(View.VISIBLE);
        goodDetailVo = JsonUtil.toBean(data, "goodsDetail", new TypeToken<GoodDetailVo>() {
        }.getType());

        String voucherTemplateListString = JsonUtil.toString(data, "goodsDetail");
        voucherTemplateList = JsonUtil.toBean(voucherTemplateListString, "voucherTemplateList", new TypeToken<ArrayList<VoucherBean>>() {
        }.getType());

        //评价数量
        mEvaluateGoodsTotal = JsonUtil.toString(data, "evaluateGoodsTotal");
        String evaluateCount = JsonUtil.toString(data, "evaluateCount");


        if (!TextUtils.isEmpty(evaluateCount)) {
            String s1 = JsonUtil.toString(evaluateCount, "1");
            String s2 = JsonUtil.toString(evaluateCount, "2");
            String s3 = JsonUtil.toString(evaluateCount, "3");
            String s4 = JsonUtil.toString(evaluateCount, "4");
            EventBus.getDefault().post(new GoodBusBean(GoodBusBean.EVALUATES_COUNT,
                    new String[]{mEvaluateGoodsTotal, s1, s2, s3, s4}));
        }

        VoucherBean voucher = JsonUtil.toBean(data, "goodsDetail", "voucherTemplate", new TypeToken<VoucherBean>() {
        }.getType());//优惠券

        storeInfo = JsonUtil.toBean(data, "storeInfo", new TypeToken<StoreInfo>() {
        }.getType());

        //加载优惠券列表
        loadVouchers();


        evaluateStoreVo = JsonUtil.toBean(data, "evaluateStoreVo", new TypeToken<EvaluateStoreVo>() {
        }.getType());

        //服务承诺转移至商品详情内
        contractVos = JsonUtil.toBean(JsonUtil.toString(data, "goodsDetail"), "contractVoList", new TypeToken<ArrayList<ContractVo>>() {
        }.getType());

        //为你推荐和排行榜
        hotGoodsVos = JsonUtil.toBean(data, "storeCommendList", new TypeToken<ArrayList<GoodsVo>>() {
        }.getType());

        storeRankingsList = JsonUtil.toBean(data, "storeRankingsList", new TypeToken<ArrayList<GoodsVo>>() {
        }.getType());

        mHotAdapter.setDatas(hotGoodsVos);
        mHotAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(0);
        if (mHotAdapter.getIndicators().size() > 0) {
            mHotAdapter.getIndicators().get(0).setSelected(true);
        }

        mRecAdapter.setDatas(storeRankingsList);
        mRecAdapter.notifyDataSetChanged();
        mViewPager2.setCurrentItem(0);
        if (mRecAdapter.getIndicators().size() > 0) {
            mRecAdapter.getIndicators().get(0).setSelected(true);
        }


        VoucherTemplate voucherTemplate = JsonUtil.toBean(data, "goodsDetail", "voucherTemplate", new TypeToken<VoucherTemplate>() {
        }.getType());//优惠券


        conforms = JsonUtil.toBean(data, "goodsDetail", "conformList", new TypeToken<ArrayList<Conform>>() {
        }.getType());//折扣

        mGroupBean = JsonUtil.toBean(data, "groups", GroupBean.class);
//        List<MobileBodyVoList> lists = JsonUtil.toBean(data, "goodsDetail", "mobileBodyVoList", new TypeToken<List<MobileBodyVoList>>() {
//        }.getType());//手机端详情
        evaluateGoodsVoList = JsonUtil.toBean(data, "evaluateGoodsVoList", new TypeToken<ArrayList<GoodsEvaluate>>() {
        }.getType());//价

        String toString = JsonUtil.toString(data, "bundlingList");
        if (!"[]".equals(toString)) {
            bundlingList = JsonUtil.toBean(data, "bundlingList", new TypeToken<ArrayList<BundlingList>>() {
            }.getType());//优惠套装
        }

        String toStr = JsonUtil.toString(data, "comboGoodsVoList");
        if (!"[]".equals(toStr)) {
            comboGoodsVoList = JsonUtil.toBean(data, "comboGoodsVoList", new TypeToken<ArrayList<ComboGoodsVo>>() {
            }.getType());//推荐组合
        }

        consultList = JsonUtil.toBean(data, "consultList", new TypeToken<ArrayList<ConsultList>>() {
        }.getType());//优惠套装

        //商品列表,默认显示第一条商品
        goodsList = goodDetailVo.getGoodsList();

        //默认初始化的规格选中商品
        if (goodsId == 0) {
            selectedGoods = goodsList.get(0);
        } else {
            for (Goods goods : goodDetailVo.getGoodsList()) {
                if (goods.getGoodsId() == goodsId) {
                    selectedGoods = goods;
                    break;
                }
            }
        }

        //试用资格显示
        if (goodsId == 0) {
            llTryInfo.setVisibility(View.GONE);
        } else {
            llTryInfo.setVisibility(View.VISIBLE);
            if (trysType == 1) {
                tvTryInfo.setText("只需付邮费即可购买1件试用商品");
            } else {
                tvTryInfo.setText("下单并提交试用报告可获得优惠券");
            }
        }

//        //促销中的折扣显示
//        if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 1 && goodDetailVo.getDiscount() != null) {
//            llXDiscount.setVisibility(View.VISIBLE);
//            tvXDiscount.setText(goodDetailVo.getDiscount().getDiscountRate() + "折");
//        } else {
//            llXDiscount.setVisibility(View.GONE);
//        }

//        if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 2) { //全款预售
//            llPreSell.setVisibility(View.VISIBLE);
//            tvPreFlag.setText("全款预售");
//            tvPreTime.setText("发货时间：" + goodDetailVo.getPromotionEndTime().substring(0, 10));
//        } else {
//            llPreSell.setVisibility(View.GONE);
//        }
//        if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 3) {//定金预售
//            selectedBook = goodDetailVo.getBook();
//        } else if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 2) {
//        } else {
//            llPreSell.setVisibility(View.GONE);
//        }

        //优惠券显示
        if (voucherTemplateList != null && voucherTemplateList.size() != 0) {
            for (int i = 0; i < voucherTemplateList.size(); i++) {
                VoucherBean voucherBean = voucherTemplateList.get(i);
                VoucherTemplateView view = new VoucherTemplateView(getActivity());
                view.setText("满" + voucherBean.getLimitAmount() + "减" + voucherBean.getTemplatePrice());
                fblVoucher.addView(view);
            }
            llGetVoucher.setVisibility(View.VISIBLE);
        } else {
            llGetVoucher.setVisibility(View.GONE);
        }

        //显示满优惠
        if (conforms != null && !conforms.isEmpty()) {
            String show = "";
            for (int i = 0; i < conforms.size(); i++) {
                Conform conform = conforms.get(i);
                if (i != 0) {
                    show += "，";
                }
                show += conform.getContentCartRule();
            }
            tvDiscount.setText(show);
            llDiscount.setVisibility(View.VISIBLE);
        } else {
            llDiscount.setVisibility(View.GONE);
        }

        //显示拼团
        if (mGroupBean != null) {
            tvGroup.setText("支付开团并邀请" + (mGroupBean.getGroupRequireNum() - 1) + "人参团，如人数不足自动退款");
            llGroup.setVisibility(View.VISIBLE);
        } else {
            llGroup.setVisibility(View.GONE);
        }

        if (mGroupBean != null || (conforms != null && conforms.size() != 0) || (mGifts != null && mGifts.size() > 0)) {
            llGoodDiscount.setVisibility(View.VISIBLE);
        } else {
            llGoodDiscount.setVisibility(View.GONE);
        }

        //赠品显示
        showGoodsGift();

        //规格选择框的显示
        if (goodDetailVo.getSpecJson().isEmpty()) {   //无规格值
            llSpecChoose.setVisibility(View.GONE);
        } else {
            llSpecChoose.setVisibility(View.VISIBLE);
        }

        showColorIdImages(selectedGoods.getColorId());
        tvSelectSpec.setText(selectedGoods.getGoodsFullSpecs());

        //默认显示其他商品信息：名称、价格等信息
        bindViewDatas();


        //显示限时折扣、预售等
        showPromotion();
    }

    /**
     * 最新限时折扣显示规则
     * promotionType：1限时折扣，2全款预售，3定金预售
     */
    private void showPromotion() {
        if (goodDetailVo.getAppUsable() == 1) {

            //隐藏普通状态价格显示方式
            llBatch0PriceModel.setVisibility(View.GONE);
            mLlDiscountModule.setVisibility(View.VISIBLE);

            //原价
            float originPrice = goodDetailVo.getBatchPrice0().floatValue();
            //折扣价
            float floatValue = goodDetailVo.getAppPrice0().floatValue();
//            String appPriceMin = String.valueOf(floatValue);
            String appPriceMin = String.format("%.2f", floatValue);
            String[] split = appPriceMin.split("\\.");

            int promotionType = goodDetailVo.getPromotionType();
            //显示限时折扣
            Discount discount = goodDetailVo.getDiscount();
            if (promotionType == 1 && discount != null) {
                llLimitDiscount.setVisibility(View.VISIBLE);
                mRlDiscountMain.setVisibility(View.VISIBLE);
                String app = goodDetailVo.getApp();
                String type = goodDetailVo.getPromotionCountDownTimeType();
                if (!TextUtils.isEmpty(app) && "1".equals(app)) {
                    mTvDiscountPrice.setText(split[0]);
                    mTvDiscountPriceFormat.setText("." + split[1]);
                    tvPrice1.setText(moneyRmb + appPriceMin);
                    String discountExplain = discount.getDiscountExplain();
                    tvDesc1.setText(TextUtils.isEmpty(discountExplain) ? "暂无描述" : discountExplain);
                    tvTime1.setText(discount.getStartTime() + " 至 " + discount.getEndTime() + "截止");
                    //正在进行
                    if ("ongoing".equals(type)) {
                        mTvOriginPrice.setText(String.format(moneyRmb + "%.2f", originPrice));
                        mTvOriginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        mTvStateDesc.setText("距活动结束");
                        //计时器
                        CountDownHelper.initCountDown(mTvDay, mTvHour, mTvMinute, mTvSec, discount.getPromotionCountDownTime());
                        //未开始
                    } else if ("future".equals(type)) {
                        mTvStateDesc.setText("距活动开始还有");
                        mTvStateDesc.setTextColor(getResources().getColor(R.color.nc_text_dark));
                        //改为灰底
                        mTvHour.setBackground(getResources().getDrawable(R.drawable.discount_text_gray_bg));
                        mTvMinute.setBackground(getResources().getDrawable(R.drawable.discount_text_gray_bg));
                        mTvSec.setBackground(getResources().getDrawable(R.drawable.discount_text_gray_bg));

                        mTvOriginPrice.setVisibility(View.GONE);
                        mRlDiscountMain.setBackground(getResources().getDrawable(R.drawable.discount_main_gradient_bg_blue));
                        CountDownHelper.initCountDown(mTvDay, mTvHour, mTvMinute, mTvSec, discount.getPromotionCountDownTime());
                    }
                }
            }
            //全款预售
            if (promotionType == 2) {
                CountDownHelper.initCountDown(mTvDay, mTvHour, mTvMinute, mTvSec, goodDetailVo.getPromotionCountDownTime());
                rlPresellMain.setVisibility(View.VISIBLE);
                llAllPresell.setVisibility(View.VISIBLE);
                llAllPresell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPresellDialog();
                    }
                });
                tvPresellDesc.setText("预售价");
                tvPrice2.setText(moneyRmb + appPriceMin);
                tvPresellPrice.setText(split[0]);
                tvPresellPriceFormat.setText("." + split[1]);
                mTvStateDesc.setText("距预售活动结束");
                String[] split1 = goodDetailVo.getPromotionEndTime().split(" ");
                tvDesc2.setText("(付款后于" + split1[0] + "日发货)");
                tvPeroid2.setText("1.付款 - 2.发货");
            }

            float engageNum = floatValue / 10;
            String[] split2 = String.format("%.2f", engageNum).split("\\.");
            //定金预售
            if (promotionType == 3) {
                CountDownHelper.initCountDown(mTvDay, mTvHour, mTvMinute, mTvSec, goodDetailVo.getPromotionCountDownTime());
                mTvStateDesc.setText("距支付定金结束");
                rlPresellMain.setVisibility(View.VISIBLE);
                tvPresellDesc.setText("定金");
                tvPresellPrice.setText(split2[0]);
                tvPresellPriceFormat.setText("." + split2[1]);
                llPayPresell.setVisibility(View.VISIBLE);
                llPayPresell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPresellDialog();
                    }
                });
                tvPayPrice3.setText(moneyRmb + String.format("%.2f", engageNum));
                tvPrice3.setText(moneyRmb + String.format("%.2f", floatValue - engageNum));
                tvPayDesc3.setText("(" + goodDetailVo.getPromotionEndTime() + "截止)");
                tvDesc3.setText("(定金支付后72小时之内)");
                tvPeroid3.setText("1.付定金 - 2.付尾款 - 3.发货");
            }
        } else {
            mLlDiscountModule.setVisibility(View.GONE);
        }
    }


    /**
     * 预售信息弹窗
     */
    private void showPresellDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.presell_popupwindow, null);
        TextView tvGroup = view.findViewById(R.id.tvGroup);

        Dialog dialog = new Dialog(getActivity(), R.style.CommonDialog);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

    }

    private void initWebView(List<MobileBodyVoList> lists) {
        String head = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title></title>" +
                "<style type=\"text/css\">" +
                "img{" +
                "width: 98%;" +
                "text-align: center;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>";
        String textHead = "<p style=\"font-size: 40px;\">";
        String text = "";

        String textEnd = "</p>\n";
        String imgHead = "<div style=\"text-align: center;\"><img src=\"";
        String img = "";
        String imgEnd = "\" /></div>";
        String end = "</body></html>";
        for (MobileBodyVoList mobileBodyVoList : lists) {
            if (mobileBodyVoList.getType().equals("image")) {
                img += imgHead + mobileBodyVoList.getValue() + imgEnd;
            } else {
                text += textHead + mobileBodyVoList.getValue() + textEnd;
            }
        }
        String s = head + text + img + end;
        wvImg.loadDataWithBaseURL(null, s, "text/html", "utf-8", null);
        wvImg.getSettings().setJavaScriptEnabled(true);
        wvImg.getSettings().setLoadWithOverviewMode(true);
        wvImg.getSettings().setUseWideViewPort(true);
        wvImg.setWebChromeClient(new WebChromeClient());
    }

    //显示轮播图
    private void setBanViews(final ArrayList<String> urlList) {
        RelativeLayout.LayoutParams cParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ScreenUtil.getScreenSize(context).y / 2);
        bannerGoodImages.setLayoutParams(cParams);
        ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
                //    点击显示大图滑动查看
                ClickBigImageDialog dialog = new ClickBigImageDialog(context, urlList, position);
                dialog.show();
            }

            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                LoadImage.loadRemoteImg(context, imageView, imageURL);
            }
        };
        bannerGoodImages.setImageResources(urlList, mAdCycleViewListener, false);
        bannerGoodImages.pushImageCycle();
    }


    //根据colorId得到该图片数据集并显示
    private void showColorIdImages(int colorId) {
        List<GoodsImage> imagesList = goodDetailVo.getGoodsImageList();
        ArrayList<String> images = new ArrayList<>();
        for (GoodsImage goodsImage : imagesList) {
            if (goodsImage.getColorId() == colorId) {
                images.add(goodsImage.getImageSrc());
            }
        }
        setBanViews(images);
    }


    @OnClick(R.id.god_pc_detail)
    public void pcClick() {
        EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodBrowseTurn));
    }

    //绑定商品数据
    private void bindViewDatas() {
        if (storeInfo.getIsOwnShop() == 1) {
            tvGoodsNameID.setText(getGoodNameSpannableString(goodDetailVo.getGoodsName()), TextView.BufferType.SPANNABLE);   //自营 + 商品名称
        } else {
            tvGoodsNameID.setText(goodDetailVo.getGoodsName());   //商品名称
        }

        //商品卖点的显示
        if (Common.isEmpty(goodDetailVo.getJingle())) {
            tvGoodsJingleID.setVisibility(View.GONE);
        } else {
            tvGoodsJingleID.setVisibility(View.VISIBLE);
            tvGoodsJingleID.setText(goodDetailVo.getJingle());
        }

        tvSaleNum.setText("销量：" + goodDetailVo.getGoodsSaleNum() + goodDetailVo.getUnitName());   //销量

        tvMemberAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressDialog dialog = new AddressDialog(getActivity());
                dialog.show();
                dialog.setOnAreaInfoConfirmedListener(new AddressDialog.OnAreaInfoConfirmedListener() {

                    @Override
                    public void onAreaInfoConfirmed(int id1, int id2, int id3, String info) {
                        areaId = id2;
                        areaText = info;
                        requestFreight();
                    }
                });
            }
        });          //地区选择

        showGoodsPrice();  //阶梯价格段显示

        //显示优惠套装
        if (bundlingList != null && bundlingList.size() != 0) {
            showBundlingList();
            tvBindCount.setText("共" + bundlingList.size() + "款");
            llBindList.setVisibility(View.VISIBLE);
        } else {
            llBindList.setVisibility(View.GONE);
        }

        //显示推荐组合
        if (comboGoodsVoList != null && comboGoodsVoList.size() != 0) {
            showComboGoodsList();
            tvCombCount.setText("共" + comboGoodsVoList.size() + "款");
            llComboGoodsVoList.setVisibility(View.VISIBLE);
        } else {
            llComboGoodsVoList.setVisibility(View.GONE);
        }

        //显示商品咨询
        if (consultList != null && consultList.size() != 0) {
            String consultContent = consultList.get(0).getConsultContent();
            tvQueryContent.setText(consultContent);
        }

        //评价显示
        showEvaluate();

//        showShopViewData();   //店铺信息显示
        showShopViewDataNew();   //店铺信息显示

        showContract();     //店铺保障服务

    }

    //显示推荐组合
    private void showComboGoodsList() {
        ComboGoodsVo comboGoodsVo = comboGoodsVoList.get(0);
        List<ComboGoodsVo.GoodsVoListBean> goodsVoList = comboGoodsVo.getGoodsVoList();
        //最多显示4个
        for (int i = 0; i < goodsVoList.size() && i < 4; i++) {
            ComboGoodsVo.GoodsVoListBean goodsVoListBean = goodsVoList.get(i);
            String imageSrc = goodsVoListBean.getImageSrc();
            ComboGoodsView comboGoodsView = new ComboGoodsView(getActivity());
            comboGoodsView.setImage(imageSrc, i != 0);
            llComboContainer.addView(comboGoodsView);
        }

        //第一个不足4个时候，从第二个里面取
        if (llComboContainer.getChildCount() < 4) {
            //且有第二个
            if (comboGoodsVoList.size() > 1) {
                ComboGoodsVo comboGoodsVo1 = comboGoodsVoList.get(1);
                List<ComboGoodsVo.GoodsVoListBean> goodsVoList1 = comboGoodsVo1.getGoodsVoList();
                //最多4个
                for (int i = 0; llComboContainer.getChildCount() < 5 && i < goodsVoList1.size(); i++) {
                    ComboGoodsVo.GoodsVoListBean goodsVoListBean = goodsVoList1.get(i);
                    ComboGoodsView comboGoodsView = new ComboGoodsView(getActivity());
                    String imageSrc = goodsVoListBean.getImageSrc();
                    comboGoodsView.setImage(imageSrc, true);
                    llComboContainer.addView(comboGoodsView);
                }
            }
        }

    }

    //显示优惠套装
    private void showBundlingList() {
        List<BundlingList.GoodsCommonListBean> goodsCommonList = bundlingList.get(0).getGoodsCommonList();
        //最多显示4个
        for (int i = 0; i < goodsCommonList.size() & i < 4; i++) {
            BundlingList.GoodsCommonListBean goodsCommonListBean = goodsCommonList.get(i);
            String imageSrc = goodsCommonListBean.getImageSrc();
            ComboGoodsView comboGoodsView = new ComboGoodsView(getActivity());
            comboGoodsView.setImage(imageSrc, i != 0);
            llBindContainer.addView(comboGoodsView);
        }

    }

    private class SimpleEvaluateAdapter extends CommonAdapter<GoodsEvaluate> {
        public SimpleEvaluateAdapter(Context context) {
            super(context, R.layout.evaluate_item_simple);
        }

        @Override
        public void convert(ViewHolder holder, final GoodsEvaluate goodsEvaluate) {
            //星级显示
            LinearLayout llStar = holder.getView(R.id.llStar);
            llStar.removeAllViews();
            for (int i = 0; i < Integer.valueOf(goodsEvaluate.getScores()); i++) {
                AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.gooddetails_evaluate_star);
                llStar.addView(addViewHolder.getCustomView());
            }
            holder.setText(R.id.customerName, goodsEvaluate.getMemberName())
                    .setText(R.id.evaluateDate, goodsEvaluate.getEvaluateTimeStr())
                    .setText(R.id.evaluateText, goodsEvaluate.getEvaluateContent1())
                    .setText(R.id.goodSpec, goodsEvaluate.getGoodsFullSpecs());

            FlexboxLayout llImages = holder.getView(R.id.evaluateImages);
            if (goodsEvaluate.getImage1FullList().isEmpty()) {
                llImages.setVisibility(View.GONE);
            } else {
                llImages.setVisibility(View.VISIBLE);
                llImages.removeAllViews();
                ArrayList<String> imgList = new ArrayList<>();
                imgList.addAll(goodsEvaluate.getImage1FullList());
                for (final String s : imgList) {
                    AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.view_image_simple);
                    addViewHolder.setImage(R.id.ivImg, s);
                    addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //点击查看大图并设置当前位置
                            ClickBigImageDialog dialog = new ClickBigImageDialog(context, goodsEvaluate.getImage1FullList(), goodsEvaluate.getImage1FullList().indexOf(s));
                            dialog.show();
                        }
                    });
                    llImages.addView(addViewHolder.getCustomView());
                }
            }

        }
    }

    private void showEvaluate() {
        tvEvaDesc.setText("评价(" + mEvaluateGoodsTotal + ")");
        tvEvaluateCount.setText(goodDetailVo.getGoodsRate() + "%");
        if (evaluateGoodsVoList == null || evaluateGoodsVoList.isEmpty()) {
            rvEvaluate.setVisibility(View.GONE);
        } else {
            rvEvaluate.setVisibility(View.VISIBLE);
            simpleEvaluateAdapter = new SimpleEvaluateAdapter(context);
            rvEvaluate.setAdapter(simpleEvaluateAdapter);
            simpleEvaluateAdapter.setmDatas(evaluateGoodsVoList);
            simpleEvaluateAdapter.notifyDataSetChanged();
            View footer = LayoutInflater.from(getActivity()).inflate(R.layout.evaluation_list_footer, null);
            TextView tv = footer.findViewById(R.id.tv);
            tv.setText("全部评价(" + mEvaluateGoodsTotal + ")");
            footer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodEvaluate));
                }
            });
            rvEvaluate.addFooterView(footer);
        }
    }

    //请求运费信息并显示
    private void requestFreight() {
        HashMap<String, String> params = new HashMap<>();
        params.put("commonId", commonId + "");
        params.put("areaId2", areaId + "");
        params.put("buyNum", selectedNum + "");

        String url = ConstantUrl.URL_GOODS_FREIGHT;

        OkHttpUtil.postAsyn(getActivity(), url, new BeanCallback<String>() {


            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String resp, int i) {
                super.onResponse(resp, i);
                BaseData baseData = JsonUtil.getBaseData(resp);

                if (baseData != null && baseData.getCode() == 200 && tvWeight != null) {
                    tvWeight.setText(Html.fromHtml("重量&emsp<font color=\"#555559\">" + JsonUtil.toString(baseData.getDatas(), "freightWeight") + "kg</font>"));
                    tvVol.setText(Html.fromHtml("体积&emsp<font color=\"#555559\">" + JsonUtil.toString(baseData.getDatas(), "freightVolume") + "m3</font>"));
                    String s = "送至&emsp<font color=\"#555559\">" + areaText + "</font><br></br>";
                    if (JsonUtil.toInteger(baseData.getDatas(), "allowSend") == 1) {
                        s = s + "&emsp&emsp&emsp<font color=\"#ED5968\">现货</font>";
                        String freightAmount = JsonUtil.toString(baseData.getDatas(), "freightAmount");
                        if (freightAmount.equals("0")) {
                            s = s + "&nbsp<font color=\"#838383\">免运费</font>";
                        } else {
                            s = s + "&emsp<font color=\"#838383\">" + moneyRmb + ShopHelper.getPriceString(new BigDecimal(freightAmount)) + "</font>";
                        }
                        mOnGoodsDetailsListener.onArrivalNotice(false);
                    } else {
                        s = s + "&emsp&emsp&emsp<font color=\"#838383\">无货</font>";
                        mOnGoodsDetailsListener.onArrivalNotice(true);
                    }
                    tvMemberAddress.setText(Html.fromHtml(s));
                } else if (baseData.getCode() == 400) {
                    String s = "送至&emsp<font color=\"#222222\">" + areaText + "</font>";
                    s = s + "&emsp<font color=\"#ED5968\">无货</font>";
                    tvMemberAddress.setText(Html.fromHtml(s));
                    mOnGoodsDetailsListener.onArrivalNotice(true);
                }
            }
        }, params);
    }

    public static final String TAG = "GoodDetailsFragment";

    private void showContract() {
        if (null == contractVos || contractVos.size() == 0) {
            llSellerPromise.setVisibility(View.GONE);
        } else {
            llSellerPromise.setVisibility(View.VISIBLE);
            for (ContractVo contractVo : contractVos) {
                AddViewHolder holder = new AddViewHolder(context, R.layout.addviewholder_goodsdetails_contract_item);
                holder.setImage(R.id.ivIcon, contractVo.getIconUrl())
                        .setText(R.id.tvCtItemname, contractVo.getCtItemname());

                lblContract.addView(holder.getCustomView());
            }
        }
    }

//    private void showShopViewData() {
//        tvStoreName.setText(storeInfo.getStoreName());
//        int colorId = getResources().getColor(R.color.gooddetails_store_low);
//
//        if (evaluateStoreVo.getDesEvalArrow().equals("low")) {
//            tvStoreDescPoint.setTextColor(colorId);
//            tvStoreDescText.setBackgroundColor(colorId);
//        }
//        tvStoreDescPoint.setText(evaluateStoreVo.getStoreDesEval());
//        tvStoreDescText.setText(evaluateStoreVo.getDesEvalTitle());
//
//        if (evaluateStoreVo.getServiceEvalArrow().equals("low")) {
//            tvStoreServicePoint.setTextColor(colorId);
//            tvStoreServiceText.setBackgroundColor(colorId);
//        }
//        tvStoreServicePoint.setText(evaluateStoreVo.getStoreServiceEval());
//        tvStoreServiceText.setText(evaluateStoreVo.getServiceEvalTitle());
//
//        if (evaluateStoreVo.getDeliveryEvalArrow().equals("low")) {
//            tvStoreDeliveryPoint.setTextColor(colorId);
//            tvStoreDeliveryText.setBackgroundColor(colorId);
//        }
//        tvStoreDeliveryPoint.setText(evaluateStoreVo.getStoreDeliveryEval());
//        tvStoreDeliveryText.setText(evaluateStoreVo.getDeliveryEvalTitle());
//    }

    //店铺信息
    private void showShopViewDataNew() {
        //全部商品
        String goodsCommonCount = JsonUtil.toString(data, "goodsCommonCount");
        tvGoodsCount.setText(goodsCommonCount);
        int storeCollect = storeInfo.getStoreCollect();
        tvLikeCount.setText("" + storeCollect);//error

        String avgStoreEval = evaluateStoreVo.getAvgStoreEval();
        tvStoreLevel.setText(avgStoreEval);

        tvStoreName.setText(storeInfo.getStoreName());

        LoadImage.loadRemoteImg(getContext(), ivStoreImg, storeInfo.getStoreAvatarUrl());
        if (evaluateStoreVo.getDesEvalArrow().equals("low")) {
            tvGoodsLevel.setText(Html.fromHtml("商品 <font color=\"#48CFAE\">" + evaluateStoreVo.getStoreDesEval() +
                    "</font> | <font color=\"#48CFAE\">低</font>"));
        } else {
            tvGoodsLevel.setText(Html.fromHtml("商品 <font color=\"#f23030\">" + evaluateStoreVo.getStoreDesEval() +
                    "</font> | <font color=\"#f23030\">高</font>"));
        }


        if (evaluateStoreVo.getServiceEvalArrow().equals("low")) {
            tvServiceLevel.setText(Html.fromHtml("服务 <font color=\"#48CFAE\">" + evaluateStoreVo.getStoreServiceEval() +
                    "</font> | <font color=\"#48CFAE\">低</font>"));
        } else {
            tvServiceLevel.setText(Html.fromHtml("服务 <font color=\"#f23030\">" + evaluateStoreVo.getStoreServiceEval() +
                    "</font> | <font color=\"#f23030\">高</font>"));
        }

        if (evaluateStoreVo.getDeliveryEvalArrow().equals("low")) {
            tvDeliveryLevel.setText(Html.fromHtml("物流 <font color=\"#48CFAE\">" + evaluateStoreVo.getStoreDeliveryEval() +
                    "</font> | <font color=\"#48CFAE\">低</font>"));
        } else {
            tvDeliveryLevel.setText(Html.fromHtml("物流 <font color=\"#f23030\">" + evaluateStoreVo.getStoreDeliveryEval() +
                    "</font> | <font color=\"#f23030\">高</font>"));
        }
    }


    /**
     * 1.目前这样 Visible  Gone
     * 2.ViewStub  代码清晰  但只能inflate一次  不知日后会否加上刷新，故暂未使用
     * 3.addView   每次inflate一个布局再设置
     * 4.listview  itemType 不推荐不推荐  不好
     * 5.viewFliper  应该会比较简洁美观  TODO
     */
    private void showGoodsPrice() {
        if (goodDetailVo.getGoodsModal() == 1) {
            //零售模式  横排排列
            if (goodDetailVo.getBatchNum0() != 0) {
                llBatch0PriceModel.setVisibility(View.VISIBLE);
                model2.setVisibility(View.GONE);
                tvSaleLimit.setVisibility(View.GONE);  //零售无起批量
                if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 1) {
                    tvSalePrice.setText(getPriceSpannable12String(selectedGoods.getAppPrice0(), selectedGoods.getGoodsPrice0()), TextView.BufferType.SPANNABLE);
                } else if (selectedBook != null) { //定金预售
//                    llPreSell.setVisibility(View.VISIBLE);
//                    tvPreFlag.setText(goodDetailVo.getPromotionTypeText());
//                    tvSalePrice.setText(getPriceSpannable12String(selectedBook.getDownPayment(), "尾款", selectedBook.getFinalPayment(), false), TextView.BufferType.SPANNABLE);
//                    tvPreTime.setText("尾款日期：" + selectedBook.getDownTime().substring(0, 10));
                } else {
                    tvSalePrice.setText(moneyRmb + String.format("%.2f", selectedGoods.getAppPrice0().floatValue()));
//                    tvSalePrice.setText(getPriceSpannable12String(selectedGoods.getAppPrice0(), null), TextView.BufferType.SPANNABLE);
                }
            }
        } else if (goodDetailVo.getGoodsModal() == 2) {
            if (goodDetailVo.getBatchNum0End() == 0) {
                //批发模式  单一价格段  横排排列
                llBatch0PriceModel.setVisibility(View.VISIBLE);
                model2.setVisibility(View.GONE);
                tvSaleLimit.setVisibility(View.VISIBLE);
                tvSaleLimit.setText(goodDetailVo.getBatchNum0() + goodDetailVo.getUnitName() + "起购");
                if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 1) {
                    tvSalePrice.setText(getPriceSpannable12String(selectedGoods.getAppPrice0(), selectedGoods.getGoodsPrice0()), TextView.BufferType.SPANNABLE);
                } else {
                    tvSalePrice.setText(getPriceSpannable12String(selectedGoods.getAppPrice0(), null), TextView.BufferType.SPANNABLE);
                }
            } else if (goodDetailVo.getBatchNum1End() == 0) {
                //批发模式  双价格段  竖排排列
                llBatch0PriceModel.setVisibility(View.GONE);
                model2.setVisibility(View.VISIBLE);
                model2Third.setVisibility(View.GONE);
                goodsSaleNumBegin0.setText(goodDetailVo.getBatchNum0() + goodDetailVo.getUnitName() + "  起购");
                goodsSaleNumBegin1.setText(" ≥ " + goodDetailVo.getBatchNum1() + goodDetailVo.getUnitName());
                if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 1) {
                    goodsSaleNumBeginPrice0.setText(getPriceSpannable12String(selectedGoods.getAppPrice0(), selectedGoods.getGoodsPrice0()), TextView.BufferType.SPANNABLE);
                    goodsSaleNumBeginPrice1.setText(getPriceSpannable12String(selectedGoods.getAppPrice1(), selectedGoods.getGoodsPrice1()), TextView.BufferType.SPANNABLE);
                } else {
                    goodsSaleNumBeginPrice0.setText(getPriceSpannable12String(selectedGoods.getAppPrice0(), null), TextView.BufferType.SPANNABLE);
                    goodsSaleNumBeginPrice1.setText(getPriceSpannable12String(selectedGoods.getAppPrice1(), null), TextView.BufferType.SPANNABLE);
                }

            } else {
                //批发模式  三价格段  竖排排列
                llBatch0PriceModel.setVisibility(View.GONE);
                model2.setVisibility(View.VISIBLE);
                goodsSaleNumBegin0.setText(goodDetailVo.getBatchNum0() + goodDetailVo.getUnitName() + "  起购");
                goodsSaleNumBegin1.setText(goodDetailVo.getBatchNum1() + " - " + goodDetailVo.getBatchNum1End() + goodDetailVo.getUnitName());
                goodsSaleNumBegin2.setText(" ≥ " + goodDetailVo.getBatchNum2() + goodDetailVo.getUnitName());
                if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 1) {
                    goodsSaleNumBeginPrice0.setText(getPriceSpannable3String(selectedGoods.getAppPrice0(), selectedGoods.getGoodsPrice0()), TextView.BufferType.SPANNABLE);
                    goodsSaleNumBeginPrice1.setText(getPriceSpannable3String(selectedGoods.getAppPrice1(), selectedGoods.getGoodsPrice1()), TextView.BufferType.SPANNABLE);
                    goodsSaleNumBeginPrice2.setText(getPriceSpannable3String(selectedGoods.getAppPrice2(), selectedGoods.getGoodsPrice2()), TextView.BufferType.SPANNABLE);
                } else {
                    goodsSaleNumBeginPrice0.setText(getPriceSpannable3String(selectedGoods.getAppPrice0(), null), TextView.BufferType.SPANNABLE);
                    goodsSaleNumBeginPrice1.setText(getPriceSpannable3String(selectedGoods.getAppPrice1(), null), TextView.BufferType.SPANNABLE);
                    goodsSaleNumBeginPrice2.setText(getPriceSpannable3String(selectedGoods.getAppPrice2(), null), TextView.BufferType.SPANNABLE);
                }

            }
        }
    }

    //显示自营店铺  TODO  使用ImageSpan做更好的显示控制
    private SpannableString getGoodNameSpannableString(String goodName) {
        String s = "自营";
        SpannableString spannableString = new SpannableString(s + "   " + goodName);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_ownshop), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new BackgroundColorSpan(ContextCompat.getColor(context, R.color.nc_btn_bg)), 0, s.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    //大小显示价格串
    private SpannableString getPriceSpannable12String(BigDecimal appPrice, BigDecimal webPrice) {
        return getPriceSpannable12String(appPrice, "", webPrice, true);
    }

    //为了定金预售的复用
    private SpannableString getPriceSpannable12String(BigDecimal appPrice, String webPricePre, BigDecimal webPrice, boolean isDeleteLine) {
        String s = moneyRmb + ShopHelper.getPriceString(appPrice) + " ";
        String ss = "";
        if (null != webPrice) {
            ss = moneyRmb + webPricePre + ShopHelper.getPriceString(webPrice);
        }
        String price = s + ss;
        SpannableString spannableString = new SpannableString(price);
        int position = s.indexOf(".");
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_small_rmb), 0, moneyRmbLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_big_price), moneyRmbLength, position, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_small_rmb), position, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_small_delete_rmb), s.length(), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (isDeleteLine)
            spannableString.setSpan(new StrikethroughSpan(), s.length(), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    //大小显示价格串
    private SpannableString getPriceSpannable3String(BigDecimal appPrice, BigDecimal webPrice) {
        String s = moneyRmb + ShopHelper.getPriceString(appPrice) + " ";
        String ss = "";
        if (null != webPrice) {
            ss = "\n" + moneyRmb + ShopHelper.getPriceString(webPrice);
        }
        String price = s + ss;
        SpannableString spannableString = new SpannableString(price);
        int position = s.indexOf(".");
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_small_rmb), 0, moneyRmbLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_big_price), moneyRmbLength, position, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_small_rmb), position, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.goods_details_small_delete_rmb), s.length(), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TabStopSpan.Standard(s.length()), 0, price.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new StrikethroughSpan(), s.length(), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    //接收规格页面选中的商品规格Map
    public void onEventMainThread(GoodBusBean goodBusBean) {
        if (goodBusBean.getFlag().equals(GoodBusBean.SelectedGoods)) {
            selectedGoods = (Goods) goodBusBean.getObj();
            showColorIdImages(selectedGoods.getColorId());
            if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 3) {
                for (BookBean bookBean : goodDetailVo.getBookList()) {
                    if (bookBean.getGoodsId() == selectedGoods.getGoodsId()) {
                        selectedBook = bookBean;
                        break;
                    } else {
                        selectedBook = null;
                        llPreSell.setVisibility(View.GONE);
                    }
                }
            }
            showGoodsPrice();
            tvSelectSpec.setText(selectedGoods.getGoodsFullSpecs());
            showGoodsGift();
        } else if (goodBusBean.getFlag().equals(GoodBusBean.GoodNum)) {
            selectedNum = (Integer) goodBusBean.getObj();
            requestFreight();//数量变化  重新请求运费
        } else if (goodBusBean.getFlag().equals(GoodBusBean.GoodPreHashMap)) {
            preGoodsMap = (HashMap<Integer, PreGoods>) goodBusBean.getObj();  //记录批发模式下的选中状态
        }
    }

    /**
     * 传值
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
        loadGood();
    }

    //赠品显示
    private void showGoodsGift() {
        mGifts = new ArrayList<>();
        for (GoodGift gift : goodDetailVo.getGiftVoList()) {
            if (gift.getItemId() == selectedGoods.getGoodsId()) {
                mGifts.add(gift);
            }
        }

        if (goodDetailVo.getIsGift() == 1 && mGifts.size() > 0) {
            tvGiftDesc.setText(mGifts.get(0).getShowText());
            llGift.setVisibility(View.VISIBLE);
        } else {
            llGift.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.llSpecChoose)
    public void onSpecChooseClick() {
        if (goodDetailVo.getGoodsStatus() == 1) {
            //弹出规格选择页面
            showSelectSpecDialog();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //终止计时器
        CountDownHelper.exit();
        wvImg.removeAllViews();
        wvImg.destroy();
        wvImg = null;
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    //联系商家
    @OnClick(R.id.llContact)
    public void storeContact() {
        if (ShopHelper.isLogin(context)) {
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            // 将文本内容放到系统剪贴板里。
            cm.setText(ConstantUrl.URL_WEB_GOODS + commonId);
            Intent intent = new Intent(context, IMDetailsActivity.class);
            intent.putExtra("sid", goodDetailVo.getStoreId());
            intent.putExtra("gid", commonId);
            intent.putExtra("flag", "details");
            startActivity(intent);
        }
    }

    @OnClick({R.id.llGetVoucher, R.id.llGoodDiscount, R.id.llGoodEvaluate, R.id.tvRec, R.id.tvRank, R.id.llGoodsQuery,
            R.id.llComboGoodsVoList, R.id.llBindList, R.id.llEnterStore1, R.id.llEnterStore,})
    public void onClick(View view) {
        switch (view.getId()) {
            //推荐组合
            case R.id.llComboGoodsVoList:
                Intent intent = new Intent(getActivity(), ProductComboActivity.class);
                intent.putExtra("commonId", commonId);
                startActivity(intent);
                break;
            //优惠套装
            case R.id.llBindList:
                Intent intent1 = new Intent(getActivity(), ProductBundlingActivity.class);
                intent1.putExtra("commonId", commonId);
                startActivity(intent1);
                break;
            case R.id.llGetVoucher:
                showVoucherDialog();
//                Intent i = new Intent(getActivity(), VoucherListActivity.class);
//                i.putExtra("storeId", storeInfo.getStoreId() + "");
//                context.startActivity(i);
                break;
            case R.id.llGoodEvaluate:
                EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodEvaluate));
                break;
            //跳转店铺首页
            case R.id.llEnterStore:
            case R.id.llEnterStore1:
                ShopHelper.gotoStoreActivity(context, goodDetailVo.getStoreId());
                break;
            case R.id.llGoodDiscount:
//                ConfirmDialog dialog = new ConfirmDialog(context, conforms);
//                dialog.show();
                showPromotionDialog();
                break;

            case R.id.tvRec:
                tvRec.setSelected(true);
                tvRank.setSelected(false);
                llStoreRec.setVisibility(View.GONE);
                llStoreHot.setVisibility(View.VISIBLE);
                break;
            case R.id.tvRank:
                tvRec.setSelected(false);
                tvRank.setSelected(true);
                llStoreRec.setVisibility(View.VISIBLE);
                llStoreHot.setVisibility(View.GONE);
                break;
            case R.id.llGoodsQuery:
                Intent intent3 = new Intent(getActivity(), GoodsQueryActivity.class);
                intent3.putExtra("commonId", commonId);
                startActivity(intent3);
                break;

        }
    }

    /**
     * 领券弹窗
     */
    private void showVoucherDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.voucher_dialog, null);
        ListView listView = view.findViewById(R.id.listView);
        VoucherAdapter adapter = new VoucherAdapter(getActivity(), voucherTemplateListAll, R.layout.voucher_dialog_list_item);
        listView.setAdapter(adapter);

        Dialog dialog = new Dialog(getActivity(), R.style.CommonDialog);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 1200);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
    }

    /**
     * 促销弹窗
     */
    private void showPromotionDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.promotion_popupwindow, null);
        TextView tvGroup = view.findViewById(R.id.tvGroup);
        LinearLayout llGroup2 = view.findViewById(R.id.llGroup);
        LinearLayout llDiscount = view.findViewById(R.id.llDiscount);
        LinearLayout llDiscountContainer = view.findViewById(R.id.llDiscountContainer);
        LinearLayout llGift = view.findViewById(R.id.llGift);
        LinearLayout llGiftContainer = view.findViewById(R.id.llGiftContainer);


        //显示拼团
        if (mGroupBean != null) {
            llGroup2.setVisibility(View.VISIBLE);
            tvGroup.setText("支付开团并邀请" + (mGroupBean.getGroupRequireNum() - 1) + "人参团，如人数不足自动退款");
            tvGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //去拼团
                    Intent intent = new Intent(context, GroupDetailActivity.class);
                    intent.putExtra("commonId", commonId);
                    Log.d(TAG, "onClick: commonId = " + commonId);
                    startActivity(intent);
                }
            });
        } else {
            llGroup2.setVisibility(View.GONE);
        }

        //显示满优惠
        if (conforms != null && !conforms.isEmpty()) {
            for (int i = 0; i < conforms.size(); i++) {
                final Conform conform = conforms.get(i);
                TextView textView = new TextView(getActivity());
                textView.setTextColor(getResources().getColor(R.color.nc_black));
                textView.setText(conform.getContentCartRule());
                llDiscount.addView(textView);
            }
            llDiscountContainer.setVisibility(View.VISIBLE);
        } else {
            llDiscountContainer.setVisibility(View.GONE);
        }

        //赠品显示
        if (mGifts.size() > 0) {
            llGiftContainer.setVisibility(View.VISIBLE);
            for (int i = 0; i < mGifts.size(); i++) {
                final GoodGift goodGift = mGifts.get(i);
                GiftItemView giftItemView = new GiftItemView(getActivity());
                giftItemView.setText(goodGift.getGoodsName() + " " + goodGift.getGoodsFullSpecs() + "\t\tx" + goodGift.getGiftNum());
                giftItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int commonId = goodGift.getCommonId();
                        Log.d(TAG, "onClick: commonId = " + commonId);
                        getActivity().finish();
                        //商品详情
                        Intent intent = new Intent(getActivity(), GoodDetailsActivity.class);
                        intent.putExtra("commonId", commonId);
                        startActivity(intent);
                    }
                });
                llGift.addView(giftItemView);
            }
        } else {
            llGiftContainer.setVisibility(View.GONE);
        }


        Dialog dialog = new Dialog(getActivity(), R.style.CommonDialog);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, /*LinearLayout.LayoutParams.WRAP_CONTENT*/1200);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

    }

    private void showSelectSpecDialog() {
        //弹出规格选择页面
        GoodDetailsSpecDialog dialog = new GoodDetailsSpecDialog(context, goodDetailVo, preGoodsMap, selectedGoods, selectedNum);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.show();
        //1.windowIsFloating=false  点击外部不消失
        //2.windowIsFloating=true   宽度不全屏，故手动设置全屏
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
    }


}
