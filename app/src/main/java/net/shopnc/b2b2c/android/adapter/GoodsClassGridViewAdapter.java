package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsClassInfo;

import java.util.ArrayList;

/**
 * 商品分类适配器
 *
 * @author dqw
 * @Time 2015-7-2
 */
public class GoodsClassGridViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<GoodsClassInfo> goodsClassInfoList;
    private int selectedItem;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public GoodsClassGridViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goodsClassInfoList == null ? 0 : goodsClassInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsClassInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectedItem(int positon) {
        selectedItem = positon;
    }

    public void setGoodsClassInfoList(ArrayList<GoodsClassInfo> goodsClassInfoList) {
        this.goodsClassInfoList = goodsClassInfoList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.gridview_goods_class_item, null);
            holder = new ViewHolder();
            holder.tvGoodsClassName = convertView.findViewById(R.id.tvGoodsClassName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GoodsClassInfo goodsClassInfo = goodsClassInfoList.get(position);
        holder.tvGoodsClassName.setText(goodsClassInfo.getGcName());

        return convertView;
    }

    class ViewHolder {
        TextView tvGoodsClassName;
    }

}
