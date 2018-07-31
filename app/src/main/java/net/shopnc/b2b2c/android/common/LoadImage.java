package net.shopnc.b2b2c.android.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.shopnc.b2b2c.android.R;

/**
 * 加载网络图片
 *
 * @author huting
 * @date 2016/1/7
 */
public class LoadImage {
    public static void loadImg(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.ic_launcher)  //设置占位图
                .error(R.drawable.ic_launcher)      //加载错误图
                .centerCrop()
                .into(imageView);
    }

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

}
