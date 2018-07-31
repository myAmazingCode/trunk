package net.shopnc.b2b2c.android.ui.store;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.StoreBaseFragment;
import net.shopnc.b2b2c.android.bean.EvaluateStoreVo;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.bean.StoreIntroduceVO;
import net.shopnc.b2b2c.android.bean.StoreMobile;
import net.shopnc.b2b2c.android.bean.StoreMobileVo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.DensityUtil;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.StorePopWindow;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 店铺详情页
 *
 * @author huting
 * @date 2016/4/12
 */
public class StoreInfoFragmentActivity extends FragmentActivity {

    @Bind(R.id.storeInFoPic)
    ImageView storeInFoPic;
    @Bind(R.id.storePic)
    ImageView storePic;
    @Bind(R.id.storeNameID)
    TextView storeNameID;
    @Bind(R.id.textFanCount)
    TextView textFanCount;
    @Bind(R.id.favoritesAddID)
    Button favoritesAddID;
    @Bind(R.id.favoritesDeleteID)
    Button favoritesDeleteID;
    @Bind(R.id.rStoreHome)
    RelativeLayout rStoreHome;
    @Bind(R.id.rStoreGoods)
    RelativeLayout rStoreGoods;
    @Bind(R.id.rStoreNew)
    RelativeLayout rStoreNew;
    @Bind(R.id.rStoreActivity)
    RelativeLayout rStoreActivity;
    @Bind(R.id.btnStoreHome)
    TextView homeBtn;
    @Bind(R.id.btnStoreGoods)
    TextView goodsBtn;
    @Bind(R.id.btnStoreNew)
    TextView newBtn;
    @Bind(R.id.btnStoreActivity)
    TextView activeBtn;
    @Bind(R.id.llContent)
    LinearLayout llContent;
    @Bind(R.id.textIntroduce)
    TextView textIntroduce;
    @Bind(R.id.textGetQuan)
    TextView textGetQuan;
    @Bind(R.id.textCall)
    TextView textCall;
    @Bind(R.id.etSearchText)
    TextView etSearchText;
    @Bind(R.id.imgClassify)
    ImageView imgClassify;
    @Bind(R.id.imgMenu)
    ImageView imgMenu;
    @Bind(R.id.llSearch)
    LinearLayout llSearch;
    @Bind(R.id.linearLayout1)
    RelativeLayout linearLayout1;
    @Bind(R.id.img_home)
    ImageView imgHome;
    @Bind(R.id.img_goods)
    ImageView imgGoods;
    //    @Bind(R.id.btnStoreGoods) TextView btnStoreGoods;
    @Bind(R.id.img_new)
    ImageView imgNew;
    //    @Bind(R.id.btnStoreNew) TextView btnStoreNew;
    @Bind(R.id.img_activity)
    ImageView imgActivity;
    @Bind(R.id.bottomView)
    RelativeLayout bottomView;
//    @Bind(R.id.btnStoreActivity) TextView btnStoreActivity;

    private int storeId;
    private MyShopApplication application;
    protected StorePopWindow popWindow;
    FragmentManager fragmentManager = getSupportFragmentManager();

    private StoreIndexFragment storeIndexFragment;  //店铺首页
    private StoreSearchFragment storeAllGoodsFragment; //店铺全部商品
    //    private StoreAllGoodsFragment storeAllGoodsFragment; //店铺全部商品
    private StoreNewGoodsFragment storeNewGoodsFragment; //店铺上新
    private StoreActivitiesFragment storeActivityFragment; //店铺活动

