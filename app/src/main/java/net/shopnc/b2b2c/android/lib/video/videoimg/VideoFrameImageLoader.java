package net.shopnc.b2b2c.android.lib.video.videoimg;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.util.LruCache;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * Created by jiana on 16-4-3.
 */
public class VideoFrameImageLoader {
    private static final String TAG = "VideoFrameImageLoader";
    /**
     * 缓存image的类，当存储image的大小大于LruCache设定的值，系统自动释放内存
     */
    private LruCache<String, Bitmap> mMemoryCache = null;
    private FileManager fileManager;
    /**
     * 获取视频流一帧图片的线程池
     */
    private ExecutorService mImageThreadPool = null;

    /**
     * 记录是否刚打开程序，用于解决进入程序不滚动不会获取图片的问题
     */
    private boolean isFirstEnter = true;

    /**
     * 一屏中第一个item的位置
     */
    private int mFirstVisibleItem;

    /**
     * 一屏中所有的item的个数
     */
    private int mVisibleItemCount;

    /**
     * 展示列表
     */
    private View listView;

    private String[] videoUrls;

    public VideoFrameImageLoader(Context context, View listView, String[] urls) {
        //获取系统分配给每个应用的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        Log.e(TAG, "maxMemory = " + maxMemory);
        //给lruCache分配1/8 4M
        int mCacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(mCacheSize) {
            //必须重写此方法，来测量Bitmap的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
        fileManager = new FileManager(context);
        this.videoUrls = urls;
        this.listView = listView;
    }
    public void initview(String videoUrl, ImageView mImageView) {
        showUrl2Image( videoUrl,  mImageView) ;
    }
    /**
     * 初始化Adapter后初始化数据
     * 这个需要在adapter中调用，做初始化
     * 监听是否为滚动状态，判断截取显示的子布局对应的哪几个视频流
     */
    public void initList() {
        //判断为ListView或GridView时
        if (listView instanceof AbsListView) {
            AbsListView absListView = (AbsListView) listView;
            absListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    //仅当ListView禁止时才去下载，ListView滑动时取消所有正在下载的任务
                    if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                        showImage(mFirstVisibleItem, mVisibleItemCount);
                    } else {
                        cancelTask();
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    mFirstVisibleItem = firstVisibleItem;
                    mVisibleItemCount = visibleItemCount;
                    if (isFirstEnter && mVisibleItemCount > 0) {
                        showImage(mFirstVisibleItem, mVisibleItemCount);
                        isFirstEnter = false;
                    }
                }
            });
            return;
        }

        //判断为RecyclerView时
        if (listView instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) listView;
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    //仅当ListView禁止时才去下载，ListView滑动时取消所有正在下载的任务
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        showImage(mFirstVisibleItem, mVisibleItemCount);
                    } else {
                        cancelTask();
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //网格布局列表或线性列表
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    mVisibleItemCount = layoutManager.getChildCount();
                    mFirstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                    if (isFirstEnter && mVisibleItemCount > 0) {
                        showImage(mFirstVisibleItem, mVisibleItemCount);
                        isFirstEnter = false;
                    }
                }
            });
        }
    }

    /**
     * 显示当前屏幕的图片，先会去查找LruCache，LruCache没有就去手机目录查找，在没有就开启线程去下载截取帧图片
     *
     * @param videoUrl
     * @param mImageView
     */
    private void showUrl2Image(String videoUrl,final ImageView mImageView) {
        Bitmap bitmap = null;
        bitmap = cutVideoFrameImage(videoUrl, new VideoFrameImageLoader.OnFrameImageLoaderListener() {
            @Override
            public void onImageLoader(Bitmap bitmap, String url) {
                if (mImageView != null && bitmap != null) {
                    mImageView.setImageBitmap(bitmap);
                }
            }
        });
//
    }


    /**
     * 显示当前屏幕的图片，先会去查找LruCache，LruCache没有就去手机目录查找，在没有就开启线程去下载截取帧图片
     *
     * @param firstVisibleItem
     * @param visibleItemCount
     */
    private void showImage(int firstVisibleItem, int visibleItemCount) {
        Bitmap bitmap = null;
        for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
            String videoUrl = videoUrls[i];
            final ImageView mImageView = listView.findViewWithTag(videoUrl);
            bitmap = cutVideoFrameImage(videoUrl, new VideoFrameImageLoader.OnFrameImageLoaderListener() {
                @Override
                public void onImageLoader(Bitmap bitmap, String url) {
                    if (mImageView != null && bitmap != null) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }
            });
        }
    }

    /**
     * 获取线程池的方法，因为涉及到并发的问题，我们加上同步锁
     *
     * @return
     */
    public ExecutorService getThreadPool() {
        if (mImageThreadPool == null) {
            synchronized (ExecutorService.class) {
                if (mImageThreadPool == null) {
                    mImageThreadPool = Executors.newFixedThreadPool(5);
                }
            }
        }
        return mImageThreadPool;
    }

    /**
     * 添加Bitmap到内存缓存
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        Log.e(TAG, "key = " + key);
        if (getBitmapFormMemCache(key) == null && bitmap != null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 从内存缓存中获取一个bitmap
     *
     * @param key
     * @return
     */
    public Bitmap getBitmapFormMemCache(String key) {

        return mMemoryCache.get(key);
    }

    /**
     * 先从内存中获取Bitmap，如果没有就从本地获取
     *
     * @param url
     * @param listener
     * @return
     */
    public Bitmap cutVideoFrameImage(final String url, final OnFrameImageLoaderListener listener) {

        final String subUrl = VideoFrameImageLoader.formatVideoUrl(url);
        Bitmap bitmap = showCacheBitmap(subUrl);
        if (bitmap != null) {
            return bitmap;
        } else {
            final Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    listener.onImageLoader((Bitmap) msg.obj, url);
                }
            };
            getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = getPathImage(url);
                    if (bitmap == null) {
//                        bitmap = getPathImage(url);
//                        if (bitmap == null)
                            return;
                    }
                    Message msg = handler.obtainMessage();
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                    try {
                        //保存在手机本地存储
                        fileManager.saveBitmap(subUrl, bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //将bitmap加如内存缓存
                    addBitmapToMemoryCache(subUrl, bitmap);
                }
            });
        }
        return null;
    }

    /**
     * 获取Bitmap内存中没有就去本地存储获取
     *
     * @param url
     * @return
     */
    public Bitmap showCacheBitmap(String url) {
        if (getBitmapFormMemCache(url) != null) {
            return getBitmapFormMemCache(url);
        } else if (fileManager.isFileExists(url) && fileManager.getFileSize(url) != 0) {
            //从本地获取bitmap
            Bitmap bitmap = fileManager.getBitmap(url);
            //将bitmap加入内存缓存
            addBitmapToMemoryCache(url, bitmap);
            return bitmap;
        }
        return null;
    }

    /**
     * 替换Url中非字母和非数字的字符，这里比较重要，因为我们用Url作为文件名，比如我们的Url
     * 是Http://xiaanming/abc.jpg;用这个作为图片名称，系统会认为xiaanming为一个目录，
     * 我们没有创建此目录保存文件就会报错
     *
     * @param videoUrl
     * @return
     */
    public static String formatVideoUrl(String videoUrl) {
        return videoUrl.replaceAll("[\\^\\W]", "") + ".jpeg";
    }

    /**
     * 从.mp4的url视频中获取第一帧
     *
     * @param url
     * @return
     */
    private Bitmap getBitmapFormUrl(String url) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime(1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                // Ignore failures while cleaning up.
            }
        }
        return bitmap;
    }


    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }
             /*获取视频第一帧*/
    public Bitmap getPathImage(String url){
        Bitmap bitmap = null;
        FFmpegMediaMetadataRetriever fmmr = new FFmpegMediaMetadataRetriever();
        try {
            fmmr.setDataSource(url);
            bitmap = fmmr.getFrameAtTime();

            if (bitmap != null) {
                Bitmap b2 = fmmr
                        .getFrameAtTime(
                                4000000,
                                FFmpegMediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                if (b2 != null) {
                    bitmap = b2;
                }
                if (bitmap.getWidth() > 640) {// 如果图片宽度规格超过640px,则进行压缩
                    bitmap = ThumbnailUtils.extractThumbnail(bitmap,
                            640, 480,
                            ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                }
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } finally {
            fmmr.release();
        }
        return bitmap;
    }


    /**
     * 取消正在下载的任务
     */
    public synchronized void cancelTask() {
        if (mImageThreadPool != null) {
            mImageThreadPool.shutdownNow();
            mImageThreadPool = null;
        }
    }

    public String[] getVideoUrls() {
        return videoUrls;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public interface OnFrameImageLoaderListener {
        void onImageLoader(Bitmap bitmap, String url);
    }
}