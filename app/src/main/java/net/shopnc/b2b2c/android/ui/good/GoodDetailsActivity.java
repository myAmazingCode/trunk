package net.shopnc.b2b2c.android.ui.good;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.GoodFragmentPageAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.GoodDetailVo;
import net.shopnc.b2b2c.android.bean.Goods;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.MyViewPager;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.GoodDetailMoreDialog;
import net.shopnc.b2b2c.android.custom.dialog.GoodDetailsSpecDialog;
import net.shopnc.b2b2c.android.custom.dialog.ShareDialog;
import net.shopnc.b2b2c.android.ui.home.CartActivity;
import net.shopnc.b2b2c.android.ui.home.CartHelper;
import net.shopnc.b2b2c.android.ui.im.IMDetailsActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class GoodDetailsActivity extends BaseActivity implements GoodDetailsFragment.OnGoodsDetailsListener {

    public static String COMMONID = "commonId";
    public static String GOODID = "goodsId";
    public static String DISTRIBUTION_ID = "distributionGoodsId";
    public static String TRYSTYPE = "trysType";

    public static final String CART_TO_DETAIL = "cart_to_detail";
    private boolean cart_to_detail;//标记：true表示从购物车点击来到详情页；false表示其他，默认值false


    @Bind(R.id.tvCommonTitle)
    TextView title;
    @Bind(R.id.tvCount)
    TextView tvCount;
    @Bind(R.id.ivMore)
    ImageView ivMore;
    @Bind(R.id.ivShare)
    ImageView ivShare;
    @Bind(R.id.tabGoods)
    TabLayout tabGoods;
    @Bind(R.id.vpFragment)
    MyViewPager vpFragment;

    @Bind(R.id.tvFav)
    TextView tvFav;
    @Bind(R.id.btnArrivalNotice)
    Button btnArrivalNotice;
    @Bind(R.id.addCartID)
    Button addCartId;
    @Bind(R.id.buyStepID)
    Button buyStepID;

    private int commonId;
    private int goodsId;
    private int distributionGoodsId;
    private int trysType;
    private GoodDetailVo goodDetailVo;
    private boolean isFav = false;

    public GoodDetailsFragment goodDetailsFragment;
    public GoodDetailsBrowseFragment goodDetailsBrowseFragment;
    public GoodDetailsEvaluateFragment goodDetailsEvaluateFragment;

    private List<String> titleList;
    private List<Fragment> fragmentList;
    private GoodFragmentPageAdapter adapter;
    private int mCurrentItem;

    private HashMap<Integer, PreGoods> preGoodsMap;
    private Goods selectedGoods;
    private int selectedNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        commonId = getIntent().getIntExtra(COMMONID, 0);
        goodsId = getIntent().getIntExtra(GOODID, 0);
        distributionGoodsId = getIntent().getIntExtra(DISTRIBUTION_ID, 0);
        //访问商品详情时验证参数distributionGoodsId是否存在，存在请求该接口。
        if (distributionGoodsId != 0)
            addDistribution();

        trysType = getIntent().getIntExtra(TRYSTYPE, 0);
        initTitle();
        initFragment();

        //取值
        cart_to_detail = getIntent().getBooleanExtra(CART_TO_DETAIL, false);

        //请求购物车商品数量
        CartHelper.requestCartCount(this, tvCount);

        preGoodsMap = new HashMap<>();

        adapter = new GoodFragmentPageAdapter(getSupportFragmentManager(), context);
        adapter.setTitleList(titleList);
        adapter.setFragmentList(fragmentList);
        vpFragment.setAdapter(adapter);
        vpFragment.setOffscreenPageLimit(4);  //设置viewPager缓存数量
        tabGoods.setupWithViewPager(vpFragment);
        loadGood();

        vpFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private boolean showDetails;//显示详情状态

    /*******************控制标题显示*********************/
    @Override
    public void onShow(boolean showDetails) {
        this.showDetails = showDetails;
        if (showDetails) {
            setCommonHeader("图文详情");
            tabGoods.setVisibility(View.GONE);
            //禁止ViewPager水平滑动
            vpFragment.setCanScroll(false);
        } else {
            setCommonHeader("");
            tabGoods.setVisibility(View.VISIBLE);
            //恢复ViewPager水平滑动
            vpFragment.setCanScroll(true);
        }
    }


    //是否显示到货通知
    @Override
    public void onArrivalNotice(boolean notice) {
        if (notice) {
            btnArrivalNotice.setVisibility(View.VISIBLE);
            buyStepID.setVisibility(View.GONE);
            addCartId.setVisibility(View.GONE);
        } else {
            btnArrivalNotice.setVisibility(View.GONE);
//            buyStepID.setVisibility(View.VISIBLE);
            //判断是否为定金支付
//            if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 3) {
//                buyStepID.setVisibility(View.GONE);
//            } else {
//                buyStepID.setVisibility(View.VISIBLE);
//            }

            addCartId.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.btnBack})
    public void btnBack() {
        //如果当前显示详情
        if (showDetails) {
            goodDetailsFragment.hideDetails();
            showDetails = false;

            setCommonHeader("");
            tabGoods.setVisibility(View.VISIBLE);
            //恢复ViewPager水平滑动
            vpFragment.setCanScroll(true);
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //如果当前显示详情
            if (showDetails) {
                goodDetailsFragment.hideDetails();
                showDetails = false;

                setCommonHeader("");
                tabGoods.setVisibility(View.VISIBLE);
                //恢复ViewPager水平滑动
                vpFragment.setCanScroll(true);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void setView() {
        setContentView(R.layout.activity_good_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestFavorite();
    }

    private void initTitle() {
        titleList = new ArrayList<>();
        titleList.add("商品");
        titleList.add("详情");
        titleList.add("评价");
    }

    private void loadGood() {
        String url = ConstantUrl.URL_GOOD_DETAILS + commonId;

        OkHttpUtil.getAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                goodDetailVo = JsonUtil.toBean(data, "goodsDetail", new TypeToken<GoodDetailVo>() {
                }.getType());
                if (goodsId == 0) {
                    selectedGoods = goodDetailVo.getGoodsList().get(0);
                } else {
                    for (Goods goods : goodDetailVo.getGoodsList()) {
                        if (goods.getGoodsId() == goodsId) {
                            selectedGoods = goods;
                            break;
                        }
                    }
                }

                setGoodsState();
                //减少网络请求
                goodDetailsFragment.setData(data);
            }
        });
    }

    /**
     * 添加推广记录用于推广分佣
     * 如果当前未登录进行记录，登陆后进行合并
     */
    private void addDistribution() {
        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("distributionGoodsId", distributionGoodsId + "");

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_DISTRIBUTION_GOODS_ADD, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.d("Distribution:data = " + data);//PRETTYLOGGER
                if ("".equals(application.getToken())) {
                    String distribution = application.getDistribution();
                    LogHelper.d("Distribution:" + distribution);
                    HashSet<String> distributionSet;
                    if ("".equals(distribution)) {
                        distributionSet = new HashSet<>();
                    } else {
                        distributionSet = JsonUtil.toBean(application.getDistribution(), new TypeToken<HashSet<String>>() {
                        }.getType());
                    }
                    distributionSet.add(JsonUtil.toString(data, "data"));
                    application.setDistribution(new Gson().toJson(distributionSet));
                }
            }
        }, params);
    }

    //下架与正常显示
    private void setGoodsState() {
        if (goodDetailVo.getGoodsStatus() == 0) {
            addCartId.setBackgroundColor(ContextCompat.getColor(context, R.color.nc_text));
            addCartId.setClickable(false);
        } else if (goodDetailVo.getAppUsable() == 1 && goodDetailVo.getPromotionType() == 3) {
            addCartId.setBackgroundColor(ContextCompat.getColor(context, R.color.nc_ming_grade_bg));
            addCartId.setText("支付定金");
            addCartId.setClickable(true);
            buyStepID.setVisibility(View.GONE);
            switchArrivalNoticeShow(goodDetailVo.getGoodsModal());
        } else {
            addCartId.setBackgroundColor(ContextCompat.getColor(context, R.color.bottom_bar_yellow));
            addCartId.setText("加入购物车");
            addCartId.setClickable(true);
            switchArrivalNoticeShow(goodDetailVo.getGoodsModal());
        }
    }

    private void switchArrivalNoticeShow(int goodsModal) {
        switch (goodsModal) {
            case 1:
                if (selectedGoods.getGoodsStorage() == 0) {
                    addCartId.setVisibility(View.GONE);
                    btnArrivalNotice.setVisibility(View.VISIBLE);
                } else {
                    addCartId.setVisibility(View.VISIBLE);
                    btnArrivalNotice.setVisibility(View.GONE);
                }
                break;
            case 2:
                int num = 0;
                for (Goods goods : goodDetailVo.getGoodsList()) {
                    num += goods.getGoodsStorage();
                }
                if (num == 0) {
                    addCartId.setVisibility(View.GONE);
                    btnArrivalNotice.setVisibility(View.VISIBLE);
                } else {
                    addCartId.setVisibility(View.VISIBLE);
                    btnArrivalNotice.setVisibility(View.GONE);
                }
                break;
        }
    }

    //请求当前用户是否收藏此商品
    private void requestFavorite() {
        if (!ShopHelper.isLogin()) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("commonId", commonId + "");
        OkHttpUtil.postAsyn(this, ConstantUrl.URL_WHETHER_FAV_GOODS, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                if (JsonUtil.toString(data, "isExist").equals("1")) {
                    isFav = true;
                    tvFav.setText("收藏");
                    tvFav.setSelected(isFav);
                } else {
                    isFav = false;
                    tvFav.setText("收藏");
                    tvFav.setSelected(isFav);
                }

            }
        }, params);
    }

    @OnClick({R.id.imID, R.id.ivMore, R.id.ivShare, R.id.tvShowStore, R.id.lvFav, R.id.showCartLayoutID, R.id.buyStepID, R.id.addCartID, R.id.btnArrivalNotice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imID:
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

                break;
            case R.id.ivMore:
                GoodDetailMoreDialog dialog = new GoodDetailMoreDialog(context, application, ivMore);
                break;
            case R.id.ivShare:
                UMImage image = new UMImage(context, goodDetailVo.getImageSrc());
//                new ShareAction((Activity) context).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
//                        .withTitle(context.getResources().getString(R.string.app_name))
//                        .withText(goodDetailVo.getGoodsName() + "     " + ConstantUrl.WAP_GOODS_URL + commonId + "     (" + getString(R.string.app_name) + ")")
//                        .withTargetUrl(ConstantUrl.WAP_GOODS_URL + commonId)
//                        .withMedia(image)
//                        .withShareBoardDirection(ivShare, Gravity.CENTER)
//                        .setCallback(umShareListener)
//                        .open();
                String url = ConstantUrl.WAP_GOODS_URL + commonId;
                ShareDialog shareDialog = new ShareDialog(context, context.getResources().getString(R.string.app_name), goodDetailVo.getGoodsName() + "     " + url + "     (" + getString(R.string.app_name) + ")", url, image, umShareListener);
                shareDialog.show();
                break;
            case R.id.tvShowStore:
                ShopHelper.gotoStoreActivity(context, goodDetailVo.getStoreId());
                break;
            case R.id.lvFav:
                ShopHelper.isLogin(context);
                Map<String, String> p = new HashMap<>();
                p.put("token", application.getToken());
                p.put("commonId", commonId + "");
                isFav = !isFav;
                if (isFav) {
                    OkHttpUtil.postAsyn(this, ConstantUrl.URL_FAV_GOODS, new BeanCallback<String>() {
                        @Override
                        public void response(String data) {
                            if (data.contains("success")) {
                                tvFav.setText("收藏");
                                tvFav.setSelected(true);
                            }
                        }
                    }, p);
                } else {
                    OkHttpUtil.postAsyn(this, ConstantUrl.URL_DELETE_FAV_GOODS, new BeanCallback<String>() {
                        @Override
                        public void response(String data) {
                            if (data.contains("success")) {
                                tvFav.setText("收藏");
                                tvFav.setSelected(false);
                            }
                        }
                    }, p);
                }
                break;
            case R.id.showCartLayoutID:
                //从购物车点击过来，则直接返回
                if (cart_to_detail) {
                    finish();
                } else {
                    //否则去往购物车Activity
                    Intent intent = new Intent(this, CartActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btnArrivalNotice:
//                TToast.showLong(context, "若商品在30日内到货,我们会通过邮件、短信和手机客户端来通知您");
                new AlertDialog.Builder(context)
                        .setMessage("若商品在30日内到货，我们会通过邮件、短信和手机客户端来通知您")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.buyStepID:
            case R.id.addCartID:
                showSelectSpecDialog();
                break;

        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                TToast.showShort(context, platform + " 收藏成功啦");
            } else {
                TToast.showShort(context, platform + " 分享成功啦");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            TToast.showShort(context, platform + " 分享失败啦");
            if (t != null) {
                LogHelper.d("throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            TToast.showShort(context, platform + " 分享取消了");
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }

    public static final String TAG = "GoodDetailsActivity";

    public void onEventMainThread(GoodBusBean goodBusBean) {
        if (goodBusBean.getFlag().equals(GoodBusBean.SelectedGoods)) {
            selectedGoods = (Goods) goodBusBean.getObj();
            switchArrivalNoticeShow(goodDetailVo.getGoodsModal());
        } else if (goodBusBean.getFlag().equals(GoodBusBean.GoodNum)) {
            selectedNum = (Integer) goodBusBean.getObj();
        } else if (goodBusBean.getFlag().equals(GoodBusBean.GoodPreHashMap)) {
            preGoodsMap = (HashMap<Integer, PreGoods>) goodBusBean.getObj();  //记录批发模式下的选中状态
        } else if (goodBusBean.getFlag().equals(GoodBusBean.GoodEvaluate)) {
            changeFragment(2);
        } else if (goodBusBean.getFlag().equals(GoodBusBean.GoodBrowseTurn)) {
            changeFragment(1);
        } else if (goodBusBean.getFlag().equals(GoodBusBean.REFRESH_CART_COUNT)) {
            CartHelper.requestCartCount(this, tvCount);
        }
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
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
    }

    private void initFragment() {
        if (goodsId == 0) {
            goodDetailsFragment = GoodDetailsFragment.newInstance(commonId);
        } else {
            goodDetailsFragment = GoodDetailsFragment.newInstance(commonId, goodsId, trysType);
        }
        goodDetailsFragment.setOnGoodsDetailsListener(this);
        goodDetailsBrowseFragment = GoodDetailsBrowseFragment.newInstance(commonId);
        goodDetailsEvaluateFragment = GoodDetailsEvaluateFragment.newInstance(commonId);
        fragmentList = new ArrayList<>();
        fragmentList.add(goodDetailsFragment);
        fragmentList.add(goodDetailsBrowseFragment);
        fragmentList.add(goodDetailsEvaluateFragment);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("index", mCurrentItem);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int savedIndex = savedInstanceState.getInt("index");
        changeFragment(savedIndex);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int index = intent.getIntExtra("index", -1);
        if (index >= 0 && index < titleList.size()) {
            changeFragment(index);
        }
    }

    private void changeFragment(int i) {
        vpFragment.setCurrentItem(i);
    }


}
