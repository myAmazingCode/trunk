package net.shopnc.b2b2c.android.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.HomeActivityMyGridViewListAdapter;
import net.shopnc.b2b2c.android.adapter.HomeGoodsMyGridViewListAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.AdvertList;
import net.shopnc.b2b2c.android.bean.Home1Data;
import net.shopnc.b2b2c.android.bean.Home2Data;
import net.shopnc.b2b2c.android.bean.Home3Data;
import net.shopnc.b2b2c.android.bean.HomeGoodsList;
import net.shopnc.b2b2c.android.bean.HomeVideo;
import net.shopnc.b2b2c.android.bracode.ui.CaptureActivity;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.OkHttpUtil;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.ViewFlipperScrollView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.fenxiao.FenXiaoAudeoListTabActivity;
import net.shopnc.b2b2c.android.pulltorefresh.library.PullToRefreshBase;
import net.shopnc.b2b2c.android.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import net.shopnc.b2b2c.android.pulltorefresh.library.PullToRefreshScrollView;
import net.shopnc.b2b2c.android.ui.fenxiao.FlashBuyActivity;
import net.shopnc.b2b2c.android.ui.fenxiao.SecondKillActivity;
import net.shopnc.b2b2c.android.ui.jifen.CreditsCenterActivity;
import net.shopnc.b2b2c.android.ui.mine.IMNewListActivity;
import net.shopnc.b2b2c.android.ui.mine.SigninActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsListFragmentManager;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 首页
 *
 * @author dqw
 * @Time 2015-8-17
 */
public class HomeFragment extends BaseFragment implements OnGestureListener, OnTouchListener {
//    private MyShopApplication myApplication;

    private Intent intent = null;

    private TextView tvSearch;
    //    private Button btnCamera;
    private View btnCamera;
    private LinearLayout llIm;
    private TextView tvSearchD;
    //    private Button btnCameraD;
    private View btnCameraD;
    private LinearLayout llImD;

    private LinearLayout llHomeGoodsClassify;//商品分类
    private LinearLayout llHomeCart;//购物车
    private LinearLayout llHomeMine;//我的商城
    private LinearLayout llHomeSignin;//每日签到


    private PullToRefreshScrollView mPullRefreshScrollView;
    private ViewFlipper viewflipper;
    private LinearLayout dian;
    private boolean showNext = true;
    private int currentPage = 0;
    private final int SHOW_NEXT = 0011;
    private float downNub;//记录按下时的距离

    private LinearLayout HomeView, tab_home_item_video;

    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;

    private GestureDetector mGestureDetector;
    private ViewFlipperScrollView myScrollView;
    private ArrayList<ImageView> viewList = new ArrayList<ImageView>();
    private Animation left_in, left_out, right_in, right_out;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    private LinearLayout homeSearch;
    private LinearLayout search;

    private Button toTopBtn;// 返回顶部的按钮
    private int scrollY = 0;// 标记上次滑动位置
    private View contentView;
    private ScrollView scrollView;
    private LinearLayout llCreditsCenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewLayout = inflater.inflate(R.layout.main_home_view, container, false);

        MyExceptionHandler.getInstance().setContext(getActivity());

        initViewID(viewLayout);//注册控件ID
        mGestureDetector = new GestureDetector(this);
        viewflipper.setOnTouchListener(this);
        myScrollView.setGestureDetector(mGestureDetector);

        return viewLayout;
    }

    /**
     * 初始化注册控件ID
     */
    public void initViewID(View view) {
//        myApplication = (MyShopApplication) getActivity().getApplicationContext();

        //搜索
        tvSearch = view.findViewById(R.id.tvSearch);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        tvSearchD = view.findViewById(R.id.tvSearchD);
        tvSearchD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        //摄像头
        btnCamera = /*(Button) */view.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });

        btnCameraD = /*(Button) */view.findViewById(R.id.btnCameraD);
        btnCameraD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });

        //IM
        llIm = view.findViewById(R.id.llIm);
        llIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
//                    startActivity(new Intent(getActivity(), IMFriendsListActivity.class));
                    startActivity(new Intent(getActivity(), IMNewListActivity.class));
                }
            }
        });

        llImD = view.findViewById(R.id.llImD);
        llImD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
