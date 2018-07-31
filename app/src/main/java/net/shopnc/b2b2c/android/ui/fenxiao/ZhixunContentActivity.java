package net.shopnc.b2b2c.android.ui.fenxiao;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orhanobut.logger.Logger;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.ZhixunContentAdapter;
import net.shopnc.b2b2c.android.bean.MobileBody;
import net.shopnc.b2b2c.android.bean.goodsCommonList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.custom.HtmlTextView;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.MyProgressDialog;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.lib.video.videoimg.VideoFrameImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;

import tcking.github.com.giraffeplayer.GiraffePlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by snm on 2016/8/31.
 */
public class ZhixunContentActivity extends BaseActivity {

    private String news_id;
    private MyGridView mygridview;
    private ZhixunContentAdapter adapter;
    private LinearLayout goods_info;
    private LinearLayout ll_content_txt;

    private ScrollView scrollview;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    MyProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhixuncontent);
        setCommonHeader("详情");

        mygridview = (MyGridView) findViewById(R.id.mygridview);
        scrollview = (ScrollView) findViewById(R.id.scrollview);
        goods_info = (LinearLayout) findViewById(R.id.goods_info);
        ll_content_txt = (LinearLayout) findViewById(R.id.ll_content_txt);
        news_id = getIntent().getStringExtra("news_id");
        String url = Constants.URL_ZHIXUN_CONTENT + "&video_id=" + news_id + "&identity=news";
        Logger.d(url);
        showDialog(context, "正在加载中...");
//        progressDialog = new MyProgressDialog(ZhixunContentActivity.this, "正在加载中...", R.drawable.progress_round);
//        progressDialog.show();
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
//                Logger.d(data.getJson());
                dissMissDialog();
                if (data.getCode() == 200) {
                    try {
                        String goods_common_list = JsonUtil.getString(data.getJson(), "goods_common_list");
                        ArrayList<goodsCommonList> commonList = JsonUtil.getBean(goods_common_list, new TypeToken<ArrayList<goodsCommonList>>() {
                        }.getType());
                        String news_info = JsonUtil.getString(data.getJson(), "news_info");
                        JSONObject JsonObj = new JSONObject(news_info);
                        String news_name = JsonObj.optString("news_name");
                        String mobile_body = JsonObj.optString("mobile_body");
                        String add_time = JsonObj.optString("add_time");
//                        String news_pic   = JsonObj.optString("news_pic");
//                        String page_view   = JsonObj.optString("page_view");

                        setTextByid(R.id.new_title, news_name);
                        setTextByid(R.id.new_time, add_time);
                        if (!commonList.isEmpty()) {
                            goods_info.setVisibility(View.VISIBLE);
                            mygridview.setVisibility(View.VISIBLE);
                            setTextByid(R.id.nes_goodsnum, "共" + commonList.size() + "个商品");
                            adapter = new ZhixunContentAdapter(getApplicationContext(), commonList);
                            mygridview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else {
                            goods_info.setVisibility(View.GONE);
                            mygridview.setVisibility(View.GONE);
                        }

                        ArrayList<MobileBody> mobileBodies = JsonUtil.getBean(mobile_body, new TypeToken<ArrayList<MobileBody>>() {
                        }.getType());

                        if (!mobileBodies.isEmpty()) {
                            for (int i = 0; i < mobileBodies.size(); i++) {
                                MobileBody body = mobileBodies.get(i);
                                if (("text").equals(body.getType())) {
                                    View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_zhixuncontent_text, null);
                                    HtmlTextView htmlTextView = view.findViewById(R.id.new_info);
                                    htmlTextView.setText(body.getValue());
                                    ll_content_txt.addView(view);
                                }
                                if (("image").equals(body.getType())) {
                                    ImageView imageView = new ImageView(getApplicationContext());
                                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                    lp.setMargins(10, 10, 10, 10);
                                    imageView.setLayoutParams(lp);
//                                    imageView.setLayoutParams(new LinearLayout.LayoutParams(150, 200));
//                                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    imageLoader.displayImage(body.getValue(), imageView, options, animateFirstListener);
                                    ll_content_txt.addView(imageView);

                                }
                                if (("video").equals(body.getType())) {
                                    final String avdeo = body.getValue();
                                    View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.auto_mediaplayer, null);

                                    final RelativeLayout img1_text = view.findViewById(R.id.img1_text1);
                                    final RelativeLayout img1_avdeo = view.findViewById(R.id.img1_avdeo);
                                    ImageView iv_image_bg = view.findViewById(R.id.iv_image_bg1);
                                    ImageView iv_image_play = view.findViewById(R.id.iv_image_play1);

                                    VideoFrameImageLoader mVideoFrameImageLoader = new VideoFrameImageLoader(getApplicationContext(), null, null);
                                    mVideoFrameImageLoader.initview(avdeo, iv_image_bg);
                                    Bitmap bitmap = mVideoFrameImageLoader.showCacheBitmap(VideoFrameImageLoader.formatVideoUrl(avdeo));
                                    if (bitmap != null) {
                                        iv_image_bg.setImageBitmap(bitmap);
                                    } else {

                                    }
                                    img1_text.setVisibility(View.VISIBLE);
                                    img1_avdeo.setVisibility(View.GONE);


                                    final GiraffePlayer player = new GiraffePlayer(ZhixunContentActivity.this, view);

                                    player.onComplete(new Runnable() {
                                        @Override
                                        public void run() {
                                            img1_text.setVisibility(View.VISIBLE);
                                            img1_avdeo.setVisibility(View.GONE);
                                            //callback when video is finish
//                                        Toast.makeText(getApplicationContext(), "播放完成",Toast.LENGTH_SHORT).show();
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

                                    iv_image_play.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            img1_text.setVisibility(View.GONE);
                                            img1_avdeo.setVisibility(View.VISIBLE);
                                            player.play(avdeo);
                                            player.setTitle(avdeo);
                                        }
                                    });
                                    ll_content_txt.addView(view);
                                }
                            }
                        }
                        if (scrollview != null) {
                            scrollview.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollview.fullScroll(ScrollView.FOCUS_UP);
                                }
                            });
                        }
//                        progressDialog.cancel();
                    } catch (Exception e) {
//                        progressDialog.cancel();
                        e.printStackTrace();
                    }

                } else {
//                    progressDialog.cancel();
                    ShopHelper.showApiError(getApplicationContext(), data.getJson());
                }
            }
        });

    }

    //  找到id 然后进行赋值
    public void setTextByid(int ids, String text) {
        TextView textView = (TextView) this.findViewById(ids);
        textView.setText(text);
    }

}
