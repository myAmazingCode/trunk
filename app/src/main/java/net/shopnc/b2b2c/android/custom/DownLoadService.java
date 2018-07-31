package net.shopnc.b2b2c.android.custom;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import net.shopnc.b2b2c.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by zs on 2016/7/8.
 */
public class DownLoadService extends Service {

    /**
     * 目标文件存储的文件夹路径
     */

    /**
     * 目标文件存储的文件名
     */
    private static final String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ShopNC/";
    private static final String saveFileName = savePath + "/"
            + "android_java_shop.apk";
    private static final int DOWN_UPDATE = 1;

    private static final int DOWN_OVER = 2;
    private Context mContext;
    private int preProgress = 0;
    private int NOTIFY_ID = 1;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private long progress;

    private Thread downLoadThread;
    private String apkUrl = new String();
    private boolean interceptFlag = false;

    //    private MyUpdateDialog mpDialog;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    UpdateManager.setProgress(preProgress);
                    updateNotification(progress);
                    break;
                case DOWN_OVER:
                    TToast.showShort(mContext, "下载完成现在安装");
                    UpdateManager.miss();
                    cancelNotification();
                    installApk();
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext = this;
        apkUrl = intent.getStringExtra("url");
        loadFile();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mHandler.removeCallbacks(mdownApkRunnable);
        mHandler.removeMessages(DOWN_UPDATE);
        downLoadThread.stop();

        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 下载文件
     */
    private void loadFile() {
        initNotification();
        statrDownLoad();
    }

    private void statrDownLoad() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(apkUrl);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];
                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((float) count / length) * 100);
                    // 更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        // 下载完成通知安装
                        mHandler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);// 点击取消就停止下载.

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    /**
     * 初始化Notification通知
     */
    public void initNotification() {
        builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.logo)
                .setContentText("0%")
                .setContentTitle("下载中")
                .setProgress(100, 0, false);
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    /**
     * 更新通知
     */
    public void updateNotification(long progress) {
        int currProgress = (int) progress;
        if (preProgress < currProgress) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, (int) progress, false);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }
        preProgress = (int) progress;
    }

    /**
     * 取消通知
     */
    public void cancelNotification() {
        notificationManager.cancel(NOTIFY_ID);
    }

    /**
     * 安装apk
     *
     * @param
     */
    private void installApk() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        installApk(apkfile);
    }

    /**
     * 安装软件
     *
     * @param file
     */
    private void installApk(File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        // 执行意图进行安装
        mContext.startActivity(install);
    }
}