//                    startActivity(new Intent(getActivity(), IMFriendsListActivity.class));
                    startActivity(new Intent(getActivity(), IMNewListActivity.class));
                }
            }
        });

        //商品分类
        llHomeGoodsClassify = view.findViewById(R.id.llHomeFavGoods);
        llHomeGoodsClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (ShopHelper.isLogin(getActivity(), myApplication.getLoginKey())) {
                    startActivity(new Intent(getActivity(), FavGoodsListActivity.class));
                }*/

                intent = new Intent(getActivity(), MainFragmentManager.class);
                application.sendBroadcast(new Intent(Constants.SHOW_Classify_URL));
                startActivity(intent);
            }
        });

        //购物车
        llHomeCart = view.findViewById(R.id.llHomeMyOrder);
        llHomeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    //startActivity(new Intent(getActivity(), OrderListActivity.class));
                    intent = new Intent(getActivity(), MainFragmentManager.class);
                    application.sendBroadcast(new Intent(Constants.SHOW_CART_URL));
                    startActivity(intent);

                }
            }
        });

        //我的商城
        llHomeMine = view.findViewById(R.id.llHomeMyAsset);
        llHomeMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (ShopHelper.isLogin(getActivity(), myApplication.getLoginKey())) {
                    startActivity(new Intent(getActivity(), MyAssetActivity.class));
                }*/
                intent = new Intent(getActivity(), MainFragmentManager.class);
                application.sendBroadcast(new Intent(Constants.SHOW_Mine_URL));
                startActivity(intent);
            }
        });

        //每日签到
        llHomeSignin = view.findViewById(R.id.llHomeSignin);
        llHomeSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), SigninActivity.class));

                }
