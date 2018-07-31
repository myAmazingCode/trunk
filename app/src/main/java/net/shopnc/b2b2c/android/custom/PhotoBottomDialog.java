package net.shopnc.b2b2c.android.custom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.EvaluatePhotoAdapter;
import net.shopnc.b2b2c.android.bean.GoodsDetailForEvaluate;
import net.shopnc.b2b2c.android.common.FileUtils;
import net.shopnc.b2b2c.android.common.MyShopApplication;

import java.io.File;


/**
 * 公共组件--loading对话框
 * 
 * @author wj
 * 
 */
public class PhotoBottomDialog extends Dialog implements View.OnClickListener{


	public static final int SELELCT_FILE_TO_UPLOAD = 101;

	public static final int SELELCT_FILE_TO_UPLOAD_ITEM = 108;

	private Context context;
	public static String localTempImageFileName = "";

	private static final int FLAG_CHOOSE_PHONE = 6;

	public static GoodsDetailForEvaluate goodsDetailForEvaluate;

	private String id = "";

	private boolean isChild = false;

	private int goodsPositon;

	private String  imageFilePath = "";

	private EvaluatePhotoAdapter mPhotoAdapter;
	private MyShopApplication myApplication;
	
	public PhotoBottomDialog(Context context, String id) {
		super(context, R.style.CommonDialog);
		myApplication = (MyShopApplication) context.getApplicationContext();
		this.context = context;
		this.id = id;
	}

	public PhotoBottomDialog(Context context) {
		super(context, R.style.CommonDialog);
		myApplication = (MyShopApplication) context.getApplicationContext();
		this.context = context;
	}

	public PhotoBottomDialog(Context context, String id, EvaluatePhotoAdapter mPhotoAdapter) {
		super(context, R.style.CommonDialog);
		this.context = context;
		myApplication = (MyShopApplication) context.getApplicationContext();
		this.id = id;
		this.mPhotoAdapter = mPhotoAdapter;
	}


	public PhotoBottomDialog(Context context, OnCancelListener onCancelListener) {
		super(context, R.style.CommonDialog);
		myApplication = (MyShopApplication) context.getApplicationContext();
		this.context = context;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.dismiss();
	}

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment_photo_dialog);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.BOTTOM);
		getWindow().setWindowAnimations(R.style.AnimBottom);
		initDataLines();
	}

	public void initDataLines(){
		findViewById(R.id.btn_photo).setOnClickListener(this);
		findViewById(R.id.btn_photobyalbum).setOnClickListener(this);
		findViewById(R.id.btn_cancle).setOnClickListener(this);
	}
	
	public void doGoToPhone() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			try {
				localTempImageFileName = "";
				localTempImageFileName = System.currentTimeMillis() + ".JPG";
				File filePath = new File(FileUtils.FRIENDSPHOTO);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File imageFile = new File(filePath, localTempImageFileName);
				imageFilePath = filePath +"/"+ localTempImageFileName;
				myApplication.setImgPath(imageFilePath);
				Log.e("imageFilePath",imageFilePath);
				// localTempImgDir和localTempImageFileName是自己定义的名字
				Uri photoUri = Uri.fromFile(imageFile);
				//intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
				((Activity)context).startActivityForResult(intent, FLAG_CHOOSE_PHONE);
			} catch (ActivityNotFoundException e) {

			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_photo:
			dismiss();
			doGoToPhone();
			break;
		case R.id.btn_photobyalbum:
			dismiss();
			showFileChooser();

			break;
		case R.id.btn_cancle:
			dismiss();
			break;
		default:
			break;
		}
	}

	public EvaluatePhotoAdapter getmPhotoAdapter() {
		return mPhotoAdapter;
	}

	private void showFileChooser() {
		/*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image*//*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);

		try {
				((Activity)context).startActivityForResult(Intent.createChooser(intent, "请选择文件"), SELELCT_FILE_TO_UPLOAD_ITEM);


		} catch (ActivityNotFoundException ex) {
			Toast.makeText(context, "请安装文件管理器", Toast.LENGTH_SHORT).show();
		}*/
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");

		((Activity)context).startActivityForResult(intent, SELELCT_FILE_TO_UPLOAD_ITEM);
	}

}
