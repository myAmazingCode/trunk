package net.shopnc.b2b2c.android.ui.type;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.BatchPriceAdapter;
import net.shopnc.b2b2c.android.adapter.CommendGridViewAdapter;
import net.shopnc.b2b2c.android.adapter.ContractDetailGridViewAdapter;
import net.shopnc.b2b2c.android.adapter.GiftListViewAdapter;
import net.shopnc.b2b2c.android.adapter.GoodsEvaluateListViewAdapter;
import net.shopnc.b2b2c.android.adapter.ManSongRuleListViewAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.CommendList;
import net.shopnc.b2b2c.android.bean.ContractDetail;
import net.shopnc.b2b2c.android.bean.GiftArrayList;
import net.shopnc.b2b2c.android.bean.GoodsDetailStoreVoucherInfo;
import net.shopnc.b2b2c.android.bean.GoodsDetails;
import net.shopnc.b2b2c.android.bean.GoodsEvaluateInfo;
import net.shopnc.b2b2c.android.bean.GoodsHairInfo;
import net.shopnc.b2b2c.android.bean.GpsInfo;
import net.shopnc.b2b2c.android.bean.ImageListBean;
import net.shopnc.b2b2c.android.bean.ManSongInFo;
import net.shopnc.b2b2c.android.bean.ManSongRules;
import net.shopnc.b2b2c.android.bean.StoreAddressBean;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.bean.StoreO2oAddressInfo;
import net.shopnc.b2b2c.android.circlelibrary.ImageCycleView;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.custom.MyScrollView;
import net.shopnc.b2b2c.android.custom.NCAddressDialog;
import net.shopnc.b2b2c.android.custom.NCGoodsSpecPopupWindow;
import net.shopnc.b2b2c.android.custom.NCStoreVoucherPopupWindow;
import net.shopnc.b2b2c.android.custom.dialog.ClickBigImageDialog;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.adapter.AudioSwitchPagerAdapter;
import net.shopnc.b2b2c.android.ncinterface.INCOnAddressDialogConfirm;
import net.shopnc.b2b2c.android.ncinterface.INCOnNumModify;
import net.shopnc.b2b2c.android.ncinterface.INCOnStringModify;
import net.shopnc.b2b2c.android.ui.store.newStoreInFoActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 商品详细信息Fragment
 *
 * @author dqw
 * @Time 2015-7-14
 */
public class GoodsDetailFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private static final String ARG_GOODS_ID = "goods_id";
    public String goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsStorage;
    private int goodsNum = 1; //商品数量
    private int goodsLimit = 0;
    private String is_fcode;//是否为F码商品 1是 0否
    private String is_virtual;//是否为虚拟商品 1-是 0-否
    private String ifcart = "0";//购物车购买标志 1购物车 0不是
    private String store_id = "";//记录店铺ID
    private String storeName = "";//记录店铺名称
    private String t_id, t_name; //记录商家ID 名称
    private int goodsBodyFLag = 0;
    private OnFragmentInteractionListener mListener;

    //商品数量修改回调
    private INCOnNumModify incOnNumModify;
    //商品规格修改回调
    private INCOnStringModify incOnStringModify;

    //主layout
    private MyScrollView svMain;
    private Button btnShowGoodsDetail;
    //商品图切换
    private ViewPager vpImage;
    private ViewGroup group;
    private ArrayList<String> imageList = new ArrayList<String>();
    private ImageView[] tips;
    //分享、收藏
    private ImageButton btnGoodsShare, btnGoodsFav;
    private String goodsWapUrl;//记录图片地址
    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
    //商品价格
    private LinearLayout llSingleGoodsPrice;
    private MyGridView gvBatchPrice;
    BatchPriceAdapter batchPriceAdapter;
    //促销
    private LinearLayout llPromotion, llManSong, llGift;
    private MyListView lvManSong, lvGift;
    //店铺代金券
    private LinearLayout llStoreVoucher;
    private NCStoreVoucherPopupWindow pwVoucher;
    //配送区域
    private LinearLayout llHairInfo;
    private Button btnHairAreaName;
    private RelativeLayout ll_btnHairAreaName;
    private TextView tvHairIfStoreCn, tvHairContent;
    //商家信息
    String storeO2oAddressString;
    String storeO2oPhone;
    private LinearLayout llStoreO2o, llStoreO2oItem;
    private TextView tvStoreO2oName, tvStoreO2oAddress;
    private ImageButton btnStoreO2oPhone;
    private Button btnStoreO2oAllAdress;
    //消费保障
    private LinearLayout llService;
    private TextView tvService;
    private MyGridView gvContract;
    //店铺信息
    private LinearLayout llStoreInfo;
    private TextView tvStoreDescPoint;
    private TextView tvStoreDescText;
    private TextView tvStoreServicePoint;
    private TextView tvStoreServiceText;
    private TextView tvStoreDeliveryPoint;
    private TextView tvStoreDeliveryText;
    //评价
    private TextView tvEvaluateGoodPercent, tvEvaluateCount;
    private LinearLayout llEvaluateList;
    private RelativeLayout rlEvaluate;
    private MyListView lvEvaluateList;
    private GoodsEvaluateListViewAdapter goodsEvaluateListViewAdapter;
    //推荐商品
    private MyGridView gridViewCommend;
    private CommendGridViewAdapter commendAdapter;
    //底部按钮
//    private TextView imID, showCartLayoutID;
//    private BadgeView badge;
//    private Button addCartID, buyStepID;

    private ImageCycleView bannerGoodImages;


    //规格
    private String specString;
    private LinearLayout llspecNameID;
    private TextView specNameID;
    private NCGoodsSpecPopupWindow pwSpec;

    private TextView goodsNameID, goodsJingleID, goodsSalenumID,
            goodsPriceID, tvGoodsMarketPrice, tvStoreName, tvGoodsType;

    private MyShopApplication myApplication;

    private String mobile_body;//商品手机版详情

    private String goods_attr;//产品规格参数
    private StringBuilder image;

    //设置规格的参数
    private GoodsDetails goodsDetails;
    private String specList;

    //判断商品能否购买
    private boolean flag = false;
    private String errorMsg = "";

    private boolean isAudio = false;

    //    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            if(message.what==1){