//                intent = new Intent(getActivity(), SecondKillActivity.class);
//                startActivity(intent);
            }
        });

        llCreditsCenter = view.findViewById(R.id.llCreditsCenter);
        llCreditsCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreditsCenterActivity.class);
                startActivity(intent);
            }
        });



        mPullRefreshScrollView = view.findViewById(R.id.pull_refresh_scrollview);
        viewflipper = view.findViewById(R.id.viewflipper);
        dian = view.findViewById(R.id.dian);
        myScrollView = view.findViewById(R.id.viewFlipperScrollViewID);

        HomeView = view.findViewById(R.id.homeViewID);
        tab_home_item_video = view.findViewById(R.id.tab_home_item_video);

        left_in = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in);
        left_out = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out);
        right_in = AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in);
        right_out = AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out);

        homeSearch = view.findViewById(R.id.homeSearch);
        search = view.findViewById(R.id.search);

        homeSearch.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //下拉刷新监听

        mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                toTopBtn.setVisibility(View.GONE);
                search.setVisibility(View.GONE);
                homeSearch.setVisibility(View.VISIBLE);
                loadUIData();
            }
        });

        scrollView = mPullRefreshScrollView.getRefreshableView();
        if (contentView == null) {
            contentView = scrollView.getChildAt(0);
        }

        toTopBtn = view.findViewById(R.id.top_btn);
        toTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
                toTopBtn.setVisibility(View.GONE);
                search.setVisibility(View.GONE);
                homeSearch.setVisibility(View.VISIBLE);
            }
        });

        scrollView.setOnTouchListener(new OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = -9983761;
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    View scroller = (View) msg.obj;
                    if (msg.what == touchEventId) {
                        if (lastY == scroller.getScrollY()) {
                            handleStop(scroller);
                        } else {
                            handler.sendMessageDelayed(handler.obtainMessage(
                                    touchEventId, scroller), 5);
                            lastY = scroller.getScrollY();
                        }
                    }
                }
            };

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendMessageDelayed(
                            handler.obtainMessage(touchEventId, view), 5);
                }
                return false;
            }

            private void handleStop(Object view) {
                ScrollView scroller = (ScrollView) view;
                scrollY = scroller.getScrollY();
                doOnBorderListener();
            }
        });

        loadUIData();

        //读取热门关键词
        getSearchHot();

        //读取搜素关键词列表
        getSearchKeyList();
    }

    private void doOnBorderListener() {
        LogHelper.i("huting----1111", ScreenUtil.getScreenViewBottomHeight(scrollView) + "  "
                + scrollView.getScrollY() + " " + ScreenUtil
                .getScreenHeight(getActivity()));

        // 底部判断
        if (contentView != null
                && contentView.getMeasuredHeight() <= scrollView.getScrollY()
                + scrollView.getHeight()) {
            toTopBtn.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);
            homeSearch.setVisibility(View.GONE);
        } else if (scrollView.getScrollY() == 0) {//顶部判断
            toTopBtn.setVisibility(View.GONE);
            homeSearch.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
        } else if (scrollView.getScrollY() > 13) {
            toTopBtn.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);
            homeSearch.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化加载数据
     */
    public void loadUIData() {
        RemoteDataHandler.asyncDataStringGet(Constants.URL_HOME, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                mPullRefreshScrollView.onRefreshComplete();//加载完成下拉控件取消显示
                if (data.getCode() == HttpStatus.SC_OK) {
                    HomeView.removeAllViews(); //删除homeview所有View
                    tab_home_item_video.removeAllViews(); //删除homeview所有View
                    try {
                        String json = data.getJson();
                        JSONArray arr = new JSONArray(json);
                        Logger.d(json);
                        int size = null == arr ? 0 : arr.length();

                        for (int i = 0; i < size; i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            JSONObject JsonObj = new JSONObject(obj.toString());

                            if (!JsonObj.isNull("home1")) {
                                showHome1(JsonObj);
                            } else if (!JsonObj.isNull("home2")) {
                                showHome2(JsonObj);
                            } else if (!JsonObj.isNull("home4")) {
                                showHome4(JsonObj);
                            } else if (!JsonObj.isNull("home3")) {
                                showHome3(JsonObj);
                            } else if (!JsonObj.isNull("adv_list")) {
                                showAdvList(JsonObj);
                            } else if (!JsonObj.isNull("goods")) {
                                showGoods(JsonObj);
                            } else if (!JsonObj.isNull("video_list")) {     //视频接口
                                showVideoView(JsonObj);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getActivity(), R.string.load_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 显示商品块
     *
     * @param jsonObj
     * @throws JSONException
     */
    private void showGoods(JSONObject jsonObj) throws JSONException {
        String goodsJson = jsonObj.getString("goods");
        JSONObject itemObj = new JSONObject(goodsJson);
        String item = itemObj.getString("item");
        String title = itemObj.getString("title");

        if (!item.equals("[]")) {
            ArrayList<HomeGoodsList> goodsList = HomeGoodsList.newInstanceList(item);
            View goodsView = getActivity().getLayoutInflater().inflate(R.layout.tab_home_item_goods, null);
            TextView textView = goodsView.findViewById(R.id.TextViewTitle);
            MyGridView gridview = goodsView.findViewById(R.id.gridview);
            gridview.setFocusable(false);
            HomeGoodsMyGridViewListAdapter adapter = new HomeGoodsMyGridViewListAdapter(getActivity());
            adapter.setHomeGoodsList(goodsList);
            gridview.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            if (!title.equals("") && !title.equals("null") && title != null) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(title);
            } else {
                textView.setVisibility(View.GONE);
            }
            HomeView.addView(goodsView);
        }
    }

    /**
     * 显示广告块
     *
     * @param jsonObj
     * @throws JSONException
     */
    private void showAdvList(JSONObject jsonObj) throws JSONException {
        String advertJson = jsonObj.getString("adv_list");
        JSONObject itemObj = new JSONObject(advertJson);
        String item = itemObj.getString("item");
        if (!item.equals("[]")) {
            ArrayList<AdvertList> advertList = AdvertList.newInstanceList(item);
            if (advertList.size() > 0 && advertList != null) {
                initHeadImage(advertList);
            }
        }
    }


    /**
     * 显示Home3
     *
     * @param jsonObj
     * @throws JSONException
     */
    private void showHome3(JSONObject jsonObj) throws JSONException {
        String home3Json = jsonObj.getString("home3");
        Home3Data bean = Home3Data.newInstanceDetelis(home3Json);
        ArrayList<Home3Data> home3Datas = Home3Data.newInstanceList(bean.getItem());
        View home3View = getActivity().getLayoutInflater().inflate(R.layout.tab_home_item_home3, null);
        TextView textView = home3View.findViewById(R.id.TextViewTitle);
        MyGridView gridview = home3View.findViewById(R.id.gridview);
        gridview.setFocusable(false);
        HomeActivityMyGridViewListAdapter adapter = new HomeActivityMyGridViewListAdapter(getActivity());
        adapter.setHome3Datas(home3Datas);
        gridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (!bean.getTitle().equals("") && !bean.getTitle().equals("null") && bean.getTitle() != null) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(bean.getTitle());
        } else {
            textView.setVisibility(View.GONE);
        }

        HomeView.addView(home3View);
    }

    /**
     * 显示Home4
     *
     * @param jsonObj
     * @throws JSONException
     */
    private void showHome4(JSONObject jsonObj) throws JSONException {
        String home2Json = jsonObj.getString("home4");
        Home2Data bean = Home2Data.newInstanceDetelis(home2Json);
        View home4View = getActivity().getLayoutInflater().inflate(R.layout.tab_home_item_home2_rehit, null);
        TextView textView = home4View.findViewById(R.id.TextViewTitle);

        ImageView imageViewSquare = home4View.findViewById(R.id.ImageViewSquare);
        ImageView imageViewRectangle1 = home4View.findViewById(R.id.ImageViewRectangle1);
        ImageView imageViewRectangle2 = home4View.findViewById(R.id.ImageViewRectangle2);

        imageLoader.displayImage(bean.getSquare_image(), imageViewSquare, options, animateFirstListener);
        imageLoader.displayImage(bean.getRectangle1_image(), imageViewRectangle1, options, animateFirstListener);
        imageLoader.displayImage(bean.getRectangle2_image(), imageViewRectangle2, options, animateFirstListener);

        OnImageViewClick(imageViewSquare, bean.getSquare_type(), bean.getSquare_data());
        OnImageViewClick(imageViewRectangle1, bean.getRectangle1_type(), bean.getRectangle1_data());
        OnImageViewClick(imageViewRectangle2, bean.getRectangle2_type(), bean.getRectangle2_data());

        if (!bean.getTitle().equals("") && !bean.getTitle().equals("null") && bean.getTitle() != null) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(bean.getTitle());
        } else {
            textView.setVisibility(View.GONE);
        }

        HomeView.addView(home4View);
    }

    /**
     * 显示Home2
     *
     * @param jsonObj
     * @throws JSONException
     */
    private void showHome2(JSONObject jsonObj) throws JSONException {
        String home2Json = jsonObj.getString("home2");
        Home2Data bean = Home2Data.newInstanceDetelis(home2Json);
        View home2View = getActivity().getLayoutInflater().inflate(R.layout.tab_home_item_home2_left, null);
        TextView textView = home2View.findViewById(R.id.TextViewTitle);

        ImageView imageViewSquare = home2View.findViewById(R.id.ImageViewSquare);
        ImageView imageViewRectangle1 = home2View.findViewById(R.id.ImageViewRectangle1);
        ImageView imageViewRectangle2 = home2View.findViewById(R.id.ImageViewRectangle2);

        imageLoader.displayImage(bean.getSquare_image(), imageViewSquare, options, animateFirstListener);
        imageLoader.displayImage(bean.getRectangle1_image(), imageViewRectangle1, options, animateFirstListener);
        imageLoader.displayImage(bean.getRectangle2_image(), imageViewRectangle2, options, animateFirstListener);

        OnImageViewClick(imageViewSquare, bean.getSquare_type(), bean.getSquare_data());
        OnImageViewClick(imageViewRectangle1, bean.getRectangle1_type(), bean.getRectangle1_data());
        OnImageViewClick(imageViewRectangle2, bean.getRectangle2_type(), bean.getRectangle2_data());

        if (!bean.getTitle().equals("") && !bean.getTitle().equals("null") && bean.getTitle() != null) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(bean.getTitle());
        } else {
            textView.setVisibility(View.GONE);
        }

        HomeView.addView(home2View);
    }

    /**
     * 显示Home1 一张图片
     *
     * @param jsonObj
     * @throws JSONException
     */
    private void showHome1(JSONObject jsonObj) throws JSONException {
        String home1Json = jsonObj.getString("home1");
        Home1Data bean = Home1Data.newInstanceList(home1Json);
        View home1View = getActivity().getLayoutInflater().inflate(R.layout.tab_home_item_home1, null);
        TextView textView = home1View.findViewById(R.id.TextViewHome1Title01);
        ImageView imageView = home1View.findViewById(R.id.ImageViewHome1Imagepic01);

        if (!bean.getTitle().equals("") && !bean.getTitle().equals("null") && bean.getTitle() != null) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(bean.getTitle());
        } else {
            textView.setVisibility(View.GONE);
        }

        imageLoader.displayImage(bean.getImage(), imageView, options, animateFirstListener);
        OnImageViewClick(imageView, bean.getType(), bean.getData());
        HomeView.addView(home1View);
    }

    public void initHeadImage(ArrayList<AdvertList> list) {

        mHandler.removeMessages(SHOW_NEXT);

        //清除已有视图防止重复
        viewflipper.removeAllViews();
        dian.removeAllViews();
        viewList.clear();

        for (int i = 0; i < list.size(); i++) {
            AdvertList bean = list.get(i);
            ImageView imageView = new ImageView(HomeFragment.this.getActivity());
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setBackgroundResource(R.drawable.dic_av_item_pic_bg);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageLoader.displayImage(bean.getImage(), imageView, options, animateFirstListener);
            viewflipper.addView(imageView);
            OnImageViewClick(imageView, bean.getType(), bean.getData());

//            ImageView dianimageView = new ImageView(HomeFragment.this.getActivity());
//            LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT, 3, 1);
//            dianimageView.setLayoutParams(params);
//            imageView.setScaleType(ScaleType.FIT_XY);
//            dianimageView.setBackgroundResource(R.drawable.dian_select);
            ImageView localImageView = new ImageView(HomeFragment.this.getActivity());
            localImageView.setId(i);
            ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
            localImageView.setScaleType(localScaleType);
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
                    24, 24);
            localImageView.setLayoutParams(localLayoutParams);
            localImageView.setPadding(5, 5, 5, 5);
            localImageView.setImageResource(R.drawable.point_unfocused);

            dian.addView(localImageView);
            viewList.add(localImageView);
        }

        //mGestureDetector = new GestureDetector(this);
        viewflipper.setOnTouchListener(this);
        //myScrollView.setGestureDetector(mGestureDetector);

        if (viewList.size() > 1) {
            dian_select(currentPage);
            mHandler.sendEmptyMessageDelayed(SHOW_NEXT, 3800);
        }
    }

    public void OnImageViewClick(View view, final String type, final String data) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("keyword")) {//搜索关键字
                    Intent intent = new Intent(getActivity(), GoodsListFragmentManager.class);
                    intent.putExtra("keyword", data);
                    intent.putExtra("gc_name", data);
                    startActivity(intent);
                } else if (type.equals("special")) {//专题编号
                    Intent intent = new Intent(getActivity(), SubjectWebActivity.class);
                    intent.putExtra("data", Constants.URL_SPECIAL + "&special_id=" + data + "&type=html");
                    startActivity(intent);
                } else if (type.equals("goods")) {//商品编号
                    Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                    intent.putExtra("goods_id", data);
                    startActivity(intent);
                } else if (type.equals("url")) {//地址
                    Intent intent = new Intent(getActivity(), SubjectWebActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                } else if (type.equals("lottery")) {
                    Intent intent = new Intent(getActivity(), LotteryWebActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }
            }
        });
        /*view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean flag = false;
                if (-1 != SystemHelper.getNetworkType(getActivity())) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //  触摸时按下
                        downNub = event.getX();
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        // 触摸时移动
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        //  触摸时抬起
                        if (downNub == event.getX()) {
                            if (type.equals("keyword")) {//搜索关键字
                                Intent intent = new Intent(getActivity(), GoodsListFragmentManager.class);
                                intent.putExtra("keyword", data);
                                intent.putExtra("gc_name", data);
                                startActivity(intent);
                            } else if (type.equals("special")) {//专题编号
                                Intent intent = new Intent(getActivity(), SubjectWebActivity.class);
                                intent.putExtra("data", Constants.URL_SPECIAL + "&special_id=" + data + "&type=html");
                                startActivity(intent);
                            } else if (type.equals("goods")) {//商品编号
                                Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                                intent.putExtra("goods_id", data);
                                startActivity(intent);
                            } else if (type.equals("url")) {//地址
                                Intent intent = new Intent(getActivity(), SubjectWebActivity.class);
                                intent.putExtra("data", data);
                                startActivity(intent);
                            }
                        }
                    }
                    flag = true;
                }
                return flag;
            }
        });*/
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_NEXT:
                    if (showNext) {
                        // 从右向左滑动
                        showNextView();
                    } else {
                        // 从左向右滑动
                        showPreviousView();
                    }
                    mHandler.sendEmptyMessageDelayed(SHOW_NEXT, 3800);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    /**
     * 向左滑动
     */
    private void showNextView() {
        viewflipper.setInAnimation(left_in);
        viewflipper.setOutAnimation(left_out);
        viewflipper.showNext();
        currentPage++;
        if (currentPage == viewflipper.getChildCount()) {
            dian_unselect(currentPage - 1);
            currentPage = 0;
            dian_select(currentPage);
        } else {
            dian_select(currentPage);//第currentPage页
            dian_unselect(currentPage - 1);
        }
    }

    /**
     * 向右滑动
     */
    private void showPreviousView() {
        dian_select(currentPage);
        viewflipper.setInAnimation(right_in);
        viewflipper.setOutAnimation(right_out);
        viewflipper.showPrevious();
        currentPage--;
        if (currentPage == -1) {
            dian_unselect(currentPage + 1);
            currentPage = viewflipper.getChildCount() - 1;
            dian_select(currentPage);
        } else {
            dian_select(currentPage);
            dian_unselect(currentPage + 1);
        }
    }

    /**
     * 对应被选中的点的图片
     *
     * @param id
     */
    private void dian_select(int id) {
        ImageView img = viewList.get(id);
        img.setImageResource(R.drawable.point_focused);
//        img.setSelected(true);
    }

    /**
     * 对应未被选中的点的图片
     *
     * @param id
     */
    private void dian_unselect(int id) {
        ImageView img = viewList.get(id);
//        img.setSelected(false);
        img.setImageResource(R.drawable.point_unfocused);
    }

    /**
     * 获取搜索热词
     */
    private void getSearchHot() {
        RemoteDataHandler.asyncDataStringGet(Constants.URL_SEARCH_HOT, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();
                    try {
                        JSONObject obj = new JSONObject(json);
                        String hotInfoString = obj.getString("hot_info");
                        String searchHotName = "";
                        if (!hotInfoString.equals("[]")) {
                            JSONObject hotInfoObj = new JSONObject(hotInfoString);
                            searchHotName = hotInfoObj.getString("name");
                            application.setSearchHotName(searchHotName);
                            application.setSearchHotValue(hotInfoObj.getString("value"));
                        }
                        if (searchHotName != null && !searchHotName.equals("")) {
                            tvSearch.setHint(searchHotName);
                        } else {
                            tvSearch.setHint(R.string.default_search_text);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 获取搜索关键词列表
     */
    private void getSearchKeyList() {
        RemoteDataHandler.asyncDataStringGet(Constants.URL_SEARCH_KEY_LIST, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();
                    try {
                        ArrayList<String> searchKeyList = new ArrayList<String>();
                        JSONObject obj = new JSONObject(json);
                        String searchKeyListString = obj.getString("list");
                        if (!searchKeyListString.equals("[]")) {
                            JSONArray searchKeyListArray = new JSONArray(searchKeyListString);
                            int size = null == searchKeyListArray ? 0 : searchKeyListArray.length();
                            for (int i = 0; i < size; i++) {
                                String key = searchKeyListArray.getString(i);
                                searchKeyList.add(key);
                            }
                        }
                        application.setSearchKeyList(searchKeyList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {//开始向左滑动了
            if (viewList.size() > 1) {
                showNextView();
                showNext = true;
            }

        } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {//开始向右滑动了
            if (viewList.size() > 1) {
                showPreviousView();
                showNext = true;
            }
        }/*else if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE
                &&  Math.abs(velocityY) > FLING_MIN_VELOCITY){
            search.setVisibility(View.VISIBLE);
            homeSearch.setVisibility(View.GONE);

        }else if(e2.getY() - e1.getY() >= 300
                && Math.abs(velocityY) > FLING_MIN_VELOCITY){
            homeSearch.setVisibility(View.VISIBLE);
            homeSearch.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
        }*/
        return false;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                            float arg3) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    /*首页中直播咨询和点播入口*/
    public void showVideoView(JSONObject jsonObj) {
        try {
            String json = jsonObj.getString("video_list");
            HomeVideo homeVideo = JsonUtil.getBean(json, HomeVideo.class);
            if (homeVideo != null) {
                if ("1".equals(homeVideo.getVideo_isuse())) {
                    View VideoView = getActivity().getLayoutInflater().inflate(R.layout.tab_home_item_video, null);
                    TextView fenxiao_title = VideoView.findViewById(R.id.fenxiao_title);
                    Button fenxiao_more = VideoView.findViewById(R.id.fenxiao_more);
                    ImageView fenxiao_logo = VideoView.findViewById(R.id.fenxiao_logo);
                    LinearLayout ll_item = VideoView.findViewById(R.id.ll_item);

                    LinearLayout llShipin1 = VideoView.findViewById(R.id.llShipin1);
                    LinearLayout ll_zhibao1 = VideoView.findViewById(R.id.ll_zhibao1);
                    TextView fengxiao_r_title_1 = VideoView.findViewById(R.id.fengxiao_r_title_1);
                    TextView fengxiao_r_brief_1 = VideoView.findViewById(R.id.fengxiao_r_brief_1);
                    ImageView fengxiao_r_img_1 = VideoView.findViewById(R.id.fengxiao_r_img_1);

                    LinearLayout llShipin2 = VideoView.findViewById(R.id.llShipin2);
                    LinearLayout ll_zhibao2 = VideoView.findViewById(R.id.ll_zhibao2);
                    TextView fengxiao_r_title_2 = VideoView.findViewById(R.id.fengxiao_r_title_2);
                    TextView fengxiao_r_brief_2 = VideoView.findViewById(R.id.fengxiao_r_brief_2);
                    ImageView fengxiao_r_img_2 = VideoView.findViewById(R.id.fengxiao_r_img_2);

                    LinearLayout llShipin3 = VideoView.findViewById(R.id.llShipin3);
                    LinearLayout ll_zhibao3 = VideoView.findViewById(R.id.ll_zhibao3);
                    TextView fengxiao_r_title_3 = VideoView.findViewById(R.id.fengxiao_r_title_3);
                    TextView fengxiao_r_brief_3 = VideoView.findViewById(R.id.fengxiao_r_brief_3);
                    ImageView fengxiao_r_img_3 = VideoView.findViewById(R.id.fengxiao_r_img_3);

                    fenxiao_title.setText(homeVideo.getVideo_modules_name());

                    fenxiao_title.setOnClickListener(new FenXiaoAudeoListTabOnclienr(""));
                    fenxiao_more.setOnClickListener(new FenXiaoAudeoListTabOnclienr(""));
                    fenxiao_logo.setOnClickListener(new FenXiaoAudeoListTabOnclienr(""));
                    String logo = homeVideo.getVideo_modules_logo();
                    if (!net.shopnc.b2b2c.android.common.StringUtils.isEmpty(logo)) {
                        LoadImage.loadImg(getActivity(), fenxiao_logo, logo);
                    }

                    int size = homeVideo.getItem().size();

                    switch (size) {
                        case 1:
                            llShipin1.setVisibility(View.VISIBLE);
                            llShipin2.setVisibility(View.INVISIBLE);
                            llShipin3.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            llShipin1.setVisibility(View.VISIBLE);
                            llShipin2.setVisibility(View.VISIBLE);
                            llShipin3.setVisibility(View.INVISIBLE);
                            break;
                        case 3:
                            llShipin1.setVisibility(View.VISIBLE);
                            llShipin2.setVisibility(View.VISIBLE);
                            llShipin3.setVisibility(View.VISIBLE);
                            break;
                    }


                    for (int i = 0; i < size; i++) {
                        if (i == 0) {
                            fengxiao_r_title_1.setText(homeVideo.getItem().get(i).getCate_name());
                            fengxiao_r_brief_1.setText(homeVideo.getItem().get(i).getCate_description());
//                            LoadImage.loadImg(getActivity(),fengxiao_r_img_1,homeVideo.getItem().get(i).getCate_image());
                            imageLoader.displayImage(homeVideo.getItem().get(i).getCate_image(), fengxiao_r_img_1, options, animateFirstListener);
                            ll_zhibao1.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_title_1.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_brief_1.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_img_1.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                        }
                        if (i == 1) {
                            fengxiao_r_title_2.setText(homeVideo.getItem().get(i).getCate_name());
                            fengxiao_r_brief_2.setText(homeVideo.getItem().get(i).getCate_description());
//                            LoadImage.loadImg(getActivity(),fengxiao_r_img_2,homeVideo.getItem().get(i).getCate_image());
                            imageLoader.displayImage(homeVideo.getItem().get(i).getCate_image(), fengxiao_r_img_2, options, animateFirstListener);
                            ll_zhibao2.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_title_2.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_brief_2.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_img_2.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                        }
                        if (i == 2) {
                            fengxiao_r_title_3.setText(homeVideo.getItem().get(i).getCate_name());
                            fengxiao_r_brief_3.setText(homeVideo.getItem().get(i).getCate_description());
//                            LoadImage.loadImg(getActivity(),fengxiao_r_img_3,homeVideo.getItem().get(i).getCate_image());
                            imageLoader.displayImage(homeVideo.getItem().get(i).getCate_image(), fengxiao_r_img_3, options, animateFirstListener);
                            ll_zhibao3.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_title_3.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_brief_3.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                            fengxiao_r_img_3.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
                        }

//                        View VideoView_item = getActivity().getLayoutInflater().inflate(R.layout.tab_home_item_video_item, null);
//                        LinearLayout ll_zhibao0 = (LinearLayout)VideoView_item.findViewById(R.id.ll_zhibao0);
//                        TextView fengxiao_r_title_0 = (TextView)VideoView_item.findViewById(R.id.fengxiao_r_title_0);
//                        TextView fengxiao_r_brief_0 = (TextView)VideoView_item.findViewById(R.id.fengxiao_r_brief_0);
//                        ImageView fengxiao_r_img_0 = (ImageView)VideoView_item.findViewById(R.id.fengxiao_r_img_0);
//                        fengxiao_r_title_0.setText(homeVideo.getItem().get(i).getCate_name());
//                        fengxiao_r_brief_0.setText(homeVideo.getItem().get(i).getCate_description());
//                        LoadImage.loadImg(getActivity(),fengxiao_r_img_0,homeVideo.getItem().get(i).getCate_image());
//
//                        ll_zhibao0.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
//                        fengxiao_r_title_0.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
//                        fengxiao_r_brief_0.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
//                        fengxiao_r_img_0.setOnClickListener(new FenXiaoAudeoListTabOnclienr(homeVideo.getItem().get(i).getCate_id()));
//                        ll_item.addView(VideoView_item);
                    }
                    tab_home_item_video.addView(VideoView);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class FenXiaoAudeoListTabOnclienr implements View.OnClickListener {

        private String mCate_id;

        public FenXiaoAudeoListTabOnclienr(String cate_id) {
            this.mCate_id = cate_id;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), FenXiaoAudeoListTabActivity.class);
            intent.putExtra("cate_id", mCate_id);
            startActivity(intent);
        }

    }


}
