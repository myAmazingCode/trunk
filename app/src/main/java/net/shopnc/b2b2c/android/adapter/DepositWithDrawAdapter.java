package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.PredepositInfo;
import net.shopnc.b2b2c.android.bean.WithDrawDeposit;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.ui.mine.PredepositWithdrawDetailActivity;
import net.shopnc.b2b2c.android.ui.promotion.WithdrawDetailActivity;

/**
 * 提现模块
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.adapter.DepositWithDrawAdapter.java
 * @author: Jie
 * @date: 2016-05-29 09:24
 */
public class DepositWithDrawAdapter extends RRecyclerAdapter<WithDrawDeposit> {

    public DepositWithDrawAdapter(Context context) {
        super(context, R.layout.item_deposit_withdraw);
    }

    @Override
    public void convert(RecyclerHolder holder, final WithDrawDeposit withDrawDeposit) {
        holder.setText(R.id.mine_deposit_state, "提现审核：" + withDrawDeposit.getStateText())
                .setText(R.id.mine_deposit_order, "提现单号：" + withDrawDeposit.getCashSn())
                .setText(R.id.mine_deposit_time, withDrawDeposit.getAddTime());

//        if (withDrawDeposit.getAmount() <= 0) {
        holder.setTextColorRes(R.id.mine_deposit_payment, R.color.green)
                .setText(R.id.mine_deposit_payment, ShopHelper.getPriceString(withDrawDeposit.getAmount()));
//        } else {
//            holder.setTextColorRes(R.id.mine_deposit_payment, R.color.nc_red)
//                    .setText(R.id.mine_deposit_payment, "+" + ShopHelper.getPriceString(withDrawDeposit.getAmount()));
//        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PredepositWithdrawDetailActivity.class);
                int cashId = withDrawDeposit.getCashId();
                intent.putExtra("cashId", String.valueOf(cashId));
                context.startActivity(intent);
            }
        });
    }
}