//                badge.setText((String)message.obj);
//                Log.i("QING",(String)message.obj);
//                badge.setVisibility(View.VISIBLE);
//                badge.show();
//            }
//        }
//    };
    private Boolean isBootom = false;
//    private ArrayList<ImageView> viewList = new ArrayList<ImageView>();

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private LinearLayout layoutBranch;
    private TextView tv_store_name;
    private TextView tvStorePrice;
    private TextView tvStoreAddress;
    private ImageView ivCart;
    private TextView tvCheckBranch;

    //    public static ViewPager main_vp;
    public static GoodsDetailFragment newInstance(String goodsId) {
        GoodsDetailFragment fragment = new GoodsDetailFragment();
//        main_vp = vp;
        Bundle args = new Bundle();
        args.putString(ARG_GOODS_ID, goodsId);
        fragment.setArguments(args);
        return fragment;
    }

    public GoodsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            goodsId = getArguments().getString(ARG_GOODS_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_goods_detail, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        myApplication = (MyShopApplication) getActivity().getApplicationContext();

        initViewID(layout);

        loadingGoodsDetailsData();//初始化加载数据
        checkStoreBranch(goodsId);//初始化门店信息

        return layout;
    }

    /**
     * 初始化注册控件ID
     */

    public void initViewID(View layout) {
        //主layout
        svMain = layout.findViewById(R.id.svMain);
        //滑动到底部加载商品描述，体验不好暂时停用，后期进行优化

        svMain.setOnScrollToBottomLintener(new MyScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                isBootom = isBottom;
            }
        });

        svMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isBootom) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        ((GoodsDetailsActivity) getActivity()).changeFreagemt(1);
                    }
                }
                return false;
            }
        });

        ll_btnHairAreaName = layout.findViewById(R.id.ll_btnHairAreaName);
        ll_btnHairAreaName.setOnClickListener(this);
        btnShowGoodsDetail = layout.findViewById(R.id.btnShowGoodsDetail);
        btnShowGoodsDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((GoodsDetailsActivity) getActivity()).changeFreagemt(1);
            }
        });

        //商品图切换
        vpImage = layout.findViewById(R.id.vpImage);
        group = layout.findViewById(R.id.viewGroup);
        bannerGoodImages = layout.findViewById(R.id.bannerGoodImages);
        bannerGoodImages.setFocusable(true);
        bannerGoodImages.setFocusableInTouchMode(true);
        bannerGoodImages.requestFocus();
        bannerGoodImages.requestFocusFromTouch();

        //商品数据
        goodsNameID = layout.findViewById(R.id.goodsNameID);
        goodsJingleID = layout.findViewById(R.id.goodsJingleID);
        goodsSalenumID = layout.findViewById(R.id.goodsSalenumID);
        goodsPriceID = layout.findViewById(R.id.goodsPriceID);
        tvGoodsMarketPrice = layout.findViewById(R.id.tvGoodsMarketPrice);
        tvGoodsType = layout.findViewById(R.id.tvGoodsType);
        llSingleGoodsPrice = layout.findViewById(R.id.llSingleGoodsPrice);
        gvBatchPrice = layout.findViewById(R.id.gvBatchPrice);

        //分享按钮
        btnGoodsShare = layout.findViewById(R.id.btnGoodsShare);
        if (Constants.APP_ID.equals("") || Constants.APP_SECRET.equals("") || Constants.WEIBO_APP_KEY.equals("") || Constants.WEIBO_APP_SECRET.equals("") || Constants.QQ_APP_ID.equals("") || Constants.QQ_APP_KEY.equals("")) {
            btnGoodsShare.setVisibility(View.GONE);
        }

        btnGoodsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**开启默认分享面板，分享列表**/
                new ShareAction(GoodsDetailFragment.this.getActivity()).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SMS)
                        .withText(goodsName + "     " + Constants.WAP_GOODS_URL + goodsId + "     (" + getString(R.string.app_name) + ")")
                        .withTitle(goodsName)
                        .withTargetUrl(Constants.WAP_GOODS_URL + goodsId)
                        .setShareboardclickCallback(shareBoardlistener)
                        .open();
            }
        });

        //收藏按钮
        btnGoodsFav = layout.findViewById(R.id.btnGoodsFav);
        btnGoodsFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGoodsFavClick(view);
            }
        });

        //促销
        llPromotion = layout.findViewById(R.id.llPromotion);
        llManSong = layout.findViewById(R.id.llManSong);
        lvManSong = layout.findViewById(R.id.lvManSong);
        llGift = layout.findViewById(R.id.llGift);
        lvGift = layout.findViewById(R.id.lvGift);
        //店铺代金券
        llStoreVoucher = layout.findViewById(R.id.llStoreVoucher);

        //配送区域
        llHairInfo = layout.findViewById(R.id.llHairInfo);

        btnHairAreaName = layout.findViewById(R.id.btnHairAreaName);
        btnHairAreaName.setOnClickListener(this);
        tvHairIfStoreCn = layout.findViewById(R.id.tvHairIfStoreCn);
        tvHairContent = layout.findViewById(R.id.tvHairContent);
        //商家信息
        llStoreO2o = layout.findViewById(R.id.llStoreO2o);
        llStoreO2oItem = layout.findViewById(R.id.llStoreO2oItem);
        tvStoreO2oName = layout.findViewById(R.id.tvStoreO2oName);
        tvStoreO2oAddress = layout.findViewById(R.id.tvStoreO2oAddress);
        btnStoreO2oPhone = layout.findViewById(R.id.btnStoreO2oPhone);
        btnStoreO2oPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopHelper.call(getActivity(), storeO2oPhone);
            }
        });
        btnStoreO2oAllAdress = layout.findViewById(R.id.btnStoreO2oAllAddress);
        btnStoreO2oAllAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StoreO2oAddressActivity.class);
                intent.putExtra("address", storeO2oAddressString);
                startActivity(intent);
            }
        });

        //店铺信息
        tvStoreName = layout.findViewById(R.id.tvStoreName);
        tvStoreName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), newStoreInFoActivity.class);
                intent.putExtra("store_id", store_id);
                startActivity(intent);
            }
        });
        //消费保障
        llService = layout.findViewById(R.id.llService);
        tvService = layout.findViewById(R.id.tvService);
        gvContract = layout.findViewById(R.id.gvContract);
        //店铺信息
        llStoreInfo = layout.findViewById(R.id.llStoreInfo);
        tvStoreDescPoint = layout.findViewById(R.id.tvStoreDescPoint);
        tvStoreDescText = layout.findViewById(R.id.tvStoreDescText);
        tvStoreServicePoint = layout.findViewById(R.id.tvStoreServicePoint);
        tvStoreServiceText = layout.findViewById(R.id.tvStoreServiceText);
        tvStoreDeliveryPoint = layout.findViewById(R.id.tvStoreDeliveryPoint);
        tvStoreDeliveryText = layout.findViewById(R.id.tvStoreDeliveryText);
        //评价
        tvEvaluateGoodPercent = layout.findViewById(R.id.tvEvaluateGoodPercent);
        tvEvaluateCount = layout.findViewById(R.id.tvEvaluateCount);
        rlEvaluate = layout.findViewById(R.id.rlEvaluate);
        rlEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((GoodsDetailsActivity) getActivity()).changeFreagemt(2);
            }
        });
        llEvaluateList = layout.findViewById(R.id.llEvaluateList);
        lvEvaluateList = layout.findViewById(R.id.lvEvaluateList);
        goodsEvaluateListViewAdapter = new GoodsEvaluateListViewAdapter(getActivity());
        lvEvaluateList.setAdapter(goodsEvaluateListViewAdapter);
        //推荐商品
        commendAdapter = new CommendGridViewAdapter(getActivity());
        gridViewCommend = layout.findViewById(R.id.gridViewCommend);
        gridViewCommend.setAdapter(commendAdapter);
        gridViewCommend.setFocusable(false);
        //底部按钮
