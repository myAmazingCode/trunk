package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.PdcashInfo;

import java.util.ArrayList;

/**
 * 预存款提现适配器
 * <p/>
 * dqw
 * 2015/8/25
 */
public class PdcashListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<PdcashInfo> list;

    public PdcashListViewAdapter(Context context) {
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

    public void setList(ArrayList<PdcashInfo> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_pdcash_item, parent, false);
            holder = new ViewHolder();
            holder.tvDesc = convertView.findViewById(R.id.tvDesc);
            holder.tvSn = convertView.findViewById(R.id.tvSn);
            holder.tvAmount = convertView.findViewById(R.id.tvAmount);
            holder.tvAddTimeText = convertView.findViewById(R.id.tvAddTimeText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PdcashInfo info = list.get(position);
        holder.tvDesc.setText("提现审核" + "：" + info.getPaymentStateText());
        holder.tvSn.setText("提现单号：" + info.getSn());
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
