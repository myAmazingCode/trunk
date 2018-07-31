package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.ManSongRules;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;

import java.util.ArrayList;

/**
 * 地区Spinner适配器
 */
public class ManSongRuleListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ManSongRules> list;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public ManSongRuleListViewAdapter(Context context) {
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

    public void setList(ArrayList<ManSongRules> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_mansong_rule_item, null);
            holder = new ViewHolder();
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.tvDiscount = convertView.findViewById(R.id.tvDiscount);
            holder.llGoods = convertView.findViewById(R.id.llGoods);
            holder.ivGoods = convertView.findViewById(R.id.ivGoods);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ManSongRules bean = list.get(position);
        holder.tvPrice.setText(bean.getPrice());
        holder.tvDiscount.setText(bean.getDiscount());
        if (!bean.getGoods_id().equals("0")) {
            holder.llGoods.setVisibility(View.VISIBLE);
            imageLoader.displayImage(bean.getGoods_image_url(), holder.ivGoods, options, animateFirstListener);
        }

        return convertView;
    }

    class ViewHolder {
        TextView tvPrice;
        TextView tvDiscount;
        LinearLayout llGoods;
        ImageView ivGoods;
    }
}
