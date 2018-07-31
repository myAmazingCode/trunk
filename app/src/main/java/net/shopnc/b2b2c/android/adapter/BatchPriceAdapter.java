package net.shopnc.b2b2c.android.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.BatchPriceInfo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BatchPriceAdapter extends BaseAdapter {

    Context context;
    ArrayList<BatchPriceInfo> list;
    LayoutInflater inflater;


    public BatchPriceAdapter(Context context, ArrayList<BatchPriceInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_batch_price, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        BatchPriceInfo batchPriceInfo = list.get(i);
        holder.tvGoodsSalenum.setText(batchPriceInfo.getBatch());
        holder.tvBatchPrice.setText("￥" + (batchPriceInfo.getPrice() == null ? "" : batchPriceInfo.getPrice()));

        return view;
    }

    class ViewHolder {
        TextView tvGoodsSalenum;
        TextView tvBatchPrice;

        ViewHolder(View view) {
            tvGoodsSalenum = view.findViewById(R.id.tvGoodsSalenum);
            tvBatchPrice = view.findViewById(R.id.tvBatchPrice);
        }
    }
}
