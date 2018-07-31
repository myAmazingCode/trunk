package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.CityList;

import java.util.ArrayList;

/**
 * 地区Spinner适配器
 */
public class AreaSpinnerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<CityList> areaList;

    public AreaSpinnerAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return areaList == null ? 0 : areaList.size();
    }

    @Override
    public Object getItem(int position) {
        return areaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setAreaList(ArrayList<CityList> areaList) {
        this.areaList = areaList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.spinner_area_item, null);
            holder = new ViewHolder();
            holder.tvAreaName = convertView.findViewById(R.id.tvAreaName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CityList areaInfo = areaList.get(position);
        holder.tvAreaName.setText(areaInfo.getArea_name());

        return convertView;
    }

    class ViewHolder {
        TextView tvAreaName;
    }
}
