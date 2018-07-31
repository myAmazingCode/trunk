package net.shopnc.b2b2c.android.ui.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
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
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.custom.CustomScrollView;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.ViewFlipperScrollView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 店铺详情页
 *
 * @author KingKong-HE
 * @Time 2015-1-6
 * @Email KingKong@QQ.COM
 */
public class StoreInFoActivity extends Activity implements OnGestureListener, OnTouchListener, OnClickListener {

    private MyShopApplication myApplication;

    private ViewFlipper viewflipper;

    private LinearLayout dian;

    private boolean showNext = true;

    private int currentPage = 0;

    private final int SHOW_NEXT = 0011;

    private float downNub;//记录按下时的距离

    private LinearLayout HomeView;

    private static final int FLING_MIN_DISTANCE = 50;

    private static final int FLING_MIN_VELOCITY = 0;

    private GestureDetector mGestureDetector;

    private ViewFlipperScrollView myScrollView;

    private CustomScrollView SView;

    private TextView storeNameID;

    private TextView goodsCountID;

    private TextView tvStoreCredit;

    private TextView storeCollectID;

    private ImageView storePic;

    private ImageView storeInFoPic;

    private Button favoritesAddID, favoritesDeleteID;

    private MyGridView sotreGoodsGridViewID;

    private String store_id;//记录店铺ID

    private String store_name;//记录店铺名称

    private ArrayList<ImageView> viewList = new ArrayList<ImageView>();

    private Animation left_in, left_out, right_in, right_out;

    private StoreGoodsMyGridViewListAdapter goodsListViewAdapter;

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();

    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    private int count = 0;
    private TimerTask task;
    private Timer delayTimer;
    private long interval = 500;
    private long firstTime = 0;
    // 点击事件结束后的事件处理
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (count == 1) {
//                System.out.println("单击事件");
            } else if (count > 1) {
//            	System.out.println("连续点击事件，共点击了 " + count + " 次");
            }
            delayTimer.cancel();
            count = 0;
            String key = myApplication.getLoginKey();
            int id = msg.arg1;
            if (id == 0) {
                AddFav();
            } else {
                DeleteFav();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_info_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();

        initViewID();//初始化注册控件ID

    }

