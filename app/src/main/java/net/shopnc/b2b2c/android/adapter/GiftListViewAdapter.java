package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GiftArrayList;

import java.util.ArrayList;

/**
 * 地区Spinner适配器
 */
public class GiftListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<GiftArrayList> list;

    public GiftListViewAdapter(Context context) {
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

    public void setList(ArrayList<GiftArrayList> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_gift_item, null);
            holder = new ViewHolder();
            holder.tvGoodsName = convertView.findViewById(R.id.tvGoodsName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final GiftArrayList bean = list.get(position);
        holder.tvGoodsName.setText(bean.getGift_goodsname() + " x " + bean.getGift_amount());
        return convertView;
    }

    class ViewHolder {
        TextView tvGoodsName;
    }
}
