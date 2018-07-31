package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.TryVoucherBean;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/2/16 10:29.
 */

public class TryVoucherAdapter extends RRecyclerAdapter<TryVoucherBean> {

    private String moneyRmb;

    public TryVoucherAdapter(Context context) {
        super(context, R.layout.mine_discount_coupon_item);
        this.moneyRmb = context.getResources().getString(R.string.money_rmb);
    }

    @Override
    public void convert(RecyclerHolder holder, final TryVoucherBean tryVoucherBean) {
        View view = holder.getConvertView();
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        holder.setText(R.id.coupon_name, tryVoucherBean.getStoreName())
                .setText(R.id.coupon_store_type, tryVoucherBean.getVoucherStateText())
                .setText(R.id.coupon_time, "有效期至：" + tryVoucherBean.getEndTimeText())
                .setImage(R.id.coupon_image, tryVoucherBean.getStoreAvatarUrl());

        holder.setText(R.id.coupon_store_money, moneyRmb + ShopHelper.getPriceString(tryVoucherBean.getPrice()))
                .setText(R.id.coupon_store_condition, Html.fromHtml("无金额限制<br>(全平台适用)"));

        if (tryVoucherBean.getVoucherState() == 1 && tryVoucherBean.getVoucherState1() == 2) {
            holder.setBackgroundRes(R.id.coupon_background, R.color.nc_red);
        } else {
            holder.setBackgroundRes(R.id.coupon_background, R.color.text_gray);
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopHelper.gotoStoreActivity(context, tryVoucherBean.getStoreId());
            }
        });
    }
}