    // 延迟时间是连击的时间间隔有效范围
    private void delay(final int cns_id) {
        if (task != null)
            task.cancel();

        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                // message.what = 0;
                message.arg1 = cns_id;
                handler.sendMessage(message);
            }
        };
        delayTimer = new Timer();
        delayTimer.schedule(task, interval);
    }

    /**
     * 初始化注册控件ID
     */
    public void initViewID() {

        SView = findViewById(R.id.SView);

        viewflipper = findViewById(R.id.viewflipper);

        dian = findViewById(R.id.dian);

        myScrollView = findViewById(R.id.viewFlipperScrollViewID);

        storeNameID = findViewById(R.id.storeNameID);

        goodsCountID = findViewById(R.id.goodsCountID);

        storeCollectID = findViewById(R.id.storeCollectID);

        TextView storeSearchID = findViewById(R.id.storeSearchID);

        storePic = findViewById(R.id.storePic);

        storeInFoPic = findViewById(R.id.storeInFoPic);

        tvStoreCredit = findViewById(R.id.tvStoreCredit);

        favoritesAddID = findViewById(R.id.favoritesAddID);

        favoritesDeleteID = findViewById(R.id.favoritesDeleteID);

        sotreGoodsGridViewID = findViewById(R.id.sotreGoodsGridViewID);

        ImageView imageBack = findViewById(R.id.imageBack);

        store_id = getIntent().getStringExtra("store_id");

        goodsListViewAdapter = new StoreGoodsMyGridViewListAdapter(this);

        sotreGoodsGridViewID.setAdapter(goodsListViewAdapter);

        loadingData(store_id);//初始化加载店铺信息

        sotreGoodsGridViewID.setFocusable(false);

        imageBack.setOnClickListener(this);

        storeSearchID.setOnClickListener(this);

        favoritesAddID.setOnClickListener(this);

        favoritesDeleteID.setOnClickListener(this);
    }


    /**
     * 初始化加载店铺信息
     */
    public void loadingData(String store_id) {

        String url = Constants.URL_STORE_INFO + "&store_id=" + store_id + "&key=" + myApplication.getLoginKey();

        RemoteDataHandler.asyncDataStringGet(url, new Callback() {
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

                            store_name = storeInFo.getStore_name() == null ? "" : storeInFo.getStore_name();
                            tvStoreCredit.setText(storeInFo.getStore_credit_text());

                            ArrayList<GoodsList> goodsList = GoodsList.newInstanceList(rec_goods_list);
                            if (storeInFo != null && storeInFo.getMb_sliders() != null && storeInFo.getMb_sliders().size() > 0) {
                                initHeadImage(storeInFo.getMb_sliders());
                            }
                            storeNameID.setText(storeInFo.getStore_name());
                            goodsCountID.setText(storeInFo.getGoods_count());
                            storeCollectID.setText(storeInFo.getStore_collect());
                            imageLoader.displayImage(storeInFo.getStore_avatar(), storePic, options, animateFirstListener);
                            imageLoader.displayImage(storeInFo.getMb_title_img(), storeInFoPic, options, animateFirstListener);
                            goodsListViewAdapter.setGoodsList(goodsList);
                            goodsListViewAdapter.notifyDataSetChanged();
                            if (storeInFo.getIs_favorate().equals("true")) {
                                favoritesAddID.setVisibility(View.GONE);
                                favoritesDeleteID.setVisibility(View.VISIBLE);
                            } else {
                                favoritesAddID.setVisibility(View.VISIBLE);
                                favoritesDeleteID.setVisibility(View.GONE);
                            }

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//					SView.smoothScrollTo(0, 0);
                } else {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            Toast.makeText(StoreInFoActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 添加店铺收藏
     */
    public void AddFav() {
        String url = Constants.URL_STORE_ADD_FAVORITES;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("store_id", store_id);
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        Toast.makeText(StoreInFoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        loadingData(store_id);
                    }
                } else {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String error = obj2.getString("error");
                        if (error != null) {
                            Toast.makeText(StoreInFoActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 删除店铺收藏
     */
    public void DeleteFav() {
        String url = Constants.URL_STORE_DELETE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("store_id", store_id);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        Toast.makeText(StoreInFoActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                        loadingData(store_id);
                    }
                } else {
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String error = obj2.getString("error");
                        if (error != null) {
                            Toast.makeText(StoreInFoActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void initHeadImage(ArrayList<StoreIndex> list) {

        //清除已有视图防止重复
        viewflipper.removeAllViews();
        dian.removeAllViews();
        viewList.clear();

        for (int i = 0; i < list.size(); i++) {
            StoreIndex bean = list.get(i);
            ImageView imageView = new ImageView(StoreInFoActivity.this);
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setBackgroundResource(R.drawable.dic_av_item_pic_bg);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageLoader.displayImage(bean.getImgUrl(), imageView, options, animateFirstListener);
            viewflipper.addView(imageView);
            OnImageViewClick(imageView, bean.getType(), bean.getLink());

            ImageView dianimageView = new ImageView(StoreInFoActivity.this);
            LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 3, 1);
            dianimageView.setLayoutParams(params);
            imageView.setScaleType(ScaleType.FIT_XY);
            dianimageView.setBackgroundResource(R.drawable.dian_select);
            dian.addView(dianimageView);
            viewList.add(dianimageView);
        }

        mGestureDetector = new GestureDetector(this);
        viewflipper.setOnTouchListener(this);
        myScrollView.setGestureDetector(mGestureDetector);

        if (viewList.size() > 1) {
            dian_select(currentPage);
            mHandler.sendEmptyMessageDelayed(SHOW_NEXT, 3800);
        }
    }

    public void OnImageViewClick(View view, final String type, final String data) {
        view.setOnTouchListener(new OnTouchListener() {
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
//	     					Intent intent = new Intent(StoreInFoActivity.this,PayMentWebActivity.class);
//	     					intent.putExtra("data", data);
//	     					StoreInFoActivity.this.startActivity(intent);
                        } else if (type.equals("2")) {//2: 商品ID
                            Intent intent = new Intent(StoreInFoActivity.this, GoodsDetailsActivity.class);
                            intent.putExtra("goods_id", data);
                            StoreInFoActivity.this.startActivity(intent);
                        }

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
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.favoritesAddID:

                // 判断每次点击的事件间隔是否符合连击的有效范围
                // 不符合时，有可能是连击的开始，否则就仅仅是单击
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime <= interval) {
                    ++count;
                } else {
                    count = 1;
                }
                // 延迟，用于判断用户的点击操作是否结束
                delay(0);
                firstTime = secondTime;

                break;

            case R.id.favoritesDeleteID:

                // 判断每次点击的事件间隔是否符合连击的有效范围
                // 不符合时，有可能是连击的开始，否则就仅仅是单击
                long secondTime2 = System.currentTimeMillis();
                if (secondTime2 - firstTime <= interval) {
                    ++count;
                } else {
                    count = 1;
                }
                // 延迟，用于判断用户的点击操作是否结束
                delay(1);
                firstTime = secondTime2;

                break;
            case R.id.imageBack:

                finish();

                break;

            case R.id.storeSearchID:

                Intent intent = new Intent(StoreInFoActivity.this, StoreSearchActivity.class);
                intent.putExtra("store_id", store_id);
                intent.putExtra("store_name", store_name);
                startActivity(intent);


                break;

            default:
                break;
        }
    }

}