//        imID = (TextView) layout.findViewById(R.id.imID);
//        showCartLayoutID = (TextView) layout.findViewById(R.id.showCartLayoutID);
//        badge=new BadgeView(layout.getContext(),showCartLayoutID);
//        badge.setTextSize(10);
//        badge.setVisibility(View.GONE);
//        badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//        if(!(myApplication.getLoginKey().equals("null")||myApplication.getLoginKey().equals(""))){
//            setCartNumShow();
//        }
        //规格
        llspecNameID = layout.findViewById(R.id.llspecNameID);
        specNameID = layout.findViewById(R.id.specNameID);

//        addCartID = (Button) layout.findViewById(R.id.addCartID);
//        buyStepID = (Button) layout.findViewById(R.id.buyStepID);
        llspecNameID.setOnClickListener(this);

        //推荐商品列表点击监听
        gridViewCommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                CommendList bean = (CommendList) gridViewCommend.getItemAtPosition(arg2);
                if (bean != null) {
                    goodsId = bean.getGoods_id();
                    Constants.goodId = goodsId;
                    loadingGoodsDetailsData();
                    ((GoodsDetailsActivity) getActivity()).changeGoods(goodsId);
                    if (AudioSwitchPagerAdapter.player != null) {
                        AudioSwitchPagerAdapter.player.onDestroy();
                        AudioSwitchPagerAdapter.player = null;
                    }
                }

            }
        });

        //门店自提按钮
        layoutBranch = layout.findViewById(R.id.layout_branch);
        tv_store_name = layout.findViewById(R.id.tv_store_name);
        tvStorePrice = layout.findViewById(R.id.tv_store_price);
        tvStoreAddress = layout.findViewById(R.id.tv_store_address);
        ivCart = layout.findViewById(R.id.iv_cart);
        tvCheckBranch = layout.findViewById(R.id.tv_check_branch);
//        addCartID.setOnClickListener(this);
//        buyStepID.setOnClickListener(this);
//        showCartLayoutID.setOnClickListener(this);
//        imID.setOnClickListener(this);
    }


    public void setAreaName() {
        NCAddressDialog ncAddressDialog = new NCAddressDialog(getActivity());
        ncAddressDialog.setOnAddressDialogConfirm(new INCOnAddressDialogConfirm() {
            @Override
            public void onAddressDialogConfirm(String cityId, final String areaId, String ThreeareaId, final String areaInfo) {
                String url = Constants.URL_GOODS_CALC + "&goods_id=" + goodsId + "&area_id=" + areaId;

                RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            GoodsHairInfo goodsHairInfo = GoodsHairInfo.newInstance(json);
                            myApplication.setAreaId(areaId);
                            myApplication.setAreaName(areaInfo);
                            showHairInfo(goodsHairInfo);
                        } else {
                            ShopHelper.showApiError(getActivity(), json);
                        }
                    }
                });
            }
        });
        ncAddressDialog.show();
    }

    //显示轮播图
    private void setBanViews(final ArrayList<String> urlList) {
//        RelativeLayout.LayoutParams cParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                ShopHelper.getScreenHeight(context) / 2);
        RelativeLayout.LayoutParams cParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

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
                imageView.setTag(imageURL);
                LoadImage.loadRemoteImg(context, imageView, imageURL);
            }
        };
        bannerGoodImages.setImageResources(urlList, mAdCycleViewListener, false);
        bannerGoodImages.pushImageCycle();
    }

    //查询门店地址信息
    private void checkStoreBranch(String goods_id) {
        RemoteDataHandler.asyncDataStringGet(Constants.URL_STORE_ADDRESS+"&goods_id="+goods_id, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                     String info = data.getJson();
                    final StoreAddressBean bean = new Gson().fromJson(info, StoreAddressBean.class);
                    if (bean.getAddr_list().size() > 0) {
                        layoutBranch.setVisibility(View.VISIBLE);
                        tv_store_name.setText(bean.getAddr_list().get(0).getChain_name());
                        tvStorePrice.setText("门店价格：¥"+bean.getAddr_list().get(0).getShopnc_chain_price());
                        tvStoreAddress.setText("门店地址："+bean.getAddr_list().get(0).getChain_address()+"，电话："+
                        bean.getAddr_list().get(0).getChain_phone());
                        tvCheckBranch.setText("查看全部"+bean.getAddr_list().size()+"家门店地址");
                        tvCheckBranch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), StoreBranchActivity.class);
                                intent.putExtra("addressBean", bean);
                                startActivity(intent);
                            }
                        });
                    } else {
                        layoutBranch.setVisibility(View.GONE);
                    }
                }
            }
        });
    }


