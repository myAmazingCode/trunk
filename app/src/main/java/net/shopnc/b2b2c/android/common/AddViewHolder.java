package net.shopnc.b2b2c.android.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.util.LoadImage;

/**
 * Created by qyf on 2016/3/14.
 */
public class AddViewHolder {
    private LayoutInflater inflater;
    private Context context;
    private View customView;
    private SparseArray<View> mViews;

    public AddViewHolder(Context context, int layoutId) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        customView = inflater.inflate(layoutId, null);
        this.mViews = new SparseArray<View>();
        customView.setTag(this);
    }

//    public static AddViewHolder get(Context context, int layoutId) {
//        if (customView == null) {
//            return new AddViewHolder(context, layoutId);
//        } else {
//            AddViewHolder holder = (AddViewHolder) customView.getTag();
//            return holder;
//        }
//    }

    public View getCustomView() {
        return customView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = customView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public AddViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public AddViewHolder setTag(int position) {
        customView.setTag(position);
        return this;
    }

    public AddViewHolder setText(int viewId, CharSequence text, TextView.BufferType type) {
        TextView tv = getView(viewId);
        tv.setText(text, type);
        return this;
    }

    public AddViewHolder setImage(int viewId, String url) {
        ImageView view = getView(viewId);
        LoadImage.loadRemoteImg(context, view, url);
        return this;
    }

    public AddViewHolder setImageRed(int viewId, String url) {
        final ImageView view = getView(viewId);
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_mine_bg)      //加载错误图
//                .diskCacheStrategy(DiskCacheStrategy.ALL)     //全缓存
                .centerCrop()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        resource.setColorFilter(context.getResources().getColor(R.color.nc_text), PorterDuff.Mode.MULTIPLY);
//                        view.setImageDrawable(resource);
//                        view.invalidateDrawable(resource);
                        GlideDrawable d = resource;
                        Bitmap b = drawableToBitmap(d);
                        view.setImageBitmap(red(b));
                    }
                });

        return this;
    }

    private Bitmap red(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
//        ColorMatrix colorMatrix = new ColorMatrix();
//        colorMatrix.setSaturation(0);
//        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
//                colorMatrix);
//        paint.setColorFilter(colorMatrixFilter);
        paint.setColor(context.getResources().getColor(R.color.nc_btn_bg));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

    public AddViewHolder setImageGrey(int viewId, String url) {//TODO
        final ImageView view = getView(viewId);
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_mine_bg)      //加载错误图
//                .diskCacheStrategy(DiskCacheStrategy.ALL)     //全缓存
                .centerCrop()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        resource.setColorFilter(context.getResources().getColor(R.color.nc_text), PorterDuff.Mode.MULTIPLY);
//                        view.setImageDrawable(resource);
//                        view.invalidateDrawable(resource);
                        GlideDrawable d = resource;
                        Bitmap b = drawableToBitmap(d);
                        view.setImageBitmap(grey(b));
                    }
                });

        return this;
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

    private static Bitmap grey(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

    public void setImage(ImageView view, String url) {
        LoadImage.loadRemoteImg(context, view, url);
    }

    public void setImageLocal(ImageView view, String url) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_mine_bg)      //加载错误图
//                .diskCacheStrategy(DiskCacheStrategy.ALL)     //全缓存
                .centerCrop()
                .into(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AddViewHolder setImageDrawbleToBitmap(int viewId, int res) {
        ImageView iv = getView(viewId);
        Drawable d = context.getDrawable(res);
        Bitmap b = drawableToBitmap(d);
        iv.setImageBitmap(grey(b));
        return this;
    }

    public AddViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public AddViewHolder setOnClickListener(int viewId,
                                            View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public Bitmap getBitmap(int viewId) {
        ImageView iv = getView(viewId);
        Bitmap b = iv.getDrawingCache();
        return b;
    }

    public void setColorFilter(int viewId, int color, PorterDuff.Mode type) {//
        ImageView iv = getView(viewId);
        iv.getDrawable().setColorFilter(color, type);
        iv.invalidate();
    }

    public AddViewHolder setColorFilter(int viewId) {
        ImageView iv = getView(viewId);

        Bitmap b = iv.getDrawingCache();
        Canvas canvas = new Canvas(b);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(b, 0, 0, paint);
        iv.setImageBitmap(b);
        return this;
    }

    public AddViewHolder setIMage(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    public void clearColorFilter(int viewId) {
        ImageView view = getView(viewId);
        view.getDrawable().clearColorFilter();
        view.invalidate();
    }

    public AddViewHolder setTextColor(int viewId, int color) {
        TextView tv = getView(viewId);
        tv.setTextColor(context.getResources().getColor(color));
        return this;
    }

    public AddViewHolder setBackgroundColor(int viewId, int color) {
        TextView tv = getView(viewId);
        tv.setBackgroundColor(context.getResources().getColor(color));
        return this;
    }

    public AddViewHolder setDimensionPixelSize(int viewId, int dimes) {
        TextView tv = getView(viewId);
        ViewGroup.LayoutParams params = tv.getLayoutParams();
        params.height = context.getResources().getDimensionPixelSize(dimes);
        tv.setLayoutParams(params);
        return this;
    }

    public String getText(int viewId) {
        TextView tv = getView(viewId);
        return tv.getText().toString();
    }

    public AddViewHolder setImageDrawable(int viewId, int res) {
        ImageView iv = getView(viewId);
        iv.setImageDrawable(context.getResources().getDrawable(res));
        return this;
    }

    public AddViewHolder setImageDrawable(int viewId, Drawable res) {
        ImageView iv = getView(viewId);
        iv.setImageDrawable(res);
        return this;
    }

    public AddViewHolder setView(int viewId, View view) {
        LinearLayout layout = getView(viewId);
        layout.addView(view);
        return this;
    }

    public AddViewHolder setAdapter(int viewId, ListAdapter adapter) {
        ListView layout = getView(viewId);
        layout.setAdapter(adapter);
        return this;
    }

    public AddViewHolder setGridAdapter(int viewId, ListAdapter adapter) {
        GridView layout = getView(viewId);
        layout.setAdapter(adapter);
        return this;
    }

//    public AddViewHolder setSelected(int viewId, boolean flag) {
//        ImageButton imageButton = getView(viewId);
//        imageButton.setSelected(flag);
//        return this;
//    }

    public AddViewHolder setSelected(int viewId, boolean flag) {
        ImageView imageButton = getView(viewId);
        imageButton.setSelected(flag);
        return this;
    }

    public boolean isselected(int viewId) {
        ImageView imageButton = getView(viewId);
        boolean flag = imageButton.isSelected();
        return flag;
    }

    public AddViewHolder setClickable(int viewId, boolean flag) {
        ImageButton imageButton = getView(viewId);
        imageButton.setClickable(flag);
        return this;
    }

    public boolean isClickable(int viewId) {
        ImageButton imageButton = getView(viewId);
        boolean flag = imageButton.isClickable();
        return flag;
    }
}
