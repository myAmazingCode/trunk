package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.PointLogInfo;

import java.util.ArrayList;

/**
 * 积分日志适配器
 *
 * dqw
 * 2015/8/25
 */
public class PointLogListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<PointLogInfo> list;

    public PointLogListViewAdapter(Context context) {
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

    public void setList(ArrayList<PointLogInfo> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_point_log_item, parent, false);
            holder = new ViewHolder();
            holder.tvDesc = convertView.findViewById(R.id.tvDesc);
            holder.tvSn = convertView.findViewById(R.id.tvSn);
            holder.tvPoints = convertView.findViewById(R.id.tvPoints);
            holder.tvAddTimeText = convertView.findViewById(R.id.tvAddTimeText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PointLogInfo info = list.get(position);
        holder.tvDesc.setText(info.getStageText());
        holder.tvSn.setText(info.getDesc());
        int points = Integer.valueOf(info.getPoints());
        if(points > 0) {
            holder.tvPoints.setText("+" + info.getPoints());
            holder.tvPoints.setTextColor(context.getResources().getColor(R.color.nc_red));
        } else {
            holder.tvPoints.setText(info.getPoints());
            holder.tvPoints.setTextColor(context.getResources().getColor(R.color.nc_green));
        }
        holder.tvAddTimeText.setText(info.getAddTimeText());

        return convertView;
    }

    class ViewHolder {
        TextView tvDesc;
        TextView tvSn;
        TextView tvPoints;
        TextView tvAddTimeText;
    }
}
