package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.PdrechargeInfo;

import java.util.ArrayList;

/**
 * 预存款日志适配器
 * <p/>
 * dqw
 * 2015/8/25
 */
public class PdrechargeListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<PdrechargeInfo> list;

    public PdrechargeListViewAdapter(Context context) {
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

    public void setList(ArrayList<PdrechargeInfo> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_pdrecharge_item, parent, false);
            holder = new ViewHolder();
            holder.tvDesc = convertView.findViewById(R.id.tvDesc);
            holder.tvSn = convertView.findViewById(R.id.tvSn);
            holder.tvAmount = convertView.findViewById(R.id.tvAmount);
            holder.tvAddTimeText = convertView.findViewById(R.id.tvAddTimeText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PdrechargeInfo info = list.get(position);
        holder.tvDesc.setText(info.getPaymentName() + "：" + info.getPaymentStateText());
        holder.tvSn.setText("充值单号：" + info.getSn());
        holder.tvAmount.setText(info.getAmount());
        holder.tvAddTimeText.setText(info.getAddTimeText());

        return convertView;
    }

    class ViewHolder {
        TextView tvDesc;
        TextView tvSn;
        TextView tvAmount;
        TextView tvAddTimeText;
    }
}
