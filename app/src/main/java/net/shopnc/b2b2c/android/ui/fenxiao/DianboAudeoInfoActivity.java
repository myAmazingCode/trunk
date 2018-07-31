package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.DianboAudeoInfoAdapter;
import net.shopnc.b2b2c.android.bean.DemandInfo;
import net.shopnc.b2b2c.android.bean.GoodsCommonLists;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.bean.TuijianGoods;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.FenxiaoGoodsSpecPopupWindow;
import net.shopnc.b2b2c.android.custom.GlideCircleTransform;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.custom.MyProgressDialog;
import net.shopnc.b2b2c.android.custom.MyScrollView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ncinterface.INCOnNumModify;
import net.shopnc.b2b2c.android.ncinterface.INCOnStringModify;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import tcking.github.com.giraffeplayer.GiraffePlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by snm on 2016/8/30.
 */
public class DianboAudeoInfoActivity extends BaseActivity {

    private String demand_id;
    private MyListView myListView;
    private LinearLayout video_text;
    private MyScrollView vocabulary_nested_scroll;
    private ArrayList<GoodsCommonLists> mList = new ArrayList<GoodsCommonLists>();
    private DianboAudeoInfoAdapter audeoInfoAdapter;

    private DemandInfo demandInfo;
    private GiraffePlayer player;
    private ImageView dian_logo, dian_shoucang, iv_image_bg;
    private TextView dian_title;
    private LinearLayout img1_text;

    //商品数量修改回调
    private INCOnNumModify incOnNumModify;
    private int goodsNum = 1; //商品数量
    private int goodsLimit = 0;
    private String is_fcode;//是否为F码商品 1是 0否
    private String is_virtual;//是否为虚拟商品 1-是 0-否
    private String ifcart = "0";//购物车购买标志 1购物车 0不是
    private String t_id, t_name; //记录商家ID 名称
    //商品规格修改回调
    private INCOnStringModify incOnStringModify;
    private FenxiaoGoodsSpecPopupWindow pwSpec;
    private String goods_id;

