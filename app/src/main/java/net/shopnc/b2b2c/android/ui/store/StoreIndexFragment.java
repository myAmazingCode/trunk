package net.shopnc.b2b2c.android.ui.store;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.StoreGoodsMyGridViewListAdapter;
import net.shopnc.b2b2c.android.bean.GoodsList;
import net.shopnc.b2b2c.android.bean.StoreIndex;
import net.shopnc.b2b2c.android.bean.StoreIndexHome1;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.ViewFlipperScrollView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.home.SubjectWebActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 店铺首页fragment
 *
 * Created by huting on 2015/12/29.
 */
public class StoreIndexFragment extends Fragment implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private MyShopApplication myApplication;
    private static final String ARG_STORE_ID = "store_id";
    private String store_id;

    private Button btnCollectOrder;//店铺收藏排行
    private Button btnSaleOrder;//销量排行

    private ImageView imgBig;//排行的大图片
    private ImageView imgSmallOne;//第一张小图片
    private ImageView imgSmallTwo;//第二张小图片

    private RelativeLayout ret1;
    private RelativeLayout ret2;
    private RelativeLayout ret3;
    private LinearLayout llRank;

    private TextView textSaleCount;
    private TextView textMoney;
    private TextView textSaleCountOne;
    private TextView textSaleCountTwo;

    private ViewFlipperScrollView myScrollView;//店铺轮播图
    private ViewFlipper viewflipper;
    private LinearLayout dian;

    private MyGridView sotreGoodsGridViewID;//店铺商品展示
    private StoreGoodsMyGridViewListAdapter goodsListViewAdapter;
    private List<GoodsList> list = new ArrayList<GoodsList>();

    private String ordertype;
    private float downNub;//记录按下时的距离
    private int viewfalg = 0;
    private ArrayList<ImageView> viewList = new ArrayList<ImageView>();
    private GestureDetector mGestureDetector;
    private int currentPage = 0;
    private boolean showNext = true;
    private final int SHOW_NEXT = 0011;
    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;
    private Animation left_in, left_out, right_in, right_out;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public StoreIndexFragment() {}
    public static StoreIndexFragment newInstance(String store_id) {
        StoreIndexFragment fragment = new StoreIndexFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STORE_ID, store_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            store_id = getArguments().getString(ARG_STORE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_store_index, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        myApplication = (MyShopApplication) getActivity().getApplicationContext();

        initView(layout);
        initStateTabButton();
        return layout;
    }

    /**
     * 初始化控件
     * @param layout
     */
    private void initView(View layout){
        btnCollectOrder = layout.findViewById(R.id.btnCollectOrder);
        btnSaleOrder = layout.findViewById(R.id.btnSaleOrder);

        textSaleCount = layout.findViewById(R.id.textSaleCount);
        textMoney = layout.findViewById(R.id.textMoney);
        textSaleCountOne = layout.findViewById(R.id.textSaleCountOne);
        textSaleCountTwo = layout.findViewById(R.id.textSaleCountTwo);

        ret1 = layout.findViewById(R.id.ret1);
        ret2 = layout.findViewById(R.id.ret2);
        ret3 = layout.findViewById(R.id.ret3);
        llRank = layout.findViewById(R.id.llRank);

        //店铺轮播
        viewflipper = layout.findViewById(R.id.viewflipper);
        dian = layout.findViewById(R.id.dian);
        myScrollView = layout.findViewById(R.id.viewFlipperScrollViewID);

        imgBig = layout.findViewById(R.id.imgBig);
        imgSmallOne = layout.findViewById(R.id.imgSmallOne);
        imgSmallTwo = layout.findViewById(R.id.imgSmallTwo);

        sotreGoodsGridViewID = layout.findViewById(R.id.sotreGoodsGridViewID);
        goodsListViewAdapter = new StoreGoodsMyGridViewListAdapter(getActivity());
        sotreGoodsGridViewID.setAdapter(goodsListViewAdapter);

        setOrderStateType("collectdesc");//默认显示收藏排行
        initData(store_id);//初始化加载店铺信息
        sotreGoodsGridViewID.setFocusable(false);

    }

    /**
     * 初始化订单状态切换按钮
     */
    private void initStateTabButton() {
        btnCollectOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrderStateType("collectdesc");
            }
        });
        btnSaleOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrderStateType("salenumdesc");
            }
        });

    }

    /**
     * 设置状态
     */
    private void setOrderStateType(String type) {
        ordertype = type;
        ordertype = type;
        btnCollectOrder.setActivated(false);
        btnSaleOrder.setActivated(false);

        if (ordertype.equals("collectdesc")) {
            btnCollectOrder.setActivated(true);
        } else if (ordertype.equals("salenumdesc")) {
            btnSaleOrder.setActivated(true);
        }

        initOrderData(type);//加载数据
    }

    /**
     * 初始化数据（店铺排行榜）
     * @param type
     */
    private void initOrderData(String type){
        String url = Constants.URL_STORE_RANKING;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("store_id", store_id);
        params.put("ordertype", type);
        params.put("num", Constants.STORE_RANKING_NUM);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String goods_list = obj.getString("goods_list");

                        if (goods_list.equals("") || goods_list == null || goods_list.equals(null)) {
                            llRank.setVisibility(View.GONE);
                        } else {
                            JSONArray jsonArray = new JSONArray(goods_list);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                showRankingView(jsonObject, i);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String error = obj2.getString("error");
                        if (error != null) {
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 初始化界面数据(店主推荐)
     */
    private void initData(String store_id){
        String url = Constants.URL_STORE_INFO + "&store_id=" + store_id + "&key=" + myApplication.getLoginKey();

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String store_info = obj.getString("store_info");
                        String rec_goods_list = obj.getString("rec_goods_list");
                        StoreIndex storeInFo = StoreIndex.newInstanceList(store_info);

                        if (storeInFo != null) {
                            ArrayList<GoodsList> goodsList = GoodsList.newInstanceList(rec_goods_list);
                            if (storeInFo != null && storeInFo.getMb_sliders() != null && storeInFo.getMb_sliders().size() > 0) {
                                if (viewfalg == 0) {
                                    initHeadImage(storeInFo.getMb_sliders());
                                }
                            }
                            goodsListViewAdapter.setGoodsList(goodsList);
                            goodsListViewAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 排行界面的初始化
     * @param jsonObj
     */
    private void showRankingView(JSONObject jsonObj,int i){
        if (!jsonObj.equals("[]") && i == 0) {
           StoreIndexHome1 bean = StoreIndexHome1.newInstanceList(jsonObj);
           LoadImage.loadImg(getActivity(),imgBig,bean.getGoods_image_url());

            ret1.setVisibility(View.VISIBLE);
            textSaleCount.setText("已售" + bean.getGoods_salenum());
            textMoney.setText("￥"+bean.getGoods_price());
            OnImageViewClick(imgBig, bean.getGoods_id());

        }else if(!jsonObj.equals("[]") && i == 1){
            StoreIndexHome1 bean = StoreIndexHome1.newInstanceList(jsonObj);
            LoadImage.loadImg(getActivity(), imgSmallOne, bean.getGoods_image_url());

            ret2.setVisibility(View.VISIBLE);
            textSaleCountOne.setText("已售" + bean.getGoods_salenum());
            OnImageViewClick(imgSmallOne, bean.getGoods_id());

        }else if(!jsonObj.equals("[]") && i == 2){
            StoreIndexHome1 bean = StoreIndexHome1.newInstanceList(jsonObj);
            LoadImage.loadImg(getActivity(), imgSmallTwo, bean.getGoods_image_url());
            ret3.setVisibility(View.VISIBLE);
            textSaleCountTwo.setText("已售" + bean.getGoods_salenum());
            OnImageViewClick(imgSmallTwo, bean.getGoods_id());
        }
    }


    public void initHeadImage(ArrayList<StoreIndex> list) {
        //清除已有视图防止重复
        viewflipper.removeAllViews();
        dian.removeAllViews();
        viewList.clear();

        for (int i = 0; i < list.size(); i++) {
            StoreIndex bean = list.get(i);
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(R.drawable.dic_av_item_pic_bg);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageLoader.displayImage(bean.getImgUrl(), imageView, options, animateFirstListener);
            viewflipper.addView(imageView);
            OnScrollImageViewClick(imageView, bean.getType(), bean.getLink());

            ImageView dianimageView = new ImageView(getActivity());
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 3, 1);
            dianimageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            dianimageView.setBackgroundResource(R.drawable.dian_select);
            dian.addView(dianimageView);
            viewList.add(dianimageView);
        }

        mGestureDetector = new GestureDetector(this);
        viewflipper.setOnTouchListener(this);
        myScrollView.setGestureDetector(mGestureDetector);

        viewfalg = viewList.size();
        if ( viewfalg > 1) {
            dian_select(currentPage);
            mHandler.sendEmptyMessageDelayed(SHOW_NEXT, 4000);
        }
    }

    public void OnScrollImageViewClick(View view, final String type, final String data) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //  触摸时按下
                    downNub = event.getX();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // 触摸时移动
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //  触摸时抬起
                    if (downNub == event.getX()) {
                        if (type.equals("1")) {//1: URL地址
                            Intent intent = new Intent(getActivity(), SubjectWebActivity.class);
                            intent.putExtra("data", data);
                            StoreIndexFragment.this.startActivity(intent);

                            /*Intent intent = new Intent(getActivity(), SubjectWebActivity.class);
                            intent.putExtra("data", Constants.URL_SPECIAL + "&special_id=" + data + "&type=html");
                            startActivity(intent);*/
                        } else if (type.equals("2")) {//2: 商品ID
                            Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                            intent.putExtra("goods_id", data);
                            StoreIndexFragment.this.startActivity(intent);
                        }

                    }
                }
                return true;
            }
        });
    }


    public void OnImageViewClick(View view, final String data) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //  触摸时按下
                    downNub = event.getX();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    // 触摸时移动
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //  触摸时抬起
                    if (downNub == event.getX()) {
                        Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                        intent.putExtra("goods_id", data);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
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
        img.setSelected(true);
    }

    /**
     * 对应未被选中的点的图片
     *
     * @param id
     */
    private void dian_unselect(int id) {
        ImageView img = viewList.get(id);
        img.setSelected(false);
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
        }
        return false;
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
