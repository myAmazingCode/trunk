package net.shopnc.b2b2c.android.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.shopnc.b2b2c.R;

import java.io.File;


/**
 * 网络图片加载帮助类
 *
 * @author qyf
 * @date 2016-05-19
 */
public class LoadImage {

    /**
     * 加载网络图片
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public static void loadRemoteImg(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }

    /**
     * 加载网络图片（有尺寸）
     *
     * @param context
     * @param imageView
     * @param url
     * @param width     px
     * @param height    px
     */
    public static void loadRemoteImg(Context context, ImageView imageView, String url, int width, int height) {
        String[] s = url.split("\\.");
        String imgUrl = url + "_" + width + "x" + height + "." + s[s.length - 1];
        Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }


    /**
     * 加载本地图片
     *
     * @param context
     * @param imageView
     * @param filePath
     */
    public static void loadLocalImg(Context context, ImageView imageView, String filePath) {
        Glide.with(context)
                .load(new File(filePath))
                .dontAnimate()        //取消默认的动画
                .into(imageView);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param imageView
     * @param resId
     */
    public static void loadLocalGreyImg(Context context, ImageView imageView, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        Bitmap bitmap = drawableToBitmap(drawable);
        imageView.setImageBitmap(grey(context, bitmap));
    }

    private static Bitmap drawableToBitmap(Drawable drawable) // drawable 转换成bitmap
    {
        int width = drawable.getIntrinsicWidth();// 取drawable的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);// 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 把drawable内容画到画布中
        return bitmap;
    }

    private static Bitmap grey(Context context, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Bitmap alphaBitmap = bitmap.extractAlpha();
        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
//        ColorMatrix colorMatrix = new ColorMatrix();
//        colorMatrix.setSaturation(0);
//        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
//                colorMatrix);
//        paint.setColorFilter(colorMatrixFilter);
        paint.setColor(context.getResources().getColor(R.color.buystep_sku_text));
        canvas.drawBitmap(alphaBitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

    /**
     * 清除图片的缓存
     *
     * @param context
     */
    public static void clearCache(Context context) {
        Glide.get(context).clearMemory();
        //Glide.get(context).clearDiskCache(); //需要在子线程执行
    }

    /**
     * 圆角图片
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadRemoteCircleImg(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载旋转图片
     *
     * @param context
     * @param imageView
     * @param degree
     * @param resourceId
     */
    public static void loadImageRotated(Context context, ImageView imageView, float degree, int resourceId) {
        Glide.with(context)
                .load(resourceId)
                .transform(new RotateTransformation(context, degree))
                .into(imageView);
    }

    /**
     * 通过文件选择器返回的Uri获取文件Path
     * 由于4.4及以上版本返回的Uri会有多种格式需要提前判断处理再调用getImagePath
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getPath(Context context, Uri uri) {
        String imagePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(context, uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return imagePath;
    }

    private static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }

            cursor.close();
        }
        return path;
    }
}