//    public  void  setCartNumShow(){
//        String url=Constants.URL_GET_CART_NUM;
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("key", myApplication.getLoginKey());
//        RemoteDataHandler.asyncPostDataString(url,params,new RemoteDataHandler.Callback() {
//            @Override
//            public void dataLoaded(ResponseData data) {
//                String json=data.getJson();
//                if (data.getCode() == HttpStatus.SC_OK) {
//                    try{
//                        JSONObject obj=new JSONObject(json);
//                        String num=obj.getString("cart_count");
//                        Message msg=new Message();
//                        msg.what=1;
//                        msg.obj=num;
//                        handler.sendMessage(msg);
//                    }catch (JSONException e){
//                        Toast.makeText(getActivity(),"获取购物车数量失败",Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//        });
//
//    }


    //设置单个监听是否分享成功
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform == SHARE_MEDIA.QQ) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "QQ分享成功啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.SINA) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "新浪微博分享成功啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.QZONE) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "QQ空间分享成功啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.WEIXIN) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "微信分享成功啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "微信朋友圈分享成功啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.SMS) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "短信分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

            if (platform == SHARE_MEDIA.QQ) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "QQ分享失败啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.SINA) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "新浪微博分享失败啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.QZONE) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "QQ空间分享失败啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.WEIXIN) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "微信分享失败啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "微信朋友圈分享失败啦", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.SMS) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "短信分享失败啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            if (platform == SHARE_MEDIA.QQ) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "QQ分享取消了", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.SINA) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "新浪微博分享取消了", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.QZONE) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "QQ空间分享取消了", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.WEIXIN) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "微信分享取消了", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "微信朋友圈分享取消了", Toast.LENGTH_SHORT).show();
            } else if (platform == SHARE_MEDIA.SMS) {
                Toast.makeText(GoodsDetailFragment.this.getActivity(), "短信分享取消了", Toast.LENGTH_SHORT).show();
            }
        }
    };

    //设置多平台分享监听
    private ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            UMImage image = new UMImage(GoodsDetailFragment.this.getActivity(), goodsWapUrl);
            if (share_media != SHARE_MEDIA.SMS) {
                new ShareAction(GoodsDetailFragment.this.getActivity()).setPlatform(share_media).setCallback(umShareListener)
                        .withText(goodsName + "     " + Constants.WAP_GOODS_URL + goodsId + "        (" + getString(R.string.app_name) + ")")
                        .withTargetUrl(Constants.WAP_GOODS_URL + goodsId)
                        .withMedia(image)
                        .withTitle(goodsName)
                        .share();
            } else {
                new ShareAction(GoodsDetailFragment.this.getActivity()).setPlatform(share_media).setCallback(umShareListener)
                        .withText(goodsName + "     " + Constants.WAP_GOODS_URL + goodsId + "        (" + getString(R.string.app_name) + ")")
                        .withTargetUrl(Constants.WAP_GOODS_URL + goodsId)
                        .withTitle(goodsName)
                        .share();

            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** 分享回调**/
        UMShareAPI.get(GoodsDetailFragment.this.getActivity()).onActivityResult(requestCode, resultCode, data);

    }


    /**
     * 初始化加载数据
     */
    public void loadingGoodsDetailsData() {
        String url = Constants.URL_GOODSDETAILS + "&goods_id=" + goodsId + "&key=" + myApplication.getLoginKey();

        if (!myApplication.getAreaId().equals("")) {
            url += "area_id=" + myApplication.getAreaId();
        }
        Logger.d(url);
        final long starttime = System.currentTimeMillis();
        showDialog(context, "正在加载中...");
//        progressDialog = new MyProgressDialog(getActivity(), "正在加载中...", R.drawable.progress_round);
//        progressDialog.show();
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
//                viewList.clear();
                dissMissDialog();
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String goods_info = obj.getString("goods_info");// 商品信息
                        GoodsDetails goodsBean = GoodsDetails.newInstanceList(goods_info);
                        //显示商品信息
                        showGoodsInfo(goodsBean);

                        //控制收藏按钮状态
                        String is_favorate = obj.optString("is_favorate");
                        if (is_favorate.equals("true")) {
                            //已收藏
                            btnGoodsFav.setSelected(true);
                        } else {
                            //未收藏
                            btnGoodsFav.setSelected(false);
                        }

                        //显示商品图片
                        String goods_image = obj.getString("goods_image"); //商品图片
                        String video_path = obj.getString("video_path"); //视频资源
//                        String video_path = "http://111.13.140.18/youku/656DBB491E357404DE463625/030008010057A045083F76003E8803A37A0BEB-7024-91AF-34FC-A52B56C8E4A2.mp4"; //视频资源
//                        Logger.d(video_path);
                        imageList.clear();
                        if (net.shopnc.b2b2c.android.common.StringUtils.isEmpty(video_path)) {
                            isAudio = false;
                        } else {
                            isAudio = true;
                            imageList.add(video_path);
                        }

                        imageList.addAll(Arrays.asList(goods_image.split(",")));
                        if (net.shopnc.b2b2c.android.common.StringUtils.isEmpty(video_path)) {
                            goodsWapUrl = imageList.get(0);
                        } else {
                            goodsWapUrl = imageList.get(1);
                        }
                        ArrayList<String> images = new ArrayList<>();
                        ArrayList<ImageListBean> imageListBeen = gson.fromJson(obj.getString("image_list"), new TypeToken<ArrayList<ImageListBean>>() {
                        }.getType());

                        for (int i = 0; i < imageListBeen.size(); i++) {
                            ImageListBean imageListBean = imageListBeen.get(i);
                            images.add(imageListBean.get_big());
                        }

//                        setBanViews(images);

                        initHeadImage();

                        //显示促销
                        String mansong_info = obj.getString("mansong_info");// 满即送信息
                        String gift_array = obj.getString("gift_array");// 赠品数组
                        showPromotion(mansong_info, gift_array);

                        //店铺代金券
                        String storeVoucher = obj.optString("voucher");
                        initStoreVoucher(storeVoucher);

                        //显示配送区域
                        String goodsHairInfoJson = obj.getString("goods_hair_info");
                        GoodsHairInfo goodsHairInfo = GoodsHairInfo.newInstance(goodsHairInfoJson);
                        myApplication.setAreaName("全国");
                        showHairInfo(goodsHairInfo);

                        //显示服务信息
                        String contractString = goodsBean.getContractlist();
                        ArrayList<ContractDetail> contractDetailArrayList = ContractDetail.newInstanceList(contractString);
                        if (contractDetailArrayList.size() > 0) {
                            ContractDetailGridViewAdapter contractDetailGridViewAdapter = new ContractDetailGridViewAdapter(getActivity());
                            contractDetailGridViewAdapter.setContractList(contractDetailArrayList);
                            gvContract.setAdapter(contractDetailGridViewAdapter);
                            llService.setVisibility(View.VISIBLE);
                        } else {
                            llService.setVisibility(View.GONE);
                        }

                        //店铺信息
                        String store_info = obj.getString("store_info");// 店铺信息
                        StoreInfo storeInfo = StoreInfo.newInstanceList(store_info);
                        tvService.setText("由“" + storeInfo.getStoreName() + "”销售和发货，并享受售后服务");
                        if (storeInfo.getIsOwnShop().equals("1")) {
                            //自营店铺不显示店铺信息
                            llStoreInfo.setVisibility(View.GONE);
                        } else {
                            tvStoreName.setText(storeInfo.getStoreName());
                            store_id = storeInfo.getStoreId();
                            storeName = storeInfo.getStoreName();
                            JSONObject creditObj = new JSONObject(storeInfo.getStoreCredit());
                            String desc = creditObj.getString("store_desccredit");
                            JSONObject objDesc = new JSONObject(desc);
                            setStoreCredit(objDesc.getString("credit"), objDesc.getString("percent_class"), tvStoreDescPoint, tvStoreDescText);
                            String service = creditObj.getString("store_servicecredit");
                            JSONObject objService = new JSONObject(service);
                            setStoreCredit(objService.getString("credit"), objService.getString("percent_class"), tvStoreServicePoint, tvStoreServiceText);
                            String delivery = creditObj.getString("store_deliverycredit");
                            JSONObject objDelivery = new JSONObject(delivery);
                            setStoreCredit(objDelivery.getString("credit"), objDelivery.getString("percent_class"), tvStoreDeliveryPoint, tvStoreDeliveryText);
                            llStoreInfo.setVisibility(View.VISIBLE);
                        }
                        t_id = storeInfo.getMemberId();
                        t_name = storeInfo.getMemberName();

                        //评价
                        String goodsEvaluateInfoString = obj.getString("goods_evaluate_info");
                        JSONObject goodsEvaluateInfoObj = new JSONObject(goodsEvaluateInfoString);
                        tvEvaluateGoodPercent.setText("好评率 " + goodsEvaluateInfoObj.getString("good_percent") + "%");
                        tvEvaluateCount.setText("(" + goodsEvaluateInfoObj.getString("all") + "人评价)");
                        String goodsEvalListString = obj.getString("goods_eval_list");
                        ArrayList<GoodsEvaluateInfo> goodsEvaluateInfoList = GoodsEvaluateInfo.newInstanceList(goodsEvalListString);
                        if (goodsEvaluateInfoList.size() > 0) {
                            llEvaluateList.setVisibility(View.VISIBLE);
                            goodsEvaluateListViewAdapter.setList(goodsEvaluateInfoList);
                            goodsEvaluateListViewAdapter.notifyDataSetChanged();
                        } else {
                            llEvaluateList.setVisibility(View.GONE);
                        }

                        //显示推荐商品列表
                        String goods_commend_list = obj.getString("goods_commend_list");
                        ArrayList<CommendList> commendLists = CommendList.newInstanceList(goods_commend_list);
                        commendAdapter.setCommendLists(commendLists);
                        commendAdapter.notifyDataSetChanged();

                        //虚拟商品
                        if (is_virtual.equals("1")) {
                            //虚拟商品不显示配送区域
                            llHairInfo.setVisibility(View.GONE);
                            //虚拟商品显示商家分店地址
                            loadStoreO2oInfo();
                        } else {
                            llStoreO2o.setVisibility(View.GONE);
                        }


                        if (!is_virtual.equals("1")) {
                            ifCanBuyS();
                        } else {
                            ifCanBuyV();
                        }

                        //初始化规格弹出窗口
                        initSpec(goodsBean, obj.getString("spec_list"));
                        goodsDetails = goodsBean;
                        specList = obj.getString("spec_list");
                        svMain.smoothScrollTo(0, 0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(getActivity(), json);
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
                setSpecStirng();
            }
        };

        incOnStringModify = new INCOnStringModify() {
            @Override
            public void onModify(String str) {
                goodsId = str;
                loadingGoodsDetailsData();
                ((GoodsDetailsActivity) getActivity()).changeGoods(goodsId);
                if (AudioSwitchPagerAdapter.player != null) {
                    AudioSwitchPagerAdapter.player.onDestroy();
                    AudioSwitchPagerAdapter.player = null;
                }
            }
        };

        //限购数量
        if (goodsBean.getUpper_limit() == null || goodsBean.getUpper_limit().equals("") || goodsBean.getUpper_limit().equals("0")) {
            goodsLimit = Integer.parseInt((goodsBean.getGoods_storage() == null ? "0" : goodsBean.getGoods_storage()) == "" ? "0" : goodsBean.getGoods_storage());
        }

        if (pwSpec == null) {
            pwSpec = new NCGoodsSpecPopupWindow(getActivity(), incOnNumModify, incOnStringModify, t_id, t_name, store_id, storeName);
        }
        pwSpec.setGoodsInfo(goodsName, goodsWapUrl, goodsPrice, goodsStorage, goodsId, ifcart, goodsNum, goodsLimit, is_fcode, is_virtual);
        pwSpec.setSpecInfo(specList, goodsBean.getSpec_name(), goodsBean.getSpec_value(), goodsBean.getGoods_spec());

       /* specNameID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwSpec.showPopupWindow();
            }
        });*/

    }

    /**
     * 初始化店铺代金券
     *
     * @param storeVoucher
     */
    private void initStoreVoucher(String storeVoucher) {
        final ArrayList<GoodsDetailStoreVoucherInfo> storeVoucherList = GoodsDetailStoreVoucherInfo.newInstanceList(storeVoucher);
        if (storeVoucherList.size() > 0) {
            llStoreVoucher.setVisibility(View.VISIBLE);
            llStoreVoucher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pwVoucher == null) {
                        pwVoucher = new NCStoreVoucherPopupWindow(getActivity());
                        pwVoucher.setStoreName(storeName);
                        pwVoucher.setVoucherList(storeVoucherList);
                    }
                    pwVoucher.showPopupWindow();
                }
            });
        } else {
            llStoreVoucher.setVisibility(View.GONE);
        }
    }

    AudioSwitchPagerAdapter audioSwitchPagerAdapter;

    /**
     * 商品图切换
     */
    public void initHeadImage() {
        group.removeAllViews();
        tips = new ImageView[imageList.size()];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.nc_page_on);
            } else {
                tips[i].setBackgroundResource(R.drawable.nc_page_off);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }
