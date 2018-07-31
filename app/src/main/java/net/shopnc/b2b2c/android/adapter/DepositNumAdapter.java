package net.shopnc.b2b2c.android.adapter;


import android.content.Context;
import android.text.Html;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.DepositLogInfo;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;

/**
 * 账户余额
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.adapter.DepositNumAdapter.java
 * @author: Jie
 * @date: 2016-05-27 10:10
 */
public class DepositNumAdapter extends RRecyclerAdapter<DepositLogInfo> {

    public DepositNumAdapter(Context context) {
        super(context, R.layout.item_deposit_view);
    }

    @Override
    public void convert(RecyclerHolder holder, DepositLogInfo depositLogInfo) {
        holder.setText(R.id.mine_deposit_order, Html.fromHtml(depositLogInfo.getDescription()))
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
