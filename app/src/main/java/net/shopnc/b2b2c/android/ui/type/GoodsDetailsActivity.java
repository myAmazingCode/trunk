package net.shopnc.b2b2c.android.ui.type;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.readystatesoftware.viewbadger.BadgeView;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.VpAdapter;
import net.shopnc.b2b2c.android.bean.GoodsDetails;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.DialogHelper;
import net.shopnc.b2b2c.android.common.Global;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.NCGoodsSpecPopupWindow;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ncinterface.INCOnNumModify;
import net.shopnc.b2b2c.android.ncinterface.INCOnStringModify;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


/**
 * 商品详细页Activity
 *
 * @author dqw
 * @Time 2015-7-14
 */
public class GoodsDetailsActivity extends BaseActivity implements GoodsDetailFragment.OnFragmentInteractionListener, GoodsDetailBodyFragment.OnFragmentInteractionListener, GoodsDetailEvaluateFragment.OnFragmentInteractionListener, View.OnClickListener {

    FragmentManager fragmentManager = getSupportFragmentManager();

    private String goods_id;
    private RadioButton btnGoodsDetail, btnGoodsBody, btnGoodsEvaluate;
    private GoodsDetailFragment goodsDetailFragment;
    private GoodsDetailBodyFragment goodsDetailBodyFragment;
    private GoodsDetailEvaluateFragment goodsDetailEvaluateFragment;

    private String title = "";

    private ViewPager vp;
    ArrayList<Fragment> list = new ArrayList<Fragment>();
    int currfragment = 0;
    public ImageView moremenu;
    //    -------------------------------gospring---------------------
    private String goodsName;
    private String goodsPrice;
    private String goodsStorage;
    private int goodsNum = 1; //商品数量
    private int goodsLimit = 0;
    private String is_fcode;//是否为F码商品 1是 0否
    private String is_virtual;//是否为虚拟商品 1-是 0-否
    private String ifcart = "0";//购物车购买标志 1购物车 0不是
    private String t_id, t_name; //记录商家ID 名称

    //商品数量修改回调
    private INCOnNumModify incOnNumModify;
    //商品规格修改回调
    private INCOnStringModify incOnStringModify;

    private ArrayList<String> imageList;
    private NCGoodsSpecPopupWindow pwSpec;

    private String goodsWapUrl;//记录图片地址

    //规格
    private String specString;
    //底部按钮
    private TextView imID, showCartLayoutID;
    private BadgeView badge;
    private Button addCartID, buyStepID;
    private boolean flag = false;
    private String errorMsg = "";
    private StoreInfo storeInfo;
    private GoodsDetails goodsBean;
    //    private String is_bc = "";
//    private String is_seller = "";
    //设置规格的参数
    private GoodsDetails goodsDetails;
    private String specList;
    private String mobile_body;//商品手机版详情

