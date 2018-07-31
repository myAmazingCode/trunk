package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GpsInfo;
import net.shopnc.b2b2c.android.bean.StoreO2oAddressInfo;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.ui.type.BaiduMapActivity;

import java.util.ArrayList;

/**
 * o2o商家分店地址适配器
 *
 * @author dqw
 * @Time 2015/9/14
 */
public class StoreO2oAddressListViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<StoreO2oAddressInfo> list;

    public StoreO2oAddressListViewAdapter(Context context) {
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

    public void setlist(ArrayList<StoreO2oAddressInfo> list) {
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_store_o2o_address_item, null);
            holder = new ViewHolder();
            holder.llStoreO2oItem = convertView.findViewById(R.id.llStoreO2oItem);
            holder.tvStoreO2oName = convertView.findViewById(R.id.tvStoreO2oName);
            holder.tvStoreO2oAddress = convertView.findViewById(R.id.tvStoreO2oAddress);
            holder.tvDistance = convertView.findViewById(R.id.tvDistance);
            holder.btnStoreO2oPhone = convertView.findViewById(R.id.btnStoreO2oPhone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        StoreO2oAddressInfo bean = list.get(position);
        final String phone = bean.getPhone();
        final double lng = Double.valueOf(bean.getLng());
        final double lat = Double.valueOf(bean.getLat());
        holder.llStoreO2oItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BaiduMapActivity.class);
                ArrayList<GpsInfo> gpsList = new ArrayList<GpsInfo>();
                gpsList.add(new GpsInfo(lng,lat));
                intent.putParcelableArrayListExtra("gps_list", gpsList);
                context.startActivity(intent);
            }
        });
        holder.tvStoreO2oName.setText(bean.getName());
        holder.tvStoreO2oAddress.setText(bean.getAddress());
        holder.tvDistance.setText("距离 " + bean.getDistance());
        holder.btnStoreO2oPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopHelper.call(context, phone);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        LinearLayout llStoreO2oItem;
        TextView tvStoreO2oName, tvStoreO2oAddress,tvDistance;
        ImageButton btnStoreO2oPhone;
    }
}