//        vpImage.setAdapter(new ImageSwitchPagerAdapter(getActivity(), imageList));

        audioSwitchPagerAdapter = new AudioSwitchPagerAdapter(getActivity(), imageList, isAudio);

        vpImage.setAdapter(audioSwitchPagerAdapter);
        vpImage.setOnPageChangeListener(this);

    }

    /**
     * 商品图选中点
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.nc_page_on);
            } else {
                tips[i].setBackgroundResource(R.drawable.nc_page_off);
            }
        }
    }

    /**
     * 设置店铺评分
     *
     * @param credit
     * @param type
     * @param tvPoint
     * @param tvText
     */
    private void setStoreCredit(String credit, String type, TextView tvPoint, TextView tvText) {
        tvPoint.setText(credit);
        if (type.equals("low")) {
            tvPoint.setTextColor(getResources().getColor(R.color.nc_green));
            tvText.setText("低");
            tvText.setBackgroundColor(getResources().getColor(R.color.nc_green));
        }
        if (type.equals("equal")) {
            tvPoint.setTextColor(getResources().getColor(R.color.nc_red));
            tvText.setText("平");
            tvText.setBackgroundColor(getResources().getColor(R.color.nc_red));
        }
        if (type.equals("high")) {
            tvPoint.setTextColor(getResources().getColor(R.color.nc_red));
            tvText.setText("高");
            tvText.setBackgroundColor(getResources().getColor(R.color.nc_red));
        }
    }


    /**
     * 显示配送区域信息
     *
     * @param goodsHairInfo
     */
    private void showHairInfo(GoodsHairInfo goodsHairInfo) {
        btnHairAreaName.setText(myApplication.getAreaName());//地址名称
        tvHairIfStoreCn.setText(goodsHairInfo.getIfStoreCn());//有货无货判断
        tvHairContent.setText(goodsHairInfo.getContent());//运费信息
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
//            addCartID.setVisibility(View.GONE);
        } else {
//            addCartID.setVisibility(View.VISIBLE);
        }

        mobile_body = goodsBean.getMobile_body();//
        goods_attr = goodsBean.getGoods_attr();//产品规格

        if (goodsBean.getIs_batch().equals("1")) {
            gvBatchPrice.setVisibility(View.VISIBLE);
            llSingleGoodsPrice.setVisibility(View.GONE);
            batchPriceAdapter = new BatchPriceAdapter(context, goodsBean.getBatch_price());
            gvBatchPrice.setAdapter(batchPriceAdapter);
        } else {
            gvBatchPrice.setVisibility(View.GONE);
            llSingleGoodsPrice.setVisibility(View.VISIBLE);
        }


        //显示商品名称
        goodsNameID.setText(goodsBean.getGoods_name() == null ? "" : goodsBean.getGoods_name());

        //判断是否显示商品说明
        if (goodsBean.getGoods_jingle() != null && !goodsBean.getGoods_jingle().equals("") && !goodsBean.getGoods_jingle().equals("null")) {
            goodsJingleID.setVisibility(View.VISIBLE);
            goodsJingleID.setText(Html.fromHtml(goodsBean.getGoods_jingle() == null ? "" : goodsBean.getGoods_jingle()));
        } else {
            goodsJingleID.setVisibility(View.GONE);
        }

        //标示商品类型
        if (goodsBean.getIs_appoint().equals("1")) {
            tvGoodsType.setText(R.string.text_appoint);
            tvGoodsType.setVisibility(View.VISIBLE);
            tvGoodsType.setBackgroundResource(R.color.text_yuyue);
        } else if (goodsBean.getIs_fcode().equals("1")) {
            tvGoodsType.setText(R.string.text_fcode);
            tvGoodsType.setVisibility(View.VISIBLE);
            tvGoodsType.setBackgroundResource(R.color.text_Fcode);
        } else if (goodsBean.getIs_presell().equals("1")) {
            tvGoodsType.setText(R.string.text_presell);
            tvGoodsType.setVisibility(View.VISIBLE);
            tvGoodsType.setBackgroundResource(R.color.text_yushou);
        } else if (goodsBean.getIs_virtual().equals("1")) {
            tvGoodsType.setText(R.string.text_virtual);
            tvGoodsType.setVisibility(View.VISIBLE);
            tvGoodsType.setBackgroundResource(R.color.text_xuni);
        }

        //显示商品价格
        if (goodsBean.getPromotion_price() != null && !goodsBean.getPromotion_price().equals("") && !goodsBean.getPromotion_price().equals("null")) {
            goodsPrice = goodsBean.getPromotion_price();
            goodsPriceID.setText("￥" + (goodsBean.getPromotion_price() == null ? "" : goodsBean.getPromotion_price()));
            tvGoodsMarketPrice.setText("￥" + (goodsBean.getGoods_price() == null ? "" : goodsBean.getGoods_price()));
            tvGoodsMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //显示促销
            if (goodsBean.getPromotion_type().equals("sole")) {
                tvGoodsType.setText("");
                tvGoodsType.setVisibility(View.VISIBLE);
                tvGoodsType.setBackgroundResource(R.drawable.nc_icon_mobile_price);
            } else if (goodsBean.getPromotion_type().equals("groupbuy")) {
                tvGoodsType.setText(R.string.text_groupbuy);
                tvGoodsType.setVisibility(View.VISIBLE);
                tvGoodsType.setBackgroundResource(R.color.text_tuangou);
            } else if (goodsBean.getPromotion_type().equals("xianshi")) {
                tvGoodsType.setText(R.string.text_xianshi);
                tvGoodsType.setVisibility(View.VISIBLE);
                tvGoodsType.setBackgroundResource(R.color.text_xianshi);
            } else if (goodsBean.getPromotion_type().equals("spike")) {
                tvGoodsType.setText(R.string.text_spike);
                tvGoodsType.setVisibility(View.VISIBLE);
                tvGoodsType.setBackgroundResource(R.color.text_xianshi);
            } else if (goodsBean.getPromotion_type().equals("flash")) {
                tvGoodsType.setText(R.string.text_flash);
                tvGoodsType.setVisibility(View.VISIBLE);
                tvGoodsType.setBackgroundResource(R.color.text_xianshi);
            }
        } else {
            goodsPrice = goodsBean.getGoods_price();
            goodsPriceID.setText("￥" + (goodsBean.getGoods_price() == null ? "0" : goodsBean.getGoods_price()));
        }

        //显示商品销量数量
        goodsSalenumID.setText(goodsBean.getGoods_salenum() == null ? "0" : goodsBean.getGoods_salenum() + "件");

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

        setSpecStirng();
    }

    /**
     * 加载O2o商家分店地址
     */
    private void loadStoreO2oInfo() {
        GpsInfo gpsInfo = ShopHelper.getGpsInfo(getActivity());
        String url = Constants.URL_GOODS_STORE_O2O_ADDR + "&store_id=" + store_id + "&lng=" + String.valueOf(gpsInfo.getLng()) + "&lat=" + String.valueOf(gpsInfo.getLat());
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        storeO2oAddressString = obj.optString("addr_list");
                        ArrayList<StoreO2oAddressInfo> storeO2oAddressList = StoreO2oAddressInfo.newInstanceList(storeO2oAddressString);
                        if (storeO2oAddressList.size() > 0) {
                            final StoreO2oAddressInfo storeO2oAddressInfo = storeO2oAddressList.get(0);
                            llStoreO2oItem.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getActivity(), BaiduMapActivity.class);
                                    ArrayList<GpsInfo> gpsList = new ArrayList<GpsInfo>();
                                    double lng = Double.valueOf(storeO2oAddressInfo.getLng());
                                    double lat = Double.valueOf(storeO2oAddressInfo.getLat());
                                    gpsList.add(new GpsInfo(lng, lat));
                                    intent.putParcelableArrayListExtra("gps_list", gpsList);
                                    startActivity(intent);
                                }
                            });
                            tvStoreO2oName.setText(storeO2oAddressInfo.getName());
                            tvStoreO2oAddress.setText(storeO2oAddressInfo.getAddress());
                            btnStoreO2oAllAdress.setText("查看全部" + storeO2oAddressList.size() + "家分店地址");
                            storeO2oPhone = storeO2oAddressInfo.getPhone();
                            llStoreO2o.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * 设置规格文字信息
     */
    private void setSpecStirng() {
        if (specString.equals("")) {
            specNameID.setText("默认 x" + goodsNum);
        } else {
            specNameID.setText(specString + " x" + goodsNum);
        }
    }

    /**
     * 显示促销信息
     *
     * @param mansong_info
     * @param gift_array
     */
    private void showPromotion(String mansong_info, String gift_array) {
        boolean mansongFlag = false;
        boolean giftFlag = false;

        //显示满即送
        if (mansong_info != null && !mansong_info.equals("") && !mansong_info.equals("[]") && !mansong_info.equals("null")) {
            mansongFlag = true;
            llManSong.setVisibility(View.VISIBLE);

            ManSongInFo manSongInFo = ManSongInFo.newInstanceList(mansong_info);
            ArrayList<ManSongRules> mRules = ManSongRules.newInstanceList(manSongInFo.getRules());
            ManSongRuleListViewAdapter manSongRuleListViewAdapter = new ManSongRuleListViewAdapter(getActivity());
            manSongRuleListViewAdapter.setList(mRules);
            lvManSong.setAdapter(manSongRuleListViewAdapter);
        } else {
            llManSong.setVisibility(View.GONE);
        }

        //显示赠品
        if (gift_array != null && !gift_array.equals("") && !gift_array.equals("[]") && !gift_array.equals("null")) {
            giftFlag = true;
            llGift.setVisibility(View.VISIBLE);

            ArrayList<GiftArrayList> giftArrayLists = GiftArrayList.newInstanceList(gift_array);
            final GiftListViewAdapter giftListViewAdapter = new GiftListViewAdapter(getActivity());
            giftListViewAdapter.setList(giftArrayLists);
            lvGift.setAdapter(giftListViewAdapter);
            lvGift.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    GiftArrayList gift = (GiftArrayList) giftListViewAdapter.getItem(i);
                    Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                    intent.putExtra("goods_id", gift.getGift_goodsid());
                    startActivity(intent);
                }
            });
        } else {
            llGift.setVisibility(View.GONE);
        }

        if (mansongFlag || giftFlag) {
            llPromotion.setVisibility(View.VISIBLE);
        } else {
            llPromotion.setVisibility(View.GONE);
        }
    }

    /**
     * 收藏按钮点击
     */
    public void btnGoodsFavClick(View view) {
        if (btnGoodsFav.isSelected()) {
            //取消收藏
            setGoodsFav(Constants.URL_DELETE_FAV, false);
        } else {
            //收藏商品
            setGoodsFav(Constants.URL_ADD_FAV, true);
        }
    }

    /**
     * 设置收藏商品
     *
     * @param url      接口地址
     * @param btnState 收藏商品为true 取消收藏为false
     */
    private void setGoodsFav(String url, final Boolean btnState) {
        HashMap<String, String> params = new HashMap<String, String>();
        if (btnState) {
            params.put("goods_id", goodsId);
        } else {
            params.put("fav_id", goodsId);
        }
        params.put("key", myApplication.getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    btnGoodsFav.setSelected(btnState);
                } else {
                    ShopHelper.showApiError(getActivity(), json);
                }
            }
        });
    }


    /**
     * 添加购物车
     */
    public void addCart(final Context context, final MyShopApplication myApplication, String goodsId, int goodsNum) {
        String url = Constants.URL_ADD_CART;

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("goods_id", goodsId);
        params.put("key", myApplication.getLoginKey());
        params.put("quantity", goodsNum + "");

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    Toast.makeText(context, "添加购物车成功", Toast.LENGTH_SHORT).show();
//                    setCartNumShow();
                    Intent intent = new Intent(Constants.SHOW_CART_NUM);
                    context.sendBroadcast(intent);
                } else {
                    ShopHelper.showApiError(context, json);
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.addCartID:
                if (flag == false) {
                    ShopHelper.showApiError(getActivity(), errorMsg);
                } else {
                    initSpec(goodsDetails, specList);
                    pwSpec.showPopupWindow();
                }

                /*if (ShopHelper.isLogin(getActivity(), myApplication.getLoginKey())) {

                    addCart(getActivity(), myApplication, goodsId, goodsNum);

                    Intent i=new Intent(Constants.SHOW_CART_NUM);
                    getActivity().sendBroadcast(i);
                }*/

                break;
            case R.id.buyStepID:
                if (ShopHelper.isLogin(getActivity(), myApplication.getLoginKey())) {
                    if (flag == false) {
                        ShopHelper.showApiError(getActivity(), errorMsg);
                    } else {
                        initSpec(goodsDetails, specList);
                        pwSpec.showPopupWindow();
                    }

                    /*if (is_virtual.equals("1")) {
                        intent = new Intent(getActivity(), VBuyStep1Activity.class);
                        intent.putExtra("is_fcode", is_fcode);
                        intent.putExtra("ifcart", "0");
                        intent.putExtra("goodscount", goodsNum);
                        intent.putExtra("cart_id", goodsId);
                    } else {
                        intent = new Intent(getActivity(), BuyStep1Activity.class);
                        intent.putExtra("is_fcode", is_fcode);
                        intent.putExtra("ifcart", "0");
                        intent.putExtra("cart_id", goodsId + "|" + goodsNum);
                    }*/
                }
                break;

            case R.id.llspecNameID:
                pwSpec.showPopupWindow();
                break;

            case R.id.imID:
                ShopHelper.showIm(getActivity(), myApplication, t_id, t_name);
                break;

            case R.id.showCartLayoutID:
                //跳转购物车
                intent = new Intent(getActivity(), MainFragmentManager.class);
                //广播通知显示购物车
                myApplication.sendBroadcast(new Intent(Constants.SHOW_CART_URL));
                pwSpec.closePoperWindow();
                break;
            case R.id.ll_btnHairAreaName:
            case R.id.btnHairAreaName:
                setAreaName();
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }


    /**
     * 判断是否能够购买商品--购买实物商品
     */
    private void ifCanBuyS() {
        String url = Constants.URL_BUY_STEP1;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("cart_id", goodsId + "|" + goodsNum);
        params.put("ifcart", "0");

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
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
        params.put("key", myApplication.getLoginKey());
        params.put("goods_id", goodsId);
        params.put("quantity", String.valueOf(goodsNum));

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        setImageBackground(i % imageList.size());
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        if (AudioSwitchPagerAdapter.player != null) {
            AudioSwitchPagerAdapter.player.onDestroy();
            AudioSwitchPagerAdapter.player = null;
        }
        super.onDestroy();
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        myApplication = (MyShopApplication) getActivity().getApplicationContext();
        ifCanBuyS();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (AudioSwitchPagerAdapter.player != null) {
            AudioSwitchPagerAdapter.player.onConfigurationChanged(newConfig);
        }
    }


}
