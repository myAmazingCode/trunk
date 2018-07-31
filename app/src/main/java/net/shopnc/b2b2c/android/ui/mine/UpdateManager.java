package net.shopnc.b2b2c.android.ui.mine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.custom.MyUpdateDialog;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;


@SuppressLint("HandlerLeak")
public class UpdateManager {

	private Context mContext;
	// 返回的安装包url
	private String apkUrl = new String();
	/* 下载包安装路径 */
	// private static final String savePath = "/sdcard/VeryNCShop/";
	private static final String savePath = Constants.CACHE_DIR;

	private static final String saveFileName = savePath + "/"
			+ "android_verync_shop.apk";

	private static final int DOWN_UPDATE = 1;

	private static final int DOWN_OVER = 2;

	private int progress;

	private Thread downLoadThread;

	private boolean interceptFlag = false;

	private MyUpdateDialog mpDialog;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mpDialog.shuzhi.setText("下载:" + (progress + 1) + "%");
				mpDialog.progress.setProgress(progress);
				break;
			case DOWN_OVER:
				Toast.makeText(mContext, "下载完成现在安装！！", Toast.LENGTH_SHORT).show();
				mpDialog.dismiss();
				installApk();
				break;
			default:
				break;
			}
		}
    };

	public UpdateManager(Context context, String url) {
		this.mContext = context;
		this.apkUrl = url;
	}

	// 外部接口让主Activity调用
	public void checkUpdateInfo() {
		mpDialog = new MyUpdateDialog(UpdateManager.this.mContext);
		// mpDialog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
		mpDialog.show();
		mpDialog.btu_on
				.setOnClickListener(new android.view.View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (Environment.MEDIA_MOUNTED.equals(Environment
								.getExternalStorageState())) {
							downLoadThread = new Thread(mdownApkRunnable);
							downLoadThread.start();
						} else {
							Toast.makeText(mContext, "亲，SD卡不在了，快去找找！！",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		mpDialog.btu_off
				.setOnClickListener(new android.view.View.OnClickListener() {
					@Override
					public void onClick(View v) {
						interceptFlag = true;
						mpDialog.dismiss();
					}
				});
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
	 * 安装apk
	 * 
	 * @param url
	 */
	private void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);

	}
}