    private Context mContext;
    private String goods_attr;//产品规格参数

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                badge.setText((String) message.obj);
                badge.setVisibility(View.VISIBLE);
                badge.show();
            }
        }
    };

    private ArrayList<ImageView> viewList = new ArrayList<ImageView>();

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_details_view);
        MyExceptionHandler.getInstance().setContext(this);
        goods_id = GoodsDetailsActivity.this.getIntent().getStringExtra("goods_id");
        Constants.goodId = goods_id;
        btnGoodsDetail = (RadioButton) findViewById(R.id.btnGoodsDetail);
        btnGoodsBody = (RadioButton) findViewById(R.id.btnGoodsBody);
        btnGoodsEvaluate = (RadioButton) findViewById(R.id.btnGoodsEvaluate);
        moremenu = (ImageView) findViewById(R.id.moremenu);
        vp = (ViewPager) findViewById(R.id.main_viewpager);
        goodsDetailFragment = GoodsDetailFragment.newInstance(goods_id);
        goodsDetailBodyFragment = GoodsDetailBodyFragment.newInstance(goods_id);
        goodsDetailEvaluateFragment = GoodsDetailEvaluateFragment.newInstance(goods_id);
        list.add(goodsDetailFragment);
        list.add(goodsDetailBodyFragment);
        list.add(goodsDetailEvaluateFragment);
        VpAdapter vpadapter = new VpAdapter(fragmentManager);
        vpadapter.setList(list);
        vp.setAdapter(vpadapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                currfragment = position;
                ChangeGoodsBackgroud(position);
            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });
        ChangeGoodsBackgroud(0);
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.llMain, goodsDetailFragment);
//        transaction.add(R.id.llMain, goodsDetailBodyFragment);
//        transaction.add(R.id.llMain, goodsDetailEvaluateFragment);
//        transaction.commit();
//        showGoodsDetail();
        //底部按钮
        mContext = this;
        imID = (TextView) findViewById(R.id.imID);
        showCartLayoutID = (TextView) findViewById(R.id.showCartLayoutID);
        badge = new BadgeView(mContext, showCartLayoutID);
        badge.setTextSize(10);
        badge.setVisibility(View.GONE);
        badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        if (!StringUtils.isEmpty(MyShopApplication.getInstance().getLoginKey())) {
            setCartNumShow();
        } else {
            if (Global.cartnum == 0) {
                badge.setVisibility(View.GONE);
            } else {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = Global.cartnum + "";
                handler.sendMessage(msg);
            }
        }
        loadingGoodsDetailsData();
        addCartID = (Button) findViewById(R.id.addCartID);
        buyStepID = (Button) findViewById(R.id.buyStepID);

        imID.setOnClickListener(this);
        showCartLayoutID.setOnClickListener(this);
        addCartID.setOnClickListener(this);
        buyStepID.setOnClickListener(this);
        moremenu.setOnClickListener(this);
        initPopupWindow();
    }

    public void ChangeGoodsBackgroud(int poistion) {
        switch (poistion) {
            case 0:
                btnGoodsDetail.setChecked(true);
                btnGoodsBody.setChecked(false);
                btnGoodsEvaluate.setChecked(false);
                break;
            case 1:
                btnGoodsDetail.setChecked(false);
                btnGoodsBody.setChecked(true);
                btnGoodsEvaluate.setChecked(false);
                break;
            case 2:
                btnGoodsDetail.setChecked(false);
                btnGoodsBody.setChecked(false);
                btnGoodsEvaluate.setChecked(true);
                break;
        }
    }

    /**
     * 更换新商品
     */
    public void changeGoods(String goodsId) {
        goods_id = goodsId;
        goodsDetailBodyFragment.setGoodsId(goodsId);//改变其余两个fragment中的商品id，
        goodsDetailEvaluateFragment.setGoodsId(goodsId);
        loadingGoodsDetailsData();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.remove(goodsDetailBodyFragment);
//        transaction.remove(goodsDetailEvaluateFragment);
//        goodsDetailBodyFragment = GoodsDetailBodyFragment.newInstance(goods_id);
//        goodsDetailEvaluateFragment = GoodsDetailEvaluateFragment.newInstance(goods_id);
//        transaction.add(R.id.llMain, goodsDetailBodyFragment);
//        transaction.add(R.id.llMain, goodsDetailEvaluateFragment);
//        transaction.show(goodsDetailFragment);
//        transaction.hide(goodsDetailBodyFragment);
//        transaction.hide(goodsDetailEvaluateFragment);
//        transaction.commit();
    }

    public void changeFreagemt(int num) {
        vp.setCurrentItem(num);
    }

    /**
     * 返回按钮点击
     */
    public void btnBackClick(View view) {
//        if (title.equals("商品")){
        finish();
//        }else{
//            (GoodsDetailsActivity.this).showGoodsDetail();
//        }
    }

    //    更多加载
    private PopupWindow popupWindow;

    /**
     * 显示popupwindow
     */
    private void showPopupWindow() {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(moremenu, moremenu.getLayoutParams().width / 2, 0);
        } else {
            popupWindow.dismiss();
        }
    }

    /**
     * 初始化popupwindow
     */
    private void initPopupWindow() {
        popupWindow = DialogHelper.initPopupWindow(this);
    }

    /**
     * 商品详细按钮点击
     */
    public void btnGoodsDetailClick(View view) {
        changeFreagemt(0);
//        showGoodsDetail();
    }

    /**
     * 商品描述按钮点击
     */
    public void btnGoodsBodyClick(View view) {
        changeFreagemt(1);
//        showGoodsBody();
    }

    /**
     * 商品评价按钮点击
     */
    public void btnGoodsEvaluateClick(View view) {
        changeFreagemt(2);
//        showGoodsEvaluate();
    }

    /**
     * 设置Tab按钮状态
     */
    private void setTabButtonState(Button btn) {
        btnGoodsDetail.setActivated(false);
        btnGoodsBody.setActivated(false);
        btnGoodsEvaluate.setActivated(false);
        btn.setActivated(true);
    }

    /**
     * 显示商品Fragement
     */
    public void showGoodsDetail() {
        setTabButtonState(btnGoodsDetail);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(goodsDetailFragment);
        transaction.hide(goodsDetailBodyFragment);
        transaction.hide(goodsDetailEvaluateFragment);
        title = "商品";
        transaction.commit();
    }

    /**
     * 显示商品描述
     */
    public void showGoodsBody() {
        setTabButtonState(btnGoodsBody);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(goodsDetailFragment);
        transaction.show(goodsDetailBodyFragment);
        transaction.hide(goodsDetailEvaluateFragment);
        title = "详情";
        transaction.commit();
    }

    /**
     * 显示商品评价
     */
    public void showGoodsEvaluate() {
        setTabButtonState(btnGoodsEvaluate);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(goodsDetailFragment);
        transaction.hide(goodsDetailBodyFragment);
        transaction.show(goodsDetailEvaluateFragment);
        title = "详情";
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //-----------------------------------------购物车--------------------------------------------------

    /**
     * 初始化加载数据
     */
    public void loadingGoodsDetailsData() {
        String url = Constants.URL_GOODSDETAILS + "&goods_id=" + goods_id + "&key=" + MyShopApplication.getInstance().getLoginKey();

        if (!MyShopApplication.getInstance().getAreaId().equals("")) {
            url += "area_id=" + MyShopApplication.getInstance().getAreaId();
        }

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                viewList.clear();
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String goods_info = obj.getString("goods_info");// 商品信息
                        String store_info = obj.getString("store_info");// 店铺信息
                        storeInfo = StoreInfo.newInstanceList(store_info);

                        goodsBean = GoodsDetails.newInstanceList(goods_info);

                        //显示商品信息
                        showGoodsInfo(goodsBean);

                        //显示商品图片
                        String goods_image = obj.getString("goods_image"); //商品图片
                        imageList = new ArrayList<String>(Arrays.asList(goods_image.split(",")));
                        goodsWapUrl = imageList.get(0);

                        t_id = storeInfo.getMemberId();
                        t_name = storeInfo.getMemberName();

                        if (!is_virtual.equals("1")) {
                            //判断是否能够购买商品--购买实物商品
                            ifCanBuyS();
                        } else {
                            //判断是否能够购买商品--购买虚拟商品
                            ifCanBuyV();
                        }

                        /**新增*/
                        String spec_image = obj.getString("spec_image");
//                        is_bc = obj.getString("is_bc");
//                        is_seller = obj.getString("is_seller");

                        //初始化规格弹出窗口
                        goodsDetails = goodsBean;
                        specList = obj.getString("spec_list");
                        initSpec(goodsBean, specList);
                    } catch (JSONException e) {
                        T.showShort(mContext, "获得数据有为空字段");
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(mContext, json);
                }
            }
        });
    }

    /**
     * 初始化规格
     */
    private void initSpec(final GoodsDetails goodsBean, final String specList) {
        incOnNumModify = new INCOnNumModify() {
            @Override
            public void onModify(int num) {
                goodsNum = num;
            }
        };

        incOnStringModify = new INCOnStringModify() {
            @Override
            public void onModify(String str) {
                goods_id = str;
                vp.setCurrentItem(0);
                loadingGoodsDetailsData();
                goodsDetailFragment.goodsId = goods_id;
                goodsDetailFragment.loadingGoodsDetailsData();
            }
        };

        //限购数量
        if (goodsBean.getUpper_limit() == null || goodsBean.getUpper_limit().equals("") || goodsBean.getUpper_limit().equals("0")) {
            goodsLimit = Integer.parseInt((goodsBean.getGoods_storage() == null ? "0" : goodsBean.getGoods_storage()) == "" ? "0" : goodsBean.getGoods_storage());
        } else {
            goodsLimit = Integer.parseInt(goodsBean.getUpper_limit());
        }

        if (pwSpec == null) {
            pwSpec = new NCGoodsSpecPopupWindow(mContext, incOnNumModify, incOnStringModify, t_id, t_name, storeInfo.getStoreId(), storeInfo.getStoreName());
        }
        pwSpec.setGoodsInfo(goodsName, imageList.get(0), goodsPrice, goodsStorage, goods_id, ifcart, goodsNum, goodsLimit, is_fcode, is_virtual);
        pwSpec.setSpecInfo(specList, goodsBean.getSpec_name(), goodsBean.getSpec_value(), goodsBean.getGoods_spec());
    }

    /**
     * 显示商品信息
     *
     * @param goodsBean
     * @throws JSONException
     */
    private void showGoodsInfo(GoodsDetails goodsBean) throws JSONException {
        goodsName = goodsBean.getGoods_name();
        goodsStorage = goodsBean.getGoods_storage();
        ifcart = goodsBean.getCart();
        if (!ifcart.equals("1")) {
            addCartID.setVisibility(View.GONE);
        } else {
            addCartID.setVisibility(View.VISIBLE);
        }

        mobile_body = goodsBean.getMobile_body();//
        goods_attr = goodsBean.getGoods_attr();//产品规格

        if (goodsBean.getPromotion_price() != null && !goodsBean.getPromotion_price().equals("") && !goodsBean.getPromotion_price().equals("null")) {
            goodsPrice = goodsBean.getPromotion_price();
        } else {
            goodsPrice = goodsBean.getGoods_price();
        }
        //是否是F吗商品
        is_fcode = goodsBean.getIs_fcode() == null ? "0" : goodsBean.getIs_fcode();

        //是否是虚拟商品
        is_virtual = goodsBean.getIs_virtual() == null ? "0" : goodsBean.getIs_virtual();


        //显示规格值
        String specValue = "";
        if (goodsBean.getGoods_spec() != null && !goodsBean.getGoods_spec().equals("") && !goodsBean.getGoods_spec().equals("null")) {
            JSONObject jsonGoods_spec = new JSONObject(goodsBean.getGoods_spec());
            Iterator<?> itName = jsonGoods_spec.keys();
            while (itName.hasNext()) {
                String specID = itName.next().toString();
                String specV = jsonGoods_spec.getString(specID);
                specValue += "," + specV;
            }
            specString = specValue.replaceFirst(",", "");
        } else {
            specString = "";
        }

    }

    /**
     * 判断是否能够购买商品--购买实物商品
     */
    private void ifCanBuyS() {
        String url = Constants.URL_BUY_STEP1;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("cart_id", goods_id + "|" + goodsNum);
        params.put("ifcart", "0");

        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    flag = true;
                } else {
                    errorMsg = json;
                }
            }
        });
    }

    /**
     * 判断是否能够购买商品--购买虚拟商品
     */
    public void ifCanBuyV() {
        String url = Constants.URL_MEMBER_VR_BUY;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("goods_id", goods_id);
        params.put("quantity", String.valueOf(goodsNum));

        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    flag = true;
                } else {
                    errorMsg = json;
                }
            }
        });
    }

    public void setCartNumShow() {
        String url = Constants.URL_GET_CART_NUM;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        RemoteDataHandler.asyncPostDataString(url, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String num = obj.getString("cart_count");
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = num;
                        handler.sendMessage(msg);
                    } catch (JSONException e) {
                        T.showShort(mContext, "获取购物车数量失败");
                    }
                }
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        ifCanBuyS();
        if (!(MyShopApplication.getInstance().getLoginKey() != "null" || MyShopApplication.getInstance().getLoginKey() != "")) {
            setCartNumShow();
        }
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        ifCanBuyS();
//    }

    @Override
    public void onStart() {
        super.onStart();
        registerBoradcastReceiver();
    }

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constants.SHOW_CART_NUM);
        registerReceiver(mBroadcastReceiver, myIntentFilter); //注册广播
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.SHOW_CART_NUM)) {
                if (StringUtils.isEmpty(application.getLoginKey())) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = Global.cartnum + "";
                    handler.sendMessage(msg);
                } else {
                    setCartNumShow();
                }

            }
        }
    };

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.imID:
                if (ShopHelper.isLogin(mContext, MyShopApplication.getInstance().getLoginKey())) {
                    ShopHelper.showIm(mContext, MyShopApplication.getInstance(), t_id, t_name);
                } else {
                    T.showShort(mContext, "请登录");
                }

                break;
            case R.id.showCartLayoutID:
                if (ShopHelper.isLogin(mContext, MyShopApplication.getInstance().getLoginKey())) {
                    //跳转购物车
                    Intent intent = new Intent(GoodsDetailsActivity.this, MainFragmentManager.class);
                    //广播通知显示购物车
                    MyShopApplication.getInstance().sendBroadcast(new Intent(Constants.SHOW_CART_URL));
                    pwSpec.closePoperWindow();
                    startActivity(intent);
                } else {
                    T.showShort(mContext, "请登录");
                }
                break;
            case R.id.addCartID:
                pwSpec.showPopupWindow();
                break;
            case R.id.buyStepID:
                if (ShopHelper.isLogin(mContext, MyShopApplication.getInstance().getLoginKey())) {
                    if (flag == false) {
                        ShopHelper.showApiError(mContext, errorMsg);
                    } else {
                        initSpec(goodsDetails, specList);
                        pwSpec.showPopupWindow();
                    }
                } else {
                    T.showShort(mContext, "请登录");
                }
                break;
            case R.id.moremenu:
                if (ShopHelper.isLogin(mContext, MyShopApplication.getInstance().getLoginKey())) {
                    showPopupWindow();
                } else {
                    T.showShort(mContext, "请登录");
                }
                break;
        }

//        if (ShopHelper.isLogin(mContext, MyShopApplication.getInstance().getLoginKey())) {
//            switch (view.getId()) {
//                case R.id.imID:
//                    ShopHelper.showIm(mContext, MyShopApplication.getInstance(), t_id, t_name);
//                    break;
//                case R.id.showCartLayoutID:
//                    //跳转购物车
//                    Intent intent = new Intent(GoodsDetailsActivity.this, MainFragmentManager.class);
//                    //广播通知显示购物车
//                    MyShopApplication.getInstance().sendBroadcast(new Intent(Constants.SHOW_CART_URL));
//                    pwSpec.closePoperWindow();
//                    startActivity(intent);
//                    break;
//        case R.id.addCartID:
//        pwSpec.showPopupWindow();
//        break;
//
//                case R.id.buyStepID:
//                    if (flag == false) {
//                        ShopHelper.showApiError(mContext, errorMsg);
//                    } else {
//                        initSpec(goodsDetails, specList);
//                        pwSpec.showPopupWindow();
//                    }
//                    break;
//                case R.id.moremenu:
//                    showPopupWindow();
//                    break;
//            }
//        } else {
//            T.showShort(mContext, "请登录");
//        }

    }
}
