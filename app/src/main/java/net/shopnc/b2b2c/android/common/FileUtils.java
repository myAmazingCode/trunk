package net.shopnc.b2b2c.android.common;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
	private static final String TAG = FileUtils.class.getName();
	
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/formats/";
	
	public static String FRIENDSPHOTO = Environment.getExternalStorageDirectory()
			+ "/images/shopnc/";


	public static void saveBitmap(Bitmap bm, String picName) {
		Log.e("", "保存图片");
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			Log.e("", "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}
	
	public static void delFile(String fileName){
		File file = new File(SDPATH + fileName);
		if(file.isFile()){
			file.delete();
        }
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;
		
		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDir(); // 递规的方式删除文件夹
		}
		dir.delete();// 删除目录本身
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}
	


	public static boolean copyFile(File from, File to) {
		if (!from.isFile() || !to.isFile())
			return false;

		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (Exception e) {
			Log.e(TAG, e.toString());
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					/* Ignore */
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					/* Ignore */
				}
			}
		}
		return true;
	}

	public static boolean writeFile(byte[] data, File file) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getPath(Context context, Uri uri) {

		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { MediaStore.Images.Media.DATA };
			Cursor cursor = null;

			try {
				cursor = context.getContentResolver().query(uri, projection,
						null, null, null);
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				Log.e("msg", column_index + "");
				if (cursor.moveToFirst()) {
					Log.e("msg", cursor.getString(column_index));
					return cursor.getString(column_index);
				}
			} catch (Exception e) {
				// Eat it
			}
		}

		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			Log.e("msg", "进来2");
			return uri.getPath();
		}

		Log.e("msg", "没进来");
		return null;
	}


	public static String getPathfile(Context context, Uri uri) {

		if ("file".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { MediaStore.Images.Media.DATA };
			Cursor cursor = null;

			try {
				cursor = context.getContentResolver().query(uri, projection,
						null, null, null);
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				Log.e("msg", column_index + "");
				if (cursor.moveToFirst()) {
					Log.e("msg", cursor.getString(column_index));
					return cursor.getString(column_index);
				}
			} catch (Exception e) {
				// Eat it
			}
		}

		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			Log.e("msg", "进来2");
			return uri.getPath();
		}

		Log.e("msg", "没进来");
		return null;
	}



}