    private StoreInfo storeInfo;
    private int isFavorate;
    private EvaluateStoreVo evaluateStoreVo;
    public final static String SER_KEY = "net.shopnc.b2b2c.android.domain";
    private StoreIntroduceVO storeIntroduceVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.store_info_view);
        ButterKnife.bind(this);

        storeId = getIntent().getIntExtra("storeId", 1);
        application = (MyShopApplication) this.getApplicationContext();
        LoadImage.loadLocalGreyImg(this, imgClassify, R.drawable.stiore_categroy_b);
        LoadImage.loadLocalGreyImg(this, imgMenu, R.drawable.nc_icon_more_dot);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.height = ScreenUtil.getScreenSize(this).y - DensityUtil.dip2px(this, 110);
        bottomView.setLayoutParams(params);

        loadStoreIndex();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStoreInfo();
    }

    public static final String TAG = "StoreInfo";

    /**
     * 加载店铺详细信息
     */
    private void loadStoreInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("storeId", storeId + "");
        params.put("token", application.getToken());
        Log.d(TAG, "loadStoreInfo: url = " + ConstantUrl.URL_STORE_INFO);
        Log.d(TAG, "loadStoreInfo: storeId = " + storeId);
        Log.d(TAG, "loadStoreInfo: token = " + application.getToken());

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_STORE_INFO, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                EventBus.getDefault().post(new StoreBusBaen(data));

                storeInfo = JsonUtil.toBean(data, "storeInfo", new TypeToken<StoreInfo>() {
                }.getType());
                StoreMobileVo mbTitleImg = JsonUtil.toBean(data, "mbTitleImg", new TypeToken<StoreMobileVo>() {
                }.getType());
                isFavorate = JsonUtil.toInteger(data, "isFavorite");
                evaluateStoreVo = JsonUtil.toBean(data, "evaluateStoreVo", new TypeToken<EvaluateStoreVo>() {
                }.getType());

                StoreMobile storeMobile = JsonUtil.toBean(data, "storeMobile", new TypeToken<StoreMobile>() {
                }.getType());

                if (storeMobile != null) {
                    LoadImage.loadRemoteImg(StoreInfoFragmentActivity.this, storeInFoPic, storeMobile.getStoreBannerUrl());
                }

                //设置实体
                storeIntroduceVO = new StoreIntroduceVO();
                storeIntroduceVO.isFavorate = isFavorate;
                storeIntroduceVO.storeInfo = storeInfo;
                storeIntroduceVO.evaluateStoreVo = evaluateStoreVo;

                //显示店铺信息
                storeNameID.setText(storeInfo.getStoreName());
                textFanCount.setText(String.valueOf(storeInfo.getStoreCollect()));
