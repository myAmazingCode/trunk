package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;

import java.util.ArrayList;

/**
 * Created by dqw on 2015/8/6.
 */
public class ImageSwitchPagerAdapter extends PagerAdapter {

    private ArrayList<String> imageList;
    private ImageView[] mImageViews;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public ImageSwitchPagerAdapter(Context context, ArrayList<String> imageList) {
        this.imageList = imageList;
        //将图片装载到数组中
        mImageViews = new ImageView[imageList.size()];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(context);
            imageLoader.displayImage(imageList.get(i), imageView, options, animateFirstListener);
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
        ((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);

    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
        return mImageViews[position % mImageViews.length];
    }
}
