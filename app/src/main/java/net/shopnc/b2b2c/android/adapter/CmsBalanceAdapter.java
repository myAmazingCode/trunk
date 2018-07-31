package net.shopnc.b2b2c.android.adapter;

import android.content.Context;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.CommissionBalance;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;

/**
 * Created by xws on 2017/2/17.
 */

public class CmsBalanceAdapter extends RRecyclerAdapter<CommissionBalance.DatasBean.LogListBean> {

    public CmsBalanceAdapter(Context context) {
        super(context, R.layout.item_deposit_view);
    }

    @Override
    public void convert(RecyclerHolder holder, CommissionBalance.DatasBean.LogListBean depositLogInfo) {
        holder.setText(R.id.mine_deposit_order, depositLogInfo.getDescription())
                .setText(R.id.mine_deposit_time, depositLogInfo.getAddTime());

        if (depositLogInfo.getAvailableAmount() <= 0) {
            holder.setTextColorRes(R.id.mine_deposit_payment, R.color.green)
                    .setText(R.id.mine_deposit_payment, ShopHelper.getPriceString(depositLogInfo.getAvailableAmount()))
                    .setTextColorRes(R.id.mine_deposit_yuan, R.color.green);
        } else {
            holder.setTextColorRes(R.id.mine_deposit_payment, R.color.nc_red)
                    .setText(R.id.mine_deposit_payment, "+" + ShopHelper.getPriceString(depositLogInfo.getAvailableAmount()))
                    .setTextColorRes(R.id.mine_deposit_yuan, R.color.nc_red);
        }
    }
}
