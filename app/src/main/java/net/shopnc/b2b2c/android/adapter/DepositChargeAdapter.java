package net.shopnc.b2b2c.android.adapter;


import android.content.Context;
import android.text.Html;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.DepositRecharge;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;

/**
 * 充值明细
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.adapter.DepositNumAdapter.java
 * @author: Jie
 * @date: 2016-05-27 10:10
 */
public class DepositChargeAdapter extends RRecyclerAdapter<DepositRecharge> {

    public DepositChargeAdapter(Context context) {
        super(context, R.layout.item_deposit_view);
    }

    @Override
    public void convert(RecyclerHolder holder, DepositRecharge depositRecharge) {
        holder.setText(R.id.mine_deposit_order, Html.fromHtml(depositRecharge.getPaymentName() + "：" + depositRecharge.getPayStateText()) + "\n充值单号：" + depositRecharge.getRechargeSn())
                .setText(R.id.mine_deposit_time, depositRecharge.getAddTime());

        if (depositRecharge.getAmount() <= 0) {
            holder.setTextColorRes(R.id.mine_deposit_payment, R.color.green)
                    .setText(R.id.mine_deposit_payment, ShopHelper.getPriceString(depositRecharge.getAmount()));
        } else {
            holder.setTextColorRes(R.id.mine_deposit_payment, R.color.nc_red)
                    .setText(R.id.mine_deposit_payment, "+" + ShopHelper.getPriceString(depositRecharge.getAmount()));
        }

    }

}
