package net.shopnc.b2b2c.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.lib.video.videoimg.VideoFrameImageLoader;

import java.util.ArrayList;

import tcking.github.com.giraffeplayer.GiraffePlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;


/**
 * Created by dqw on 2015/8/6.
 */
public class AudioSwitchPagerAdapter extends PagerAdapter {

    private ArrayList<String> imageList;
    private ImageView[] mImageViews;

    public static final String TAG = "AudioSwitchPagerAdapter";
    int currentState = 0;

    private View view;
    private SurfaceView mSurfaceView = null;
    private boolean isAudios;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    private Context mContext;
    String mPath;
    public static GiraffePlayer player = null;
    Activity mActivity;
    /*
     * isAudio 传递是否有视频文件
     */
    public AudioSwitchPagerAdapter(Context context, ArrayList<String> imageList, boolean isAudio) {
        this.mContext = context;
        this.imageList = imageList;
        isAudios = isAudio;
        this.mActivity = (Activity)context;
        //将图片装载到数组中
        mImageViews = new ImageView[imageList.size()];
        for (int i = 0; i < mImageViews.length; i++) {

            if(i == 0) {
                if(isAudio){
                    mPath = imageList.get(i);

                    view = getMediaPlayerView(mPath);
                    continue;
                }
            }
            ImageView imageView = new ImageView(context);
//            imageLoader.displayImage(imageList.get(i), imageView, options, animateFirstListener);
            LoadImage.loadImg(context,imageView,imageList.get(i));
            mImageViews[i] = imageView;
        }
    }


    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        if(position != 0) {
            ((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);
        } else {
            if(isAudios) {
                ((ViewPager) container).removeView(view);
            }else {
                ((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);
            }
        }

    }

    @Override
    public Object instantiateItem(View container, int position) {

        if(position != 0) {
            ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
            return mImageViews[position % mImageViews.length];
        }else {
            if(isAudios){
                ((ViewPager) container).addView(view, 0);
                return view;
            }else{
                ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
                return mImageViews[position % mImageViews.length];
            }

        }
    }

    public View getMediaPlayerView(final String path){
        View view = LayoutInflater.from(mContext).inflate(R.layout.vpage_mediaplayer, null);


        final RelativeLayout img1_text = view.findViewById(R.id.img1_text1);
        final RelativeLayout img1_avdeo = view.findViewById(R.id.img1_avdeo);
        ImageView iv_image_bg = view.findViewById(R.id.iv_image_bg1);
        ImageView iv_image_play = view.findViewById(R.id.iv_image_play1);

        VideoFrameImageLoader mVideoFrameImageLoader = new VideoFrameImageLoader(mContext, null, null);
        mVideoFrameImageLoader.initview(path,iv_image_bg);
        Bitmap bitmap = mVideoFrameImageLoader.showCacheBitmap(VideoFrameImageLoader.formatVideoUrl(path));
        if (bitmap != null) {
            iv_image_bg.setImageBitmap(bitmap);
        }else {

        }
        img1_text.setVisibility(View.VISIBLE);
        img1_avdeo.setVisibility(View.GONE);
        player = new GiraffePlayer(mActivity,view);

        player.onComplete(new Runnable() {
            @Override
            public void run() {
                img1_text.setVisibility(View.VISIBLE);
                img1_avdeo.setVisibility(View.GONE);
                //callback when video is finish
//                Toast.makeText(mContext, "播放完成",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(mContext, "视频播放失败",Toast.LENGTH_SHORT).show();
            }
        });

        iv_image_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1_text.setVisibility(View.GONE);
                img1_avdeo.setVisibility(View.VISIBLE);
                player.play(path);
                player.setTitle(path);
//                player.start();
            }
        });

//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        player.pause();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//                return true;
//            }
//        });

        return view;
    }



}
