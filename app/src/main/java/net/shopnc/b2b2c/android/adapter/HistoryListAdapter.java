package net.shopnc.b2b2c.android.adapter;

import android.content.Context;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.bean.History;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;

import java.util.List;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.adapter.HistoryListAdapter.java
 * @author: Jie
 * @date: 2016-05-26 10:49
 */
public class HistoryListAdapter extends CommonAdapter<History> {

    private static final String TAG = "HistoryListAdapter";

    public HistoryListAdapter(Context context, List<History> datas) {
        super(context, datas, R.layout.listview_history_item);
    }

    @Override
    public void convert(ViewHolder holder, History history) {

        holder.setImage(R.id.his_fri_item_image, history.getImageUrl());
        holder.setText(R.id.his_fri_item_name, history.getName());
        holder.setText(R.id.his_fri_item_price, "¥" + history.getPrice());

    }
}