    private AudioManager mAudioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audeo_dianbo_content);
        setCommonHeader("点播详情");
        demand_id = getIntent().getStringExtra("demand_id");
        initView();
        initDate();
        //将系统音量设置为音乐音量，避免每次进入都是默认0音量
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM), 0);
    }

    private LinearLayout.LayoutParams params;

    private int mDefaultHeadHeight, displayWidth;

    public void initView() {
        myListView = (MyListView) findViewById(R.id.list);
        video_text = (LinearLayout) findViewById(R.id.video_text);
        img1_text = (LinearLayout) findViewById(R.id.img1_text);
        iv_image_bg = (ImageView) findViewById(R.id.iv_image_bg);


        vocabulary_nested_scroll = (MyScrollView) findViewById(R.id.vocabulary_nested_scroll);

        player = new GiraffePlayer(DianboAudeoInfoActivity.this, null);

        player.onComplete(new Runnable() {
            @Override
            public void run() {
                //callback when video is finish
//                Toast.makeText(getApplicationContext(), "播放完成",Toast.LENGTH_SHORT).show();
            }
        }).onInfo(new GiraffePlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                switch (what) {
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        //do something when buffering start
                        break;
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        //do something when buffering end
                        break;
                    case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                        //download speed
//                                    ((TextView) findViewById(R.id.tv_speed)).setText(Formatter.formatFileSize(getApplicationContext(),extra)+"/s");
                        break;
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        //do something when video rendering
//                                    findViewById(R.id.tv_speed).setVisibility(View.GONE);
                        break;
                }
            }
        }).onError(new GiraffePlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                Toast.makeText(getApplicationContext(), "视频播放失败", Toast.LENGTH_SHORT).show();
            }
        });
        audeoInfoAdapter = new DianboAudeoInfoAdapter(getApplicationContext());
        myListView.setAdapter(audeoInfoAdapter);

        dian_logo = (ImageView) findViewById(R.id.dian_logo);
        dian_shoucang = (ImageView) findViewById(R.id.dian_shoucang);
        dian_title = (TextView) findViewById(R.id.dian_title);
        dian_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!demandInfo.isIs_favorate()) {
                    ShouCang(demandInfo.getStore_id());
                } else {
                    UnShouCang(demandInfo.getStore_id());
                }
            }
        });
        mDefaultHeadHeight = video_text.getResources().getDisplayMetrics().widthPixels;
        displayWidth = video_text.getWidth();

        final int navHeight = 60;
        final int navWidth = displayWidth;
        vocabulary_nested_scroll.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(final int scrollY) {
//                Logger.d(scrollY+"");
                Logger.d(mDefaultHeadHeight + "");
                final int hi = 450 - (navHeight + navHeight * scrollY / (navWidth - navHeight - navHeight) - navHeight) - scrollY;
//                Logger.d(hi + "");
                video_text.setMinimumHeight(200);
                video_text.post(new Runnable() {
                    @Override
                    public void run() {

                        if (scrollY >= 0 && scrollY <= 250) {
                            params = (LinearLayout.LayoutParams) video_text.getLayoutParams();
                            params.height = hi;
                            video_text.setLayoutParams(params);
                            video_text.invalidate(); // 刷新界面
                        }
                    }
                });
            }
        });

        audeoInfoAdapter.setAddGouWuListener(new DianboAudeoInfoAdapter.addGouWuListener() {
            @Override
            public void ShowaddgouwuPop(GoodsCommonLists goods) {
                loadingGoodsDetailsData1(goods.getGoods_id());
            }
        });
    }


    public void initDate() {
        String url = Constants.URL_DIANBO_CONTENT + "&video_id=" + demand_id + "&key=" + MyShopApplication.getInstance().getLoginKey();
        Logger.d(url);
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                Logger.d(data.getJson());
                if (data.getCode() == 200) {
                    String demand_info = JsonUtil.getString(data.getJson(), "demand_info");
                    demandInfo = JsonUtil.getBean(demand_info, DemandInfo.class);

                    String recommend_goods_list = JsonUtil.getString(data.getJson(), "recommend_goods_common_list");
//                    String goods_detail = JsonUtil.getString(recommend_goods_list,"goods_detail");
//                    ArrayList<TuijianGoods> list = TuijianGoods.getInstanceList(goods_detail);
                    ArrayList<GoodsCommonLists> list = JsonUtil.getBean(recommend_goods_list, new TypeToken<ArrayList<GoodsCommonLists>>() {
                    }.getType());

                    mList.addAll(list);
                    audeoInfoAdapter.setmList(mList);
                    dian_title.setText(demandInfo.getStore_info().getStore_name());

                    Glide.with(getApplicationContext()).load(demandInfo.getStore_info().getStore_avatar()).transform(new GlideCircleTransform(getApplicationContext())).into(dian_logo);
                    if (!demandInfo.isIs_favorate()) {
                        dian_shoucang.setImageResource(R.drawable.shouocang);
                    } else {
                        dian_shoucang.setImageResource(R.drawable.yishoucang);
                    }
                    String palyerurl = demandInfo.getDemand_video_url();
//                    String palyerurl = "http://live.shopnctest.com/shopnc/test1.m3u8";

                    if (!StringUtils.isEmpty(palyerurl)) {

                        video_text.setVisibility(View.VISIBLE);
                        img1_text.setVisibility(View.GONE);
                        player.play(palyerurl);
                        player.start();
                    } else {
                        video_text.setVisibility(View.GONE);
                        img1_text.setVisibility(View.VISIBLE);
                        LoadImage.loadImg(getApplicationContext(), iv_image_bg, demandInfo.getPromote_image_url());
                    }
                } else {
                    ShopHelper.showApiError(getApplicationContext(), data.getJson());
                }
            }
        });

    }


    /*收藏*/
    public void ShouCang(String store_id) {
        String url = Constants.URL_STORE_ADD_FAVORITES;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("store_id", store_id);
        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                        mList.clear();
                        initDate();
                    }
                } else {
                    ShopHelper.showApiError(getApplicationContext(), json);
                }
            }
        });
    }

    /*取消收藏*/
    public void UnShouCang(String store_id) {
        String url = Constants.URL_STORE_DELETE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("store_id", store_id);

        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if ("1".equals(json)) {
                        Toast.makeText(getApplicationContext(), "取消成功", Toast.LENGTH_SHORT).show();
                        mList.clear();
                        initDate();
                    }
                } else {
                    ShopHelper.showApiError(getApplicationContext(), json);
                }
            }
        });
    }


    /**
     * 初始化规格
     */
    private void initSpec(TuijianGoods goodsBean) {
        incOnNumModify = new INCOnNumModify() {
            @Override
            public void onModify(int num) {
//                goodsNum = num;
            }
        };

        incOnStringModify = new INCOnStringModify() {
            @Override
            public void onModify(String str) {
                goods_id = str;
                loadingGoodsDetailsData1(goods_id);
            }


        };

        //限购数量
//        if (goodsBean.getUpper_limit() == null || goodsBean.getUpper_limit().equals("") || goodsBean.getUpper_limit().equals("0")) {
//            goodsLimit = Integer.parseInt((goodsBean.getGoods_storage() == null ? "0" : goodsBean.getGoods_storage()) == "" ? "0" : goodsBean.getGoods_storage());
//        }
        if (goodsBean.getGoods_info().getUpper_limit() == null || goodsBean.getGoods_info().getUpper_limit().equals("") || goodsBean.getGoods_info().getUpper_limit().equals("0")) {
            goodsLimit = Integer.parseInt((goodsBean.getGoods_info().getGoods_storage() == null ? "0" : goodsBean.getGoods_info().getGoods_storage()) == "" ? "0" : goodsBean.getGoods_info().getGoods_storage());
        }

        if (pwSpec == null) {
            pwSpec = new FenxiaoGoodsSpecPopupWindow(this, incOnNumModify, incOnStringModify);
        }
        ifcart = goodsBean.getGoods_info().getCart() + "";
        String goods_image = Arrays.asList(goodsBean.getGoods_image().split(",")).get(0);
//        Logger.d(goods_image);
        String store_info = goodsBean.getStore_info();
        net.shopnc.b2b2c.android.bean.StoreInfo storeInfo = StoreInfo.newInstanceList(store_info);
        t_id = storeInfo.getStoreId();
        t_name = storeInfo.getMemberName();
        pwSpec.setGoodsInfo(goodsBean.getGoods_info().getGoods_name(), goods_image, goodsBean.getGoods_info().getGoods_price(),
                goodsBean.getGoods_info().getGoods_storage(), goodsBean.getGoods_info().getGoods_id(), ifcart, goodsNum, goodsLimit,
                goodsBean.getGoods_info().getIs_fcode(), goodsBean.getGoods_info().getIs_virtual(), t_id, t_name);

        String spec_name = goodsBean.getGoods_info().getSpec_name();
        String spec_value = goodsBean.getGoods_info().getSpec_value();
        String goods_spec = goodsBean.getGoods_info().getGoods_spec();


        pwSpec.setSpecInfo(goodsBean.getSpec_list(), spec_name, spec_value, goods_spec);


    }

    MyProgressDialog progressDialog;

    //    private void loadingGoodsDetailsData(String goods_id) {
