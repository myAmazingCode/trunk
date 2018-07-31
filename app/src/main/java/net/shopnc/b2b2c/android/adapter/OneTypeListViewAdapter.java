package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.OneType;

import java.util.ArrayList;

/**
 * 一级分类适配器
 *
 * @author dqw
 * @Time 2015-7-2
 */
public class OneTypeListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<OneType> typeList;
    private int selectedItem;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public OneTypeListViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return typeList == null ? 0 : typeList.size();
    }

    @Override
    public Object getItem(int position) {
        return typeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectedItem(int positon) {
        selectedItem = positon;
    }

    public void setTypeList(ArrayList<OneType> typeList) {
        this.typeList = typeList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listivew_type_item, null);
            holder = new ViewHolder();
            holder.ivGoodsClassImage = convertView.findViewById(R.id.ivGoodsClassImage);
            holder.tvGoodsClassName = convertView.findViewById(R.id.tvGoodsClassName);
            holder.tvLine = convertView.findViewById(R.id.tvLine);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        OneType bean = typeList.get(position);
        holder.tvGoodsClassName.setText(bean.getGc_name());
        if (position != selectedItem) {
            holder.tvGoodsClassName.setTextColor(context.getResources().getColor(R.color.nc_text));
            holder.tvLine.setBackgroundColor(context.getResources().getColor(R.color.nc_border));
            ViewGroup.LayoutParams params = holder.tvLine.getLayoutParams();
            params.height = 1;
            holder.tvLine.setLayoutParams(params);
        } else {
            holder.tvGoodsClassName.setTextColor(context.getResources().getColor(R.color.nc_btn_bg));
            holder.tvLine.setBackgroundColor(context.getResources().getColor(R.color.nc_btn_bg));
            ViewGroup.LayoutParams params = holder.tvLine.getLayoutParams();
            params.height = 6;
            holder.tvLine.setLayoutParams(params);
        }

        //加载分类图标
        imageLoader.loadImage(bean.getImage(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.ivGoodsClassImage.setImageBitmap(loadedImage);
                if (position != selectedItem) {
                    holder.ivGoodsClassImage.getDrawable().setColorFilter(R.color.nc_fg, PorterDuff.Mode.MULTIPLY);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView ivGoodsClassImage;
        TextView tvGoodsClassName;
        TextView tvLine;
    }

}
