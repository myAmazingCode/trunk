package net.shopnc.b2b2c.android.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.View;

import net.shopnc.b2b2c.android.custom.dialog.MyUpdateDialog;


@SuppressLint("HandlerLeak")
public class UpdateManager {

	private Context mContext;
	// 返回的安装包url
	private String apkUrl = new String();

	public static MyUpdateDialog mpDialog;


	public static void miss() {
		mpDialog.dismiss();
	}
	public static void show() {
		if(!mpDialog.isShowing()) {
			mpDialog.show();
		}
	}

	public static void setProgress(long progress) {
		mpDialog.shuzhi.setText("下载:" + (progress + 1) + "%");
		mpDialog.progress.setProgress((int) progress);
	}

	public UpdateManager(Context context, String url) {
		this.mContext = context;
		this.apkUrl = url;
	}

	// 外部接口让主Activity调用
	public void checkUpdateInfo(final String text) {
		mpDialog = new MyUpdateDialog(UpdateManager.this.mContext);
		mpDialog.setTv2(text);
		mpDialog.btu_on
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (Environment.MEDIA_MOUNTED.equals(Environment
								.getExternalStorageState())) {
							Intent intent = new Intent(mContext, DownLoadService.class);
							intent.putExtra("url",apkUrl);
							mContext.startService(intent);
						} else {
							TToast.showShort( mContext,"亲，SD卡不在了，快去找找！！")  ;
						}
					}
				});
		mpDialog.btu_off
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(mContext, DownLoadService.class);
						mContext.stopService(intent);
						mpDialog.dismiss();
					}
				});
	}


}
