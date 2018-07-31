package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.CommissionWithdraw;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.ui.promotion.WithdrawDetailActivity;

/**
 * Created by xws on 2017/2/17.
 */

public class WithdrawAdapter extends RRecyclerAdapter<CommissionWithdraw.DatasBean.CashListBean> {

    public WithdrawAdapter(Context context) {
        super(context, R.layout.cms_balance_list_item);
    }

    @Override
    public void convert(RecyclerHolder holder, final CommissionWithdraw.DatasBean.CashListBean withDrawDeposit) {
        Spanned spanned = Html.fromHtml("<font <b>提现审核：" + withDrawDeposit.getStateText() + "</b></font>");
        holder.setText(R.id.mine_deposit_order, spanned)
                .setText(R.id.tvOrderNo, "单号：" + withDrawDeposit.getCashSn())
                .setText(R.id.mine_deposit_time, withDrawDeposit.getAddTime());

        holder.setTextColorRes(R.id.mine_deposit_payment, R.color.green)
                .setText(R.id.mine_deposit_payment, ShopHelper.getPriceString(withDrawDeposit.getAmount()));

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WithdrawDetailActivity.class);
                intent.putExtra("cashId",withDrawDeposit.getCashId());
                context.startActivity(intent);
            }
        });
    }
}