//                if (mbTitleImg != null) {
//                    LoadImage.loadRemoteImg(StoreInfoFragmentActivity.this, storeInFoPic, mbTitleImg.getFullPicUrl());
//                }
                LoadImage.loadRemoteImg(StoreInfoFragmentActivity.this, storePic, storeInfo.getStoreAvatarUrl());

                if (isFavorate == 1) {
                    favoritesAddID.setVisibility(View.GONE);
                    favoritesDeleteID.setVisibility(View.VISIBLE);
                } else {
                    favoritesAddID.setVisibility(View.VISIBLE);
                    favoritesDeleteID.setVisibility(View.GONE);
                }
            }
        }, params);
    }


    /**
     * 加载店铺首页信息
     */
    private void loadStoreIndex() {
        popWindow = new StorePopWindow(this);
        storeIndexFragment = (StoreIndexFragment) StoreBaseFragment.newInstance("index", storeId + "");
        storeAllGoodsFragment = (StoreSearchFragment) StoreBaseFragment.newInstance("goods", storeId + "");
        storeNewGoodsFragment = (StoreNewGoodsFragment) StoreBaseFragment.newInstance("new", storeId + "");
        storeActivityFragment = (StoreActivitiesFragment) StoreBaseFragment.newInstance("activity", storeId + "");

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.llContent, storeIndexFragment);
        transaction.add(R.id.llContent, storeAllGoodsFragment);
        transaction.add(R.id.llContent, storeNewGoodsFragment);
        transaction.add(R.id.llContent, storeActivityFragment);
        transaction.commit();

        //默认显示店铺首页
        showView(rStoreHome, homeBtn, storeIndexFragment, storeAllGoodsFragment, storeNewGoodsFragment, storeActivityFragment);
    }

    /**
     * 联系客服
     */
    private void callIM() {
        //TODO
        ShopHelper.call(this, textCall.getText().toString());
    }

    /**
     * 设置切换按钮状态
     */
    private void setTabButtonState(RelativeLayout rel, TextView text) {
        rStoreHome.setActivated(false);
        rStoreGoods.setActivated(false);
        rStoreNew.setActivated(false);
        rStoreActivity.setActivated(false);
        rel.setActivated(true);
        homeBtn.setSelected(false);
        goodsBtn.setSelected(false);
        newBtn.setSelected(false);
        activeBtn.setSelected(false);
        text.setSelected(true);
    }


    /**
     * 根据切换按钮显示不同界面
     */
    private void showView(RelativeLayout relativeLayout, TextView text, Fragment showFragment, Fragment hideFragemnt1, Fragment hideFragemnt2, Fragment hideFragemnt3) {
        setTabButtonState(relativeLayout, text);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(showFragment);
        transaction.hide(hideFragemnt1);
        transaction.hide(hideFragemnt2);
        transaction.hide(hideFragemnt3);
        transaction.commit();
    }


    @OnClick({R.id.rStoreHome, R.id.rStoreGoods, R.id.rStoreNew, R.id.rStoreActivity, R.id.imgMenu, R.id.btnBack, R.id.etSearchText,
            R.id.imgClassify, R.id.textIntroduce, R.id.textGetQuan, R.id.textCall})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rStoreHome:
                showView(rStoreHome, homeBtn, storeIndexFragment, storeAllGoodsFragment, storeNewGoodsFragment, storeActivityFragment);
                break;

            case R.id.rStoreGoods:
                showView(rStoreGoods, goodsBtn, storeAllGoodsFragment, storeIndexFragment, storeNewGoodsFragment, storeActivityFragment);
                break;

            case R.id.rStoreNew:
                showView(rStoreNew, newBtn, storeNewGoodsFragment, storeIndexFragment, storeAllGoodsFragment, storeActivityFragment);
                break;

            case R.id.rStoreActivity:
                showView(rStoreActivity, activeBtn, storeActivityFragment, storeIndexFragment, storeAllGoodsFragment, storeNewGoodsFragment);
                break;

            case R.id.btnBack:
                this.finish();
                break;

            case R.id.imgMenu:
                StorePopWindow.showPopupWindow(imgMenu);
                break;

            case R.id.etSearchText:
                HashMap<String, String> param = new HashMap<>();
                param.put("storeId", String.valueOf(storeId));
                Common.gotoActivity(this, StoreSearchActivity.class, false, param);
                break;

            case R.id.imgClassify:
                HashMap<String, String> params = new HashMap<>();
                params.put("storeId", String.valueOf(storeId));
                Common.gotoActivity(this, StoreSearchActivity.class, false, params);
                break;

            case R.id.textIntroduce:
                Intent i = new Intent(StoreInfoFragmentActivity.this, StoreIntroduceActivity.class);
                i.putExtra("storeId", storeId);
                startActivity(i);
                break;
            case R.id.textGetQuan:
                i = new Intent(StoreInfoFragmentActivity.this, VoucherListActivity.class);
                i.putExtra("storeId", String.valueOf(storeId));
                startActivity(i);
                break;

            case R.id.textCall:
                TToast.showShort(this, "联系客服");
                callIM();
                break;
        }
    }

    /**
     * 店铺收藏
     *
     * @param v
     */
    @OnClick(R.id.favoritesAddID)
    public void onFavoritesAdd(View v) {
        if (ShopHelper.isLogin(StoreInfoFragmentActivity.this)) {
            Map<String, String> params = new HashMap<>();
            params.put("storeId", String.valueOf(storeId));
            params.put("token", application.getToken());

            OkHttpUtil.postAsyn(this,ConstantUrl.URL_STORE_FAVORITE_ADD, new BeanCallback<String>() {
                @Override
                public void response(String data) {
                    favoritesAddID.setVisibility(View.GONE);
                    favoritesDeleteID.setVisibility(View.VISIBLE);
                    textFanCount.setText(String.valueOf(Integer.valueOf(textFanCount.getText().toString()) + 1));
                }
            }, params);
        }
    }

    /**
     * 取消店铺收藏
     *
     * @param v
     */
    @OnClick(R.id.favoritesDeleteID)
    public void onFavoritesDelete(View v) {
        if (ShopHelper.isLogin(StoreInfoFragmentActivity.this)) {
            Map<String, String> params = new HashMap<>();
            params.put("storeId", String.valueOf(storeId));
            params.put("token", application.getToken());

            OkHttpUtil.postAsyn(this,ConstantUrl.URL_STORE_FAVORITE_DEL, new BeanCallback<String>() {
                @Override
                public void response(String data) {
                    favoritesAddID.setVisibility(View.VISIBLE);
                    favoritesDeleteID.setVisibility(View.GONE);
                    int count = Integer.valueOf(textFanCount.getText().toString()) - 1;
                    if (count <= 0) {
                        count = 0;
                    }
                    textFanCount.setText(String.valueOf(count));
                }
            }, params);
        }
    }

}
