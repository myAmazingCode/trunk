package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsBrowseInfo;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;

import java.util.ArrayList;

/**
 * 商品浏览列表适配器
 *
 * @author dqw
 * @Time 2015/8/12
 */
public class GoodsBrowseListViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<GoodsBrowseInfo> list;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public GoodsBrowseListViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<GoodsBrowseInfo> getlist() {
        return list;
    }

    public void setlist(ArrayList<GoodsBrowseInfo> list) {
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_goods_browse_item, null);
            holder = new ViewHolder();
            holder.ivGoodsImage = convertView.findViewById(R.id.ivGoodsImage);
            holder.tvGoodsName = convertView.findViewById(R.id.tvGoodsName);
            holder.tvGoodsPrice = convertView.findViewById(R.id.tvGoodsPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GoodsBrowseInfo bean = list.get(position);
        holder.tvGoodsName.setText(bean.getGoodsName());
        holder.tvGoodsPrice.setText("¥" + bean.getGoodsPrice());
        imageLoader.displayImage(bean.getGoodsImageUrl(), holder.ivGoodsImage, options, animateFirstListener);
        return convertView;
    }

    public class ViewHolder {
        ImageView ivGoodsImage;
        TextView tvGoodsName, tvGoodsPrice;
    }
}