//
//        String url = Constants.URL_GOODSDETAILS + "&goods_id=" + goods_id + "&key=" + MyShopApplication.getInstance().getLoginKey();
//
//        if (!StringUtils.isEmpty(MyShopApplication.getInstance().getAreaId())) {
//            url += "area_id=" + MyShopApplication.getInstance().getAreaId();
//        }
//        Logger.d(url);
//        progressDialog = new MyProgressDialog(DianboAudeoInfoActivity.this,"正在加载中...",R.anim.progress_round);
//        progressDialog.show();
//        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
//            @Override
//            public void dataLoaded(ResponseData data) {
//                String json = data.getJson();
//
//                if (data.getCode() == HttpStatus.SC_OK) {
//                    try {
//                        JSONObject obj = new JSONObject(json);
//                        TuijianGoods goods = TuijianGoods.getInstance(obj);
//                        initSpec(goods);
//                        if(!pwSpec.isShowing()) {
//                            pwSpec.showPopupWindow();
//                        }
//                    }catch (Exception e){
//                        ShopHelper.showApiError(getApplicationContext(),"加载失败");
//                        e.printStackTrace();
//                    }
//                }else {
//                    ShopHelper.showApiError(getApplicationContext(),json);
//                }
//                progressDialog.cancel();
//            }
//        });
//    }
    private void loadingGoodsDetailsData1(String goods_commonid) {
        showDialog(context, "正在加载中");
//        progressDialog = new MyProgressDialog(DianboAudeoInfoActivity.this, "正在加载中...", R.drawable.progress_round);
//        progressDialog.show();
        String url = Constants.URL_RECOMMEND_GOODS_DETAIL + "&goods_id=" + goods_commonid + "&key=" + MyShopApplication.getInstance().getLoginKey();

        Logger.d(url);

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                dissMissDialog();
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        String goods_detail = JsonUtil.getString(json, "goods_detail");
                        JSONObject obj = new JSONObject(goods_detail);

                        TuijianGoods goods = TuijianGoods.getInstance(obj);
                        initSpec(goods);
                        if (!pwSpec.isShowing()) {
                            pwSpec.showPopupWindow();
                        }
                    } catch (Exception e) {
                        ShopHelper.showApiError(getApplicationContext(), "加载失败");
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(getApplicationContext(), json);
                }
//                progressDialog.cancel();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                mAudioManager.adjustStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE,
                        AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
                mAudioManager.adjustStreamVolume(  //仍旧设置系统音量是为了防止进入另一详情页仍旧是旧的系统音量
                        AudioManager.STREAM_SYSTEM,
                        AudioManager.ADJUST_RAISE,
                        AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                mAudioManager.adjustStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER,
                        AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
                mAudioManager.adjustStreamVolume(
                        AudioManager.STREAM_SYSTEM,
                        AudioManager.ADJUST_LOWER,
                        AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
                return true;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

}